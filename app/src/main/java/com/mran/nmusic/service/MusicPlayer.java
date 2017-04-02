package com.mran.nmusic.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.mran.nmusic.Constant;
import com.mran.nmusic.IMusicAidlInterface;
import com.mran.nmusic.bean.MusicListDetailBean;

import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by M on 2017/4/1.
 */

public class MusicPlayer {
    private static IMusicAidlInterface sIMusicAidlInterface;

    private static PlayerServiceConnect sPlayerServiceConnect;

    //
    public static final void bindService(Context context) {

        Intent intent = new Intent(context, MusicPlayService.class);
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                sIMusicAidlInterface = IMusicAidlInterface.Stub.asInterface(iBinder);

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        context.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        context.startService(intent);
    }

    public static void playOrPsuse() {
        try {
            if (sIMusicAidlInterface.isPlaying()) {
                sIMusicAidlInterface.stop();
            } else sIMusicAidlInterface.play();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPlaying() {
        try {
            return sIMusicAidlInterface.isPlaying();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void next(boolean isforce) {
        try {
            sIMusicAidlInterface.next(isforce);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void previours() {
        try {
            sIMusicAidlInterface.previous();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void playByIndex(int index) {
        try {
            sIMusicAidlInterface.playByIndex(index);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void setPlayMode(int playMode) {
        try {
            sIMusicAidlInterface.setPlayMode(playMode);
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    public static int getPlayMode() {
        try {
            return sIMusicAidlInterface.getPlayMode();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return Constant.ALLREPEAT;
    }

    public static MusicListDetailBean getMusic() {
        try {
            return sIMusicAidlInterface.getMusic();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addMusic(MusicListDetailBean musicListDetailBean) {
        try {
            sIMusicAidlInterface.addMusic(musicListDetailBean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void addMusicList(List<MusicListDetailBean> listDetailBeen) {
        try {
            sIMusicAidlInterface.addMusicList(listDetailBeen);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static List<MusicListDetailBean> getMusicList() {
        try {
            return sIMusicAidlInterface.getMusicList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteMusic(int index) {
        try {
            sIMusicAidlInterface.deleteMusic(index);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void deleteALlMusic() {
        try {
            sIMusicAidlInterface.deleteAllMusic();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static String getMusicName() {
        try {
            return sIMusicAidlInterface.getMusicName();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMusicSinger() {
        try {
            return sIMusicAidlInterface.getMusicSinger();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMusicImgUrl() {
        try {
            return sIMusicAidlInterface.getMusicImgUrl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMusicId() {
        try {
            return sIMusicAidlInterface.getMusicId();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getMusicListSize() {
        try {
            return sIMusicAidlInterface.getMusicListSize();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static final class PlayerServiceConnect implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            sIMusicAidlInterface = IMusicAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
