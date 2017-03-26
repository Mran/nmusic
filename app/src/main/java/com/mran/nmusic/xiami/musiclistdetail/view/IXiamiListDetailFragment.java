package com.mran.nmusic.xiami.musiclistdetail.view;

import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/8.
 */

public interface IXiamiListDetailFragment {
    void showResult(List<MusicListDetailBean> listDetailBeanList);
    void showError(String error);
}
