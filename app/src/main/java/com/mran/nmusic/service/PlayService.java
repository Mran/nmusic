package com.mran.nmusic.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.NotificationTarget;
import com.mran.nmusic.BaseApplication;
import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.Constant;
import com.mran.nmusic.R;
import com.mran.nmusic.mainactivity.MainActivity;

import java.io.IOException;

/**
 * Created by 张孟尧 on 2016/10/10.
 */

public class PlayService extends Service {
    MediaPlayer mMediaPlayer = null;
    boolean isErrorStop = false;
    RemoteViews remoteViews;
    Notification notification;
    private MusicControlBinder musicControlBinder = new MusicControlBinder();
    private String songUrl = "/";
    private boolean mediaPlayerIsInit = false;
    private int statusCode = 0;
    private NotificationTarget notificationCoverTarget;
    private NotificationTarget notificationPlayTarget;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicControlBinder;
    }

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
        Intent playIntent = new Intent(Constant.MUSIC_CONTORL_PLAY);
        playIntent.setAction(Constant.MUSIC_CONTORL_PLAY);
        Intent nextIntent = new Intent(Constant.MUSIC_CONTORL_NEXT);

        nextIntent.setAction(Constant.MUSIC_CONTORL_NEXT);
        PendingIntent playPi = PendingIntent.getBroadcast(BaseApplication.getContext(), 1, playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent nextPi = PendingIntent.getBroadcast(BaseApplication.getContext(), 2, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notification__play, playPi);
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
                R.id.notification__play,
                notification, 1
        );
    }

    public class MusicControlBinder extends Binder implements MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener {

        public void newSongInfo(String imageUrl, String songName, String singer) {
            remoteViews.setTextViewText(R.id.notification_song, songName);
            remoteViews.setTextViewText(R.id.notification_singer, singer);
            Glide.with(getApplicationContext())
                    .load(imageUrl)

                    .asBitmap()
                    .priority(Priority.LOW)

                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .fitCenter()
                    .into(notificationCoverTarget);
        }

        public void showPlayOrPause(boolean isplaying) {
            if (isplaying) {

                remoteViews.setImageViewResource(R.id.notification__play, R.drawable.ic_pause);
                NotificationManager manager = (NotificationManager)
                        getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1, notification);
            } else {

                remoteViews.setImageViewResource(R.id.notification__play, R.drawable.ic_play);
                NotificationManager manager = (NotificationManager)
                        getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1, notification);
            }
        }

        public void newSong(String musicUrl) {
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

        public void play() {
            if (mMediaPlayer != null) {
                mMediaPlayer.start();
            }
        }

        public void pause() {
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
            }
        }

        public void stop() {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.reset();
            }
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mMediaPlayer.start();
            if (BuildConfig.DEBUG)
                Log.d("MusicControlBinder", "start");
        }

        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            if (BuildConfig.DEBUG)
                Log.d("MusicControlBinder", "percent:" + percent);
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            if (isErrorStop) {
                isErrorStop = false;
                return;
            }
            if (BuildConfig.DEBUG)
                Log.d("MusicControlBinder", "onCompletion");
            Intent intent = new Intent(Constant.MUSIC_CONTORL_NEXT);
            intent.putExtra("statusCode", statusCode);
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
            statusCode = 0;
        }

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            mMediaPlayer.stop();
            isErrorStop = true;
            if (BuildConfig.DEBUG) {
                Log.d("MusicControlBinder", "onErrorextra:" + extra);

                Log.d("MusicControlBinder", "onErrorwhat:" + what);
            }
            Intent intent = new Intent(Constant.MUSIC_CONTORL_STOP);

            LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
            return false;
        }

        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            if (BuildConfig.DEBUG) {
                Log.d("MusicControlBinder", "onInfo " + what + ":" + extra);

            }
            statusCode = what;
            return false;
        }
    }
}
