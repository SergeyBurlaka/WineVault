package com.woxapp.go.test.burlaka.winevaultapp.data.model;

/**
 * Created by Operator on 21.10.2016.
 */
public class WineInStock implements Model {

    public static final String TABLE = "WineInStockTable";

    //Labels Table Columns names
    public static final String KEY_ID = "id";

    public static final String TOTAL = "total";

    public static final String INBOX = "inbox";

    public static final String BOTTLE = "bottle";


    int total ;
    int inbox;
    int bottle;

    public WineInStock(){

    }

    public WineInStock(
            int total,
            int inbox,
            int bottle
    ) {

        this.total = total;
        this.inbox = inbox;
        this.bottle = bottle;
    }

    public int getBottle() {
        return bottle;
    }

    public void setBottle(String bottle) {
        if(bottle.matches("")) {this.bottle = 0; return;}
        this.bottle = Integer.parseInt(bottle);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(String total) {
        if(total.matches("")) {this.total = 0; return;}
        this.total = Integer.parseInt(total);
    }

    public int getInbox() {
        return inbox;
    }

    public void setInbox(String inbox) {
        if(inbox.matches("")) {this.inbox = 0; return;}
        this.inbox = Integer.parseInt(inbox);
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public String getWineName() {
        return null;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public int getBottleCount() {
        return 0;
    }

    @Override
    public int getBoxCount() {
        return 0;
    }

    @Override
    public int getStatus_id() {
        return 0;
    }
}
