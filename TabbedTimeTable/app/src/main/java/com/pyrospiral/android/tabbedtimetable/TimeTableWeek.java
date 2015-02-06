package com.pyrospiral.android.tabbedtimetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TimeTableWeek extends Activity {

    TextView subname;
    TextView timing;
    float density;

    TableLayout.LayoutParams trparams;
    LinearLayout.LayoutParams subjectTextParams;
    LinearLayout.LayoutParams timeTextParams;
    TableRow.LayoutParams linearLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_week);

        density = this.getResources().getDisplayMetrics().density;

        //Layout params
        trparams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        subjectTextParams = new LinearLayout.LayoutParams(getPx(90),getPx(40));
        timeTextParams = new LinearLayout.LayoutParams(getPx(90),getPx(40));
        linearLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);


        //Find table in xml
        TableLayout table = (TableLayout) findViewById(R.id.gridtable);


        LinearLayout header = createHeaderRow();
        TableRow headrow = createRow();
        headrow.addView(header);
        //table.addView(headrow);

        for(int i=0;i<7;i++) {

            //Create linear layouts

            LinearLayout layout1 = createLinearLayout();
            fillLayout(layout1);
            LinearLayout layout2 = createLinearLayout();
            fillLayout(layout2);
            LinearLayout layout3 = createLinearLayout();
            fillLayout(layout3);
            LinearLayout layout4 = createLinearLayout();
            fillLayout(layout4);
            LinearLayout layout5 = createLinearLayout();
            fillLayout(layout5);
            LinearLayout layout6 = createLinearLayout();
            fillLayout(layout6);

            //Create row
            TableRow row = createRow();
            //Add to row
            row.addView(layout1);
            row.addView(layout2);
            row.addView(layout3);
            row.addView(layout4);
            row.addView(layout5);
            row.addView(layout6);
            //Add row to table
            table.addView(row);
        }




    }

        private LinearLayout createHeaderRow(){
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setLayoutParams(linearLayoutParams);
            layout.setBackground(getResources().getDrawable(R.drawable.customborder));

            for(int i =0;i<6;i++) {
                TextView text = new TextView(this);
                text.setLayoutParams(subjectTextParams);
                text.setText("Monday");
                text.setGravity(Gravity.CENTER);
                text.setBackgroundColor(getResources().getColor(R.color.accentColor));
                text.setTextColor(getResources().getColor(R.color.primaryColor));
                layout.addView(text);
            }

            return layout;
        }

        private LinearLayout fillLayout(LinearLayout layout){
            //Create Subject text view
            TextView subtext = createSubjectText();
            //Create time text view
            TextView timetext = createTimeText();
            //Add to linear layout
            layout.addView(subtext);
            layout.addView(timetext);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(TimeTableWeek.this,SubjectDetail.class));
                }
            });

            return layout;
        }

        private TextView createSubjectText(){
            TextView text = new TextView(this);
            text.setLayoutParams(subjectTextParams);
            text.setText("dsasdf");
            text.setGravity(Gravity.CENTER);
            text.setBackgroundColor(getResources().getColor(R.color.primaryColor));
            text.setTextColor(getResources().getColor(R.color.accentColor));
            return text;
        }

        private TextView createTimeText(){
            TextView text = new TextView(this);
            text.setLayoutParams(timeTextParams);
            text.setGravity(Gravity.CENTER);
            text.setBackgroundColor(getResources().getColor(R.color.primaryColorLight));
            text.setTextColor(getResources().getColor(R.color.my_color_highlight));
            text.setText("8:00 - 9:00");
            return text;
        }

        private LinearLayout createLinearLayout(){
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(linearLayoutParams);
            layout.setBackground(getResources().getDrawable(R.drawable.customborder));
            return layout;
        }

        private TableRow createRow(){
            TableRow row = new TableRow(this);
            row.setLayoutParams(trparams);
            row.setGravity(Gravity.CENTER_HORIZONTAL);
            return row;
        }

    public int getPx(int dimensionDp) {
        return (int) (dimensionDp * density + 0.5f);
    }

}