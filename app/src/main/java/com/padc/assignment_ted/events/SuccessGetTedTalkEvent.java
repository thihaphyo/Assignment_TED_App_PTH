package com.padc.assignment_ted.events;

import com.padc.assignment_ted.data.vos.TedTalksVO;

import java.util.List;

/**
 * Created by Phyo Thiha on 6/17/18.
 */
public class SuccessGetTedTalkEvent {

    private List<TedTalksVO> mTedTalkList;

    public SuccessGetTedTalkEvent(List<TedTalksVO> mTedTalkList) {
        this.mTedTalkList = mTedTalkList;
    }

    public List<TedTalksVO> getmTedTalkList() {
        return mTedTalkList;
    }
}
