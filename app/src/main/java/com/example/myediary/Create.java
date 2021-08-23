package com.example.myediary;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Create extends Fragment {

    //Variables declared and to be used in linking.
    EditText date, semester, topic, descriptions, task;
    Button create;
    public ContentValues contentValues = new ContentValues();


    //Connecting to the database
    //  database_Helper myDatabase;
    SQLiteOpenHelper programDatabase;
    SQLiteDatabase sqLiteDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View myView = inflater.inflate(R.layout.create, container, false);

        //Linking the variables with the edit text from design view by their ID

        date = myView.findViewById(R.id.etDate);
        semester = myView.findViewById(R.id.etSemester);
        topic = myView.findViewById(R.id.etTopic);
        descriptions = myView.findViewById(R.id.etDescription);
        task = myView.findViewById(R.id.etTasks);
        create = myView.findViewById(R.id.btnCreate);


        //  myDatabase = new database_Helper(getActivity());
        programDatabase = new programDatabase(getActivity());

        //Action for clicking the button
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating/saving to sqlite database

                //Converting the String edit text text to integer

                try {
                    int semesterNum = 0;
                    semesterNum = Integer.parseInt(semester.getText().toString());


                    //Checks for any empty fields
                    if (date.getText().toString().isEmpty() || semester.getText().toString().isEmpty()
                            || topic.getText().toString().isEmpty() || descriptions.getText().toString().isEmpty()
                            || task.getText().toString().isEmpty()) {

                        Toast.makeText(getActivity(), "Input is required", Toast.LENGTH_SHORT).show();
                    }
                    //Making sure that the semester number is not greater than 2
                    else if (semesterNum > 2) {
                        Toast.makeText(getActivity(), "Semester cannot be higher than 2", Toast.LENGTH_SHORT).show();

                    }
                    //Now inserting data to the database
                    else {
                        String myDate = date.getText().toString();
                        String mySemester = semester.getText().toString();
                        String myTopic = topic.getText().toString();
                        String myDescription = descriptions.getText().toString();
                        String myTasks = task.getText().toString();

                        //Writing to the database
                        sqLiteDatabase = programDatabase.getWritableDatabase();

                        Boolean checkData = insertToDB(myDate, mySemester, myTopic, myDescription, myTasks);

                        if (checkData == true)
                            Toast.makeText(getActivity(), "Added to diary!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getActivity(), "Data for that date already exist", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception exception) {
                    //Error message if converting to int was not successful
                    Toast.makeText(getActivity(), "Only input number for the semester", Toast.LENGTH_LONG).show();

                }


            }
        });


        return myView;
    }


    //Method to help us insert data to the database
    public Boolean insertToDB(String date, String semseter, String topic, String description, String tasks) {

        contentValues.put(com.example.myediary.programDatabase.COLUMN_2, date);
        contentValues.put(com.example.myediary.programDatabase.COLUMN_3, semseter);
        contentValues.put(com.example.myediary.programDatabase.COLUMN_4, topic);
        contentValues.put(com.example.myediary.programDatabase.COLUMN_5, description);
        contentValues.put(com.example.myediary.programDatabase.COLUMN_6, tasks);

        long l = sqLiteDatabase.insert(com.example.myediary.programDatabase.table, null, contentValues);
        if (l == -1) {
            return false;
        } else {
            return true;
        }
    }


}
