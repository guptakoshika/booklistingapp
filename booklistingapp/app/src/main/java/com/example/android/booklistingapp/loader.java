package com.example.android.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.widget.Toast;

import com.example.android.booklistingapp.books;
import com.example.android.booklistingapp.QueryUtlis;

import java.util.List;

import java.util.List;

/**
 * Created by Koshika Gupta on 17-03-2018.
 */

public class loader extends AsyncTaskLoader<List<books>> {

    private String mUrl;

    public loader(Context context, String url) {
        super(context);
        mUrl = url;
        System.out.println(mUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<books> loadInBackground() {
        List<books> Book = QueryUtlis.fetchData(mUrl);
        return Book;
    }
}
