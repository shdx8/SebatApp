package com.example.sebatapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sebatapp.Model.ModelData;
import com.example.sebatapp.PinjamActivity;
import com.example.sebatapp.R;
import com.example.sebatapp.RiwayatActivity;
import com.example.sebatapp.UpdateActivity;
import com.example.sebatapp.Util.AppController;
import com.example.sebatapp.Util.ServerAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//ADAPTER DATA UNTUK INSERT DATA
public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems ;
    private Context context;
    private ProgressDialog pd;

    public AdapterData (Context context, List<ModelData> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, final int position) {
        ModelData md  = mItems.get(position);
        holder.tvId_pinjam.setText(md.getId_pinjam());
        holder.tvnama_peminjam.setText(md.getNama_peminjam());
        holder.tvno_hp.setText(md.getNo_hp());
        holder.tvkabel.setText(md.getKabel());
        holder.tvtotal.setText(md.getTotal());
        holder.tvtgl_pinjam.setText(md.getTgl_pinjam());
        holder.tvstatus.setText(md.getStatus());
//        holder.done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String id = mItems.get(position).getId_pinjam();
//                String value = mItems.get(position).getNama_peminjam();
//                done();
//            }
//        });

        holder.md = md;
    }

//    private void done() {
//
//    }
    //final long id_pinjam = mItems.getJSONObject(position).getInt("id_pinjam");
        /*holder.tvoptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelData md  = mItems.get(position);
                PopupMenu popup = new PopupMenu(context, holder.tvoptions);
                popup.inflate(R.menu.options_riwayat);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.selesai :
                                // belum ada action selesai
                                break;
                            case R.id.hapus:
                                // panggil method Delete disini
                                RiwayatActivity riwayat = new RiwayatActivity();
                                riwayat.deleteData(md.getId_pinjam());
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        }); */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_riwayat, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.update:
//                newGame();
//                return true;
//            case R.id.Id_pinjam:
//                deleteData();
//                RiwayatActivity riwayat = new RiwayatActivity();
//                riwayat.deleteData(riwayat.getText('id_pinjam'));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvId_pinjam, tvnama_peminjam, tvno_hp, tvkabel,tvtotal,tvtgl_pinjam,tvstatus, tvoptions;
        ImageButton done, delete;
        ModelData md;

        public  HolderData (View view)
        {
            super(view);
            tvId_pinjam = (TextView) view.findViewById(R.id.Id_pinjam);
            tvnama_peminjam = (TextView) view.findViewById(R.id.nama_peminjam);
            tvno_hp = (TextView) view.findViewById(R.id.no_hp);
            tvkabel = (TextView) view.findViewById(R.id.kabel);
            tvtotal = (TextView) view.findViewById(R.id.total);
            tvtgl_pinjam = (TextView) view.findViewById(R.id.tgl_pinjam);
            tvstatus = (TextView) view.findViewById(R.id.status);
//            done = (ImageButton) view.findViewById(R.id.done);
//            delete = (ImageButton) view.findViewById(R.id.delete);
            //tvoptions = (TextView) view.findViewById(R.id.textViewOptions);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, UpdateActivity.class);
                    update.putExtra("update",1);
                    update.putExtra("id_pinjam",md.getId_pinjam());
                    update.putExtra("nama_peminjam",md.getNama_peminjam());
                    update.putExtra("status",md.getStatus());

                    context.startActivity(update);
                }
            });
        }
//        private void serverdelete(final int id){
//
//            StringRequest stringRequest2 = new StringRequest(Request.Method.POST, ServerAPI.URL_DATA, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//
//                    Toast.makeText(RiwayatActivity.this, response, Toast.LENGTH_SHORT).show();
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                    Toast.makeText(Ad, error.toString(), Toast.LENGTH_SHORT).show();
//
//                }
//            }){
//
//                protected Map<String, String> getParams(){
//
//                    Map<String, String> params = new HashMap<>();
//
//                    params.put("id_pinjam", String.valueOf(id));
//
//                    return params;
//
//                }
//
//            };
//
//            AppController.getInstance().addToRequestQueue(stringRequest2);
//        }
    }}