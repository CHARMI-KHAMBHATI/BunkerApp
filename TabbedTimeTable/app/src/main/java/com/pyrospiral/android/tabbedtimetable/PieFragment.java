package com.pyrospiral.android.tabbedtimetable;


import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;


/**
 * A simple {@link Fragment} subclass.
 */
public class PieFragment extends Fragment {

    private String check;







    //TODO:GET PERCENTAGE ATTENDANCE FROM DATABASE, CONVERT TO FLOAT
    private float value = (float) 84.4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        // View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
        {
            check =intent.getStringExtra(Intent.EXTRA_TEXT);
        }

        final DBAttendence dba=new DBAttendence(getActivity());
        dba.open();
        Cursor c=dba.getContact(check);
       if(c.moveToFirst())
        {
            int index1,index2;
            float a,b;
            index1=c.getColumnIndex(DBAttendence.PRESENT);
            index2=c.getColumnIndex(DBAttendence.TOTAL);
            a=c.getFloat(index1);
            b=c.getFloat(index2);

            Log.e("",""+a+"");
            Log.e("",""+b+"");
            Log.e("",""+a/b+"");


            if(b!=0.0)
            value=a/b*100;
            else
                value=100;
        }



        dba.close();

        final View v = inflater.inflate(R.layout.fragment_pie, container, false);
        final Resources resources = getResources();
        TextView text = (TextView) v.findViewById(R.id.text);
        TextView percent = (TextView) v.findViewById(R.id.percentage);
        final PieGraph pg = (PieGraph) v.findViewById(R.id.piegraph);
        PieSlice slice1 = new PieSlice();
        slice1.setColor(resources.getColor(R.color.dividerColor));
        slice1.setValue(100);
        slice1.setGoalValue(100 - value);
        pg.addSlice(slice1);
        PieSlice slice2 = new PieSlice();
        slice2.setColor(resources.getColor(R.color.highAttendance));
        slice2.setValue((float)0.00000001);
        slice2.setGoalValue(value);
        pg.addSlice(slice2);

        percent.setText(value+"%");

        if(value<10) {
            text.setText("I think you don't go to college anymore, you might wanna uninstall this app.");
            slice2.setColor(resources.getColor(R.color.attendance10));
        }
        else if(value<20){
            text.setText("You really don't like this subject do you?");
            slice2.setColor(resources.getColor(R.color.attendance20));
        }
        else if(value<30){
            text.setText("Abandon all hope - ye who enter");
            slice2.setColor(resources.getColor(R.color.attendance30));}
        else if(value<40){
            text.setText("This is your attendance, not your marks in electrical, keep the percentage up!");
            slice2.setColor(resources.getColor(R.color.attendance40));}
        else if(value<50){
            text.setText("Are you planning to repeat this semester?");
            slice2.setColor(resources.getColor(R.color.attendance50));}
        else if(value<60){
            text.setText("You better have your medical certificate.");
            slice2.setColor(resources.getColor(R.color.attendance60));}
        else if(value<70){
            text.setText("Attend your classes dude or you're going to be in trouble.");
            slice2.setColor(resources.getColor(R.color.attendance70));}
        else if(value<80){
            text.setText("You might wanna keep your bunks limited.");
            slice2.setColor(resources.getColor(R.color.attendance80));}
        else if(value<90){
            text.setText("You're safe bro, keep up the bunks.");
            slice2.setColor(resources.getColor(R.color.attendance90));}
        else if(value>90){
            text.setText("Try not going to class once in a while, it's fun, I promise.");
            slice2.setColor(resources.getColor(R.color.highAttendance));}


        //For setting inner cicle radius
        pg.setInnerCircleRatio(210);


        //Handles animation
        pg.setDuration(1700);
        pg.setInterpolator(new AccelerateDecelerateInterpolator());//default if unspecified is linear; constant speed
        pg.setAnimationListener(getAnimationListener());
        pg.animateToGoalValues();//animation will always overwrite. Pass true to call the onAnimationCancel Listener with onAnimationEnd


        /*
        pg.setOnSliceClickedListener(new PieGraph.OnSliceClickedListener() {

            @Override
            public void onClick(int index) {
                Toast.makeText(getActivity(),
                        "Slice " + index + " clicked",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
        */


        return v;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public Animator.AnimatorListener getAnimationListener(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)
            return new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.e("piefrag", "anim end");
                }

                @Override
                public void onAnimationCancel(Animator animation) {//you might want to call slice.setvalue(slice.getGoalValue)
                    Log.e("piefrag", "anim cancel");
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };
        else return null;

    }


}
