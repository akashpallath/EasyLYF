package com.example.easylyf.Reminders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AlarmReminderDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "alarmreminder.db";

    private static final int DATABASE_VERSION = 1;

    public AlarmReminderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a String that contains the SQL statement to create the reminder table
        String SQL_CREATE_ALARM_TABLE =  "CREATE TABLE " + com.example.easylyf.Reminders.AlarmReminderContract.AlarmReminderEntry.TABLE_NAME + " ("
                + com.example.easylyf.Reminders.AlarmReminderContract.AlarmReminderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + com.example.easylyf.Reminders.AlarmReminderContract.AlarmReminderEntry.KEY_TITLE + " TEXT, "
                + com.example.easylyf.Reminders.AlarmReminderContract.AlarmReminderEntry.KEY_DATE + " TEXT, "
                + com.example.easylyf.Reminders.AlarmReminderContract.AlarmReminderEntry.KEY_TIME + " TEXT, "
                + com.example.easylyf.Reminders.AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT + " TEXT, "
                + com.example.easylyf.Reminders.AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO + " TEXT, "
                + com.example.easylyf.Reminders.AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE + " TEXT, "
                + com.example.easylyf.Reminders.AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE + " TEXT " + " );";

        // Execute the SQL statement
        sqLiteDatabase.execSQL(SQL_CREATE_ALARM_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
