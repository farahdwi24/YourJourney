package com.example.yourjourney.activties;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.example.yourjourney.ApiService;
import com.example.yourjourney.R;
import com.example.yourjourney.RetrofitClient;
import com.example.yourjourney.model.ModelKuliner;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKulinerActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar tbDetailKuliner;
    TextView tvNamaKuliner, tvAddressKuliner, tvPhoneKuliner, tvOpenTime, tvDesc;
    ProgressBar progressBar;
    ModelKuliner modelKuliner;
    String idKuliner;
    ApiService apiService;
    LinearLayout layout_detKuliner;

    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kuliner);

        tbDetailKuliner = findViewById(R.id.tbDetailKuliner);
        tbDetailKuliner.setTitle("Detail Kuliner");
        setSupportActionBar(tbDetailKuliner);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layout_detKuliner = findViewById(R.id.layout_detKuliner);

        tvNamaKuliner = findViewById(R.id.tvNamaKuliner);
        tvAddressKuliner = findViewById(R.id.tvAddressKuliner);
        tvPhoneKuliner = findViewById(R.id.tvPhoneKuliner);
        tvOpenTime = findViewById(R.id.tvOpenTime);
        tvDesc = findViewById(R.id.tvDesc);
        progressBar = findViewById(R.id.progressBar);

        modelKuliner = (ModelKuliner) getIntent().getSerializableExtra("detailKuliner");
        if (modelKuliner != null) {
            idKuliner = modelKuliner.getIdKuliner();
        }
        apiService = RetrofitClient.getClient().create(ApiService.class);
        getDetailKuliner();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void getDetailKuliner() {
        progressBar.setVisibility(View.VISIBLE);
        layout_detKuliner.setVisibility(View.GONE);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                Call<ModelKuliner> call = apiService.getKulinerDetail(idKuliner);
                call.enqueue(new Callback<ModelKuliner>() {
                    @Override
                    public void onResponse(Call<ModelKuliner> call, Response<ModelKuliner> response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                layout_detKuliner.setVisibility(View.VISIBLE);

                                if (response.isSuccessful() && response.body() != null) {
                                    ModelKuliner kuliner = response.body();
                                    tvNamaKuliner.setText(kuliner.getTxtNamaKuliner());
                                    tvAddressKuliner.setText(kuliner.getTxtAlamatKuliner());
                                    tvPhoneKuliner.setText(kuliner.getPhoneKuliner());
                                    tvOpenTime.setText(kuliner.getTxtOpenTime());
                                    tvDesc.setText(kuliner.getDeskripsi());
                                } else {
                                    Toast.makeText(DetailKulinerActivity.this, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ModelKuliner> call, Throwable t) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(DetailKulinerActivity.this, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (modelKuliner != null && modelKuliner.getKoordinat() != null && !modelKuliner.getKoordinat().isEmpty()) {
            String[] latlong = modelKuliner.getKoordinat().split(",");
            if (latlong.length == 2) {
                try {
                    double latitude = Double.parseDouble(latlong[0]);
                    double longitude = Double.parseDouble(latlong[1]);
                    LatLng latLng = new LatLng(latitude, longitude);
                    googleMap.addMarker(new MarkerOptions().position(latLng).title(modelKuliner.getTxtNamaKuliner()));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                } catch (NumberFormatException e) {
                }
            }
        }
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.setTrafficEnabled(true);
    }
}
