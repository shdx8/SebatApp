package com.sebat.sebatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sebat.sebatapp.Util.AppController;
import com.sebat.sebatapp.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PinjamActivity extends AppCompatActivity {
    EditText nama_peminjam, no_hp;
    RadioGroup radio_kabel, radio_durasi;
    RadioButton inp_lightning, inp_micro, inp_typec, inp_30, inp_2, inp_60, inp_5, inp_90, inp_24;
    Button btn_batal, btn_pinjam;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam);

        //MENGMBIL INPUTAN DARI activity_pinjam
        nama_peminjam = (EditText) findViewById(R.id.inp_nama_peminjam);
        no_hp = (EditText) findViewById(R.id.inp_no_hp);
        radio_kabel = (RadioGroup) findViewById(R.id.radio_kabel);
        btn_batal = (Button) findViewById(R.id.btn_batal);
        btn_pinjam = (Button) findViewById(R.id.btn_pinjam);
        pd = new ProgressDialog(PinjamActivity.this);
        radio_durasi = (RadioGroup) findViewById(R.id.radio_durasi);
        btn_batal = (Button) findViewById(R.id.btn_batal);
        btn_pinjam = (Button) findViewById(R.id.btn_pinjam);
        pd = new ProgressDialog(PinjamActivity.this);

        inp_lightning = (RadioButton) findViewById(R.id.inp_lightning);
        inp_micro = (RadioButton) findViewById(R.id.inp_micro);
        inp_typec = (RadioButton) findViewById(R.id.inp_typec);

        inp_lightning.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inp_lightning.setChecked(true);
                inp_micro.setChecked(false);
                inp_typec.setChecked(false);
            }
        });
        inp_micro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inp_lightning.setChecked(false);
                inp_micro.setChecked(true);
                inp_typec.setChecked(false);
            }
        });
        inp_typec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inp_lightning.setChecked(false);
                inp_micro.setChecked(false);
                inp_typec.setChecked(true);
            }
        });

        int radioButtonId = radio_kabel.getCheckedRadioButtonId();
        String selectedValue;
        switch (radioButtonId){
            case R.id.inp_lightning:
                selectedValue = "Lightning";
                break;
            case R.id.inp_micro:
                selectedValue = "Micro";
                break;
            case R.id.inp_typec:
                selectedValue = "Type-C";
                break;
        }



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



        btn_pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanData();
            }
        });

        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(PinjamActivity.this, RiwayatActivity.class);
                startActivity(main);
            }
        });
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
                    Toast.makeText(PinjamActivity.this, "pesan : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(PinjamActivity.this, RiwayatActivity.class));
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(PinjamActivity.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("nama_peminjam", nama_peminjam.getText().toString());
                map.put("no_hp", no_hp.getText().toString());
                if (inp_lightning.isChecked()) {
                    map.put("kabel", inp_lightning.getText().toString());
                } else if (inp_micro.isChecked()) {
                    map.put("kabel", inp_micro.getText().toString());
                } else if (inp_typec.isChecked()) {
                    map.put(("kabel"), inp_typec.getText().toString());
                }

                if (inp_30.isChecked()) {
                    map.put(("total"), inp_30.getText().toString());
                } else if (inp_2.isChecked()) {
                    map.put(("total"), inp_2.getText().toString());
                } else if (inp_60.isChecked()) {
                    map.put(("total"), inp_60.getText().toString());
                } else if (inp_5.isChecked()) {
                    map.put(("total"), inp_5.getText().toString());
                } else if (inp_90.isChecked()) {
                    map.put(("total"), inp_90.getText().toString());
                } else if (inp_24.isChecked()) {
                    map.put(("total"), inp_24.getText().toString());
                }

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}