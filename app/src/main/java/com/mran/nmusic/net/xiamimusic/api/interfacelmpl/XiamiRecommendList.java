package com.mran.nmusic.net.xiamimusic.api.interfacelmpl;


import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/28.
 */
public interface XiamiRecommendList {
    /**
     * Gets .获取推荐歌单
     *
     * @param recommendPageId the recommend page id 推荐歌单页id
     * @return the artist
     */
    @Headers({"Host:www.xiami.com",
            "Referer:http://m.xiami.com/",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @GET("http://www.xiami.com/collect/recommend/page/{id}")
    Observable<String> getRecommendList(@Path("id") int recommendPageId);

}
