package com.example.sp2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import sun.bob.mcalendarview.MCalendarView;

public class CalenderFragment extends Fragment{

    private MCalendarView calendarView;
    private DBHandler dbHandler;
    Queue<Integer> qy
            = new LinkedList<>();

    Queue<Integer> qm
            = new LinkedList<>();

    Queue<Integer> qd
            = new LinkedList<>();

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

        while(!qy.isEmpty()){
            calendarView.unMarkDate(qy.peek(), qm.peek(), qd.peek());
            qy.remove();
            qm.remove();
            qd.remove();
        }

        for(int i=0; i<dbHandler.readCourses().size(); i++) {

            int y = Integer.parseInt(dbHandler.readCourses().get(i).getCourseYear());
            int m = Integer.parseInt(dbHandler.readCourses().get(i).getCourseMonth())+1;
            int d = Integer.parseInt(dbHandler.readCourses().get(i).getCourseDay());

            calendarView.markDate(y, m, d);
            qy.add(y);
            qm.add(m);
            qd.add(d);

        }
        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).commit();
//
//    }

}