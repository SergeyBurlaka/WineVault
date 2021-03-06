package com.woxapp.go.test.burlaka.winevaultapp.ui;

import android.os.AsyncTask;

import com.woxapp.go.test.burlaka.winevaultapp.R;
import com.woxapp.go.test.burlaka.winevaultapp.WineAmountActivity;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Reminder;
import com.woxapp.go.test.burlaka.winevaultapp.data.repo.ReminderRepo;

import java.util.List;

/**
 * Created by Operator on 23.10.2016.
 */


public class UpdateUIReminder extends AsyncTask<String, String , List<Reminder>> {

        UpdateUIInterface wineVaultActivity;
        ReminderRepo reminderRepo;
        private List<Reminder> reminders;

        public UpdateUIReminder(WineAmountActivity wineVaultActivity){
            this.wineVaultActivity = wineVaultActivity;
            this.reminderRepo = new ReminderRepo();
        }

    
        @Override
        protected List <Reminder> doInBackground(String... params) {
            reminders = reminderRepo.getReminderList();   
            return reminders;
        }


        @Override
        protected void onPostExecute(List<Reminder> reminders) {
            wineVaultActivity.onUpdateUI(reminders, R.id.reminder);
        }

    
        @Override
        protected void onPreExecute() {
        }

    
        @Override
        protected void onProgressUpdate(String... text) {
        }
    }