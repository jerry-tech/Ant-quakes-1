package com.accounting.ant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainWallet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wallet);


        EditText amountEdit = findViewById(R.id.amountPay);
        //getting the amount from the Fund wallet fragment
        amountEdit.setText(getIntent().getStringExtra("inputAmount"));

    }


    /* method called in a click listener in the layout */
    public void gotoFund(View view){
        Intent intent = new Intent(this, UserOptions.class);
        startActivity(intent);


    }
}
