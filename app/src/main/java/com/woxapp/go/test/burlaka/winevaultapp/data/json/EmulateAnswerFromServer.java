package com.woxapp.go.test.burlaka.winevaultapp.data.json;

import com.woxapp.go.test.burlaka.winevaultapp.data.model.Reminder;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Operator on 21.10.2016.
 */
public class EmulateAnswerFromServer {

    public  JSONObject getDashBoardJSON(){

        JSONObject bodyFromServer = new  JSONObject ();
        try {
            bodyFromServer.put("wineInStok", createWineInStock());
            bodyFromServer.put("reminder", createReminderArray());
            bodyFromServer.put("turnover", createTurnoverArray());
        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    //turnover
    private JSONArray createTurnoverArray() {

        JSONObject turnover1 = createTurnoverObj ( new Turnover(
                "23",//id
                "23", //"canary_id"
                "23", //"box_count"
                "23",//"bottle_count"
                "2016-03-15 14:23:43",//"date"
                "Test wine name", //"wineName"
                "1"//"status_id"
        ));

        JSONObject turnover2 = createTurnoverObj ( new Turnover(
                "25",//id
                "25", //"canary_id"
                "23", //"box_count"
                "23",//"bottle_count"
                "2016-03-15 14:23:43",//"date"
                "Test wine name", //"wineName"
                "0"//"status_id"
        ));

        JSONArray turnoverArray  = new JSONArray();
        turnoverArray.put( turnover1);
        turnoverArray.put( turnover2);

        return  turnoverArray;

    }

    private JSONObject createTurnoverObj(Turnover turnoverModel) {

        JSONObject turnover = new JSONObject();
        try {
            turnover.put("id",turnoverModel.getId());
            turnover.put("canary_id", turnoverModel.getCanary_id());
            turnover.put("box_count",turnoverModel.getBox_count() );
            turnover.put("bottle_count",turnoverModel.getBottle_count() );
            turnover.put("date",turnoverModel.getDate() );
            turnover.put("wineName", turnoverModel.getWineName() );
            turnover.put("status_id",turnoverModel.getStatus_id());

        } catch (JSONException e) {

            e.printStackTrace();
        }
        return turnover;
    }

    // reminder
    private JSONArray createReminderArray() {

        JSONObject remindObj1 =  createReminderObj( new Reminder (
                "2", //id
                "23", // canaryId
                "2016-03-15 14:23:43", //date
                "Test wine name", //wineName
                "2", // boxCount
                "",  // bottleCount
                "На подарок", // text
                "0" // reminderType
        )
        );


        JSONObject remindObj2 = createReminderObj( new Reminder (
                "2", //id
                "23", // canaryId
                "2016-03-15 14:23:43", //date
                "Test wine name", //wineName
                "2", // boxCount
                "",  // bottleCount
                "На подарок", // text
                "0" // reminderType
        )
        );

        JSONArray reminder = new JSONArray();
        reminder.put(remindObj1);
        reminder.put(remindObj2);

        return  reminder;
    }


    private  JSONObject createReminderObj(Reminder reminderModel) {

        JSONObject reminder = new JSONObject();
        try {
            reminder.put("id",reminderModel.getId());
            reminder.put("canary_id", reminderModel.getCanaryId());
            reminder.put("date",reminderModel.getDate() );
            reminder.put("wineName",reminderModel.getWineName() );
            reminder.put("box_count",reminderModel.getBoxCount() );
            reminder.put("bottle_count",reminderModel.getBottleCount() );
            reminder.put("text", reminderModel.getText() );
            reminder.put("reminderType",reminderModel.getReminderType());

        } catch (JSONException e) {

            e.printStackTrace();

        }
        return reminder;

    }

    // wine in stock
    private JSONObject createWineInStock() {
        JSONObject wineInStock = new JSONObject();
        try {
            wineInStock.put("total", "8 967");
            wineInStock.put("inbox", "25");
            wineInStock.put("year", "4rd");
            wineInStock.put("bottle", "7 843");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return wineInStock;
    }

}
