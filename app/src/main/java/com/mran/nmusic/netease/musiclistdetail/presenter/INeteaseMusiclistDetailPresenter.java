package com.mran.nmusic.netease.musiclistdetail.presenter;

import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;

/**
 * Created by 张孟尧 on 2016/10/8.
 */

public interface INeteaseMusiclistDetailPresenter {
    void getMusicListDetail(String listid);
    void cancle();

}
