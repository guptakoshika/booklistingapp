package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.booklistingapp.books;
import com.example.android.booklistingapp.list;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<books>> {

    private list mAdapter;
    ImageView search;
    EditText userInput;
    ListView booksListView;
    TextView nothing;
    static final String RESULTS = "booksSearchResults";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nothing = findViewById(R.id.noAnswer);
        userInput = findViewById(R.id.editText);
        booksListView = findViewById(R.id.bookList);
        search = findViewById(R.id.search);
        nothing.setVisibility(View.GONE);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (networkInfo != null && networkInfo.isConnected()) {
                    LoaderManager loaderManager = getLoaderManager();
                    loaderManager.initLoader(1, null, MainActivity.this);
                    if (loaderManager != null) {
                        loaderManager.restartLoader(1, null, MainActivity.this);
                        nothing.setVisibility(View.GONE);
                    }
                }
            }
        });

        mAdapter = new list(this, new ArrayList<books>());
        booksListView.setAdapter(mAdapter);
        if (savedInstanceState != null) {
            books[] books = (books[]) savedInstanceState.getParcelableArray(RESULTS);
            mAdapter.addAll(books);
        }
    }
    private String getHttpUrl() {
        final String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=search+";
        String formatUserInput = userInput.getText().toString().trim().replaceAll("\\s+", "+");
        String url = baseUrl + formatUserInput;
        return url;
    }


    @Override
    public Loader<List<books>> onCreateLoader(int i, Bundle bundle) {
        return new loader(this, getHttpUrl());
    }

    @Override
    public void onLoadFinished(Loader<List<books>> loader, List<books> books) {
        mAdapter.clear();
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        } else {
            nothing.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<books>> loader) {
        mAdapter.clear();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        books[] books = new books[mAdapter.getCount()];
        for (int i = 0; i < books.length; i++) {
            books[i] = mAdapter.getItem(i);
        }
        outState.putParcelableArray(RESULTS, books);
    }
}