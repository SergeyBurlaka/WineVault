package com.woxapp.go.test.burlaka.winevaultapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.woxapp.go.test.burlaka.winevaultapp.data.generate.ListReminder;
import com.woxapp.go.test.burlaka.winevaultapp.data.generate.ListTurnoverGetW;
import com.woxapp.go.test.burlaka.winevaultapp.data.generate.ListTurnoverLostW;
import com.woxapp.go.test.burlaka.winevaultapp.ui.GWineRVAdapter;
import com.woxapp.go.test.burlaka.winevaultapp.ui.LWineRVAdapter;
import com.woxapp.go.test.burlaka.winevaultapp.ui.ReminderRVAdapter;

public class WineVaultActivity extends AppCompatActivity {

    private RecyclerView rvGetWine;
    private RecyclerView rvReminder;
    private RecyclerView rvLostWine;
    private ListTurnoverGetW listTurnoverGetW;
    private ListTurnoverLostW listTurnoverLostW;
    private ListReminder listReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onGetData();
        onCreateOfDataLists ();
    }


    private void onCreateOfDataLists() {
        LinearLayoutManager llmGetWine = new LinearLayoutManager(this);
        LinearLayoutManager llmReminder = new LinearLayoutManager(this);
        LinearLayoutManager llmLostWine = new LinearLayoutManager(this);

        rvGetWine = (RecyclerView) findViewById(R.id.rv_get_wine);
        rvLostWine = (RecyclerView) findViewById(R.id.rv_lost_wine);

        rvReminder = (RecyclerView) findViewById(R.id.rv_reminder);

        rvGetWine.setLayoutManager(llmGetWine);
        rvLostWine.setLayoutManager(llmLostWine);
        rvReminder.setLayoutManager(llmReminder );

        ReminderRVAdapter reminderRVAdapter = new  ReminderRVAdapter (listReminder.getReminders());
        rvReminder.setAdapter(reminderRVAdapter);

        LWineRVAdapter lWineRVAdapter = new LWineRVAdapter(listTurnoverLostW.getTurnovers());
        rvLostWine.setAdapter(lWineRVAdapter);

        GWineRVAdapter gWineRVAdapter = new GWineRVAdapter (listTurnoverGetW.getTurnovers());
        rvGetWine.setAdapter(gWineRVAdapter);
    }

    // my cheat engine
    public void onGetData() {
         listTurnoverGetW = new ListTurnoverGetW();
        listTurnoverGetW.createListT();

         listTurnoverLostW = new ListTurnoverLostW();
        listTurnoverLostW.createListT();

        listReminder = new ListReminder();
        listReminder.createListR();

    }
}
