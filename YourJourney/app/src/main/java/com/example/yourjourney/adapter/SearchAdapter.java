package com.example.yourjourney.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourjourney.R;
import com.example.yourjourney.activties.DetailHotelActivity;
import com.example.yourjourney.activties.DetailKulinerActivity;
import com.example.yourjourney.activties.DetailWisataActivity;
import com.example.yourjourney.activties.PrayPlaceActivity;
import com.example.yourjourney.model.ModelHotel;
import com.example.yourjourney.model.ModelKuliner;
import com.example.yourjourney.model.ModelPrayPlace;
import com.example.yourjourney.model.ModelWisata;

import java.io.Serializable;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<Object> searchResults;

    public SearchAdapter(Context context, List<Object> searchResults) {
        this.context = context;
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object result = searchResults.get(position);
        if (result instanceof ModelHotel) {
            ModelHotel hotel = (ModelHotel) result;
            holder.title.setText(hotel.getTxtNamaHotel());
            holder.kategori.setText("Hotel");
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailHotelActivity.class);
                intent.putExtra("detailHotel", hotel);
                context.startActivity(intent);
            });
        } else if (result instanceof ModelKuliner) {
            ModelKuliner kuliner = (ModelKuliner) result;
            holder.title.setText(kuliner.getTxtNamaKuliner());
            holder.kategori.setText("Kuliner");
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailKulinerActivity.class);
                intent.putExtra("detailKuliner", kuliner);
                context.startActivity(intent);
            });
        } else if (result instanceof ModelPrayPlace) {
            ModelPrayPlace prayPlace = (ModelPrayPlace) result;
            holder.title.setText(prayPlace.getTxtTempatIbadah());
            holder.kategori.setText("Tempat ibadah");
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, PrayPlaceActivity.class);
                intent.putExtra("id", prayPlace);
                context.startActivity(intent);
            });
        } else if (result instanceof ModelWisata) {
            ModelWisata wisata = (ModelWisata) result;
            holder.title.setText(wisata.getTxtNamaWisata());
            holder.kategori.setText("Wisata");
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailWisataActivity.class);
                intent.putExtra("detailWisata", wisata);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, kategori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            kategori =itemView.findViewById(R.id.kategori);
        }
    }
}

