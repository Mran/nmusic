package com.mran.nmusic.mainactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
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
import com.mran.nmusic.adapter.MusiclistDetailAdapter;
import com.mran.nmusic.bean.MusicListDetailBean;
import com.mran.nmusic.fragmentutil.FragmentControl;
import com.mran.nmusic.main.view.MainFragment;
import com.mran.nmusic.mainactivity.presenter.MainActivityPresenterCompl;
import com.mran.nmusic.mainactivity.view.IMainActivity;
import com.mran.nmusic.musiclistdetail.view.DetailsTransition;
import com.mran.nmusic.musicplaying.view.MusicPlayingFragment;
import com.mran.nmusic.service.MusicPlayer;

public class MainActivity extends BaseActivity implements IMainActivity, View.OnClickListener, View.OnTouchListener, MusiclistDetailAdapter.OnRecyclerItemClickedListener {
    private MainActivityPresenterCompl mainActivityPresenterCompl;
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
        bottomMusicControlCoverImage.setTransitionName("bottom_music_playing_cover");
        bottomMusicControlPre = (ImageButton) findViewById(R.id.music_control__pre);
        bottomMusicControlPlay = (ImageButton) findViewById(R.id.music_control_play);
        bottomMusicControlNext = (ImageButton) findViewById(R.id.music_control_next);
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

    }

    @Override
    protected void onStart() {
        super.onStart();
        musicPlayingFragment = MusicPlayingFragment.newInstance(isplaying, "", "", "没有歌曲", "");
        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheetPlaylistRecycleview);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        musiclistDetailAdapter = new MusiclistDetailAdapter(this);
        musiclistDetailAdapter.setOnRecyclerItemClickListener(this);
        bottomsheetPlaylistRecycleview.setAdapter(musiclistDetailAdapter);
        bottomsheetPlaylistRecycleview.setLayoutManager(new LinearLayoutManager(this));
    }

    void initcontrol() {

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
        MusicPlayer.bindService(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.music_control__pre:

                MusicPlayer.previours();
                break;
            case R.id.music_control_play:
                if (MusicPlayer.getMusicListSize() == 0) {
                    Toast.makeText(this, "当前播放列表没有歌曲", Toast.LENGTH_SHORT).show();
                    break;
                }
                MusicPlayer.playOrPsuse();
                if (MusicPlayer.isPlaying()) {
                    bottomMusicControlPlay.setImageResource(R.drawable.ic_pause);
                } else {
                    bottomMusicControlPlay.setImageResource(R.drawable.ic_play);
                }
                break;
            case R.id.music_control_next:
                MusicPlayer.next(true);
                break;
            case R.id.music_control_cover:
                MusicListDetailBean musicListDetailBean = MusicPlayer.getMusic();
                if (musicListDetailBean != null) {
                    musicPlayingFragment = MusicPlayingFragment.newInstance(isplaying, musicListDetailBean.getImageUrl(), musicListDetailBean.getId(), musicListDetailBean.getSongName(), musicListDetailBean.getArtits());
                }
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                musicPlayingFragment.setSharedElementEnterTransition(new DetailsTransition());
                musicPlayingFragment.setSharedElementReturnTransition(new DetailsTransition());

                fragmentTransaction.addToBackStack("mainfragment")
                        .setCustomAnimations(R.anim.fra_in, R.anim.fra_out, R.anim.fra_in, R.anim.fra_out)
                        .add(R.id.main_fragment_holder, musicPlayingFragment)
                        .addSharedElement(bottomMusicControlCoverImage,"playing_music_playing_cover")
                        .commit();
                hidemusicControl(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    public void showPlayingList() {
        
        if (MusicPlayer.getMusicListSize() == 0) {
            Toast.makeText(this, "当前播放列表没有歌曲", Toast.LENGTH_SHORT).show();
            return;
        }
        musiclistDetailAdapter.clearALlData();
        musiclistDetailAdapter.adddata(MusicPlayer.getMusicList());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

//    public void add(MusicListDetailBean musicListDetailBean) {
//        musicControlBinder.stop();
//        mainActivityPresenterCompl.add(musicListDetailBean);
//
//    }
//
//    public void playall(List<MusicListDetailBean> musicListDetailBeans) {
//        mainActivityPresenterCompl.playAll(musicListDetailBeans);
//    }
//
//    public void playSelectInList(int index) {
//        mainActivityPresenterCompl.play(index);
//    }
//
//    public void pre() {
//        mainActivityPresenterCompl.pre(true);
//    }
//
//    public void next() {
//        mainActivityPresenterCompl.next(true);
//    }
//
//
//    public void play() {
//        if (mainActivityPresenterCompl.getListsize() == 0) {
//            Toast.makeText(this, "当前播放列表没有歌曲", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (!isplaying) {
//            bottomMusicControlPlay.setImageResource(R.drawable.ic_pause);
//            if (musicPlayingFragment.isAdded()) {
//                musicPlayingFragment.setMusicPlayButton(!isplaying);
//            }
//            musicControlBinder.showPlayOrPause(!isplaying);
//
//            isplaying = true;
//
//            musicControlBinder.play();
//        } else {
//            bottomMusicControlPlay.setImageResource(R.drawable.ic_play);
//            if (musicPlayingFragment.isAdded()) {
//                musicPlayingFragment.setMusicPlayButton(!isplaying);
//            }
//            musicControlBinder.showPlayOrPause(!isplaying);
//
//            isplaying = false;
//            musicControlBinder.pause();
//        }
//    }

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

        isplaying = false;
        if (musicPlayingFragment.isAdded()) {
            musicPlayingFragment.setMusicPlayButton(isplaying);
        }
    }

    @Override
    public void changeMusicUI() {
        showMusicControl(MusicPlayer.getMusic());
    }

    @Override
    public void showMusicControl(MusicListDetailBean musicListDetailBean) {
        bottomMusicControlPlay.setImageResource(R.drawable.ic_pause);
        musiclistDetailAdapter.notifyDataSetChanged();
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
//        musicControlBinder.newSongInfo(musicListDetailBean.getImageUrl(), musicListDetailBean.getSongName(), musicListDetailBean.getArtits());
    }

    @Override
    public void play() {
        bottomMusicControlPlay.setImageResource(R.drawable.ic_pause);

    }

    @Override
    public void stop() {
        bottomMusicControlPlay.setImageResource(R.drawable.ic_play);

    }

    //隐藏底部音乐控制条
    public void hidemusicControl(int visible) {
        if (musicControlLinearLayout.getVisibility() != View.INVISIBLE && visible == View.INVISIBLE) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, musicControlLinearLayout.getHeight());
            translateAnimation.setDuration(100);
            musicControlLinearLayout.setClickable(false);
            translateAnimation.setInterpolator(new AccelerateInterpolator(0.8f));
            musicControlLinearLayout.startAnimation(translateAnimation);
            musicControlLinearLayout.setVisibility(View.INVISIBLE);
        } else if (musicControlLinearLayout.getVisibility() == View.INVISIBLE && visible == View.VISIBLE) {
            musicControlLinearLayout.setClickable(true);
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, musicControlLinearLayout.getHeight(), 0);
            translateAnimation.setDuration(100);
            translateAnimation.setInterpolator(new AccelerateInterpolator(0.8f));
            musicControlLinearLayout.startAnimation(translateAnimation);
            musicControlLinearLayout.setVisibility(View.VISIBLE);
        }
        hideBottomSheet();

    }

    public void hideBottomSheet() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED || bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_SETTLING) {
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
        MusicPlayer.playByIndex(position);
    }

    public class MusicControlLocalReciver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.MUSIC_CONTORL_PLAY:
                    MusicPlayer.playOrPsuse();
                    break;
                case Constant.MUSIC_CONTORL_PRE:
                    MusicPlayer.previours();
                    break;
                case Constant.MUSIC_CONTORL_NEXT:
                    if (BuildConfig.DEBUG)
                        Log.d("MusicControlLocalRecive", "MUSIC_CONTORL_NEXT");
                    int statusCode = intent.getIntExtra("statusCode", 0);
                    if (statusCode == 0)
                        MusicPlayer.next(true);
                    else {
                        showError("缓冲失败");
                        isplaying = false;
                    }
                    break;
                case Constant.MUSIC_CONTORL_STOP:
                    MusicPlayer.playOrPsuse();
                    break;
            }


        }
    }
}
