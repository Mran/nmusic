package com.mran.nmusic.qqmusic.musiclist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mran.nmusic.BaseFragment;
import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.R;
import com.mran.nmusic.bean.MusicListBean;
import com.mran.nmusic.adapter.MusiclistAdapter;
import com.mran.nmusic.netease.musiclist.view.IMusiclistFragment;
import com.mran.nmusic.qqmusic.musiclist.presenter.QQmusicPresenterCompl;
import com.mran.nmusic.qqmusic.musiclistdetail.view.QQMusicListDetailFragment;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/4.
 */

public class QmusicFragment extends BaseFragment implements IMusiclistFragment, PullLoadMoreRecyclerView.PullLoadMoreListener, MusiclistAdapter.OnRecyclerItemClickedListener {
    private View view;
    private PullLoadMoreRecyclerView recyclerView;
    private MusiclistAdapter musiclistAdapter;
    private QQmusicPresenterCompl qqmusicPresenterCompl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.explore_viewpager_content_fragment, container, false);
        qqmusicPresenterCompl = new QQmusicPresenterCompl(getActivity(), this);
        bindView();
        initView();
        musiclistAdapter.setOnRecyclerItemClickListener(this);

        if (BuildConfig.DEBUG)
            Log.d("QmusicFragment", "onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setRefreshing(true);

        qqmusicPresenterCompl.loadMusiclist(false);


    }

    @Override
    public void onItemClick(View view, int position, MusicListBean musicListBean) {
        QQMusicListDetailFragment qqMusicListDetailFragment = QQMusicListDetailFragment.newInstance(musicListBean.getCoverImageUrl(), musicListBean.getListId(), musicListBean.getTitle());
        FragmentManager fragmentManager = getParentFragment().getParentFragment().getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack("mainfragment").add(R.id.main_fragment_holder, qqMusicListDetailFragment).hide(fragmentManager.getFragments().get(0)).commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void bindView() {

        recyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.explore_viewpage_content_recycleview);
    }

    void initView() {
        musiclistAdapter = new MusiclistAdapter(getActivity());
        recyclerView.setAdapter(musiclistAdapter);
        recyclerView.setGridLayout(2);
        recyclerView.setOnPullLoadMoreListener(this);
    }

    @Override
    public void showError(String error) {
        recyclerView.setRefreshing(false);
        recyclerView.setPullLoadMoreCompleted();
        Log.d("QmusicFragment", error);
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showResult(boolean isloadmore, List<MusicListBean> musicListBeen) {
        recyclerView.setRefreshing(false);
        recyclerView.setPullLoadMoreCompleted();
        musiclistAdapter.adddata(isloadmore, musicListBeen);
    }

    @Override
    public void onRefresh() {
        qqmusicPresenterCompl.loadMusiclist(false);
    }

    @Override
    public void onLoadMore() {
        qqmusicPresenterCompl.loadMusiclist(true);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
