package com.example.vaibhav.pti.DashboardClasses;

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
import com.example.vaibhav.pti.Adapters.AttendanceAdapter;
import com.example.vaibhav.pti.ModelClasses.Attendance_model;
import com.example.vaibhav.pti.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import static com.example.vaibhav.pti.Login.MY_PREFS;

public class Attendance extends AppCompatActivity {
    RecyclerView recyclerView;
    RequestQueue queue;
    String TAG = "courserequest";
    SharedPreferences sharedPreferences;
    Date date;
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
        queue = Volley.newRequestQueue(this);
        String url = "http://parportal.000webhostapp.com/login.php?act=Attendance_Details&stu_reg_id=" + sturegid;
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


}
