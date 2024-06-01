package com.example.yourjourney.activties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import com.example.yourjourney.DatabaseHelper;
import com.example.yourjourney.R;
import com.example.yourjourney.model.ModelUser;

public class WelcomeActivity extends AppCompatActivity {
    Button btnwel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnwel = findViewById(R.id.btn_welcome);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
        int userId = preferences.getInt("userId", -1);

        if (isLoggedIn) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userId",userId);
            startActivity(intent);
            finish();
        } else {
            btnwel.setOnClickListener(v -> {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            });
        }
    }
}