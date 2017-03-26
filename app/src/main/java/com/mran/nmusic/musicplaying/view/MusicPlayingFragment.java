package com.mran.nmusic.musicplaying.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mran.nmusic.BaseApplication;
import com.mran.nmusic.BaseFragment;
import com.mran.nmusic.Constant;
import com.mran.nmusic.R;
import com.mran.nmusic.mainactivity.MainActivity;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by 张孟尧 on 2016/10/14.
 */

public class MusicPlayingFragment extends BaseFragment implements View.OnTouchListener, View.OnClickListener {
    boolean isplaying = false;
    private View view;
    private ImageView musicCoverImage;
    private ImageView backGroundImage;
    private ImageButton musicPreButton;
    private ImageButton musicPlayButton;
    private ImageButton musicNextButton;
    private ImageButton openPlayingListsButton;
    private ImageButton playModeButton;
    private Toolbar toolbar;
    private MainActivity mainActivity;
    private CoordinatorLayout coordinatorLayout;
    private String musicCoverurl;
    private String musicid;
    private String musicTitle;
    private String musicSinger;
    private int playMode = Constant.ALLREPEAT;

    public static MusicPlayingFragment newInstance(boolean isplaying, String listcoverurl, String listid, String listTitle, String singer) {
        MusicPlayingFragment musicPlayingFragment = new MusicPlayingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("musicCoverurl", listcoverurl);
        bundle.putString("musicid", listid);
        bundle.putString("musicTitle", listTitle);
        bundle.putString("musicSinger", singer);
        bundle.putBoolean("isplaying", isplaying);

        musicPlayingFragment.setArguments(bundle);
        return musicPlayingFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.music_playing_fragment, container, false);
        view.setOnTouchListener(this);
        bindview();
        initview();
        mainActivity = (MainActivity) getActivity();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicCoverurl = getArguments().getString("musicCoverurl");
        musicid = getArguments().getString("musicid");
        musicTitle = getArguments().getString("musicTitle");
        musicSinger = getArguments().getString("musicSinger");
        isplaying = getArguments().getBoolean("isplaying");
    }

    private void bindview() {
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.music_playing_coordinator);
        musicCoverImage = (ImageView) view.findViewById(R.id.music_playing_cover);
        backGroundImage = (ImageView) view.findViewById(R.id.music_playing_back);
        toolbar = (Toolbar) view.findViewById(R.id.music_playing_toolbar);

        musicPreButton = (ImageButton) view.findViewById(R.id.music_playing_control_pre);
        musicPlayButton = (ImageButton) view.findViewById(R.id.music_playing_control_play);
        musicNextButton = (ImageButton) view.findViewById(R.id.music_playing_control_next);
        openPlayingListsButton = (ImageButton) view.findViewById(R.id.music_playing_control_playinglist);
        playModeButton = (ImageButton) view.findViewById(R.id.music_playing_control_playmode);
    }

    private void initview() {
        setView(isplaying, musicCoverurl, musicid, musicTitle, musicSinger);
        toolbar.setNavigationIcon(R.drawable.back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                mainActivity.hidemusicControl(View.VISIBLE);
            }
        });
        musicPreButton.setOnClickListener(this);
        musicPlayButton.setOnClickListener(this);
        musicNextButton.setOnClickListener(this);
        openPlayingListsButton.setOnClickListener(this);
        playModeButton.setOnClickListener(this);
    }

    public void setMusicPlayButton(boolean isplaying) {
        if (isplaying) {
            musicPlayButton.setImageResource(R.drawable.ic_pause_b);
        } else {
            musicPlayButton.setImageResource(R.drawable.ic_play_b);
        }
    }

    public void setView(boolean isplaying, String musicCoverurl, String musicId, String musicTitle, String singer) {
        this.musicCoverurl = musicCoverurl;
        this.musicid = musicId;
        this.musicTitle = musicTitle;
        this.musicSinger = singer;

        toolbar.setTitle(musicTitle);
        toolbar.setSubtitle(musicSinger);
        if (isplaying) {
            musicPlayButton.setImageResource(R.drawable.ic_pause_b);
        } else {
            musicPlayButton.setImageResource(R.drawable.ic_play_b);
        }
        setPlayMode();
        Glide.with(BaseApplication.getContext())
                .load(musicCoverurl)
                .priority(Priority.HIGH)
                .crossFade(300)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .placeholder(R.drawable.ic_32_multimeda_music_note)
                .error(R.drawable.ic_32_multimeda_music_note)
                .into(musicCoverImage);
        Glide.with(BaseApplication.getContext())
                .load(musicCoverurl)
                .priority(Priority.LOW)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.music_playing_bg)
                .centerCrop()
                .crossFade(300)
                .bitmapTransform(new BlurTransformation(BaseApplication.getContext(), 50))
                .into(backGroundImage);
    }

    private void setPlayMode() {
        switch (playMode) {
            case Constant.ALLREPEAT:
                playModeButton.setImageResource(R.drawable.ic_repeat);
                break;
            case Constant.ONEREPEAT:
                playModeButton.setImageResource(R.drawable.ic_nr1);
                break;
            case Constant.SHUFFLE:
                playModeButton.setImageResource(R.drawable.ic_shuffle);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.music_playing_control_pre:
                mainActivity.pre();
                break;
            case R.id.music_playing_control_play:
                mainActivity.play();
                if (!isplaying) {
                    musicPlayButton.setImageResource(R.drawable.ic_pause_b);
                    isplaying = true;
                } else {
                    musicPlayButton.setImageResource(R.drawable.ic_play_b);
                    isplaying = false;
                }
                break;
            case R.id.music_playing_control_next:
                mainActivity.next();
                break;
            case R.id.music_playing_control_playmode:
                if (playMode == 5)
                    playMode = Constant.ONEREPEAT;
                else
                    playMode++;
                setPlayMode();
                mainActivity.setPlayMode(playMode);
                break;
            case R.id.music_playing_control_playinglist:
                mainActivity.showPlayingList();
        }
    }
}

