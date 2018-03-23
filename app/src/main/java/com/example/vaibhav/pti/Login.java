package com.example.vaibhav.pti;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button button_login,button_newUser;
    EditText editText_password,editText_phone;
    TextView textView_forgot;
    RequestQueue queue;
    String TAG="courserequest",tdphone,tdpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login=findViewById(R.id.login);
        button_newUser=findViewById(R.id.new_User);

        editText_password=findViewById(R.id.password);
        editText_phone=findViewById(R.id.phone);
        textView_forgot=findViewById(R.id.forgot_password);

        button_login.setOnClickListener(this);
        button_newUser.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Initialize();
        final String result="Login Failed!";
        switch (v.getId()){
            case R.id.login:
                if(!validations()){

                }
                else {
                    queue = Volley.newRequestQueue(this);
                    String url = "http://iamguptag.000webhostapp.com/Register.php?act=Login&td_phone=" + tdphone + "&td_password=" + tdpassword + "";
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
                                        if (success.equals(result)) {
                                            Toast.makeText(getApplicationContext(), "Phone No. or Password Is Incorrect", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Sign in Complete", Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.new_User:
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
                break;
        }

    }

    //GETTING DATA FROM EDITTEXT AND STORING THEM IN STRING
    public void Initialize(){
        tdphone=editText_phone.getText().toString();
        tdpassword=editText_password.getText().toString();
    }

    private boolean validations(){
        boolean value=true;
        if(tdphone.isEmpty()||tdphone.length()>11){
            editText_phone.setError("Required Field");
            value=false;
        }

        if(tdpassword.isEmpty()){
            editText_password.setError("Required Field");
            value=false;
        }
        return value;
    }
}
