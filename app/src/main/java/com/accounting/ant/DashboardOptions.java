package com.accounting.ant;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.accounting.ant.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class DashboardOptions extends AppCompatActivity implements DashAirtime.OnFragmentInteractionListener,DashSubscription.OnFragmentInteractionListener,DashTransfer.OnFragmentInteractionListener, DashBulkSms.OnFragmentInteractionListener,DashElectric.OnFragmentInteractionListener,DashSavings.OnFragmentInteractionListener,DashAirToMoney.OnFragmentInteractionListener,DashExamPin.OnFragmentInteractionListener{
    String fragmentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_options);

        //getting the value from the intent
        fragmentId = getIntent().getStringExtra("frag");
        if(fragmentId != null){
            Log.i("Fragment",fragmentId);//log.i to view the value in the logcat /* Subject to removal*/
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        //using the click listener value to set the specific tab position
        int itemId = Integer.parseInt(fragmentId);
        viewPager.setCurrentItem(itemId);

        //floating action button
        FloatingActionButton fab = findViewById(R.id.fab);
        if(fragmentId != null) {
            fab.setOnClickListener(view -> Snackbar.make(view, fragmentId, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show());
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}