package com.woxapp.go.test.burlaka.winevaultapp.data.model;

/**
 * Created by Operator on 21.10.2016.
 */
public class WineInStock {
    String total ;
    String inbox;
    String year;
    String bottle;

    public WineInStock(){

    }

    public WineInStock(
            String total,
            String inbox,
            String year,
            String bottle
    ) {

        this.total = total;
        this.inbox = inbox;
        this.year = year;
        this.bottle = bottle;
    }

    public String getBottle() {
        return bottle;
    }

    public void setBottle(String bottle) {
        this.bottle = bottle;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getInbox() {
        return inbox;
    }

    public void setInbox(String inbox) {
        this.inbox = inbox;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}
