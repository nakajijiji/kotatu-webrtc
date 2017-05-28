package com.kotatu.webrtc.communication.message;

/**
 * Created by mayuhei on 2017/05/18.
 */

public class CallMe extends Message{
    public CallMe(){
        this.type = Type.CALLME;
    }
}
