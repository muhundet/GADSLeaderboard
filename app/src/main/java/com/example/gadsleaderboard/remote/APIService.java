package com.example.gadsleaderboard.remote;

import com.example.gadsleaderboard.models.SubmitPost;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<SubmitPost> savePost(@Field("entry.1824927963") String emailAddress,
                              @Field("entry.1877115667") String firstName,
                              @Field("entry.2006916086") String lastName,
                              @Field("entry.2006916086") String linkToProject);
}
