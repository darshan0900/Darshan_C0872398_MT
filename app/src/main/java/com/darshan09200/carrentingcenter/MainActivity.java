package com.darshan09200.carrentingcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.darshan09200.carrentingcenter.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<String> carNames;
    private ArrayAdapter<String> carNamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        carNames = new ArrayList<>(Arrays.asList(getString(R.string.car_name_prompt),"BMW", "Audi", "Cadillac", "Volks Wagon", "Mercedes", "Peugeot"));
        carNamesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carNames);
        carNamesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.carName.setAdapter(carNamesAdapter);

        binding.noOfDaysSeekbar.incrementProgressBy(1);
        binding.noOfDaysSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.noOfDays.setText(String.format("%d Days", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}