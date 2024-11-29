package com.example.lastassignment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText customerNameField, dateField, timeField;
    private RadioGroup breadLengthGroup;
    private RadioButton length15cm;
    private CheckBox paneerCheckbox, mushroomCheckbox, onionCheckbox, jalapenoCheckbox;
    private AutoCompleteTextView drinkField;
    private Button orderButton;

    // Constants for pricing
    private static final double PRICE_15CM = 100.0;
    private static final double PRICE_30CM = 200.0;
    private static final double PRICE_PREMIUM_TOPPING = 50.0;
    private static final double PRICE_REGULAR_TOPPING = 30.0;
    private static final double PRICE_DRINK = 40.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.lastassignment.R.layout.activity_main);

        initializeViews();
        setupCurrentDateTime();
        setupDrinkAutocomplete();
        setupOrderButton();
    }

    private void initializeViews() {
        customerNameField = findViewById(com.example.lastassignment.R.id.customerNameField);
        dateField = findViewById(com.example.lastassignment.R.id.dateField);
        timeField = findViewById(com.example.lastassignment.R.id.timeField);
        breadLengthGroup = findViewById(com.example.lastassignment.R.id.breadLengthGroup);
        length15cm = findViewById(com.example.lastassignment.R.id.length15cm);
        paneerCheckbox = findViewById(com.example.lastassignment.R.id.paneerCheckbox);
        mushroomCheckbox = findViewById(com.example.lastassignment.R.id.mushroomCheckbox);
        onionCheckbox = findViewById(com.example.lastassignment.R.id.onionCheckbox);
        jalapenoCheckbox = findViewById(com.example.lastassignment.R.id.jalapenoCheckbox);
        drinkField = findViewById(com.example.lastassignment.R.id.drinkField);
        orderButton = findViewById(com.example.lastassignment.R.id.orderButton);
    }

    private void setupCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date now = new Date();

        dateField.setText(dateFormat.format(now));
        timeField.setText(timeFormat.format(now));
    }

    private void setupDrinkAutocomplete() {
        String[] drinks = {"Coke", "Pepsi", "Fanta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, drinks);
        drinkField.setAdapter(adapter);
    }

    private void setupOrderButton() {
        orderButton.setOnClickListener(v -> calculateAndShowTotal());
    }

    private double calculateTotal() {
        double total = 0;
        ArrayList<String> selectedToppings = new ArrayList<>();

        // Add bread price
        total += length15cm.isChecked() ? PRICE_15CM : PRICE_30CM;

        // Add toppings price
        if (paneerCheckbox.isChecked()) {
            total += PRICE_PREMIUM_TOPPING;
            selectedToppings.add("Paneer");
        }
        if (mushroomCheckbox.isChecked()) {
            total += PRICE_PREMIUM_TOPPING;
            selectedToppings.add("Mushroom");
        }
        if (onionCheckbox.isChecked()) {
            total += PRICE_REGULAR_TOPPING;
            selectedToppings.add("Onion");
        }
        if (jalapenoCheckbox.isChecked()) {
            total += PRICE_REGULAR_TOPPING;
            selectedToppings.add("Jalapeno");
        }

        // Add drink price if selected
        String selectedDrink = drinkField.getText().toString();
        if (!selectedDrink.isEmpty()) {
            total += PRICE_DRINK;
        }

        return total;
    }

    private void calculateAndShowTotal() {
        String customerName = customerNameField.getText().toString();
        if (customerName.isEmpty()) {
            Toast.makeText(this, "Please enter customer name", Toast.LENGTH_SHORT).show();
            return;
        }

        double total = calculateTotal();
        ArrayList<String> selectedToppings = new ArrayList<>();

        // Get selected toppings
        if (paneerCheckbox.isChecked()) selectedToppings.add("Paneer");
        if (mushroomCheckbox.isChecked()) selectedToppings.add("Mushroom");
        if (onionCheckbox.isChecked()) selectedToppings.add("Onion");
        if (jalapenoCheckbox.isChecked()) selectedToppings.add("Jalapeno");

        // Prepare order summary
        String summary = String.format(Locale.getDefault(),
                "Order Summary:\n\n" +
                        "Customer: %s\n" +
                        "Bread Length: %s\n" +
                        "Toppings: %s\n" +
                        "Drink: %s\n\n" +
                        "Total Amount: ₹%.2f",
                customerName,
                length15cm.isChecked() ? "15cm" : "30cm",
                selectedToppings.isEmpty() ? "None" : String.join(", ", selectedToppings),
                drinkField.getText().toString().isEmpty() ? "None" : drinkField.getText().toString(),
                total);

        // Show order summary dialog
        new AlertDialog.Builder(this)
                .setTitle("Order Confirmation")
                .setMessage(summary)
                .setPositiveButton("OK", null)
                .show();
    }
}