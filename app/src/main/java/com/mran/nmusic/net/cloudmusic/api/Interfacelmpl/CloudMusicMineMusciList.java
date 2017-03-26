package com.mran.nmusic.net.cloudmusic.api.Interfacelmpl;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/13.
 */
public interface CloudMusicMineMusciList {
    /**
     * Gets hot music list.
     *
     * @param order  the order请求的歌单类型,默认的是hot,即热门歌单
     * @param limit  the limit一次请求的数量,默认是35
     * @param offset the offset偏移量,0表示从头开始搜索,1表示从1开始搜索,依次类推,默认为35的倍数
     * @return the hot music list
     */
    @Headers({"Referer:http://music.163.com/"
            ,"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @GET("discover/playlist/")
    Observable<String> getRecommendMusicList(@Query("order") String order, @Query("limit") int limit, @Query("offset") int offset);
}
