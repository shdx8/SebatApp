package com.example.sebatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sebatapp.Adapter.DashboardAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<SetterGetter> datamenu;
    GridLayoutManager gridLayoutManager;
    DashboardAdapter adapter;
    TextView user;
    String username;
    SharedPreferences sharedpreferences;

    public static final String TAG_USERNAME = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (TextView) findViewById(R.id.halo_user);
        //sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);
        user.setText("Halo " + username);

        recyclerView = findViewById(R.id.rv_menu);
        addData();
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new DashboardAdapter(datamenu);
        recyclerView.setAdapter(adapter);
    }
    public void addData(){
        datamenu = new ArrayList<>();
        datamenu.add(new SetterGetter("Dashboard", "logomenu1"));
        datamenu.add(new SetterGetter("Pinjam", "logomenu2"));
        datamenu.add(new SetterGetter("Riwayat", "logomenu3"));
        datamenu.add(new SetterGetter("Bantuan", "logomenu4"));
    }
}