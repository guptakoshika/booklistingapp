package com.example.android.booklistingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.booklistingapp.MainActivity;
import com.example.android.booklistingapp.books;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Koshika Gupta on 17-03-2018.
 */

public class list extends ArrayAdapter<books> {

    public list(Activity context, List<books> li) {
        super(context, 0, li);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }
        books currentWord = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        titleTextView.setText(currentWord.getTitle());

        TextView auhtorTextView = (TextView) listItemView.findViewById(R.id.author);
        auhtorTextView.setText(currentWord.getAuthor());

        return listItemView;
    }
}
