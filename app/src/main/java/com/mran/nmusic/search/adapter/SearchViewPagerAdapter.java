package com.mran.nmusic.search.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mran.nmusic.Constant;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/13.
 */

public class SearchViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;

    public SearchViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constant.THREETITLE[position];
    }
}
