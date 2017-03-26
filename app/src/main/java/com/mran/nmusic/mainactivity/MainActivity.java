package com.mran.nmusic.mainactivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mran.nmusic.BaseActivity;
import com.mran.nmusic.BaseApplication;
import com.mran.nmusic.BuildConfig;
import com.mran.nmusic.Constant;
import com.mran.nmusic.R;
import com.mran.nmusic.fragmentutil.FragmentControl;
import com.mran.nmusic.main.view.MainFragment;
import com.mran.nmusic.mainactivity.presenter.MainActivityPresenterCompl;
import com.mran.nmusic.mainactivity.view.IMainActivity;
import com.mran.nmusic.musicplaying.view.MusicPlayingFragment;
import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;
import com.mran.nmusic.netease.musiclistdetail.adapter.MusiclistDetailAdapter;
import com.mran.nmusic.service.PlayService;

import java.util.List;

public class MainActivity extends BaseActivity implements IMainActivity, View.OnClickListener, View.OnTouchListener, MusiclistDetailAdapter.OnRecyclerItemClickedListener {
    MainActivityPresenterCompl mainActivityPresenterCompl;
    private ImageButton bottomMusicControlPlay;
    private boolean isplaying = false;

    private MusicPlayingFragment musicPlayingFragment;
    private FragmentManager fragmentManager;
    private FragmentControl fragmentControl;
    private ImageView bottomMusicControlCoverImage;
    private TextView bottomMusicControlSinger;
    private TextView bottomMusicControlSongName;
    private ImageButton bottomMusicControlPre;
    private ImageButton bottomMusicControlNext;
    private LinearLayout musicControlLinearLayout;
    private RecyclerView bottomsheetPlaylistRecycleview;
    private BottomSheetBehavior bottomSheetBehavior;
    private PlayService.MusicControlBinder musicControlBinder;
    private MusicControlLocalReciver musicControlLocalReciver;
    private LocalBroadcastManager localBroadcastManager;
    private ServiceConnection playServiceConnection;
    private MusiclistDetailAdapter musiclistDetailAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        bindView();
        initView();
        fragmentManager = getSupportFragmentManager();
        fragmentControl = new FragmentControl(fragmentManager);
        if (savedInstanceState == null) {
            fragmentControl.selectFragment("mainfragment");
        } else {
            fragmentControl.restoreFragmentState(savedInstanceState);
        }
        initcontrol();
        startMyService();
    }

    void bindView() {
        MainFragment mainFragment = new MainFragment();
        bottomMusicControlCoverImage = (ImageView) findViewById(R.id.music_control_cover);
        bottomMusicControlPre = (ImageButton) findViewById(R.id.music_control__pre);
        bottomMusicControlPlay = (ImageButton) findViewById(R.id.music_control__play);
        bottomMusicControlNext = (ImageButton) findViewById(R.id.music_control__next);
        bottomMusicControlSinger = (TextView) findViewById(R.id.music_control__singer);
        bottomMusicControlSongName = (TextView) findViewById(R.id.music_control__song);
        musicControlLinearLayout = (LinearLayout) findViewById(R.id.main_fragment_music_control);
        bottomsheetPlaylistRecycleview = (RecyclerView) findViewById(R.id.main_fragment_bottomsheet_playlist);
    }

    void initView() {
        bottomMusicControlCoverImage.setOnClickListener(this);
        bottomMusicControlPre.setOnClickListener(this);
        bottomMusicControlPlay.setOnClickListener(this);
        bottomMusicControlNext.setOnClickListener(this);
        musicControlLinearLayout.setOnTouchListener(this);
        musicPlayingFragment = MusicPlayingFragment.newInstance(isplaying, "", "", "没有歌曲", "");
        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheetPlaylistRecycleview);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        musiclistDetailAdapter = new MusiclistDetailAdapter(this);
        musiclistDetailAdapter.setOnRecyclerItemClickListener(this);
        bottomsheetPlaylistRecycleview.setAdapter(musiclistDetailAdapter);
        bottomsheetPlaylistRecycleview.setLayoutManager(new LinearLayoutManager(this));
    }

    void initcontrol() {

        playServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                musicControlBinder = (PlayService.MusicControlBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        mainActivityPresenterCompl = new MainActivityPresenterCompl(this, this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.MUSIC_CONTORL_PLAY);
        intentFilter.addAction(Constant.MUSIC_CONTORL_STOP);
        intentFilter.addAction(Constant.MUSIC_CONTORL_PRE);
        intentFilter.addAction(Constant.MUSIC_CONTORL_NEXT);
        musicControlLocalReciver = new MusicControlLocalReciver();
        localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getContext());
        localBroadcastManager.registerReceiver(musicControlLocalReciver, intentFilter);

    }

    void startMyService() {
        Intent musicPlay = new Intent(BaseApplication.getContext(), PlayService.class);
        bindService(musicPlay, playServiceConnection, BIND_AUTO_CREATE);
        startService(musicPlay);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.music_control__pre:
                musicControlBinder.stop();

                mainActivityPresenterCompl.pre(true);
                break;
            case R.id.music_control__play:
                if (mainActivityPresenterCompl.getListsize() == 0) {
                    Toast.makeText(this, "当前播放列表没有歌曲", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!isplaying) {
                    bottomMusicControlPlay.setImageResource(R.drawable.ic_pause);
                    musicControlBinder.showPlayOrPause(true);

                    isplaying = true;

                    musicControlBinder.play();
                } else {
                    bottomMusicControlPlay.setImageResource(R.drawable.ic_play);
                    musicControlBinder.showPlayOrPause(false);

                    isplaying = false;
                    musicControlBinder.pause();
                }

                break;
            case R.id.music_control__next:
                musicControlBinder.stop();

                mainActivityPresenterCompl.next(true);
                break;
            case R.id.music_control_cover:
                MusicListDetailBean musicListDetailBean = mainActivityPresenterCompl.getCurrentMusicDetailBean();
                if (musicListDetailBean != null) {
                    musicPlayingFragment = MusicPlayingFragment.newInstance(isplaying, musicListDetailBean.getImageUrl(), musicListDetailBean.getId(), musicListDetailBean.getSongName(), musicListDetailBean.getArtits());
                }

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack("mainfragment")
                        .add(R.id.main_fragment_holder, musicPlayingFragment)
                        .commit();
                hidemusicControl(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    public void showPlayingList() {
        if (mainActivityPresenterCompl.getPlayingLists().size()==0)
        {
            Toast.makeText(this, "当前播放列表没有歌曲", Toast.LENGTH_SHORT).show();
            return;
        }
        musiclistDetailAdapter.clearALlData();
        musiclistDetailAdapter.adddata(mainActivityPresenterCompl.getPlayingLists());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
public void setPlayMode(int mode)
{
    mainActivityPresenterCompl.setPlayMode(mode);
}
    public void add(MusicListDetailBean musicListDetailBean) {
        musicControlBinder.stop();
        mainActivityPresenterCompl.add(musicListDetailBean);

    }

    public void playall(List<MusicListDetailBean> musicListDetailBeans) {
        mainActivityPresenterCompl.playAll(musicListDetailBeans);
    }

    public void playSelectInList(int index) {
        mainActivityPresenterCompl.play(index);
    }

    public void pre() {
        mainActivityPresenterCompl.pre(true);
    }

    public void next() {
        mainActivityPresenterCompl.next(true);
    }


    public void play() {
        if (mainActivityPresenterCompl.getListsize() == 0) {
            Toast.makeText(this, "当前播放列表没有歌曲", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isplaying) {
            bottomMusicControlPlay.setImageResource(R.drawable.ic_pause);
            if (musicPlayingFragment.isAdded()) {
                musicPlayingFragment.setMusicPlayButton(!isplaying);
            }
            musicControlBinder.showPlayOrPause(!isplaying);

            isplaying = true;

            musicControlBinder.play();
        } else {
            bottomMusicControlPlay.setImageResource(R.drawable.ic_play);
            if (musicPlayingFragment.isAdded()) {
                musicPlayingFragment.setMusicPlayButton(!isplaying);
            }
            musicControlBinder.showPlayOrPause(!isplaying);

            isplaying = false;
            musicControlBinder.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(musicControlLocalReciver);
        musicControlLocalReciver = null;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        bottomMusicControlPlay.setImageResource(R.drawable.ic_play);
        musicControlBinder.showPlayOrPause(false);

        isplaying = false;
        if (musicPlayingFragment.isAdded()) {
            musicPlayingFragment.setMusicPlayButton(isplaying);
        }
    }

    @Override
    public void showMusicControl(MusicListDetailBean musicListDetailBean) {
        bottomMusicControlPlay.setImageResource(R.drawable.ic_pause);
        musicControlBinder.showPlayOrPause(true);
        musiclistDetailAdapter.notifyDataSetChanged();
        isplaying = true;
        bottomMusicControlSinger.setText(musicListDetailBean.getArtits());
        bottomMusicControlSongName.setText(musicListDetailBean.getSongName());
        Glide.with(BaseApplication.getContext())
                .load(musicListDetailBean.getImageUrl())
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.ic_32_multimeda_music_note)
                .error(R.drawable.ic_32_multimeda_music_note)
                .crossFade()
                .fitCenter()
                .into(bottomMusicControlCoverImage);
        musicControlBinder.newSongInfo(musicListDetailBean.getImageUrl(), musicListDetailBean.getSongName(), musicListDetailBean.getArtits());
        if (musicPlayingFragment.isAdded()) {
            musicPlayingFragment.setView(isplaying, musicListDetailBean.getImageUrl(), musicListDetailBean.getId(), musicListDetailBean.getSongName(), musicListDetailBean.getArtits());
        }
    }

    @Override
    public void play(String songUrl) {
        if (BuildConfig.DEBUG)
            Log.d("MainActivity", songUrl);
        musicControlBinder.newSong(songUrl);

    }


    public void hidemusicControl(int visibility) {
        musicControlLinearLayout.setVisibility(visibility);
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /*阻止活动被销毁*/
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            } else if (fragmentManager.getBackStackEntryCount() != 0) {
                hidemusicControl(View.VISIBLE);
                fragmentManager.popBackStack();
            } else
                moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onItemClick(View view, int position, MusicListDetailBean musicListDetailBean) {
        playSelectInList(position);
    }

    public class MusicControlLocalReciver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.MUSIC_CONTORL_PLAY:
                    play();
                    break;
                case Constant.MUSIC_CONTORL_PRE:

                    break;
                case Constant.MUSIC_CONTORL_NEXT:
                    if (BuildConfig.DEBUG)
                        Log.d("MusicControlLocalRecive", "MUSIC_CONTORL_NEXT");
                    int statusCode = intent.getIntExtra("statusCode", 0);
                    if (statusCode == 0)
                        mainActivityPresenterCompl.next(false);
                    else {
                        showError("缓冲失败");
                        isplaying = false;
                    }

                    break;
                case Constant.MUSIC_CONTORL_STOP:
                    //                    bottomMusicControlPlay.setImageResource(R.drawable.ic_play);
                    //                    musicControlBinder.pause();
                    //                    isplaying = false;
                    play();
                    break;
            }


        }
    }
}
