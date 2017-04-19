package com.mran.nmusic.musiclistdetail.view;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mran.nmusic.BaseApplication;
import com.mran.nmusic.Constant;
import com.mran.nmusic.R;
import com.mran.nmusic.adapter.MusiclistDetailAdapter;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.musiclistdetail.presenter.MusiclistDetailPresenterCompls;
import com.mran.nmusic.netease.search.view.MusicSearchRecycleviewItemDecoration;
import com.mran.nmusic.service.MusicPlayer;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by M on 2017/4/9.
 */

public class MusicListDetailFragment extends Fragment implements IMusicListDetailFragment, MusiclistDetailAdapter.OnRecyclerItemClickedListener, View.OnClickListener, View.OnTouchListener {
    private View view;
    private Toolbar toolbar;
    private RecyclerView listRecyclerView;
    private ImageView listCoverImageView;
    private ImageView listCoverBackRoundImageView;
    private Button playallButton;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private MusiclistDetailAdapter musiclistDetailAdapter;
    private MusiclistDetailPresenterCompls mMusiclistDetailPresenterCompls;

    private String listcoverurl;
    private String listid;
    private String listTitle;
    private int tag;

    public static MusicListDetailFragment newInstance(String listcoverurl, String listid, String listTitle, int tag) {
        MusicListDetailFragment musicListDetailFragment = new MusicListDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("listcoverurl", listcoverurl);
        bundle.putString("listid", listid);
        bundle.putString("listTitle", listTitle);
        bundle.putInt("tag", tag);

        musicListDetailFragment.setArguments(bundle);
        return musicListDetailFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listcoverurl = getArguments().getString("listcoverurl");
        listid = getArguments().getString("listid");
        listTitle = getArguments().getString("listTitle");
        tag = getArguments().getInt("tag");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.musiclist_detail_fragment, container, false);
        mMusiclistDetailPresenterCompls = new MusiclistDetailPresenterCompls(BaseApplication.getContext(), this);
        view.setOnTouchListener(this);
        bindView();
        initView();
        return view;
    }

    public void setParams(String listcoverurl, String listid, String listTitle, int tag) {
        this.listcoverurl = listcoverurl;
        this.listid = listid;
        this.listTitle = listTitle;
        this.tag = tag;

    }

    void bindView() {
        toolbar = (Toolbar) view.findViewById(R.id.musiclist_detail_fragment_toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.musiclist_detail_fragment_collapsingtoolbar);
        listRecyclerView = (RecyclerView) view.findViewById(R.id.musiclist_detail_fragment_recycleview);
        listCoverImageView = (ImageView) view.findViewById(R.id.musiclist_detail_fragment_music_cover);
        listCoverBackRoundImageView = (ImageView) view.findViewById(R.id.musiclist_detail_fragment_music_cover_backround);
        playallButton = (Button) view.findViewById(R.id.musiclist_detail_fragment_playall_button);
    }

    void finishThis() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
    }

    void initView() {
        toolbar.setNavigationIcon(R.drawable.back_material);
        toolbar.setTitleTextColor(ContextCompat.getColor(BaseApplication.getContext(), R.color.main_tab_color));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishThis();
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
        musiclistDetailAdapter.setOnRecyclerItemClickListener(this);
        Glide.with(BaseApplication.getContext()).load(listcoverurl)
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(listCoverImageView);
        Glide.with(BaseApplication.getContext())
                .load(listcoverurl)
                .fitCenter()
//                .listener(new CrossFadeListener())
                .crossFade(300)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .bitmapTransform(new BlurTransformation(BaseApplication.getContext()))
                .into(listCoverBackRoundImageView);
        switch (tag) {
            case Constant.NETEASETAG:
                mMusiclistDetailPresenterCompls.getCloudMusicListDetail(listid);
                break;
            case Constant.QQTAG:
                mMusiclistDetailPresenterCompls.getQQMusicListDetail(listid);
                break;
            case Constant.XIAMITAG:
                mMusiclistDetailPresenterCompls.getXiaMiMusicListDetail(listid);
                break;
            default:
                break;

        }

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
                MusicPlayer.addMusicList(mMusiclistDetailPresenterCompls.getMlistDetailBeen());
                break;
            default:
                break;


        }
    }

    @Override
    public void onItemClick(View view, int position, MusicListDetailBean musicListDetailBean) {
        MusicPlayer.addMusic(musicListDetailBean);
    }

    private int mXPosition;
    private int offsetx;


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mXPosition = (int) motionEvent.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                offsetx = (int) (motionEvent.getX() - mXPosition);
                if (view.getX() + offsetx >= 0)
                    view.offsetLeftAndRight(offsetx);
                break;
            case MotionEvent.ACTION_UP:
                ObjectAnimator animator = new ObjectAnimator();
                animator.setTarget(view);
                if (view.getX() < view.getWidth() / 5) {

//                    view.startAnimation(new TranslateAnimation(0,200, 0, 0));
//                    view.offsetLeftAndRight((int) -view.getX());
                    view.animate().translationX(-view.getLeft()).start();
//                    view.scrollTo(100,0);
                } else {
                    view.animate().translationX(view.getWidth() - view.getLeft()).start();

//                    view.offsetLeftAndRight((int) (view.getWidth() - view.getX()));
                    finishThis();
                }
                break;
            default:
                break;

        }
        return false;
    }


}
