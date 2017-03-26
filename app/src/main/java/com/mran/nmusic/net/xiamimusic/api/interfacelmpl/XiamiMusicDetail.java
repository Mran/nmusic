package com.mran.nmusic.net.xiamimusic.api.interfacelmpl;


import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/24.
 */
public interface XiamiMusicDetail {
    @Headers({"Host:api.xiami.com",
            "Referer:http://m.xiami.com/",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @GET("http://www.xiami.com/song/playlist/id/{id}/object_name/default/object_id/0/cat/json")
    Observable<String> getArtist(@Query("id") String MusicId);

}
