package com.mran.nmusic.qqmusic.search.presenter;

/**
 * Created by 张孟尧 on 2016/10/13.
 */

public interface IQQmusicSearchPresenter {
    void getSearchResult(boolean isLoadMore, String query);
    void cancle();
}
