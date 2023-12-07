package com.cs407.morningrecovery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class AlarmDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Alarm.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AlarmContract.AlarmEntry.TABLE_NAME + " (" +
                    AlarmContract.AlarmEntry._ID + " INTEGER PRIMARY KEY," +
                    AlarmContract.AlarmEntry.COLUMN_NAME_HOUR + " INTEGER," +
                    AlarmContract.AlarmEntry.COLUMN_NAME_MINUTE + " INTEGER," +
                    AlarmContract.AlarmEntry.COLUMN_NAME_AM_PM + " TEXT," +
                    AlarmContract.AlarmEntry.COLUMN_NAME_QUIZ_TYPE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AlarmContract.AlarmEntry.TABLE_NAME;

    public AlarmDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // AlarmDbHelper 클래스에 추가할 메소드
    public long insertAlarm(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AlarmContract.AlarmEntry.COLUMN_NAME_HOUR, alarm.getHour());
        values.put(AlarmContract.AlarmEntry.COLUMN_NAME_MINUTE, alarm.getMinute());
        values.put(AlarmContract.AlarmEntry.COLUMN_NAME_AM_PM, alarm.getAmPm());
        values.put(AlarmContract.AlarmEntry.COLUMN_NAME_QUIZ_TYPE, alarm.getQuizType());

        return db.insert(AlarmContract.AlarmEntry.TABLE_NAME, null, values);
    }

    public void deleteAlarm(int alarmId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = AlarmContract.AlarmEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(alarmId)};
        db.delete(AlarmContract.AlarmEntry.TABLE_NAME, selection, selectionArgs);
    }

    public List<Alarm> getAllAlarms() {
        List<Alarm> alarmList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                AlarmContract.AlarmEntry.TABLE_NAME,
                new String[]{
                        AlarmContract.AlarmEntry._ID,
                        AlarmContract.AlarmEntry.COLUMN_NAME_HOUR,
                        AlarmContract.AlarmEntry.COLUMN_NAME_MINUTE,
                        AlarmContract.AlarmEntry.COLUMN_NAME_AM_PM,
                        AlarmContract.AlarmEntry.COLUMN_NAME_QUIZ_TYPE
                },
                null, null, null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry._ID);
            int hourIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry.COLUMN_NAME_HOUR);
            int minuteIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry.COLUMN_NAME_MINUTE);
            int amPmIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry.COLUMN_NAME_AM_PM);
            int quizTypeIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry.COLUMN_NAME_QUIZ_TYPE);


            if (idIndex != -1 && hourIndex != -1 && minuteIndex != -1 && amPmIndex != -1 && quizTypeIndex != -1) {
                do {
                    Alarm alarm = new Alarm(
                            cursor.getInt(idIndex),
                            cursor.getInt(hourIndex),
                            cursor.getInt(minuteIndex),
                            cursor.getString(amPmIndex),
                            cursor.getString(quizTypeIndex)
                    );
                    alarmList.add(alarm);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return alarmList;
    }
}
