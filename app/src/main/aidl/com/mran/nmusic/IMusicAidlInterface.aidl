// IMusicAidlInterface.aidl
package com.mran.nmusic;

// Declare any non-default types here with import statements
import com.mran.nmusic.bean.MusicListDetailBean;
interface IMusicAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

            void play();//开始
            void stop();//停止
            void next(boolean isforce);//下一首
            void previous();//上一首
            void playByIndex(int index);
            boolean isPlaying();//是否正在播放
            void setPlayMode( int mode);//设置播放模式
            int getPlayMode();//获取播放模式
            void setSleep(long time);//设置睡眠时间
            MusicListDetailBean getMusic();//获取当前播放音乐
            String getMusicName();//获取音乐名
            String getMusicImgUrl();//获取音乐封面链接
            String getMusicSinger();//获取歌手名
            String getMusicId();//获取音乐id;
            void addMusic(in MusicListDetailBean music);//添加一首音乐
            void addMusicList(in List<MusicListDetailBean> musicList);//添加一个歌单到当前播放列表
            List<MusicListDetailBean> getMusicList();//获取当前播放列表
            int getMusicListSize();//获取音乐列表中的音乐数量
            void deleteMusic(int index);//删除某一位置音乐
            void deleteAllMusic();//删除列表中的所有音乐

}
