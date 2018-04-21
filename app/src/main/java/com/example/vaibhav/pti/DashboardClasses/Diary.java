package com.example.vaibhav.pti.DashboardClasses;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vaibhav.pti.Adapters.DiaryAdapter;
import com.example.vaibhav.pti.ModelClasses.Diary_model;
import com.example.vaibhav.pti.ModelClasses.URLSettup;
import com.example.vaibhav.pti.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Diary extends AppCompatActivity {
    Diary_model diary;
    String TAG = "courserequest";
    RequestQueue queue;
    RecyclerView recylerView;
    ArrayList<Diary_model> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        recylerView = findViewById(R.id.diaryrecy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        queue = Volley.newRequestQueue(this);
        String url = URLSettup.url + "act=Notice";
        final ProgressDialog pDialog = new ProgressDialog(this);
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
                                String notice = jobj1.getString("Notice");
                                String date = jobj1.getString("Date");
                                String cl = jobj1.getString("class_name");
                                diary = new Diary_model(cl, notice, date);
                                arrayList.add(diary);

                            }
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Diary.this);
                            recylerView.setLayoutManager(layoutManager);
                            recylerView.setItemAnimator(new DefaultItemAnimator());
                            recylerView.setAdapter(new DiaryAdapter(Diary.this, arrayList));


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


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
