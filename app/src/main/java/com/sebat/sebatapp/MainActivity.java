package com.sebat.sebatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.sebat.sebatapp.Adapter.DashboardAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<SetterGetter> datamenu;
    GridLayoutManager gridLayoutManager;
    DashboardAdapter adapter;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#0FC883"));
        }
        setContentView(R.layout.activity_main);
        //sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.rv_menu);
        addData();
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new DashboardAdapter(datamenu);
        recyclerView.setAdapter(adapter);

        logout = (Button) findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
    public void addData(){
        datamenu = new ArrayList<>();
        datamenu.add(new SetterGetter("Dashboard", "logomenu1"));
        datamenu.add(new SetterGetter("Pinjam", "logomenu2"));
        datamenu.add(new SetterGetter("Riwayat", "logomenu3"));
        datamenu.add(new SetterGetter("Bantuan", "logomenu4"));
    }
}