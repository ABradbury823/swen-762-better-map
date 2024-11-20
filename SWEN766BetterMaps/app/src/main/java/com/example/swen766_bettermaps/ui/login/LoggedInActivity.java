package com.example.swen766_bettermaps.ui.login;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.swen766_bettermaps.R;
import com.example.swen766_bettermaps.data.RITUser;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        TextView accountInfoTextView = findViewById(R.id.account_info_text);

        // Retrieve RITUser from Intent
        RITUser ritUser = (RITUser) getIntent().getSerializableExtra("RIT_USER");
        if (ritUser != null) {
            String displayText = "Welcome, " + ritUser.getFirst_name() +
                    "\nRIT ID: " + ritUser.getRIT_id() +
                    "\nEmail: " + ritUser.getEmail();
            accountInfoTextView.setText(displayText);
        }

        // Implement sign-out functionality
        findViewById(R.id.sign_out_button).setOnClickListener(v -> {
            // Logic for signing out and returning to the login screen
            finish();
        });
    }
}