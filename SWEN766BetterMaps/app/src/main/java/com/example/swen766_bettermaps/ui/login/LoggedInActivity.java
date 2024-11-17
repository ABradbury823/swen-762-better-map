package com.example.swen766_bettermaps.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swen766_bettermaps.R;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        // Get the name from the Intent that was passed from LoginActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        // Find the TextView where the greeting will be displayed
        TextView greetingTextView = findViewById(R.id.greeting_text_view);

        // Set the greeting message
        greetingTextView.setText("Hello, " + name);

        // Optionally, you can add functionality to navigate back to the main activity
        // For example, by adding a back button or a button to open the main content.
    }
}