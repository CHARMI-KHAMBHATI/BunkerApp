package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by kaano8 on 30/6/15.
 */
public class FavEventAdapter extends RecyclerView.Adapter<FavEventAdapter.MyViewHolder> {




    private LayoutInflater inflater;
    List<EventData> data = Collections.emptyList();
    public Context context;

    public FavEventAdapter(Context context, List<EventData> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context=context;
    }

    @Override
    public FavEventAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fav_event_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FavEventAdapter.MyViewHolder holder, int position) {

        final EventData current= data.get(position);
        holder.logo.setText(current.chapterName.substring(0,1));
        holder.name_event.setText(current.eventName);
        holder.chapter_name.setText(current.chapterName);
        holder.date_event.setText(current.date);
        holder.time_event.setText(current.time);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }






    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView logo;
        public TextView name_event;
        public TextView chapter_name;
        public TextView date_event;
        public TextView time_event;
        public ImageView star;

        public MyViewHolder(View itemView) {
            super(itemView);

            logo = (TextView) itemView.findViewById(R.id.logo);
            name_event = (TextView) itemView.findViewById(R.id.event_name);
            chapter_name = (TextView) itemView.findViewById(R.id.chapter_name);
            date_event = (TextView) itemView.findViewById(R.id.date);
            time_event = (TextView) itemView.findViewById(R.id.time);


            itemView.setOnClickListener(this);
//            star.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            int position = this.getPosition();
            final EventData current= data.get(position);
                Intent intent=new Intent(context, EventDetails.class).putExtra("#123", current.eventName);
                context.startActivity(intent);

            }

        }
    }
