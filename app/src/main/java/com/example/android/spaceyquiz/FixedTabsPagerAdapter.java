package com.example.android.spaceyquiz;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by bogda on 11/19/2017.
 */

public class FixedTabsPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public FixedTabsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new SpaceFragment();
        } else if (position == 1) {
            return new GoTFragment();
        } else {
            return new QuantumFragment();
        }
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.space_quiz);
        } else if (position == 1) {
            return mContext.getString(R.string.got_quiz);
        } else {
            return mContext.getString(R.string.quantum_quiz);
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
}
