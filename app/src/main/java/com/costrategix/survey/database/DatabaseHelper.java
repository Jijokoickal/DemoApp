package com.costrategix.survey.database;

import android.content.Context;

import com.costrategix.survey.access.AnswerAccess;
import com.costrategix.survey.access.AnswerTypeAccess;
import com.costrategix.survey.access.QuestionAccess;
import com.costrategix.survey.access.SurveyResponseAccess;
import com.costrategix.survey.constants.Constants;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "Database.db";
    // Database Version
    private static final int DATABASE_VERSION = Constants.DB_VERSION;
    private static final String DROP_TABLE_PRE_FIX = "DROP TABLE IF EXISTS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(AnswerAccess.CREATE_TABLE);
        db.execSQL(AnswerTypeAccess.CREATE_TABLE);
        db.execSQL(QuestionAccess.CREATE_TABLE);
        db.execSQL(SurveyResponseAccess.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // create new tables
        db.execSQL(DROP_TABLE_PRE_FIX + " " + AnswerAccess.TABLE_NAME);
        db.execSQL(DROP_TABLE_PRE_FIX + " " + AnswerTypeAccess.TABLE_NAME);
        db.execSQL(DROP_TABLE_PRE_FIX + " " + QuestionAccess.TABLE_NAME);
        db.execSQL(DROP_TABLE_PRE_FIX + " " + SurveyResponseAccess.TABLE_NAME);
        onCreate(db);
    }
}

