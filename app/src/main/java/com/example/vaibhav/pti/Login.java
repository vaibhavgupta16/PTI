package com.example.vaibhav.pti;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vaibhav.pti.ModelClasses.Parent_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {
    public static final String MY_PREFS ="My Prefrences" ;
    Button button_login;
    EditText editText_password,editText_email;
    TextView textView_forgot;
    RequestQueue queue;
    String TAG="courserequest", tdemail,tdpassword;
    Parent_model p;
    ArrayList<Parent_model> arrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login=findViewById(R.id.login);
        
        editText_password=findViewById(R.id.password);
        editText_email=findViewById(R.id.phone);
        textView_forgot=findViewById(R.id.forgot_password);

        button_login.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        Initialize();
        switch (v.getId()){
            case R.id.login:
                if(validations()){
                    queue = Volley.newRequestQueue(this);
                    String url = "http://parportal.000webhostapp.com/login.php?act=Parent_Login&email="+tdemail+"&password="+tdpassword;
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
                                        String success = jobj.getString("result");
                                        JSONArray jarr=jobj.getJSONArray("data");
                                        for (int i=0;i<jarr.length();i++)
                                        {
                                            JSONObject jobj1=jarr.getJSONObject(i);
                                            String name=jobj1.getString("first_name");
                                            String phone=jobj1.getString("phone_no");
                                            String address=jobj1.getString("address");
                                            String email=jobj1.getString("email");
                                            p=new Parent_model(name,address,email,phone);
                                            arrayList.add(p);
                                        }
                                        if (success.equals("Login Successfull")) {
                                            SharedPreferences.Editor editor= getSharedPreferences(MY_PREFS,MODE_PRIVATE).edit();
                                            editor.putBoolean("loginkey",true);
                                            editor.apply();
                                            Intent intent = new Intent(Login.this,Home.class);
                                            startActivity(intent);
                                            Toast.makeText(getApplicationContext(), "Sign in Complete", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Phone No. or Password Is Incorrect", Toast.LENGTH_SHORT).show();
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
                else {
                     Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_LONG).show();
                }
                break;
            }

    }

    //GETTING DATA FROM EDITTEXT AND STORING THEM IN STRING
    public void Initialize(){
        tdemail =editText_email.getText().toString();
        tdpassword=editText_password.getText().toString();
    }

    private boolean validations(){
        boolean value=true;
        if(tdemail.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(tdemail).matches()){
            editText_email.setError("Required Field");
            value=false;
        }

        if(tdpassword.isEmpty()){
            editText_password.setError("Required Field");
            value=false;
        }
        return value;
    }
}
