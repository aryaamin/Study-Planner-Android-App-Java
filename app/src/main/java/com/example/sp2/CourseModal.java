package com.example.sp2;

public class CourseModal {

    private String courseName;
    private String courseDuration;
    private String courseDay;
    private String courseMonth;
    private String courseYear;
    private String courseDescription;
    private String courseType;
    private int id;


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseDay() {
        return courseDay;
    }

    public void setCourseDay(String courseDay) {
        this.courseDay = courseDay;
    }

    public String getCourseMonth() {
        return courseMonth;
    }

    public void setCourseMonth(String courseMonth) {
        this.courseMonth = courseMonth;
    }

    public String getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(String courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CourseModal(String courseName, String courseDuration, String courseDescription, String courseDay, String courseMonth, String courseYear, String courseType) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseDay = courseDay;
        this.courseMonth = courseMonth;
        this.courseYear = courseYear;
        this.courseDescription = courseDescription;
        this.courseType = courseType;
    }
}
