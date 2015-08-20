package com.andridlearning.amit_gupta.databaseexample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andridlearning.amit_gupta.databaseexample.model.Student;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Amit_Gupta on 8/20/15.
 */
public class MySqliteHelper extends SQLiteOpenHelper {

    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "StudentDB";
    private static final String table_Students = "students";
    private static final String student_ID = "id";
    private static final String student_name = "name";
    private static final String student_Subject = "subject";

    private static final String[] COLUMNS = {student_ID, student_name, student_Subject};

    public MySqliteHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // SQL statement to create book table
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + table_Students + " ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + student_name + " TEXT, " + student_Subject + " TEXT )";
        sqLiteDatabase.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_Students);
        this.onCreate(sqLiteDatabase);
    }

    public void insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(student_name, student.getName());
        values.put(student_Subject, student.getSubject());

        db.insert(table_Students, null, values);
        db.close();
    }

    public Student readStudentDetail(int id) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(table_Students, COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student();
        student.setId(Integer.parseInt(cursor.getString(0)));
        student.setName(cursor.getString(1));
        student.setSubject(cursor.getString(2));
        db.close();
        return student;
    }

    public List getAllStudents() {

        List students = new LinkedList();

        String query = "SELECT  * FROM " + table_Students;

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Student student = null;

        if (cursor.moveToFirst()) {

            do {
                student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setSubject(cursor.getString(2));
                students.add(student);
            } while (cursor.moveToNext());
        }
        db.close();
        return students;
    }

    public int updateStudentInfo(Student student) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(student_name, student.getName());
        values.put(student_Subject, student.getSubject());

        int i = db.update(table_Students, values, student_ID + " = ?", new String[]{Integer.toString(student.getId())});
        db.close();
        return i;
    }

    public void deleteStudent(Student student) {

        SQLiteDatabase db = getWritableDatabase();

        db.delete(table_Students, student_ID + " = ?", new String[]{Integer.toString(student.getId())});
        db.close();
    }

}
