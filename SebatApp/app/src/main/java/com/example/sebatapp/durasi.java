package com.example.sebatapp;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;


public class durasi extends AppCompatActivity {

        RadioButton inp_30, inp_60, inp_90, inp_2, inp_5, inp_24;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            inp_30 = (RadioButton) findViewById(R.id.inp_30);
            inp_60 = (RadioButton) findViewById(R.id.inp_60);
            inp_90 = (RadioButton) findViewById(R.id.inp_90);
            inp_2 = (RadioButton) findViewById(R.id.inp_2);
            inp_5 = (RadioButton) findViewById(R.id.inp_5);
            inp_24 = (RadioButton) findViewById(R.id.inp_24);

            inp_30.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    inp_30.setChecked(true);
                    inp_60.setChecked(false);
                    inp_90.setChecked(false);
                    inp_2.setChecked(false);
                    inp_5.setChecked(false);
                    inp_24.setChecked(false);
                }
            });

            inp_60.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    inp_30.setChecked(false);
                    inp_60.setChecked(true);
                    inp_90.setChecked(false);
                    inp_2.setChecked(false);
                    inp_5.setChecked(false);
                    inp_24.setChecked(false);
                }
            });

            inp_90.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    inp_30.setChecked(false);
                    inp_60.setChecked(false);
                    inp_90.setChecked(true);
                    inp_2.setChecked(false);
                    inp_5.setChecked(false);
                    inp_24.setChecked(false);
                }
            });

            inp_2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    inp_30.setChecked(false);
                    inp_60.setChecked(false);
                    inp_90.setChecked(false);
                    inp_2.setChecked(true);
                    inp_5.setChecked(false);
                    inp_24.setChecked(false);
                }
            });

            inp_5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    inp_30.setChecked(false);
                    inp_60.setChecked(false);
                    inp_90.setChecked(false);
                    inp_2.setChecked(false);
                    inp_5.setChecked(true);
                    inp_24.setChecked(false);
                }
            });

            inp_24.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    inp_30.setChecked(false);
                    inp_60.setChecked(false);
                    inp_90.setChecked(false);
                    inp_2.setChecked(false);
                    inp_5.setChecked(false);
                    inp_24.setChecked(true);
                }
            });
        } // onCreate()
    }

