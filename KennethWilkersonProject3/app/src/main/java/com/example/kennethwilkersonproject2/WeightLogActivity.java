package com.example.kennethwilkersonproject2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;

public class WeightLogActivity extends AppCompatActivity {
    // UI components
    EditText editNewWeight;
    Button buttonAddWeight;
    Button buttonGoToGoals;
    RecyclerView recyclerViewWeightList;

    // Data and adapter
    ArrayList<String> weightEntries;
    WeightAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaydata); // Layout set to Weight Log screen

        // Initialize UI components with their IDs from the layout
        editNewWeight = findViewById(R.id.editNewWeight);
        buttonAddWeight = findViewById(R.id.buttonAddWeight);
        buttonGoToGoals = findViewById(R.id.buttonGoToGoals);
        recyclerViewWeightList = findViewById(R.id.recyclerViewWeightList);

        // Initialize data storage
        weightEntries = new ArrayList<>();
        adapter = new WeightAdapter(weightEntries);

        // Set up the RecyclerView with a vertical list layout and adapter
        recyclerViewWeightList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWeightList.setAdapter(adapter);

        // Set click listener for adding new weight entry
        buttonAddWeight.setOnClickListener(v -> {
            String weight = editNewWeight.getText().toString();
            if (!weight.isEmpty()) {
                weightEntries.add(weight + " lbs"); // Add new weight to the list
                adapter.notifyDataSetChanged(); // Notify adapter that data has changed
                editNewWeight.setText(""); // Clear input
            }
            else {
                Toast.makeText(this, "Please enter a weight", Toast.LENGTH_SHORT).show(); // Message for if input is empty
            }
        });

        // Set click listener to navigate to Goal screen
        buttonGoToGoals.setOnClickListener(v -> {
            Intent intent = new Intent(WeightLogActivity.this, GoalSettingActivity.class);
            startActivity(intent); // Start GoalSettingActivity
        });

        // Set click listener for Logout button
        Button buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(v -> {
            // Go back to LoginActivity
            Intent intent = new Intent(WeightLogActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
            startActivity(intent); // Start LoginActivity
            finish(); // Close current WeightLogActivity
        });
    }
}
