package com.woxapp.go.test.burlaka.winevaultapp.data.model;

/**
 * Created by Operator on 21.10.2016.
 */
public class Turnover {

    private String id ;
    private String canary_id ;
    private String box_count ;
    private String bottle_count;
    private String date;
    private String wineName;
    private String  status_id;

    public Turnover (){

    }


    public Turnover(
            String id,
            String canary_id,
            String box_count,
            String bottle_count,
            String date,
            String wineName,
            String status_id
    ) {
        this.id = id;
        this.canary_id = canary_id;
        this.box_count = box_count;
        this.bottle_count = bottle_count;
        this.date = date;
        this.wineName = wineName;
        this.status_id = status_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCanary_id() {
        return canary_id;
    }

    public String getBox_count() {
        return box_count;
    }

    public void setBox_count(String box_count) {
        this.box_count = box_count;
    }

    public String getBottle_count() {
        return bottle_count;
    }

    public void setBottle_count(String bottle_count) {
        this.bottle_count = bottle_count;
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

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }
}
