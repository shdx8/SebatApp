package com.example.sebatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
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
import com.example.sebatapp.RiwayatActivity;

public class PinjamActivity extends AppCompatActivity {
    EditText nama_peminjam, no_hp;
    RadioGroup radio_kabel, radio_durasi;
    RadioButton radioButton;
    RadioButton inp_30, inp_60, inp_90, inp_2, inp_5, inp_24, inp_lightning, inp_micro, inp_typec;
    Button btn_batal, btn_pinjam;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update", 0);
        String intent_nama = data.getStringExtra("nama_peminjam");
        String intent_no_hp = data.getStringExtra("no_hp");
        String intent_lightning = data.getStringExtra("kabel");
        String intent_micro = data.getStringExtra("kabel");
        String intent_typec = data.getStringExtra("kabel");
        String intent_inp_30 = data.getStringExtra("total");
        String intent_inp_2 = data.getStringExtra("total");
        String intent_inp_60 = data.getStringExtra("total");
        String intent_inp5 = data.getStringExtra("total");
        String intent_inp_90 = data.getStringExtra("total");
        String intent_inp_24 = data.getStringExtra("total");
        //String intent_status = data.getStringExtra("status");
        /*end get data from intent*/

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

        //radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangedListener() {

          //  @Override
            //public void onCheckedChanged(RadioGroup group, int checkedId) {
        //<type>value = <default value>;
                //switch (checkedId) {
                    //case R.id.radioButton1:
                        //value = ...;
                        //break;
                    //case R.id.radioButton2:
                        //value = ...;
                        //break;
                //}
                // do something with value
            //}
        //});

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



        if(update == 1)
        {
            btn_pinjam.setText("Update Data");
            nama_peminjam.setText(intent_nama);
            no_hp.setText(intent_no);
            inp_lightning.setText(intent_kabel);
            inp_30.setText(intent_durasi);

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
                //map.put("kabel",radio_kabel.getCheckedRadioButtonId().toString());
                //map.put("total",radio_1.getCheckedRadioButtonId().toString());
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);

        btn_batal = (Button) findViewById(R.id.btn_batal);
        btn_pinjam = (Button) findViewById(R.id.btn_pinjam);
        pd = new ProgressDialog(PinjamActivity.this);


        if (update == 1) {
            btn_pinjam.setText("Update Data");
            nama_peminjam.setText(intent_nama);
            no_hp.setVisibility(View.GONE);
        }
        btn_pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update == 1) {
                    Update_data();
                } else {
                    simpanData();
                }
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

    private void Update_data() {
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
                 Map<String,String> map = new HashMap<>();
                map.put("nama_peminjam",nama_peminjam.getText().toString());
                map.put("no_hp",no_hp.getText().toString());
                
                map.put("kabel",inp_lightning.getText().toString());
                map.put("total",inp_30.getText().toString());
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
                Map<String,String> map = new HashMap<>();
                map.put("nama_peminjam",nama_peminjam.getText().toString());
                map.put("no_hp",no_hp.getText().toString());
                map.put("kabel",inp_lightning.getText().toString());
                map.put("total",inp_30.getText().toString());
                //LAST EDIT
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }


}