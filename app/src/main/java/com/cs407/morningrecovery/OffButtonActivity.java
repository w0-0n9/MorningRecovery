package com.cs407.morningrecovery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OffButtonActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donebutton);

        Button doneBtn = findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send a broadcast to stop the alarm
                AlarmReceiver.stopAlarmSound();

                // Continue with your existing code
                Intent intent = new Intent(OffButtonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
