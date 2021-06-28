package com.example.sebatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
    RadioGroup radio_kabel, radio_1, radio_2, radio_3;
    Button btn_batal,btn_pinjam;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update", 0);
        String intent_username = data.getStringExtra("username");
        String intent_grup = data.getStringExtra("grup");
        String intent_nama = data.getStringExtra("nama");
        String intent_password = data.getStringExtra("password");
        /*end get data from intent*/

        //MENGMBIL INPUTAN DARI activity_pinjam
        nama_peminjam = (EditText) findViewById(R.id.inp_nama_peminjam);
        no_hp = (EditText) findViewById(R.id.inp_no_hp);
        radio_kabel = (RadioGroup) findViewById(R.id.radio_kabel);
        radio_1 = (RadioGroup) findViewById(R.id.radio_1);
        radio_2 = (RadioGroup) findViewById(R.id.radio_2);
        radio_3 = (RadioGroup) findViewById(R.id.radio_3);
        btn_batal = (Button) findViewById(R.id.btn_batal);
        btn_pinjam = (Button) findViewById(R.id.btn_pinjam);
        pd = new ProgressDialog(PinjamActivity.this);
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
                map.put("Nama Peminjam",nama_peminjam.getText().toString());
                map.put("No HP",no_hp.getText().toString());

                //LAST EDIT
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}