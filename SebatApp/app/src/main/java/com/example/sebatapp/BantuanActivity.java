package com.example.sebatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sebatapp.Adapter.BantuanAdapter;

import java.util.ArrayList;

public class BantuanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> JudulMeme;
    //Daftar Judul
    private String[] Judul = {"Bantuan #1", "Bantuan #2", "Bantuan #3", "Bantuan #4", "Bantuan #5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);
        JudulMeme = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        DaftarItem();
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new BantuanAdapter(JudulMeme);
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);
    }

    //Mengambil data dari Varibale Gambar dan Judul, lalu memasangnya pada list yang terhubung dengan Class Adapter
    private void DaftarItem(){
        for (int w=0; w<Judul.length; w++){
            JudulMeme.add(Judul[w]);
        }
    }
}