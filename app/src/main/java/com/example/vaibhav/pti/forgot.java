package com.example.vaibhav.pti;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.vaibhav.pti.ModelClasses.URLSettup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class forgot extends AppCompatActivity implements View.OnClickListener {
    EditText email;
    Button submit;
    RequestQueue queue;
    String TAG = "courserequest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        email = findViewById(R.id.editfor);
        submit = findViewById(R.id.btnsubmit);

        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final String s = email.getText().toString().trim();
        queue = Volley.newRequestQueue(this);
        String url = URLSettup.url + "act=Forgot_Password&email=" + s;
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
                            //String success = jobj.getString("result");
                            JSONArray jarr = jobj.getJSONArray("data");
                            for (int i = 0; i < jarr.length(); i++) {
                                JSONObject jobj1 = jarr.getJSONObject(i);
                                String pass = jobj1.getString("password");
                                Log.e("password", "" + pass);
                                email(s, "Password", "Hello Your password is:" + pass);
                                //txt.setText(Html.fromHtml("<a href=\"mailto:sipika@btes.co.in\">Send Feedback</a>"));
                                //txt.setMovementMethod(LinkMovementMethod.getInstance());
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

    public void email(String email, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Email"));
        Toast.makeText(getApplicationContext(), "Password has been sent to email Id provided", Toast.LENGTH_SHORT).show();

    }
}
