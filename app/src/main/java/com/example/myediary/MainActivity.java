package com.example.myediary;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

        EditText date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final Intent anim = new Intent(this,Options.class);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.transition);

        Thread timer = new Thread()
        {
            public void run()
            {
                try {
                    sleep(8000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(anim);
                    finish();
                }
            }

        };

        timer.start();

    }

}