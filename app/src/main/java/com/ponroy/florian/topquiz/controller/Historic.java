package com.ponroy.florian.topquiz.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ponroy.florian.topquiz.R;
import com.ponroy.florian.topquiz.model.UserArrayAdaptor;
import com.ponroy.florian.topquiz.model.User;

import java.util.List;

public class Historic extends AppCompatActivity {

    private List<User> mBest;
    private UserArrayAdaptor adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        mBest = getIntent().getParcelableExtra("HISTORIC");

        adapter = new UserArrayAdaptor(this, mBest);
        list = (ListView) findViewById(R.id.liste);
        list.setAdapter(adapter);
    }
}
