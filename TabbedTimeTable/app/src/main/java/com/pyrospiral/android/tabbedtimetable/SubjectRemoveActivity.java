package com.pyrospiral.android.tabbedtimetable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SubjectRemoveActivity extends ActionBarActivity {
    String[] subname = new String[100];

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_remove);

        //String[] subname=new String[100];
        final DBAttendence dba = new DBAttendence(SubjectRemoveActivity.this);
        dba.open();
        Cursor c = dba.getAllContacts();
        int j = 0;
        if (c.moveToFirst()) {
            do {
                subname[j] = c.getString(c.getColumnIndex(DBAttendence.SUBJECT));
                j++;

            } while (c.moveToNext());
        }


        dba.close();


        LinearLayout layout = (LinearLayout) findViewById(R.id.subjectsLayout);

        for (int i = 0; i < j; i++) {
            LinearLayout layout1 = createSubject(i);
            layout.addView(layout1);
        }


    }

    private LinearLayout createSubject(final int x) {

        LinearLayout.LayoutParams Params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams Params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams ParamsWeight = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        ParamsWeight.weight = 1;

        LinearLayout layout = new LinearLayout(this);
        Params2.setMargins(0, getPx(5), 0, getPx(5));
        Params2.gravity = Gravity.CENTER_VERTICAL;
        layout.setLayoutParams(Params2);
        layout.setPadding(getPx(30), getPx(10), getPx(30), getPx(10));


        final TextView subjectName = new TextView(this);
        subjectName.setLayoutParams(ParamsWeight);
        subjectName.setText(subname[x]);
        subjectName.setTextSize(getPx(7));
        subjectName.setBackgroundColor(333333);

        Button button = new Button(this);
        button.setLayoutParams(ParamsWeight);
        button.setText("Remove");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder  = new AlertDialog.Builder(SubjectRemoveActivity.this);
                view = v;

                alertDialogBuilder.setTitle("Are you sure?");
                alertDialogBuilder
                        .setMessage("Select yes to remove the subject.")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            view.setVisibility(View.GONE);
                            subjectName.setVisibility(View.GONE);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();


                Toast.makeText(SubjectRemoveActivity.this, "REMOVING SUBJECT " + subname[x] + " ", Toast.LENGTH_SHORT).show();
                final DBAttendence dba = new DBAttendence(SubjectRemoveActivity.this);
                dba.open();

                dba.deleteContact(subname[x]);

                dba.close();

                final DBAdapter dbm = new DBAdapter(SubjectRemoveActivity.this);
                dbm.open();

                dbm.deleteContact(subname[x]);

                dbm.close();

                //tuesday


            }
        });


        //Toast.makeText(SubjectRemoveActivity.this,"",Toast.LENGTH_SHORT).show();


        layout.addView(subjectName);
        layout.addView(button);

        return layout;

    }

    public int getPx(int dimensionDp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);
    }
}
