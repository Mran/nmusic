package com.mran.nmusic.qqmusic.musiclistdetail.presenter;

import android.content.Context;
import android.util.Log;

import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.net.qqmusic.api.QQmusicApi;
import com.mran.nmusic.net.qqmusic.jsonadapter.QQmusicMusiclistDetailjson;
import com.mran.nmusic.net.qqmusic.util.QQmusicParse;
import com.mran.nmusic.qqmusic.musiclistdetail.view.IQQMusicListDetailFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 张孟尧 on 2016/10/8.
 */

public class QQMusiclistDetailPresenterCompl implements com.mran.nmusic.netease.musiclistdetail.presenter.INeteaseMusiclistDetailPresenter {
    private Context context;
    private IQQMusicListDetailFragment iqqMusicListDetailFragment;
    private List<MusicListDetailBean> mlistDetailBeen = new ArrayList<>();

    public QQMusiclistDetailPresenterCompl(Context context, IQQMusicListDetailFragment iqqMusicListDetailFragment) {
        this.context = context;
        this.iqqMusicListDetailFragment = iqqMusicListDetailFragment;
    }

    @Override
    public void getMusicListDetail(String listid) {
        QQmusicApi.getqQmusicMusiclistDetail().getMusiclistDetail(listid)

                .flatMap(new Func1<String, Observable<QQmusicMusiclistDetailjson>>() {
            @Override
            public Observable<QQmusicMusiclistDetailjson> call(String s) {

                return Observable.just(QQmusicParse.getjson(s, QQmusicMusiclistDetailjson.class));
            }
        }).flatMap(new Func1<QQmusicMusiclistDetailjson, Observable<List<MusicListDetailBean>>>() {
            @Override
            public Observable<List<MusicListDetailBean>> call(QQmusicMusiclistDetailjson qQmusicMusiclistDetailjson) {
                return Observable.just(QQmusicParse.getMusiclistDetailbean(qQmusicMusiclistDetailjson));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MusicListDetailBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        iqqMusicListDetailFragment.showError(throwable.getMessage());
                        if (BuildConfig.DEBUG)
                            Log.d("QQMusiclistDetailPresen", throwable.getMessage());
                    }

                    @Override
                    public void onNext(List<MusicListDetailBean> musicListDetailBeanList) {
                        iqqMusicListDetailFragment.showResult(musicListDetailBeanList);
                        mlistDetailBeen.addAll(musicListDetailBeanList);
                    }
                });
    }

    @Override
    public void cancle() {

    }

    public List<MusicListDetailBean> getMlistDetailBeen() {
        return mlistDetailBeen;
    }


}

