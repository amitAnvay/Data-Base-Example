package com.andridlearning.amit_gupta.databaseexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andridlearning.amit_gupta.databaseexample.db.MySqliteHelper;
import com.andridlearning.amit_gupta.databaseexample.model.Student;

public class StudentListActivity extends Activity {

    TextView studentName;
    TextView studentSubject;
    Student selectedStudent;
    MySqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        studentName = (TextView) findViewById(R.id.name);
        studentSubject = (TextView) findViewById(R.id.subject);

        Intent i = getIntent();
        int id = i.getIntExtra("student", -1);

        sqliteHelper = new MySqliteHelper(this);
        selectedStudent = sqliteHelper.readStudentDetail(id);

        studentName.setText(selectedStudent.getName());
        studentSubject.setText(selectedStudent.getSubject());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void update(View v) {
        String name = ((EditText) findViewById(R.id.titleEdit)).getText().toString();
        String subject = ((EditText) findViewById(R.id.authorEdit)).getText().toString();
        if(!name.equals("") && !subject.equals("")) {
            selectedStudent.setName(name);
            selectedStudent.setSubject(subject);
            // update book with changes
            sqliteHelper.updateStudentInfo(selectedStudent);
            Toast.makeText(getApplicationContext(), "This Student is updated.", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(getApplicationContext(), "Name or Subject cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View v) {
        Toast.makeText(getApplicationContext(), "This student is deleted.", Toast.LENGTH_SHORT).show();

        // delete selected book
        sqliteHelper.deleteStudent(selectedStudent);
        finish();
    }
}
