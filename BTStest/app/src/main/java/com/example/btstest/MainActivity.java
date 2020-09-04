package com.example.btstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<FavoriteBarangModel> favoriteBarangModelList = new ArrayList<>();
    private FavoriteBarangAdapter favoriteBarangAdapter;

    private TextView textJudul;
    private TextView batalButton;
    private ImageView addButton;
    private EditText cariBarang;
    private ImageView searchButton;
    private ViewGroup containerMain;
    boolean doubleTap = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(this.getResources().getColor(R.color.background_default_layout));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        textJudul = findViewById(R.id.text_judul);
        cariBarang = findViewById(R.id.cari_barang);
        searchButton = findViewById(R.id.search_button);
        batalButton = findViewById(R.id.batal_button);
        containerMain = findViewById(R.id.container_main);
        addButton = findViewById(R.id.add_button);

        RecyclerView recyclerFavoriteBarang = findViewById(R.id.recylcerFavorite);
        favoriteBarangAdapter = new FavoriteBarangAdapter(favoriteBarangModelList);
        recyclerFavoriteBarang.setHasFixedSize(true);
        recyclerFavoriteBarang.setLayoutManager(new GridLayoutManager(this,2));
        recyclerFavoriteBarang.setItemAnimator(new DefaultItemAnimator());
        recyclerFavoriteBarang.setAdapter(favoriteBarangAdapter);
        prepareFavoriteBarang();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(containerMain);
                searchButton.setVisibility(View.GONE);
                textJudul.setVisibility(View.GONE);
                addButton.setVisibility(View.GONE);
                cariBarang.setVisibility(View.VISIBLE);
                batalButton.setVisibility(View.VISIBLE);
                cariBarang.requestFocus();
                showSoftKeyboard(cariBarang);
            }
        });

        batalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(containerMain);
                searchButton.setVisibility(View.VISIBLE);
                textJudul.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.VISIBLE);
                cariBarang.setVisibility(View.GONE);
                batalButton.setVisibility(View.GONE);
                cariBarang.clearFocus();
                hideKeyboard(cariBarang);
                cariBarang.setText("");
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        cariBarang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (cariBarang.getText().toString().length() > 0) {
                    cariBarang.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_clear, 0);
                } else {
                    cariBarang.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                filter(s.toString());
            }
        });

        cariBarang.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= cariBarang.getRight() - cariBarang.getTotalPaddingRight()) {
                        // your action for drawable click event
                        cariBarang.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void filter(String text) {
        ArrayList<FavoriteBarangModel> filteredList = new ArrayList<>();
        for (FavoriteBarangModel item : favoriteBarangModelList) {
            if (item.getJudulFavorite().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        favoriteBarangAdapter.filterList(filteredList);
    }



    private void prepareFavoriteBarang() {
        FavoriteBarangModel favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Chickern Wings Ultra Man", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Barang 1", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Mouse", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Keyboard", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Monitor", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Kursi Gaming", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Meja Gaming", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Kasur Gaming", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Lemari Aestetik", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Hello World", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Sepatu Sandal", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangModel = new FavoriteBarangModel(R.drawable.img1, "Chickern Wings Ultra Man", "Rp20000", "rp10000", "Kota Bandung",3);
        favoriteBarangModelList.add(favoriteBarangModel);
        favoriteBarangAdapter.notifyDataSetChanged();
    }

    public void showSoftKeyboard(final View view) {
        if (view.requestFocus()) {
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
                }

            }, 500);

        }
    }

    public void hideKeyboard(final View view) {
        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

        }, 500);
    }

    @Override
    public void onBackPressed() {
        if (doubleTap) {
            super.onBackPressed();
            finishAffinity();
            System.exit(0);
        } else {
            Toast.makeText(this, "Tekan Lagi Untuk Keluar Aplikasi", Toast.LENGTH_SHORT).show();
            doubleTap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleTap = false;
                }
            },500);
        }
    }
}