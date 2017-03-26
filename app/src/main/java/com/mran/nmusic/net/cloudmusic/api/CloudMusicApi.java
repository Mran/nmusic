package com.mran.nmusic.net.cloudmusic.api;


import com.mran.nmusic.Constant;
import com.mran.nmusic.net.cloudmusic.api.Interfacelmpl.CloudMusicAlbum;
import com.mran.nmusic.net.cloudmusic.api.Interfacelmpl.CloudMusicArtist;
import com.mran.nmusic.net.cloudmusic.api.Interfacelmpl.CloudMusicMusicListDetail;
import com.mran.nmusic.net.cloudmusic.api.Interfacelmpl.CloudMusicMusicUrl;
import com.mran.nmusic.net.cloudmusic.api.Interfacelmpl.CloudMusicRecommendMusciList;
import com.mran.nmusic.net.cloudmusic.api.Interfacelmpl.SearchMusic;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孟尧 on 2016/8/14.
 */
public class CloudMusicApi {
    // /创建代理服务器
   private static InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);
   private static Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理

    private static CloudMusicRecommendMusciList cloudMusicRecommendMusciList;
    private static SearchMusic searchMusic;
    private static CloudMusicMusicListDetail cloudMusicMusicListDetail;
    private static CloudMusicMusicUrl cloudMusicMusicUrl;
    private static CloudMusicArtist cloudMusicArtist;
    private static CloudMusicAlbum cloudMusicAlbum;
    private static ScalarsConverterFactory scalarsConverterFactory=ScalarsConverterFactory.create();
    private static GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    private static CookieJar cookieJar = new CookieJar() {
        List<Cookie> cookies;

        @Override
        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
            cookies = list;
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    };
    private static OkHttpClient client = new OkHttpClient.Builder()
//            .proxy(proxy)
            .cookieJar(cookieJar)

            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    public static SearchMusic getSearchMusic() {
        if (searchMusic == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.cloudMusicBaseUrl)
                    .client(client)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            searchMusic = retrofit.create(SearchMusic.class);
        }
        return searchMusic;
    }

    public static CloudMusicRecommendMusciList getCloudMusicRecommendMusciList() {
        if (cloudMusicRecommendMusciList == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.cloudMusicBaseUrl)
                    .client(client)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(scalarsConverterFactory)
                    .build();
            cloudMusicRecommendMusciList = retrofit.create(CloudMusicRecommendMusciList.class);
        }
        return cloudMusicRecommendMusciList;
    }
    public static CloudMusicMusicListDetail getMusiclistDetail() {
        if (cloudMusicMusicListDetail == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.cloudMusicBaseUrl)
                    .client(client)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(scalarsConverterFactory)
                    .build();
            cloudMusicMusicListDetail = retrofit.create(CloudMusicMusicListDetail.class);
        }
        return cloudMusicMusicListDetail;
    }
    public static CloudMusicMusicUrl getCloudMusicMusicUrl() {
        if (cloudMusicMusicUrl == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.cloudMusicBaseUrl)
                    .client(client)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            cloudMusicMusicUrl = retrofit.create(CloudMusicMusicUrl.class);
        }
        return cloudMusicMusicUrl;
    }
    public static CloudMusicArtist getCloudMusicArtist() {
        if (cloudMusicArtist == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.cloudMusicBaseUrl)
                    .client(client)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            cloudMusicArtist = retrofit.create(CloudMusicArtist.class);
        }
        return cloudMusicArtist;
    }
    public static CloudMusicAlbum getCloudMusicAblum() {
        if (cloudMusicAlbum == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.cloudMusicBaseUrl)
                    .client(client)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            cloudMusicAlbum = retrofit.create(CloudMusicAlbum.class);
        }
        return cloudMusicAlbum;
    }
}
