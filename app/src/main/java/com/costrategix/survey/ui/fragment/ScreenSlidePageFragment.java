package com.costrategix.survey.ui.fragment;

/**
 * Created by Admin on 04-12-2015.
 */

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.costrategix.survey.R;
import com.costrategix.survey.access.AnswerAccess;
import com.costrategix.survey.access.QuestionAccess;
import com.costrategix.survey.access.SurveyResponseAccess;
import com.costrategix.survey.database.DatabaseHelper;
import com.costrategix.survey.database.DatabaseManager;
import com.costrategix.survey.log.AppLog;
import com.costrategix.survey.pojo.AnswerData;
import com.costrategix.survey.pojo.QuestionData;
import com.costrategix.survey.pojo.SurveyResponseData;
import com.costrategix.survey.singleton.SurveyResponse;
import com.costrategix.survey.ui.activity.HomeActivity;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    private static List<QuestionData> sQuestionDataList;

    private LinearLayout mLaoyoutAnswerTypeText;
    private LinearLayout mLaoyoutAnswerTypeRadio;
    private LinearLayout mLaoyoutAnswerTypeCheckBox;
    private LinearLayout mLaoyoutAnswerTypeDropdown;
    private LinearLayout mLaoyoutSource;

    private Button mBtnSubmitSurvey;

    private EditText mNameEditText;

    private List<String> mCountryList;


    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);

        SQLiteDatabase database = DatabaseManager.getInstance().openDataBase(new DatabaseHelper(this.getActivity()));
        QuestionAccess questionAccess = new QuestionAccess(database);
        sQuestionDataList = (List<QuestionData>) questionAccess.select(null, null);
        DatabaseManager.getInstance().closeDataBase();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);
        initViews(rootView);

        return rootView;
    }

    private void initViews(ViewGroup rootView) {
        // Set the title view to show the page number.
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(getString(R.string.title_template_step, mPageNumber + 1));
        ((TextView) rootView.findViewById(R.id.question)).setText(sQuestionDataList.get(mPageNumber).getQuestionText());


        mLaoyoutAnswerTypeText = (LinearLayout) rootView.findViewById(R.id.answerTypeText);//1 name
        mLaoyoutAnswerTypeRadio = (LinearLayout) rootView.findViewById(R.id.answerTypeRadio);//2 Gender
        mLaoyoutAnswerTypeCheckBox = (LinearLayout) rootView.findViewById(R.id.answerTypeCheckBox);//3 Features
        mLaoyoutAnswerTypeDropdown = (LinearLayout) rootView.findViewById(R.id.answerTypeDropdown);//4 Country
        mLaoyoutSource = (LinearLayout) rootView.findViewById(R.id.mLaoyoutSource);//5 source


        mBtnSubmitSurvey = (Button) rootView.findViewById(R.id.btnSubmitSurvey);//5


        if (mPageNumber == 0) {
            mLaoyoutAnswerTypeText.setVisibility(View.VISIBLE);
            mLaoyoutAnswerTypeRadio.setVisibility(View.GONE);
            mLaoyoutAnswerTypeCheckBox.setVisibility(View.GONE);
            mLaoyoutAnswerTypeDropdown.setVisibility(View.GONE);
            mLaoyoutSource.setVisibility(View.GONE);
            mBtnSubmitSurvey.setVisibility(View.GONE);

            mNameEditText = (EditText) rootView.findViewById(R.id.userName);
            mNameEditText.addTextChangedListener(nameWatcher);


        } else if (mPageNumber == 1) {
            mLaoyoutAnswerTypeText.setVisibility(View.GONE);
            mLaoyoutAnswerTypeRadio.setVisibility(View.VISIBLE);
            mLaoyoutAnswerTypeCheckBox.setVisibility(View.GONE);
            mLaoyoutAnswerTypeDropdown.setVisibility(View.GONE);
            mLaoyoutSource.setVisibility(View.GONE);
            mBtnSubmitSurvey.setVisibility(View.GONE);

            RadioGroup genderGroup = (RadioGroup) mLaoyoutAnswerTypeRadio.findViewById(R.id.genderGroup);
            genderGroup.setOnCheckedChangeListener(genderChangeListener);

        } else if (mPageNumber == 2) {
            mLaoyoutAnswerTypeText.setVisibility(View.GONE);
            mLaoyoutAnswerTypeRadio.setVisibility(View.GONE);
            mLaoyoutAnswerTypeCheckBox.setVisibility(View.VISIBLE);
            mLaoyoutAnswerTypeDropdown.setVisibility(View.GONE);
            mLaoyoutSource.setVisibility(View.GONE);
            mBtnSubmitSurvey.setVisibility(View.GONE);

            CheckBox checkBoxPrice = ((CheckBox) mLaoyoutAnswerTypeCheckBox.findViewById(R.id.checkBoxPrice));
            checkBoxPrice.setOnCheckedChangeListener(new ChangeListener(checkBoxPrice));

            CheckBox checkBoxUsability = ((CheckBox) mLaoyoutAnswerTypeCheckBox.findViewById(R.id.checkBoxUsability));
            checkBoxUsability.setOnCheckedChangeListener(new ChangeListener(checkBoxUsability));

            CheckBox checkBoxFeatures = ((CheckBox) mLaoyoutAnswerTypeCheckBox.findViewById(R.id.checkBoxFeatures));
            checkBoxFeatures.setOnCheckedChangeListener(new ChangeListener(checkBoxFeatures));

            CheckBox checkBoxSupport = ((CheckBox) mLaoyoutAnswerTypeCheckBox.findViewById(R.id.checkBoxSupport));
            checkBoxSupport.setOnCheckedChangeListener(new ChangeListener(checkBoxSupport));

        } else if (mPageNumber == 3) {
            mLaoyoutAnswerTypeText.setVisibility(View.GONE);
            mLaoyoutAnswerTypeRadio.setVisibility(View.GONE);
            mLaoyoutAnswerTypeCheckBox.setVisibility(View.GONE);
            mLaoyoutAnswerTypeDropdown.setVisibility(View.VISIBLE);
            mLaoyoutSource.setVisibility(View.GONE);
            mBtnSubmitSurvey.setVisibility(View.GONE);

            Spinner countrySpinner = (Spinner) mLaoyoutAnswerTypeDropdown.findViewById(R.id.spinnerCountry);

            mCountryList = getCountryList();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mCountryList);
            countrySpinner.setAdapter(adapter);

            countrySpinner.setOnItemSelectedListener(onItemSelectedListener);

        } else if (mPageNumber == 4) {
            mLaoyoutAnswerTypeText.setVisibility(View.GONE);
            mLaoyoutAnswerTypeRadio.setVisibility(View.GONE);
            mLaoyoutAnswerTypeCheckBox.setVisibility(View.GONE);
            mLaoyoutAnswerTypeDropdown.setVisibility(View.GONE);
            mLaoyoutSource.setVisibility(View.VISIBLE);
            mBtnSubmitSurvey.setVisibility(View.VISIBLE);

            RadioGroup sourceGroup = (RadioGroup) mLaoyoutSource.findViewById(R.id.sourceGroup);
            sourceGroup.setOnCheckedChangeListener(sourceChangeListener);

            mBtnSubmitSurvey.setOnClickListener(submitListener);


        }
    }

    private List<String> getCountryList() {
        List<String> countries = new ArrayList<>();
        countries.clear();

        SQLiteDatabase database = DatabaseManager.getInstance().openDataBase(new DatabaseHelper(this.getActivity()));
        AnswerAccess questionAccess = new AnswerAccess(database);
        List<AnswerData> ans = (List<AnswerData>) questionAccess.select(null, AnswerAccess.COLUMN_SORT_ORDER);
        DatabaseManager.getInstance().closeDataBase();

        for (AnswerData data : ans) {
            if (data.getQuestionId() == 4) {
                countries.add(data.getAnswer());
            }
        }
        return countries;

    }


    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

    private View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteDatabase database = DatabaseManager.getInstance().openDataBase(new DatabaseHelper(ScreenSlidePageFragment.this.getActivity()));
            SurveyResponseAccess access = new SurveyResponseAccess(database);

            int lastSurveyId = access.getCount() + 1;

            String features = android.text.TextUtils.join(", ", SurveyResponse.getInstance().getFeaturesList());

            SurveyResponseData data = new SurveyResponseData(lastSurveyId,
                    SurveyResponse.getInstance().getUserName(),
                    SurveyResponse.getInstance().getGender(),
                    features,
                    SurveyResponse.getInstance().getCountry(),
                    SurveyResponse.getInstance().getSource());

            access.insert(data);

            AppLog.logString("data inserted");
            SurveyResponse.getInstance().clear();

            Intent intent = new Intent(ScreenSlidePageFragment.this.getActivity(), HomeActivity.class);
            startActivity(intent);

            ScreenSlidePageFragment.this.getActivity().finish();
        }
    };

    private final TextWatcher nameWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            SurveyResponse.getInstance().setUserName(mNameEditText.getText().toString());
        }
    };

    private RadioGroup.OnCheckedChangeListener genderChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(id);
            int radioId = group.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) group.getChildAt(radioId);
            String selection = (String) btn.getText();
            SurveyResponse.getInstance().setGender(selection);
        }
    };


    private RadioGroup.OnCheckedChangeListener sourceChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(id);
            int radioId = group.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) group.getChildAt(radioId);
            String selection = (String) btn.getText();
            SurveyResponse.getInstance().setSource(selection);
        }
    };

    private class ChangeListener implements CompoundButton.OnCheckedChangeListener {

        CheckBox checkBox;

        public ChangeListener(CheckBox buttonView) {
            this.checkBox = buttonView;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                SurveyResponse.getInstance().addFeature(checkBox.getText().toString());

            } else {
                SurveyResponse.getInstance().removeFeature(checkBox.getText().toString());
            }

        }
    }

    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            SurveyResponse.getInstance().setCountry(mCountryList.get(position));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
