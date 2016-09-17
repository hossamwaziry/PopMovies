package com.hossam.popularmovies.DataBase;

import android.provider.BaseColumns;


public class Contract {


    public static final class Favorite_Table implements BaseColumns {

        public static final String Table_Name = "favorite_movie_table";

        public static final String PosterFilm_col = "poster_film";

        public static final String TitleFilm_col = "title_film";

        public static final String OverviewFilm_col = "overview_film";

        public static final String VoteFilm_col = "vote_film";

        public static final String PopularityFilm_col = "popularity_film";

        public static final String ReleaseFilm_col = "release_film";

        public static final String ID = "fav_id";

    }

}
