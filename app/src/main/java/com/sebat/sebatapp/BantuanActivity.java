package com.sebat.sebatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sebat.sebatapp.Adapter.BantuanAdapter;

import java.util.ArrayList;

public class BantuanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> JudulMeme;
    Button btn_kembali;
    //Daftar Judul
    private String[] Judul = {"Bantuan #1", "Bantuan #2", "Bantuan #3", "Bantuan #4", "Bantuan #5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#0FC883"));
        }
        setContentView(R.layout.activity_bantuan);
        JudulMeme = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        btn_kembali = (Button) findViewById(R.id.btn_kembali);
        DaftarItem();
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new BantuanAdapter(JudulMeme);
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(BantuanActivity.this, MainActivity.class);
                startActivity(main);
            }
        });
    }

    //Mengambil data dari Varibale Gambar dan Judul, lalu memasangnya pada list yang terhubung dengan Class Adapter
    private void DaftarItem(){
        for (int w=0; w<Judul.length; w++){
            JudulMeme.add(Judul[w]);
        }
    }
}