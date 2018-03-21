package com.example.vaibhav.pti;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    EditText first,last,email,phone,passw,cpassw;
    Button register;
    RequestQueue queue;
    String TAG="courserequest";
    String tdname,tdlast,tdemail,tdphone,tdpassw,tdcpassw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        first=findViewById(R.id.first);
        last=findViewById(R.id.last);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        passw=findViewById(R.id.password);
        cpassw=findViewById(R.id.confpass);

        register=findViewById(R.id.register);
        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        tdname=first.getText().toString();
        tdlast=last.getText().toString();
        tdemail=email.getText().toString();
        tdphone=phone.getText().toString();
        tdpassw=passw.getText().toString();
        tdcpassw=cpassw.getText().toString();
        final String confirm="Success!";
        switch (v.getId()){
            case  R.id.register:
                queue = Volley.newRequestQueue(this);
                String url ="http://iamguptag.000webhostapp.com/Register.php?act=Insertion&td_first="+tdname+"&td_last="+tdlast+"&td_email="+tdemail+"&td_password="+tdpassw+"&td_confirmpass="+tdcpassw+"&td_phone="+tdphone+"";
                final ProgressDialog pDialog = new ProgressDialog(this);
                pDialog.setMessage("Loading...");
                pDialog.show();

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("===response==","=="+response);
                                pDialog.dismiss();

                                try {
                                    JSONObject jobj=new JSONObject(response);
                                    String success=jobj.getString("Result");
                                    Log.e("==Result==",""+success );
                                    if(success.equals(confirm))
                                    {
                                        Toast.makeText(getApplicationContext(),"User account created successfully",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"Some Error Occured",Toast.LENGTH_SHORT).show();
                                    }

                                    Log.e("==Result==",""+success );


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("===error==","=="+error);
                        pDialog.dismiss();
                    }
                });
                stringRequest.setTag(TAG);

                // Add the request to the RequestQueue.
                queue.add(stringRequest);


                break;
        }
    }
}
