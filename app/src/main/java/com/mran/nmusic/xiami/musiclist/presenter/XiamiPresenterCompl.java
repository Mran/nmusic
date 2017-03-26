package com.mran.nmusic.xiami.musiclist.presenter;

import android.content.Context;

import com.mran.nmusic.net.cloudmusic.bean.MusicListBean;
import com.mran.nmusic.net.xiamimusic.api.XiamiMusicApi;
import com.mran.nmusic.net.xiamimusic.jsonadapter.XiamiMusicListBean;
import com.mran.nmusic.net.xiamimusic.util.XiamiparseHtml;
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

public class XiamiPresenterCompl implements IXiamiPresenter {
    private Context context;
    private IMusiclistFragment iMusiclistFragment;
    private int pageCount = 1;

    public XiamiPresenterCompl(Context context, IMusiclistFragment iMusiclistFragment) {
        this.context = context;
        this.iMusiclistFragment = iMusiclistFragment;
    }

    @Override
    public void loadMusiclist(final boolean isloadmore) {
        if (isloadmore)
            pageCount++;
        else
            pageCount = 1;
        XiamiMusicApi.getXiamiRecommendList().getRecommendList(pageCount).flatMap(new Func1<String, Observable<List<MusicListBean>>>() {

            @Override
            public Observable<List<MusicListBean>> call(String s) {
                return Observable.just(XiamiparseHtml.getXiamiMusicList(s));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MusicListBean>>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable throwable) {
                iMusiclistFragment.showError(throwable.getMessage());
            }

            @Override
            public void onNext(List<MusicListBean> o) {
               iMusiclistFragment.showResult(isloadmore,o);
            }
        });
    }
}
