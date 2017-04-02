package com.mran.nmusic;

import android.content.Context;

import solid.ren.skinlibrary.base.SkinBaseApplication;

/**
 * Created by 张孟尧 on 2016/8/5.
 */
public class BaseApplication extends SkinBaseApplication {
    private static Context context;


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        BaseApplication.context = context;
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
