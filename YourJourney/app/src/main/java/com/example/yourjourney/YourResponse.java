package com.example.yourjourney;


import com.example.yourjourney.model.ModelHotel;
import com.example.yourjourney.model.ModelKuliner;
import com.example.yourjourney.model.ModelPrayPlace;
import com.example.yourjourney.model.ModelWisata;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YourResponse {
    @SerializedName("hotel")
    private List<ModelHotel> hotelList;

    public List<ModelHotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList (List<ModelHotel> hotelList) {
        this.hotelList = hotelList;
    }

    @SerializedName("kuliner")
    private List<ModelKuliner> kulinerList;

    public List<ModelKuliner> getKulinerList() {
        return kulinerList;
    }

    public void setKulinerList (List<ModelKuliner> kulinerList) {
        this.kulinerList = kulinerList;
    }

    @SerializedName("tempat_ibadah")
    private List<ModelPrayPlace> prayPlaceList;

    public List<ModelPrayPlace> getPrayPlaceList() {
        return prayPlaceList;
    }

    public void setPrayPlaceList(List<ModelPrayPlace> prayPlaceList) {
        this.prayPlaceList = prayPlaceList;
    }

    @SerializedName("wisata")
    private List<ModelWisata> wisataList;

    public List<ModelWisata> getWisataList() {
        return wisataList;
    }

    public void setWisataList(List<ModelWisata> wisataList) {
        this.wisataList = wisataList;
    }
}
