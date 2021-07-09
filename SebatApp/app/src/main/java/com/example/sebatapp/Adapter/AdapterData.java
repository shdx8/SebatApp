package com.example.sebatapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sebatapp.Model.ModelData;
import com.example.sebatapp.PinjamActivity;
import com.example.sebatapp.R;
import com.example.sebatapp.RiwayatActivity;

import java.util.List;

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
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md  = mItems.get(position);
        holder.tvId_pinjam.setText(md.getId_pinjam());
        holder.tvnama_peminjam.setText(md.getNama_peminjam());
        holder.tvno_hp.setText(md.getNo_hp());
        holder.tvkabel.setText(md.getKabel());
        holder.tvtotal.setText(md.getTotal());
        holder.tvtgl_pinjam.setText(md.getTgl_pinjam());
        holder.tvstatus.setText(md.getStatus());

        //final long id_pinjam = mItems.getJSONObject(position).getInt("id_pinjam");
        holder.tvoptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        return false;
                    }
                });
                popup.show();
            }
        });
        holder.md = md;


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvId_pinjam, tvnama_peminjam, tvno_hp, tvkabel,tvtotal,tvtgl_pinjam,tvstatus, tvoptions;
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
            tvoptions = (TextView) view.findViewById(R.id.textViewOptions);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, PinjamActivity.class);
                    update.putExtra("Status",md.getStatus());

                    context.startActivity(update);
                }
            });
        }
    }
}