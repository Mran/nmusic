package com.mran.nmusic.net.cloudmusic.api.Interfacelmpl;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/15.
 */
public interface CloudMusicMyMusicListDetail {
    /**
     * Gets hot music list.
     *
     * @param id the id 歌单的id
     * @return the hot music list
     */
    @Headers({"Referer:http://music.163.com/"
            ,"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @GET("playlist")
    Observable<String> getMusicListDetail(@Query("id") String id);
}
