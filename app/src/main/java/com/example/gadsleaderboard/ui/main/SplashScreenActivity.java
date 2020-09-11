package com.example.gadsleaderboard.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gadsleaderboard.MainActivity;
import com.example.gadsleaderboard.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

    }
}