package com.mran.nmusic.qqmusic.search.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mran.nmusic.BaseApplication;
import com.mran.nmusic.BaseFragment;
import com.mran.nmusic.R;
import com.mran.nmusic.mainactivity.MainActivity;
import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;
import com.mran.nmusic.netease.search.adapter.MusicSearchAdapter;
import com.mran.nmusic.netease.search.view.MusicSearchRecycleviewItemDecoration;
import com.mran.nmusic.qqmusic.search.presenter.QQmusicSearchPresenterCompl;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/4.
 */

public class QmusicSearchFragment extends BaseFragment implements IQQmusicSearchFragment, MusicSearchAdapter.OnRecyclerItemClickedListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private View view;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private MusicSearchAdapter musicSearchAdapter;
    private QQmusicSearchPresenterCompl qmusicSearchPresenterCompl;
    private String query;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.music_search_viewpager_content, container, false);
        bindView();
        initView();
        qmusicSearchPresenterCompl = new QQmusicSearchPresenterCompl(this);
        mainActivity = (MainActivity) getActivity();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void bindView() {
        pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.music_search_viewpager_content_recycleview);
    }

    void initView() {
        musicSearchAdapter = new MusicSearchAdapter();
        musicSearchAdapter.setOnRecyclerItemClickListener(this);
        pullLoadMoreRecyclerView.setAdapter(musicSearchAdapter);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.addItemDecoration(new MusicSearchRecycleviewItemDecoration(BaseApplication.getContext(),0.05));

        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
    }

    @Override
    public void onItemClick(View view, int position, MusicListDetailBean musicListDetailBean) {
        mainActivity.add(musicListDetailBean);

    }

    @Override
    public void onRefresh() {
        qmusicSearchPresenterCompl.getSearchResult(false, query);

    }

    @Override
    public void onLoadMore() {
        qmusicSearchPresenterCompl.getSearchResult(true, query);
    }

    @Override
    public void showResult(boolean isLoadMore, List<MusicListDetailBean> musicListDetailBeen) {
        pullLoadMoreRecyclerView.setRefreshing(false);
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        musicSearchAdapter.adddata(isLoadMore, musicListDetailBeen);
    }

    @Override
    public void showError(String error) {
        pullLoadMoreRecyclerView.setRefreshing(false);

        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        Toast.makeText(BaseApplication.getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        qmusicSearchPresenterCompl.cancle();
    }

    public void setQuery(String query) {
        this.query = query;
        if (this.isVisible()) {
            pullLoadMoreRecyclerView.setRefreshing(true);
            qmusicSearchPresenterCompl.getSearchResult(false, query);
        }
    }
}
