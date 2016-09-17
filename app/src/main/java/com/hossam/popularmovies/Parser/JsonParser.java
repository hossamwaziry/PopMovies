package com.hossam.popularmovies.Parser;

import com.hossam.popularmovies.DataProcess.Encap;
import com.hossam.popularmovies.DataProcess.KeyTags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    List<Encap> data;

    public List<Encap> JsonProcess(String api) {


        data = new ArrayList<>();

        try {

            JSONObject mainObject = new JSONObject(api);

            JSONArray jsonArray = mainObject.getJSONArray(KeyTags.main_array);

            for (int i = 0; i < jsonArray.length(); i++) {

                Encap encap = new Encap();

                JSONObject main_Object = jsonArray.getJSONObject(i);
                encap.setPosterFilm(main_Object.getString(KeyTags.poster_film));
                encap.setTitleFilm(main_Object.getString(KeyTags.title_film));
                encap.setReleaseFilm(main_Object.getString(KeyTags.release_date_film));
                encap.setOverviewFilm(main_Object.getString(KeyTags.overview_film));
                encap.setVoteFilm(main_Object.getString(KeyTags.vote_film));
                encap.setPopularityFilm(main_Object.getString(KeyTags.popularity_film));
                encap.setID(main_Object.getString(KeyTags.id_film));
                data.add(encap);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<Encap> getlist() {
        return data;
    }

}
