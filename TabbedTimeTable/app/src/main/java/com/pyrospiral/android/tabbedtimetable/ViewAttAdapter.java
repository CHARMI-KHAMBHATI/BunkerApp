package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by kaano8 on 24/1/15.
 */
public class ViewAttAdapter extends RecyclerView.Adapter<ViewAttAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Info> data = Collections.emptyList();
    private ClickListener clickListener;
    private Context context;

    public ViewAttAdapter(Context context, List<Info> data) {
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ViewAttAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_att_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewAttAdapter.MyViewHolder holder, final int position) {

        final Info current = data.get(position);
        holder.subName.setText(current.subName);
        holder.teacherName.setText(current.teacherName);
        holder.timings.setText(current.timings);
        holder.subName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubjectDetail.class).putExtra(Intent
                .EXTRA_TEXT, current.subName);
                context.startActivity(intent);
            }
        });
    }

    public void setClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subName, teacherName, timings;


        public MyViewHolder(View itemView) {
            super(itemView);
            subName = (TextView) itemView.findViewById(R.id.subName);
            teacherName = (TextView) itemView.findViewById(R.id.teacherName);
            timings = (TextView) itemView.findViewById(R.id.timings);
        }

        @Override
        public void onClick(View v) {

            if(clickListener!=null)
            {
                clickListener.itemClicked(v,getPosition());
            }
        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }

}
