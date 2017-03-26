package com.mran.nmusic.net.cloudmusic.bean;

/**
 * Created by 张孟尧 on 2016/8/15.
 */
public class MusicListBean {
    private String coverImageUrl;
    private String title;
    private String listId;

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }
}
