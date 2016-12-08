package com.igdtuw.megha.megha.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.igdtuw.megha.megha.Constants.Constants;
import com.igdtuw.megha.megha.Fragments.CoursesFragment;

/**
 * Created by megha on 23/7/16.
 */
public class CourseAdapter extends FragmentStatePagerAdapter implements Constants {
    Context context = null;
    private int numofScreens;

    public CourseAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        numofScreens = 3;
    }

    @Override
    public int getCount() {
        return numofScreens;
    }

    @Override
    public Fragment getItem(int position) {
        return CoursesFragment.CoursesIndividualFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return ONLINE_COURSES;
        else if(position == 1)
            return  GRADUATE_COURSES;
        return  EXTERNAL_CLASSROOM;
    }

}
