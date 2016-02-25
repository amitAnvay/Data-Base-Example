package com.andridlearning.amit_gupta.databaseexample;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.andridlearning.amit_gupta.databaseexample.db.MySqliteHelper;
import com.andridlearning.amit_gupta.databaseexample.model.Student;

/**
 * Created by Amit_Gupta on 2/21/16.
 */
public class AddStudentDialogFragment extends DialogFragment {

    EditText name;
    EditText  subject;
    Button cancel;
    Button add;
    MySqliteHelper _sqliteHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_student, container,false);
        getDialog().setTitle("Add Student");
        _sqliteHelper = ((AndroidDatabaseExample)getActivity()).sqliteHelper;
        name = (EditText)rootView.findViewById(R.id.studentName);
        subject  = (EditText)rootView.findViewById(R.id.subject);
        cancel = (Button)rootView.findViewById(R.id.btn_1);
        cancel.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                      dismiss();
                                      }
                                  });

                add = (Button) rootView.findViewById(R.id.btn_2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _sqliteHelper.insertStudent(new Student(name.getText().toString(), subject.getText().toString()));
                ((AndroidDatabaseExample)getActivity()).setListData();
                dismiss();
            }
        });

        // Do something else
        return rootView;
    }

}
