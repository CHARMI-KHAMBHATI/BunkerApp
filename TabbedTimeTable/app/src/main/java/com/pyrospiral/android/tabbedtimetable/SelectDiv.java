package com.pyrospiral.android.tabbedtimetable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public RecyclerView mrecyclerView;
    private DownloadTTAdapter mAdapter;

    Bundle bundle = new Bundle();
    final private String key = "#123";

    TextView Title;

    List<String> data = new ArrayList<String>();

    String[] DivI = {"A","B","C","D","E", "F","G","H","I","J"};


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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_select_year, container, false);

        bundle = getArguments();
        final String value = bundle.getString("#123");

        Toast.makeText(getActivity(),value,Toast.LENGTH_SHORT).show();

        for (int i=0;i<DivI.length;i++)
        {
            String current;
            current = DivI[i%DivI.length];
            data.add(current);
        }

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
                    public void onItemClick(View view, int position) {
                        String val = value;
                        val = val.concat(Integer.toString(position));
                        Toast.makeText(getActivity(),val,Toast.LENGTH_SHORT).show();


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

                             //TODO: Put the code to load the time table here according to the val

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
