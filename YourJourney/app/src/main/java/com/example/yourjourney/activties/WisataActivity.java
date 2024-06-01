package com.example.yourjourney.activties;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yourjourney.ApiService;
import com.example.yourjourney.R;
import com.example.yourjourney.RetrofitClient;
import com.example.yourjourney.YourResponse;
import com.example.yourjourney.adapter.WisataAdapter;
import com.example.yourjourney.model.ModelWisata;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WisataActivity extends AppCompatActivity {

    RecyclerView rvWisata;
    WisataAdapter wisataAdapter;
    ProgressDialog progressDialog;
    Toolbar tbWisata;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata);

        tbWisata = findViewById(R.id.toolbar_wisata);
        tbWisata.setTitle("Daftar Wisata Purwakarta");
        setSupportActionBar(tbWisata);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvWisata = findViewById(R.id.rvWisata);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        rvWisata.setLayoutManager(mLayoutManager);
        rvWisata.setHasFixedSize(true);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        getWisata();
    }

    private void getWisata() {
        progressDialog.show();
        Call<YourResponse> call = apiService.getWisata();
        call.enqueue(new Callback<YourResponse>() {
            @Override
            public void onResponse(Call<YourResponse> call, Response<YourResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    List<ModelWisata> wisata = response.body().getWisataList();
                    wisataAdapter = new WisataAdapter(wisata);
                    rvWisata.setAdapter(wisataAdapter);
                } else {
                    Toast.makeText(WisataActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<YourResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("WisataActivity", "Network request failed", t);
                Toast.makeText(WisataActivity.this, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
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
