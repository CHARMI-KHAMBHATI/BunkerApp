package com.pyrospiral.android.tabbedtimetable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectDiv.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectDiv#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectDiv extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String querypassed;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public RecyclerView mrecyclerView;
    private DownloadTTAdapter mAdapter;

    static String[] mSubjects=new String[100];
    static boolean[] mAssignments=new boolean[10];
    static String[] mAttendances=new String[100];
    static String[] mTeachers=new String[100];

    static Double[] mStartTimings=new Double[100];

    static Double[] mEndTimings=new Double[100];

    static String[] mDay=new String[100];

    Bundle bundle = new Bundle();
    final private String key = "#123";
    String value;

    TextView Title;

    List<String> data = new ArrayList<String>();

    String[] DivI = new String[1000];



    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectDiv.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectDiv newInstance(String param1, String param2) {
        SelectDiv fragment = new SelectDiv();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SelectDiv() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Toast.makeText(getActivity(),"NOW",Toast.LENGTH_SHORT).show();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        bundle = getArguments();

        final String value = bundle.getString("#123");

        ParseQuery<ParseObject> query;




        querypassed="Time_Table_Btech";
        if(value.length()==1)
        {
            querypassed=querypassed+"1";
        }
        else
        {
            querypassed=querypassed+"2_";
            int branch=Integer.parseInt(value.charAt(1)+"");
            switch (branch) {
                case 0:
                    querypassed=querypassed+"coed";
                    break;
                case 1:
                    querypassed=querypassed+"ec";
                    break;
                case 2:
                    querypassed=querypassed+"ee";
                    break;
                case 3:
                    querypassed=querypassed+"mech";
                    break;
                case 4:
                    querypassed=querypassed+"ce";
                    break;
                case 5:
                    querypassed=querypassed+"ch";
                    break;
                default:
                    break;


            }

        }

        query = ParseQuery.getQuery(querypassed);


        final DBAllTimeTable dbt=new DBAllTimeTable(getActivity());
        dbt.open();
        Cursor c=dbt.getAllContact();

        if(c.moveToFirst()){

            //Toast.makeText(getActivity(),"TRD"+value,Toast.LENGTH_LONG).show();


        }
        else {

            query.findInBackground(new FindCallback<ParseObject>() {


                @Override
                public void done(List<ParseObject> list, com.parse.ParseException e) {
                    int datalenn=0;
                    for (ParseObject totem :list)
                    {
                        datalenn++;
                        String data= totem.getString("Division");
                        dbt.insertContact(data);
                    }
                    dbt.close();
                    if (datalenn==0){
                        Toast.makeText(getActivity(),"SORRY NO DATA",Toast.LENGTH_SHORT).show();

                        getActivity().finish();
                    }
                    SelectDiv newFragment = new SelectDiv();

                    bundle.putString(key, value);
                    newFragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(android.R.id.content, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();



                }

            });
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_select_year, container, false);


        final DBAllTimeTable dbt=new DBAllTimeTable(getActivity());

        bundle = getArguments();

        final String value = bundle.getString("#123");

        dbt.open();
        Cursor c2=dbt.getContact();
        if(c2.moveToFirst())
        {
            ProgressBar progress;
            progress=(ProgressBar)rootView.findViewById(R.id.progress);
            progress.setVisibility(View.GONE);
            int j=0;
            do{

                DivI[j]=c2.getString(c2.getColumnIndex(DBAllTimeTable.DIVISION));
                j++;

            }while (c2.moveToNext());

        }
        else
        {
           // Toast.makeText(getActivity(),"HERE",Toast.LENGTH_SHORT).show();

        }
        //dbt.deleteEverything();
        int lengthh=0;

        for (int i=0;i<DivI.length;i++)
        {
            if(DivI[i]==null)
                continue;
            lengthh++;
        }


        for(int k=lengthh-1;k>-1;k--)
        {
            String current;
            current = DivI[k%DivI.length];
            data.add(current);


        }



        if(DivI[0]!=null)
            dbt.deleteEverything();
        dbt.close();






       // Toast.makeText(getActivity(),value,Toast.LENGTH_SHORT).show();



        Title = (TextView) rootView.findViewById(R.id.title);

        Title.setText("Select Your Division");

        mrecyclerView = (RecyclerView) rootView.findViewById(R.id.select_year_list);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new DownloadTTAdapter(getActivity(),data);
        mrecyclerView.setAdapter(mAdapter);

        //Staggered Grid Layout Manager
        mrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());


        mrecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        String val = value;
                        val = val.concat(Integer.toString(position));



                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        // Set Alert Dialog Title
                        builder.setTitle("Alert!");

                        // Set an Icon for this Alert Dialog
                        builder.setIcon(R.drawable.warning);

                        // Set Alert Dialog Message
                        builder.setMessage("Are you sure you want to get this Time Table?")

                                // Positive button functionality
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {



                                    public void onClick(DialogInterface dialog, int arg0) {
                                        String divs="";
                                        switch (position)
                                        {
                                            case 0:
                                                divs="A";
                                                break;
                                            case 1:
                                                divs="B";
                                                break;
                                            case 2:
                                                divs="C";
                                                break;
                                            case 3:
                                                divs="D";
                                                break;
                                            case 4:
                                                divs="E";
                                                break;
                                            case 5:
                                                divs="F";
                                                break;
                                            case 6:
                                                divs="G";
                                                break;
                                            case 7:
                                                divs="H";
                                                break;
                                            case 8:
                                                divs="I";
                                                break;
                                            case 9:
                                                divs="J";
                                                break;
                                            case 10:
                                                divs="K";
                                                break;
                                        }



                                        ParseQuery<ParseObject> query_download=ParseQuery.getQuery(querypassed);
                                        query_download.whereEqualTo("Division",divs);

                                        query_download.findInBackground(new FindCallback<ParseObject>() {


                                            @Override
                                            public void done(List<ParseObject> list, com.parse.ParseException e) {
                                                int h=0;
                                                for(int xl=0;xl<100;xl++)
                                                {
                                                    mSubjects[xl]=null;
                                                }
                                                for (ParseObject totem : list) {

                                                    mSubjects[h]=totem.getString("Subject");

                                                    mDay[h]=totem.getString("DAY_week");
                                                    mStartTimings[h]=totem.getDouble("Start_time");
                                                    mEndTimings[h]=totem.getDouble("End_time");
                                                    mTeachers[h]=totem.getString("Teacher");
                                                    h++;

                                                }

                                                final DBAdapter db=new DBAdapter(getActivity());
                                                db.open();
                                                db.deleteEverything();
                                                for(int i=0;;i++)
                                                {
                                                    if(mSubjects[i]==null)
                                                        break;

                                                    db.insertContact(mSubjects[i], mStartTimings[i], mEndTimings[i], mTeachers[i], mDay[i]);

                                                }

                                                final DBAttendence dba=new DBAttendence(getActivity());
                                                dba.open();
                                                Cursor c2=dba.getAllContacts();

                                                if(c2.moveToFirst())
                                                {

                                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());


                                                    // Set Alert Dialog Title
                                                    builder2.setTitle("Alert!");

                                                    // Set an Icon for this Alert Dialog
                                                    builder2.setIcon(R.drawable.warning);

                                                    // Set Alert Dialog Message
                                                    builder2.setMessage("Do you want to clear attendance records?")

                                                            // Positive button functionality
                                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {


                                                                    Cursor c3=db.getSubjects();
                                                                    dba.deleteEverything();
                                                                    if(c3.moveToFirst())
                                                                    {
                                                                        do{

                                                                            String s=c3.getString(c3.getColumnIndex(DBAdapter.SUBJECT));
                                                                            dba.insertContact(s);


                                                                        }while (c3.moveToNext());
                                                                    }


                                                                }



                                                            })


                                                             // Negative button functionality
                                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int arg0) {
                                                        dialog.cancel();
                                                    }
                                                    });

                                                    // Create the Alert Dialog
                                                    AlertDialog alertdialog = builder2.create();

                                                    // Show Alert Dialog
                                                    alertdialog.show();


                                                }
                                                else
                                                {
                                                    Cursor c3=db.getSubjects();

                                                    if(c3.moveToFirst())
                                                    {
                                                        do{

                                                            String s=c3.getString(c3.getColumnIndex(DBAdapter.SUBJECT));
                                                            dba.insertContact(s);


                                                        }while (c3.moveToNext());
                                                    }

                                                }


                                                db.close();
                                                dba.close();

                                                getActivity().finish();


                                            }
                                        });
                                        
                                        Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                        // Negative button functionality
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int arg0) {

                                        dialog.cancel();
                                    }
                                });

                        // Create the Alert Dialog
                        AlertDialog alertdialog = builder.create();

                        // Show Alert Dialog
                        alertdialog.show();

                    }
                })
        );




        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

/*
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
