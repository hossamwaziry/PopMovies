package com.hossam.popularmovies.Parser;

import com.hossam.popularmovies.DataProcess.Encap;
import com.hossam.popularmovies.DataProcess.KeyTags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Json_reviews {

    List<Encap> data;

    public List<Encap> JsonProcess(String api) {

        data = new ArrayList<>();

        try {

            JSONObject mainObject = new JSONObject(api);

            JSONArray jsonArray = mainObject.getJSONArray(KeyTags.main_array);

            for (int i = 0; i < jsonArray.length(); i++) {

                Encap encap = new Encap();

                JSONObject main_Object = jsonArray.getJSONObject(i);
                encap.setReviews(main_Object.getString(KeyTags.reviews_film));
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
