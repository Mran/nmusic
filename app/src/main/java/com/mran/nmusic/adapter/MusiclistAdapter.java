package com.mran.nmusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mran.nmusic.R;
import com.mran.nmusic.bean.MusicListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/6.
 */

public class MusiclistAdapter extends RecyclerView.Adapter<MusiclistAdapter.MusicListItemHolder> {
    private Context context;
    private List<MusicListBean> musicListBeen = new ArrayList<>();
    private OnRecyclerItemClickedListener listener;


    public MusiclistAdapter(Context context) {
        this.context = context;


    }

    @Override
    public MusicListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_musiclist_item, parent, false);
        return new MusicListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(MusiclistAdapter.MusicListItemHolder holder, final int position) {
        holder.musiclistCover.setTransitionName("list_img" + position);
        Glide.with(context)
                .load(musicListBeen.get(position).getCoverImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .fitCenter()
                .into(holder.musiclistCover);
        holder.musiclistTitle.setText(musicListBeen.get(position).getTitle());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position, musicListBeen.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicListBeen.size();
    }

    public void adddata(boolean isloadmore, List<MusicListBean> musicListBeen) {
        if (isloadmore) {
            this.musicListBeen.addAll(musicListBeen);
        } else {
            this.musicListBeen.clear();
            this.musicListBeen.addAll(musicListBeen);
        }
        notifyDataSetChanged();

    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickedListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }

    public interface OnRecyclerItemClickedListener {
        void onItemClick(View view, int position, MusicListBean musicListBean);

    }

    class MusicListItemHolder extends RecyclerView.ViewHolder {
        private ImageView musiclistCover;
        private TextView musiclistTitle;
        private View view;

        public MusicListItemHolder(View itemView) {
            super(itemView);
            musiclistCover = (ImageView) itemView.findViewById(R.id.explore_muisclistcover_item_cover);
            musiclistTitle = (TextView) itemView.findViewById(R.id.explore_muisclistcover_item_title);
            view = itemView;

        }
    }
}
