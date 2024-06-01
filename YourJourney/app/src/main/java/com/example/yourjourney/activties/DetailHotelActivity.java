package com.example.yourjourney.activties;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.example.yourjourney.R;
import com.example.yourjourney.model.ModelHotel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DetailHotelActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar tbDetailHotel;
    TextView txtNameHotel, txtAddressHotel, txtPhoneHotel;
    ProgressBar progressBar;
    String NameHotel, AddressHotel, PhoneHotel;
    ModelHotel modelHotel;
    LinearLayout layout_detHotel;
    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        tbDetailHotel = findViewById(R.id.tbDetailHotel);
        tbDetailHotel.setTitle("Detail Hotel");
        setSupportActionBar(tbDetailHotel);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layout_detHotel = findViewById(R.id.layout_detHotel);
        progressBar = findViewById(R.id.progressBar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelHotel = (ModelHotel) getIntent().getSerializableExtra("detailHotel");
        if (modelHotel != null) {
            loadHotelDetails();
        }
    }

    private void loadHotelDetails() {
        progressBar.setVisibility(View.VISIBLE);
        layout_detHotel.setVisibility(View.GONE);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NameHotel = modelHotel.getTxtNamaHotel();
                        AddressHotel = modelHotel.getTxtAlamatHotel();
                        PhoneHotel = modelHotel.getTxtNoTelp();

                        txtNameHotel = findViewById(R.id.tvNamaHotel);
                        txtAddressHotel = findViewById(R.id.tvAddressHotel);
                        txtPhoneHotel = findViewById(R.id.tvPhoneHotel);

                        txtNameHotel.setText(NameHotel);
                        txtAddressHotel.setText(AddressHotel);
                        txtPhoneHotel.setText(PhoneHotel);

                        progressBar.setVisibility(View.GONE);
                        layout_detHotel.setVisibility(View.VISIBLE);
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
        if (modelHotel != null && modelHotel.getKoordinat() != null && !modelHotel.getKoordinat().isEmpty()) {
            String[] latlong = modelHotel.getKoordinat().split(",");
            if (latlong.length == 2) {
                try {
                    double latitude = Double.parseDouble(latlong[0]);
                    double longitude = Double.parseDouble(latlong[1]);
                    LatLng latLng = new LatLng(latitude, longitude);
                    googleMap.addMarker(new MarkerOptions().position(latLng).title(modelHotel.getTxtNamaHotel()));
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
