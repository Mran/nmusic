package com.mran.nmusic.qqmusic.search.view;

import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/13.
 */

public interface IQQmusicSearchFragment {
    void showResult(boolean isLoadMore, List<MusicListDetailBean> musicListDetailBeen);

    void showError(String error);
}
