package com.mran.nmusic.net.qqmusic.api.Interfacelmpl;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/10/14.
 */

public interface QQmusicSearch {
    @Headers({"Accept:application/json, text/plain, */*","Referer:http://y.qq.com/","User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.113 Safari/537.36"})
@GET("http://i.y.qq.com/s.music/fcgi-bin/search_for_qq_cp?g_tk=938407465&uin=0&format=jsonp&inCharset=utf-8&outCharset=utf-8&notice=0&platform=h5&needNewCode=1&&zhidaqu=1&catZhida=1&t=0&flag=1&ie=utf-8&sem=1&aggr=0&perpage=20&n=20&remoteplace=txt.mqq.all&jsonpCallback=jsonp4")
    Observable<String> getSearch(@Query("w")String query,@Query("p") int pageid ,@Query("_")long time);
}
