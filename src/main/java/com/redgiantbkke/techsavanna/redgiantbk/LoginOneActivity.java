package com.redgiantbkke.techsavanna.redgiantbk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginOneActivity extends AppCompatActivity {
    @Bind(R.id.input_phone)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_one);


        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboard=new Intent(LoginOneActivity.this,DashboardActivity.class);
                startActivity(dashboard);
            }
        });
    }
}
