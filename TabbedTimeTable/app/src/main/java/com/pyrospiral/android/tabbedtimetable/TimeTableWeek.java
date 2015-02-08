package com.pyrospiral.android.tabbedtimetable;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

    int i,j;
    String subject_[][]=new String[10][10];
    String timing_[][]=new String[10][10];



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


      //  LinearLayout header = createHeaderRow();
        //TableRow headrow = createRow();
        //headrow.addView(header);
        //table.addView(headrow);

        i=0;j=0;


        final DBAdapterM dbm=new DBAdapterM(TimeTableWeek.this);
        dbm.open();
        Cursor c1=dbm.getAllContacts();
        if(c1.moveToFirst())
        {

            do{

                subject_[i][j]=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));
                int index7=c1.getColumnIndex(DBAdapterM.START_TIME);
                double a=Double.parseDouble(c1.getString(index7));
                int x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                int y=(int)a;
                if(y<10)
                    timing_[i][j]=Integer.toString(x)+":0"+Integer.toString(y);
                else
                timing_[i][j]=Integer.toString(x)+":"+Integer.toString(y);

                int index8=c1.getColumnIndex(DBAdapterM.END_TIME);

                a=Double.parseDouble(c1.getString(index8));
                x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                y=(int)a;
                if(y<10)
                    timing_[i][j]=timing_[i][j]+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timing_[i][j]=timing_[i][j]+"-"+Integer.toString(x)+":"+Integer.toString(y);

                i++;



            }while(c1.moveToNext());

        }

        dbm.close();
        j++;
        i=0;
        //tuesday

        final DBAdapterTu dbtu=new DBAdapterTu(TimeTableWeek.this);
        dbtu.open();
        Cursor c2=dbtu.getAllContacts();
        if(c2.moveToFirst())
        {

            do{

                subject_[i][j]=c2.getString(c2.getColumnIndex(DBAdapterTu.SUBJECT));
                int index7=c2.getColumnIndex(DBAdapterTu.START_TIME);
                double a=Double.parseDouble(c2.getString(index7));
                int x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                int y=(int)a;
                if(y<10)
                    timing_[i][j]=Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timing_[i][j]=Integer.toString(x)+":"+Integer.toString(y);

                int index8=c2.getColumnIndex(DBAdapterTu.END_TIME);

                a=Double.parseDouble(c2.getString(index8));
                x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                y=(int)a;
                if(y<10)
                    timing_[i][j]=timing_[i][j]+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timing_[i][j]=timing_[i][j]+"-"+Integer.toString(x)+":"+Integer.toString(y);

                i++;



            }while(c2.moveToNext());
        }

        dbtu.close();
        j++;
        i=0;

        //wednesday


        final DBAdapterW dbw=new DBAdapterW(TimeTableWeek.this);
        dbw.open();
        c2=dbw.getAllContacts();
        if(c2.moveToFirst())
        {

            do{

                subject_[i][j]=c2.getString(c2.getColumnIndex(DBAdapterW.SUBJECT));
                int index7=c2.getColumnIndex(DBAdapterW.START_TIME);
                double a=Double.parseDouble(c2.getString(index7));
                int x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                int y=(int)a;
                if(y<10)
                    timing_[i][j]=Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timing_[i][j]=Integer.toString(x)+":"+Integer.toString(y);

                int index8=c2.getColumnIndex(DBAdapterW.END_TIME);

                a=Double.parseDouble(c2.getString(index8));
                x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                y=(int)a;
                if(y<10)
                    timing_[i][j]=timing_[i][j]+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timing_[i][j]=timing_[i][j]+"-"+Integer.toString(x)+":"+Integer.toString(y);

                i++;



            }while(c2.moveToNext());
        }

        dbw.close();
        j++;
        i=0;

        //thursday


        final DBAdapterTh dbth=new DBAdapterTh(TimeTableWeek.this);
        dbth.open();
        c2=dbth.getAllContacts();
        if(c2.moveToFirst())
        {

            do{

                subject_[i][j]=c2.getString(c2.getColumnIndex(DBAdapterTh.SUBJECT));
                int index7=c2.getColumnIndex(DBAdapterTh.START_TIME);
                double a=Double.parseDouble(c2.getString(index7));
                int x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                int y=(int)a;
                if(y<10)
                    timing_[i][j]=Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timing_[i][j]=Integer.toString(x)+":"+Integer.toString(y);

                int index8=c2.getColumnIndex(DBAdapterTh.END_TIME);

                a=Double.parseDouble(c2.getString(index8));
                x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                y=(int)a;
                if(y<10)
                    timing_[i][j]=timing_[i][j]+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timing_[i][j]=timing_[i][j]+"-"+Integer.toString(x)+":"+Integer.toString(y);

                i++;



            }while(c2.moveToNext());
        }

        dbth.close();
        j++;
        i=0;

        //friday


        final DBAdapterF dbf=new DBAdapterF(TimeTableWeek.this);
        dbf.open();
        c2=dbf.getAllContacts();
        if(c2.moveToFirst())
        {

            do{

                subject_[i][j]=c2.getString(c2.getColumnIndex(DBAdapterF.SUBJECT));
                int index7=c2.getColumnIndex(DBAdapterF.START_TIME);
                double a=Double.parseDouble(c2.getString(index7));
                int x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                int y=(int)a;
                if(y<10)
                    timing_[i][j]=Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timing_[i][j]=Integer.toString(x)+":"+Integer.toString(y);

                int index8=c2.getColumnIndex(DBAdapterF.END_TIME);

                a=Double.parseDouble(c2.getString(index8));
                x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                y=(int)a;
                if(y<10)
                    timing_[i][j]=timing_[i][j]+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timing_[i][j]=timing_[i][j]+"-"+Integer.toString(x)+":"+Integer.toString(y);

                i++;



            }while(c2.moveToNext());
        }

        dbf.close();
        //j++;

        for(int i=0;i<1;i++) {
            j=0;

            //Create linear layouts


            LinearLayout  layout1 = createLinearLayout();
            fillLayoutDay(layout1,"MONDAY");
            j++;

            LinearLayout  layout2 = createLinearLayout();
            fillLayoutDay(layout2,"TUESDAY");
            j++;

            LinearLayout  layout3 = createLinearLayout();
            fillLayoutDay(layout3,"WEDNESDAY");
            j++;

            LinearLayout  layout4 = createLinearLayout();
            fillLayoutDay(layout4,"THURSDAY");
            j++;

            LinearLayout  layout5 = createLinearLayout();
            fillLayoutDay(layout5,"FRIDAY");
            j++;
            LinearLayout  layout6 = createLinearLayout();
            fillLayoutDay(layout6,"SATURDAY");
            j++;





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




        for(int i=0;i<8;i++) {
            j=0;

            //Create linear layouts


                LinearLayout  layout1 = createLinearLayout();
                fillLayout(layout1,subject_[i][j],timing_[i][j]);
                 j++;

                LinearLayout  layout2 = createLinearLayout();
                fillLayout(layout2,subject_[i][j],timing_[i][j]);
            j++;

                LinearLayout  layout3 = createLinearLayout();
                fillLayout(layout3,subject_[i][j],timing_[i][j]);
            j++;

                LinearLayout  layout4 = createLinearLayout();
                fillLayout(layout4,subject_[i][j],timing_[i][j]);
            j++;

                LinearLayout  layout5 = createLinearLayout();
                fillLayout(layout5,subject_[i][j],timing_[i][j]);
            j++;
            LinearLayout  layout6 = createLinearLayout();
            fillLayout(layout6,subject_[i][j],timing_[i][j]);
            j++;





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

    private LinearLayout fillLayoutDay(LinearLayout layout,String s1){
        //Create Subject text view
        TextView subtext = createSubjectText(s1);
        //Create time text view

        layout.addView(subtext);
       // layout.addView(timetext);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimeTableWeek.this,SubjectDetail.class));
            }
        });

        return layout;
    }



        private LinearLayout fillLayout(LinearLayout layout,String s1,String s2){
            //Create Subject text view
            TextView subtext = createSubjectText(s1);
            //Create time text view
            TextView timetext = createTimeText(s2);
            //Add to linear layout
            if(s2==null){
                return layout;
            }
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

        private TextView createSubjectText(String s1){
            TextView text = new TextView(this);
            text.setLayoutParams(subjectTextParams);
            text.setText(s1);
            text.setGravity(Gravity.CENTER);
            text.setBackgroundColor(getResources().getColor(R.color.primaryColor));
            text.setTextColor(getResources().getColor(R.color.accentColor));
            return text;
        }

        private TextView createTimeText(String s2){
            TextView text = new TextView(this);
            text.setLayoutParams(timeTextParams);
            text.setGravity(Gravity.CENTER);
            text.setBackgroundColor(getResources().getColor(R.color.primaryColorLight));
            text.setTextColor(getResources().getColor(R.color.my_color_highlight));
            text.setText(s2);
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