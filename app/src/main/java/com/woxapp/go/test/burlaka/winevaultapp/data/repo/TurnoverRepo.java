package com.woxapp.go.test.burlaka.winevaultapp.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.woxapp.go.test.burlaka.winevaultapp.data.DatabaseManager;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operator on 22.10.2016.
 */
public class TurnoverRepo {

    private static final String TAG ="myTag" ;

    public  String createTable (){
        return "CREATE TABLE " + Turnover.TABLE + "("+
                Turnover.KEY_ID + " INTEGER PRIMARY KEY, "+
                Turnover.CANARY_ID + " INTEGER, " +
                Turnover.DATE + " TEXT, " +
                Turnover.WINE_NAME + " TEXT, " +
                Turnover.BOX_COUNT + " INTEGER, " +
                Turnover.BOTTLE_COUNT + " INTEGER, " +
                Turnover.STATUS_ID + " INTEGER);";
    }


    public  void insert ( List<Turnover> turnovers ){
        for( Turnover turnover : turnovers){
            insert (turnover);
        }
    }

    
    public   int insert ( Turnover turnover ){
        int turnoverId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Turnover.KEY_ID, turnover.getId());
        values.put(Turnover.CANARY_ID, turnover.getCanary_id());
        values.put(Turnover.DATE, turnover.getDate());
        values.put(Turnover.WINE_NAME, turnover.getWineName());
        values.put(Turnover.BOX_COUNT, turnover.getBoxCount());
        values.put(Turnover.BOTTLE_COUNT, turnover.getBottleCount());
        values.put(Turnover.STATUS_ID, turnover.getStatus_id());

        //Inserting Row
        turnoverId = (int)db.insert(Turnover.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return turnoverId;
    }

    
    public  void delete( ) {
        // if(db.e)
        try {
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            db.delete(Turnover.TABLE,null,null);
            DatabaseManager.getInstance().closeDatabase();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
            Log.e(TAG, "Exception on getReminderList = " + e);
            Log.i(TAG, "Exception on getReminderList = " + e);
        }
    }

    
    public Cursor query (){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        return db.query(Turnover.TABLE, null, null, null, null, null, null);
    }

    
    public List<Turnover> getTurnoverList (){
        List<Turnover> turnovers = new ArrayList<>();
        Turnover turnover;// = new Turnover ();
        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

            String selectQuery =  " SELECT "
                    + Turnover.TABLE + "." + Turnover.BOTTLE_COUNT + ", "
                    + Turnover.TABLE + "." + Turnover.BOX_COUNT + ", "
                    + Turnover.TABLE + "." + Turnover.DATE + ", "
                    + Turnover.TABLE + "." + Turnover.WINE_NAME + ", "
                    + Turnover.TABLE + "." + Turnover.STATUS_ID
                    + " FROM " +	Turnover.TABLE
                    ;

            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            Log.i(TAG, " try to get  SQl " );

            if (cursor.moveToFirst()) {
                do {
                    turnover = new Turnover ();
                    turnover.setBottle_count(cursor.getString(cursor.getColumnIndex(Turnover.BOTTLE_COUNT)));
                    turnover.setBox_count(cursor.getString(cursor.getColumnIndex(Turnover.BOX_COUNT)));
                    turnover.setDate(cursor.getString(cursor.getColumnIndex(Turnover.DATE)));
                    turnover.setWineName(cursor.getString(cursor.getColumnIndex(Turnover.WINE_NAME)));
                    turnover.setStatus_id(cursor.getString(cursor.getColumnIndex(Turnover.STATUS_ID)));
                    turnovers.add(turnover);
                } while (cursor.moveToNext());
            }
            cursor.close();

            DatabaseManager.getInstance().closeDatabase();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
            Log.e(TAG, "Exception on getReminderList = " + e);
            Log.i(TAG, "Exception on getReminderList = " + e);
        }
        return turnovers;
    }
}
