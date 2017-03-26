package com.mran.nmusic.net.cloudmusic.api.Interfacelmpl;



import com.mran.nmusic.net.cloudmusic.jsonadapter.CloudMusicSearchJson;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

import java.util.Map;

/**
 * Created by 张孟尧 on 2016/8/12.
 */
public interface SearchMusic {
    /**
     * Gets result.
     *
     * @param data the data 包含搜索内容的请求体
     * @return the result
     */
    @Headers({"Referer:http://music.163.com/"
            ,"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"})
    @FormUrlEncoded
    @POST("api/search/pc")
    Observable<CloudMusicSearchJson> getResult(@FieldMap Map<String, String> data);

}
