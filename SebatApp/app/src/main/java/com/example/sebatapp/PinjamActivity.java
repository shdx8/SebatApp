package com.example.sebatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sebatapp.Util.AppController;
import com.example.sebatapp.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PinjamActivity extends AppCompatActivity {
    EditText nama_peminjam, no_hp;
    RadioGroup radio_kabel, radio_1;
    Button btn_batal,btn_pinjam;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update", 0);
        String intent_nama = data.getStringExtra("nama_peminjam");
        String intent_no = data.getStringExtra("no_hp");
        String intent_kabel = data.getStringExtra("kabel");
        String intent_durasi = data.getStringExtra("total");
        /*end get data from intent*/

        //MENGMBIL INPUTAN DARI activity_pinjam
        nama_peminjam = (EditText) findViewById(R.id.inp_nama_peminjam);
        no_hp = (EditText) findViewById(R.id.inp_no_hp);
        radio_kabel = (RadioGroup) findViewById(R.id.radio_kabel);
        radio_1 = (RadioGroup) findViewById(R.id.radio_1);
        btn_batal = (Button) findViewById(R.id.btn_batal);
        btn_pinjam = (Button) findViewById(R.id.btn_pinjam);
        pd = new ProgressDialog(PinjamActivity.this);



        if(update == 1)
        {
            btn_pinjam.setText("Update Data");
            nama_peminjam.setText(intent_nama);
            nama_peminjam.setText(intent_nama);
        }
    btn_pinjam.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(update == 1)
            {
                Update_data();
            }else {
                simpanData();
            }
        }
    });

        btn_batal.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent main = new Intent(PinjamActivity.this,RiwayatActivity.class);
            startActivity(main);
        }
    });
}

    private void Update_data(){
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(PinjamActivity.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(PinjamActivity.this,RiwayatActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(PinjamActivity.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nama_peminjam",nama_peminjam.getText().toString());
                map.put("no_hp",no_hp.getText().toString());
                map.put("kabel",radio_kabel.getCheckedRadioButtonId().toString());
                map.put("total",radio_1.getCheckedRadioButtonId().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }

    private void simpanData() {
        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(PinjamActivity.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(PinjamActivity.this,RiwayatActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(PinjamActivity.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nama_peminjam",nama_peminjam.getText().toString());
                map.put("no_hp",no_hp.getText().toString());
                map.put("kabel",radio_kabel.getText().toString());
                map.put("total",radio_1.getText().toString());
                //LAST EDIT
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}