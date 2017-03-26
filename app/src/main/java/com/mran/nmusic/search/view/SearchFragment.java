package com.mran.nmusic.search.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.mran.nmusic.BaseFragment;
import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.R;
import com.mran.nmusic.netease.search.view.NeteaseSearchFragment;
import com.mran.nmusic.qqmusic.search.view.QmusicSearchFragment;
import com.mran.nmusic.search.adapter.SearchViewPagerAdapter;
import com.mran.nmusic.xiami.search.view.XiamiaSearchFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/4.
 */

public class SearchFragment extends BaseFragment implements View.OnClickListener, SearchView.OnQueryTextListener {
    NeteaseSearchFragment neteaseSearchFragment;
    QmusicSearchFragment qmusicSearchFragment;
    XiamiaSearchFragment xiamiaSearchFragment;
    private View view;
    private SearchView searchView;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private SearchViewPagerAdapter searchViewPagerAdapter;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);
        bindView();
        initView();
        fragmentManager = getFragmentManager();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void bindView() {
        searchView = (SearchView) view.findViewById(R.id.search_fragment_searchview);
        toolbar = (Toolbar) view.findViewById(R.id.search_fragment_toolbar);
        tabLayout = (TabLayout) view.findViewById(R.id.search_fragment_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.search_viewpager);
    }

    void initView() {

        toolbar.setNavigationIcon(R.drawable.back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);

                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                fragmentManager.popBackStack();
            }
        });
        neteaseSearchFragment = new NeteaseSearchFragment();
        qmusicSearchFragment = new QmusicSearchFragment();
        xiamiaSearchFragment = new XiamiaSearchFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(neteaseSearchFragment);
        fragmentList.add(xiamiaSearchFragment);
        fragmentList.add(qmusicSearchFragment);

        searchViewPagerAdapter = new SearchViewPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(searchViewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //是搜索框默认展开
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.drawable.back_material:

                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (BuildConfig.DEBUG)
            Log.d("SearchFragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG)
            Log.d("SearchFragment", "onDestroy");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!TextUtils.isEmpty(query)) {
            neteaseSearchFragment.setQuery(query);
            xiamiaSearchFragment.setQuery(query);
            qmusicSearchFragment.setQuery(query);
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}
