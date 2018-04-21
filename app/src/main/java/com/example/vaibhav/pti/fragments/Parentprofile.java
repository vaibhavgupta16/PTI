package com.example.vaibhav.pti.fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vaibhav.pti.R;
import com.example.vaibhav.pti.adapters.ParentAdapter;
import com.example.vaibhav.pti.modelClasses.Parent_model;
import com.example.vaibhav.pti.modelClasses.URLSettup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.vaibhav.pti.Login.MY_PREFS;


public class Parentprofile extends Fragment {

    RecyclerView recyclerView;
    RequestQueue queue;
    String TAG = "courserequest";
    Parent_model p;
    ArrayList<Parent_model> arrayList = new ArrayList<>();
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_parentprofile, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        String sturegid = sharedPreferences.getString("Sturegid", null);
        recyclerView = root.findViewById(R.id.parrec);

        queue = Volley.newRequestQueue(getActivity());
        String url = URLSettup.url + "act=Parent_Details&stu_reg_id=" + sturegid;
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        Log.e("==url==", "" + url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("===response==", "==" + response);
                        pDialog.dismiss();

                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jarr = jobj.getJSONArray("data");
                            for (int i = 0; i < jarr.length(); i++) {
                                JSONObject jobj1 = jarr.getJSONObject(i);
                                String name = jobj1.getString("first_name");
                                String phone = jobj1.getString("phone_no");
                                String address = jobj1.getString("address");
                                String email = jobj1.getString("email");
                                String image = jobj1.getString("image");
                                p = new Parent_model(name, address, email, phone, image);
                                arrayList.add(p);
                            }
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(new ParentAdapter(getActivity(), arrayList));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("===error==", "==" + error);
                pDialog.dismiss();
            }
        });
        stringRequest.setTag(TAG);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return root;
    }


}
