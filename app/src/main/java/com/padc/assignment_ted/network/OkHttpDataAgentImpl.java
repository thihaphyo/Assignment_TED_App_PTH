package com.padc.assignment_ted.network;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.padc.assignment_ted.events.ApiErrorEvent;
import com.padc.assignment_ted.events.SuccessGetTedTalkEvent;
import com.padc.assignment_ted.network.responses.GetTedTalkResponse;
import com.padc.assignment_ted.utils.TedTalkConstants;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Phyo Thiha on 6/21/18.
 */
public class OkHttpDataAgentImpl implements TedTalkDataAgent {

    private static OkHttpDataAgentImpl objectReference;

    private OkHttpClient mOkHttpClient;

    private OkHttpDataAgentImpl() {

        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

    }

    public static OkHttpDataAgentImpl getObjectReference() {
        if (objectReference == null) {
            objectReference = new OkHttpDataAgentImpl();
        }
        return objectReference;
    }

    @Override
    public void loadTalkList(final int page, final String accessToken) {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                RequestBody requestBody = new FormBody.Builder()
                        .add(TedTalkConstants.PARAM_PAGE, String.valueOf(page))
                        .add(TedTalkConstants.PARAM_ACCESS_TOKEN, accessToken)
                        .build();

                Request request = new Request.Builder()
                        .url(TedTalkConstants.BASE_URL + TedTalkConstants.API_GET_TED_TALKS)
                        .post(requestBody)
                        .build();

                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        return responseString;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetTedTalkResponse getTedTalkResponse = gson.fromJson(responseString, GetTedTalkResponse.class);
                if (getTedTalkResponse.isResponseOk()) {
                    SuccessGetTedTalkEvent event = new SuccessGetTedTalkEvent(getTedTalkResponse.getTedTalks());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent errorEvent = new ApiErrorEvent(getTedTalkResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }

            }
        }.execute();

    }

    @Override
    public void loadTalkPlayList(final int page, final String accessToken) {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                RequestBody body = new FormBody.Builder()
                        .add(TedTalkConstants.PARAM_ACCESS_TOKEN, accessToken)
                        .add(TedTalkConstants.PARAM_PAGE, String.valueOf(page))
                        .build();
                Request request = new Request.Builder()
                        .url(TedTalkConstants.BASE_URL + TedTalkConstants.API_GET_TED_PLAYLIST)
                        .post(body)
                        .build();

                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    String responseString = response.body().string();
                    return responseString;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetTedTalkResponse tedTalkResponse = gson.fromJson(responseString, GetTedTalkResponse.class);
                if (tedTalkResponse.isResponseOk()) {
                    SuccessGetTedTalkEvent successGetTedTalkEvent = new SuccessGetTedTalkEvent(tedTalkResponse.getTedTalks());
                    EventBus.getDefault().post(successGetTedTalkEvent);
                } else {
                    ApiErrorEvent errorEvent = new ApiErrorEvent(tedTalkResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }
            }
        }.execute();
    }

    @Override
    public void loadTalkPodCasts(final int page, final String accessToken) {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                RequestBody body = new FormBody.Builder()
                        .add(TedTalkConstants.PARAM_ACCESS_TOKEN, accessToken)
                        .add(TedTalkConstants.PARAM_PAGE, String.valueOf(page))
                        .build();
                Request request = new Request.Builder()
                        .url(TedTalkConstants.BASE_URL + TedTalkConstants.API_GET_TED_PODCASTS)
                        .post(body)
                        .build();
                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    String responseString = response.body().string();
                    return responseString;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetTedTalkResponse tedTalkResponse = gson.fromJson(responseString, GetTedTalkResponse.class);
                if (tedTalkResponse.isResponseOk()) {
                    SuccessGetTedTalkEvent event = new SuccessGetTedTalkEvent(tedTalkResponse.getTedTalks());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent errorEvent = new ApiErrorEvent(tedTalkResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }
            }
        }.execute();

    }
}
