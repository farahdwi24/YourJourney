package com.example.yourjourney.activties;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.yourjourney.ApiService;
import com.example.yourjourney.R;
import com.example.yourjourney.RetrofitClient;
import com.example.yourjourney.YourResponse;
import com.example.yourjourney.adapter.HotelAdapter;
import com.example.yourjourney.model.ModelHotel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelActivity extends AppCompatActivity {

    RecyclerView rvHotel;
    HotelAdapter hotelAdapter;
    ProgressDialog progressDialog;
    List<ModelHotel> modelHotel = new ArrayList<>();
    Toolbar tbHotel;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        tbHotel = findViewById(R.id.toolbar_hotel);
        tbHotel.setTitle("Daftar Hotel Purwakarta");
        setSupportActionBar(tbHotel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvHotel = findViewById(R.id.rvHotel);
        rvHotel.setHasFixedSize(true);
        rvHotel.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getClient().create(ApiService.class);

        getHotel();
    }

    private void getHotel() {
        progressDialog.show();
        Call<YourResponse> call = apiService.getHotel();
        call.enqueue(new Callback<YourResponse>() {
            @Override
            public void onResponse(Call<YourResponse> call, Response<YourResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    modelHotel = response.body().getHotelList();
                    hotelAdapter = new HotelAdapter(modelHotel);
                    rvHotel.setAdapter(hotelAdapter);
                } else {
                    Toast.makeText(HotelActivity.this, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<YourResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HotelActivity.this, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
