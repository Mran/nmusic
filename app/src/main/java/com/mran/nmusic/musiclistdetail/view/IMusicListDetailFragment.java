package com.mran.nmusic.musiclistdetail.view;

import com.mran.nmusic.bean.MusicListDetailBean;

import java.util.List;

/**
 * Created by M on 2017/4/10.
 */

public interface IMusicListDetailFragment {
    void showResult(List<MusicListDetailBean> listDetailBeanList);

    void showError(String error);
}
