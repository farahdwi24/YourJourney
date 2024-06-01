package com.example.yourjourney.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yourjourney.R;
import com.example.yourjourney.activties.DetailWisataActivity;
import com.example.yourjourney.model.ModelWisata;

import java.util.List;


public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.ViewHolder> {

    private List<ModelWisata> items;

    public WisataAdapter(List<ModelWisata> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelWisata data = items.get(position);

        Glide.with(holder.itemView.getContext())
                .load(data.getGambarWisata())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgWisata);

        holder.tvKategori.setText(data.getKategoriWisata());
        holder.tvWisata.setText(data.getTxtNamaWisata());
        holder.cvWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailWisataActivity.class);
                intent.putExtra("detailWisata", data);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvKategori;
        public TextView tvWisata;
        public CardView cvWisata;
        public ImageView imgWisata;

        public ViewHolder(View itemView) {
            super(itemView);
            cvWisata = itemView.findViewById(R.id.cvWisata);
            tvWisata = itemView.findViewById(R.id.tvWisata);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            imgWisata = itemView.findViewById(R.id.imgWisata);
        }
    }

}
