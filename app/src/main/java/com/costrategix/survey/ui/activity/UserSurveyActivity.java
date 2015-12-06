package com.costrategix.survey.ui.activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.costrategix.survey.R;
import com.costrategix.survey.access.QuestionAccess;
import com.costrategix.survey.database.DatabaseHelper;
import com.costrategix.survey.database.DatabaseManager;
import com.costrategix.survey.log.AppLog;
import com.costrategix.survey.ui.fragment.ScreenSlidePageFragment;

import net.sqlcipher.database.SQLiteDatabase;

public class UserSurveyActivity extends FragmentActivity {

    private static int sNumberOfQuestions;

    private ViewPager mPager;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_survey);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);


        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDataBase(new DatabaseHelper(UserSurveyActivity.this));

        QuestionAccess questionAccess = new QuestionAccess(sqLiteDatabase);
        sNumberOfQuestions = questionAccess.getCount();
        DatabaseManager.getInstance().closeDataBase();

        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.addOnPageChangeListener(mPageChangeListener);

    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return sNumberOfQuestions;
        }
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            AppLog.logString("" + position);
        }

        @Override
        public void onPageSelected(int position) {
            AppLog.logString("" + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
