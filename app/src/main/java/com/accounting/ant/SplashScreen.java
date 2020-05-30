package com.accounting.ant;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity{

    ProgressBar progressBar;
    TextView textBelow;
    Animation fromText;
    ImageView imgLogo;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //calling the loadLocale method onCreate which holds the languages shared preference
//        loadLocale();

        setContentView(R.layout.activity_splash_screen);

        //getting the (id) of the spinner from the xml
        progressBar = findViewById(R.id.loading_spinner);//NOT USED FOR nada!
        textBelow = findViewById(R.id.dialCodeWeb);
        imgLogo = findViewById(R.id.splashLogo);



        //loading the animation from the anim folder and assigning it
        fromText = AnimationUtils.loadAnimation(this, R.anim.frombutton);
        textBelow.setAnimation(fromText);

        //calling animated intent for next page
       moveLogin();
    }

    //creating animated intent for next page
    public void moveLogin() {

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            finish();
        },4000);

    }
}
