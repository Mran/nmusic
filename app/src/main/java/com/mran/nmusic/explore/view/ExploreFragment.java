package com.mran.nmusic.explore.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mran.nmusic.BaseFragment;
import com.mran.nmusic.R;
import com.mran.nmusic.explore.adapter.ExploreViewpagerAdapter;
import com.mran.nmusic.netease.musiclist.view.NeteaseFragment;
import com.mran.nmusic.qqmusic.musiclist.view.QmusicFragment;
import com.mran.nmusic.xiami.musiclist.view.XiamiFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/4.
 */

public class ExploreFragment extends BaseFragment {
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ExploreViewpagerAdapter exploreViewpagerAdapter;
    private NeteaseFragment neteaseFragment;
    private QmusicFragment qmusicFragment;
    private XiamiFragment xiamiaFragment;
    private List<Fragment> fragmentList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.explore_fragment, container, false);
        bindView();
        if (savedInstanceState == null) {
            neteaseFragment = new NeteaseFragment();
            qmusicFragment = new QmusicFragment();
            xiamiaFragment = new XiamiFragment();
        } else {
            FragmentManager fragmentManager = getChildFragmentManager();
            List<Fragment> fragments = new ArrayList<>();
            fragmentList = fragmentManager.getFragments();
            neteaseFragment = (NeteaseFragment) fragmentList.get(0);
            xiamiaFragment = (XiamiFragment) fragmentList.get(1);

            qmusicFragment = (QmusicFragment) fragmentList.get(2);
        }
        initView();
        return view;
    }

    void bindView() {
        tabLayout = (TabLayout) view.findViewById(R.id.explore_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.explore_viewpager);
    }

    void initView() {

        fragmentList = new ArrayList<>();
        fragmentList.add(neteaseFragment);
        fragmentList.add(xiamiaFragment);
        fragmentList.add(qmusicFragment);
        exploreViewpagerAdapter = new ExploreViewpagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(exploreViewpagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }
}
