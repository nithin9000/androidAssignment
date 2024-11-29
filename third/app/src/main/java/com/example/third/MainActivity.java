package com.example.third;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText urlEditText;
    private Button openWebsiteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        urlEditText = findViewById(R.id.urlEditText);
        openWebsiteButton = findViewById(R.id.openWebsiteButton);

        // Set click listener for the button
        openWebsiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();

                if (!url.isEmpty()) {
                    try {
                        // Create the implicit intent
                        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                        // Verify that there's an app to handle this intent
                        if (websiteIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(websiteIntent);
                        } else {
                            Toast.makeText(MainActivity.this, "No browser app found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Invalid URL!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}