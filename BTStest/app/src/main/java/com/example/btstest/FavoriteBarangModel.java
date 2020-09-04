package com.example.btstest;

public class FavoriteBarangModel {
    private String judulFavorite, hargaFavorite, diskonFavorite, kotaFavorite;
    private int gambarFavorite, iconFavorite;
    private int ratingFavorite;

    public FavoriteBarangModel(int gambarFavorite, String judulFavorite, String hargaFavorite, String diskonFavorite, String kotaFavorite, int ratingFavorite) {
        this.gambarFavorite = gambarFavorite;
        this.judulFavorite = judulFavorite;
        this.hargaFavorite = hargaFavorite;
        this.diskonFavorite = diskonFavorite;
        this.kotaFavorite = kotaFavorite;
        this.ratingFavorite = ratingFavorite;
    }

    public FavoriteBarangModel() {
    }


    public String getKotaFavorite() {
        return kotaFavorite;
    }

    public void setKotaFavorite(String kotaFavorite) {
        this.kotaFavorite = kotaFavorite;
    }

    public int getRatingFavorite() {
        return ratingFavorite;
    }

    public void setRatingFavorite(int ratingFavorite) {
        this.ratingFavorite = ratingFavorite;
    }

    public String getJudulFavorite() {
        return judulFavorite;
    }

    public void setJudulFavorite(String judulFavorite) {
        this.judulFavorite = judulFavorite;
    }

    public String getHargaFavorite() {
        return hargaFavorite;
    }

    public void setHargaFavorite(String hargaFavorite) {
        this.hargaFavorite = hargaFavorite;
    }

    public String getDiskonFavorite() {
        return diskonFavorite;
    }

    public void setDiskonFavorite(String diskonFavorite) {
        this.diskonFavorite = diskonFavorite;
    }

    public int getGambarFavorite() {
        return gambarFavorite;
    }

    public void setGambarFavorite(int gambarFavorite) {
        this.gambarFavorite = gambarFavorite;
    }

}
