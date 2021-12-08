package com.example.sp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.bob.mcalendarview.MCalendarView;

public class FragmentSecond extends Fragment{

    private MCalendarView calendarView;

    private DBHandler dbHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second11, container, false);

        calendarView = ((MCalendarView) view.findViewById(R.id.calendar));
        dbHandler = new DBHandler(getActivity());


        for(int i=0; i<dbHandler.readCourses().size(); i++) {
            String sd = dbHandler.readCourses().get(i).getCourseYear() + "/" + dbHandler.readCourses().get(i).getCourseMonth()+
            "/" + dbHandler.readCourses().get(i).getCourseDay() + " " + dbHandler.readCourses().get(i).getCourseDuration();

            int y = Integer.parseInt(dbHandler.readCourses().get(i).getCourseYear());
            int m = Integer.parseInt(dbHandler.readCourses().get(i).getCourseMonth());
            int d = Integer.parseInt(dbHandler.readCourses().get(i).getCourseDay());

            calendarView.markDate(y, m, d);
        }
        return view;
    }

}