package com.woxapp.go.test.burlaka.winevaultapp.data.model;

/**
 * Created by Operator on 21.10.2016.
 */

public class Reminder {

    private String id;
    private String canaryId;
    private String date;
    private String wineName;
    private String boxCount;
    private String bottleCount;
    private String text;
    private String reminderType;

    public Reminder (){

    }

    public Reminder(
            String id,
            String canaryId,
            String date,
            String wineName,
            String boxCount,
            String bottleCount,
            String text,
            String reminderType) {

        this.id = id;
        this.canaryId = canaryId;
        this.date = date;
        this.wineName = wineName;
        this.boxCount = boxCount;
        this.bottleCount = bottleCount;
        this.text = text;
        this.reminderType = reminderType;
    }

    public String getCanaryId() {
        return canaryId;
    }

    public void setCanaryId(String canaryId) {
        this.canaryId = canaryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getBoxCount() {
        return boxCount;
    }

    public void setBoxCount(String boxCount) {
        this.boxCount = boxCount;
    }

    public String getBottleCount() {
        return bottleCount;
    }

    public void setBottleCount(String bottleCount) {
        this.bottleCount = bottleCount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReminderType() {
        return reminderType;
    }

    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }

}
