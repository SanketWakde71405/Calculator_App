package com.example.calculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryAdapter extends  RecyclerView.Adapter<HistoryAdapter.MyViewHolder>{

    Context context;
    ArrayList<String> historyList;
    boolean themeMode;
    ItemClickListener itemClickListener;

    public boolean isThemeMode() {
        return themeMode;
    }

    public void setThemeMode(boolean themeMode) {
        this.themeMode = themeMode;
    }

    public HistoryAdapter(Context context, ArrayList<String> data, ItemClickListener itemClickListener) {
        this.context = context;
        this.historyList = data;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyViewHolder holder, int position) {

        String calculations = historyList.get(position);
        String [] strArr= calculations.split("=");
        holder.updateCalculations.setText(strArr[0]);
        holder.updateResult.setText(strArr[1]);

        if (isThemeMode()){
            holder.historyItem.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.updateCalculations.setTextColor(Color.parseColor("#373737"));
            holder.updateResult.setTextColor(Color.parseColor("#373737"));
        }else{
            holder.historyItem.setCardBackgroundColor(Color.parseColor("#003661"));
            holder.updateCalculations.setTextColor(Color.parseColor("#FBFBFB"));
            holder.updateResult.setTextColor(Color.parseColor("#FBFBFB"));
        }

        holder.historyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(holder.updateResult.getText().toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView updateCalculations, updateResult;
        MaterialCardView historyItem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            updateCalculations=itemView.findViewById(R.id.input_user);
            updateResult=itemView.findViewById(R.id.result_texts);
            historyItem=itemView.findViewById(R.id.history_Item);

        }
    }
}
