package com.costrategix.survey.access;

import android.content.ContentValues;
import android.database.Cursor;

import com.costrategix.survey.database.AbstractDb;
import com.costrategix.survey.pojo.QuestionData;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

public class QuestionAccess extends AbstractDb {

    public static final String TABLE_NAME = "Question";

    //Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ANSWER_TYPE_ID = "answer_type_id";
    private static final String COLUMN_QUESTION = "question";

    public static final String CREATE_TABLE = "  CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ANSWER_TYPE_ID + " NUMERIC, " +
            COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_QUESTION + " TEXT)";

    private QuestionData questionData = null;

    public QuestionAccess(SQLiteDatabase database) {
        super(database);
    }

    @Override
    protected String getPrimaryKey() {
        return COLUMN_ID;
    }

    @Override
    protected String[] getColumns() {
        return new String[]{COLUMN_ID, COLUMN_QUESTION, COLUMN_ANSWER_TYPE_ID};
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Object loadColumns(Cursor c) {

        ArrayList<QuestionData> codeInfoDataList = new ArrayList<>();
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                questionData = new QuestionData();

                questionData.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
                questionData.setAnswerTypeId(c.getInt(c.getColumnIndex(COLUMN_ANSWER_TYPE_ID)));
                questionData.setQuestionText(c.getString(c.getColumnIndex(COLUMN_QUESTION)));

                codeInfoDataList.add(questionData);
            } while (c.moveToNext());
            c.close();
        }
        return codeInfoDataList;
    }

    @Override
    protected ContentValues buildColumns(Object obj) {
        questionData = (QuestionData) obj;

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ANSWER_TYPE_ID, questionData.getAnswerTypeId());
        contentValues.put(COLUMN_QUESTION, questionData.getQuestionText());

        return contentValues;
    }
}
