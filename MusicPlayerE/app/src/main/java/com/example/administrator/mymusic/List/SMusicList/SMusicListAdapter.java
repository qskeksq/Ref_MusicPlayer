package com.example.administrator.mymusic.List.SMusicList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mymusic.Main.IInteraction;
import com.example.administrator.mymusic.R;
import com.example.administrator.mymusic.domain.music.Music;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 2017-06-28.
 */

public class SMusicListAdapter extends RecyclerView.Adapter<SMusicListAdapter.MusicHolder> {

    IInteraction interaction;

    List<Music> data;
    Context context;

    public SMusicListAdapter(List<Music> data, IInteraction interaction) {
        this.interaction = interaction;
        this.data = data;
    }

    @Override
    public SMusicListAdapter.MusicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_smusic_list_item, parent, false);
        return new MusicHolder(itemView);

    }

    @Override
    public void onBindViewHolder(SMusicListAdapter.MusicHolder holder, int position) {
        Music music = data.get(position);
        Glide.with(context).load(music.albumArtUri).bitmapTransform(new RoundedCornersTransformation(context, 8, 0))
                .placeholder(R.mipmap.android).into(holder.musicItemImg);
        holder.musicItemTitle.setText(music.musicTitle);
        holder.musicItemAlbum.setText(music.albumTitle);
        holder.no.setText(position+1+"");

    }

    @Override
    public int getItemCount() {
        return data.size();

    }

    public class MusicHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView musicItemImg;
        TextView musicItemTitle, musicItemAlbum, no;

        public MusicHolder(View itemView) {
            super(itemView);
            musicItemImg = (ImageView) itemView.findViewById(R.id.sMusicItemImg);
            musicItemTitle = (TextView) itemView.findViewById(R.id.sMusicItemTitle);
            musicItemAlbum = (TextView) itemView.findViewById(R.id.sMusicItemAlbum);
            no = (TextView) itemView.findViewById(R.id.sMusicItemNo);
            setListener();
        }

        public void setListener(){

            itemView.setOnClickListener(this);
        }

        public void onClick(View view){

            interaction.addDetailFragment();
        }
    }

}
