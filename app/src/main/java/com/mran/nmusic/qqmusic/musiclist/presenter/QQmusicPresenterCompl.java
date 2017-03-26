package com.mran.nmusic.qqmusic.musiclist.presenter;

import android.content.Context;
import android.util.Log;

import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.Constant;
import com.mran.nmusic.net.cloudmusic.bean.MusicListBean;
import com.mran.nmusic.net.qqmusic.api.QQmusicApi;
import com.mran.nmusic.net.qqmusic.jsonadapter.QQmusicRecommendMusicListJson;
import com.mran.nmusic.net.qqmusic.util.QQmusicParse;
import com.mran.nmusic.netease.musiclist.view.IMusiclistFragment;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 张孟尧 on 2016/10/6.
 */

public class QQmusicPresenterCompl implements IQQmusicPresenter {
    private Context context;
    private IMusiclistFragment iMusiclistFragment;
    private int pageCount = 0;

    public QQmusicPresenterCompl(Context context, IMusiclistFragment iMusiclistFragment) {
        this.context = context;
        this.iMusiclistFragment = iMusiclistFragment;
    }

    @Override
    public void loadMusiclist(final boolean isloadmore) {
        if (isloadmore)
            pageCount++;
        else
            pageCount = 0;
        QQmusicApi.getqQmusicRecommendMusicList().getRecommendMusicList(5,pageCount*Constant.QQ_MUSICLIST_DEFUALTSIZE,(pageCount+1)*Constant.QQ_MUSICLIST_DEFUALTSIZE-1).flatMap(new Func1<String, Observable<QQmusicRecommendMusicListJson>>() {
            @Override
            public Observable<QQmusicRecommendMusicListJson> call(String s) {
                return Observable.just(QQmusicParse.getjson(s, QQmusicRecommendMusicListJson.class));
            }
        }).flatMap(new Func1<QQmusicRecommendMusicListJson, Observable<List<MusicListBean>>>() {
            @Override
            public Observable<List<MusicListBean>> call(QQmusicRecommendMusicListJson qQmusicRecommendMusicListJson) {
                return Observable.just(QQmusicParse.getMusiclistbean(qQmusicRecommendMusicListJson));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MusicListBean>>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable throwable) {
                iMusiclistFragment.showError(throwable.getMessage());
                if (BuildConfig.DEBUG)
                    Log.d("QQMusiclistPresen", throwable.getMessage());
            }

            @Override
            public void onNext(List<MusicListBean> o) {
                iMusiclistFragment.showResult(isloadmore, o);
            }
        });
    }
}
