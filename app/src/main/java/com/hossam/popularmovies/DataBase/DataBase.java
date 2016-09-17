package com.hossam.popularmovies.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBase extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "pop_movie_db";
    private static final int DATABASE_VERSION = 2;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_Commends.CREATE_FAVORITES_MOVIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_Commends.Drop_POP_MOVIES_TABLE);

        db.execSQL(SQL_Commends.Drop_POP_MOVIES_TABLE);

        onCreate(db);

    }

    public boolean CheckOpject(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + Contract.Favorite_Table.Table_Name + " WHERE " + Contract.Favorite_Table.TitleFilm_col + " =?";

        Cursor cursor = db.rawQuery(selectString, new String[]{id});

        boolean checkOpject = false;
        if (cursor.moveToFirst()) {
            checkOpject = true;

        }
        cursor.close();
        db.close();
        return checkOpject;
    }


    public void insertData(SQLiteDatabase sql, String overviewFilm, String titleFilm, String posterFilm, String releaseFilm, String popularityFilm, String voteFilm) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Favorite_Table.OverviewFilm_col, overviewFilm);
        contentValues.put(Contract.Favorite_Table.TitleFilm_col, titleFilm);
        contentValues.put(Contract.Favorite_Table.PosterFilm_col, posterFilm);
        contentValues.put(Contract.Favorite_Table.ReleaseFilm_col, releaseFilm);
        contentValues.put(Contract.Favorite_Table.PopularityFilm_col, popularityFilm);
        contentValues.put(Contract.Favorite_Table.VoteFilm_col, voteFilm);
        sql.insert(Contract.Favorite_Table.Table_Name, null, contentValues);

    }

}


