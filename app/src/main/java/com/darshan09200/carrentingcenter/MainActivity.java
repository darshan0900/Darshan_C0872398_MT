package com.darshan09200.carrentingcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

        carNames = Database.getInstance().getCarNames();
        carNamesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carNames);
        carNamesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.carName.setAdapter(carNamesAdapter);

        binding.carName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Car currentCar = Database.getInstance().getCar(position);
                    if (currentCar != null) {
                        binding.dailyRent.setText(String.format("$ %.2f", currentCar.getPrice()));
                    }
                } else {
                    binding.dailyRent.setText("");
                }
                onSubmit(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                onSubmit(false);
            }
        });

        binding.noOfDaysSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.noOfDays.setText(String.format("%d Day%s", progress, progress > 1 ? "s" : ""));
                onSubmit(false);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.ageGroup.setOnCheckedChangeListener((group, checkedId) -> onSubmit(false));
        binding.gps.setOnCheckedChangeListener((buttonView, isChecked) -> onSubmit(false));
        binding.childSeat.setOnCheckedChangeListener((buttonView, isChecked) -> onSubmit(false));
        binding.unlimitedMileage.setOnCheckedChangeListener((buttonView, isChecked) -> onSubmit(false));

        binding.viewDetails.setOnClickListener(v -> onSubmit(true));

    }

    private void onSubmit(boolean validate) {
        Car carDetails = getCarDetails();
        if (carDetails == null) {
            if (validate) showToast("Please Choose a Car");
            binding.amountLabel.setText("Amount");
            binding.totalPaymentLabel.setText("Total Payment");
            return;
        }
        int noOfDays = binding.noOfDaysSeekbar.getProgress();
        AgeGroup ageGroup = getSelectedAgeGroup();
        boolean isGpsChecked = binding.gps.isChecked();
        boolean isChildSeatChecked = binding.childSeat.isChecked();
        boolean isUnlimitedMileageChecked = binding.unlimitedMileage.isChecked();

        double amountBeforeTaxes = 0;
        double dailyAmount = carDetails.getPrice();

        if (ageGroup == AgeGroup.UNDER_20) dailyAmount += Database.UNDER_20_CHARGES;
        if (isGpsChecked) dailyAmount += Database.GPS_CHARGES;
        if (isChildSeatChecked) dailyAmount += Database.CHILD_SEAT_CHARGES;
        if (isUnlimitedMileageChecked) dailyAmount += Database.UNLIMITED_MILEAGE_CHARGES;

        System.out.println(isGpsChecked + " " + isChildSeatChecked + " " + isUnlimitedMileageChecked);
        amountBeforeTaxes = dailyAmount * noOfDays;
        if (ageGroup == AgeGroup.ABOVE_60) amountBeforeTaxes -= 10;

        double amountAfterTaxes = amountBeforeTaxes + amountBeforeTaxes * 0.13;

        binding.amountLabel.setText(String.format("Amount: %.2f", amountBeforeTaxes));
        binding.totalPaymentLabel.setText(String.format("Total Payment: %.2f", amountAfterTaxes));
    }

    private Car getCarDetails() {
        return Database.getInstance().getCar(binding.carName.getSelectedItemPosition());
    }

    private AgeGroup getSelectedAgeGroup() {
        switch (binding.ageGroup.getCheckedRadioButtonId()) {
            case R.id.lessThanAge:
                return AgeGroup.UNDER_20;
            case R.id.aboveAge:
                return AgeGroup.ABOVE_60;
            default:
                return AgeGroup.BETWEEN_21_AND_60;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}