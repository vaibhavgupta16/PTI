package com.example.vaibhav.pti.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.pti.DashboardClasses.Diary;
import com.example.vaibhav.pti.ModelClasses.Diary_model;
import com.example.vaibhav.pti.R;

import java.util.ArrayList;


public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.MyViewHolder> {
    private ArrayList<Diary_model> arrayList;
    private Diary diary;

    public DiaryAdapter(Diary diary, ArrayList<Diary_model> arrayList) {
        this.arrayList = arrayList;
        this.diary = diary;
    }

    @Override
    public DiaryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.diarycust, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DiaryAdapter.MyViewHolder holder, int position) {
        holder.class_name.setText(String.format("Class: %s", arrayList.get(position).getClass_name()));
        holder.date.setText(String.format("Date: %s", arrayList.get(position).getDate()));
        holder.notice.setText(arrayList.get(position).getNotice());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, class_name, notice;

        MyViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textView2);
            class_name = itemView.findViewById(R.id.cn);
            notice = itemView.findViewById(R.id.n);
        }
    }
}
