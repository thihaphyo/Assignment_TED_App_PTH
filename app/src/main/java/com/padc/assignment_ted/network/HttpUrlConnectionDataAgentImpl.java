package com.padc.assignment_ted.network;

import android.os.AsyncTask;
import android.util.Log;

import com.padc.assignment_ted.utils.TedTalkConstants;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyo Thiha on 6/10/18.
 */
public class HttpUrlConnectionDataAgentImpl implements TedTalkDataAgent {

    private static HttpUrlConnectionDataAgentImpl objectReference;

    private HttpUrlConnectionDataAgentImpl() {

    }

    @Override
    public void loadTalkList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {

            URL url;
            BufferedReader bufferedReader;
            StringBuilder stringBuilder;

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    url = new URL(TedTalkConstants.BASE_URL + TedTalkConstants.API_GET_TED_TALKS);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setReadTimeout(15 * 1000);

                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair(TedTalkConstants.PARAM_ACCESS_TOKEN, accessToken));
                    params.add(new BasicNameValuePair(TedTalkConstants.PARAM_PAGE, String.valueOf(page)));

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    httpURLConnection.connect();

                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }

                    String responseString = stringBuilder.toString();
                    return responseString;


                } catch (Exception e) {
                    Log.e("ERR", e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {

                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            Log.e("ERR", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                if (responseString != null) {
                }
            }
        }.execute();
    }

    @Override
    public void loadTalkPlayList(final int page, final String accessToken) {

        new AsyncTask<Void, Void, String>() {

            URL url;
            BufferedReader bufferedReader;
            StringBuilder stringBuilder;

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    url = new URL(TedTalkConstants.BASE_URL + TedTalkConstants.API_GET_TED_PLAYLIST);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setReadTimeout(15 * 1000);

                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair(TedTalkConstants.PARAM_ACCESS_TOKEN, accessToken));
                    params.add(new BasicNameValuePair(TedTalkConstants.PARAM_PAGE, String.valueOf(page)));

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    httpURLConnection.connect();

                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String lines = null;

                    while ((lines = bufferedReader.readLine()) != null) {

                        stringBuilder.append(lines + "\n");
                    }

                    String responseString = stringBuilder.toString();
                    return responseString;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute();
    }

    @Override
    public void loadTalkPodCasts(final int page, final String accessToken) {

        new AsyncTask<Void, Void, String>() {
            URL url;
            BufferedReader reader;
            StringBuilder stringBuilder;
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    url = new URL(TedTalkConstants.BASE_URL+TedTalkConstants.API_GET_TED_PLAYLIST);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(15 * 1000);
                    httpURLConnection.setReadTimeout(15 * 1000);

                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair(TedTalkConstants.PARAM_ACCESS_TOKEN,accessToken));
                    params.add(new BasicNameValuePair(TedTalkConstants.PARAM_PAGE,String.valueOf(page)));

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    httpURLConnection.connect();

                    reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String lines = "";

                    while((lines = reader.readLine())!=null){
                        stringBuilder.append(lines+"\n");
                    }

                    String responseString = stringBuilder.toString();
                    return responseString;
                } catch (Exception e) {
                    Log.e("ERR_"+TedTalkConstants.API_GET_TED_PODCASTS,e.getMessage());
                    e.printStackTrace();
                }finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            Log.e("ERR_"+TedTalkConstants.API_GET_TED_PODCASTS,e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute();

    }


    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {

        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;


        for (NameValuePair pair : params) {

            if (isFirst) {

                isFirst = false;

            } else {

                stringBuilder.append("&");
            }

            stringBuilder.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(pair.getValue(), "UTF-8"));

        }

        return stringBuilder.toString();
    }

    public static HttpUrlConnectionDataAgentImpl getObjectReference() {

        if (objectReference == null) {
            objectReference = new HttpUrlConnectionDataAgentImpl();
        }
        return objectReference;
    }
}
