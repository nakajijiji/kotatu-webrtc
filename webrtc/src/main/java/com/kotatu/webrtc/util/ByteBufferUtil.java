package com.kotatu.webrtc.util;

import java.nio.ByteBuffer;

/**
 * Created by mayuhei on 2017/05/28.
 */

public class ByteBufferUtil {
    public static byte[] toArray(ByteBuffer byteBuffer){
        if(byteBuffer.hasArray()){
            return byteBuffer.array();
        }
        byte[] result = new byte[byteBuffer.remaining()];
        byteBuffer.get(result, 0, result.length);
        return result;
    }
}
