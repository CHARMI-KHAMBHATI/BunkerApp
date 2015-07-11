package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectYear.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectYear#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectYear extends Fragment {

    public static final String PREF_FILE = "divisionUpdated";

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
    Bundle bundle2=new Bundle();

    List<String> data = new ArrayList<String>();

    String[] Years = {"I","II","III","IV"};

    final private String key = "#123";

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectYear.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectYear newInstance(String param1, String param2) {
        SelectYear fragment = new SelectYear();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SelectYear() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            saveToPreferences(getActivity(), "divisionsUpdated", "0");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_select_year, container, false);

        getFragmentManager().popBackStack();


        for (int i=0;i<Years.length;i++)
        {
            String current;
            current = Years[i%Years.length];
            data.add(current);
        }

        ProgressBar progress;
        progress=(ProgressBar)rootView.findViewById(R.id.progress);
        progress.setVisibility(View.GONE);




        mrecyclerView = (RecyclerView) rootView.findViewById(R.id.select_year_list);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new DownloadTTAdapter(getActivity(),data);
        mrecyclerView.setAdapter(mAdapter);

        mrecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Fragment newFragment;
                        if (position != 0)
                        {

                            newFragment = new SelectBranch();

                        }
                        else
                        {

                            newFragment = new SelectDiv();
                        }
                        String val = Integer.toString(position);
                        bundle.putString(key, val);
                        newFragment.setArguments(bundle);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(android.R.id.content, newFragment);
                        //transaction.addToBackStack(null);
                        transaction.commit();

                    }
                })
        );


        //Staggered Grid Layout Manager
        mrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

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

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(preferenceName, preferenceValue);
        edit.apply();
    }




}
