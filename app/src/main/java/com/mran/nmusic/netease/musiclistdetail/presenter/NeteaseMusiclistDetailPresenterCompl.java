package com.mran.nmusic.netease.musiclistdetail.presenter;

import android.content.Context;

import com.mran.nmusic.net.cloudmusic.api.CloudMusicApi;
import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;
import com.mran.nmusic.net.cloudmusic.util.CloudMusicParse;
import com.mran.nmusic.netease.musiclistdetail.view.INeteaseMusicListDetailFragment;

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

public class NeteaseMusiclistDetailPresenterCompl implements INeteaseMusiclistDetailPresenter {
    List<MusicListDetailBean> mlistDetailBeen = new ArrayList<>();
    private Context context;
    private INeteaseMusicListDetailFragment iNeteaseMusicListDetailFragment;

    public NeteaseMusiclistDetailPresenterCompl(Context context, INeteaseMusicListDetailFragment iNeteaseMusicListDetailFragment) {
        this.context = context;
        this.iNeteaseMusicListDetailFragment = iNeteaseMusicListDetailFragment;
    }

    @Override
    public  void getMusicListDetail(String listid) {
        CloudMusicApi.getMusiclistDetail().getMusicListDetail(listid)
                .flatMap(new Func1<String, Observable<List<MusicListDetailBean>>>() {
                    @Override
                    public Observable<List<MusicListDetailBean>> call(String s) {

                        return Observable.just(CloudMusicParse.parseRecommendMusicListDetail(s));
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
                        iNeteaseMusicListDetailFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<MusicListDetailBean> listDetailBeen) {
                        iNeteaseMusicListDetailFragment.showResult(listDetailBeen);
                        mlistDetailBeen.addAll(listDetailBeen);
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
