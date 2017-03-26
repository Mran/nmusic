package com.mran.nmusic.net.qqmusic.api.Interfacelmpl;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/10/11.
 */
public interface QQmusicUrl {
    @Headers({"Accept:application/json, text/plain, */*","Referer:http://y.qq.com/","User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.113 Safari/537.36"})
    @GET("http://base.music.qq.com/fcgi-bin/fcg_musicexpress.fcg?json=3&guid=780782017&g_tk=938407465&loginUin=0&hostUin=0&format=jsonp&inCharset=GB2312&outCharset=GB2312&notice=0&platform=yqq&jsonpCallback=jsonCallback&needNewCode=0")
    Observable<String> getMusicUrl();

}
