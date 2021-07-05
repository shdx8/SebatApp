package com.example.sebatapp.Model;

public class ModelData {String nama_peminjam, no_hp, kabel, total, tgl_pinjam, status;
    public ModelData(){}

    public ModelData(String nama_peminjam, String no_hp, String kabel, String total, String tgl_pinjam, String status) {
        this.nama_peminjam = nama_peminjam;
        this.no_hp = no_hp;
        this.kabel = kabel;
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

    public String getNo_hp() {
        return no_hp;
    }
    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getKabel() {
        return kabel;
    }
    public void setKabel(String kabel) { this.kabel = kabel;
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
    public void setStatus(String status) {
        this.status = status;
    }
}