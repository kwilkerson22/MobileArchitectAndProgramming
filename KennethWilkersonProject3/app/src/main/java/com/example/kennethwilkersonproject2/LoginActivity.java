package com.example.kennethwilkersonproject2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // UI components
    EditText editTextUsername, editTextPassword;
    Button buttonLogin, buttonCreateAccount;
    DatabaseHelper db; // Database helper for user authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Setting the activity_login.xml layout

        // Initialize database helper
        db = new DatabaseHelper(this);

        // Locate UI components by their IDs
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        // Set click listener for Login button
        buttonLogin.setOnClickListener(v -> {
            String user = editTextUsername.getText().toString();
            String pass = editTextPassword.getText().toString();

            // Check if username and password match a record in the database
            if (db.checkUser(user, pass)) {
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                // Move to WeightLogActivity when login is successful
                Intent intent = new Intent(LoginActivity.this, WeightLogActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for Create Account button
        buttonCreateAccount.setOnClickListener(v -> {
            String user = editTextUsername.getText().toString();
            String pass = editTextPassword.getText().toString();

            // Check that fields aren't empty
            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            }
            else {
                // Try to insert new user into the database
                boolean insert = db.insertUser(user, pass);
                if (insert) {
                    Toast.makeText(LoginActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Account creation failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
