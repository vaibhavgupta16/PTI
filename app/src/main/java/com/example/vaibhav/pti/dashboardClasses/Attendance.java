package com.example.vaibhav.pti.dashboardClasses;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
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
import com.example.vaibhav.pti.R;
import com.example.vaibhav.pti.adapters.AttendanceAdapter;
import com.example.vaibhav.pti.modelClasses.Attendance_model;
import com.example.vaibhav.pti.modelClasses.URLSettup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.vaibhav.pti.Login.MY_PREFS;

public class Attendance extends AppCompatActivity {
    RecyclerView recyclerView;
    RequestQueue queue;
    String TAG = "courserequest";
    SharedPreferences sharedPreferences;
    Attendance_model attendance;
    ArrayList<Attendance_model> arrayList = new ArrayList<>();
    private String name, attd, cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        sharedPreferences = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        String sturegid = sharedPreferences.getString("Sturegid", null);

        recyclerView = findViewById(R.id.atterecy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        queue = Volley.newRequestQueue(this);
        String url = URLSettup.url + "act=Attendance_Details&stu_reg_id=" + sturegid;
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
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
                        editor.putString("response", response);
                        editor.commit();
                        if (response.equalsIgnoreCase("{\"result\":\"Failed!\"}")) {
                            try {
                                JSONObject jobj = new JSONObject(response);
                                String result = jobj.getString("result");
                                attendance = new Attendance_model(result);
                                arrayList.add(attendance);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Attendance.this);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(new AttendanceAdapter(Attendance.this, arrayList));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                JSONObject jobj = new JSONObject(response);
                                JSONArray jarr = jobj.getJSONArray("data");
                                for (int i = 0; i < jarr.length(); i++) {
                                    JSONObject jobj1 = jarr.getJSONObject(i);
                                    name = jobj1.getString("Name");
                                    attd = jobj1.getString("Date");
                                    cl = jobj1.getString("Class");
                                    attendance = new Attendance_model(attd, name, cl);
                                    arrayList.add(attendance);
                                }
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Attendance.this);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(new AttendanceAdapter(Attendance.this, arrayList));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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
        return true;
    }
}
