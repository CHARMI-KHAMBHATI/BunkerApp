package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by kaano8 on 23/1/15.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Info> data = Collections.emptyList();


    public AttendanceAdapter(Context context, List<Info> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AttendanceAdapter.MyViewHolder holder, int position) {

        Info current = data.get(position);
        holder.subName.setText(current.subName);
        holder.teacherName.setText(current.teacherName);
        holder.timings.setText(current.timings);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subName, teacherName, timings;
        ImageView cancel;

        public MyViewHolder(View itemView) {
            super(itemView);
            subName = (TextView) itemView.findViewById(R.id.subName);
            teacherName = (TextView) itemView.findViewById(R.id.teacherName);
            timings = (TextView) itemView.findViewById(R.id.timings);
            cancel = (ImageView) itemView.findViewById(R.id.cancelled);
            cancel.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
          if(v.equals(cancel))
          {
              removeAt(getPosition());
          }
        }
    }

    public void removeAt(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, data.size());
    }

}