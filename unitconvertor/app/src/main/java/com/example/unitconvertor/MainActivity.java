package com.example.unitconvertor;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnitSpinner, toUnitSpinner;
    Button convertBtn;
    TextView resultText;

    String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertBtn = findViewById(R.id.convertBtn);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String inputStr = inputValue.getText().toString();
        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double input = Double.parseDouble(inputStr);
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        double meters = convertToMeters(input, fromUnit);
        double result = convertFromMeters(meters, toUnit);

        resultText.setText("Result: " + String.format("%.4f", result) + " " + toUnit);
    }

    private double convertToMeters(double value, String fromUnit) {
        switch (fromUnit) {
            case "Feet":
                return value * 0.3048;
            case "Inches":
                return value * 0.0254;
            case "Centimeters":
                return value * 0.01;
            case "Meters":
                return value;
            case "Yards":
                return value * 0.9144;
            default:
                return 0;
        }
    }

    private double convertFromMeters(double meters, String toUnit) {
        switch (toUnit) {
            case "Feet":
                return meters / 0.3048;
            case "Inches":
                return meters / 0.0254;
            case "Centimeters":
                return meters * 100;
            case "Meters":
                return meters;
            case "Yards":
                return meters / 0.9144;
            default:
                return 0;
        }
    }
}
