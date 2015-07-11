package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by kaano8 on 29/6/15.
 */
public class DownloadTTAdapter extends RecyclerView.Adapter<DownloadTTAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    List<String> data = Collections.emptyList();
    private Context context;
    float density;

    public DownloadTTAdapter(Context context, List<String> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context=context;
        density = context.getResources().getDisplayMetrics().density;
    }

    @Override
    public DownloadTTAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.select_year_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DownloadTTAdapter.MyViewHolder holder, final int position) {

        final String current = data.get(position);
        holder.title.setText(current);
        if(current.length()<=3) {
            holder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, (120/density));
            holder.title.setPadding(getPx(50),getPx(50),getPx(50),getPx(50));

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public int getPx(int dimensionDp) {
        return (int) (dimensionDp * density + 0.5f);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            int[] rainbow = context.getResources().getIntArray(R.array.font_colors);

//            Random rand = new Random();
//            int  n = rand.nextInt(10);


            title = (TextView) itemView.findViewById(R.id.title);
//            itemView.setBackgroundColor(rainbow[n]);
        }

        @Override
        public void onClick(View v) {

        }
    }
}