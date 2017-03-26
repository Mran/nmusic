package com.mran.nmusic.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.mran.nmusic.BaseApplication;
import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.Constant;

/**
 * Created by 张孟尧 on 2017/1/14.
 */

public class MusicControlBroadReciver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case Constant.MUSIC_CONTORL_PLAY:
                if (BuildConfig.DEBUG)
                    Log.d("MusicControlBroadRecive", "play");
                Intent stopIntent = new Intent(Constant.MUSIC_CONTORL_STOP);
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(stopIntent);

                break;
            case Constant.MUSIC_CONTORL_PRE:
                if (BuildConfig.DEBUG)
                    Log.d("MusicControlBroadRecive", "pre");

                break;
            case Constant.MUSIC_CONTORL_NEXT:
                if (BuildConfig.DEBUG)
                    Log.d("MusicControlBroadRecive", "next");
                Intent nextIntent = new Intent(Constant.MUSIC_CONTORL_NEXT);
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(nextIntent);
                break;
            case Constant.MUSIC_CONTORL_STOP:
                break;
        }
    }
}
