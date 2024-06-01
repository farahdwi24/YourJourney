package com.example.yourjourney.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourjourney.ApiService;
import com.example.yourjourney.R;
import com.example.yourjourney.RetrofitClient;
import com.example.yourjourney.YourResponse;
import com.example.yourjourney.activties.HotelActivity;
import com.example.yourjourney.activties.KulinerActivity;
import com.example.yourjourney.activties.PrayPlaceActivity;
import com.example.yourjourney.activties.WisataActivity;
import com.example.yourjourney.adapter.SearchAdapter;
import com.example.yourjourney.model.ModelHotel;
import com.example.yourjourney.model.ModelKuliner;
import com.example.yourjourney.model.ModelPrayPlace;
import com.example.yourjourney.model.ModelWisata;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private TextInputEditText searchInputEditText;
    private RecyclerView recyclerView;
    private SearchAdapter searchResultAdapter;
    private List<Object> searchResults;
    private List<ModelHotel> hotelList;
    private List<ModelKuliner> kulinerList;
    private List<ModelPrayPlace> prayPlaceList;
    private List<ModelWisata> wisataList;
    private CardView cvHotel, cvKuliner, cvPrayPlace, cvWisata;
    TextView tvToday;
    String hariIni;
    LinearLayout dftrKategori;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        searchInputEditText = view.findViewById(R.id.search_input_edit_text);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchResults = new ArrayList<>();
        searchResultAdapter = new SearchAdapter(getActivity(), searchResults);
        recyclerView.setAdapter(searchResultAdapter);
        cvHotel = view.findViewById(R.id.cvHotel);
        cvKuliner = view.findViewById(R.id.cvKuliner);
        cvPrayPlace = view.findViewById(R.id.cvPrayPlace);
        cvWisata = view.findViewById(R.id.cvWisata);
        tvToday = view.findViewById(R.id.tvDate);
        dftrKategori = view.findViewById(R.id.daftar_kategori);

        setupSearch();

        cvHotel.setOnClickListener(v -> startActivity(new Intent(getActivity(), HotelActivity.class)));
        cvKuliner.setOnClickListener(v -> startActivity(new Intent(getActivity(), KulinerActivity.class)));
        cvPrayPlace.setOnClickListener(v -> startActivity(new Intent(getActivity(), PrayPlaceActivity.class)));
        cvWisata.setOnClickListener(v -> startActivity(new Intent(getActivity(), WisataActivity.class)));
        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        getToday();
        loadData();
        getToday();

        return view;
    }

    private void setupSearch() {
        searchInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchItems(s.toString());

            }
        });
    }

    private void searchItems(String query) {
        if (TextUtils.isEmpty(query)) {
            recyclerView.setVisibility(View.GONE);
            dftrKategori.setVisibility(View.VISIBLE);
            return;
        }
        searchResults.clear();

        for (ModelHotel hotel : hotelList) {
            if (hotel.getTxtNamaHotel().toLowerCase().contains(query.toLowerCase()) ) {
                searchResults.add(hotel);
            }
        }

        for (ModelKuliner kuliner : kulinerList) {
            if (kuliner.getTxtNamaKuliner().toLowerCase().contains(query.toLowerCase()) ) {
                searchResults.add(kuliner);
            }
        }

        for (ModelPrayPlace prayPlace : prayPlaceList) {
            if (prayPlace.getTxtTempatIbadah().toLowerCase().contains(query.toLowerCase()) ) {
                searchResults.add(prayPlace);
            }
        }

        for (ModelWisata wisata : wisataList) {
            if (wisata.getTxtNamaWisata().toLowerCase().contains(query.toLowerCase()) ) {
                searchResults.add(wisata);
            }
        }

        if (searchResults.isEmpty()) {
            Toast.makeText(getActivity(), "No results found", Toast.LENGTH_SHORT).show();
            dftrKategori.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        dftrKategori.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        searchResultAdapter.notifyDataSetChanged();
    }

    private void loadData() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        apiService.getHotel().enqueue(new Callback<YourResponse>() {
            @Override
            public void onResponse(Call<YourResponse> call, Response<YourResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hotelList = response.body().getHotelList();
                }
            }

            @Override
            public void onFailure(Call<YourResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to load hotels", Toast.LENGTH_SHORT).show();
            }
        });

        apiService.getKuliner().enqueue(new Callback<YourResponse>() {
            @Override
            public void onResponse(Call<YourResponse> call, Response<YourResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    kulinerList = response.body().getKulinerList();
                }
            }

            @Override
            public void onFailure(Call<YourResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to load kuliner", Toast.LENGTH_SHORT).show();
            }
        });

        apiService.getPrayPlace().enqueue(new Callback<YourResponse>() {
            @Override
            public void onResponse(Call<YourResponse> call, Response<YourResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    prayPlaceList = response.body().getPrayPlaceList();
                }
            }

            @Override
            public void onFailure(Call<YourResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to load pray places", Toast.LENGTH_SHORT).show();
            }
        });

        apiService.getWisata().enqueue(new Callback<YourResponse>() {
            @Override
            public void onResponse(Call<YourResponse> call, Response<YourResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    wisataList = response.body().getWisataList();
                }
            }

            @Override
            public void onFailure(Call<YourResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to load wisata", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String formatFix = hariIni + ", " + tanggal;
        tvToday.setText(formatFix);
    }
}
