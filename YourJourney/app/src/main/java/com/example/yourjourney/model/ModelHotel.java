package com.example.yourjourney.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelHotel implements Serializable {
    @SerializedName("nama")
    private String txtNamaHotel;
    @SerializedName("alamat")
    private String txtAlamatHotel;
    @SerializedName("nomor_telp")
    private String txtNoTelp;
    @SerializedName("kordinat")
    private String Koordinat ;
    @SerializedName("gambar_url")
    private String GambarHotel;

    public String getTxtNamaHotel() {
        return txtNamaHotel;
    }

    public void setTxtNamaHotel(String txtNamaHotel) {
        this.txtNamaHotel = txtNamaHotel;
    }

    public String getTxtAlamatHotel() {
        return txtAlamatHotel;
    }

    public void setTxtAlamatHotel(String txtAlamatHotel) {
        this.txtAlamatHotel = txtAlamatHotel;
    }

    public String getTxtNoTelp() {
        return txtNoTelp;
    }

    public void setTxtNoTelp(String txtNoTelp) {
        this.txtNoTelp = txtNoTelp;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.Koordinat = koordinat;
    }

    public String getGambarHotel() {
        return GambarHotel;
    }

    public void setGambarHotel(String gambarHotel) {
        this.GambarHotel = gambarHotel;
    }
}
