package com.accounting.ant;

import android.os.Bundle;
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
}
