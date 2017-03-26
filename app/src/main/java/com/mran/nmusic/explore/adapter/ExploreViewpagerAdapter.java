package com.mran.nmusic.explore.adapter;


import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.mran.nmusic.Constant;
import com.mran.nmusic.netease.musiclist.view.NeteaseFragment;
import com.mran.nmusic.qqmusic.musiclist.view.QmusicFragment;
import com.mran.nmusic.xiami.musiclist.view.XiamiFragment;

import java.util.List;


/**
 * Created by 张孟尧 on 2016/3/18.
 * scheuleviewpager的适配器
 */
public class ExploreViewpagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;

    public ExploreViewpagerAdapter(FragmentManager fm, List<Fragment> tabblefragmentList) {
        super(fm);
        fragmentList = tabblefragmentList;
        fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {

                return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //    @Override
    //    public void destroyItem(ViewGroup container, int position, Object object) {
    //
    //        super.destroyItem(container, position, object);
    //    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constant.THREETITLE[position];
    }
}
