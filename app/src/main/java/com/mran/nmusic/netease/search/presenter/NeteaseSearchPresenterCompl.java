package com.mran.nmusic.netease.search.presenter;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.net.cloudmusic.api.CloudMusicApi;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.net.cloudmusic.jsonadapter.CloudMusicSearchJson;
import com.mran.nmusic.net.cloudmusic.util.CloudMusicParse;
import com.mran.nmusic.netease.search.view.INeteaseSearchFragment;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 张孟尧 on 2016/10/13.
 */

public class NeteaseSearchPresenterCompl implements INeteaseSearchPresenter {

    private INeteaseSearchFragment iNeteaseSearchFragment;
    private Subscriber subscriber;
    private int now = 0;

    public NeteaseSearchPresenterCompl(INeteaseSearchFragment iMusicSearchFragment) {
        this.iNeteaseSearchFragment = iMusicSearchFragment;
    }

    @Override
    public void getSearchResult(final boolean isLoadMore, String query) {
        subscriber = new Subscriber<List<MusicListDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                iNeteaseSearchFragment.showError(e.getMessage());
                if (BuildConfig.DEBUG)
                    Log.d("NeteaseSearchPresenterC", e.getMessage());
            }

            @Override
            public void onNext(List<MusicListDetailBean> beanList) {
iNeteaseSearchFragment.showResult(isLoadMore,beanList);
            }
        };
        if (isLoadMore) {
            now++;
        } else {now = 0;}
        Map<String, String> data = new ArrayMap<>();
        data.put("limit", "20");
        data.put("offset", String.valueOf(now * 20));
        data.put("s", query);
        data.put("type", "1");
        CloudMusicApi.getSearchMusic()
                .getResult(data)
                .flatMap(new Func1<CloudMusicSearchJson, Observable<List<MusicListDetailBean>>>() {
                    @Override
                    public Observable<List<MusicListDetailBean>> call(CloudMusicSearchJson cloudMusicSearchJson) {
                        return Observable.just(CloudMusicParse.parseMusicSearchList(cloudMusicSearchJson));
                    }
                })
                .subscribeOn(Schedulers.io())
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
