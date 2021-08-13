package com.example.appmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.ui.ItemClick;
import com.example.appmusic.model.Song;
import com.example.appmusic.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    ArrayList<Song> mSongArrayList;
    Context context;
    private ItemClick itemClick;

    public SongAdapter(ArrayList<Song> songArrayList , ItemClick itemClick) {
        this.mSongArrayList = songArrayList;
        this.itemClick = itemClick;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.song_iteam,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder( SongAdapter.ViewHolder holder, int position) {
        holder.txtId.setText(mSongArrayList.get(position).getmId());
        holder.txtName.setText(mSongArrayList.get(position).getmName());
        holder.txtTime.setText(mSongArrayList.get(position).getmTime());
        TextView sName = holder.txtName;
        TextView sTime = holder.txtTime;
        holder.SetOnClickListener(new ItemClick(){

            @Override
            public void onItemClick(View view, int vitri) {

            }
        });



    }

    public String martchTime(long giay){
        String time =String.format(String.valueOf(TimeUnit.MILLISECONDS.toMinutes(giay)),TimeUnit.MILLISECONDS.toSeconds(giay)
         - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(giay)));
        return  time;
    }


    @Override
    public int getItemCount() {
        return mSongArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtId;
        TextView txtName;
        TextView txtTime;
        private Song song;
        ItemClick itemClick = null;


        public ViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.text_id);
            txtName = itemView.findViewById(R.id.text_name);
            txtTime = itemView.findViewById(R.id.text_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.onItemClick(itemView,getAdapterPosition());
                }
            });
        }
        public final void SetOnClickListener(ItemClick clickitem){
            this.itemClick = clickitem;
        }


        @Override
        public void onClick(View v) {
            itemClick.onItemClick(v,getAdapterPosition());
        }
    }
}
