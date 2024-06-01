package com.example.yourjourney;


import com.example.yourjourney.model.ModelKuliner;
import com.example.yourjourney.model.ModelWisata;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("wisata")
    Call<YourResponse> getWisata();

    @GET("wisata/{id}")
    Call<ModelWisata> getWisataDetail(@Path("id") String id);

    @GET("kuliner")
    Call<YourResponse> getKuliner();

    @GET("kuliner/{id}")
    Call<ModelKuliner> getKulinerDetail(@Path("id") String id);

    @GET("hotel")
    Call<YourResponse> getHotel();
    @GET("tempatibadah")
    Call<YourResponse> getPrayPlace();


}
