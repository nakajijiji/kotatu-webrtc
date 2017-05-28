package com.kotatu.webrtc;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        ByteBuffer bf = ByteBuffer.allocateDirect(10);
        for(byte b : bf.array()){
            System.out.println(b);
        }
    }
}