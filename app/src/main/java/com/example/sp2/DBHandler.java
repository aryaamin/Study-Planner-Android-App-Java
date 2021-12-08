package com.example.sp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "coursedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mycourses";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String DURATION_COL = "duration";
    private static final String DESCRIPTION_COL = "description";
    private static final String DAY_COL = "day";
    private static final String MONTH_COL = "month";
    private static final String YEAR_COL = "year";
    private static final String TYPE_COL = "type";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + DAY_COL + " TEXT,"
                + MONTH_COL + " TEXT,"
                + YEAR_COL + " TEXT,"
                + TYPE_COL + " TEXT)";


        db.execSQL(query);
    }


    public void addNewCourse(String courseName, String courseDuration, String courseDescription, String courseDay, String courseMonth, String courseYear, String courseType) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(DAY_COL, courseDay);
        values.put(MONTH_COL, courseMonth);
        values.put(YEAR_COL, courseYear);
        values.put(TYPE_COL, courseType);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<CourseModal> readCourses() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        if (cursorCourses.moveToFirst()) {
            do {

                courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5),
                        cursorCourses.getString(6),
                        cursorCourses.getString(7)));
            } while (cursorCourses.moveToNext());

        }

        cursorCourses.close();
        return courseModalArrayList;
    }

    public void deleteCourse(String courseName) {


        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name=?", new String[]{courseName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
