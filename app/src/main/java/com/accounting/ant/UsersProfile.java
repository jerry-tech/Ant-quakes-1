package com.accounting.ant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsersProfile extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener,ChangePassFragment.OnFragmentInteractionListener{

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ChangePassFragment  passFragment;
    private ProfileFragment  profileFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);


        viewPager = findViewById(R.id.viewPagerProfile);

        tabLayout = findViewById(R.id.tabProfile);

        passFragment = new ChangePassFragment();
        profileFragment = new ProfileFragment();

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabIconTint(ContextCompat.getColorStateList(UsersProfile.this, R.color.background_main));

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //adding tabs
        viewPagerAdapter.addFragment(profileFragment,"Profile");
        viewPagerAdapter.addFragment(passFragment,"Change Password");
        viewPager.setAdapter(viewPagerAdapter);


        //adding icons to tabs
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.person_35dp);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.regform_lock_24dp);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO: Update argument type and name
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitle.add(title);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }

    /* method called in a click listener in the layout */
    public void gotoDashBoard(View view){
        Intent intent = new Intent(this,UserOptions.class);
        startActivity(intent);
    }
}
