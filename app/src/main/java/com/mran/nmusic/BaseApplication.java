package com.mran.nmusic;

import android.content.Context;

import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;

import java.util.List;

import solid.ren.skinlibrary.base.SkinBaseApplication;

/**
 * Created by 张孟尧 on 2016/8/5.
 */
public class BaseApplication extends SkinBaseApplication {
    private static Context context;
    private static List<MusicListDetailBean> detailBeanList;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        BaseApplication.context = context;
    }

    public static List<MusicListDetailBean> getDetailBeanList() {
        return detailBeanList;
    }

    public static void setDetailBeanList(List<MusicListDetailBean> detailBeanList) {
        BaseApplication.detailBeanList.clear();
        detailBeanList.addAll(detailBeanList);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //        SkinConfig.setCanChangeStatusColor(true);
        //        SkinConfig.addSupportAttr("navigationViewMenu", new NavigationViewAttr());
        //        SkinConfig.addSupportAttr("CollapsingToolbarLayoutcontent",new CollapsingToolbarViewAttr());
    }
}
