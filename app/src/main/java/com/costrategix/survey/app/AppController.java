package com.costrategix.survey.app;

import android.app.Application;
import android.provider.Settings;

import com.costrategix.survey.access.AnswerAccess;
import com.costrategix.survey.access.AnswerTypeAccess;
import com.costrategix.survey.access.QuestionAccess;
import com.costrategix.survey.database.DatabaseHelper;
import com.costrategix.survey.database.DatabaseManager;
import com.costrategix.survey.pojo.AnswerData;
import com.costrategix.survey.pojo.AnswerTypeData;
import com.costrategix.survey.pojo.QuestionData;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Application instance class.
 * This will start when the application starts.
 */
public class AppController extends Application {

    private static AppController mInstance;

    /**
     * This is the constructor of AppController
     */
    public AppController() {
        mInstance = this;
    }

    /**
     * this is for returning the appcontroller singleton instance
     *
     * @return instance of the AppController
     */
    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        /* load library for sqlite */
        SQLiteDatabase.loadLibs(getApplicationContext());

       /* initializing database instances */
        DatabaseManager.initializeInstance(new DatabaseHelper(getApplicationContext()));

        createSurveyData();
    }

    private void createSurveyData() {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDataBase(new DatabaseHelper(getApplicationContext()));

        //value to check if value is inserted.
        // id will be primary key
        long id = -1;

        QuestionAccess questionAccess = new QuestionAccess(sqLiteDatabase);
        questionAccess.delete(null, null);
        for (QuestionData data : getQuestionData()) {
            id = questionAccess.insert(data);
        }

        AnswerTypeAccess answerTypeAccess = new AnswerTypeAccess(sqLiteDatabase);
        answerTypeAccess.delete(null, null);
        for (AnswerTypeData data : getAnswerTypeData()) {
            id = answerTypeAccess.insert(data);
        }

        AnswerAccess answerAccess = new AnswerAccess(sqLiteDatabase);
        answerAccess.delete(null, null);
        for (AnswerData data : getAnswerData()) {
            id = answerAccess.insert(data);
        }

        DatabaseManager.getInstance().closeDataBase();
    }

    /**
     * get android device id
     *
     * @return
     */
    public String getAndroidId() {
        return Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }


    private List<QuestionData> getQuestionData() {
        List<QuestionData> list = new ArrayList<>();

        QuestionData data = new QuestionData("What is your name ?", 1);
        list.add(data);
        data = new QuestionData("What is your gender ?", 2);
        list.add(data);
        data = new QuestionData("What factors are important to you when buying a software product ?", 3);
        list.add(data);
        data = new QuestionData("Which country do you live in ?", 4);
        list.add(data);
        data = new QuestionData("How did you hear about us ?", 2);
        list.add(data);

        return list;
    }

    private List<AnswerTypeData> getAnswerTypeData() {
        List<AnswerTypeData> list = new ArrayList<>();

        AnswerTypeData data = new AnswerTypeData(1, "Text");
        list.add(data);
        data = new AnswerTypeData(2, "Radio");
        list.add(data);
        data = new AnswerTypeData(3, "Checkbox");
        list.add(data);
        data = new AnswerTypeData(4, "Dropdown");
        list.add(data);

        return list;
    }

    private List<AnswerData> getAnswerData() {
        List<AnswerData> list = new ArrayList<>();

        //For Question 2
        AnswerData data = new AnswerData(2, 1, "Male");
        list.add(data);
        data = new AnswerData(2, 2, "Female");
        list.add(data);

        //For Question 3
        data = new AnswerData(3, 1, "Price");
        list.add(data);
        data = new AnswerData(3, 2, "Usability");
        list.add(data);
        data = new AnswerData(3, 3, "Features");
        list.add(data);
        data = new AnswerData(3, 4, "Support");
        list.add(data);

        //For Question 4
        data = new AnswerData(4, 1, "Argentina");
        list.add(data);
        data = new AnswerData(4, 2, "Australia");
        list.add(data);
        data = new AnswerData(4, 3, "Bahamas");
        list.add(data);
        data = new AnswerData(4, 4, "Bangladesh");
        list.add(data);
        data = new AnswerData(4, 5, "India");
        list.add(data);


        //For Question 5
        data = new AnswerData(5, 1, "Television Ad");
        list.add(data);
        data = new AnswerData(5, 2, "Newspaper Ad");
        list.add(data);
        data = new AnswerData(5, 3, "Radio Ad");
        list.add(data);
        data = new AnswerData(5, 4, "Other");
        list.add(data);

        return list;
    }
}
