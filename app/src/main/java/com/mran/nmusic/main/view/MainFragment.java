package com.mran.nmusic.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mran.nmusic.BaseFragment;
import com.mran.nmusic.R;
import com.mran.nmusic.explore.view.ExploreFragment;
import com.mran.nmusic.main.adapter.MainViewpagerAdapter;
import com.mran.nmusic.mine.view.MineFragment;
import com.mran.nmusic.search.view.SearchFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/4.
 */

public class MainFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener {
    private Toolbar toolbar;
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ExploreFragment exploreFragment;
    private MineFragment mineFragment;
    private List<Fragment> fragmentList;
    private MainViewpagerAdapter mainViewpagerAdapter;
    private SearchFragment searchFragment;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);
        bindView();
        initView();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void bindView() {
        toolbar = (Toolbar) view.findViewById(R.id.main_fragment_toolbar);
        tabLayout = (TabLayout) view.findViewById(R.id.main_fragment_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.main_viewpager);
    }

    void initView() {
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(this);
        exploreFragment = new ExploreFragment();
        mineFragment = new MineFragment();
        fragmentList = new ArrayList<>();
//        fragmentList.add(mineFragment);
        fragmentList.add(exploreFragment);

        mainViewpagerAdapter = new MainViewpagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(mainViewpagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        fragmentManager = getFragmentManager();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        searchFragment = new SearchFragment();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("mainfragment")
                .add(R.id.main_fragment_holder, searchFragment, "searchfragment")
                .hide(this).commit();

        return false;
    }


}
