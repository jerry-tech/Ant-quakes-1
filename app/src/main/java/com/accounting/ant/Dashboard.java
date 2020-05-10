package com.accounting.ant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    //Creating the shared preference
    SharedPreferences preferences;

    //Creating a variable to house the username for the session
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Getting the email of the user with shared preference.
        preferences = getSharedPreferences("user_details", Context.MODE_PRIVATE);
        username = preferences.getString("username", null);

        //Checking if the session works.
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();

    }
}
