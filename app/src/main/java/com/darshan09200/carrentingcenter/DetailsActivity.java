package com.darshan09200.carrentingcenter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.darshan09200.carrentingcenter.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailsBinding binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        String carName = intent.getStringExtra("carName");
        double dailyRent = intent.getDoubleExtra("dailyRent", 0);
        int noOfDays = intent.getIntExtra("noOfDays", 0);
        AgeGroup ageGroup = (AgeGroup) intent.getSerializableExtra("ageGroup");
        boolean isGpsChecked = intent.getBooleanExtra("isGpsChecked", false);
        boolean isChildSeatChecked = intent.getBooleanExtra("isChildSeatChecked", false);
        boolean isUnlimitedMileageChecked = intent.getBooleanExtra("isUnlimitedMileageChecked", false);

        double discount = intent.getDoubleExtra("discount", 0);
        double amount = intent.getDoubleExtra("amount", 0);
        double totalPayment = intent.getDoubleExtra("totalPayment", 0);

        binding.carName.setText(carName);
        binding.dailyRent.setText(String.format("$ %.2f", dailyRent));
        binding.noOfDays.setText(String.format("$ %d", noOfDays));
        binding.driversAge.setText(ageGroup.getLabel());

        String additionalOptions = "";
        if (isGpsChecked) additionalOptions += "\u2022 GPS";
        if (isChildSeatChecked) {
            if (additionalOptions.length() > 0) additionalOptions += "\n";
            additionalOptions += "\u2022 Child Seat";
        }
        if (isUnlimitedMileageChecked) {
            if (additionalOptions.length() > 0) additionalOptions += "\n";
            additionalOptions += "\u2022 Unlimited Mileage";
        }
        if (additionalOptions.length() > 0)
            binding.additionalOptions.setText(additionalOptions);
        else {
            binding.additionalOptionsLabel.setVisibility(View.GONE);
            binding.additionalOptions.setVisibility(View.GONE);
        }

        if (discount > 0) {
            binding.discount.setText(String.format("$ %.2f", discount));
        } else {
            binding.discount.setVisibility(View.GONE);
            binding.discountLabel.setVisibility(View.GONE);
        }

        binding.amount.setText(String.format("$ %.2f", amount));
        binding.taxes.setText(String.format("$ %.2f", 13.0));
        binding.totalPayment.setText(String.format("$ %.2f", totalPayment));

        binding.confirmService.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Service")
                    .setMessage("Are you sure you want to confirm this service?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        Toast.makeText(DetailsActivity.this, "Service Confirmed", Toast.LENGTH_SHORT).show();
                        Database.getInstance().setClearDataFlag();
                        finish();
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });
    }

}