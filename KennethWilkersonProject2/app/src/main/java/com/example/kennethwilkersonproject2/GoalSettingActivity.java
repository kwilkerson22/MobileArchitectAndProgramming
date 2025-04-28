package com.example.kennethwilkersonproject2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.SwitchCompat;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GoalSettingActivity extends AppCompatActivity {

    // UI components
    EditText editTextGoalWeight;
    Button buttonSaveGoal, buttonBack;
    TextView textViewSavedGoal;
    SwitchCompat switchSMS;

    // Storing goal data locally
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goalweight); // Set to Goal screen

        // Locate UI components by their IDs
        editTextGoalWeight = findViewById(R.id.editTextGoalWeight);
        buttonSaveGoal = findViewById(R.id.buttonSaveGoal);
        buttonBack = findViewById(R.id.buttonBack);
        textViewSavedGoal = findViewById(R.id.textViewSavedGoal);
        switchSMS = findViewById(R.id.switchSMS);

        // Initialize SharedPreferences for saving goal data
        sharedPreferences = getSharedPreferences("GoalPrefs", MODE_PRIVATE);

        // Set click listener to save goal weight and set SMS preference
        buttonSaveGoal.setOnClickListener(v -> {
            String goal = editTextGoalWeight.getText().toString();
            boolean smsEnabled = switchSMS.isChecked(); // Check if SMS switch is enabled

            if (!goal.isEmpty()) {
                // Save goal and SMS setting into SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("goal_weight", goal);
                editor.putBoolean("sms_enabled", smsEnabled);
                editor.apply(); // Apply changes

                // Update UI with saved goal
                textViewSavedGoal.setText(getString(R.string.goal_saved_message, goal));
                Toast.makeText(this, "Goal saved!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Please enter a goal weight", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for Back button
        buttonBack.setOnClickListener(v -> {
            finish(); // Close this activity and return to previous screen
        });
    }
}
