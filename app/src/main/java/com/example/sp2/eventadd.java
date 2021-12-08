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


public class eventadd extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt;
    private Button addCourseBtn;
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

                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseDay[0], courseMonth[0], courseYear[0], courseType);
                // after adding the data we are displaying a toast message.
                Toast.makeText(eventadd.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseDescriptionEdt.setText("");
                Intent i = new Intent(eventadd.this, MainActivity.class);
                startActivity(i);
            }
        });

        if (getIntent().getExtras() != null) {
            String activityFrom = getIntent().getStringExtra(TAG_ACTIVITY_FROM);
            String s11 = getIntent().getStringExtra(VALUE);

            if(activityFrom.equals("Fromadap")){

                String courseName = s11;
                dbHandler.deleteCourse(courseName);
  
                
                Toast.makeText(this, "Course Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(eventadd.this, MainActivity.class);
                startActivity(i);

            }

            if(activityFrom.equals("Fromfrag")){
                courseType = s11;
            }
        }
    }
        @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}