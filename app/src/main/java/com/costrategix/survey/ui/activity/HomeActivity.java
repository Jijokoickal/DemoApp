package com.costrategix.survey.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.costrategix.survey.R;


public class HomeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btnSubmitSurvey).setOnClickListener(submitSurveyClickListener);
        findViewById(R.id.btnViewSurvey).setOnClickListener(viewSurveyClickListener);

    }

    private void startSurvey() {
        Intent intent = new Intent(this, UserSurveyActivity.class);
        startActivity(intent);

    }

    private void viewSurvey() {
        Intent intent = new Intent(this, SurveyListActivity.class);
        startActivity(intent);
    }


    private View.OnClickListener submitSurveyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startSurvey();
        }
    };


    private View.OnClickListener viewSurveyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewSurvey();
        }
    };
}
