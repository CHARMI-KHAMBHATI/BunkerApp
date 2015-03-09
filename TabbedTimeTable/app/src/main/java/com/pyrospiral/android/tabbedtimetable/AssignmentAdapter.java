package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Kush on 1/18/2015.
 */
public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<AssignmentData> data = Collections.emptyList();

    public AssignmentAdapter(Context context, List<AssignmentData> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_assignment_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AssignmentAdapter.MyViewHolder holder, int position) {

        AssignmentData current = data.get(position);
        holder.subName.setText(current.subject);
        holder.teacherName.setText(current.assignmentContent);
        holder.timings.setText(current.dueDate);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subName, teacherName, timings;


        public MyViewHolder(View itemView) {
            super(itemView);
            subName = (TextView) itemView.findViewById(R.id.subName);
            teacherName = (TextView) itemView.findViewById(R.id.assignmentContent);
            timings=(TextView)itemView.findViewById(R.id.dueDate);
        }
    }




}
