package com.costrategix.survey.access;

import android.content.ContentValues;
import android.database.Cursor;

import com.costrategix.survey.database.AbstractDb;
import com.costrategix.survey.pojo.SurveyResponseData;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Admin on 05-12-2015.
 */
public class SurveyResponseAccess extends AbstractDb {

    public static final String TABLE_NAME = "User_Response_table";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SURVEY_ID = "_survey_id";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_GENDER = "_gender";
    private static final String COLUMN_FEATURES = "_features";
    private static final String COLUMN_COUNTRY = "_country";
    private static final String COLUMN_SOURCE = "_source";


    public static final String CREATE_TABLE = "CREATE  TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , " +
            COLUMN_SURVEY_ID + " INTEGER, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_GENDER + " TEXT, " +
            COLUMN_FEATURES + " TEXT, " +
            COLUMN_COUNTRY + " TEXT, " +
            COLUMN_SOURCE + " TEXT)";


    private SurveyResponseData surveyResponseData = null;

    public SurveyResponseAccess(SQLiteDatabase database) {
        super(database);
    }

    @Override
    protected String getPrimaryKey() {
        return COLUMN_ID;
    }

    @Override
    protected String[] getColumns() {
        return new String[]{COLUMN_ID, COLUMN_SURVEY_ID, COLUMN_NAME, COLUMN_GENDER, COLUMN_FEATURES, COLUMN_COUNTRY, COLUMN_SOURCE};
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Object loadColumns(Cursor c) {
        ArrayList<SurveyResponseData> codeInfoDataList = new ArrayList<>();
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                surveyResponseData = new SurveyResponseData();

                surveyResponseData.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
                surveyResponseData.setSurveyId(c.getInt(c.getColumnIndex(COLUMN_SURVEY_ID)));
                surveyResponseData.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                surveyResponseData.setGender(c.getString(c.getColumnIndex(COLUMN_GENDER)));
                surveyResponseData.setFeatures(c.getString(c.getColumnIndex(COLUMN_FEATURES)));
                surveyResponseData.setCountry(c.getString(c.getColumnIndex(COLUMN_COUNTRY)));
                surveyResponseData.setSource(c.getString(c.getColumnIndex(COLUMN_SOURCE)));


                codeInfoDataList.add(surveyResponseData);
            } while (c.moveToNext());
            c.close();
        }
        return codeInfoDataList;
    }

    @Override
    protected ContentValues buildColumns(Object obj) {
        surveyResponseData = (SurveyResponseData) obj;

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_SURVEY_ID, surveyResponseData.getSurveyId());
        contentValues.put(COLUMN_NAME, surveyResponseData.getName());

        contentValues.put(COLUMN_GENDER, surveyResponseData.getGender());
        contentValues.put(COLUMN_FEATURES, surveyResponseData.getFeatures());
        contentValues.put(COLUMN_COUNTRY, surveyResponseData.getCountry());
        contentValues.put(COLUMN_SOURCE, surveyResponseData.getSource());


        return contentValues;
    }


    public int getLastSurveyId() {
        int id = 0;

        Cursor cursor = appDatabase.rawQuery("SELECT MAX(" + SurveyResponseAccess.COLUMN_SURVEY_ID + ") FROM " + SurveyResponseAccess.TABLE_NAME + "", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            id = cursor.getInt(0);
            cursor.close();
        }
        return id;
    }
}
