package com.padc.assignment_ted.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.assignment_ted.data.vos.TedTalksVO;

import java.util.List;

/**
 * Created by Phyo Thiha on 6/17/18.
 */
public class GetTedTalkResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("apiVersion")
    private String apiVersion;
    @SerializedName("page")
    private String page;
    @SerializedName("ted_talks")
    private List<TedTalksVO> tedTalks;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<TedTalksVO> getTedTalks() {
        return tedTalks;
    }

    public boolean isResponseOk() {
        return code == 200 && tedTalks != null;
    }
}
