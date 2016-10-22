package com.woxapp.go.test.burlaka.winevaultapp.data.json;

import com.woxapp.go.test.burlaka.winevaultapp.data.model.Reminder;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.WineInStock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operator on 21.10.2016.
 */
public class ParsingDashBoard {

    private List <Reminder> reminders;
    private List <Turnover> turnovers;
    private WineInStock wineInStok;
    private JSONArray reminderArray;
    private JSONArray turnoverArray;
    private JSONObject wineInStokJSON;

    public ParsingDashBoard (){
        this.reminders = new ArrayList<>();
        this.turnovers = new ArrayList<>();
        this.wineInStok = new WineInStock();

    }

    public void parseAnswer (JSONObject answerDashBoard) throws JSONException {

       reminderArray =  answerDashBoard.getJSONArray("reminder");
        turnoverArray = answerDashBoard.getJSONArray("turnover");
        wineInStokJSON = answerDashBoard.getJSONObject("wineInStok");

        getWineInStock( wineInStokJSON);
        getListReminder(reminderArray);
        getListTurnover (turnoverArray);


    }



    private void getListReminder(JSONArray reminderArray) {


    }

    private void getListTurnover(JSONArray turnoverArray) {

    }

    private void getWineInStock(JSONObject wineInStokJSON) {


    }


    public void saveResult (){

    }


}
