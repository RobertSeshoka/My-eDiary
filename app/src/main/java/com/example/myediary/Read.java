package com.example.myediary;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class Read extends Fragment {

    Button read, show;

    //Connecting to the database
    programDatabase myDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRead = inflater.inflate(R.layout.read, container, false);


        //Linking the button variable to the the button from the design
        read = viewRead.findViewById(R.id.btnRead);
        show = viewRead.findViewById(R.id.btnShowData);


        myDatabase = new programDatabase(getActivity());

        //Action when the button is clicked
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reading the data from the database
                try {
                    Cursor getDetails = myDatabase.getdata();
                    if (getDetails.getCount() == 0) {
                        Toast.makeText(getActivity(), "There is nothing in your diary", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    StringBuffer buffer = new StringBuffer();
                    while (getDetails.moveToNext()) {

                        buffer.append("Date :" + getDetails.getString(1) + "\n\n");
                        buffer.append("Semester number :" + getDetails.getString(2) + "\n\n");
                        buffer.append("Post title or topic :" + getDetails.getString(3) + "\n");
                        buffer.append("Description :" + getDetails.getString(4) + "\n");
                        buffer.append("Tasks :" + getDetails.getString(5) + "\n");
                    }


                    //Shows message on a dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(true);
                    builder.setTitle("My diary");
                    builder.setMessage(buffer.toString());
                    builder.show();
                } catch (Exception es) {

                    Toast.makeText(getActivity(), es.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Show button


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openNew = new Intent(getActivity(), Display.class);
                startActivity(openNew);
            }

        });


        return viewRead;
    }


}
