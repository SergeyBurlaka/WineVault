package com.woxapp.go.test.burlaka.winevaultapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woxapp.go.test.burlaka.winevaultapp.R;
import com.woxapp.go.test.burlaka.winevaultapp.data.model.Reminder;

import java.util.List;

/**
 * Created by Operator on 22.10.2016.
 */
public class ReminderRVAdapter extends RecyclerView.Adapter<ReminderRVAdapter.ReminderViewHolder>{

    List<Reminder> reminders;

    public ReminderRVAdapter(List<Reminder> reminders){
        this.reminders = reminders;
    }


    public void swap(List<Reminder> datas){
        reminders.clear();
        reminders.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_reminder_card, parent, false);
        ReminderViewHolder pvh = new ReminderViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {

        if(reminders.get(position).getBottleOrWine() == R.id.box){
                    holder.boxOrBottle.setBackgroundResource(R.mipmap.ic_box);
                }else {
            holder.boxOrBottle.setBackgroundResource( R.mipmap.ic_wine_bottle);}

        if (position==0){holder.relativeLayoutReminder.setBackgroundResource(R.drawable.frame_reminder_red);
        }else {
            holder.relativeLayoutReminder.setBackgroundResource(R.drawable.frame_reminder);
        }

        holder.textData.setText(reminders.get(position).getDate());
        holder.name_text.setText(reminders.get(position).getWineName());
        holder.info_text.setText(reminders.get(position).getText());
        holder.amount_wine.setText(Integer.toString(reminders.get(position).getBottleCount()));
    }

    
    @Override
    public int getItemCount() {
        return reminders.size();
    }


    public static class ReminderViewHolder extends RecyclerView.ViewHolder {

        TextView textData;
        TextView name_text;
        TextView info_text;
        TextView amount_wine;
        ImageView boxOrBottle;
        RelativeLayout relativeLayoutReminder;

        ReminderViewHolder(View itemView) {
            super(itemView);
            boxOrBottle = (ImageView) itemView.findViewById(R.id.image_wine_r);
            textData = (TextView)itemView.findViewById(R.id.text_data_r);
            name_text = (TextView)itemView.findViewById(R.id.text_main_in_r);
            info_text = (TextView)itemView.findViewById(R.id.secondary_text_in_r);
            amount_wine = (TextView)itemView.findViewById(R.id.text_amount_wine_r);
            relativeLayoutReminder = (RelativeLayout) itemView.findViewById(R.id.reminder_rl);
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
