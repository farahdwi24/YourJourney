package com.example.yourjourney.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.yourjourney.R;
import com.example.yourjourney.activties.HotelActivity;
import com.example.yourjourney.activties.KulinerActivity;
import com.example.yourjourney.activties.PrayPlaceActivity;
import com.example.yourjourney.activties.WisataActivity;

import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    TextView tvToday;
    String hariIni;
    CardView cvHotel, cvKuliner, cvPrayPlace, cvWisata;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvToday = view.findViewById(R.id.tvDate);
        cvHotel = view.findViewById(R.id.cvHotel);
        cvKuliner = view.findViewById(R.id.cvKuliner);
        cvPrayPlace = view.findViewById(R.id.cvPrayPlace);
        cvWisata = view.findViewById(R.id.cvWisata);

        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        getToday();

        cvHotel.setOnClickListener(v -> startActivity(new Intent(getActivity(), HotelActivity.class)));
        cvKuliner.setOnClickListener(v -> startActivity(new Intent(getActivity(), KulinerActivity.class)));
        cvPrayPlace.setOnClickListener(v -> startActivity(new Intent(getActivity(), PrayPlaceActivity.class)));
        cvWisata.setOnClickListener(v -> startActivity(new Intent(getActivity(), WisataActivity.class)));

        return view;
    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String formatFix = hariIni + ", " + tanggal;
        tvToday.setText(formatFix);
    }
}
