package com.ponroy.florian.topquiz.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by franck on 14/01/2018.
 */

public class ArrayAdaptor extends ArrayAdapter<User> {



    public ArrayAdaptor(Context context, List<User> user) {
        super(context, android.R.layout.simple_list_item_1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.add();
        return super.getView(position, convertView, parent);
    }
}
