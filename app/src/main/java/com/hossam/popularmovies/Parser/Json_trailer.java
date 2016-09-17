package com.hossam.popularmovies.Parser;


import com.hossam.popularmovies.DataProcess.Encap;
import com.hossam.popularmovies.DataProcess.KeyTags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Json_trailer {
    List<Encap> data2;

    public List<Encap> JsonProcess_trailer(String api) {

        data2 = new ArrayList<>();

        try {

            JSONObject mainObject = new JSONObject(api);

            JSONArray jsonArray = mainObject.getJSONArray(KeyTags.main_array);

            for (int i = 0; i < jsonArray.length(); i++) {

                Encap encap = new Encap();

                JSONObject main_Object = jsonArray.getJSONObject(i);
                encap.setKey(main_Object.getString(KeyTags.key_trailer));

                System.out.println(encap.getKey());

                data2.add(encap);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data2;
    }

    public List<Encap> getlist2() {
        return data2;
    }
}
