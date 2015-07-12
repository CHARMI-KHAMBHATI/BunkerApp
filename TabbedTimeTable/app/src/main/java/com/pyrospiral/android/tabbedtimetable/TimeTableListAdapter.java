package com.pyrospiral.android.tabbedtimetable;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kush on 1/15/2015.
 */
public class TimeTableListAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<TimeTableArrayList.TimeTableArrayListItems> items;

    public TimeTableListAdapter(Activity context, ArrayList<TimeTableArrayList.TimeTableArrayListItems> items) {
        super();
        this.items = items;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public TimeTableArrayList.TimeTableArrayListItems getItem(int position) {
        TimeTableArrayList.TimeTableArrayListItems item = items.get(position);
        return item;

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        TimeTableArrayList.TimeTableArrayListItems item = items.get(position);

        View vi = convertView;

        if (convertView == null)
            vi = inflater.inflate(R.layout.timetable_row, null);


        final TextView sub = (TextView) vi.findViewById(R.id.subjecttext);
        final TextView tim = (TextView) vi.findViewById(R.id.timingtext);
        final TextView ass = (TextView) vi.findViewById(R.id.teachertext);
        final TextView circ = (TextView) vi.findViewById(R.id.asscircle);

        sub.setText(item.subject);
        tim.setText(item.timing);
        ass.setText(item.teacher);




        final String subject = item.subject;

        circ.setText(""+subject.charAt(0));

        return vi;
    }
}
