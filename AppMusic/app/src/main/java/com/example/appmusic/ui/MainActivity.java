package com.example.appmusic.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity implements  ItemClick, LoaderManager.LoaderCallbacks<Cursor> {

    Toolbar mToolBar;
    ViewPager mViewPager;
    TabLayout mTabLayout;
    RecyclerView mRecyclerView;
    ArrayList<Song> mSongArrayList;
    SongAdapter mSongAdapter;
    private final int PERMISSION_REQUEST_CODE = 1;
    LoadersCallBack loadersCallBack;

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

        }


        init();
    }
    static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DATA
    };

    String curFilter;
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // This is called when a new Loader needs to be created.  This
        // sample only has one Loader, so we don't care about the ID.
        // First, pick the base URI to use depending on whether we are
        // currently filtering.
        getLoaderManager().initLoader(0,null,loadersCallBack);
        Uri baseUri;
        if (curFilter != null) {
            baseUri = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    Uri.encode(curFilter));
        } else {
            baseUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }

        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        String select = "((" + MediaStore.Video.Media.DISPLAY_NAME + " NOTNULL) AND ("
                + MediaStore.Video.Media.DURATION + "=1) AND ("
                + MediaStore.Video.Media.DATA + " != '' ))";
        return new CursorLoader(getApplicationContext(),baseUri,CONTACTS_SUMMARY_PROJECTION,
                select,null, MediaStore.Video.Media.DISPLAY_NAME);
    }

    @Override
    public void onLoadFinished( Loader<Cursor> loader, Cursor data) {


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE){
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
        }
    }

    private void init(){
//        toolbar = findViewById(R.id.mToolbar);
        mViewPager = findViewById(R.id.frameLayout);
        mTabLayout = findViewById(R.id.mTablayout);
        mRecyclerView = findViewById(R.id.recycler_View);
        mSongAdapter = new SongAdapter(mSongArrayList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mSongAdapter);

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