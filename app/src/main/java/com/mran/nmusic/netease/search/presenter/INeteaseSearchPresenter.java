package com.mran.nmusic.netease.search.presenter;

/**
 * Created by 张孟尧 on 2016/10/13.
 */

public interface INeteaseSearchPresenter {
    void getSearchResult(boolean isLoadMore,String query);
    void cancle();
}
