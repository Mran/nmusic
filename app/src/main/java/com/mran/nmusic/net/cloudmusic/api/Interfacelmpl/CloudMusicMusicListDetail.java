package com.mran.nmusic.net.cloudmusic.api.Interfacelmpl;

import com.mran.nmusic.net.cloudmusic.jsonadapter.CloudMusicListDetailsJson;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/8/15.
 */
public interface CloudMusicMusicListDetail {

        /**
         * Gets hot music list.
         *
         * @param data the data
         * @return the hot music list
         */
        @Headers({"Referer:http://music.163.com/"
                ,"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
        @FormUrlEncoded
        @POST("/weapi/v3/playlist/detail?csrf_token=")
        Observable<CloudMusicListDetailsJson> getMusicListDetail(@FieldMap Map<String,String> data);


}
