package com.kotatu.webrtc.communication;

import com.kotatu.webrtc.communication.DefaultCommunicationManager;
import com.kotatu.webrtc.communication.observer.AppendQueue;
import com.kotatu.webrtc.communication.observer.DefaultDataChannelObserver;
import com.kotatu.webrtc.util.ByteBufferUtil;
import com.unity3d.player.UnityPlayer;

import org.webrtc.DataChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by mayuhei on 2017/05/28.
 */

public class UnityCommunicationManager extends DefaultCommunicationManager {
    private Map<String, LinkedBlockingQueue<DataChannel.Buffer>> queueMap = new HashMap<>();

    @Override
    public DefaultDataChannelObserver.OnMessageCallback buildOnMessageCallback() {
        return new AppendQueue(queueMap, new AppendQueue.OnQueueCreated() {
            @Override
            public void call(String socketId) {
                UnityPlayer.UnitySendMessage("AndroidHelper", "onQueueCreated", socketId);
            }
        });
    }

    @Override
    public byte[] dequeueStreamData(String roomId, String socketId) {
        LinkedBlockingQueue<DataChannel.Buffer> queue = queueMap.get(socketId);
        DataChannel.Buffer buffer = queue.poll();
        if(buffer == null){
            return null;
        }
        return ByteBufferUtil.toArray(buffer.data);
    }
}
