package com.example.vaibhav.pti.dialog;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.example.vaibhav.pti.R;
import com.example.vaibhav.pti.modelClasses.URLSettup;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.vaibhav.pti.Login.MY_PREFS;

public class Editprofile implements View.OnClickListener {
    RequestQueue queue;
    String TAG = "courserequest";
    Context context;
    private EditText editname, editemail, editaddress, editphone;
    private Button btnok;
    private TextView x;
    private Dialog dialog;
    private String em, add, phn, na, regid;
    private SharedPreferences sharedPreferences;
    public Editprofile(Context context, String name, String email, String Address, String Phone) {
        this.context = context;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.editprofile);

        editname = dialog.findViewById(R.id.par_namee);
        editemail = dialog.findViewById(R.id.par_emaile);
        editaddress = dialog.findViewById(R.id.par_addresse);
        editphone = dialog.findViewById(R.id.par_phonee);
        btnok = dialog.findViewById(R.id.btnedite);
        x = dialog.findViewById(R.id.x);

        sharedPreferences = dialog.getContext().getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        regid = sharedPreferences.getString("Sturegid", null);
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
                edit();
                queue = Volley.newRequestQueue(context);
                String url = URLSettup.url + "act=Update_Profile&first_name=" + na + "&phone_no=" + phn + "&address=" + add + "&email=" + em + "&stu_reg_id=" + regid;
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
                                    JSONObject obj = new JSONObject(response);
                                    String res = obj.getString("Result");
                                    if (res.equals("Successfully")) {
                                        Toast.makeText(dialog.getContext(), "Details Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(dialog.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.x:
                dialog.dismiss();
                break;
        }

    }

    void edit() {
        na = editname.getText().toString().trim();
        em = editemail.getText().toString();
        add = editaddress.getText().toString();
        phn = editphone.getText().toString();
    }

}
