package com.example.sp2;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp2.CourseModal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<CourseModal> courseModalArrayList;
    private Context context;
    private DBHandler dbHandler;

    // constructor
    public CourseRVAdapter(ArrayList<CourseModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

//    private long lastTouchTime = 0;
//    private long currentTouchTime = 0;
//    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            lastTouchTime = currentTouchTime;
//            currentTouchTime = System.currentTimeMillis();
//            dbHandler = new DBHandler(context.getApplicationContext());
//            //String i = String.valueOf(v.getId());
//            get
//            //String item = String.valueOf(courseModalArrayList.get(selected_position).getCourseName());
//
//            if (currentTouchTime - lastTouchTime < 250) {
//                dbHandler.deleteCourse("ssss");
//                Toast.makeText(context, "gsbx", Toast.LENGTH_SHORT).show();
//                lastTouchTime = 0;
//                currentTouchTime = 0;
//            }
//
//        }
//    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        //view.setOnClickListener(mOnClickListener);
        return new ViewHolder(view);
    }

    private long lastTouchTime = 0;
    private long currentTouchTime = 0;

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data 
        // to our views of recycler view item.
        CourseModal modal = courseModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getCourseName());
        holder.courseDescTV.setText(modal.getCourseDescription());
        holder.courseDurationTV.setText(modal.getCourseDuration());
        holder.courseDayTV.setText(modal.getCourseDay());
        holder.courseMonthTV.setText(modal.getCourseMonth());
        holder.courseYearTV.setText(modal.getCourseYear());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();
                String id= String.valueOf(courseModalArrayList.get(holder.getAdapterPosition()).getCourseName());
                //dbHandler.deleteCourse(id);
                Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
                //You can call detail fragment here

                if (currentTouchTime - lastTouchTime < 250) {


                    String courseName = id;
//                    Intent i = new Intent(context, eventadd.class);
//                    i.putExtra("key", courseName);
//                    view.getContext().startActivity(i);


                    Intent intent = new Intent(context, eventadd.class);
                    intent.putExtra(eventadd.TAG_ACTIVITY_FROM, "Fromadap");
                    intent.putExtra(eventadd.VALUE, courseName);
                    view.getContext().startActivity(intent);



                    lastTouchTime = 0;
                    currentTouchTime = 0;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView courseNameTV, courseDescTV, courseDurationTV, courseDayTV, courseMonthTV, courseYearTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);
            courseDayTV = itemView.findViewById(R.id.idTVDay);
            courseMonthTV = itemView.findViewById(R.id.idTVMonth);
            courseYearTV = itemView.findViewById(R.id.idTVYear);
        }
    }
}