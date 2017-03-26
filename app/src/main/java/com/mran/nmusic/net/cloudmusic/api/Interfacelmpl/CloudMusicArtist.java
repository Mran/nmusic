package com.mran.nmusic.net.cloudmusic.api.Interfacelmpl;


import com.mran.nmusic.net.cloudmusic.jsonadapter.CloudMusicArtistJson;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/22.
 */
public interface CloudMusicArtist {
    /**
     * Gets artist.
     *
     * @param id the id 艺术家id
     * @return the artist 返回含有艺术家id的json
     */
    @Headers({"Referer:http://music.163.com/"
            ,"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @GET("api/artist/{id}")
    Observable<CloudMusicArtistJson> getArtist(@Path("id") String id);
}
