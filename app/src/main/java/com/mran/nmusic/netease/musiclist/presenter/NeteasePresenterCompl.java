package com.mran.nmusic.netease.musiclist.presenter;

import android.content.Context;

import com.mran.nmusic.Constant;
import com.mran.nmusic.net.cloudmusic.api.CloudMusicApi;
import com.mran.nmusic.bean.MusicListBean;
import com.mran.nmusic.net.cloudmusic.util.CloudMusicParse;
import com.mran.nmusic.netease.musiclist.view.IMusiclistFragment;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 张孟尧 on 2016/10/6.
 */

public class NeteasePresenterCompl implements INeteasePresenter {
    private Context context;
    private IMusiclistFragment iMusiclistFragment;
    private int pageCount = 0;

    public NeteasePresenterCompl(Context context, IMusiclistFragment iMusiclistFragment) {
        this.context = context;
        this.iMusiclistFragment = iMusiclistFragment;
    }

    @Override
    public void loadMusiclist(final boolean isloadmore) {
        if (isloadmore)
            pageCount++;
        else
            pageCount = 0;
        CloudMusicApi.getCloudMusicRecommendMusciList().getRecommendMusicList("hot", Constant.NETEASE_MUSICLIST_DEFUALTSIZE, pageCount * Constant.NETEASE_MUSICLIST_DEFUALTSIZE)
                .map(new Func1<String, List<MusicListBean>>() {
                    @Override
                    public List<MusicListBean> call(String s) {
                        return CloudMusicParse.parseRecommendMusicList(s);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MusicListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
              iMusiclistFragment.showError(e.getMessage());
            }

            @Override
            public void onNext(List<MusicListBean> musicListBeen) {
                iMusiclistFragment.showResult(isloadmore,musicListBeen);
            }
        });
    }
}
