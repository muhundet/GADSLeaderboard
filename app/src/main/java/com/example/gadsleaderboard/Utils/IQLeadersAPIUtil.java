package com.example.gadsleaderboard.Utils;

import android.net.Uri;
import android.util.Log;

import com.example.gadsleaderboard.models.LearningLeader;
import com.example.gadsleaderboard.models.SkillIQLeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class IQLeadersAPIUtil {

    public static final String BASE_API_URL = "https://gadsapi.herokuapp.com/api/skilliq";

    public static URL buildUrl() {

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .build();
        try {
            url = new URL(uri.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getJson(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            } else {
                return null;
            }
        }
        catch (Exception e){
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }
    }

    public static ArrayList<SkillIQLeader> getIQLeanersFromJson(String json) {
        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY= "country";
        final String BADGE_URL = "badgeUrl";
        final int NUMBER_OF_LEARNERS = 20;

        ArrayList<SkillIQLeader> iqLeaders = new ArrayList<>();
        try {
            JSONArray jsonIq = new JSONArray(json);
            //JSONArray arrayIq = jsonIq.getJSONArray(json);

            for (int i =0; i<NUMBER_OF_LEARNERS;i++){
                JSONObject iqJSONObject = jsonIq.getJSONObject(i);
                SkillIQLeader iqLeader;
                iqLeader = new SkillIQLeader(iqJSONObject.getString(NAME),
                        iqJSONObject.getString(SCORE),
                        iqJSONObject.getString(COUNTRY),
                        iqJSONObject.getString(BADGE_URL));

                iqLeaders.add(iqLeader);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return iqLeaders;
    }

}
