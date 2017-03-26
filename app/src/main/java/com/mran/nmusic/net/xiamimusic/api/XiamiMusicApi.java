package com.mran.nmusic.net.xiamimusic.api;


import com.mran.nmusic.Constant;
import com.mran.nmusic.net.xiamimusic.api.interfacelmpl.XiamiAlbumDetail;
import com.mran.nmusic.net.xiamimusic.api.interfacelmpl.XiamiArtistDetail;
import com.mran.nmusic.net.xiamimusic.api.interfacelmpl.XiamiArtistHotMusic;
import com.mran.nmusic.net.xiamimusic.api.interfacelmpl.XiamiMusicDetail;
import com.mran.nmusic.net.xiamimusic.api.interfacelmpl.XiamiMusiclistDeatail;
import com.mran.nmusic.net.xiamimusic.api.interfacelmpl.XiamiRecommendList;
import com.mran.nmusic.net.xiamimusic.api.interfacelmpl.XiamiSearchMusic;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

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

/**
 * Created by 张孟尧 on 2016/8/22.
 */
public class XiamiMusicApi {
    private static final ThreadLocal<XiamiSearchMusic> xiamiSearchMusic = new ThreadLocal<>();
    // /创建代理服务器
    private static InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);
    private static Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
    private static XiamiAlbumDetail xiamiAlbumDetail;
    private static XiamiArtistHotMusic xiamiArtistHotMusic;
    private static XiamiArtistDetail xiamiArtistDetail;
    private static XiamiMusicDetail xiamiMusicDetail;
    private static XiamiRecommendList xiamiRecommendList;
    private static XiamiMusiclistDeatail xiamiMusiclistDeatail;
    private static ScalarsConverterFactory scalarsConverterFactory = ScalarsConverterFactory.create();
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
            //            .connectTimeout(Constant.DEFAULTTIMEOUT0, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE))
            .build();

    public static XiamiSearchMusic getSearchMusic() {
        if (xiamiSearchMusic.get() == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.xiamiBaseUrl)
                    .client(client)
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            xiamiSearchMusic.set(retrofit.create(XiamiSearchMusic.class));
        }
        return xiamiSearchMusic.get();
    }

    public static XiamiAlbumDetail getXiamiAlbumDetail() {
        if (xiamiAlbumDetail == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.xiamiBaseUrl)
                    .client(client)
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            xiamiAlbumDetail = retrofit.create(XiamiAlbumDetail.class);
        }
        return xiamiAlbumDetail;
    }

    public static XiamiArtistDetail getXiamiArtistDetail() {
        if (xiamiArtistDetail == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.xiamiBaseUrl)
                    .client(client)
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            xiamiArtistDetail = retrofit.create(XiamiArtistDetail.class);
        }
        return xiamiArtistDetail;
    }

    public static XiamiArtistHotMusic getXiamiArtistHotMusic() {
        if (xiamiArtistHotMusic == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.xiamiBaseUrl)
                    .client(client)
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            xiamiArtistHotMusic = retrofit.create(XiamiArtistHotMusic.class);
        }
        return xiamiArtistHotMusic;
    }

    public static XiamiMusicDetail getXiamiMusicDetail() {
        if (xiamiMusicDetail == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.xiamiBaseUrl)
                    .client(client)
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            xiamiMusicDetail = retrofit.create(XiamiMusicDetail.class);
        }
        return xiamiMusicDetail;
    }

    public static XiamiRecommendList getXiamiRecommendList() {
        if (xiamiRecommendList == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.xiamiBaseUrl)
                    .client(client)
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            xiamiRecommendList = retrofit.create(XiamiRecommendList.class);
        }
        return xiamiRecommendList;
    }

    public static XiamiMusiclistDeatail getXiamiMusiclistDeatail() {
        if (xiamiMusiclistDeatail == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.xiamiBaseUrl)
                    .client(client)
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            xiamiMusiclistDeatail = retrofit.create(XiamiMusiclistDeatail.class);
        }
        return xiamiMusiclistDeatail;
    }
}
