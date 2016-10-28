package com.woxapp.go.test.burlaka.winevaultapp;

import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.woxapp.go.test.burlaka.winevaultapp.background.GDBLoader;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Model;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Reminder;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.WineInStock;
import com.woxapp.go.test.burlaka.winevaultapp.ui.ReminderRVAdapter;
import com.woxapp.go.test.burlaka.winevaultapp.ui.TurnoverRVAdapter;
import com.woxapp.go.test.burlaka.winevaultapp.ui.UpdateUIInterface;
import com.woxapp.go.test.burlaka.winevaultapp.ui.UpdateUIReminder;
import com.woxapp.go.test.burlaka.winevaultapp.ui.UpdateUITurnover;

import java.util.ArrayList;
import java.util.List;

public class WineAmountActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, UpdateUIInterface {
    
    private static final String TAG = "myTag";
    private RecyclerView rvReminder;
    private ReminderRVAdapter reminderRVAdapter;
    private TextView ammountAllBottle;
    private TextView countBottle;
    private TextView countBox;
    private RecyclerView rvTurnover;
    private TurnoverRVAdapter turnoverRVAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTurnover = (RecyclerView)  findViewById(R.id.tv_list_turnover);
        rvReminder = (RecyclerView) findViewById(R.id.rv_list_reminder);

        //amount board elements
        ammountAllBottle =(TextView) findViewById(R.id.amount_all_wine);
        countBottle = (TextView)findViewById(R.id.text_amount_wine_2);
        countBox = (TextView)findViewById(R.id.text_amount_box);

        getLoaderManager().initLoader(R.id.get_dashboard_loader, Bundle.EMPTY, this);

        onCreateUI();
    }


    private void onCreateUI() {

        List <Reminder> reminders = new ArrayList<>();
        List <Turnover> turnovers = new ArrayList<>();

        reminders.add(new Reminder());
        turnovers.add(new Turnover());

 //Create RecyclerVIew List incrementally :
   //- Create layout manager
        LinearLayoutManager llmReminder = new LinearLayoutManager(this);
        LinearLayoutManager llmTurnover = new LinearLayoutManager(this);

    //- Set layout manager
        rvReminder.setLayoutManager(llmReminder );
        rvTurnover.setLayoutManager(llmTurnover);

    //- Create adapter
        //reminder view list
        reminderRVAdapter = new ReminderRVAdapter(reminders);
        turnoverRVAdapter = new TurnoverRVAdapter(turnovers);

    //- Set adapter
        rvReminder.setAdapter(reminderRVAdapter);
        rvTurnover.setAdapter(turnoverRVAdapter);
    }


    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        switch (id) {
            case R.id.get_dashboard_loader:
                return new GDBLoader(this);
            default:
                return null;
        }
    }


    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {
        switch ( loader.getId()) {
            case R.id.get_dashboard_loader:
                onFinishLoader();
                break;
        }
    }


    private void onFinishLoader() {

        Log.i(TAG, "<==== update ui =====>");

        new UpdateUIReminder(this).execute();
        new UpdateUITurnover(this).execute();

    }


    @Override
    public void onUpdateUI(List <? extends Model> models, int model) {
        switch (model){
            case R.id.reminder:
                reminderRVAdapter.swap((List<Reminder>) models);
                break;
            case R.id.turnover:
                turnoverRVAdapter.swap((List<Turnover>)models);
                break;
            case R.id.whine_in_stock:
                onUpdateWineInfo(models);
                break;
        }
    }


    private void onUpdateWineInfo(List<? extends Model> models) {
        WineInStock wineInStock = (WineInStock) models.get(0);
        ammountAllBottle.setText(Integer.toString(wineInStock.getTotal()));
        countBottle.setText(Integer.toString(wineInStock.getBottle()));
        countBox.setText(Integer.toString(wineInStock.getInbox()));
    }

    
    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
    }

}
