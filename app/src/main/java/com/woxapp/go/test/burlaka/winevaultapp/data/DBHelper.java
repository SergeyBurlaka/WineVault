package com.woxapp.go.test.burlaka.winevaultapp.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.woxapp.go.test.burlaka.winevaultapp.data.model.Reminder;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.WineInStock;
import com.woxapp.go.test.burlaka.winevaultapp.data.repo.ReminderRepo;
import com.woxapp.go.test.burlaka.winevaultapp.data.repo.TurnoverRepo;
import com.woxapp.go.test.burlaka.winevaultapp.data.repo.WineInStockRepo;
import com.woxapp.go.test.burlaka.winevaultapp.App;

/**
 * Created by Operator on 22.10.2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION =17;

    // Database Name
    private static final String DATABASE_NAME = "sqliteDBMultiTbl.db";
    private static final String TAG = DBHelper.class.getSimpleName().toString();
    private ReminderRepo reminderlRepo;
    private TurnoverRepo turnoverRepo;
    private WineInStockRepo wineInStockRepo;

    public DBHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        reminderlRepo = new ReminderRepo();
        turnoverRepo = new TurnoverRepo();
        wineInStockRepo = new WineInStockRepo();

        //All necessary tables you like to create will create here
        db.execSQL(reminderlRepo.createTable());
        db.execSQL(turnoverRepo.createTable());
        db.execSQL(wineInStockRepo.createTable());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //  Log.i(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));
        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Reminder.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Turnover.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + WineInStock.TABLE);
        onCreate(db);
    }
}
