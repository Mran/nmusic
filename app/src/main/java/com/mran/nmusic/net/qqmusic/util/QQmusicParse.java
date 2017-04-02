package com.mran.nmusic.net.qqmusic.util;


import com.google.gson.Gson;
import com.mran.nmusic.Constant;
import com.mran.nmusic.bean.MusicListBean;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.net.qqmusic.jsonadapter.QQmusicMusiclistDetailjson;
import com.mran.nmusic.net.qqmusic.jsonadapter.QQmusicRecommendMusicListJson;
import com.mran.nmusic.net.qqmusic.jsonadapter.QQmusicSearchJson;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * Created by 张孟尧 on 2016/10/7.
 */
public class QQmusicParse {
    public static List<MusicListBean> getMusiclistbean(QQmusicRecommendMusicListJson qQmusicRecommendMusicListJson) {
        List<MusicListBean> musicListBeanList = new ArrayList<>();
        List<QQmusicRecommendMusicListJson.DataBean.ListBean> listBeanList = qQmusicRecommendMusicListJson.getData().getList();
        for (QQmusicRecommendMusicListJson.DataBean.ListBean listBean : listBeanList) {
            MusicListBean musicListBean = new MusicListBean();
            musicListBean.setCoverImageUrl(listBean.getImgurl());
            musicListBean.setListId(listBean.getDissid());
            musicListBean.setTitle(listBean.getDissname());
            musicListBeanList.add(musicListBean);

        }
        return musicListBeanList;
    }

    public static <T> T getjson(String s, Class<T> T) {
        s = s.substring(s.indexOf("(") + 1, s.lastIndexOf(")"));

        Gson gson = new Gson();

        return gson.fromJson(s, T);
    }

    public static List<MusicListDetailBean> getMusiclistDetailbean(QQmusicMusiclistDetailjson qQmusicMusiclistDetailjson) {
        List<MusicListDetailBean> listDetailBeans = new ArrayList<>();
        List<QQmusicMusiclistDetailjson.CdlistBean.SonglistBean> listBeanList = qQmusicMusiclistDetailjson.getCdlist().get(0).getSonglist();
        for (QQmusicMusiclistDetailjson.CdlistBean.SonglistBean cdlistBean : listBeanList) {
            MusicListDetailBean musicListBean = new MusicListDetailBean();
            musicListBean.setImageUrl(cdlistBean.getAlbummid());
            musicListBean.setId(cdlistBean.getStrMediaMid());
            musicListBean.setArtits(cdlistBean.getSinger().get(0).getName());
            musicListBean.setSongName(cdlistBean.getSongname());
            musicListBean.setTag(Constant.QQTAG);
            musicListBean.setPlayable(true);

            listDetailBeans.add(musicListBean);
        }
        return listDetailBeans;
    }

    public static List<MusicListDetailBean> getMusicSearchList(QQmusicSearchJson qQmusicSearchJson) {
        List<MusicListDetailBean> listDetailBeans = new ArrayList<>();
        for (QQmusicSearchJson.DataBean.SongBean.ListBean listbean :
                qQmusicSearchJson.getData().getSong().getList()) {
            MusicListDetailBean musicListDetailBean = new MusicListDetailBean();
            musicListDetailBean.setSongName(listbean.getSongname());
            musicListDetailBean.setImageUrl(listbean.getAlbummid());
            musicListDetailBean.setId(listbean.getSongmid());
            musicListDetailBean.setArtits(listbean.getSinger().get(0).getName());
            musicListDetailBean.setTag(Constant.QQTAG);
            musicListDetailBean.setPlayable(true);

            musicListDetailBean.setAlbum(listbean.getAlbumname());
            listDetailBeans.add(musicListDetailBean);
        }
        return listDetailBeans;
    }
    public static String gzip2str(String gzip)
    {
        InputStream inputStream=new ByteArrayInputStream(gzip.getBytes());
        try {
            GZIPInputStream gzipInputStream=new GZIPInputStream(inputStream);
     return        gzipInputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
