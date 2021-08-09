package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.ItemClick;
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    ArrayList<Song> songArrayList;
    Context context;
    private ItemClick itemClick;

    public SongAdapter(ArrayList<Song> songArrayList , ItemClick itemClick) {
        this.songArrayList = songArrayList;
        this.itemClick = itemClick;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.song_iteam,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder( SongAdapter.ViewHolder holder, int position) {
        holder.txtId.setText(songArrayList.get(position).getmId());
        holder.txtName.setText(songArrayList.get(position).getmName());
        holder.txtTime.setText(songArrayList.get(position).getmTime());


    }

    public String martchTime(long giay){
        String time =String.format(String.valueOf(TimeUnit.MILLISECONDS.toMinutes(giay)),TimeUnit.MILLISECONDS.toSeconds(giay)
         - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(giay)));
        return  time;
    }


    @Override
    public int getItemCount() {
        return songArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtId;
        TextView txtName;
        TextView txtTime;
        private Song song;

        public ViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.text_id);
            txtName = itemView.findViewById(R.id.text_name);
            txtTime = itemView.findViewById(R.id.text_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.oniTemClick(itemView,getAdapterPosition());
                }
            });
        }


        @Override
        public void onClick(View v) {
            itemClick.oniTemClick(v,getAdapterPosition());
        }
    }
}
