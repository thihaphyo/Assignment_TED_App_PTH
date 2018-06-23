package com.padc.assignment_ted.data.models;

import com.padc.assignment_ted.data.vos.TedTalksVO;
import com.padc.assignment_ted.events.SuccessGetTedTalkEvent;
import com.padc.assignment_ted.network.OkHttpDataAgentImpl;
import com.padc.assignment_ted.network.RetrofitDataAgentImpl;
import com.padc.assignment_ted.network.TedTalkDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Phyo Thiha on 6/10/18.
 */
public class TedTalkModel {

    private static TedTalkModel objectReference;

    private TedTalkDataAgent mTedTalkDataAgent;

    private static final String ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private Map<Integer, TedTalksVO> mTedTalks;

    private TedTalkModel() {

        mTedTalks = new HashMap<>();
        // mTedTalkDataAgent = HttpUrlConnectionDataAgentImpl.getObjectReference();
        //mTedTalkDataAgent = OkHttpDataAgentImpl.getObjectReference();
        mTedTalkDataAgent = RetrofitDataAgentImpl.getObjectReference();
        EventBus.getDefault().register(this);

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

    public void loadTadPodCasts() {

        mTedTalkDataAgent.loadTalkPodCasts(1, ACCESS_TOKEN);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetTedTalks(SuccessGetTedTalkEvent successGetTedTalkEvent) {

        for (TedTalksVO tedTalk : successGetTedTalkEvent.getmTedTalkList()) {

            mTedTalks.put(tedTalk.getTalkId(), tedTalk);
        }
    }

    public TedTalksVO getTedTalkById(int talkId) {

        return mTedTalks.get(talkId);

    }

}
