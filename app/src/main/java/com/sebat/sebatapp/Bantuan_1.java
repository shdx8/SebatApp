package com.sebat.sebatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sebat.sebatapp.R;

public class Bantuan_1 extends AppCompatActivity {
    Button btn_kembali;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#0FC883"));
        }
        setContentView(R.layout.activity_bantuan_1);
        btn_kembali = (Button) findViewById(R.id.btn_kembali);
        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(Bantuan_1.this, BantuanActivity.class);
                startActivity(main);
            }
        });
    }
}