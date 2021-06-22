package com.example.sebatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sebatapp.PinjamActivity;
import com.example.sebatapp.Model.ModelData;
import com.example.sebatapp.R;

import java.util.List;

//ADAPTER DATA UNTUK INSERT DATA
public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems ;
    private Context context;

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
        holder.tvnama_peminjam.setText(md.getNama_peminjam());
        holder.tvtotal.setText(md.getTotal());
        holder.tvtgl_pinjam.setText(md.getTgl_pinjam());
        holder.tvstatus.setText(md.getStatus());
        holder.md = md;


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvnama_peminjam,tvtotal,tvtgl_pinjam,tvstatus;
        ModelData md;

        public  HolderData (View view)
        {
            super(view);
            tvnama_peminjam = (TextView) view.findViewById(R.id.nama_peminjam);
            tvtotal = (TextView) view.findViewById(R.id.total);
            tvtgl_pinjam = (TextView) view.findViewById(R.id.tgl_pinjam);
            tvstatus = (TextView) view.findViewById(R.id.status);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, PinjamActivity.class);
                    update.putExtra("update",1);
                    update.putExtra("Nama Peminjam",md.getNama_peminjam());
                    update.putExtra("Total",md.getTotal());
                    update.putExtra("Tanggal Order",md.getTgl_pinjam());
                    update.putExtra("Status",md.getStatus());

                    context.startActivity(update);
                }
            });
        }
    }
}