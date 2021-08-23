package com.example.myediary;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Display extends AppCompatActivity {

    final String TAG = "Display";
    ListView listView;
    programDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        database = new programDatabase(this);
        listView = findViewById(R.id.displayData);
        displayDate();

    }


    //Helper method to display the data
    public void displayDate() {
        Log.d(TAG, "Showing data on a list view");


        //Retrieving the data
        Cursor cursor = database.getdata();
        ArrayList<String> showData = new ArrayList<>();

        if (cursor.getCount() == 0) {
            Toast.makeText(Display.this, "There is nothing in your diary", Toast.LENGTH_SHORT).show();
            return;
        }
        //Using a while loop to loop through the data line by line
        while (cursor.moveToNext()) {
        showData.add(cursor.getString(1));
        showData.add(cursor.getString(2));
        showData.add(cursor.getString(3));
        showData.add(cursor.getString(4));
        showData.add(cursor.getString(5));

        }

        //Setting the adapter with the list adapter
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, showData);
        listView.setAdapter(listAdapter);
    }
}