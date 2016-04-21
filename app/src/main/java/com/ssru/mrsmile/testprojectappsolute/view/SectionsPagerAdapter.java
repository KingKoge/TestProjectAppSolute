package com.ssru.mrsmile.testprojectappsolute.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new NewsFragment();
                break;
            case 1:
                fragment = new FacultyFragment();
                break;
            case 2:
                fragment = new FollowFragment();
                break;
            case 3:
                fragment = new ContactFragment();
                break;
        }
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragment;
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ข่าวสาร";
            case 1:
                return "ตารางเรียน";
            case 2:
                return "ติดตาม";
            case 3:
                return "ติดต่อเรา";
            default:
                return null;
        }
    }


}