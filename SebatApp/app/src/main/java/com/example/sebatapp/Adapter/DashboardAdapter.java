package com.example.sebatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sebatapp.BantuanActivity;
import com.example.sebatapp.DashboardActivity;
import com.example.sebatapp.LoginActivity;
import com.example.sebatapp.MainActivity;
import com.example.sebatapp.PinjamActivity;
import com.example.sebatapp.R;
import com.example.sebatapp.RiwayatActivity;
import com.example.sebatapp.SetterGetter;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardHolder> {
    private ArrayList<SetterGetter> listdata;

    public DashboardAdapter(ArrayList<SetterGetter>listdata){
        this.listdata = listdata;
    }
    @NonNull
    @Override
    public DashboardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard,parent, false);
        DashboardHolder holder = new DashboardHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardHolder holder, int position) {
        final SetterGetter getData = listdata.get(position);
        String titlemenu = getData.getTitle();
        String logomenu = getData.getImg();
        holder.titleMenu.setText(titlemenu);
        if(logomenu.equals("logomenu1")) {
            holder.imgMenu.setImageResource(R.drawable.dashboard);
        } else if (logomenu.equals("logomenu2")) {
            holder.imgMenu.setImageResource(R.drawable.pinjam);
        } else if (logomenu.equals("logomenu3")) {
            holder.imgMenu.setImageResource(R.drawable.history);
        } else if (logomenu.equals("logomenu4")) {
            holder.imgMenu.setImageResource(R.drawable.info);
        }
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class DashboardHolder extends RecyclerView.ViewHolder {
        TextView titleMenu;
        ImageView imgMenu;
        LinearLayout DsbList;
        Context context;

        public DashboardHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            titleMenu = itemView.findViewById(R.id.menu_tambah);
            imgMenu = itemView.findViewById(R.id.logo_menu);
            DsbList = itemView.findViewById(R.id.item_dsb);
            DsbList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    switch (getAdapterPosition()) {
                        case 0:
                            intent = new Intent(context, DashboardActivity.class);
                            break;
                        case 1:
                            intent = new Intent(context, PinjamActivity.class);
                            break;
                        case 2:
                            intent = new Intent(context, RiwayatActivity.class);
                            break;
                        case 3:
                            intent = new Intent(context, BantuanActivity.class);
                            break;
                    }
                    context.startActivity(intent);
                }
            });
        }
    }
}