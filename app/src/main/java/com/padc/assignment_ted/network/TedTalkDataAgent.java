package com.padc.assignment_ted.network;

/**
 * Created by Phyo Thiha on 6/10/18.
 */
public interface TedTalkDataAgent {

    void loadTalkList(int page, String accessToken);

    void loadTalkPlayList(int page, String accessToken);

    void loadTalkPodCasts(int page, String accessToken);
}
