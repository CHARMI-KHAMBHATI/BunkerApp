package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Kush on 1/9/2015.
 */
public class DrawerAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    List<DrawerData> data = Collections.emptyList();

    public DrawerAdapter(Context context, List<DrawerData> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public DrawerData getItem(int position) {
        DrawerData dataItem = data.get(position);
        return dataItem;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrawerData item = data.get(position);

        View vi = convertView;

        if (convertView == null)
            vi = inflater.inflate(R.layout.drawer_row, null);

        ImageView img = (ImageView) vi.findViewById(R.id.listIcon);
        TextView title = (TextView) vi.findViewById(R.id.listText);

        title.setText(item.title);
        img.setImageResource(item.iconId);

        return vi;
    }




    }





