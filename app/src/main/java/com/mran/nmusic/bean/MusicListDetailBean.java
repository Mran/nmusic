package com.mran.nmusic.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 张孟尧 on 2016/8/15.
 */
public class MusicListDetailBean implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.songName);
        dest.writeString(this.imageUrl);
        dest.writeString(this.artits);
        dest.writeString(this.album);
        dest.writeString(this.id);
        dest.writeString(this.musicUrl);
        dest.writeByte(this.playable ? (byte) 1 : (byte) 0);
        dest.writeInt(this.tag);
        dest.writeByte(this.isPlaying ? (byte) 1 : (byte) 0);
    }

    public void readToParcel(Parcel dest) {
        songName = dest.readString();
        imageUrl = dest.readString();
        artits = dest.readString();
        album = dest.readString();
        id = dest.readString();
        musicUrl = dest.readString();
        playable = dest.readByte() == 1;
        tag = dest.readInt();
        isPlaying = dest.readByte() == 1;
    }

    public MusicListDetailBean() {
    }

    protected MusicListDetailBean(Parcel in) {
        this.songName = in.readString();
        this.imageUrl = in.readString();
        this.artits = in.readString();
        this.album = in.readString();
        this.id = in.readString();
        this.musicUrl = in.readString();
        this.playable = in.readByte() != 0;
        this.tag = in.readInt();
        this.isPlaying = in.readByte() != 0;
    }

    public static final Creator<MusicListDetailBean> CREATOR = new Creator<MusicListDetailBean>() {
        @Override
        public MusicListDetailBean createFromParcel(Parcel source) {
            return new MusicListDetailBean(source);
        }

        @Override
        public MusicListDetailBean[] newArray(int size) {
            return new MusicListDetailBean[size];
        }
    };
}
