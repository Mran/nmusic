package com.mran.nmusic.mainactivity.view;

import com.mran.nmusic.bean.MusicListDetailBean;

/**
 * Created by 张孟尧 on 2016/10/11.
 */

public interface IMainActivity {
    void showError(String error);

    void showMusicControl(MusicListDetailBean musicListDetailBean);

}
