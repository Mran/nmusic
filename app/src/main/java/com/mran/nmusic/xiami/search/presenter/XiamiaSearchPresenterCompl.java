package com.mran.nmusic.xiami.search.presenter;

import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.net.xiamimusic.api.XiamiMusicApi;
import com.mran.nmusic.net.xiamimusic.jsonadapter.XiamiSeachJson;
import com.mran.nmusic.net.xiamimusic.util.XiamiparseHtml;
import com.mran.nmusic.xiami.search.view.IXiamiaSearchFragment;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 张孟尧 on 2016/10/13.
 */

public class XiamiaSearchPresenterCompl implements IXiamiaSearchPresenter {

    private IXiamiaSearchFragment iXiamiaSearchFragment;
    private Subscriber subscriber;
    private int now = 0;

    public XiamiaSearchPresenterCompl(IXiamiaSearchFragment iXiamiaSearchFragment) {
        this.iXiamiaSearchFragment = iXiamiaSearchFragment;
    }

    @Override
    public void getSearchResult(final boolean isLoadMore, String query) {
        subscriber = new Subscriber<List<MusicListDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                iXiamiaSearchFragment.showError(e.getMessage());
            }

            @Override
            public void onNext(List<MusicListDetailBean> musicListDetailBeen) {
                iXiamiaSearchFragment.showResult(isLoadMore, musicListDetailBeen);
            }
        };
        if (isLoadMore) {
            now++;
        } else {now = 0;}
        XiamiMusicApi.getSearchMusic().getMusicSearch(query, now, 35).map(new Func1<String, XiamiSeachJson>() {
            @Override
            public XiamiSeachJson call(String s) {
                return  XiamiparseHtml.getjson(s, XiamiSeachJson.class);
            }
        }).flatMap(new Func1<XiamiSeachJson, Observable<List<MusicListDetailBean>>>() {
            @Override
            public Observable<List<MusicListDetailBean>> call(XiamiSeachJson xiamiSeachJson) {
                return Observable.just(XiamiparseHtml.getMusicSearchList(xiamiSeachJson));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

    @Override
    public void cancle() {
        if (subscriber != null && subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }
}
