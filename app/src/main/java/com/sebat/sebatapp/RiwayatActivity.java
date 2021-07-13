package com.sebat.sebatapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sebat.sebatapp.Adapter.AdapterData;
import com.sebat.sebatapp.Model.ModelData;
import com.sebat.sebatapp.Util.AppController;
import com.sebat.sebatapp.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiwayatActivity extends AppCompatActivity {
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    ProgressDialog pd;
    PopupMenu.OnMenuItemClickListener deleteID;
    ImageButton done, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerviewTemp);
        pd = new ProgressDialog(RiwayatActivity.this);
        mItems = new ArrayList<>();
//        done = (ImageButton) findViewById(R.id.done);
//        delete = (ImageButton) findViewById(R.id.delete);

        loadJson();

        mManager = new LinearLayoutManager(RiwayatActivity.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterData(RiwayatActivity.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

    }

    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData md = new ModelData();
                                //memanggil set pada Model Data
                                md.setId_pinjam(data.getString("id_pinjam"));
                                md.setNama_peminjam(data.getString("nama_peminjam"));
                                md.setNo_hp(data.getString("no_hp"));
                                md.setKabel(data.getString("kabel"));
                                md.setTotal(data.getString("total"));
                                md.setTgl_pinjam(data.getString("tgl_pinjam"));
                                md.setStatus(data.getString("status"));
                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Length:"+response.length());
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }

    public void deleteData() {
        if (pd != null){
            pd.setMessage("Delete Data ...");
            pd.setCancelable(false);
            pd.show();
        }
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest delReq = new StringRequest(Request.Method.POST,
                ServerAPI.URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        Log.d("volley", "response :" + response.toString());
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(RiwayatActivity.this, "pesan:" + res.getString("message"),
                                    Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        loadJson();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error:" + error.getMessage());
                        Toast.makeText(RiwayatActivity.this, "pesan : gagal menghapus data",
                                Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_pinjam", deleteID.toString());
                //map.put("id_pinjam", mAdapter.getId_pinjam().getText(), deleteID);
                return map;
            }
        };
        queue.add(delReq);
    }


}