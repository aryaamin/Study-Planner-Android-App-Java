package com.example.sp2;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class thirdFragment extends Fragment {

    private ArrayList<CourseModal> courseModalArrayList;
    private DBHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        Button addbutton3 = (Button) view.findViewById(R.id.addbutton3);
        addbutton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String courseType = "Exams";

                Intent intent = new Intent(getActivity(), eventadd.class);
                intent.putExtra(eventadd.TAG_ACTIVITY_FROM, "Fromfrag");
                intent.putExtra(eventadd.VALUE, courseType);
                view.getContext().startActivity(intent);
            }

        });

        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());


        for(int i=0; i<dbHandler.readCourses().size(); i++) {
            if(dbHandler.readCourses().get(i).getCourseType().equals("Exams")){
                courseModalArrayList.add(dbHandler.readCourses().get(i));
            }
        }

        courseRVAdapter = new CourseRVAdapter(courseModalArrayList, getActivity());
        coursesRV = view.findViewById(R.id.idRVCourses3);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        coursesRV.setAdapter(courseRVAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).commit();

    }

}