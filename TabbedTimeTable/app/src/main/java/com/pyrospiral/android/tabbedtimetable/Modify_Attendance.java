package com.pyrospiral.android.tabbedtimetable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaano8 on 1/2/15.
 */
public class Modify_Attendance extends Fragment {

    public Modify_Attendance() {
    }

    public RecyclerView mrecyclerView;
    private EditAttAdapter mAdapter;
    List<Info> new_data = new ArrayList<>();
    Button all_A,all_C;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_modify_attendance, container, false);

        new_data = new ArrayList<>();

        //String array for Subject Name
        final String[] subName = {"DBMS",
                "TCS",
                "CS",
                "Cont. S",
                "EM III",
                "SW II"};

        for(int i=0;i<subName.length;i++)
        {
            Info current = new Info();
            current.subName = subName[i%subName.length];
            new_data.add(current);
        }


        //setting recycler view
        mrecyclerView = (RecyclerView) rootView.findViewById(R.id.subjects);
        mrecyclerView.setHasFixedSize(true);
        mAdapter = new EditAttAdapter(getActivity(),new_data);
        mrecyclerView.setAdapter(mAdapter);

        //Set out layout Manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(mLayoutManager);

        all_A = (Button) rootView.findViewById(R.id.all_absent);
        all_C = (Button) rootView.findViewById(R.id.all_cancelled);

        all_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // Set Alert Dialog Title
                builder.setTitle("Are you sure?");

                // Set an Icon for this Alert Dialog
                builder.setIcon(R.drawable.warning);

                // Set Alert Dialog Message
                builder.setMessage("Were you Absent in all the classes yesterday?")

                        // Positive button functionality
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg0) {

                             /*You need to subtract 1 attendance from  class attended */

                             Toast.makeText(getActivity(), "All Abesent Saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                                // Negative button functionality
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg0) {

                                //Do more stuffs
                                //dialog.cancel();
                            }
                        });

                // Create the Alert Dialog
                AlertDialog alertdialog = builder.create();

                // Show Alert Dialog
                alertdialog.show();
            }
        });

        all_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // Set Alert Dialog Title
                builder.setTitle("Are you sure?");

                // Set an Icon for this Alert Dialog
                builder.setIcon(R.drawable.warning);

                // Set Alert Dialog Message
                builder.setMessage("Were you Absent in all the classes yesterday?")

                        // Positive button functionality
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg0) {

                             /*You need to subtract 1-1 attendance from both class attended and
                              total classes*/

                                 Toast.makeText(getActivity(), "All Cancelled Saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                                // Negative button functionality
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg0) {

                                //Do more stuffs
                                //dialog.cancel();
                            }
                        });

                // Create the Alert Dialog
                AlertDialog alertdialog = builder.create();

                // Show Alert Dialog
                alertdialog.show();
            }
        });

        return rootView;
    }

}