package com.padc.assignment_ted.network;


import com.padc.assignment_ted.network.responses.GetTedTalkResponse;
import com.padc.assignment_ted.utils.TedTalkConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Phyo Thiha on 6/21/18.
 */
public interface TedTalksApi {

    @FormUrlEncoded
    @POST(TedTalkConstants.API_GET_TED_TALKS)
    Call<GetTedTalkResponse> loadTalkList(
            @Field(TedTalkConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TedTalkConstants.PARAM_PAGE) int page
    );

    @FormUrlEncoded
    @POST(TedTalkConstants.API_GET_TED_PLAYLIST)
    Call<GetTedTalkResponse> loadTalkPlayList(
            @Field(TedTalkConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TedTalkConstants.PARAM_PAGE) int page
    );

    @FormUrlEncoded
    @POST(TedTalkConstants.API_GET_TED_PODCASTS)
    Call<GetTedTalkResponse> loadTalkPodCasts(
            @Field(TedTalkConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TedTalkConstants.PARAM_PAGE) int page
    );

}
