package com.mran.nmusic.mine.presenter;

import android.content.Context;

import com.mran.nmusic.mine.view.IMineFragment;

/**
 * Created by 张孟尧 on 2017/2/2.
 */

public class MinePresenterCompl {
    private IMineFragment iMineFragment;
    private Context context;

    public MinePresenterCompl(Context context, IMineFragment iMineFragment) {
        this.context = context;
        this.iMineFragment = iMineFragment;
    }
    public void getMyMusicList(String userId)
    {

    }
}
