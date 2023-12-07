package com.cs407.morningrecovery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlarmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        TextView alarmText = findViewById(R.id.alarmText);
        Button dismissButton = findViewById(R.id.dismissButton);

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement the logic to stop the alarm sound and notification
                finish();
            }
        });
    }
}
