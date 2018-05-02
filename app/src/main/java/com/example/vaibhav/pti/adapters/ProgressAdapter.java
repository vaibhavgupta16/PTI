package com.example.vaibhav.pti.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.pti.R;
import com.example.vaibhav.pti.dashboardClasses.Progress;
import com.example.vaibhav.pti.modelClasses.Progress_model;

import java.util.ArrayList;


public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.MyViewHolder> {
    Progress progress;
    ArrayList<Progress_model> arrayList;

    public ProgressAdapter(Progress progress, ArrayList<Progress_model> arrayList) {
        this.arrayList = arrayList;
        this.progress = progress;
    }

    @Override
    public ProgressAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progresscust, parent, false);
        return new ProgressAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgressAdapter.MyViewHolder holder, int position) {
        holder.txtsub.setText(arrayList.get(position).getSub());
        holder.txttm.setText(arrayList.get(position).getTmark());
        holder.txtmo.setText(arrayList.get(position).getSmark());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtsub, txtmo, txttm;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtsub = itemView.findViewById(R.id.txtsubject);
            txtmo = itemView.findViewById(R.id.txtmo);
            txttm = itemView.findViewById(R.id.txttm);
        }
    }
}
