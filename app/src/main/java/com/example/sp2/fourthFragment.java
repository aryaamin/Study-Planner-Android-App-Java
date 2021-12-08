package com.example.sp2;

import static androidx.lifecycle.Lifecycle.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class fourthFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        Button addbutton4 = (Button) view.findViewById(R.id.addbutton4);
        addbutton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String courseType = "Lectures";

                Intent intent = new Intent(getActivity(), eventadd.class);
                intent.putExtra(eventadd.TAG_ACTIVITY_FROM, "Fromfrag");
                intent.putExtra(eventadd.VALUE, courseType);
                view.getContext().startActivity(intent);
            }

        });

        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());

        // getting our course array
        // list from db handler class.
        for(int i=0; i<dbHandler.readCourses().size(); i++) {
            if(dbHandler.readCourses().get(i).getCourseType().equals("Lectures")){
                courseModalArrayList.add(dbHandler.readCourses().get(i));
            }
        }

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new CourseRVAdapter(courseModalArrayList, getActivity());
        coursesRV = view.findViewById(R.id.idRVCourses4);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).commit();

    }

}