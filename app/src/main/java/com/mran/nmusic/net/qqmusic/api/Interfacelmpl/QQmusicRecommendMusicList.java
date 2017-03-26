package com.mran.nmusic.net.qqmusic.api.Interfacelmpl;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 张孟尧 on 2016/10/7.
 */
public interface QQmusicRecommendMusicList {
    /**
     * Gets recommend music list.获取QQ音乐推荐列表
     *
     * @param sortId the sort id 列表页id
     * @param ein    the ein 歌单终止位置
     *               @param sin  the sin 歌单起始位置
     * @return the recommend music list
     */
    @Headers({"Accept:application/json, text/plain, */*","Referer:http://y.qq.com/","User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.113 Safari/537.36"})
    @GET("http://i.y.qq.com/s.plcloud/fcgi-bin/fcg_get_diss_by_tag.fcg?categoryId=10000000&format=jsonp&g_tk=5381&loginUin=0&hostUin=0&format=jsonp&inCharset=utf-8&outCharset=utf-8&notice=0&platform=yqq&jsonpCallback=MusicJsonCallback&needNewCode=0")
    Observable<String> getRecommendMusicList(@Query("sortId") int sortId, @Query("sin")int sin,@Query("ein") int ein);
}
