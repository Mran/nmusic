package com.mran.nmusic.netease.musiclistdetail.view;

import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/8.
 */

public interface INeteaseMusicListDetailFragment {
    void showResult(List<MusicListDetailBean> listDetailBeanList);

    void showError(String error);
}
