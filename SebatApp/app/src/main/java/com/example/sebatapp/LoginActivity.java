package com.example.sebatapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sebatapp.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

        // Parameter sesuai dengan tipe data
        EditText txt_user, txt_pass;
        Button button;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            // Deklarasi komponen view
            txt_user = (EditText) findViewById(R.id.txt_username);
            txt_pass = (EditText) findViewById(R.id.txt_password);
            button = (Button) findViewById(R.id.btn_login);
            // Setting button ketika di klik
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Panggil void sendLogin()
                    sendLogin();
                }
            });
        }
        private void sendLogin() {
            // Setting POST request ke server
            StringRequest loginRequest = new StringRequest(Request.Method.POST, ServerAPI.URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Handle response dari server ketika sukses dengan mengkonvert menjadi JSON
                            try {
                                JSONObject json = new JSONObject(response);
                                // Mengambil variable status pada response
                                String status = json.getString("status");
                                if(status.equals("success")){
                                    // Jika Login Sukses Maka pindah ke activity lain.
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    // Jika Login Gagal Maka mengeluarkan Toast dengan message.
                                    Toast.makeText(getApplicationContext(), "Username & Password Salah", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle response dari server ketika gagal
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("username", username.getText().toString());
                    params.put("password", password.getText().toString());
                    return params;
                }
            };
            // Buat antrian request pada cache android
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            // Tambahkan Request pada antrian request
            requestQueue.add(loginRequest);
        }
    }