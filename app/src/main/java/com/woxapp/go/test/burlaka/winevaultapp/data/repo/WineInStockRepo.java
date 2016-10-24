package com.woxapp.go.test.burlaka.winevaultapp.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.woxapp.go.test.burlaka.winevaultapp.data.DatabaseManager;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.WineInStock;

/**
 * Created by Operator on 22.10.2016.
 */
public class WineInStockRepo {

    private static final String TAG = "myTag";

    public  String createTable (){
        return "CREATE TABLE " + WineInStock.TABLE + "("+
                WineInStock.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                WineInStock.TOTAL + " INTEGER, " +
                WineInStock.INBOX + " INTEGER, " +
                WineInStock.BOTTLE + " INTEGER);";
    }


    public   int insert ( WineInStock wineInStock ){

        int wineInStockId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(WineInStock.TOTAL, wineInStock.getTotal());
        values.put(WineInStock.INBOX, wineInStock.getInbox());

        values.put(WineInStock.BOTTLE, wineInStock.getBottle());

        //Inserting Row
        wineInStockId = (int)db.insert(WineInStock.TABLE, null, values);

        DatabaseManager.getInstance().closeDatabase();

        return wineInStockId;
    }

    
    public  void delete( ) {
        // if(db.e)
        try {
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            db.delete(WineInStock.TABLE,null,null);
            DatabaseManager.getInstance().closeDatabase();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
            Log.e(TAG, "Exception on getReminderList. " + e);
            Log.i(TAG, "Exception on getReminderList. " + e);
        }
    }

    
    public Cursor query (){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        return db.query(WineInStock.TABLE, null, null, null, null, null, null);
    }

    
    public WineInStock getWineInStock (){
        WineInStock wineInStock = new WineInStock ();

        try{
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

            String selectQuery =  " SELECT "

                    + WineInStock.TABLE + "." + WineInStock.TOTAL  + ", "
                    + WineInStock.TABLE + "." + WineInStock.INBOX  + ", "
                    + WineInStock.TABLE + "." + WineInStock.BOTTLE

                    + " FROM " +	WineInStock.TABLE
                    ;

            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    wineInStock.setTotal(cursor.getString(cursor.getColumnIndex(WineInStock.TOTAL)));
                    wineInStock.setInbox(cursor.getString(cursor.getColumnIndex(WineInStock.INBOX)));
                    wineInStock.setBottle(cursor.getString(cursor.getColumnIndex(WineInStock.BOTTLE)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            DatabaseManager.getInstance().closeDatabase();

        } catch (SQLiteException e) {
            // database doesn't exist yet.
            Log.e(TAG, "Exception on getReminderList. " + e);
            Log.i(TAG, "Exception on getReminderList. " + e);
        }
        return wineInStock;
    }
}
