package com.sebat.sebatapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sebat.sebatapp.Adapter.DashboardAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
public class DashboardActivity extends AppCompatActivity {
    Button btn_kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#0FC883"));
        }
        setContentView(R.layout.activity_dashboard);
        drawLineChart();

        btn_kembali = (Button) findViewById(R.id.btn_kembali);
        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(main);
            }
        });
    }

    private void drawLineChart() {
        LineChart lineChart = findViewById(R.id.lineChart);
        List<Entry> lineEntries = getDataSet();
        LineDataSet lineDataSet = new LineDataSet(lineEntries, getString(R.string.oil_price));
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.DKGRAY);

        LineData lineData = new LineData(lineDataSet);
        lineChart.getDescription().setText(getString(R.string.price_in_last_12_days));
        lineChart.getDescription().setTextSize(12);
        lineChart.setDrawMarkers(true);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        lineChart.animateY(1000);
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(1.0f);
        lineChart.getXAxis().setLabelCount(lineDataSet.getEntryCount());
        lineChart.setData(lineData);
    }

    private List<Entry> getDataSet() {
        List<Entry> lineEntries = new ArrayList<Entry>();
        lineEntries.add(new Entry(0, 1));
        lineEntries.add(new Entry(1, 2));
        lineEntries.add(new Entry(2, 3));
        lineEntries.add(new Entry(3, 4));
        lineEntries.add(new Entry(4, 2));
        lineEntries.add(new Entry(5, 3));
        lineEntries.add(new Entry(6, 1));
        lineEntries.add(new Entry(7, 5));
        lineEntries.add(new Entry(8, 7));
        lineEntries.add(new Entry(9, 6));
        lineEntries.add(new Entry(10, 4));
        lineEntries.add(new Entry(11, 5));
        return lineEntries;
    }
}