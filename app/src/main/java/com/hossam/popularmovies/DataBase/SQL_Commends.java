package com.hossam.popularmovies.DataBase;


public class SQL_Commends {


    public final static String CREATE_FAVORITES_MOVIES_TABLE = "CREATE TABLE " + Contract.Favorite_Table.Table_Name + " (" +

            Contract.Favorite_Table.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

            Contract.Favorite_Table.OverviewFilm_col + " TEXT NOT NULL," +

            Contract.Favorite_Table.TitleFilm_col + " TEXT NOT NULL," +

            Contract.Favorite_Table.PosterFilm_col + " TEXT NOT NULL ," +

            Contract.Favorite_Table.ReleaseFilm_col + " TEXT NOT NULL ," +

            Contract.Favorite_Table.PopularityFilm_col + " TEXT NOT NULL ," +

            Contract.Favorite_Table.VoteFilm_col + " TEXT NOT NULL " +
            "); ";

    public final static String Drop_POP_MOVIES_TABLE = "DROP TABLE IF EXISTS " + Contract.Favorite_Table.Table_Name;

    public final static String SELECT_ALL_QUERY = "SELECT  * FROM " + Contract.Favorite_Table.Table_Name;
}
