package com.mran.nmusic.net.qqmusic.api.Interfacelmpl;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/10/8.
 */
public interface QQmusicMusiclistDetail {
    @Headers({"Accept:application/json, text/plain, */*","Cache-Control:no-cache","Connection:keep-alive"/*,"Accept-Encoding:gzip, deflate, sdch"*/,"Upgrade-Insecure-Requests:1","Referer:http://y.qq.com/","User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.113 Safari/537.36"})

    @GET("http://i.y.qq.com/qzone-music/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg?type=1&json=1&utf8=1&onlysong=0&jsonpCallback=jsonCallback&nosign=1&g_tk=5381&loginUin=0&hostUin=0&format=jsonp&inCharset=GB2312&outCharset=utf-8&notice=0&platform=yqq&jsonpCallback=jsonCallback&needNewCode=0")
    Observable<String> getMusiclistDetail(@Query("disstid") String dissid);
}
