package com.woxapp.go.test.burlaka.winevaultapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.woxapp.go.test.burlaka.winevaultapp.R;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;

import java.util.List;

/**
 * Created by Operator on 28.10.2016.
 */
public class TurnoverRVAdapter extends RecyclerView.Adapter <TurnoverRVAdapter.TurnoverViewHolder>{

    private static final String TAG = "myTag";
    List<Turnover> turnover;

    public TurnoverRVAdapter(List<Turnover> turnover){
        this.turnover = turnover;
    }

    public void swap(List<Turnover> datas){
       turnover.clear();
       turnover.addAll(datas);
       notifyDataSetChanged();
    }


    @Override
    public TurnoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.turnover_card_layout, parent, false);
        TurnoverViewHolder pvh = new TurnoverViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(TurnoverViewHolder holder, int position) {

        if(turnover.get(position).getStatus_id() == 0) {

    //On lost wine data
            //Make get vine card - invisible
            holder.card_gwine.setVisibility(View.INVISIBLE);

            //Show lost vine card
            holder.card_lwine.setVisibility(View.VISIBLE);
            holder.data_lwine.setText(turnover.get(position).getDate());
            holder.text_lwine.setText(turnover.get(position).getWineName());
            holder.box_lwine.setText("-"+Integer.toString(turnover.get(position).getBoxCount()));
            holder.wine_lwine.setText("-"+Integer.toString(turnover.get(position).getBottleCount()));

            return;
        }

    //On get wine data
        //Make lost wine card invisible
        holder.card_lwine.setVisibility(View.INVISIBLE);

        //Show get wine card
        holder.card_gwine.setVisibility(View.VISIBLE);
        holder.data_gwine.setText(turnover.get(position).getDate());
        holder.text_gwine.setText(turnover.get(position).getWineName());
        holder.box_gwine.setText("+"+Integer.toString(turnover.get(position).getBoxCount()));
        holder.wine_gwine.setText("+"+Integer.toString(turnover.get(position).getBottleCount()));
    }


    @Override
    public int getItemCount() {
        return turnover.size();
    }


    public static class TurnoverViewHolder extends RecyclerView.ViewHolder {
        //For lost wine cards
        View card_gwine;
        TextView text_gwine, wine_gwine, box_gwine, data_gwine;

        //For get wine cards
        View card_lwine;
        TextView text_lwine, wine_lwine, box_lwine, data_lwine;

        TurnoverViewHolder(View itemView) {
            super(itemView);
            //get wine card
            card_gwine =  itemView.findViewById(R.id.get_wine_fragment );
            text_gwine = (TextView) card_gwine.findViewById( R.id.info_text);
            wine_gwine = (TextView) card_gwine.findViewById(R.id.amount_wine_text);
            box_gwine = (TextView) card_gwine.findViewById(R.id.amount_box_text);
            data_gwine = (TextView) card_gwine.findViewById(R.id.data_text);

            //lost wine card
            card_lwine =  itemView.findViewById(R.id.lost_wine_fragment );
            text_lwine = (TextView) card_lwine.findViewById( R.id.info_text);
            wine_lwine = (TextView) card_lwine.findViewById(R.id.amount_wine_text);
            box_lwine = (TextView) card_lwine.findViewById(R.id.amount_box_text);
            data_lwine = (TextView) card_lwine.findViewById(R.id.data_text);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}