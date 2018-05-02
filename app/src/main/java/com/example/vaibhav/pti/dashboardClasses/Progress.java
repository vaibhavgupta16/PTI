package com.example.vaibhav.pti.dashboardClasses;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vaibhav.pti.R;
import com.example.vaibhav.pti.adapters.ProgressAdapter;
import com.example.vaibhav.pti.modelClasses.Progress_model;
import com.example.vaibhav.pti.modelClasses.URLSettup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.vaibhav.pti.Login.MY_PREFS;

public class Progress extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    RecyclerView recyclerView;
    String[] test = {"FA-1", "FA-2", "FA-3", "FA-4", "SA-1", "SA-2"};
    RequestQueue queue;
    String TAG = "courserequest", regid;
    SharedPreferences sharedPreferences;
    Progress_model model;
    ArrayList<Progress_model> arrayList = new ArrayList<Progress_model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        sharedPreferences = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        regid = sharedPreferences.getString("Sturegid", null);
        spinner = findViewById(R.id.testspinner);
        recyclerView = findViewById(R.id.progressrecy);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, test);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String test = parent.getItemAtPosition(position).toString();
        queue = Volley.newRequestQueue(this);
        String url = URLSettup.url + "act=Progress_Report&stu_reg=" + regid + "&test_name=" + test;
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
                            String res = jobj.getString("result");

                            if (res.equals("Successfull")) {
                                JSONArray jarr = jobj.getJSONArray("data");
                                for (int i = 0; i < jarr.length(); i++) {
                                    JSONObject jobj1 = jarr.getJSONObject(i);
                                    String cname = jobj1.getString("class_name");
                                    String sub = jobj1.getString("sub_name");
                                    String smark = jobj1.getString("stu_marks");
                                    String tmark = jobj1.getString("total_marks");
                                    model = new Progress_model(cname, sub, smark, tmark);
                                    arrayList.add(model);
                                }
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Progress.this);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(new ProgressAdapter(Progress.this, arrayList));


                            } else {
                                Toast.makeText(getApplicationContext(), "Some Error Occured", Toast.LENGTH_SHORT).show();
                            }
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
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
