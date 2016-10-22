package com.woxapp.go.test.burlaka.winevaultapp.data.generate;

import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Operator on 22.10.2016.
 */
public class ListTurnoverLostW  {

    private List<Turnover> turnovers;

        public ListTurnoverLostW(){
            this.turnovers = new ArrayList<>();

        }

        public void createListT (){
            turnovers.add( createT1());


        }


        public List<Turnover> getTurnovers() {
            return turnovers;
        }

        private Turnover createT1() {
            return  new Turnover(
                    "23",//id
                    "23", //"canary_id"
                    "-3", //"box_count"
                    "-2",//"bottle_count"
                    "2016-03-15 14:23:43",//"date"
                    "Test wine name", //"wineName"
                    "0"//"status_id"
            );

        }

        private Turnover createT2() {
            return  new Turnover(
                    "25",//id
                    "25", //"canary_id"
                    "23", //"box_count"
                    "23",//"bottle_count"
                    "2016-03-15 14:23:43",//"date"
                    "Test wine name", //"wineName"
                    "0"//"status_id"
            );
        }
}
