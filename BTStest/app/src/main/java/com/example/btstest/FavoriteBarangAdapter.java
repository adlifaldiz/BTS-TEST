package com.example.btstest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteBarangAdapter extends RecyclerView.Adapter<FavoriteBarangAdapter.MyViewHolder> {
    private List<FavoriteBarangModel> favoriteBarangModelList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView judulFavorite, hargaFavorite, diskonFavorite, kotaFavorite;
        private ImageView gambarFavorite;
        private RatingBar ratingFavorite;

        MyViewHolder(View view) {
            super(view);
            judulFavorite = view.findViewById(R.id.judul_favorite);
            diskonFavorite = view.findViewById(R.id.harga_favorite);
            hargaFavorite = view.findViewById(R.id.diskon_favorite);
            kotaFavorite = view.findViewById(R.id.kota_favorite);
            gambarFavorite = view.findViewById(R.id.gambar_favorite);
            ratingFavorite = view.findViewById(R.id.rating_favorite);
        }
    }

    public FavoriteBarangAdapter(List<FavoriteBarangModel> favoriteBarangModelList) {
        this.favoriteBarangModelList = favoriteBarangModelList;
    }

    @NonNull
    @Override
    public FavoriteBarangAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_barang_favorite, parent, false);
        return new FavoriteBarangAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteBarangAdapter.MyViewHolder holder, int position) {
        final FavoriteBarangModel favoriteBarangModel = favoriteBarangModelList.get(position);

        String stringJudul = favoriteBarangModel.getJudulFavorite();
        String stringHarga = favoriteBarangModel.getHargaFavorite();
        String stringDiskon = favoriteBarangModel.getDiskonFavorite();
        String stringKota = favoriteBarangModel.getKotaFavorite();

        holder.judulFavorite.setText(stringJudul);
        holder.hargaFavorite.setText(stringHarga);
        holder.diskonFavorite.setText(stringDiskon);
        holder.kotaFavorite.setText(stringKota);
        holder.gambarFavorite.setImageResource(favoriteBarangModel.getGambarFavorite());
        holder.ratingFavorite.setRating(favoriteBarangModel.getRatingFavorite());
    }

    @Override
    public int getItemCount() {
        return favoriteBarangModelList.size();
    }

    public void filterList(ArrayList<FavoriteBarangModel> filteredList) {
        favoriteBarangModelList = filteredList;
        notifyDataSetChanged();
    }
}
