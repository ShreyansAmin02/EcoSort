package com.example.ecosort;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import DB.Bin;

public class BinAdapter extends RecyclerView.Adapter<BinAdapter.BinViewHolder> {
    private ArrayList<Bin> binList;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public BinAdapter(ArrayList<Bin> binList) {
        this.binList = binList;
    }

    @NonNull
    @Override
    public BinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bin, parent, false);
        return new BinViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BinViewHolder holder, int position) {
        Bin bin = binList.get(position);
        holder.binLocation.setText(bin.getLocation());
        holder.binType.setText(bin.getType());
    }

    @Override
    public int getItemCount() {
        return binList.size();
    }

    public class BinViewHolder extends RecyclerView.ViewHolder {
        TextView binLocation;
        TextView binType;

        public BinViewHolder(@NonNull View itemView) {
            super(itemView);
            binLocation = itemView.findViewById(R.id.TextBinLocation);
            binType = itemView.findViewById(R.id.TextBinType);
            itemView.setOnClickListener(v -> {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position);
                    }
                }
            });
        }

    }
}