package com.padc.assignment_ted.data.models;

import com.padc.assignment_ted.network.HttpUrlConnectionDataAgentImpl;
import com.padc.assignment_ted.network.TedTalkDataAgent;

/**
 * Created by Phyo Thiha on 6/10/18.
 */
public class TedTalkModel {

    private static TedTalkModel objectReference;

    private TedTalkDataAgent mTedTalkDataAgent;

    private static final String ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private TedTalkModel() {

        mTedTalkDataAgent = HttpUrlConnectionDataAgentImpl.getObjectReference();

    }

    public static TedTalkModel getObjectReference() {

        if (objectReference == null) {
            objectReference = new TedTalkModel();
        }
        return objectReference;
    }

    public void loadTalkList() {

        mTedTalkDataAgent.loadTalkList(1, ACCESS_TOKEN);
    }

    public void loadTalkPlayList() {

        mTedTalkDataAgent.loadTalkPlayList(1, ACCESS_TOKEN);
    }

    public void loadTadPodCasts(){

        mTedTalkDataAgent.loadTalkPodCasts(1,ACCESS_TOKEN);
    }

}
