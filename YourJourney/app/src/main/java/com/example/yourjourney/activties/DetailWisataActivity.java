package com.example.yourjourney.activties;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yourjourney.ApiService;
import com.example.yourjourney.R;
import com.example.yourjourney.RetrofitClient;
import com.example.yourjourney.model.ModelWisata;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailWisataActivity extends AppCompatActivity {

    Toolbar tbDetailWisata;
    TextView tvNamaWisata, tvDescWisata;
    ImageView imgWisata;
    ProgressBar progressBar;
    String idWisata;
    ModelWisata modelWisata;
    ApiService apiService;
    LinearLayout layout_detWisata;

    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        tbDetailWisata = findViewById(R.id.tbDetailWisata);
        tbDetailWisata.setTitle("Detail Wisata");
        setSupportActionBar(tbDetailWisata);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layout_detWisata = findViewById(R.id.layout_detWisata);
        progressBar = findViewById(R.id.progressBar);

        modelWisata = (ModelWisata) getIntent().getSerializableExtra("detailWisata");
        if (modelWisata != null) {
            idWisata = modelWisata.getIdWisata();
            imgWisata = findViewById(R.id.imgWisata);
            tvNamaWisata = findViewById(R.id.tvNamaWisata);
            tvDescWisata = findViewById(R.id.tvDescWisata);

            Glide.with(this)
                    .load(modelWisata.getGambarWisata())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgWisata);

            apiService = RetrofitClient.getClient().create(ApiService.class);
            getDetailWisata();
        }
    }

    private void getDetailWisata() {
        progressBar.setVisibility(View.VISIBLE);
        layout_detWisata.setVisibility(View.GONE);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                Call<ModelWisata> call = apiService.getWisataDetail(idWisata);
                call.enqueue(new Callback<ModelWisata>() {
                    @Override
                    public void onResponse(Call<ModelWisata> call, Response<ModelWisata> response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                layout_detWisata.setVisibility(View.VISIBLE);
                                if (response.isSuccessful() && response.body() != null) {
                                    ModelWisata wisata = response.body();
                                    tvNamaWisata.setText(wisata.getTxtNamaWisata());
                                    tvDescWisata.setText(wisata.getDeskripsi());
                                } else {
                                    Toast.makeText(DetailWisataActivity.this,
                                            "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ModelWisata> call, Throwable t) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(DetailWisataActivity.this,
                                        "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
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
