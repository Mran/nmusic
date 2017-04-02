package com.mran.nmusic.netease.musiclistdetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mran.nmusic.BaseApplication;
import com.mran.nmusic.BaseFragment;
import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.service.MusicPlayer;
import com.mran.nmusic.R;
import com.mran.nmusic.adapter.MusiclistDetailAdapter;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.mainactivity.MainActivity;
import com.mran.nmusic.netease.musiclistdetail.presenter.NeteaseMusiclistDetailPresenterCompl;
import com.mran.nmusic.netease.search.view.MusicSearchRecycleviewItemDecoration;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * Created by 张孟尧 on 2016/10/4.
 */

public class NeteaseMusicListDetailFragment extends BaseFragment implements INeteaseMusicListDetailFragment, MusiclistDetailAdapter.OnRecyclerItemClickedListener, View.OnClickListener {

    private MainActivity mainActivity;
    private View view;
    private Toolbar toolbar;
    private RecyclerView listRecyclerView;
    private ImageView listCoverImageView;
    private ImageView listCoverBackRoundImageView;
    private Button playallButton;
    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private MusiclistDetailAdapter musiclistDetailAdapter;
    private NeteaseMusiclistDetailPresenterCompl neteaseMusiclistDetailPresenterCompl;
    private String listcoverurl;
    private String listid;
    private String listTitle;

    public static NeteaseMusicListDetailFragment newInstance(String listcoverurl, String listid, String listTitle) {
        NeteaseMusicListDetailFragment neteaseMusicListDetailFragment = new NeteaseMusicListDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("listcoverurl", listcoverurl);
        bundle.putString("listid", listid);
        bundle.putString("listTitle", listTitle);
        neteaseMusicListDetailFragment.setArguments(bundle);
        return neteaseMusicListDetailFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.musiclist_detail_fragment, container, false);
        bindView();
        initView();

        mainActivity = (MainActivity) getActivity();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        neteaseMusiclistDetailPresenterCompl = new NeteaseMusiclistDetailPresenterCompl(BaseApplication.getContext(), this);
        listcoverurl = getArguments().getString("listcoverurl");
        listid = getArguments().getString("listid");
        listTitle = getArguments().getString("listTitle");
    }

    void bindView() {
        toolbar = (Toolbar) view.findViewById(R.id.musiclist_detail_fragment_toolbar);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.musiclist_detail_fragment_coordinator);
        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.musiclist_detail_fragment_collapsingtoolbar);
        listRecyclerView = (RecyclerView) view.findViewById(R.id.musiclist_detail_fragment_recycleview);
        listCoverImageView = (ImageView) view.findViewById(R.id.musiclist_detail_fragment_music_cover);
        listCoverBackRoundImageView = (ImageView) view.findViewById(R.id.musiclist_detail_fragment_music_cover_backround);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.musiclist_detail_fragment_appbar);
        playallButton = (Button) view.findViewById(R.id.musiclist_detail_fragment_playall_button);
    }

    void initView() {
        toolbar.setNavigationIcon(R.drawable.back_material);
        toolbar.setTitleTextColor(ContextCompat.getColor(BaseApplication.getContext(), R.color.main_tab_color));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentManager.popBackStack();
                fragmentTransaction.commit();
            }
        });
        playallButton.setOnClickListener(this);
        playallButton.setClickable(false);
        collapsingToolbarLayout.setTitleEnabled(false);
        toolbar.setTitle(listTitle);
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(BaseApplication.getContext(), R.color.main_tab_color));
        musiclistDetailAdapter = new MusiclistDetailAdapter(BaseApplication.getContext());
        listRecyclerView.setLayoutManager(new LinearLayoutManager(BaseApplication.getContext()));
        listRecyclerView.addItemDecoration(new MusicSearchRecycleviewItemDecoration(BaseApplication.getContext(), 0.1));

        listRecyclerView.setAdapter(musiclistDetailAdapter);

        listRecyclerView.setNestedScrollingEnabled(false);
        neteaseMusiclistDetailPresenterCompl.getMusicListDetail(listid);
        musiclistDetailAdapter.setOnRecyclerItemClickListener(this);
        Glide.with(BaseApplication.getContext()).load(listcoverurl)
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(listCoverImageView);
        Glide.with(BaseApplication.getContext())
                .load(listcoverurl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .bitmapTransform(new BlurTransformation(BaseApplication.getContext()))
                .into(listCoverBackRoundImageView);

    }

    @Override
    public void showResult(List<MusicListDetailBean> listDetailBeanList) {
        musiclistDetailAdapter.adddata(listDetailBeanList);
        playallButton.setClickable(true);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(BaseApplication.getContext(), error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.musiclist_detail_fragment_playall_button:
                MusicPlayer.addMusicList(neteaseMusiclistDetailPresenterCompl.getMlistDetailBeen());
                break;
            default:
                break;


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (BuildConfig.DEBUG)
            Log.d("NeteaseMusicListDetailF", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG)
            Log.d("NeteaseMusicListDetailF", "onDestroy");
    }

    @Override
    public void onItemClick(View view, int position, MusicListDetailBean musicListDetailBean) {
        MusicPlayer.addMusic(musicListDetailBean);
    }
}
