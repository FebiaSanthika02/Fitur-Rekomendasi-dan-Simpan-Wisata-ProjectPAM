package com.example.pamprojectkedua;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.WisataViewHolder> {

    private List<Wisata> wisataList;
    private OnItemClickListener listener;

    public WisataAdapter(List<Wisata> wisataList, OnItemClickListener listener) {
        this.wisataList = wisataList;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onSimpanClick(Wisata wisata);
        void onDeleteClick(Wisata wisata);
    }

    @NonNull
    @Override
    public WisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata, parent, false);
        return new WisataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WisataViewHolder holder, int position) {
        Wisata wisata = wisataList.get(position);
        holder.bind(wisata);
    }

    @Override
    public int getItemCount() {
        return wisataList.size();
    }

    public void updateWisataList(List<Wisata> updatedList) {
        this.wisataList = updatedList;
        notifyDataSetChanged();
    }

    class WisataViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNamaWisata;
        TextView textViewDeskripsi;
        RatingBar ratingBar;
        Button buttonSimpan;
        Button buttonTersimpan;
        ImageView imageView;

        public WisataViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamaWisata = itemView.findViewById(R.id.textViewNamaWisata);
            textViewDeskripsi = itemView.findViewById(R.id.textViewDeskripsi);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            buttonSimpan = itemView.findViewById(R.id.buttonSimpan);
            buttonTersimpan = itemView.findViewById(R.id.buttonTersimpan);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(Wisata wisata) {
            textViewNamaWisata.setText(wisata.getNama());
            textViewDeskripsi.setText(wisata.getDeskripsi());
            ratingBar.setRating(wisata.getRating());
            imageView.setImageResource(wisata.getImage());

            if (wisata.isSaved()) {
                buttonSimpan.setVisibility(View.GONE);
                buttonTersimpan.setVisibility(View.VISIBLE);
            } else {
                buttonSimpan.setVisibility(View.VISIBLE);
                buttonTersimpan.setVisibility(View.GONE);
            }

            buttonSimpan.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onSimpanClick(wisata);
                }
            });

            buttonTersimpan.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDeleteClick(wisata);
                }
            });
        }
    }
}
