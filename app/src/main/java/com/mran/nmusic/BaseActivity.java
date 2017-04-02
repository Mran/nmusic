package com.mran.nmusic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 张孟尧 on 2016/8/4.
 */
public class BaseActivity extends AppCompatActivity {
    private BaseBroadReciver mBaseBroadReciver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseBroadReciver = new BaseBroadReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.MUSIC_CHANGE);
        filter.addAction(Constant.MUSIC_CONTORL_PLAY);
        filter.addAction(Constant.MUSIC_CONTORL_STOP);

        registerReceiver(mBaseBroadReciver, filter);
    }

    public void changeMusicUI() {

    }

    public void play() {

    }

    public void stop() {

    }

    class BaseBroadReciver extends BroadcastReceiver {
        String action;

        @Override
        public void onReceive(Context context, Intent intent) {
            action = intent.getAction();
            switch (action) {
                case Constant.MUSIC_CHANGE:
                    changeMusicUI();
                    break;
                case Constant.MUSIC_CONTORL_PLAY:
                    play();
                    break;
                case Constant.MUSIC_CONTORL_STOP:
                    stop();
                    break;
            }
        }
    }


}
