package com.example.yourjourney.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelKuliner implements Serializable {
    @SerializedName("id")
    private String idKuliner;
    @SerializedName("nama")
    private String txtNamaKuliner;
    @SerializedName("alamat")
    private String txtAlamatKuliner;
    @SerializedName("jam_buka_tutup")
    private String txtOpenTime;
    @SerializedName("kordinat")
    private String Koordinat;
    @SerializedName("gambar_url")
    private String GambarKuliner;
    @SerializedName("kategori")
    private String KategoriKuliner;
    @SerializedName("nomor_telp")
    private String phoneKuliner;
    @SerializedName("deskripsi")
    private String deskripsi;

    public String getIdKuliner() {
        return idKuliner;
    }

    public void setIdKuliner(String idKuliner) {
        this.idKuliner = idKuliner;
    }

    public String getTxtNamaKuliner() {
        return txtNamaKuliner;
    }

    public void setTxtNamaKuliner(String txtNamaKuliner) {
        this.txtNamaKuliner = txtNamaKuliner;
    }

    public String getTxtAlamatKuliner() {
        return txtAlamatKuliner;
    }

    public void setTxtAlamatKuliner(String txtAlamatKuliner) {
        this.txtAlamatKuliner = txtAlamatKuliner;
    }

    public String getTxtOpenTime() {
        return txtOpenTime;
    }

    public void setTxtOpenTime(String txtOpenTime) {
        this.txtOpenTime = txtOpenTime;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.Koordinat = koordinat;
    }

    public String getGambarKuliner() {
        return GambarKuliner;
    }

    public void setGambarKuliner(String gambarKuliner) {
        this.GambarKuliner = gambarKuliner;
    }

    public String getKategoriKuliner() {
        return KategoriKuliner;
    }

    public void setKategoriKuliner(String kategoriKuliner) {
        this.KategoriKuliner = kategoriKuliner;
    }

    public String getPhoneKuliner() {
        return phoneKuliner;
    }

    public void setPhoneKuliner(String phoneKuliner) {
        this.phoneKuliner = phoneKuliner;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
