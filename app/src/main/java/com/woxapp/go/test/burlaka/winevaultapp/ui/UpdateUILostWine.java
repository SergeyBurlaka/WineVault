package com.woxapp.go.test.burlaka.winevaultapp.ui;

import android.os.AsyncTask;

import com.woxapp.go.test.burlaka.winevaultapp.R;
import com.woxapp.go.test.burlaka.winevaultapp.WineAmountActivity;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;
import com.woxapp.go.test.burlaka.winevaultapp.data.repo.TurnoverRepo;

import java.util.List;

/**
 * Created by Operator on 23.10.2016.
 */
public class UpdateUILostWine extends AsyncTask<String, String , List<Turnover>> {

    private static final  String TAG = "myTag";
    UpdateUIInterface wineVaultActivity;

    TurnoverRepo turnoverRepo;

    private List<Turnover> turnovers;

    public UpdateUILostWine (WineAmountActivity wineVaultActivity){
        this.wineVaultActivity = wineVaultActivity;
        this.turnoverRepo = new TurnoverRepo();
    }

    @Override
    protected List <Turnover> doInBackground(String... params) {



        turnovers = turnoverRepo.getTurnoverList();
       /* Log.i(TAG, "^-^-^-^-^-^-^-^-^-^-^-^-" );
        Log.i(TAG, "" );
        Log.i(TAG, "reminder size = " +turnovers.size());

        for (Turnover turnover : turnovers){
            Log.i(TAG, "Reminder get wine name = " + turnover.getWineName() );

        }*/

        return turnovers;
    }


    @Override
    protected void onPostExecute(List<Turnover> turnovers) {
        wineVaultActivity.onUpdateUI(turnovers, R.id.turnover);
    }


    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(String... text) {
    }
}


