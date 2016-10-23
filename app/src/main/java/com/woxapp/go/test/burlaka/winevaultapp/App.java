package com.woxapp.go.test.burlaka.winevaultapp;

import android.app.Application;
import android.content.Context;

import com.woxapp.go.test.burlaka.winevaultapp.data.DBHelper;
import com.woxapp.go.test.burlaka.winevaultapp.data.DatabaseManager;
import com.woxapp.go.test.burlaka.winevaultapp.data.singletone.InternetUser;

/**
 * Created by Operator on 22.10.2016.
 */


public class App  extends Application {


    private static Context context;
    private static DBHelper dbHelper;

    public static InternetUser iu;

    @Override
    public void onCreate()
    {
        super.onCreate();

        //initialize app context
        context = this.getApplicationContext();

        dbHelper = new DBHelper();

        //DB initialize
        DatabaseManager.initializeInstance(dbHelper);

        //get first instance for InternetUser singleton class initialize
        iu = InternetUser.getInstance();
    }

    public static Context getContext(){
        return context;
    }
}
