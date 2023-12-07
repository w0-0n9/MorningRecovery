package com.cs407.morningrecovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlarmListAdapter adapter;
    List<Alarm> alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.alarm_list_view);
        Button addButton = findViewById(R.id.add_btn);
        Button setButton = findViewById(R.id.setting_btn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetAlarmActivity.class);
                startActivity(intent);
            }
        });

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshAlarmList();
    }

    private void refreshAlarmList() {
        AlarmDbHelper dbHelper = new AlarmDbHelper(this);
        alarms = dbHelper.getAllAlarms();
        adapter = new AlarmListAdapter(this, alarms);
        listView.setAdapter(adapter);
    }

    public void deleteAlarm(int alarmId) {
        AlarmDbHelper dbHelper = new AlarmDbHelper(this);
        dbHelper.deleteAlarm(alarmId);
        refreshAlarmList();
    }
}
