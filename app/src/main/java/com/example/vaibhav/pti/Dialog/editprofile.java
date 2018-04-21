package com.example.vaibhav.pti.Dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.vaibhav.pti.R;

public class editprofile implements View.OnClickListener {
    RequestQueue queue;
    String TAG = "courserequest";
    Context context;
    private EditText editname, editemail, editaddress, editphone;
    private Button btnok;
    private TextView x;
    private Dialog dialog;

    public editprofile(Context context, String name, String email, String Address, String Phone) {
        this.context = context;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.editprofile);

        editname = dialog.findViewById(R.id.par_namee);
        editemail = dialog.findViewById(R.id.par_emaile);
        editaddress = dialog.findViewById(R.id.par_addresse);
        editphone = dialog.findViewById(R.id.par_phonee);
        btnok = dialog.findViewById(R.id.btnedite);
        x = dialog.findViewById(R.id.x);

        btnok.setOnClickListener(this);
        x.setOnClickListener(this);
        editname.setText(name);
        editemail.setText(email);
        editphone.setText(Phone);
        editaddress.setText(Address);

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnedite:
                /*queue = Volley.newRequestQueue(context);
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
                */
                break;
            case R.id.x:
                dialog.dismiss();
                break;
        }


    }
}
