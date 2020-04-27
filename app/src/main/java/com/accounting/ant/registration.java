package com.accounting.ant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void nextLogin(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
