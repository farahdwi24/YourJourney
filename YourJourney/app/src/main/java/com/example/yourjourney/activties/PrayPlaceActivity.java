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
import com.example.yourjourney.adapter.PrayPlaceAdapter;
import com.example.yourjourney.model.ModelPrayPlace;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayPlaceActivity extends AppCompatActivity {

    RecyclerView rvPrayPlace;
    PrayPlaceAdapter prayPlaceAdapter;
    ProgressDialog progressDialog;
    List<ModelPrayPlace> modelPrayPlace;
    Toolbar tbPlace;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pray_place);

        tbPlace = findViewById(R.id.toolbar_place);
        tbPlace.setTitle("Daftar Tempat Ibadah");
        setSupportActionBar(tbPlace);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        rvPrayPlace = findViewById(R.id.rvPrayPlace);
        rvPrayPlace.setHasFixedSize(true);
        rvPrayPlace.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getClient().create(ApiService.class);

        getPrayPlace();
    }

    private void getPrayPlace() {
        progressDialog.show();
        Call<YourResponse> call = apiService.getPrayPlace();
        call.enqueue(new Callback<YourResponse>() {
            @Override
            public void onResponse(Call<YourResponse> call, Response<YourResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    YourResponse prayPlaceResponse = response.body();
                    modelPrayPlace = prayPlaceResponse.getPrayPlaceList();
                    prayPlaceAdapter = new PrayPlaceAdapter(modelPrayPlace);
                    rvPrayPlace.setAdapter(prayPlaceAdapter);
                } else {
                    Toast.makeText(PrayPlaceActivity.this,
                            "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<YourResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PrayPlaceActivity.this,
                        "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
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
