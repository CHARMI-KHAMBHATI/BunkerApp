package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by kaano8 on 1/2/15.
 */
public class EditAttAdapter extends RecyclerView.Adapter<EditAttAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Info> new_data = Collections.emptyList();
    private Context context;

    public EditAttAdapter(Context context, List<Info> new_data) {
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.new_data = new_data;
    }
    
    @Override
    public EditAttAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.edit_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EditAttAdapter.MyViewHolder holder, int position) {

        final Info current = new_data.get(position);
        holder.subName.setText(current.subName);
        holder.p_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Added One", Toast.LENGTH_SHORT).show();
            }
        });
        holder.m_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Subtracted One", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return new_data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subName;
        ImageButton p_one,m_one;

        public MyViewHolder(View itemView) {
            super(itemView);
            subName = (TextView) itemView.findViewById(R.id.subName);
            p_one = (ImageButton) itemView.findViewById(R.id.plus_one);
            m_one = (ImageButton) itemView.findViewById(R.id.minus_one);
        }
    }
}