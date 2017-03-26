package com.mran.nmusic.qqmusic.search.presenter;

import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;
import com.mran.nmusic.net.qqmusic.api.QQmusicApi;
import com.mran.nmusic.net.qqmusic.jsonadapter.QQmusicSearchJson;
import com.mran.nmusic.net.qqmusic.util.QQmusicParse;
import com.mran.nmusic.qqmusic.search.view.IQQmusicSearchFragment;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 张孟尧 on 2016/10/13.
 */

public class QQmusicSearchPresenterCompl implements IQQmusicSearchPresenter {

    private IQQmusicSearchFragment iqQmusicSearchFragment;
    private Subscriber subscriber;
    private int now = 0;

    public QQmusicSearchPresenterCompl(IQQmusicSearchFragment iqQmusicSearchFragment) {
        this.iqQmusicSearchFragment = iqQmusicSearchFragment;
    }

    @Override
    public void getSearchResult(final boolean isLoadMore, String query) {
        subscriber = new Subscriber<List<MusicListDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                iqQmusicSearchFragment.showError(e.getMessage());
            }

            @Override
            public void onNext(List<MusicListDetailBean> musicListDetailBeen) {
                iqQmusicSearchFragment.showResult(isLoadMore, musicListDetailBeen);
            }
        };
        if (isLoadMore) {
            now++;
        } else {now = 0;}
        QQmusicApi.getqQmusicSearch().getSearch(query, now, System.currentTimeMillis())
                .flatMap(new Func1<String, Observable<QQmusicSearchJson>>() {
                    @Override
                    public Observable<QQmusicSearchJson> call(String s) {
                        return Observable.just(QQmusicParse.getjson(s, QQmusicSearchJson.class));
                    }
                }).flatMap(new Func1<QQmusicSearchJson, Observable<List<MusicListDetailBean>>>() {
            @Override
            public Observable<List<MusicListDetailBean>> call(QQmusicSearchJson qQmusicSearchJson) {
                return Observable.just(QQmusicParse.getMusicSearchList(qQmusicSearchJson));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void cancle() {
        if (subscriber != null && subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }
}
