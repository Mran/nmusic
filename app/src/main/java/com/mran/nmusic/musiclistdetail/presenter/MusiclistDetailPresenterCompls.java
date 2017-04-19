package com.mran.nmusic.musiclistdetail.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.Constant;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.musiclistdetail.view.IMusicListDetailFragment;
import com.mran.nmusic.net.cloudmusic.api.CloudMusicApi;
import com.mran.nmusic.net.cloudmusic.jsonadapter.CloudMusicListDetailsJson;
import com.mran.nmusic.net.cloudmusic.util.encrypt.Encrypt_Param;
import com.mran.nmusic.net.qqmusic.api.QQmusicApi;
import com.mran.nmusic.net.qqmusic.jsonadapter.QQmusicMusiclistDetailjson;
import com.mran.nmusic.net.qqmusic.util.QQmusicParse;
import com.mran.nmusic.net.xiamimusic.api.XiamiMusicApi;
import com.mran.nmusic.net.xiamimusic.jsonadapter.XiamiMusiclistDetailJson;
import com.mran.nmusic.net.xiamimusic.util.XiamiparseHtml;

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

public class MusiclistDetailPresenterCompls {
   private List<MusicListDetailBean> mlistDetailBeen = new ArrayList<>();
    private IMusicListDetailFragment iMusicListDetailFragment;
private Subscriber subscriber;
    public MusiclistDetailPresenterCompls(Context context, IMusicListDetailFragment iMusicListDetailFragment) {
        this.iMusicListDetailFragment = iMusicListDetailFragment;
    }

    public void getXiaMiMusicListDetail(String listid) {
        subscriber=new Subscriber<List<MusicListDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                iMusicListDetailFragment.showError(throwable.getMessage());
            }

            @Override
            public void onNext(List<MusicListDetailBean> musicListDetailBeanList) {
                iMusicListDetailFragment.showResult(musicListDetailBeanList);
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
    public  void getCloudMusicListDetail(String listid) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", listid);
        jsonObject.addProperty("offset", 0);
        jsonObject.addProperty("csrf_token", "");
        jsonObject.addProperty("total", "True");
        jsonObject.addProperty("limit", 1000);
        jsonObject.addProperty("n", 1000);
        CloudMusicApi.getMusiclistDetail().getMusicListDetail(Encrypt_Param.encrypt(jsonObject))
                .flatMap(new Func1<CloudMusicListDetailsJson, Observable<List<MusicListDetailBean>>>() {
                    @Override
                    public Observable<List<MusicListDetailBean>> call(CloudMusicListDetailsJson cloudMusicListDetailsJson) {
                        List<MusicListDetailBean> musicListDetailBeanList = new ArrayList<>();
                        for (CloudMusicListDetailsJson.PlaylistBean.TracksBean bean : cloudMusicListDetailsJson.getPlaylist().getTracks()) {
                            MusicListDetailBean musicListDetailBean = new MusicListDetailBean();
                            musicListDetailBean.setSongName(bean.getName());
                            musicListDetailBean.setTag(Constant.NETEASETAG);
                            musicListDetailBean.setAlbum(bean.getAl().getName());
                            musicListDetailBean.setArtits(bean.getAr().get(0).getName());
                            musicListDetailBean.setImageUrl(bean.getAl().getPicUrl());
                            musicListDetailBean.setId(String.valueOf(bean.getId()));
                            if (bean.getSt() == -1)
                                musicListDetailBean.setPlayable(false);
                            else
                                musicListDetailBean.setPlayable(true);
                            musicListDetailBeanList.add(musicListDetailBean);
                        }
                        return Observable.just(musicListDetailBeanList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MusicListDetailBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iMusicListDetailFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<MusicListDetailBean> listDetailBeen) {
                        iMusicListDetailFragment.showResult(listDetailBeen);
                        mlistDetailBeen.addAll(listDetailBeen);
                    }
                });
    }

    public void getQQMusicListDetail(String listid) {
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
                        iMusicListDetailFragment.showError(throwable.getMessage());
                        if (BuildConfig.DEBUG)
                            Log.d("QQMusiclistDetailPresen", throwable.getMessage());
                    }

                    @Override
                    public void onNext(List<MusicListDetailBean> musicListDetailBeanList) {
                        iMusicListDetailFragment.showResult(musicListDetailBeanList);
                        mlistDetailBeen.addAll(musicListDetailBeanList);
                    }
                });
    }
    public void cancle() {

    }

    public List<MusicListDetailBean> getMlistDetailBeen() {
        return mlistDetailBeen;
    }

}
