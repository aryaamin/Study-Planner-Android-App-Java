package com.example.sp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

public class CalenderFragment extends Fragment{

    private MCalendarView calendarView;
    private DBHandler dbHandler;
    private ListView courseslist;
    ArrayList<String> courses = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calender, container, false);

        ImageButton backcal = (ImageButton) view.findViewById(R.id.backarrow);
        backcal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), MainActivity.class);
                view.getContext().startActivity(intent);
            }

        });

        calendarView = ((MCalendarView) view.findViewById(R.id.calendar));
        dbHandler = new DBHandler(getActivity());

        for(int i=2000; i<2100; i++){
            for(int j=1; j<13; j++){
                for(int k=1; k<32; k++){
                    calendarView.unMarkDate(i, j, k);
                }
            }
        }

        for(int i=0; i<dbHandler.readCourses().size(); i++) {

            int y = Integer.parseInt(dbHandler.readCourses().get(i).getCourseYear());
            int m = Integer.parseInt(dbHandler.readCourses().get(i).getCourseMonth())+1;
            int d = Integer.parseInt(dbHandler.readCourses().get(i).getCourseDay());

            calendarView.markDate(y, m, d);

        }

        courseslist = ((ListView) view.findViewById(R.id.courseslist));

        calendarView.setOnDateClickListener(new OnDateClickListener(){

            @Override
            public void onDateClick(View view, DateData date) {

                int no1 = 0;
                int no2 = 0;
                int no3 = 0;
                int no4 = 0;
                courses.clear();
                for(int i=0; i<dbHandler.readCourses().size(); i++) {
                    if(dbHandler.readCourses().get(i).getCourseDay().equals(date.getDayString())) {
                        if (dbHandler.readCourses().get(i).getCourseType().equals("StudyPlans")) {
                            no1++;
                        }
                        if (dbHandler.readCourses().get(i).getCourseType().equals("Assignments")) {
                            no2++;
                        }
                        if (dbHandler.readCourses().get(i).getCourseType().equals("Exams")) {
                            no3++;
                        }
                        if (dbHandler.readCourses().get(i).getCourseType().equals("Lectures")) {
                            no4++;
                        }
                    }
                }

                courses.add("StudyPlans    " + no1);
                courses.add("Assignments   " + no2);
                courses.add("Exams         " + no3);
                courses.add("Lectures      " + no4);

                ArrayAdapter<String> coursesAdapter = new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.simple_list_item_1,
                        courses
                );
                courseslist.setAdapter(coursesAdapter);
            }

        });


        return view;
    }
}