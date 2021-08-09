package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.appmusic.Adapter.MainViewPagerAdapter;
import com.example.appmusic.Adapter.SongAdapter;
import com.example.appmusic.Fragment.AllSongsFragment;
import com.example.appmusic.Fragment.MediaPlaybackFragment;
import com.example.appmusic.Model.Song;
import com.example.appmusic.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  ItemClick{

    Toolbar toolbar;
    FrameLayout viewPager;
    TabLayout tabLayout;
    RecyclerView recyclerView;
    ArrayList<Song> songArrayList;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  ActionBar actionBar = getActionBar();


        init();
    }

    private void init(){
//        toolbar = findViewById(R.id.mToolbar);
        viewPager = findViewById(R.id.frameLayout);
        tabLayout = findViewById(R.id.mTablayout);
        recyclerView =findViewById(R.id.recycler_View);
        songAdapter = new SongAdapter(songArrayList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songAdapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void oniTemClick(View view, int vitri) {

    }
}