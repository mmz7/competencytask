package com.example.competencytask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgroup;
    Button submit;
    String movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.submitbtn);
        rgroup = findViewById(R.id.radiogroup);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedid = rgroup.getCheckedRadioButtonId();
                if(selectedid == R.id.betterdays) {
                    movie = "Better Days";
                } else if(selectedid == R.id.upcomingsummer) {
                    movie = "Upcoming Summer";
                } else if(selectedid == R.id.loveo2o) {
                    movie = "Love O2O";
                }
            }
        });

    }
}