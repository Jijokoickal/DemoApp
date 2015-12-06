package com.costrategix.survey.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.costrategix.survey.R;
import com.costrategix.survey.access.QuestionAccess;
import com.costrategix.survey.access.SurveyResponseAccess;
import com.costrategix.survey.database.DatabaseHelper;
import com.costrategix.survey.database.DatabaseManager;
import com.costrategix.survey.pojo.QuestionData;
import com.costrategix.survey.pojo.SurveyResponseData;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.List;


public class SurveyListActivity extends Activity {


    private ListView mListView;
    private List<SurveyResponseData> mSurveyList;
    private List<QuestionData> questionDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);

        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDataBase(new DatabaseHelper(SurveyListActivity.this));

        SurveyResponseAccess access = new SurveyResponseAccess(sqLiteDatabase);
        mSurveyList = (List<SurveyResponseData>) access.select(null, null);

        QuestionAccess questionAccess = new QuestionAccess(sqLiteDatabase);
        questionDataList = (List<QuestionData>) questionAccess.select(null, null);

        DatabaseManager.getInstance().closeDataBase();


        mListView = (ListView) findViewById(R.id.surveyList);
        SurveyAdapter adapter = new SurveyAdapter(mSurveyList, questionDataList, SurveyListActivity.this);

        mListView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public class SurveyAdapter extends BaseAdapter {

        List<SurveyResponseData> surveyListData;
        List<QuestionData> questionData;
        private Context mContext;
        private LayoutInflater mInflater;

        public SurveyAdapter(List<SurveyResponseData> surveyListData, List<QuestionData> questionData, Context context) {
            this.surveyListData = surveyListData;
            this.questionData = questionData;
            this.mContext = context;
            mInflater = ((Activity) mContext).getLayoutInflater();
        }

        @Override
        public int getCount() {
            return surveyListData.size();
        }

        @Override
        public Object getItem(int position) {
            return surveyListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.survey_row, null);

                viewHolder = new ViewHolder();

                viewHolder.surveyId = (TextView) convertView.findViewById(R.id.responseID);

                viewHolder.nameQ = (TextView) convertView.findViewById(R.id.nameQ);
                viewHolder.nameAns = (TextView) convertView.findViewById(R.id.nameAns);

                viewHolder.genderQ = (TextView) convertView.findViewById(R.id.genderQ);
                viewHolder.genderAns = (TextView) convertView.findViewById(R.id.genderAns);

                viewHolder.factorsQ = (TextView) convertView.findViewById(R.id.factorsQ);
                viewHolder.factorsAns = (TextView) convertView.findViewById(R.id.factorsAns);

                viewHolder.CountryQ = (TextView) convertView.findViewById(R.id.CountryQ);
                viewHolder.CountryAns = (TextView) convertView.findViewById(R.id.CountryAns);

                viewHolder.sourceQ = (TextView) convertView.findViewById(R.id.sourceQ);
                viewHolder.sourceAns = (TextView) convertView.findViewById(R.id.sourceAns);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }


            viewHolder.surveyId.setText("Survey ID :" + surveyListData.get(position).getSurveyId());
            ;

            viewHolder.nameQ.setText(questionData.get(0).getQuestionText());
            viewHolder.nameAns.setText(surveyListData.get(position).getName());

            viewHolder.genderQ.setText(questionData.get(1).getQuestionText());
            viewHolder.genderAns.setText(surveyListData.get(position).getGender());

            viewHolder.factorsQ.setText(questionData.get(2).getQuestionText());
            viewHolder.factorsAns.setText(surveyListData.get(position).getFeatures());

            viewHolder.CountryQ.setText(questionData.get(3).getQuestionText());
            viewHolder.CountryAns.setText(surveyListData.get(position).getCountry());

            viewHolder.sourceQ.setText(questionData.get(4).getQuestionText());
            viewHolder.sourceAns.setText(surveyListData.get(position).getSource());


            return convertView;
        }
    }


    static class ViewHolder {

        TextView surveyId;

        TextView nameQ;
        TextView nameAns;

        TextView genderQ;
        TextView genderAns;

        TextView factorsQ;
        TextView factorsAns;

        TextView CountryQ;
        TextView CountryAns;

        TextView sourceQ;
        TextView sourceAns;


    }
}
