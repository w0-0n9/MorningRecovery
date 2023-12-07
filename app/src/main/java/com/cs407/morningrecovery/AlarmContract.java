package com.cs407.morningrecovery;

import android.provider.BaseColumns;

// Contract class for the alarm table
public final class AlarmContract {

    // Private constructor to prevent instantiation
    private AlarmContract() {}

    // Inner class that defines the table contents
    public static class AlarmEntry implements BaseColumns {
        public static final String TABLE_NAME = "alarm";
        public static final String COLUMN_NAME_HOUR = "hour";
        public static final String COLUMN_NAME_MINUTE = "minute";
        public static final String COLUMN_NAME_AM_PM = "am_pm";
        public static final String COLUMN_NAME_QUIZ_TYPE = "quiz_type";
    }
}
