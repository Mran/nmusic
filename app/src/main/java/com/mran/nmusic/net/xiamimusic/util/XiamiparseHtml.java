package com.mran.nmusic.net.xiamimusic.util;

import com.google.gson.Gson;
import com.mran.nmusic.Constant;
import com.mran.nmusic.bean.MusicListBean;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.net.xiamimusic.jsonadapter.XiamiMusiclistDetailJson;
import com.mran.nmusic.net.xiamimusic.jsonadapter.XiamiSeachJson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孟尧 on 2016/8/25.
 */
public class XiamiparseHtml {
    public static <T> T getjson(String s, Class<T> T) {

        s = s.substring(s.indexOf("(") + 1, s.length() - 1);
        Gson gson = new Gson();

        return gson.fromJson(s, T);
    }

    public static List<MusicListBean> getXiamiMusicList(String s) {
        Document document = Jsoup.parse(s);
        Elements elements = document.getElementsByAttributeValue("class", "block_list clearfix").get(0).getElementsByTag("ul").get(0).getElementsByTag("li");
        List<MusicListBean> listBeanList = new ArrayList<>();
        for (Element aa : elements) {
            Element bb = aa.getElementsByTag("div").get(0).getElementsByAttributeValue("class", "block_cover").get(0);
            Element cc = bb.getElementsByTag("a").first();
            MusicListBean musicListBean = new MusicListBean();
            musicListBean.setTitle(cc.attr("title"));
            musicListBean.setListId(cc.attr("href").replace("/collect/", ""));
            Element dd = bb.getElementsByTag("img").first();
            musicListBean.setCoverImageUrl(dd.attr("src").replace("1.jpg","4.jpg"));
            listBeanList.add(musicListBean);
        }
        return listBeanList;
    }

    public static List<MusicListDetailBean> getMusiclistDetailbean(XiamiMusiclistDetailJson xiamiMusiclistDetailJson) {
        List<MusicListDetailBean> listDetailBeans = new ArrayList<>();
        List<XiamiMusiclistDetailJson.DataBean.SongsBean> listBeanList = xiamiMusiclistDetailJson.getData().getSongs();
        for (XiamiMusiclistDetailJson.DataBean.SongsBean cdlistBean : listBeanList) {
            MusicListDetailBean musicListBean = new MusicListDetailBean();
            musicListBean.setImageUrl(cdlistBean.getAlbum_logo().replace("1.jpg","4.jpg"));
            musicListBean.setMusicUrl(cdlistBean.getListen_file());
            musicListBean.setId(String.valueOf(cdlistBean.getSong_id()));
            musicListBean.setArtits(cdlistBean.getSingers());
            musicListBean.setSongName(cdlistBean.getSong_name());
            musicListBean.setPlayable(true);

            musicListBean.setTag(Constant.XIAMITAG);
            listDetailBeans.add(musicListBean);
        }
        return listDetailBeans;
    }

    public static List<MusicListDetailBean> getMusicSearchList(XiamiSeachJson xiamiSeachJson) {
        List<MusicListDetailBean> listDetailBeans = new ArrayList<>();
        for (XiamiSeachJson.DataBean.SongsBean songbean : xiamiSeachJson.getData().getSongs()) {
            MusicListDetailBean musicListDetailBean = new MusicListDetailBean();
            musicListDetailBean.setAlbum(songbean.getAlbum_name());
            musicListDetailBean.setArtits(songbean.getArtist_name());
            musicListDetailBean.setMusicUrl(songbean.getListen_file());
            musicListDetailBean.setSongName(songbean.getSong_name());
            musicListDetailBean.setImageUrl(songbean.getAlbum_logo());
            musicListDetailBean.setPlayable(true);
            musicListDetailBean.setTag(Constant.XIAMITAG);
            listDetailBeans.add(musicListDetailBean);
        }
        return listDetailBeans;
    }
}
