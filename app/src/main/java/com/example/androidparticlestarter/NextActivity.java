package com.example.androidparticlestarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity
{
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // create the outlet for the label
        TextView t = (TextView) findViewById(R.id.txt1);

        SharedPreferences prefs = getSharedPreferences("userName", MODE_PRIVATE);
        String restoredText = prefs.getString("name", null);
        if (restoredText != null) {
            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
            t.setText("Hello " + name + "!!");
        }



    }

    public void startButtonPressed(View view)
    {
        Log.d("ALAY", "Button pressed;");

        // creating a segue in Android
        Intent i = new Intent(this, LastActivity.class);
        startActivity(i);

    }

}
