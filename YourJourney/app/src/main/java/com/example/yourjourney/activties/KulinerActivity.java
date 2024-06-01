package com.example.yourjourney.activties;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourjourney.ApiService;
import com.example.yourjourney.R;
import com.example.yourjourney.RetrofitClient;
import com.example.yourjourney.YourResponse;
import com.example.yourjourney.adapter.KulinerAdapter;
import com.example.yourjourney.model.ModelKuliner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KulinerActivity extends AppCompatActivity {

    RecyclerView rvKuliner;
    KulinerAdapter kulinerAdapter;
    ProgressDialog progressDialog;
    Toolbar tbKuliner;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuliner);

        tbKuliner = findViewById(R.id.toolbar_kuliner);
        tbKuliner.setTitle("Daftar Kuliner Purwakarta");
        setSupportActionBar(tbKuliner);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvKuliner = findViewById(R.id.rvKuliner);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvKuliner.setLayoutManager(mLayoutManager);
        rvKuliner.setHasFixedSize(true);
        apiService = RetrofitClient.getClient().create(ApiService.class);
        loadKulinerData();
    }

    private void loadKulinerData() {
        progressDialog.show();
        Call<YourResponse> call = apiService.getKuliner();

        call.enqueue(new Callback<YourResponse>() {
            @Override
            public void onResponse(Call<YourResponse> call, Response<YourResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    List<ModelKuliner> kulinerList = response.body().getKulinerList();
                    kulinerAdapter = new KulinerAdapter(kulinerList);
                    rvKuliner.setAdapter(kulinerAdapter);
                } else {
                    Toast.makeText(KulinerActivity.this, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<YourResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KulinerActivity.this, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
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

