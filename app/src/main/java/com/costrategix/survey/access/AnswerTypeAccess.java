package com.costrategix.survey.access;

import android.content.ContentValues;
import android.database.Cursor;

import com.costrategix.survey.database.AbstractDb;
import com.costrategix.survey.pojo.AnswerTypeData;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

public class AnswerTypeAccess extends AbstractDb {

    public static final String TABLE_NAME = "AnswerType";

    //Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ANSWER_TYPE = "answer_type";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_ANSWER_TYPE + " INTEGER)";


    private AnswerTypeData answerTypeData = null;

    public AnswerTypeAccess(SQLiteDatabase database) {
        super(database);
    }

    @Override
    protected String getPrimaryKey() {
        return COLUMN_ID;
    }

    @Override
    protected String[] getColumns() {
        return new String[]{COLUMN_ID, COLUMN_ANSWER_TYPE};
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Object loadColumns(Cursor c) {

        ArrayList<AnswerTypeData> codeInfoDataList = new ArrayList<>();
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                answerTypeData = new AnswerTypeData();

                answerTypeData.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
                answerTypeData.setAnswerType(c.getString(c.getColumnIndex(COLUMN_ANSWER_TYPE)));

                codeInfoDataList.add(answerTypeData);
            } while (c.moveToNext());
            c.close();
        }
        return codeInfoDataList;
    }

    @Override
    protected ContentValues buildColumns(Object obj) {

        answerTypeData = (AnswerTypeData) obj;

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ANSWER_TYPE, answerTypeData.getAnswerType());

        return contentValues;
    }
}
