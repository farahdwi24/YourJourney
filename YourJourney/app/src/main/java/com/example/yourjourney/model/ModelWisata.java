package com.example.yourjourney.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelWisata implements Serializable {
    @SerializedName("id")
    private String idWisata;

    @SerializedName("nama")
    private  String txtNamaWisata;

    @SerializedName("gambar_url")
    private String GambarWisata;

    @SerializedName("kategori")
    private  String KategoriWisata;
    @SerializedName("deskripsi")
    private  String Deskripsi;

    public String getIdWisata() {
        return idWisata;
    }

    public void setIdWisata(String idWisata) {
        this.idWisata = idWisata;
    }

    public String getTxtNamaWisata() {
        return txtNamaWisata;
    }

    public void setTxtNamaWisata(String txtNamaWisata) {
        this.txtNamaWisata = txtNamaWisata;
    }

    public String getGambarWisata() {
        return GambarWisata;
    }

    public void setGambarWisata(String gambarWisata) {
        GambarWisata = gambarWisata;
    }

    public String getKategoriWisata() {
        return KategoriWisata;
    }

    public void setKategoriWisata(String kategoriWisata) {
        KategoriWisata = kategoriWisata;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }
}
