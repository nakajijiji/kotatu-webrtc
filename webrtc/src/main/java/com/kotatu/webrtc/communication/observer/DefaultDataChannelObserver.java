package com.kotatu.webrtc.communication.observer;

import android.media.AudioTrack;
import android.util.Log;

import com.kotatu.webrtc.communication.DefaultCommunicationManager;

import org.webrtc.DataChannel;

/**
 * Created by mayuhei on 2017/05/15.
 */

public class DefaultDataChannelObserver implements DataChannel.Observer{
    private final static String TAG = DefaultCommunicationManager.class.getCanonicalName();
    private DataChannel dataChannel;
    private String socketId;
    private OnMessageCallback callback;
    private AudioTrack track;

    public DefaultDataChannelObserver(String socketId, DataChannel dataChannel, OnMessageCallback callback){
        this.dataChannel = dataChannel;
        this.callback = callback;
    }

    @Override
    public void onStateChange() {
        Log.d(TAG, "onStateChange() " + dataChannel.state().name());
    }

    @Override
    public void onMessage(DataChannel.Buffer buffer) {
        callback.call(socketId, buffer);
    }

    @Override
    public void onBufferedAmountChange(long l) {

    }

    public static interface OnMessageCallback {
        void call(String socketId, DataChannel.Buffer buffer);

        void destroy();
    }
}
