package com.woxapp.go.test.burlaka.winevaultapp.ui;

import android.os.AsyncTask;

import com.woxapp.go.test.burlaka.winevaultapp.R;
import com.woxapp.go.test.burlaka.winevaultapp.WineAmountActivity;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;
import com.woxapp.go.test.burlaka.winevaultapp.data.repo.TurnoverRepo;

import java.util.List;

/**
 * Created by Operator on 28.10.2016.
 */
public class UpdateUITurnover extends AsyncTask<String, String , List<Turnover>> {

    private static final  String TAG = "myTag";
    UpdateUIInterface wineVaultActivity;
    TurnoverRepo turnoverRepo;

    private List<Turnover> turnovers;

    public UpdateUITurnover (WineAmountActivity wineVaultActivity){
        this.wineVaultActivity = wineVaultActivity;
        this.turnoverRepo = new TurnoverRepo();
    }


    @Override
    protected List <Turnover> doInBackground(String... params) {
        turnovers = turnoverRepo.getTurnoverList();
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


