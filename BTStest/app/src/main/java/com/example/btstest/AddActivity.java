package com.example.btstest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView textSyaratKetentuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWindow().setStatusBarColor(this.getResources().getColor(R.color.background_default_layout));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        backButton = findViewById(R.id.back_add);
        textSyaratKetentuan = findViewById(R.id.text_syarat_ketentuan);

        String text = "Saya mengerti dan menyetujui Syarat & Ketentuan yang berlaku";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(AddActivity.this, "Syaratnya Ada Barangnya gan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#07BFE4"));
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan, 29, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textSyaratKetentuan.setText(ss);
        textSyaratKetentuan.setMovementMethod(LinkMovementMethod.getInstance());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}