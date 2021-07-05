package com.example.sebatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sebatapp.Bantuan_1;
import com.example.sebatapp.Bantuan_2;
import com.example.sebatapp.Bantuan_3;
import com.example.sebatapp.Bantuan_4;
import com.example.sebatapp.Bantuan_5;
import com.example.sebatapp.R;

import java.util.ArrayList;

public class BantuanAdapter extends RecyclerView.Adapter<BantuanAdapter.ViewHolder>{

        private ArrayList<String> arrayList; //Digunakan untuk Judul

        public BantuanAdapter(ArrayList<String> arrayList){
            this.arrayList = arrayList;
        }

        //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
        class ViewHolder extends RecyclerView.ViewHolder{

            private TextView JudulMeme;
            private RelativeLayout ItemList;
            private Context context;

            ViewHolder(View itemView) {
                super(itemView);

                //Untuk Menghubungkan dan Mendapakan Context dari MainActivity
                context = itemView.getContext();

                //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
                JudulMeme = itemView.findViewById(R.id.memetitle);
                ItemList = itemView.findViewById(R.id.item_list);
                ItemList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        switch (getAdapterPosition()){
                            case 0 :
                                intent = new Intent(context, Bantuan_1.class);
                                break;
                            case 1 :
                                intent = new Intent(context, Bantuan_2.class);
                                break;
                            case 2 :
                                intent = new Intent(context, Bantuan_3.class);
                                break;
                            case 3 :
                                intent = new Intent(context, Bantuan_4.class);
                                break;
                            case 4 :
                                intent = new Intent(context, Bantuan_5.class);
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
            View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bantuan, parent, false);
            ViewHolder VH = new ViewHolder(V);
            return VH;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            //Memasukan Nilai/Value Pada View-View Yang Telah Dibuat
            final String Nama = arrayList.get(position);//Mengambil data sesuai dengan posisi yang telah ditentukan
            holder.JudulMeme.setText(Nama);
        }

        @Override
        public int getItemCount() {
            //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
            return arrayList.size();
        }

    }
