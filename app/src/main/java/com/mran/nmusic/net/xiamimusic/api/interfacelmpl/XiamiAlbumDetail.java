package com.mran.nmusic.net.xiamimusic.api.interfacelmpl;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/24.
 */
public interface XiamiAlbumDetail {
    /**
     * Gets album detail.
     *
     * @param albumid   the key 专辑id
     * @param page  the page 第几页,默认1
     * @param limit the limit 一页的数量,默认20
     * @return the album detail 返回专辑的歌曲详情
     */
    @Headers({"Host:api.xiami.com",
            "Referer:http://m.xiami.com/",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @GET("web?v=2.0&app_key=1&callback=jsonp217&r=album/detail")
    Observable<String> getAlbumDetail(@Query("id") int albumid, @Query("page") int page, @Query("limit") int limit);
}
