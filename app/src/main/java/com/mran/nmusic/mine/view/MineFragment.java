package com.mran.nmusic.mine.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mran.nmusic.BaseFragment;
import com.mran.nmusic.R;

/**
 * Created by 张孟尧 on 2016/10/4.
 */

public class MineFragment extends BaseFragment implements IMineFragment,View.OnClickListener {
    private View view;
    private Button localMusicButton;
    private Button favouriteButton;
    private Button importMusicListButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_fragment, container, false);
        bindView();
        initView();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void bindView() {
        localMusicButton = (Button) view.findViewById(R.id.mine_local_music_button);
        favouriteButton = (Button) view.findViewById(R.id.mine_favourte_button);
        importMusicListButton = (Button) view.findViewById(R.id.mine_my_music_import);
    }

    void initView() {
        localMusicButton.setOnClickListener(this);
        favouriteButton.setOnClickListener(this);
        importMusicListButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.mine_my_music_import:

                break;
            default:
                break;
        }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMineMusicList() {

    }
}
