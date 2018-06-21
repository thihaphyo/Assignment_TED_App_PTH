package com.padc.assignment_ted.network;

import com.padc.assignment_ted.events.ApiErrorEvent;
import com.padc.assignment_ted.events.SuccessGetTedTalkEvent;
import com.padc.assignment_ted.network.responses.GetTedTalkResponse;
import com.padc.assignment_ted.utils.TedTalkConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Phyo Thiha on 6/21/18.
 */
public class RetrofitDataAgentImpl implements TedTalkDataAgent {

    private static RetrofitDataAgentImpl objectReference;

    private TedTalksApi mTheApi;

    private RetrofitDataAgentImpl() {

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TedTalkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTheApi = retrofit.create(TedTalksApi.class);

    }

    public static RetrofitDataAgentImpl getObjectReference() {
        if (objectReference == null) {
            objectReference = new RetrofitDataAgentImpl();
        }
        return objectReference;
    }

    @Override
    public void loadTalkList(int page, String accessToken) {

        Call<GetTedTalkResponse> apiCall = mTheApi.loadTalkList(accessToken, page);
        apiCall.enqueue(new Callback<GetTedTalkResponse>() {
            @Override
            public void onResponse(Call<GetTedTalkResponse> call, Response<GetTedTalkResponse> response) {
                GetTedTalkResponse tedTalkResponse = response.body();
                if (tedTalkResponse != null && tedTalkResponse.isResponseOk()) {
                    SuccessGetTedTalkEvent event = new SuccessGetTedTalkEvent(tedTalkResponse.getTedTalks());
                    EventBus.getDefault().post(event);
                } else {
                    if (tedTalkResponse == null) {
                        ApiErrorEvent errorEvent = new ApiErrorEvent("Empty in Response");
                        EventBus.getDefault().post(errorEvent);

                    } else {
                        ApiErrorEvent errorEvent = new ApiErrorEvent(tedTalkResponse.getMessage());
                        EventBus.getDefault().post(errorEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTedTalkResponse> call, Throwable t) {

                ApiErrorEvent errorEvent = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });

    }

    @Override
    public void loadTalkPlayList(int page, String accessToken) {

    }

    @Override
    public void loadTalkPodCasts(int page, String accessToken) {

    }
}
