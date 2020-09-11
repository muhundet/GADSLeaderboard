package com.example.gadsleaderboard.Utils;

import android.net.Uri;
import android.util.Log;

import com.example.gadsleaderboard.models.LearningLeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class LeadingLearnersAPIUtil {
    private LeadingLearnersAPIUtil(){}

    public static final String BASE_API_URL = "https://gadsapi.herokuapp.com/api/hours";


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

    public static ArrayList<LearningLeader> getLeanersFromJson(String json) {
        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY= "country";
        final String BADGE_URL = "badgeUrl";
        final int NUMBER_OF_LEARNERS = 20;

        ArrayList<LearningLeader> learningLeaders = new ArrayList<LearningLeader>();
        try {
            JSONArray jsonLearners = new JSONArray(json);

            for (int i =0; i<NUMBER_OF_LEARNERS;i++){
                JSONObject learnersJSONObject = jsonLearners.getJSONObject(i);
                LearningLeader learningLeader;
                learningLeader = new LearningLeader(learnersJSONObject.getString(NAME),
                        learnersJSONObject.getString(HOURS),
                        learnersJSONObject.getString(COUNTRY),
                        learnersJSONObject.getString(BADGE_URL));

                learningLeaders.add(learningLeader);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return learningLeaders;
    }
}
