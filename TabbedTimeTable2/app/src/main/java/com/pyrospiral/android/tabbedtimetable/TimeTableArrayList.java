package com.pyrospiral.android.tabbedtimetable;

import java.util.ArrayList;

/**
 * Created by Kush on 1/15/2015.
 */
public class TimeTableArrayList {

    public String[] subjects;
    public boolean[] assignments;
    public String[] attendances;
    public String[] teachers;
    public String[] timings;

    public ArrayList<TimeTableArrayListItems> items;
    int k;


    public class TimeTableArrayListItems
    {
        public String subject;
        public String attendance;
        public boolean assignment;
        public String teacher;
        public String timing;
    }

    public TimeTableArrayList(String[] subjects, boolean[] assignments, String[] attendances, String[] teachers,String[] timings) {
        super();
        items = new ArrayList<TimeTableArrayListItems>();
        this.teachers = teachers;
        this.subjects = subjects;
        this.assignments = assignments;
        this. attendances = attendances;
        this.timings = timings;
        fillArrayList();
    }

    private void fillArrayList()
    {
        items.clear();
        for(k=0;k<subjects.length && k<attendances.length && k<assignments.length && k<teachers.length&&k<timings.length;k++)
        {
            if((teachers[k]==null) && (subjects[k]==null) && timings[k]==null)
                continue;
            items.add(new TimeTableArrayListItems()
            {{
                    teacher = teachers[k];
                    subject = subjects[k];
                    attendance = attendances[k];
                    assignment = assignments[k];
                    timing = timings[k];

                }});


        }
    }

}
