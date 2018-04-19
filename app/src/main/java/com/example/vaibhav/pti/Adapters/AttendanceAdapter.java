package com.example.vaibhav.pti.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.pti.DashboardClasses.Attendance;
import com.example.vaibhav.pti.ModelClasses.Attendance_model;
import com.example.vaibhav.pti.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {
    ArrayList<Attendance_model> arrayList = new ArrayList<>();
    Attendance attendance;
    Date date = new Date();

    public AttendanceAdapter(Attendance attendance, ArrayList<Attendance_model> arrayList) {
        this.arrayList = arrayList;
        this.attendance = attendance;
    }

    @Override
    public AttendanceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.atte_custom, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttendanceAdapter.MyViewHolder holder, int position) {
        SimpleDateFormat ft = new SimpleDateFormat("E dd/MM/yyyy");
        holder.txtname.setText(arrayList.get(position).getName());
        holder.txtatt.setText(arrayList.get(position).getAtt());
        holder.txtdate.setText("Date: " + ft.format(date));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtatt, txtdate;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtatt = itemView.findViewById(R.id.txtatt);
            txtdate = itemView.findViewById(R.id.txtdate);
        }
    }
}
