package com.sebat.sebatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sebat.sebatapp.BantuanActivity;
import com.sebat.sebatapp.DashboardActivity;
import com.sebat.sebatapp.PinjamActivity;
import com.sebat.sebatapp.R;
import com.sebat.sebatapp.RiwayatActivity;

import java.util.ArrayList;

public class DsbAdapter extends RecyclerView.Adapter<DsbAdapter.ViewHolder>{

    private ArrayList<String> arrayList; //Digunakan untuk Judul
    private ArrayList<Integer> memeList; //Digunakan untuk Image/Gambar

    DsbAdapter(ArrayList<String> arrayList, ArrayList<Integer> memeList){
        this.arrayList = arrayList;
        this.memeList = memeList;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView MenuTambah;
        private ImageView LogoMenu;
        private RelativeLayout ItemList;
        private Context context;

        ViewHolder(View itemView) {
            super(itemView);

            //Untuk Menghubungkan dan Mendapakan Context dari MainActivity
            context = itemView.getContext();

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            MenuTambah = itemView.findViewById(R.id.menu_tambah);
            LogoMenu = itemView.findViewById(R.id.logo_menu);
            ItemList = itemView.findViewById(R.id.item_dsb);
            ItemList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    switch (getAdapterPosition()){
                        case 0 :
                            intent = new Intent(context, DashboardActivity.class);
                            break;
                        case 1 :
                            intent = new Intent(context, PinjamActivity.class);
                            break;
                        case 2 :
                            intent = new Intent(context, RiwayatActivity.class);
                            break;
                        case 3 :
                            intent = new Intent(context, BantuanActivity.class);
                            break;
                    }
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard, parent, false);
        ViewHolder VH = new ViewHolder(V);
        return VH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Memasukan Nilai/Value Pada View-View Yang Telah Dibuat
        final String Nama = arrayList.get(position);//Mengambil data sesuai dengan posisi yang telah ditentukan
        holder.MenuTambah.setText(Nama);
        holder.LogoMenu.setImageResource(memeList.get(position)); // Mengambil gambar sesuai posisi yang telah ditentukan
    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return arrayList.size();
    }

}
