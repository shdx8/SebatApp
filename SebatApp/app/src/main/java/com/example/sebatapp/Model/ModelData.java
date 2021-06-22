package com.example.sebatapp.Model;

public class ModelData {String nama_peminjam, total, tgl_pinjam, status;
    public ModelData(){}

    public ModelData(String nama_peminjam, String total, String tgl_pinjam, String status) {
        this.nama_peminjam = nama_peminjam;
        this.total = total;
        this.tgl_pinjam = tgl_pinjam;
        this.status = status;
    }

    //terhubung dengan Adapter Data
    public String getNama_peminjam() {
        return nama_peminjam;
    }
    //terhubung dengan RiwayatActivity
    public void setNama_peminjam(String nama_peminjam) {
        this.nama_peminjam = nama_peminjam;
    }

    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }

    public String getTgl_pinjam() {
        return tgl_pinjam;
    }
    public void setTgl_pinjam(String tgl_pinjam) {
        this.tgl_pinjam = tgl_pinjam;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String grup) {
        this.status = grup;
    }
}