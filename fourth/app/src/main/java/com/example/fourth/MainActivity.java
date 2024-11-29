package com.example.fourth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkboxPython, checkboxBanana, checkboxJava, checkboxOrange;
    private RadioGroup radioGroup;
    private Button submitButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initializeViews();

        // Set click listener for submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScore();
            }
        });
    }

    private void initializeViews() {
        checkboxPython = findViewById(R.id.checkbox_python);
        checkboxBanana = findViewById(R.id.checkbox_banana);
        checkboxJava = findViewById(R.id.checkbox_java);
        checkboxOrange = findViewById(R.id.checkbox_orange);
        radioGroup = findViewById(R.id.radio_group);
        submitButton = findViewById(R.id.submit_button);
        resultText = findViewById(R.id.result_text);
    }

    private void calculateScore() {
        int totalScore = 0;

        // Check question 1 (Multiple choice)
        boolean q1Correct = checkboxPython.isChecked() &&
                checkboxJava.isChecked() &&
                !checkboxBanana.isChecked() &&
                !checkboxOrange.isChecked();
        if (q1Correct) {
            totalScore++;
        }

        // Check question 2 (Single choice)
        if (radioGroup.getCheckedRadioButtonId() == R.id.radio_paris) {
            totalScore++;
        }

        // Display results
        showResults(totalScore);
    }

    private void showResults(int score) {
        resultText.setVisibility(View.VISIBLE);
        resultText.setText("Your Score: " + score + " out of 2");
    }
}