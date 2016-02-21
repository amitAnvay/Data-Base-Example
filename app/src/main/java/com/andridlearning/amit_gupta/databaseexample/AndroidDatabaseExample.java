package com.andridlearning.amit_gupta.databaseexample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.andridlearning.amit_gupta.databaseexample.db.MySqliteHelper;
import com.andridlearning.amit_gupta.databaseexample.model.Student;

import java.util.ArrayList;
import java.util.List;

public class AndroidDatabaseExample extends ListActivity implements AdapterView.OnItemClickListener {

    MySqliteHelper sqliteHelper = new MySqliteHelper(this);
    ArrayAdapter arrayAdapter;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_database_example);

        sqliteHelper.onUpgrade(sqliteHelper.getWritableDatabase(), 0, 0);
        sqliteHelper.insertStudent(new Student("George Smith", "History"));
        sqliteHelper.insertStudent(new Student("Mark Woolf", "English"));
        sqliteHelper.insertStudent(new Student("Chris Mayers", "Science"));
        sqliteHelper.insertStudent(new Student("John Smith", "Biology"));
        sqliteHelper.insertStudent(new Student("Rhonda Rodes", "English"));
        sqliteHelper.insertStudent(new Student("Kevin Macdonald", "Science"));
        sqliteHelper.insertStudent(new Student("Sasha Johnson", "History"));

        setListData();
        getListView().setOnItemClickListener(this);
    }

    private void setListData(){
        list =  sqliteHelper.getAllStudents();

        ArrayList arrayList = new ArrayList();

        for(int i=0; i < list.size(); i++) {
            Student student = (Student) list.get(i);
            arrayList.add(i, student.getName());
        }
        arrayAdapter = new ArrayAdapter(this, R.layout.row_layout, R.id.listText, arrayList);
        setListAdapter(arrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Student student = (Student)list.get(i);
        Intent intent = new Intent(this, StudentListActivity.class);
        intent.putExtra("student", student.getId());
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        setListData();
    }
}
