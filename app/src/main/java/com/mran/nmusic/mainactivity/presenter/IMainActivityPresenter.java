package com.mran.nmusic.mainactivity.presenter;

import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/11.
 */

public interface IMainActivityPresenter {
    void play(int now);

    void pre(boolean isPositive);

    void next(boolean isPositive);

    void add(MusicListDetailBean musicListDetailBean);

    List<MusicListDetailBean> getPlayingLists();

    void setPlayMode(int mode);

    void playAll(List<MusicListDetailBean> listDetailBeen);
}
