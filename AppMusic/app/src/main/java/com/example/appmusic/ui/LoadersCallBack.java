package com.example.appmusic.ui;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;

public interface LoadersCallBack extends LoaderManager.LoaderCallbacks<Cursor>{


    @Override
    Loader<Cursor> onCreateLoader(int id, Bundle args);


    @Override
    void onLoadFinished(Loader<Cursor> loader, Cursor data);

    @Override
    void onLoaderReset(Loader<Cursor> loader);
}
