package com.mran.nmusic.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.NotificationTarget;
import com.google.gson.JsonObject;
import com.mran.nmusic.BaseApplication;
import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.Constant;
import com.mran.nmusic.IMusicAidlInterface;
import com.mran.nmusic.R;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.mainactivity.MainActivity;
import com.mran.nmusic.net.cloudmusic.api.CloudMusicApi;
import com.mran.nmusic.net.cloudmusic.jsonadapter.MusicUrlJson;
import com.mran.nmusic.net.cloudmusic.util.encrypt.Encrypt_Param;
import com.mran.nmusic.net.qqmusic.api.QQmusicApi;
import com.mran.nmusic.net.qqmusic.jsonadapter.QQmusicUrlPreJson;
import com.mran.nmusic.net.qqmusic.util.QQmusicParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by M on 2017/3/30.
 */

public class MusicPlayService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnInfoListener, MediaPlayer.OnErrorListener {

    private MediaPlayer mMediaPlayer = null;

    private RemoteViews remoteViews;
    private Notification notification;
    private String songUrl = "/";
    private boolean mediaPlayerIsInit = false;

    private NotificationTarget notificationCoverTarget;
    private NotificationTarget notificationPlayTarget;

    private List<MusicListDetailBean> detailBeanList = new ArrayList<>();
    private MusicListDetailBean nowMusicListDetailBean;
    private int mNowIndex = 0;//当前播放的歌曲在列表中的位置
    private int playMode = Constant.ALLREPEAT;

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showNotification();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    private void showNotification() {

        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0,
                new Intent(getApplicationContext(), MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        Intent playIntent = new Intent(Constant.MUSIC_NOTIFICATION_CONTORL_PLAY);
        Intent nextIntent = new Intent(Constant.MUSIC_NOTIFICATION_CONTORL_NEXT);

        PendingIntent playPi = PendingIntent.getBroadcast(BaseApplication.getContext(), 1, playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent nextPi = PendingIntent.getBroadcast(BaseApplication.getContext(), 2, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notification_play, playPi);
        remoteViews.setOnClickPendingIntent(R.id.notification_next, nextPi);

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(BaseApplication.getContext());
        notificationCompat.setContent(remoteViews)
                .setSmallIcon(R.drawable.ic_32_multimeda_music_note)
                .setOngoing(true)
                .setContentTitle("nmusic")
                .setContentIntent(pi);
        notification = notificationCompat.build();
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
        notificationCoverTarget = new NotificationTarget(getApplicationContext(),
                remoteViews,
                R.id.notification_cover,
                notification, 1
        );
        notificationPlayTarget = new NotificationTarget(getApplicationContext(),
                remoteViews,
                R.id.notification_play,
                notification, 1
        );
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIMusicAidlInterface;
    }

    private final IMusicAidlInterface.Stub mIMusicAidlInterface = new IMusicAidlInterface.Stub() {
        @Override
        public void play() throws RemoteException {
            if (mMediaPlayer != null) {

                mMediaPlayer.start();
            }
            notificationShowPlay(true);
            sendBroadcast(Constant.MUSIC_CONTORL_PLAY);
        }

        @Override
        public boolean isPlaying() throws RemoteException {
            if (mMediaPlayer != null) {
                return mMediaPlayer.isPlaying();
            }
            return false;

        }

        @Override
        public void stop() throws RemoteException {
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
            }
            notificationShowPlay(false);
            sendBroadcast(Constant.MUSIC_CONTORL_STOP);
        }

        @Override
        public void next(boolean isforce) throws RemoteException {
            setListStateToNOPlaying(mNowIndex);
            if (isforce || playMode != Constant.ONEREPEAT) {
                if (playMode == Constant.SHUFFLE) {
                    //随机播放就设定一个随机数
                    mNowIndex = (int) (Math.random() * detailBeanList.size());
                } else if (mNowIndex + 1 < detailBeanList.size())
                    ++mNowIndex;
                else {
                    mNowIndex = 0;
                }
                if (detailBeanList.size() != 0)
                    playByIndex(mNowIndex);
                else {
                    mNowIndex = 0;
                }
            } else playByIndex(mNowIndex);
            if (BuildConfig.DEBUG)
                Log.d("MainActivityPresenterCo", "now:" + mNowIndex);

        }

        @Override
        public void previous() throws RemoteException {
            setListStateToNOPlaying(mNowIndex);
            if (mNowIndex - 1 >= 0) {
                --mNowIndex;
            } else {
                mNowIndex = detailBeanList.size() - 1;
            }
            if (detailBeanList.size() != 0) {
                playByIndex(mNowIndex);
            } else {
                mNowIndex = 0;

            }
            if (BuildConfig.DEBUG)
                Log.d("MainActivityPresenterCo", "now:" + mNowIndex);
        }

        @Override
        public void playByIndex(int index) throws RemoteException {
            setListStateToNOPlaying(mNowIndex);
            notificationShowPlay(true);
            mNowIndex = index;
            playPrepar(index);
            sendBroadcast(Constant.MUSIC_CHANGE);
            changeNotification(nowMusicListDetailBean);
        }

        @Override
        public void setPlayMode(int mode) throws RemoteException {
            playMode = mode;
        }

        @Override
        public int getPlayMode() throws RemoteException {
            return playMode;
        }

        @Override
        public void setSleep(long time) throws RemoteException {

        }

        @Override
        public MusicListDetailBean getMusic() throws RemoteException {
            return nowMusicListDetailBean;
        }

        @Override
        public String getMusicName() throws RemoteException {
            if (nowMusicListDetailBean != null)
                return nowMusicListDetailBean.getSongName();
            else return "";
        }

        @Override
        public String getMusicImgUrl() throws RemoteException {
            if (nowMusicListDetailBean != null)
                return nowMusicListDetailBean.getImageUrl();
            else return "";
        }

        @Override
        public String getMusicSinger() throws RemoteException {
            if (nowMusicListDetailBean != null)
                return nowMusicListDetailBean.getArtits();
            else return "";
        }

        @Override
        public String getMusicId() throws RemoteException {
            if (nowMusicListDetailBean != null)
                return nowMusicListDetailBean.getId();
            else return "";
        }

        @Override
        public void addMusic(MusicListDetailBean music) throws RemoteException {
            setListStateToNOPlaying(mNowIndex);
            detailBeanList.add(mNowIndex, music);
            playByIndex(mNowIndex);

        }

        @Override
        public void addMusicList(List<MusicListDetailBean> musicList) throws RemoteException {
            detailBeanList.clear();
            detailBeanList.addAll(musicList);
            playByIndex(0);
        }

        @Override
        public List<MusicListDetailBean> getMusicList() throws RemoteException {
            return detailBeanList;
        }

        @Override
        public int getMusicListSize() throws RemoteException {
            if (detailBeanList != null)
                return detailBeanList.size();
            return 0;
        }

        @Override
        public void deleteMusic(int index) throws RemoteException {
            detailBeanList.remove(index);
        }

        @Override
        public void deleteAllMusic() throws RemoteException {
            detailBeanList.clear();
            stop();
        }
    };

    private void sendBroadcast(String message) {
        Intent intent = new Intent(message);
        sendBroadcast(intent);
    }

    private void changeNotification(MusicListDetailBean musicListDetailBean) {
        String songName = "";
        String singer = "";
        String imgUrl = "";

        if (musicListDetailBean != null) {
            songName = musicListDetailBean.getSongName();
            singer = musicListDetailBean.getArtits();
            imgUrl = musicListDetailBean.getImageUrl();
        }
        remoteViews.setTextViewText(R.id.notification_song, songName);
        remoteViews.setTextViewText(R.id.notification_singer, singer);
        Glide.with(getApplicationContext())
                .load(imgUrl)
                .asBitmap()
                .priority(Priority.LOW)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .into(notificationCoverTarget);
    }

    private void notificationShowPlay(boolean isplaying) {
        int resouceId = isplaying ? R.drawable.ic_pause : R.drawable.ic_play;
        remoteViews.setImageViewResource(R.id.notification_play, resouceId);
        NotificationManager manager = (NotificationManager)
                getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }

    public void playPrepar(int index) {

        detailBeanList.get(index).setPlaying(true);
        nowMusicListDetailBean = detailBeanList.get(index);
        int tag = nowMusicListDetailBean.getTag();
        switch (tag) {
            case Constant.NETEASETAG:
                playNetease(nowMusicListDetailBean);
                break;
            case Constant.QQTAG:
                playQQmusic(nowMusicListDetailBean);
                break;
            case Constant.XIAMITAG:
                playXiami(nowMusicListDetailBean);
                break;
            default:
                break;
        }
    }

    private void playMusic(String musicUrl) {
        if (musicUrl.contains("126.net")) {
            if (musicUrl.substring(musicUrl.lastIndexOf("/")).equals(songUrl.substring(songUrl.lastIndexOf("/"))) && mediaPlayerIsInit) {
                mMediaPlayer.start();
                return;
            }
        }
        songUrl = musicUrl;

        if (mMediaPlayer != null) {
            try {

                mMediaPlayer.stop();
                mMediaPlayer.reset();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setDataSource(musicUrl);
                mMediaPlayer.setOnPreparedListener(this);
                mMediaPlayer.setOnCompletionListener(this);
                mMediaPlayer.setOnBufferingUpdateListener(this);
                mMediaPlayer.setOnInfoListener(this);
                mMediaPlayer.setOnErrorListener(this);
                mMediaPlayer.prepareAsync(); // prepare async to not block main thread
                mediaPlayerIsInit = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    int checkIndex(int index) {
        if (index >= detailBeanList.size() || index < 0)
            return 0;
        return index;
    }

    //把正在播放列表里的歌曲状态变成未播放状态
    private void setListStateToNOPlaying(int index) {
        if (detailBeanList.size() != 0)
            detailBeanList.get(index).setPlaying(false);
    }

    //播放网易云音乐
    private void playNetease(final MusicListDetailBean musicListDetailBean) {

//        iMainActivity.showMusicControl(musicListDetailBean);
        if (musicListDetailBean.getId() != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("ids", musicListDetailBean.getId());
            jsonObject.addProperty("br", 320000);
            jsonObject.addProperty("csrf_token", "");

            CloudMusicApi.getCloudMusicMusicUrl()
                    .getMusicUrl(Encrypt_Param.encrypt(jsonObject))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<MusicUrlJson>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable throwable) {
//                            iMainActivity.showError(throwable.getMessage());
                        }

                        @Override
                        public void onNext(MusicUrlJson musicUrlJson) {
                            if (musicUrlJson.getData().get(0).getCode() == 404) {
//                                iMainActivity.showError("版权原因不能播放,请尝试其他平台");
//                                "版权原因不能播放,请尝试其他平台".isEmpty();
                            } else {
                                playMusic(musicUrlJson.getData().get(0).getUrl());
                            }
                        }
                    });
        } else {

            playMusic(musicListDetailBean.getMusicUrl());

        }
    }

    //播放QQ音乐
    private void playQQmusic(final MusicListDetailBean musicListDetailBean) {
        String imageid = musicListDetailBean.getImageUrl();
        if (!musicListDetailBean.getImageUrl().contains("http:")) {
            String imageUrl = String.format("http://imgcache.qq.com/music/photo/mid_album_300/%c/%c/%s.jpg", imageid.charAt(imageid.length() - 2), imageid.charAt(imageid.length() - 1), imageid);
            if (BuildConfig.DEBUG)
                Log.d("MainActivityPresenterCo", imageUrl);
            musicListDetailBean.setImageUrl(imageUrl);
        }
//        iMainActivity.showMusicControl(musicListDetailBean);
        QQmusicApi.getqQmusicUrl().getMusicUrl().flatMap(new Func1<String, Observable<QQmusicUrlPreJson>>() {
            @Override
            public Observable<QQmusicUrlPreJson> call(String s) {
                return Observable.just(QQmusicParse.getjson(s, QQmusicUrlPreJson.class));

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QQmusicUrlPreJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        iMainActivity.showError(e.getMessage());

                    }

                    @Override
                    public void onNext(QQmusicUrlPreJson qQmusicUrlPreJson) {
                        String musicUrl = String.format("%sC200%s.m4a?vkey=%s&fromtag=0&guid=780782017", qQmusicUrlPreJson.getSip().get(0), musicListDetailBean.getId(), qQmusicUrlPreJson.getKey());
                        playMusic(musicUrl);

                    }
                });
    }

    //播放虾米音乐
    private void playXiami(MusicListDetailBean musicListDetailBean) {
        playMusic(musicListDetailBean.getMusicUrl());
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

        MusicPlayer.next(false);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mMediaPlayer.start();
        if (BuildConfig.DEBUG)
            Log.d("MusicControlBinder", "start");
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
        if (BuildConfig.DEBUG)
            Log.d("MusicControlBinder", "percent:" + percent);
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        if (BuildConfig.DEBUG) {
            Log.d("MusicControlBinder", "onInfo " + what + ":" + extra);
        }
        return false;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mMediaPlayer.stop();
        if (BuildConfig.DEBUG) {
            Log.d("MusicControlBinder", "onErrorextra:" + extra);

            Log.d("MusicControlBinder", "onErrorwhat:" + what);
        }
        Intent intent = new Intent(Constant.MUSIC_CONTORL_STOP);

        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
        return false;
    }
}
