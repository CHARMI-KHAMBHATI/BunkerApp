package com.pyrospiral.android.tabbedtimetable;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AssignmentEntry extends ActionBarActivity {

    private TextView dueDate;
    private AssignmentDatePickerFragment DatePicker;

    private Cursor constantsCursor=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_entry);

        dueDate = (TextView) findViewById(R.id.dueDate);


        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                DatePicker.updateDate(dueDate);
            }
        });


        Button saves=(Button)findViewById(R.id.save_ass);
        Button discards=(Button)findViewById(R.id.discard_ass);

        final EditText subject=(EditText)findViewById(R.id.subject_ass);
        final EditText assignment=(EditText)findViewById(R.id.assignment_ass);
        final TextView time=(TextView)findViewById(R.id.dueDate);

        final DBAdapterAssign db=new DBAdapterAssign(AssignmentEntry.this);


        saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.open();
                String subject_tt=subject.getText().toString();
                String assignment_tt=assignment.getText().toString();
                String times=time.getText().toString();
                db.insertContact(subject_tt,times,assignment_tt);

                db.close();
                Toast.makeText(getBaseContext(),"YOLO",Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        discards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject.setText("");
                assignment.setText("");

                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });




        // EditText teachers=(EditText)findViewById(R.id.monTeacherText);



    }


    public void showDatePickerDialog() {
        DialogFragment newFragment = new AssignmentDatePickerFragment();
        DatePicker = (AssignmentDatePickerFragment) newFragment;
        newFragment.show(getFragmentManager(), "datePicker");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_assignment_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
