package com.accounting.ant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        button = findViewById(R.id.btnRegister);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Registration.this,AuthOtp.class);
            startActivity(intent);
        });
    }

    //intent and animation to go to login page
    public void nextLogin(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }


}
