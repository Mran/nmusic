package com.mran.nmusic.xiami.search.presenter;

/**
 * Created by 张孟尧 on 2016/10/13.
 */

public interface IXiamiaSearchPresenter {
    void getSearchResult(boolean isLoadMore, String query);
    void cancle();
}
