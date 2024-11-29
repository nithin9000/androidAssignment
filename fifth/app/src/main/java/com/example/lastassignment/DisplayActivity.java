package com.example.lastassignment;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    private TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        displayText = findViewById(R.id.textViewDisplay);

        // Get data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("NAME");
            String email = extras.getString("EMAIL");
            String phone = extras.getString("PHONE");

            // Display the information
            String displayString = "Name: " + name + "\n" +
                    "Email: " + email + "\n" +
                    "Phone: " + phone;
            displayText.setText(displayString);
        }
    }
}