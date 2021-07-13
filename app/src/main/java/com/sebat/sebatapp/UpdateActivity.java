package com.sebat.sebatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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

public class UpdateActivity extends AppCompatActivity{
    EditText id_pinjam, nama_peminjam, status;
    Button btn_kembali, btn_update, btn_delete;
    Switch switch1;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_id = data.getStringExtra("id_pinjam");
        String intent_nama = data.getStringExtra("nama_peminjam");
        String intent_status = data.getStringExtra("status");
        /*end get data from intent*/


        id_pinjam = (EditText) findViewById(R.id.inp_id);
        nama_peminjam = (EditText) findViewById(R.id.inp_nama);
        status = (EditText) findViewById(R.id.inp_status);
        btn_kembali = (Button) findViewById(R.id.btn_kembali);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        pd = new ProgressDialog(UpdateActivity.this);

        if(update == 1) {

            btn_update.setText("Update Data");
            id_pinjam.setText(intent_id);
            nama_peminjam.setText(intent_nama);
            status.setText(intent_status);

        }
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatus();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }


    private void updateStatus() {
        pd.setMessage("Selesai");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();
                try {
                    JSONObject res = new JSONObject(response);
                    Toast.makeText(UpdateActivity.this, "pesan : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(UpdateActivity.this, RiwayatActivity.class));
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(UpdateActivity.this, "pesan : Gagal Update", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_pinjam", id_pinjam.getText().toString());
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }



    private void delete(){
        pd.setMessage("Delete Data ...");
        pd.setCancelable(false);
        pd.show();

        StringRequest delReq = new StringRequest(Request.Method.POST,ServerAPI.URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(UpdateActivity.this,"pesan : " +res.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(UpdateActivity.this,RiwayatActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_pinjam", id_pinjam.getText().toString());
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(delReq);
    }
}
