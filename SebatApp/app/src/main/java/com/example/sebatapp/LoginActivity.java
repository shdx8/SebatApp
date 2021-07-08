package com.example.sebatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;
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
import com.example.sebatapp.Util.AppController;
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
        ProgressDialog pDialog;
        EditText txt_user, txt_pass;
        Button button_login;

        int success;
        ConnectivityManager conMgr;
        private String url = ServerAPI.URL_LOGIN + "login.php";

        private static final String TAG = LoginActivity.class.getSimpleName();

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";

        public final static String TAG_USERNAME = "username";

        String tag_json_obj = "json_obj_req";

        SharedPreferences sharedpreferences;
        Boolean session = false;
        String username;
        public static final String my_shared_preferences = "my_shared_preferences";
        public static final String session_status = "session_status";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            {
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection",
                            Toast.LENGTH_LONG).show();
                }
            }
            // Deklarasi komponen view
            txt_user = (EditText) findViewById(R.id.txt_username);
            txt_pass = (EditText) findViewById(R.id.txt_password);
            button_login = (Button) findViewById(R.id.btn_login);
            // Setting button ketika di klik

            // Cek session login jika TRUE maka langsung buka MainActivity
            sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
            session = sharedpreferences.getBoolean(session_status, false);
            username = sharedpreferences.getString(TAG_USERNAME, null);

            if (session) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra(TAG_USERNAME, username);
                finish();
                startActivity(intent);
            }

            button_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = txt_user.getText().toString();
                    String password = txt_pass.getText().toString();

                    // mengecek kolom yang kosong
                    if (username.trim().length() > 0 && password.trim().length() > 0) {
                        if (conMgr.getActiveNetworkInfo() != null
                                && conMgr.getActiveNetworkInfo().isAvailable()
                                && conMgr.getActiveNetworkInfo().isConnected()) {
                            checkLogin(username, password);
                        } else {
                            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        // Prompt user to enter credentials
                        Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
            private void checkLogin (final String username, final String password) {
                pDialog = new ProgressDialog(this);
                pDialog.setCancelable(false);
                pDialog.setMessage("Logging in ...");
                showDialog();

                StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Login Response: " + response.toString());
                        hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);

                            // Check for error node in json
                            if (success == 1) {
                                String username = jObj.getString(TAG_USERNAME);

                                Log.e("Successfully Login!", jObj.toString());

                                Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                                // menyimpan login ke session
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putBoolean(session_status, true);
                                editor.putString(TAG_USERNAME, username);
                                editor.commit();

                                // Memanggil main activity
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra(TAG_USERNAME, username);
                                finish();
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Login Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();

                        hideDialog();

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        // Posting parameters to login url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", username);
                        params.put("password", password);

                        return params;
                    }

                };

                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
            }

            private void showDialog() {
                if (!pDialog.isShowing())
                    pDialog.show();
            }

            private void hideDialog() {
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        }