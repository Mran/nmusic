package com.mran.nmusic.xiami.musiclistdetail.presenter;

import android.content.Context;

import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;
import com.mran.nmusic.net.xiamimusic.api.XiamiMusicApi;
import com.mran.nmusic.net.xiamimusic.jsonadapter.XiamiMusiclistDetailJson;
import com.mran.nmusic.net.xiamimusic.util.XiamiparseHtml;
import com.mran.nmusic.xiami.musiclistdetail.view.IXiamiListDetailFragment;

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

public class XiamilistDetailPresenterCompl implements com.mran.nmusic.netease.musiclistdetail.presenter.INeteaseMusiclistDetailPresenter {
    private Context context;
    private IXiamiListDetailFragment iXiamiListDetailFragment;
    private List<MusicListDetailBean> mlistDetailBeen = new ArrayList<>();
    private Subscriber subscriber;

    public XiamilistDetailPresenterCompl(Context context, IXiamiListDetailFragment iXiamiListDetailFragment) {
        this.context = context;
        this.iXiamiListDetailFragment = iXiamiListDetailFragment;
    }

    @Override
    public void getMusicListDetail(String listid) {
        subscriber=new Subscriber<List<MusicListDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                iXiamiListDetailFragment.showError(throwable.getMessage());
            }

            @Override
            public void onNext(List<MusicListDetailBean> musicListDetailBeanList) {
                iXiamiListDetailFragment.showResult(musicListDetailBeanList);
                mlistDetailBeen.addAll(musicListDetailBeanList);
            }
        };
        XiamiMusicApi.getXiamiMusiclistDeatail().getListDeatail(listid).flatMap(new Func1<String, Observable<XiamiMusiclistDetailJson>>() {
            @Override
            public Observable<XiamiMusiclistDetailJson> call(String s) {

                return Observable.just(XiamiparseHtml.getjson(s, XiamiMusiclistDetailJson.class));
            }
        }).flatMap(new Func1<XiamiMusiclistDetailJson, Observable<List<MusicListDetailBean>>>() {
            @Override
            public Observable<List<MusicListDetailBean>> call(XiamiMusiclistDetailJson xiamiMusiclistDeatailJson) {
                return Observable.just(XiamiparseHtml.getMusiclistDetailbean(xiamiMusiclistDeatailJson));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void cancle() {

    }

    public List<MusicListDetailBean> getMlistDetailBeen() {
        return mlistDetailBeen;
    }


}

