package com.sebat.sebatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bantuan_2 extends AppCompatActivity {
    Button btn_kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan_2);
        btn_kembali = (Button) findViewById(R.id.btn_kembali);

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(Bantuan_2.this, BantuanActivity.class);
                startActivity(main);
            }
        });
    }
}