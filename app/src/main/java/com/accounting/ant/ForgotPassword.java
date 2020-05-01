package com.accounting.ant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {

    TextView backLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        backLogin = findViewById(R.id.backBtnLogin);

        backLogin.setOnClickListener(v -> backToLogin());
    }
//    Intent for going back to login
    public void backToLogin(){
        Intent intent = new Intent(ForgotPassword.this,MainActivity.class);
        startActivity(intent);
    }
}

