package com.kotatu.webrtc;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by mayuhei on 2017/05/25.
 */

public class Experiment extends UnityPlayerActivity {
    private String hoge = "hoge";

    public static Experiment instance(){
        Experiment experiment = new Experiment();
        return experiment;
    }

    public String sendUnity(){
        UnityPlayer.UnitySendMessage("Main Camera", "Hey", "this is oppai");
        return "this is real";
    }
}
