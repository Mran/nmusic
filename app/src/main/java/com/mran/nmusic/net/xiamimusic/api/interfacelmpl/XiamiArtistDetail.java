package com.mran.nmusic.net.xiamimusic.api.interfacelmpl;


import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/24.
 */
public interface XiamiArtistDetail {
    /**
     * Gets artist.
     *
     * @param artisId the artis id 艺术家id
     * @param page    the page 默认1
     * @param limit   the limit 默认20
     * @return the artist
     */
    @Headers({"Host:api.xiami.com",
            "Referer:http://m.xiami.com/",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @GET("web?v=2.0&app_key=1&_ksTS=1459931285956_21&callback=jsonp217&r=artist/detail")
    Observable<String> getArtist(@Query("id") int artisId, @Query("page") int page, @Query("limit") int limit);

}
