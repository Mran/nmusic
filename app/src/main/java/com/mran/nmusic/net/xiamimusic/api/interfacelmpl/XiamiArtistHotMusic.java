package com.mran.nmusic.net.xiamimusic.api.interfacelmpl;


import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/24.
 */
public interface XiamiArtistHotMusic {
    /**
     * Gets artist hot music.
     *
     * @param artistId the artist id 艺术家id
     * @param page     the page 页码 默认1 依次累加到10
     * @param limit    the limit 一页的数量 默认20
     * @return the artist hot music
     */
    @Headers({"Host:api.xiami.com",
            "Referer:http://m.xiami.com/",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @GET("web?v=2.0&app_key=16&callback=jsonp217&r=artist/hot-songs")
    Observable<String> getArtistHotMusic(@Query("id") String artistId, @Query("page") String page, @Query("limit") String limit);

}
