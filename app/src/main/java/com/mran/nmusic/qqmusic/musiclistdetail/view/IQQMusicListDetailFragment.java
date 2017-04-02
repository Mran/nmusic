package com.mran.nmusic.qqmusic.musiclistdetail.view;

import com.mran.nmusic.bean.MusicListDetailBean;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/8.
 */

public interface IQQMusicListDetailFragment {
    void showResult(List<MusicListDetailBean> listDetailBeanList);
    void showError(String error);
}
