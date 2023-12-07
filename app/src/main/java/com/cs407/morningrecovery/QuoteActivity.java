package com.cs407.morningrecovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.BroadcastReceiver;


import java.util.Random;

public class QuoteActivity extends AppCompatActivity {
    private TextView quoteTextView;

    private String[] quotes = {
            "Be there for others, but never leave yourself behind.",
            "The only way to do great work is to love what you do.",
            "Believe you can and you're halfway there.",
            "Success is not final, failure is not fatal: It is the courage to continue that counts.",
            "The best way to predict the future is to create it."
            // Add more quotes as needed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        Button doneBtn = findViewById(R.id.doneBtn);
        quoteTextView = findViewById(R.id.quoteTextView);

        // Set a random quote initially
        setRandomQuote();

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send a broadcast to stop the alarm
                AlarmReceiver.stopAlarmSound();

                // Continue with your existing code
                Intent intent = new Intent(QuoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setRandomQuote() {
        // Get a random index to select a quote from the array
        Random random = new Random();
        int index = random.nextInt(quotes.length);

        // Set the randomly selected quote to the TextView
        quoteTextView.setText(quotes[index]);
    }
}