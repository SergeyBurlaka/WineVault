package com.woxapp.go.test.burlaka.winevaultapp;

import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.woxapp.go.test.burlaka.winevaultapp.data.model.Model;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Reminder;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.WineInStock;
import com.woxapp.go.test.burlaka.winevaultapp.loaders.GDBLoader;
import com.woxapp.go.test.burlaka.winevaultapp.ui.RVAdapterGetWine;
import com.woxapp.go.test.burlaka.winevaultapp.ui.RVAdapterLostWine;
import com.woxapp.go.test.burlaka.winevaultapp.ui.RVAdapterReminder;
import com.woxapp.go.test.burlaka.winevaultapp.ui.UpdateUIInterface;
import com.woxapp.go.test.burlaka.winevaultapp.ui.UpdateUILostWine;
import com.woxapp.go.test.burlaka.winevaultapp.ui.UpdateUIReminder;
import com.woxapp.go.test.burlaka.winevaultapp.ui.UpdateUIWIS;

import java.util.ArrayList;
import java.util.List;

public class WineAmountActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, UpdateUIInterface {


    private static final String TAG = "myTag";
    private static final int LOST_WINE = 0;
    private static final int GET_WINE = 1 ;
    private RecyclerView rvGetWine;
    private RecyclerView rvReminder;
    private RecyclerView rvLostWine;

    private RVAdapterGetWine RVAdapterGetWine;
    private RVAdapterLostWine RVAdapterLostWine;
    private RVAdapterReminder RVAdapterReminder;
    private TextView ammountAllBottle;
    private TextView countBottle;
    private TextView countBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvGetWine = (RecyclerView) findViewById(R.id.rv_get_wine); // for turnover board 1st column view list
        rvLostWine = (RecyclerView) findViewById(R.id.rv_lost_wine); //for turnover board 2nd column view list
        rvReminder = (RecyclerView) findViewById(R.id.rv_reminder);

         ammountAllBottle =(TextView) findViewById(R.id.amount_all_wine);
         countBottle = (TextView)findViewById(R.id.text_amount_wine_2);
        countBox = (TextView)findViewById(R.id.text_amount_box);


        getLoaderManager().initLoader(R.id.get_dashboard_loader, Bundle.EMPTY, this);

        onCreateUI();
    }


    private void onCreateUI() {

        LinearLayoutManager llmGetWine = new LinearLayoutManager(this);
        LinearLayoutManager llmReminder = new LinearLayoutManager(this);
        LinearLayoutManager llmLostWine = new LinearLayoutManager(this);


        rvGetWine.setLayoutManager(llmGetWine);
        rvLostWine.setLayoutManager(llmLostWine);
        rvReminder.setLayoutManager(llmReminder );

        List <Reminder> reminders = new ArrayList<>();
        List <Turnover> turnovers = new ArrayList<>();

        reminders.add(new Reminder());
        turnovers.add(new Turnover());

        //reminder view list
        RVAdapterReminder = new RVAdapterReminder(reminders);
        rvReminder.setAdapter(RVAdapterReminder);

        //lost wine view list
        RVAdapterLostWine = new RVAdapterLostWine(turnovers);
        rvLostWine.setAdapter(RVAdapterLostWine);

        //got new wine view list
        RVAdapterGetWine = new RVAdapterGetWine(turnovers);
        rvGetWine.setAdapter(RVAdapterGetWine);
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
        new UpdateUILostWine(this).execute();
        new UpdateUIWIS(this).execute();
    }

    @Override
    public void onUpdateUI(List <? extends Model> models, int model) {
        switch (model){
            case R.id.reminder:
                RVAdapterReminder.swap((List<Reminder>) models);
                break;
            case R.id.turnover:
                onUpdateTurnover ((List<Turnover>) models);
                RVAdapterGetWine.swap((List<Turnover>)models);
                RVAdapterLostWine.swap((List<Turnover>)models);
                break;
            case R.id.whine_in_stock:
                onUpdateWineInfo(models);
                break;

        }



    }

    private void onUpdateTurnover(List <Turnover> models) {

        List<Turnover> getWine,lostWine;

        getWine = new ArrayList<>();
        lostWine = new ArrayList<>();

        for ( Turnover turnover : models){
            Log.i(TAG, "^+^+^");
            Log.i(TAG, "Turnover status id "+turnover.getStatus_id());
            Log.i(TAG, "case lost bottle "+turnover.getWineName());
            Log.i(TAG, "^+^+^");

            switch (turnover.getStatus_id()){
                case LOST_WINE:
                    Log.i(TAG, "----case lost bottle "+turnover.getWineName());
                    Log.i(TAG, "-----case lost bottle "+turnover.getStatus_id());
                    lostWine.add(turnover);
                    continue;
                case GET_WINE:
                    Log.i(TAG, "+++case get bottle "+turnover.getWineName());
                    Log.i(TAG, "+++case get bottle "+turnover.getStatus_id());
                    getWine.add(turnover);
                    continue;
            }
        }
        Log.i(TAG, "size get = "+getWine.size()+" lost ="+lostWine.size());
        RVAdapterGetWine.swap(getWine);
        RVAdapterLostWine.swap(lostWine);
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
