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
import com.example.yourjourney.activties.DetailKulinerActivity;
import com.example.yourjourney.model.ModelKuliner;

import java.util.List;

public class KulinerAdapter extends RecyclerView.Adapter<KulinerAdapter.ViewHolder> {

    private List<ModelKuliner> items;
    public KulinerAdapter(List<ModelKuliner> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kuliner, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelKuliner data = items.get(position);

        //Get Image
        Glide.with(holder.itemView.getContext())
                .load(data.getGambarKuliner())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgKuliner);

        holder.tvKategori.setText(data.getKategoriKuliner());
        holder.tvKuliner.setText(data.getTxtNamaKuliner());
        holder.cvKuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailKulinerActivity.class);
                intent.putExtra("detailKuliner", data);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvKuliner;
        public TextView tvKategori;
        public CardView cvKuliner;
        public ImageView imgKuliner;

        public ViewHolder(View itemView) {
            super(itemView);
            cvKuliner = itemView.findViewById(R.id.cvKuliner);
            tvKuliner = itemView.findViewById(R.id.tvKuliner);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            imgKuliner = itemView.findViewById(R.id.imgKuliner);
        }
    }

}

