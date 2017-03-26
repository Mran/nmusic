package com.mran.nmusic.net.cloudmusic.bean;

/**
 * Created by 张孟尧 on 2016/8/15.
 */
public class MusicListDetailBean {
    private String songName;//歌名
    private String imageUrl;//歌曲封面链接
    private String artits;//演唱者
    private String album;//专辑
    private String id;//歌曲id
    private String musicUrl;//歌曲连接
    private boolean playable;//判断是否能够播放
    private int tag;//标记,判断是网易,QQ,Xiami
    private boolean isPlaying = false;

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }


    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArtits() {
        return artits;
    }

    public void setArtits(String artits) {
        this.artits = artits;
    }
}
