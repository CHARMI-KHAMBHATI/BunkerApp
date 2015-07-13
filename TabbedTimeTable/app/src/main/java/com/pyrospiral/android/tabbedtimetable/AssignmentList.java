package com.pyrospiral.android.tabbedtimetable;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.mrengineer13.snackbar.SnackBar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AssignmentList extends Fragment implements SnackBar.OnMessageClickListener {

    int doing=0;

    String[] subName = new String[100];

    String[] assignmentName = new String[255];


    String[] dueDates = new String[100];

    //Snackbar values
    static final int SHORT_MSG = 0, LONG_MSG = 1;
    static final int SHORT_SNACK = 0, MED_SNACK = 1, LONG_SNACK = 2;
    static final int DEFAULT = 0, ALERT = 1, CONFIRM = 2, INFO = 3;
    static final int ACTION_BTN = 0, NO_ACTION_BTN = 1;
    static final int STRING_TYPE_STRING = 0, STRING_TYPE_RESOURCE = 1;

    private RecyclerView mrecyclerView;
    public AssignmentAdapter mAdapter;
    private AssignmentData temp;

    private int dataPosition;

    private List<AssignmentData> data;

    private View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        data = new ArrayList<>();

        final DBAdapterAssign db=new DBAdapterAssign(getActivity());
        db.open();
        Cursor c = db.getAllContacts();
        int i=0;
        if(c.moveToFirst())
        {
            do {
                int index1,index2,index3,index4,index5;
                index5=c.getColumnIndex(DBAdapterAssign.ROW_ID);
                index4=c.getColumnIndex(DBAdapterAssign.DELETE);
                index1=c.getColumnIndex(DBAdapterAssign.SUBJECT);
                index2=c.getColumnIndex(DBAdapterAssign.ASSIGNMENT);
                index3=c.getColumnIndex(DBAdapterAssign.DUE_DATE);
                int y=c.getInt(index4);
                if(y==0) {
                    subName[i] = c.getString(index1);
                    assignmentName[i] = c.getString(index2);
                    dueDates[i] = c.getString(index3);

                    i++;
                }
                else
                {
                    db.deleteContact(c.getInt(index5));
                }


            }while(c.moveToNext());
        }
        db.close();

        for(i=0;i<subName.length && i<assignmentName.length;i++)
        {

            if(subName[i]==null && assignmentName[i]==null)
                continue;
            AssignmentData current = new AssignmentData();
            current.subject = subName[i];
            current.assignmentContent = assignmentName[i];
            current.dueDate=dueDates[i];
            data.add(current);
        }


        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMessageClick(Parcelable parcelable) {



        data.add(dataPosition,temp);
        mAdapter.notifyDataSetChanged();

        String s=assignmentName[dataPosition];





        final DBAdapterAssign db=new DBAdapterAssign(getActivity());

        db.open();
        int x=0;
        Cursor c2=db.getContact(s);
        if(c2.moveToFirst())
        {
            x=c2.getInt(c2.getColumnIndex(DBAdapterAssign.ROW_ID));
            db.updateContact(x,0);


        }
        db.close();


    }

    public AssignmentList() {
        // Required empty public constructor
    }


   @Override
    public void onResume(
                         ){

       super.onResume();
       if(doing==0) {

           final FragmentManager fragmentManager = getFragmentManager();


           fragmentManager.beginTransaction()
                   .replace(R.id.container, new AssignmentList())
                   .commit();

           //    return rootView;
       }

        

    }








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        doing=1;

        rootView = inflater.inflate(R.layout.fragment_assignment, container, false);





        //setting recycler view
        mrecyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        mrecyclerView.setHasFixedSize(true);
        mAdapter = new AssignmentAdapter(getActivity(),data);
        mrecyclerView.setAdapter(mAdapter);


        FloatingActionButton buttona = (FloatingActionButton) rootView.findViewById(R.id.action_a);
        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onDestroyView();
               // fragmentTransaction.remove(yourfragment).commit()

                doing=0;


                startActivity(new Intent(getActivity(),AssignmentEntry.class));
            }
        });


        //Staggered Grid Layout Manager
        mrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        applyToRecycler();

        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Assignments");

        return rootView;
    }




    //SWIPE TO DISMISS HANDLED BELOW




    private void applyToRecycler(){

        SwipeDismissRecyclerViewTouchListener touchListener =
                new SwipeDismissRecyclerViewTouchListener(
                        mrecyclerView,
                        new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {


                                    //Add data to temporary object

                                    Log.e("temp position",""+position);
                                    temp = new AssignmentData();
                                    temp.dueDate = data.get(position).dueDate;
                                    temp.assignmentContent = data.get(position).assignmentContent;
                                    temp.subject = data.get(position).subject;

                                    dataPosition = position;

                                    String s=assignmentName[position];

                                    final DBAdapterAssign db=new DBAdapterAssign(getActivity());

                                    db.open();
                                    int x=0;
                                    Cursor c2=db.getContact(s);
                                    if(c2.moveToFirst())
                                    {
                                        x=c2.getInt(c2.getColumnIndex(DBAdapterAssign.ROW_ID));
                                        db.updateContact(x,1);


                                    }
                                    db.close();







                                    //Create Snackbar
                                   new SnackBar.Builder(getActivity().getApplicationContext(), rootView)
                                            .withMessage("Assignment Deleted.")
                                            .withActionMessage("UNDO")
                                            .withOnClickListener(AssignmentList.this)
                                            .withStyle(SnackBar.Style.ALERT)
                                            .withDuration(SnackBar.LONG_SNACK)
                                            .show();






                                    data.remove(position);


                                    int length =data.size();


                                }
                                // do not call notifyItemRemoved for every item, it will cause gaps on deleting items
                                mAdapter.notifyDataSetChanged();

                            }
                        });


        mrecyclerView.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        mrecyclerView.setOnScrollListener(touchListener.makeScrollListener());
        mrecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                    }
                }));
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }


    public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }
    }

}



