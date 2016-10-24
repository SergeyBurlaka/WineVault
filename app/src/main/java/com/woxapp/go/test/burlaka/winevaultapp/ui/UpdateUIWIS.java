package com.woxapp.go.test.burlaka.winevaultapp.ui;

import android.os.AsyncTask;

import com.woxapp.go.test.burlaka.winevaultapp.R;
import com.woxapp.go.test.burlaka.winevaultapp.WineAmountActivity;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.WineInStock;
import com.woxapp.go.test.burlaka.winevaultapp.data.repo.WineInStockRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operator on 23.10.2016.
 */
public class UpdateUIWIS extends AsyncTask<String, String ,WineInStock > {

        //private static final String TAG = "myTag";
        UpdateUIInterface wineVaultActivity;
        WineInStockRepo wineInStock;
        private WineInStock wineInStock1;

        public UpdateUIWIS (WineAmountActivity wineVaultActivity){
             this.wineVaultActivity = wineVaultActivity;
             this.wineInStock = new WineInStockRepo();
        }

        
        @Override
        protected WineInStock doInBackground(String... params) {
              wineInStock1 = wineInStock.getWineInStock();
              return wineInStock1;
        }


        @Override
        protected void onPostExecute(WineInStock wineInStock) {
             List <WineInStock> wineInStocks = new ArrayList<>();
             wineInStocks.add (wineInStock);
             wineVaultActivity.onUpdateUI(wineInStocks, R.id.whine_in_stock);
        }

        @Override
        protected void onPreExecute() {
       }

        @Override
        protected void onProgressUpdate(String... text) {
       }
    }
