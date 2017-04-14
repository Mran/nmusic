package com.mran.nmusic.mainactivity.presenter;

import android.content.Context;

import com.mran.nmusic.mainactivity.view.IMainActivity;

/**
 * Created by 张孟尧 on 2016/10/11.
 */

public class MainActivityPresenterCompl implements IMainActivityPresenter {
    Context context;
    IMainActivity iMainActivity;

    public MainActivityPresenterCompl(Context context, IMainActivity iMainActivity) {
        this.context = context;
        this.iMainActivity = iMainActivity;
    }


}
