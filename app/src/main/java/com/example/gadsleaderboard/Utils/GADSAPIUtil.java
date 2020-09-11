package com.example.gadsleaderboard.Utils;

import com.example.gadsleaderboard.remote.APIService;
import com.example.gadsleaderboard.remote.RetrofitClient;

public class GADSAPIUtil {
    private GADSAPIUtil() {}

    public static final String BASE_URL = "https://docs.google.com/forms/d/e/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
