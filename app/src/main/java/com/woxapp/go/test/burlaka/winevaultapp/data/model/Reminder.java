package com.woxapp.go.test.burlaka.winevaultapp.data.model;

/**
 * Created by Operator on 21.10.2016.
 */

public class Reminder implements Model {

    public static final String TABLE = "ReminderTable";

    //Labels Table Columns names
    public static final String KEY_ID = "id";

    public static final String CANARY_ID = "canaryId";

    public static final String DATE = "date";

    public static final String WINE_NAME = "wineName";

    public static final String BOX_COUNT= "boxCount";

    public static final String BOTTLE_COUNT = "bottleCount";

    public static final String TEXT = "text";

    public static final String REMINDER_TYPE = "reminderType";

    private int id;
    private int canaryId;
    private String date;
    private String wineName;
    private int boxCount;
    private int bottleCount;
    private String text;
    private int reminderType;
    private int bottleOrWine; //R.id.box or R.id.bottle

    
    public Reminder(){
        this.id =0;
        this.canaryId =0;
        this.date = "no date";
        this.wineName = "no wine name";
        this.boxCount = 0;
        this.bottleCount = 0;
        this.text = "no text";
        this.reminderType = 0;
    }

    
    public Reminder(
            int id,
            int canaryId,
            String date,
            String wineName,
            int boxCount,
            int bottleCount,
            String text,
            int reminderType,
            int bottleOrWine) {

        this.id = id;
        this.canaryId = canaryId;
        this.date = date;
        this.wineName = wineName;
        this.boxCount = boxCount;
        this.bottleCount = bottleCount;
        this.text = text;
        this.reminderType = reminderType;
        this.bottleOrWine = bottleOrWine;
    }

    
    public int getCanaryId() {
        return canaryId;
    }

    
    public void setCanaryId(String canaryId) {
        if(canaryId.matches("")) {this.canaryId = 0; return;}
        this.canaryId = Integer.parseInt(canaryId);
    }

    
    public void setCanaryId(int canaryId) {
        this.canaryId = canaryId;
    }

    
    public int getId() {
        return id;
    }

    
    public void setId(String id) {
        if(id.matches("")) {this.id = 0; return;}
        this.id = Integer.parseInt(id);
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public int getBoxCount() {
        return boxCount;
    }

    @Override
    public int getStatus_id() {
        return 0;
    }

    public void setBoxCount(String boxCount) {
        if(boxCount.matches("")) {this.boxCount = 0; return;}
        this.boxCount = Integer.parseInt(boxCount);
    }

    public void setBoxCount(int boxCount) {

        this.boxCount = boxCount;
    }

    public int getBottleCount() {
        return bottleCount;
    }

    public void setBottleCount(String bottleCount) {
        if(bottleCount.matches("")) {this.bottleCount = 0; return;}
        this.bottleCount = Integer.parseInt(bottleCount);
    }

    public void setBottleCount(int bottleCount) {

        this.bottleCount = bottleCount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReminderType() {
        return reminderType;
    }


    public void setReminderType(String reminderType) {
        if(reminderType.matches("")) {this.reminderType = 0; return;}
        this.reminderType = Integer.parseInt(reminderType);
    }

    public void setReminderType(int reminderType) {
        this.reminderType = reminderType;
    }

    public int getBottleOrWine() {
        return bottleOrWine;
    }

    public void setBottleOrWine(int bottleOrWine) {
        this.bottleOrWine = bottleOrWine;
    }
}
