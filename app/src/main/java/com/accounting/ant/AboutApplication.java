package com.accounting.ant;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutApplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_application);

        TextView linkText = findViewById(R.id.webLink);

        //link to antquakes
        String links = "<a href='http://www.antquakes.com.ng'>http://www.antquakes.com.ng</a>";
        linkText.setText(Html.fromHtml(links));
        linkText.setMovementMethod(LinkMovementMethod.getInstance());

        //link to developers

        TextView developers = findViewById(R.id.teamDevelopers);
        String developersLink = "<span><a href='https://www.linkedin.com/in/osaigbovojamesidada'>Osaigbovo James Idada,</a> <a href='https://www.linkedin.com/in/udoh-jeremiah-87368a19b'> Jeremiah Udoh</a> and <a href='http://www.codeweb.com.ng'> CodeWeb Developers</a></span>";
        developers.setText(Html.fromHtml(developersLink));
        developers.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* onclick listener called from the layout */
    public void gotoDashBoard(View view){
        Intent intent = new Intent(this,UserOptions.class);
        startActivity(intent);
    }

    /* onclick listener called from the layout */
    public void gotoFeedBack(View view){
        Intent intent = new Intent(this,UsersFeedback.class);
        startActivity(intent);
    }


}
