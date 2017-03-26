package com.mran.nmusic.netease.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mran.nmusic.R;
import com.mran.nmusic.net.cloudmusic.bean.MusicListBean;
import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;
import com.mran.nmusic.netease.musiclist.adapter.MusiclistAdapter;
import com.mran.nmusic.search.adapter.SearchViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/13.
 */

public class MusicSearchAdapter extends RecyclerView.Adapter<MusicSearchAdapter.MusicSearchHolder> {
    List<MusicListDetailBean> listDetailBeen=new ArrayList<>();
    Context context;
    private MusicSearchAdapter.OnRecyclerItemClickedListener listener;



    @Override
    public MusicSearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_search_item, parent, false);
        return new MusicSearchHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicSearchHolder holder, final int position) {
        holder.song.setText(listDetailBeen.get(position).getSongName());
        holder.singer.setText(String.format("%s - %s",listDetailBeen.get(position).getArtits(),listDetailBeen.get(position).getAlbum()));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position,listDetailBeen.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDetailBeen.size();
    }

    public void adddata(boolean isloadmore, List<MusicListDetailBean> musicListBeen) {
        if (isloadmore) {
            this.listDetailBeen.addAll(musicListBeen);
        } else {
            this.listDetailBeen.clear();
            this.listDetailBeen.addAll(musicListBeen);
        }
        notifyDataSetChanged();

    }

    public void setOnRecyclerItemClickListener(MusicSearchAdapter.OnRecyclerItemClickedListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }

    public interface OnRecyclerItemClickedListener {
        void onItemClick(View view, int position, MusicListDetailBean musicListDetailBean);

    }

    class MusicSearchHolder extends RecyclerView.ViewHolder {
        TextView song;
        TextView singer;
        View view;

        public MusicSearchHolder(View itemView) {
            super(itemView);
            singer = (TextView) itemView.findViewById(R.id.music_search_item_singer);
            song = (TextView) itemView.findViewById(R.id.music_search_item_song);
            view = itemView;
        }
    }
}
