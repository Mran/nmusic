package com.mran.nmusic.net.cloudmusic.api.Interfacelmpl;


import com.mran.nmusic.net.cloudmusic.jsonadapter.MusicUrlJson;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

import java.util.Map;

/**
 * Created by 张孟尧 on 2016/8/19.
 */
public interface CloudMusicMusicUrl {
    /**
     * Gets music url.
     *
     * @param data the data加密后的请求体
     * @return the music url 返回含有歌曲地址的json
     */
    @Headers({"Referer:http://music.163.com/"
            ,"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @FormUrlEncoded
    @POST("weapi/song/enhance/player/url?csrf_token=")
    Observable<MusicUrlJson> getMusicUrl(@FieldMap Map<String, String> data);
}
