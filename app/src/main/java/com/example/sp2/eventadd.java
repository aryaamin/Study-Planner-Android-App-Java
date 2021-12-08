package com.example.sp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sp2.DBHandler;
import com.example.sp2.R;
import com.example.sp2.ViewCourses;

public class eventadd extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt;
    private Button addCourseBtn, readCourseBtn, deleteCourseBtn;
    private DBHandler dbHandler;
    private CalendarView calendarView;
    public static final String VALUE = "value";
    public static final String TAG_ACTIVITY_FROM = "WhichActivity";
    String courseType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventadd);

        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        readCourseBtn = findViewById(R.id.idBtnReadCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDelete);
        calendarView = findViewById(R.id.calendarView);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(eventadd.this);
        final String[] courseDay = new String[1];
        final String[] courseMonth = new String[1];
        final String[] courseYear = new String[1];

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                courseDay[0] = Integer.toString(dayOfMonth);
                courseMonth[0] = Integer.toString(month);
                courseYear[0] = Integer.toString(year);
            }
        });



        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String courseName = courseNameEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();
                //String courseType = "fjnd";


                // validating if the text fields are empty or not.
                if (courseName.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(eventadd.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseDay[0], courseMonth[0], courseYear[0], courseType);

                // after adding the data we are displaying a toast message.
                Toast.makeText(eventadd.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseDescriptionEdt.setText("");
            }
        });

        readCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(eventadd.this, ViewCourses.class);
                startActivity(i);
            }
        });

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            String courseName = extras.getString("key");
//            dbHandler.deleteCourse(courseName);
//            Intent i = new Intent(eventadd.this, ViewCourses.class);
//            startActivity(i);
//
//            //The key argument here must match that used in the other activity
//        }
//
//        if (extras != null) {
//            courseType = extras.getString("key1");
//            //The key argument here must match that used in the other activity
//        }




        if (getIntent().getExtras() != null) {
            String activityFrom = getIntent().getStringExtra(TAG_ACTIVITY_FROM);
            String s11 = getIntent().getStringExtra(VALUE);

            if(activityFrom.equals("Fromadap")){

                String courseName = s11;
                dbHandler.deleteCourse(courseName);
                Intent i = new Intent(eventadd.this, MainActivity.class);
                startActivity(i);

            }

            if(activityFrom.equals("Fromfrag")){
                courseType = s11;
            }
        }

        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.

                String courseName = courseNameEdt.getText().toString();
//                String courseDuration = courseDurationEdt.getText().toString();
//                String courseDescription = courseDescriptionEdt.getText().toString();


                dbHandler.deleteCourse(courseName);
                Toast.makeText(eventadd.this, "Deleted the course", Toast.LENGTH_SHORT).show();
            }
        });
    }
}