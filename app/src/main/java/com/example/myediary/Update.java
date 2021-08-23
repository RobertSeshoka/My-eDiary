package com.example.myediary;

import android.content.ContentValues;
import android.database.Cursor;
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

public class Update extends Fragment {
    EditText date, semester, topic, descriptions, task;
    Button updateData;
    public ContentValues contentValues = new ContentValues();


    //Connecting to the database
    //  database_Helper myDatabase;
    SQLiteOpenHelper programDatabase;
    SQLiteDatabase sqLiteDatabase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.update, container, false);
        updateData = view.findViewById(R.id.btnUpdate);


        //Linking the variables
        date = view.findViewById(R.id.etDateUpdate);
        semester = view.findViewById(R.id.etSemesterUpdate);
        topic = view.findViewById(R.id.etTopicUpdate);
        descriptions = view.findViewById(R.id.etDescriptionUpdate);
        task = view.findViewById(R.id.etTasksUpdate);

        programDatabase = new programDatabase(getActivity());


        //Update button action
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateDate = date.getText().toString();
                String updateSemester = semester.getText().toString();
                String updateTopic = topic.getText().toString();
                String updateDescription = descriptions.getText().toString();
                String updateTasks = task.getText().toString();


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

                    } else {
                        sqLiteDatabase = programDatabase.getWritableDatabase();

                        Boolean checkUpdate = updateData(updateDate, updateSemester, updateTopic, updateDescription, updateTasks);


                        //Checks if updated was granted
                        if (checkUpdate = true) {
                            Toast.makeText(getActivity(), "Diary updated!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Diary already updated!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception exception) {
                    //Error message if converting to int was not successful
                    Toast.makeText(getActivity(), "Only input number for the semester", Toast.LENGTH_LONG).show();
                }

            }
        });



        return view;


    }

    programDatabase programDatabase2;

    //Updating data from the database
    public Boolean updateData(String date, String semseter, String topic, String description, String tasks) {


        contentValues.put(com.example.myediary.programDatabase.COLUMN_2, date);
        contentValues.put(com.example.myediary.programDatabase.COLUMN_3, semseter);
        contentValues.put(com.example.myediary.programDatabase.COLUMN_4, topic);
        contentValues.put(com.example.myediary.programDatabase.COLUMN_5, description);
        contentValues.put(com.example.myediary.programDatabase.COLUMN_6, tasks);


        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + com.example.myediary.programDatabase.table +
                " where date = ?", new String[]{date});
        if (cursor.getCount() > 0) {
            long result = sqLiteDatabase.update(com.example.myediary.programDatabase.table, contentValues, "date=?", new String[]{date});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
