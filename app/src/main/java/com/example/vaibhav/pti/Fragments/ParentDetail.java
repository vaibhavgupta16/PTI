package com.example.vaibhav.pti.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaibhav.pti.Adapters.ParentAdapter;
import com.example.vaibhav.pti.ModelClasses.Parent_model;
import com.example.vaibhav.pti.R;

import java.util.ArrayList;


public class ParentDetail extends Fragment {
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_parent_detail, container, false);
        recyclerView=rootview.findViewById(R.id.parentrecy);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new ParentAdapter(getActivity()));
        return rootview;
    }


}
