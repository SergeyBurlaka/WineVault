package com.woxapp.go.test.burlaka.winevaultapp.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.woxapp.go.test.burlaka.winevaultapp.R;
import com.woxapp.go.test.burlaka.winevaultapp.data.DatabaseManager;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Reminder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operator on 22.10.2016.
 */

public class ReminderRepo {

    private static final String TAG = "myTag";

    public ReminderRepo (){
    }

    
    public  String createTable (){
        return "CREATE TABLE " + Reminder.TABLE + "("+
                Reminder.KEY_ID + " INTEGER PRIMARY KEY, "+
                Reminder.CANARY_ID + " INTEGER, " +
                Reminder.DATE + " TEXT, " +
                Reminder.WINE_NAME + " TEXT, " +
                Reminder.BOX_COUNT + " INTEGER, " +
                Reminder.BOTTLE_COUNT + " INTEGER, " +
                Reminder.TEXT + " TEXT, " +
                Reminder.REMINDER_TYPE + " INTEGER);";
    }


    public  void insert ( List <Reminder> reminderList ){
        for( Reminder reminder : reminderList){;
            insert (reminder);
        }
    }

    
    public   int insert ( Reminder reminder ){

        int reminderId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        
        values.put(Reminder.KEY_ID, reminder.getId());

        values.put(Reminder.CANARY_ID, reminder.getCanaryId());

        values.put(Reminder.DATE, reminder.getDate());

        values.put(Reminder.WINE_NAME, reminder.getWineName());

        values.put(Reminder.BOX_COUNT, reminder.getBoxCount());

        values.put(Reminder.BOTTLE_COUNT, reminder.getBottleCount());

        values.put(Reminder.TEXT, reminder.getText());

        values.put(Reminder.REMINDER_TYPE, reminder.getReminderType());

        //Inserting Row
        reminderId = (int)db.insert(Reminder.TABLE, null, values);

        DatabaseManager.getInstance().closeDatabase();

        return reminderId;
    }


    public  void delete( ) {
        // if(db.e)
        try {
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            db.delete(Reminder.TABLE,null,null);
            DatabaseManager.getInstance().closeDatabase();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
            Log.e(TAG, "Exception on getReminderList " + e);
            Log.i(TAG, "Exception on getReminderList " + e);
        }
    }
    

    public Cursor query (){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        return db.query(Reminder.TABLE, null, null, null, null, null, null);
    }

    
    public List<Reminder> getReminderList (){

       List <Reminder> reminderList = new ArrayList<>();
        Reminder reminder; //= new Reminder();

        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            String selectQuery =  " SELECT "
                    + Reminder.TABLE + "." + Reminder.BOTTLE_COUNT + ", "
                    + Reminder.TABLE + "." + Reminder.BOX_COUNT + ", "
                    + Reminder.TABLE + "." + Reminder.DATE + ", "
                    + Reminder.TABLE + "." + Reminder.WINE_NAME + ", "
                    + Reminder.TABLE + "." + Reminder.TEXT
                    + " FROM " +	Reminder.TABLE
                    ;
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
            
                do {

                    reminder = new Reminder();

                    reminderSetBottleCount(cursor, reminder);

                    reminderSetBoxCount(cursor, reminder);

                    reminder.setDate(cursor.getString(cursor.getColumnIndex(Reminder.DATE)));

                    reminder.setWineName(cursor.getString(cursor.getColumnIndex(Reminder.WINE_NAME)));        

                    reminder.setText(cursor.getString(cursor.getColumnIndex(Reminder.TEXT)));

                    reminderList.add(reminder);

                } while (cursor.moveToNext());
            }
            cursor.close();

            DatabaseManager.getInstance().closeDatabase();

        } catch (SQLiteException e) {
            Log.e(TAG, "Exception on getReminderList = " + e);
            Log.i(TAG, "Exception on getReminderList = " + e);
            // database doesn't exist yet.
        }
        return reminderList;
    }


    private void reminderSetBoxCount(Cursor cursor, Reminder reminder) {
        int bottleCount = cursor.getInt(cursor.getColumnIndex(Reminder.BOTTLE_COUNT));
        if(bottleCount ==-1 ){ reminder.setBottleOrWine(R.id.box); }
        reminder.setBottleCount(bottleCount);
    }

    
    private void reminderSetBottleCount(Cursor cursor, Reminder reminder) {
        int boxCount = cursor.getInt(cursor.getColumnIndex(Reminder.BOX_COUNT));
        if ( boxCount == -1 ) {reminder.setBottleOrWine(R.id.bottle); return;}
        reminder.setBottleCount(boxCount);
    }
}
