package com.mran.nmusic.net.xiamimusic.api.interfacelmpl;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/22.
 */
public interface XiamiSearchMusic {
    /**
     * Gets music search.
     *
     * @param key   the key 搜索的关键字
     * @param page  the page 页码
     * @param limit the limit 数量
     * @return the music search
     */
    @Headers({"Host:api.xiami.com",
            "Referer:http://m.xiami.com/",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @GET("web?v=2.0&app_key=1&callback=jsonp154&r=search/songs")
    Observable<String> getMusicSearch(@Query("key") String key, @Query("page") int page, @Query("limit") int limit);
}