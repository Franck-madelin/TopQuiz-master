package com.ponroy.florian.topquiz.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ponroy.florian.topquiz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franck on 14/01/2018.
 */

public class UserArrayAdaptor extends ArrayAdapter<User> {

    private Context context;
    private List<User> mBest;

    public UserArrayAdaptor(Context context, List<User> users) {
        super(context, android.R.layout.simple_list_item_1);

        this.context = context;
        this.mBest = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.liste_layout, parent, false);

        TextView name = v.findViewById(R.id.name);
        TextView score = v.findViewById(R.id.score);

        name.setText("Name: " + mBest.get(position).getFirstname());
        score.setText("Score: " + mBest.get(position).getmBestScore());
        return v;
    }
}