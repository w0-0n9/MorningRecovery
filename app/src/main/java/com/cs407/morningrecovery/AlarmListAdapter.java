package com.cs407.morningrecovery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class AlarmListAdapter extends BaseAdapter {
    private Context context;
    private List<Alarm> alarms;

    public AlarmListAdapter(Context context, List<Alarm> alarms) {
        this.context = context;
        this.alarms = alarms;
    }

    @Override
    public int getCount() {
        return alarms.size();
    }

    @Override
    public Object getItem(int position) {
        return alarms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_alarmlist, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.alarmText);
        Alarm alarm = alarms.get(position);
        textView.setText(alarm.getHour() + ":" + alarm.getMinute() + " " + alarm.getAmPm() + " - " + alarm.getQuizType());

        Button deleteButton = convertView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).deleteAlarm(alarm.getId());
            }
        });

        return convertView;
    }

}
