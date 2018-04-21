package com.example.vaibhav.pti.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibhav.pti.DashboardClasses.Attendance;
import com.example.vaibhav.pti.ModelClasses.Attendance_model;
import com.example.vaibhav.pti.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.vaibhav.pti.Login.MY_PREFS;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {
    private ArrayList<Attendance_model> arrayList = new ArrayList<>();
    private Attendance attendance;
    private Date date = new Date();

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
        SharedPreferences sharedPreferences = attendance.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        String res = sharedPreferences.getString("response", null);
        String a = arrayList.get(position).getAtt();
        Log.e("...Attendance", "" + a);

        if (res.equals("{\"result\":\"Failed!\"}")) {
            holder.txt.setText(R.string.attennotmarked);
            holder.img.setVisibility(View.VISIBLE);
        } else {
            if (a.equals("null")) {
                holder.txt.setText(R.string.attennotmarked);
                holder.img.setVisibility(View.VISIBLE);
            } else {
                holder.txt.setText(R.string.attenmarked);
                holder.txtclass.setText(String.format("Class: %s", arrayList.get(position).getCl()));
                holder.txtname.setText(arrayList.get(position).getName());
                holder.txtatt.setText(arrayList.get(position).getAtt());
                holder.txtdate.setText(String.format("Date: %s", ft.format(date)));
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtatt, txtdate, txt, txtclass;
        ImageView img;

        MyViewHolder(View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtatt = itemView.findViewById(R.id.txtatt);
            txtdate = itemView.findViewById(R.id.txtdate);
            txt = itemView.findViewById(R.id.txt);
            img = itemView.findViewById(R.id.img);
            txtclass = itemView.findViewById(R.id.txtclass);
        }
    }
}
