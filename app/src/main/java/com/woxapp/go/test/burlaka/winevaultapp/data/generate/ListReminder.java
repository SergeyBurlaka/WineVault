package com.woxapp.go.test.burlaka.winevaultapp.data.generate;

import com.woxapp.go.test.burlaka.winevaultapp.data.model.Reminder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operator on 22.10.2016.
 */
public class ListReminder {

    private List<Reminder> reminders;

    public ListReminder(){
        this.reminders = new ArrayList<>();

    }

    public void createListR(){
        reminders.add( createR1());

        reminders.add(createR2());



    }


    public List<Reminder> getReminders() {
        return reminders;
    }

    private Reminder createR1() {
      return  new Reminder (
                "1", //id
                "23", // canaryId
                "2016-03-15 14:23:43", //date
                "Test wine name", //wineName
                "2", // boxCount
                "5",  // bottleCount
                "На подарок", // text
                "0" // reminderType
        );

    }

    private Reminder createR2() {
        return  new Reminder (
                "3", //id
                "23", // canaryId
                "2016-03-15 16:23:43", //date
                "Best wine in world", //wineName
                "2", // boxCount
                "1",  // bottleCount
                "На подарок", // text
                "0" // reminderType
        );

    }

}
