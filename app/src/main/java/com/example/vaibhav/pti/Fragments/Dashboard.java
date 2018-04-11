package com.example.vaibhav.pti.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaibhav.pti.Adapters.HomeAdapter;
import com.example.vaibhav.pti.R;

public class Dashboard extends Fragment {
    RecyclerView recyclerView;
    String title[] = {"Attendance", "Daily Dairy", "Progress Graph", "Parent Detail", "Online Chat"};
    int image[] = {R.drawable.logo,R.drawable.diary, R.drawable.logo, R.drawable.logo, R.drawable.chathome};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView=rootview.findViewById(R.id.dashrecy);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new HomeAdapter(title,image,getActivity()));
        return rootview;
    }


}
