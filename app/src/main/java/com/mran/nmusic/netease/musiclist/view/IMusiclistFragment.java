package com.mran.nmusic.netease.musiclist.view;

import com.mran.nmusic.net.cloudmusic.bean.MusicListBean;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/6.
 */

public interface IMusiclistFragment {
    void showError(String error);

    void showResult(boolean isloadmore,List<MusicListBean> musicListBeen);
}
