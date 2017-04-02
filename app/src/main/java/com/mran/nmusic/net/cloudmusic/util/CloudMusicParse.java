package com.mran.nmusic.net.cloudmusic.util;


import com.google.gson.Gson;
import com.mran.nmusic.Constant;
import com.mran.nmusic.bean.MusicListBean;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.net.cloudmusic.jsonadapter.CloudMusicListDetailJson;
import com.mran.nmusic.net.cloudmusic.jsonadapter.CloudMusicSearchJson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孟尧 on 2016/8/15.
 */
public class CloudMusicParse {

    /**
     * Parse music list list.
     *
     * @param s the s接受传过来的网页内容
     * @return the list返回一个包含所有歌单的列表
     */
    public static List<MusicListBean> parseRecommendMusicList(String s) {
        List<MusicListBean> musicListBeanList = new ArrayList<MusicListBean>();
        Document document = Jsoup.parse(s);
        Element elements = document.getElementById("m-pl-container");
        Elements es = elements.getElementsByTag("li");
        for (Element aa : es) {
            MusicListBean musicListBean = new MusicListBean();
            Elements bb = aa.getElementsByClass("j-flag");
            for (Element cc : bb) {
                musicListBean.setCoverImageUrl(cc.attr("src").replace("?param=140y140", "?param=240y240"));
            }
            Elements bbb = aa.getElementsByClass("msk");
            for (Element ccc : bbb) {
                musicListBean.setTitle(ccc.attr("title"));
                String tmp = ccc.attr("href");
                musicListBean.setListId(tmp.substring(13));
            }
            musicListBeanList.add(musicListBean);
        }
        return musicListBeanList;
    }

    /**
     * Parse music list detail list.
     *
     * @param s the s一个包含歌单详情的网页
     * @return the list返回歌单详情
     */
    public static List<MusicListDetailBean> parseRecommendMusicListDetail(String s) {
        List<MusicListDetailBean> musicListDetailBeanList = new ArrayList<>();
        CloudMusicListDetailJson cloudMusicListDetailJson = new CloudMusicListDetailJson();
        Document document = Jsoup.parse(s);
        Elements elements = document.getElementsByTag("textarea");
        Gson gson = new Gson();
        String tmp = "{results:" + elements.get(0).text() + "}";
        cloudMusicListDetailJson = gson.fromJson(tmp, CloudMusicListDetailJson.class);
        for (CloudMusicListDetailJson.ResultsBean bean : cloudMusicListDetailJson.getResults()) {
            MusicListDetailBean musicListDetailBean = new MusicListDetailBean();
            musicListDetailBean.setSongName(bean.getName());
            musicListDetailBean.setTag(Constant.NETEASETAG);
            musicListDetailBean.setAlbum(bean.getAlbum().getName());
            musicListDetailBean.setArtits(bean.getArtists().get(0).getName());
            musicListDetailBean.setImageUrl(bean.getAlbum().getPicUrl());
            musicListDetailBean.setId(String.valueOf(bean.getId()));
            if (bean.getStatus() == -1)
                musicListDetailBean.setPlayable(false);
            else
                musicListDetailBean.setPlayable(true);
            musicListDetailBeanList.add(musicListDetailBean);
        }
        return musicListDetailBeanList;
    }

    public static List<MusicListDetailBean> parseMusicSearchList(CloudMusicSearchJson cloudMusicSearchJson) {
        List<MusicListDetailBean> musicListDetailBeanList = new ArrayList<>();
        for (CloudMusicSearchJson.ResultBean.SongsBean songsbean : cloudMusicSearchJson.getResult().getSongs()) {
            MusicListDetailBean musicListDetailBean = new MusicListDetailBean();
            musicListDetailBean.setImageUrl(songsbean.getAlbum().getPicUrl());
            musicListDetailBean.setMusicUrl(songsbean.getMp3Url());
            musicListDetailBean.setId(String.valueOf(songsbean.getId()));
            musicListDetailBean.setSongName(songsbean.getName());
            musicListDetailBean.setAlbum(songsbean.getAlbum().getName());
            musicListDetailBean.setArtits(songsbean.getArtists().get(0).getName());

                musicListDetailBean.setPlayable(songsbean.getStatus()>=0&&songsbean.getFee()!=4);

            musicListDetailBean.setTag(Constant.NETEASETAG);
            musicListDetailBeanList.add(musicListDetailBean);
        }
        return musicListDetailBeanList;

    }
}
