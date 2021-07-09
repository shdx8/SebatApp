package com.example.sebatapp.Model;

public class ModelData {String id_pinjam, nama_peminjam, no_hp, kabel, total, tgl_pinjam, status;
    public ModelData(){}

    public ModelData(String id_pinjam, String nama_peminjam, String no_hp, String kabel, String total,
                     String tgl_pinjam, String status) {
        this.id_pinjam= id_pinjam;
        this.nama_peminjam = nama_peminjam;
        this.no_hp = no_hp;
        this.kabel = kabel;
        this.total = total;
        this.tgl_pinjam = tgl_pinjam;
        this.status = status;
    }

    /*
    *inp_lightning, inp_micro, inp_typec,
        inp_30, inp_2, inp_60, inp_5, inp_90,
    *
    * this.inp_lightning = kabel;
        this.inp_micro = kabel;
        this.inp_typec = kabel;
        this.inp_30 = total;
        this.inp_2 = total;
        this.inp_60 = total;
        this.inp_5 = total;
        this.inp_90 = total;
    *  */

    //terhubung dengan Adapter Data
    public String getId_pinjam() {
        return id_pinjam;
    }
    public void setId_pinjam(String id_pinjam) {
        this.id_pinjam = id_pinjam;
    }

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

   /* public String getKabel_lightning() {
        return inp_lightning;
    }
    public void setKabel_lightning(String kabel) { this.inp_lightning = kabel;
    }

    public String getKabel_micro() {
        return inp_micro;
    }
    public void setKabel_micro(String kabel) { this.inp_micro = kabel;
    }

    public String getKabel_typec() {
        return inp_typec;
    }
    public void setKabel_typec(String kabel) { this.inp_typec = kabel;
    }

    public String getTotal_30() {
        return inp_30;
    }
    public void setTotal_30(String total) {
        this.inp_30 = total;
    }

    public String getTotal_2() {
        return inp_2;
    }
    public void setTotal_2(String total) {
        this.inp_2 = total;
    }

    public String getTotal_60() {
        return inp_60;
    }
    public void setTotal_60(String total) {
        this.inp_60 = total;
    }

    public String getTotal_5() {
        return inp_5;
    }
    public void setTotal_5(String total) {
        this.inp_5 = total;
    }

    public String getTotal_90() {
        return inp_90;
    }
    public void setTotal_90(String total) {
        this.inp_90 = total;
    }

    public String getTotal_24() {
        return inp_24;
    }
    public void setTotal_24(String total) {
        this.inp_24 = total;
    } */

    public String getKabel() {
        return kabel;
    }
    public void setKabel(String kabel) {
        this.kabel = kabel;
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