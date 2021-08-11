package com.example.appmusic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appmusic.model.Song;

import java.util.ArrayList;
import java.util.List;

public class DataSongs extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "listsongs";
    private static final String TABLE_NAME = "songs";
    private static  final String NAME = "name";
    private static  final String TIME = "time";
    private static final String PATH = "path";
    private static final String ID = "id";
    private static int VERSION = 1;

    private String creater = "CREATE TABLE " +TABLE_NAME + " ("
            + ID + " integer primary key, " +
            NAME + " TEXT, " +
            TIME + " TEXT, " +
            PATH + " TEXT)";

    public DataSongs(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(creater);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addSong(Song song){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,song.getmName());
        values.put(TIME,song.getmTime());
        values.put(PATH,song.getmPath());
        db.insert(TABLE_NAME,null,values);
        db.close();

    }
    public List<Song> getAllSongs(){
        List<Song> songList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                Song song = new Song();
                song.setmId(cursor.getInt(0));
                song.setmName(cursor.getString(1));
                song.setmTime(cursor.getString(2));
                song.setmPath(cursor.getString(3));
                songList.add(song);

            }while (cursor.moveToNext());
        }
        db.close();
      return songList;


    }
}
