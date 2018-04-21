package com.example.vaibhav.pti.dialog;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vaibhav.pti.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;

public class Viewteacher {
    RequestQueue queue;
    TextView name, contact, email;
    Button ok;
    Dialog dialog;

    public Viewteacher(Context context, String tid) {

        dialog = new Dialog(context);
        //dialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.viewteacherdetail);

        name = dialog.findViewById(R.id.tname);
        contact = dialog.findViewById(R.id.tcontact);
        email = dialog.findViewById(R.id.temail);
        ok = dialog.findViewById(R.id.dialog_close);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        queue = Volley.newRequestQueue(context);
        String url = "http://parportal.000webhostapp.com/login.php?act=Teacher_Data&Teacher_Id=" + tid;
        final ProgressDialog pDialog = new ProgressDialog(context);
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
                                name.setText(jobj1.getString("first_name"));
                                contact.setText(jobj1.getString("contact"));
                                email.setText(jobj1.getString("email"));
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

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}
