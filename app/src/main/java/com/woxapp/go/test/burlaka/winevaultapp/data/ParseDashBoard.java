package com.woxapp.go.test.burlaka.winevaultapp.data;

import android.util.Log;

import com.woxapp.go.test.burlaka.winevaultapp.data.model.Reminder;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.WineInStock;
import com.woxapp.go.test.burlaka.winevaultapp.data.repo.ReminderRepo;
import com.woxapp.go.test.burlaka.winevaultapp.data.repo.TurnoverRepo;
import com.woxapp.go.test.burlaka.winevaultapp.data.repo.WineInStockRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operator on 21.10.2016.
 */
public class ParseDashBoard {

    private static final String TAG ="myTag" ;

    private List <Reminder> reminders;
    private List <Turnover> turnovers;
    private WineInStock wineInStock;

    private JSONArray reminderArrayJSON;
    private JSONArray turnoverArrayJSON;

    private JSONObject wineInStokJSONObject;
    private JSONObject answerDashBoardJSONObject;

    private ReminderRepo reminderRepo;
    private TurnoverRepo turnoverRepo;
    private WineInStockRepo wineInStockRepo;

    
    public ParseDashBoard(JSONObject jsonObject){

        this.answerDashBoardJSONObject = jsonObject;

        this.reminders = new ArrayList<>();
        this.turnovers = new ArrayList<>();
        this.wineInStock = new WineInStock();

        this.reminderRepo = new ReminderRepo();
        this.turnoverRepo = new TurnoverRepo();
        this.wineInStockRepo = new WineInStockRepo();

    }

    
    public void onParsingJSON()  {
       // Log.i(TAG, "1 step parse");
        try {

            reminderArrayJSON =  answerDashBoardJSONObject.getJSONArray("reminder");
            turnoverArrayJSON = answerDashBoardJSONObject.getJSONArray("turnover");
            wineInStokJSONObject = answerDashBoardJSONObject.getJSONObject("wineInStock");

            onParsingWineInStock(wineInStokJSONObject);
            onParsingReminderList(reminderArrayJSON);
            onParsingTurnoverList(turnoverArrayJSON);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(TAG, "JSONException  onParsingTurnoverList");
        }

        onFinishParsingJSON ();
    }


    private void onFinishParsingJSON (){
        reminderRepo.insert(reminders);
        turnoverRepo.insert(turnovers);
        wineInStockRepo.insert(wineInStock);
    }


    private void onParsingReminderList(JSONArray reminderArray) throws JSONException {
        Reminder rm;
        
        for (int i = 0; i < reminderArray.length(); i++) {

            JSONObject jsonobject = reminderArray.getJSONObject(i);
            rm = new Reminder();
            
            rm.setId(jsonobject.getString("id"));
            rm.setCanaryId(jsonobject.getString("canary_id"));
            rm.setDate(jsonobject.getString("date" ));
            rm.setWineName(jsonobject.getString("wineName" ));
            rm.setBoxCount(jsonobject.getString("box_count"));
            rm.setBottleCount(jsonobject.getString("bottle_count"));
            rm.setText(jsonobject.getString("text"));
            rm.setReminderType(jsonobject.getString("ReminderType"));

            reminders.add(rm);
        }
    }

    private void onParsingTurnoverList(JSONArray turnoverArray) throws JSONException {

        Turnover tm;

        for (int i = 0; i < reminderArrayJSON.length(); i++) {  
            
            JSONObject turnoverJSON = turnoverArray.getJSONObject(i);
            tm = new Turnover();

            tm.setId(turnoverJSON.getString("id"));
            tm.setCanary_id(turnoverJSON.getString("canary_id"));
            tm.setBox_count(turnoverJSON.getString("box_count"));
            tm.setBottle_count(turnoverJSON.getString("bottle_count"));
            tm.setDate(turnoverJSON.getString("date"));
            tm.setWineName(turnoverJSON.getString("wineName"));
            tm.setStatus_id(turnoverJSON.getString("status_id"));

            turnovers.add(tm);
        }
    }

    private void onParsingWineInStock(JSONObject wineInStokJSON) throws JSONException {
        wineInStock.setTotal(wineInStokJSON.getString("total"));
        wineInStock.setInbox( wineInStokJSON.getString("inbox"));
        wineInStock.setBottle(wineInStokJSON.getString("bottle"));
    }
}
