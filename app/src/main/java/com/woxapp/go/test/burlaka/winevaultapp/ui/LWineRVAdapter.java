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
 * Created by Operator on 22.10.2016.
 */
public class LWineRVAdapter extends RecyclerView.Adapter<LWineRVAdapter.TurnoverViewHolder>{

    List<Turnover> turnover;
    public LWineRVAdapter(List<Turnover> turnover){
        this.turnover= turnover;
    }



    @Override
    public TurnoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lost_wine, parent, false);
        TurnoverViewHolder pvh = new TurnoverViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(TurnoverViewHolder holder, int position) {

        holder.textData.setText(turnover.get(position).getDate());
        holder.info_text.setText(turnover.get(position).getWineName());
        holder.amount_box.setText(turnover.get(position).getBox_count());
        holder.amount_wine.setText(turnover.get(position).getBottle_count());

    }

    @Override
    public int getItemCount() {
        return turnover.size();
    }

    public static class TurnoverViewHolder extends RecyclerView.ViewHolder {


        TextView textData;
        TextView info_text;

        TextView amount_box;

        TextView amount_wine;


        TurnoverViewHolder(View itemView) {
            super(itemView);


            textData = (TextView)itemView.findViewById(R.id.text_data_l_w);

            info_text = (TextView)itemView.findViewById(R.id.info_text_l_w);

            amount_box = (TextView)itemView.findViewById(R.id.amount_lost_box_l_w);

            amount_wine = (TextView)itemView.findViewById(R.id.amount_lost_wine_l_w);
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
