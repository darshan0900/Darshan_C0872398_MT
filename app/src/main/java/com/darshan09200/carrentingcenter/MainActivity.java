package com.darshan09200.carrentingcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.darshan09200.carrentingcenter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}