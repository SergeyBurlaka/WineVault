package com.woxapp.go.test.burlaka.winevaultapp.data.generate;

import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operator on 22.10.2016.
 */
public class ListTurnoverGetW {
    private List<Turnover> turnovers;

    public ListTurnoverGetW(){
        this.turnovers = new ArrayList<>();

    }

    public void createListT (){
        turnovers.add( createT1());

        turnovers.add(createT2());
        turnovers.add( createT1());
        turnovers.add(createT2());

    }


    public List<Turnover> getTurnovers() {
        return turnovers;
    }

    private Turnover createT1() {
        return  new Turnover(
                "23",//id
                "23", //"canary_id"
                "+1", //"box_count"
                "+50",//"bottle_count"
                "2016-03-15 14:23:43",//"date"
                "Test wine name", //"wineName"
                "1"//"status_id"
        );

    }

    private Turnover createT2() {
        return  new Turnover(
                "25",//id
                "22", //"canary_id"
                "+2", //"box_count"
                "+100",//"bottle_count"
                "2016-03-15 14:23:43",//"date"
                "Test wine name", //"wineName"
                "1"//"status_id"
        );
    }

}
