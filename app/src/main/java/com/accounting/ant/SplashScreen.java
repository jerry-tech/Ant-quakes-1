package com.accounting.ant;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
        Spinner language = findViewById(R.id.selectLanguage);
        progressBar = findViewById(R.id.loading_spinner);//NOT USED FOR nada!
        textBelow = findViewById(R.id.dialCodeWeb);
        imgLogo = findViewById(R.id.splashLogo);

        // using the array adapter class && the array adapter public constructor which accepts three parameters to get all items
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.languageItems,
                R.layout.spinner_item
        );

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        language.setAdapter(adapter);
//        language.setOnItemSelectedListener(this);


        //loading the animation from the anim folder and assigning it
        fromText = AnimationUtils.loadAnimation(this, R.anim.frombutton);
        textBelow.setAnimation(fromText);

        //calling animated intent for next page
       moveLogin();
    }

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    //second method under the itemListener interface
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String selectedText = parent.getSelectedItem().toString().trim();
//        if (selectedText.equalsIgnoreCase("Yoruba")) {
//
//            // calling the setLocale method
//            setLocale("yo");
//            recreate();//inbuilt method in the Activity class
//          //calling the method holding the intent
//            moveLogin();
//        }else if(selectedText.equalsIgnoreCase("English")){
//            // calling the setLocale method
//            setLocale("en");
//            recreate();//inbuilt method in the Activity class
//            //calling the method holding the intent
//            moveLogin();
//        }
//    }

    //second method under the itemListener interface
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

//    //method for setting language on selection
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    private void setLocale(String lang) {
//        Locale locale = new Locale(lang);
//        Locale.setDefault(locale);
//
//        Configuration configuration = new Configuration();
//
//        //configuration.setLocale(locale);
//        configuration.locale = locale;
//
//        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
//
//        SharedPreferences.Editor sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE).edit();
//        sharedPreferences.putString("Language", lang);
//        sharedPreferences.apply();
//    }

//    //loading the languages in android shared preferences in the onCreate method
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    public void loadLocale() {
//
//        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
//        String language = prefs.getString("Language", "");
//        setLocale(language);
//    }

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
