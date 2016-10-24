package com.woxapp.go.test.burlaka.winevaultapp.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woxapp.go.test.burlaka.winevaultapp.App;
import com.woxapp.go.test.burlaka.winevaultapp.R;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Turnover;

import java.util.List;

/**
 * Created by Operator on 22.10.2016.
 */
public class RVAdapterLostWine extends RecyclerView.Adapter<RVAdapterLostWine.TurnoverViewHolder>{

    private static final String TAG = "myTag";
    List<Turnover> turnover;
    public RVAdapterLostWine(List<Turnover> turnover){
        this.turnover = turnover;
    }

    public void swap(List<Turnover> datas){
        turnover.clear();
        turnover.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public TurnoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_lost_wine, parent, false);
        TurnoverViewHolder pvh = new TurnoverViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(TurnoverViewHolder holder, int position) {
       // int status = turnover.get(position).getStatus_id();
        //if(status == 1) {
         //   Log.i(TAG,"on get wine ");
          //  return;}//because it is bottle waste
        if(turnover.get(position).getStatus_id() == 1) {
            Log.i(TAG,"on lost wine ");
            holder.textData.setText(turnover.get(position).getDate());
            holder.info_text.setText("нет убытка в это время");
            holder.info_text.setTextColor(ContextCompat.getColor(App.getContext(), R.color.background_color_green_dark));
            holder.amount_box.setText("");
            holder.amount_wine.setText("");
            holder.relativeLayout.setBackgroundColor(ContextCompat.getColor(App.getContext(), R.color.background_color_green_light));
            return;
        }//

        holder.textData.setText(turnover.get(position).getDate());
        holder.info_text.setText(turnover.get(position).getWineName());
        holder.amount_box.setText("-"+Integer.toString(turnover.get(position).getBoxCount()));
        holder.amount_wine.setText("-"+Integer.toString(turnover.get(position).getBottleCount()));
        holder.info_text.setTextColor(ContextCompat.getColor(App.getContext(), R.color.colorPrimaryDark));
        holder.relativeLayout.setBackgroundColor(ContextCompat.getColor(App.getContext(), R.color.background_color_white));
    }

    @Override
    public int getItemCount() {
        return turnover.size();
    }

    public static class TurnoverViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        TextView textData;
        TextView info_text;

        TextView amount_box;

        TextView amount_wine;


        TurnoverViewHolder(View itemView) {
            super(itemView);

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.get_wine) ;

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
