package com.example.btstest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrasiActivity extends AppCompatActivity {

    private TextView logoText;
    private TextView textLearn;
    private TextView textErrorEmailSignup;
    private TextView textErrorPasswordSignup;
    private TextView textErrorUsernameSignup;
    private EditText emailSignUp;
    private EditText passwordSignUp;
    private EditText usernameSignUp;
    private AppCompatCheckBox passwordToggle;

    private Button signupButton;
    private TextView loginLink;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        getWindow().setStatusBarColor(this.getResources().getColor(R.color.background_default_layout));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        logoText = findViewById(R.id.logo);
        textLearn = findViewById(R.id.textlearn);
        textErrorEmailSignup = findViewById(R.id.text_error_email_signup);
        textErrorPasswordSignup = findViewById(R.id.text_error_password_signup);
        textErrorUsernameSignup = findViewById(R.id.text_error_username_signup);
        emailSignUp = findViewById(R.id.email_signup);
        passwordSignUp = findViewById(R.id.password_signup);
        usernameSignUp = findViewById(R.id.username_signup);
        signupButton = findViewById(R.id.button_signup);
        loginLink = findViewById(R.id.loginlink);
        passwordToggle = findViewById(R.id.toggle_password);

        String n = getColoredSpanned("n","#3cba54");
        String u = getColoredSpanned("u","#f4c20d");
        String d = getColoredSpanned("d","#db3236");
        String l = getColoredSpanned("l","#4885ed");
        String e = getColoredSpanned("e","#3cba54");

        logoText.setText(Html.fromHtml(n+" "+u+" "+d+" "+l+" "+e));

        String text = "For your Nudle Account. Learn more";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(RegistrasiActivity.this, "Learn More", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#07BFE4"));
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan, 24, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textLearn.setText(ss);
        textLearn.setMovementMethod(LinkMovementMethod.getInstance());

        usernameSignUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String usernameInput =  usernameSignUp.getText().toString();

                if (usernameInput.isEmpty()) {
                    textErrorUsernameSignup.setText("");
                    textErrorUsernameSignup.setVisibility(View.GONE);
                    Log.d("valemail", "validateEmail: email kosong");
                } else if (usernameInput.length() > 0){
                    textErrorUsernameSignup.setError("");
                    textErrorUsernameSignup.setVisibility(View.GONE);
                }
            }
        });

        emailSignUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String emailInput =  emailSignUp.getText().toString();

                if (emailInput.isEmpty()) {
                    textErrorEmailSignup.setText("");
                    textErrorEmailSignup.setVisibility(View.GONE);
                    Log.d("valemail", "validateEmail: email kosong");
                } else if (emailInput.length() > 0){
                    textErrorEmailSignup.setError("");
                    textErrorEmailSignup.setVisibility(View.GONE);
                }
            }
        });

        passwordSignUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String passwordInput =  passwordSignUp.getText().toString().trim();

                if (passwordInput.equals("")){
                    textErrorPasswordSignup.setVisibility(View.GONE);
                    passwordToggle.setVisibility(View.GONE);
                } else if (passwordInput.length() > 0){
                    passwordToggle.setVisibility(View.VISIBLE);
                    textErrorPasswordSignup.setVisibility(View.GONE);
                    textErrorPasswordSignup.setText("");
                }
            }
        });

        passwordToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    passwordSignUp.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    passwordSignUp.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(signupButton);
                if (!validateEmail(emailSignUp) | !validatePassword() | !validateUsername()) {
                    Log.d("login Activity", "onClick: SignUp gagal");
                    Toast.makeText(getApplicationContext(), "Sign Up Failed", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(getApplicationContext(), "Sign Up Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validatePassword() {
        String passwordInput =  passwordSignUp.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textErrorPasswordSignup.setText("Password can't be empty");
            textErrorPasswordSignup.setVisibility(View.VISIBLE);
            return false;
        } else if (passwordInput.length()<8){
            textErrorPasswordSignup.setText("Password minimum 8 character");
            textErrorPasswordSignup.setVisibility(View.VISIBLE);
            return false;
        } else {
            textErrorPasswordSignup.setText(null);
            textErrorPasswordSignup.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput =  usernameSignUp.getText().toString();

        if (usernameInput.isEmpty()) {
            textErrorUsernameSignup.setText("Username can't be empty");
            textErrorUsernameSignup.setVisibility(View.VISIBLE);
            Log.d("valemail", "validateEmail: username kosong");
            return false;
        } else {
            textErrorUsernameSignup.setError(null);
            textErrorUsernameSignup.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean validateEmail(EditText emailSignUp) {
        String emailInput =  emailSignUp.getText().toString();

        if (emailInput.isEmpty()) {
            textErrorEmailSignup.setText("Email can't be empty");
            textErrorEmailSignup.setVisibility(View.VISIBLE);
            Log.d("valemail", "validateEmail: email empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textErrorEmailSignup.setText("Incorrect format email");
            textErrorEmailSignup.setVisibility(View.VISIBLE);
            Log.d("valemail", "validateEmail: format salah");
            return false;
        } else {
            textErrorEmailSignup.setError(null);
            textErrorEmailSignup.setVisibility(View.GONE);
            return true;
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
}