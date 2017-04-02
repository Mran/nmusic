package com.mran.nmusic.mainactivity.presenter;

import android.content.Context;

import com.mran.nmusic.Constant;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.mainactivity.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/11.
 */

public class MainActivityPresenterCompl implements IMainActivityPresenter {
    List<MusicListDetailBean> detailBeanList;
    MusicListDetailBean nowMusicListDetailBean;
    int now = 0;//当前播放的歌曲在列表中的位置
    Context context;

    IMainActivity iMainActivity;
    private int mode=Constant.ALLREPEAT;

    public MainActivityPresenterCompl(Context context, IMainActivity iMainActivity) {
        this.context = context;
        this.iMainActivity = iMainActivity;
        detailBeanList = new ArrayList<>();
    }

//
//            default:
//                break;
//        }
//    }
//
//    //播放上一首音乐
//    @Override
//    public void pre(boolean isPositive) {
//        if (now - 1 >= 0) {--now;} else {
//            now = detailBeanList.size() - 1;
//        }
//        if (detailBeanList.size() != 0) {
//            play(now);
//        } else {
//            now = 0;
//            iMainActivity.showError("播放列表没有歌曲");
//        }
//        if (BuildConfig.DEBUG)
//            Log.d("MainActivityPresenterCo", "now:" + now);
//    }
//
//    //播放下一首音乐
//    @Override
//    public void next(boolean isPositive) {
//        if (mode == Constant.SHUFFLE) {
//            //随机播放就设定一个随机数
//            now = (int) (Math.random() * detailBeanList.size());
//        } else if (mode == Constant.ONEREPEAT && !isPositive) {
//            //循环播放就不改变
//        } else if (now + 1 < detailBeanList.size())
//            ++now;
//        else {
//            now = 0;
//        }
//        if (detailBeanList.size() != 0)
//            play(now);
//        else {
//            now = 0;
//            iMainActivity.showError("播放列表没有歌曲");
//        }
//
//        if (BuildConfig.DEBUG)
//            Log.d("MainActivityPresenterCo", "now:" + now);
//    }
//
//    //添加一首音乐到当前播放列表
//    @Override
//    public void add(MusicListDetailBean musicListDetailBean) {
//        if (detailBeanList.size() == 0)
//            detailBeanList.add(musicListDetailBean);
//        else
//            detailBeanList.add(++now, musicListDetailBean);
//        play(now);
//    }
//
//    //返回当前的播放列表
//    @Override
//    public List<MusicListDetailBean> getPlayingLists() {
//        return detailBeanList;
//    }
//
//    @Override
//    public void setPlayMode(int mode) {
//        this.mode = mode;
//    }
//
//    //播放传进来的播放列表
//    @Override
//    public void playAll(List<MusicListDetailBean> listDetailBeen) {
//        detailBeanList.clear();
//        detailBeanList.addAll(listDetailBeen);
//        now = 0;
//        play(now);
//    }
//
//    //获得当前音乐的实例
//    public MusicListDetailBean getCurrentMusicDetailBean() {
//        if (detailBeanList.size() != 0)
//            return detailBeanList.get(now);
//        else
//            return null;
//    }
//
//    //播放网易云音乐
//    private void playNetease(final MusicListDetailBean musicListDetailBean) {
//
//        iMainActivity.showMusicControl(musicListDetailBean);
//        if (musicListDetailBean.getId() != null) {
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("ids", musicListDetailBean.getId());
//            jsonObject.addProperty("br", 320000);
//            jsonObject.addProperty("csrf_token", "");
//            //        System.out.println(jsonObject.toString());
//            CloudMusicApi.getCloudMusicMusicUrl()
//                    .getMusicUrl(Encrypt_Param.encrypt(jsonObject))
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<MusicUrlJson>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable throwable) {
//                            iMainActivity.showError(throwable.getMessage());
//                        }
//
//                        @Override
//                        public void onNext(MusicUrlJson musicUrlJson) {
//                            if (musicUrlJson.getData().get(0).getCode() == 404) {
//                                iMainActivity.showError("版权原因不能播放,请尝试其他平台");
//                            } else {iMainActivity.play(musicUrlJson.getData().get(0).getUrl());}
//                        }
//                    });
//        } else {
//
//            iMainActivity.play(musicListDetailBean.getMusicUrl());
//
//        }
//    }
//
//    //播放QQ音乐
//    private void playQQmusic(final MusicListDetailBean musicListDetailBean) {
//        String imageid = musicListDetailBean.getImageUrl();
//        if (!musicListDetailBean.getImageUrl().contains("http:")) {
//            String imageUrl = String.format("http://imgcache.qq.com/music/photo/mid_album_300/%c/%c/%s.jpg", imageid.charAt(imageid.length() - 2), imageid.charAt(imageid.length() - 1), imageid);
//            if (BuildConfig.DEBUG)
//                Log.d("MainActivityPresenterCo", imageUrl);
//
//            musicListDetailBean.setImageUrl(imageUrl);
//        }
//        iMainActivity.showMusicControl(musicListDetailBean);
//        QQmusicApi.getqQmusicUrl().getMusicUrl().flatMap(new Func1<String, Observable<QQmusicUrlPreJson>>() {
//            @Override
//            public Observable<QQmusicUrlPreJson> call(String s) {
//                return Observable.just(QQmusicParse.getjson(s, QQmusicUrlPreJson.class));
//
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<QQmusicUrlPreJson>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        iMainActivity.showError(e.getMessage());
//
//                    }
//
//                    @Override
//                    public void onNext(QQmusicUrlPreJson qQmusicUrlPreJson) {
//                        String musicUrl = String.format("%sC200%s.m4a?vkey=%s&fromtag=0&guid=780782017", qQmusicUrlPreJson.getSip().get(0), musicListDetailBean.getId(), qQmusicUrlPreJson.getKey());
//                        iMainActivity.play(musicUrl);
//                    }
//                });
//    }
//
//    //播放虾米音乐
//    private void playXiami(MusicListDetailBean musicListDetailBean) {
//        iMainActivity.showMusicControl(musicListDetailBean);
//        iMainActivity.play(musicListDetailBean.getMusicUrl());
//    }
}
