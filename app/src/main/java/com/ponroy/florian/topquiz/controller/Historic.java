package com.ponroy.florian.topquiz.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ponroy.florian.topquiz.R;
import com.ponroy.florian.topquiz.model.User;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Historic extends AppCompatActivity {

    private ArrayList<User> mBest;
    private ArrayAdapter<User> adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        mBest = getIntent().getParcelableArrayListExtra("HISTORIC");

        adapter = new ArrayAdapter<User>(Historic.this, android.R.layout.simple_expandable_list_item_1, mBest);
        list = (ListView) findViewById(R.id.liste);
        list.setAdapter(adapter);
    }
}
