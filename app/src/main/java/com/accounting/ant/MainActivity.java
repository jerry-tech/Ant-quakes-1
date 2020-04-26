package com.accounting.ant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView footerTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        footerTime = findViewById(R.id.footerTime);

        Calendar calendar = Calendar.getInstance();
        footerTime.setText(calendar.getTime().toString());
    }
    public void nextRegister(View view){
        Intent intent = new Intent(this,registration.class);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
