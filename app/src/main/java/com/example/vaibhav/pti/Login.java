package com.example.vaibhav.pti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button button_login,button_newUser;
    EditText editText_password;
    AutoCompleteTextView autoCompleteTextView_email;
    TextView textView_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login=findViewById(R.id.login);
        button_newUser=findViewById(R.id.new_User);

        editText_password=findViewById(R.id.password);
        autoCompleteTextView_email=findViewById(R.id.email);
        textView_forgot=findViewById(R.id.forgot_password);

        button_login.setOnClickListener(this);
        button_newUser.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                break;
            case R.id.new_User:
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
                break;
        }

    }
}
