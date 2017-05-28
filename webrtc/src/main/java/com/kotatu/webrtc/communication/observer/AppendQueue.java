package com.kotatu.webrtc.communication.observer;

import org.webrtc.DataChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by mayuhei on 2017/05/25.
 */

public class AppendQueue implements DefaultDataChannelObserver.OnMessageCallback{
    private Map<String, LinkedBlockingQueue<DataChannel.Buffer>> realtimeActionMap = new HashMap<>();
    private OnQueueCreated onQueueCreated;

    public AppendQueue(Map<String, LinkedBlockingQueue<DataChannel.Buffer>> queue, OnQueueCreated onQueueCreated){
        this.realtimeActionMap = queue;
        this.onQueueCreated = onQueueCreated;
    }

    @Override
    public void call(String socketId, DataChannel.Buffer buffer) {
        LinkedBlockingQueue<DataChannel.Buffer> queue = realtimeActionMap.get(socketId);
        if(queue == null){
            queue = new LinkedBlockingQueue<>(100);
            realtimeActionMap.put(socketId, queue);
            onQueueCreated.call(socketId);
        }
        queue.add(buffer);
    }

    @Override
    public void destroy() {
        realtimeActionMap = new HashMap<>();
    }

    public static interface OnQueueCreated {
        void call(String socketId);
    }
}
