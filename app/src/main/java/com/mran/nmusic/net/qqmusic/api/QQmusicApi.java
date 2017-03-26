package com.mran.nmusic.net.qqmusic.api;

import com.mran.nmusic.Constant;
import com.mran.nmusic.net.qqmusic.api.Interfacelmpl.QQmusicMusiclistDetail;
import com.mran.nmusic.net.qqmusic.api.Interfacelmpl.QQmusicRecommendMusicList;
import com.mran.nmusic.net.qqmusic.api.Interfacelmpl.QQmusicSearch;
import com.mran.nmusic.net.qqmusic.api.Interfacelmpl.QQmusicUrl;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
 * Created by 张孟尧 on 2016/10/6.
 */
public class QQmusicApi {
    // /创建代理服务器
    private static InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);
    private static Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理

    private static QQmusicRecommendMusicList qQmusicRecommendMusicList;
    private static QQmusicMusiclistDetail qQmusicMusiclistDetail;
    private static QQmusicUrl qQmusicUrl;
    private static QQmusicSearch qQmusicSearch;
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
              .cookieJar(cookieJar)
                        .connectTimeout(Constant.DEFAULTTIMEOUT0, TimeUnit.MILLISECONDS)
            .readTimeout(Constant.DEFAULTTIMEOUT0, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    public static QQmusicRecommendMusicList getqQmusicRecommendMusicList() {
        if (qQmusicRecommendMusicList == null) {
            Retrofit retrofit = new Retrofit.Builder().client(client)
                    .baseUrl("http://y.qq.com")
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            qQmusicRecommendMusicList = retrofit.create(QQmusicRecommendMusicList.class);
        }
        return qQmusicRecommendMusicList;
    }

    public static QQmusicMusiclistDetail getqQmusicMusiclistDetail() {
        if (qQmusicMusiclistDetail == null) {
            Retrofit retrofit = new Retrofit.Builder().client(client)
                    .baseUrl("http://y.qq.com")
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            qQmusicMusiclistDetail = retrofit.create(QQmusicMusiclistDetail.class);
        }
        return qQmusicMusiclistDetail;
    }

    public static QQmusicUrl getqQmusicUrl() {
        if (qQmusicUrl == null) {
            Retrofit retrofit = new Retrofit.Builder().client(client)
                    .baseUrl("http://y.qq.com")
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            qQmusicUrl = retrofit.create(QQmusicUrl.class);
        }
        return qQmusicUrl;
    }
    public static QQmusicSearch getqQmusicSearch() {
        if (qQmusicSearch== null) {
            Retrofit retrofit = new Retrofit.Builder().client(client)
                    .baseUrl("http://y.qq.com")
                    .addConverterFactory(scalarsConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            qQmusicSearch= retrofit.create(QQmusicSearch.class);
        }
        return qQmusicSearch;
    }
}
