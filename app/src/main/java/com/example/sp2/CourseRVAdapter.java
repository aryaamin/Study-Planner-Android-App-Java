package com.example.sp2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    private ArrayList<CourseModal> courseModalArrayList;
    private Context context;
    private DBHandler dbHandler;

    public CourseRVAdapter(ArrayList<CourseModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        //view.setOnClickListener(mOnClickListener);
        return new ViewHolder(view);
    }

    private long lastTouchTime = 0;
    private long currentTouchTime = 0;

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CourseModal modal = courseModalArrayList.get(position);
        holder.courseNameTV.setText("Name: "+modal.getCourseName());
        holder.courseDescTV.setText("Description: "+modal.getCourseDescription());
        holder.courseDurationTV.setText("Duration: "+modal.getCourseDuration());
        holder.courseDayTV.setText("Day: "+modal.getCourseDay());
        holder.courseMonthTV.setText("Month: "+String.valueOf(Integer.parseInt(modal.getCourseMonth())+1));
        holder.courseYearTV.setText("Year: "+modal.getCourseYear());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();
                String id= String.valueOf(courseModalArrayList.get(holder.getAdapterPosition()).getCourseName());

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
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView courseNameTV, courseDescTV, courseDurationTV, courseDayTV, courseMonthTV, courseYearTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);
            courseDayTV = itemView.findViewById(R.id.idTVDay);
            courseMonthTV = itemView.findViewById(R.id.idTVMonth);
            courseYearTV = itemView.findViewById(R.id.idTVYear);
        }
    }
}