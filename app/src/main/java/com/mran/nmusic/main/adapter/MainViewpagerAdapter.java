package com.mran.nmusic.main.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.mran.nmusic.Constant;

import java.util.List;


/**
 * Created by 张孟尧 on 2016/3/18.
 * scheuleviewpager的适配器
 */
public class MainViewpagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mscheduleTabblefragmentList;
    private FragmentManager fragmentManager;

    public MainViewpagerAdapter(FragmentManager fm, List<Fragment> tabblefragmentList) {
        super(fm);
        mscheduleTabblefragmentList = tabblefragmentList;
        fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return mscheduleTabblefragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mscheduleTabblefragmentList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return Constant.TITLE[position];
    }
}
