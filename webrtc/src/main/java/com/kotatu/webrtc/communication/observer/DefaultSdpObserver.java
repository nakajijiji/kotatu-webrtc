package com.kotatu.webrtc.communication.observer;

import android.util.Log;

import com.github.nkzawa.socketio.client.Socket;
import com.kotatu.webrtc.communication.DefaultMediaConstraints;
import com.kotatu.webrtc.communication.SocketMessageKey;
import com.kotatu.webrtc.communication.message.Message;
import com.kotatu.webrtc.communication.message.SdpMessage;
import com.kotatu.webrtc.util.JsonSerializer;

import org.webrtc.PeerConnection;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

/**
 * Created by mayuhei on 2017/05/15.
 */

public class
DefaultSdpObserver implements SdpObserver {
    private final String TAG = this.getClass().getCanonicalName();

    private PeerConnection connection;
    private Socket socket;
    private Message recievedMessage;
    private boolean sendAnswer = false;

    public DefaultSdpObserver(PeerConnection connection, Socket socket, Message recievedMessage) {
        super();
        this.connection = connection;
        this.socket = socket;
        this.recievedMessage = recievedMessage;
    }

    public DefaultSdpObserver answerMode(boolean sendAnswer){
        this.sendAnswer = sendAnswer;
        return this;
    }

    @Override
    public void onCreateSuccess(SessionDescription sessionDescription) {
        Log.d(TAG, " onCreateSuccess()");
        connection.setLocalDescription(new DoNothingSdpObserver(), sessionDescription);
        SdpMessage message = new SdpMessage();
        // send back to the person who send sdp offer
        message.setTo(recievedMessage.getFrom());
        message.setSessionDescription(sessionDescription);
        message.setRoomId(recievedMessage.getRoomId());
        Message.Type type = sendAnswer? Message.Type.ANSWER : Message.Type.OFFER;
        message.setType(type);
        socket.emit(SocketMessageKey.MESSAGE.toString(), JsonSerializer.serialize(message));
    }

    @Override
    public void onSetSuccess() {
        Log.d(TAG, "so1 onSetSuccess()");
        connection.createAnswer(this, new DefaultMediaConstraints());
    }

    @Override
    public void onCreateFailure(String s) {
        Log.d(TAG, "so1 onCreateFailure() " + s);
    }

    @Override
    public void onSetFailure(String s) {
        Log.d(TAG, "so1 onSetFailure() " + s);
    }
}
