package com.mran.nmusic.netease.musiclistdetail.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mran.nmusic.R;
import com.mran.nmusic.net.cloudmusic.bean.MusicListBean;
import com.mran.nmusic.net.cloudmusic.bean.MusicListDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/6.
 */

public class MusiclistDetailAdapter extends RecyclerView.Adapter<MusiclistDetailAdapter.MusicListDetailItemHolder> {
    private Context context;
    private List<MusicListDetailBean> musicListDetailBeen = new ArrayList<>();
    private OnRecyclerItemClickedListener listener;

    {}

    public MusiclistDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MusicListDetailItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.musiclist_detail_item, parent, false);
        return new MusicListDetailItemHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicListDetailItemHolder holder, final int position) {
        if (musicListDetailBeen.get(position).isPlaying())
        {
            holder.songname.setTextColor(ContextCompat.getColor(context,R.color.playing));
            holder.singer.setTextColor(ContextCompat.getColor(context,R.color.playing));
            holder.number.setTextColor(ContextCompat.getColor(context,R.color.playing));
        }
        else {
            holder.songname.setTextColor(ContextCompat.getColor(context,R.color.black));
            holder.singer.setTextColor(ContextCompat.getColor(context,R.color.black));
            holder.number.setTextColor(ContextCompat.getColor(context,R.color.black));

        }
        holder.songname.setText(musicListDetailBeen.get(position).getSongName());
        holder.number.setText(String.valueOf(position));
        holder.singer.setText(musicListDetailBeen.get(position).getArtits());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position,musicListDetailBeen.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicListDetailBeen.size();
    }
public void clearALlData()
{
    this.musicListDetailBeen.clear();}
    public void adddata(List<MusicListDetailBean> musicListDetailBeen) {

        this.musicListDetailBeen.addAll(musicListDetailBeen);

        notifyDataSetChanged();

    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickedListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }

    public interface OnRecyclerItemClickedListener {
        void onItemClick(View view, int position, MusicListDetailBean musicListDetailBean);

    }

    class MusicListDetailItemHolder extends RecyclerView.ViewHolder {
        private TextView number;
        private TextView singer;
        private TextView songname;
        private View view;

        public MusicListDetailItemHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.musiclist_item_number);
            singer = (TextView) itemView.findViewById(R.id.musiclist_item_singer);
            songname = (TextView) itemView.findViewById(R.id.musiclist_item_songname);
            view = itemView;

        }
    }
}
