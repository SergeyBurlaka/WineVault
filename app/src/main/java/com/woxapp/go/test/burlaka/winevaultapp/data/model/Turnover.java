package com.woxapp.go.test.burlaka.winevaultapp.data.model;

/**
 * Created by Operator on 21.10.2016.
 */
public class Turnover implements Model {

    public static final String TABLE = "TurnoverTable";

    //Labels Table Columns names
    public static final String KEY_ID = "id";

    public static final String CANARY_ID = "canaryId";

    public static final String BOX_COUNT = "box_count";

    public static final String BOTTLE_COUNT = "bottle_count";

    public static final String DATE = "date";

    public static final String WINE_NAME = "wineName";

    public static final String STATUS_ID = "statusId";

    private int id ;
    private int canary_id ;
    private String date;
    private String wineName;
    private int box_count ;
    private int bottle_count;
    private int status_id;

    
    public Turnover(){

        this.id =0;
        this.canary_id  =0;

        this.date = "no date";
        this.wineName = "no wine name";

        this.box_count = 0;
        this.bottle_count = 0;

        this.status_id = 0;
    }


    public Turnover(
            int id,
            int canary_id,
            int box_count,
            int bottle_count,
            String date,
            String wineName,
            int status_id
    ) {
        this.id = id;
        this.canary_id = canary_id;
        this.box_count = box_count;
        this.bottle_count = bottle_count;
        this.date = date;
        this.wineName = wineName;
        this.status_id = status_id;
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

    public int getCanary_id() {
        return canary_id;
    }

    public int getBoxCount() {
        return box_count;
    }

    public void setBox_count(String box_count) {
        if(box_count.matches("")) {this.box_count = 0; return;}
        this.box_count = Integer.parseInt(box_count);
    }

    public void setBox_count(int box_count) {

        this.box_count = box_count;
    }

    public int getBottleCount() {
        return bottle_count;
    }

    public void setBottle_count(String bottle_count) {
        if( bottle_count.matches("")) {this.bottle_count = 0; return;}
        this.bottle_count = Integer.parseInt(bottle_count);
    }

    public void setBottle_count(int bottle_count) {

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

    @Override
    public String getText() {
        return null;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        if( status_id.matches("")) {this.status_id= 0; return;}
        this.status_id = Integer.parseInt(status_id);
    }

    public void setStatus_id(int status_id) {

        this.status_id = status_id;
    }

    public void setCanary_id(String canary_id) {
        if( canary_id.matches("")) {this.canary_id = 0; return;}
        this.canary_id = Integer.parseInt(canary_id);
    }

    public void setCanary_id(int canary_id) {

        this.canary_id = canary_id;
    }
}
