package com.example.vaibhav.pti.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.pti.R;
import com.example.vaibhav.pti.dashboardClasses.Teacher;
import com.example.vaibhav.pti.dialog.Viewteacher;
import com.example.vaibhav.pti.modelClasses.Teacher_model;

import java.util.ArrayList;


public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyViewHolder> {
    private Teacher teacher;
    private ArrayList<Teacher_model> arrayList;

    public TeacherAdapter(Teacher teacher, ArrayList<Teacher_model> arrayList) {
        this.arrayList = arrayList;
        this.teacher = teacher;
    }

    @Override
    public TeacherAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teachercust, parent, false);
        return new TeacherAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TeacherAdapter.MyViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getTname());
        holder.sub.setText(arrayList.get(position).getTsub());
        holder.id.setText(arrayList.get(position).getTid());
        final String tid = holder.id.getText().toString().trim();
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Viewteacher(teacher, tid);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, id, sub, edit;

        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtname);
            id = itemView.findViewById(R.id.txtid);
            sub = itemView.findViewById(R.id.txtsub);
            edit = itemView.findViewById(R.id.btnview);
        }
    }
}
