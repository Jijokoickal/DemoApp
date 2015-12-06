package com.costrategix.survey.access;

import android.content.ContentValues;
import android.database.Cursor;

import com.costrategix.survey.database.AbstractDb;
import com.costrategix.survey.pojo.AnswerData;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

public class AnswerAccess extends AbstractDb {

    public static final String TABLE_NAME = "Answer";

    //Column names
    private static final String COLUMN_ID = "id";
    public static final String COLUMN_SORT_ORDER = "sort_order";
    private static final String COLUMN_QUESTION_ID = "question_id";
    private static final String COLUMN_ANSWER = "answer";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_SORT_ORDER + " INTEGER, " +
            COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_QUESTION_ID + " NUMERIC, " +
            COLUMN_ANSWER + " TEXT)";

    private AnswerData answerData = null;

    public AnswerAccess(SQLiteDatabase database) {
        super(database);
    }

    @Override
    protected String getPrimaryKey() {
        return COLUMN_ID;
    }

    @Override
    protected String[] getColumns() {
        return new String[]{COLUMN_ID, COLUMN_SORT_ORDER, COLUMN_QUESTION_ID, COLUMN_ANSWER};
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Object loadColumns(Cursor c) {

        ArrayList<AnswerData> codeInfoDataList = new ArrayList<>();
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                answerData = new AnswerData();

                answerData.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
                answerData.setSortOder(c.getInt(c.getColumnIndex(COLUMN_SORT_ORDER)));
                answerData.setQuestionId(c.getInt(c.getColumnIndex(COLUMN_QUESTION_ID)));
                answerData.setAnswer(c.getString(c.getColumnIndex(COLUMN_ANSWER)));

                codeInfoDataList.add(answerData);
            } while (c.moveToNext());
            c.close();
        }
        return codeInfoDataList;
    }

    @Override
    protected ContentValues buildColumns(Object obj) {
        answerData = (AnswerData) obj;

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_SORT_ORDER, answerData.getSortOder());
        contentValues.put(COLUMN_QUESTION_ID, answerData.getQuestionId());
        contentValues.put(COLUMN_ANSWER, answerData.getAnswer());
        return contentValues;
    }
}
