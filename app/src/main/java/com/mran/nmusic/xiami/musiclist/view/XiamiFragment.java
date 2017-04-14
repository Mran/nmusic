package com.mran.nmusic.xiami.musiclist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mran.nmusic.BaseFragment;
import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.Constant;
import com.mran.nmusic.musiclistdetail.view.DetailsTransition;
import com.mran.nmusic.R;
import com.mran.nmusic.adapter.MusiclistAdapter;
import com.mran.nmusic.bean.MusicListBean;
import com.mran.nmusic.musiclistdetail.view.MusicListDetailFragment;
import com.mran.nmusic.netease.musiclist.view.IMusiclistFragment;
import com.mran.nmusic.xiami.musiclist.presenter.XiamiPresenterCompl;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/4.
 */

public class XiamiFragment extends BaseFragment implements IMusiclistFragment, PullLoadMoreRecyclerView.PullLoadMoreListener, MusiclistAdapter.OnRecyclerItemClickedListener {
    private View view;

    private PullLoadMoreRecyclerView recyclerView;
    private MusiclistAdapter musiclistAdapter;
    private XiamiPresenterCompl xiamiPresenterCompl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.explore_viewpager_content_fragment, container, false);
        xiamiPresenterCompl = new XiamiPresenterCompl(getActivity(), this);
        bindView();
        initView();
        if (BuildConfig.DEBUG)
            Log.d("XiamiFragment", "onCreateView");
        return view;
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
        musiclistAdapter.setOnRecyclerItemClickListener(this);

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
        xiamiPresenterCompl.loadMusiclist(false);
    }

    @Override
    public void onLoadMore() {
        xiamiPresenterCompl.loadMusiclist(true);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }



    @Override
    public void onItemClick(View view, int position, MusicListBean musicListBean) {


        MusicListDetailFragment musicListDetailFragment = MusicListDetailFragment.newInstance(musicListBean.getCoverImageUrl(), musicListBean.getListId(), musicListBean.getTitle(), Constant.XIAMITAG);
        FragmentManager fragmentManager = getParentFragment().getParentFragment().getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        musicListDetailFragment.setSharedElementEnterTransition(new DetailsTransition());
        setEnterTransition(new Fade());
        setExitTransition(new Explode());
        musicListDetailFragment.setSharedElementReturnTransition(new DetailsTransition());


        transaction.addToBackStack("mainfragment")
                .setCustomAnimations(R.anim.list_deatil_in,R.anim.list_detail_out,R.anim.list_deatil_in,R.anim.list_detail_out)
                .add(R.id.main_fragment_holder, musicListDetailFragment)
//                .hide(fragmentManager.getFragments().get(0))
                .addSharedElement(view.findViewById(R.id.explore_muisclistcover_item_cover), "list_cover_img")
                .commit();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setRefreshing(true);
        xiamiPresenterCompl.loadMusiclist(false);


    }
}
