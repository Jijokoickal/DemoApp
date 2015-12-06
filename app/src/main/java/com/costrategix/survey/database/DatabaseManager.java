package com.costrategix.survey.database;


import com.costrategix.survey.app.AppController;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseManager {

    private AtomicInteger mOpenCounter = new AtomicInteger();
    private static DatabaseManager mDataBaseManagerInstance;
    private static SQLiteOpenHelper mSqLiteOpenHelper;
    private SQLiteDatabase mSqLiteDatabase;


    public static synchronized void initializeInstance(SQLiteOpenHelper sqLiteOpenHelper) {
        mDataBaseManagerInstance = new DatabaseManager();
        mSqLiteOpenHelper = sqLiteOpenHelper;
    }

    public static synchronized DatabaseManager getInstance() {
        if (mDataBaseManagerInstance == null) {
            mDataBaseManagerInstance = new DatabaseManager();
        }
        return mDataBaseManagerInstance;
    }

    public synchronized SQLiteDatabase openDataBase(SQLiteOpenHelper sqLiteOpenHelper) {
        if (null == mSqLiteOpenHelper) {
            mSqLiteOpenHelper = sqLiteOpenHelper;
        }

        if(null==mSqLiteDatabase){

        }

        if (mOpenCounter.incrementAndGet() == 1) {
            mSqLiteDatabase = mSqLiteOpenHelper.getReadableDatabase(AppController.getInstance().getAndroidId());
        }
        return mSqLiteDatabase;
    }

    public synchronized void closeDataBase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            mSqLiteOpenHelper.close();
        }
    }
}
