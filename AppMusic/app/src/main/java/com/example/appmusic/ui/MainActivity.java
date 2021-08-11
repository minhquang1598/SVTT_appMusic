package com.example.appmusic.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.appmusic.adapter.SongAdapter;
import com.example.appmusic.database.DataSongs;
import com.example.appmusic.model.Song;
import com.example.appmusic.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  ItemClick{

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    RecyclerView recyclerView;
    ArrayList<Song> songArrayList;
    SongAdapter songAdapter;
    private final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  ActionBar actionBar = getActionBar();

        if(ContextCompat.checkSelfPermission(getApplicationContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE );
        }else{
            LoadData();
        }


        init();
    }
    private void LoadData(){
        DataSongs dataSongs = null;
        Cursor songCursor =getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null,null,null,null);
        if(songCursor != null){
            songCursor.moveToFirst();
            do{
                String songName =songCursor.getString(songCursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                String songDuration =songCursor.getString(songCursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                String songPath =songCursor.getString(songCursor.getColumnIndex(MediaStore.Video.Media.DATA));
                dataSongs.addSong(new Song(songName,songDuration,songPath));


            }while (songCursor.moveToNext());
        }
        songArrayList = (ArrayList<Song>) dataSongs.getAllSongs();
        songAdapter = new SongAdapter(songArrayList, (ItemClick) getApplicationContext());
        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(songAdapter);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults == null && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"Cho phep truy cap",Toast.LENGTH_LONG).show();
                LoadData();
            }
        }
    }

    private void init(){
//        toolbar = findViewById(R.id.mToolbar);
        viewPager = findViewById(R.id.frameLayout);
        tabLayout = findViewById(R.id.mTablayout);
        recyclerView = findViewById(R.id.recycler_View);
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
    public void onItemClick(View view, int vitri) {

    }
}