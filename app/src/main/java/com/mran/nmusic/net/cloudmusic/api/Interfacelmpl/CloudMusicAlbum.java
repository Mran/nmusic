package com.mran.nmusic.net.cloudmusic.api.Interfacelmpl;


import com.mran.nmusic.net.cloudmusic.jsonadapter.CloudMusicAlbumJson;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/22.
 */
public interface CloudMusicAlbum {
    @Headers({"Referer:http://music.163.com/"
            ,"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @GET("api/album/{id}")
    Observable<CloudMusicAlbumJson> getAlbum(@Path("id") String id);
}
