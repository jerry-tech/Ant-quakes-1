package com.accounting.ant.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.accounting.ant.DashDataBundle;
import com.accounting.ant.DashAirtime;
import com.accounting.ant.DashBulkAirtime;
import com.accounting.ant.DashBulkSms;
import com.accounting.ant.DashElectric;
import com.accounting.ant.DashExamPin;
import com.accounting.ant.DashSubscription;
import com.accounting.ant.DashTransfer;
import com.accounting.ant.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4, R.string.tab_text_5, R.string.tab_text_6, R.string.tab_text_7, R.string.tab_text_8};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//        return PlaceholderFragment.newInstance(position + 1);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = DashAirtime.newInstance("Air", "time");
                break;
            case 1:
                fragment = DashSubscription.newInstance("zz", "dd");
                break;
            case 2:
                fragment = DashTransfer.newInstance("Gotv", "Dstv");
                break;
            case 3:
                fragment = DashBulkSms.newInstance("Gotv", "Dstv");
                break;
            case 4:
                fragment = DashElectric.newInstance("Gotv", "Dstv");
                break;
            case 5:
                fragment = DashBulkAirtime.newInstance("Gotv", "Dstv");
                break;
            case 6:
                fragment = DashDataBundle.newInstance("Gotv", "Dstv");
                break;
            case 7:
                fragment = DashExamPin.newInstance("Gotv", "Dstv");
                break;
        }


        return fragment;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 8 total pages.
        return 8;
    }
}