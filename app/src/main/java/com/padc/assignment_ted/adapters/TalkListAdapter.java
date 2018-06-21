package com.padc.assignment_ted.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.assignment_ted.R;
import com.padc.assignment_ted.data.vos.TedTalksVO;
import com.padc.assignment_ted.delegates.TalksDelegate;
import com.padc.assignment_ted.viewholders.TalkItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyo Thiha on 5/29/18.
 */

public class TalkListAdapter extends RecyclerView.Adapter<TalkItemViewHolder> {

    private TalksDelegate mTalksDelegate;
    private List<TedTalksVO> mTedTalks;

    public TalkListAdapter(TalksDelegate talksDelegate) {
        mTalksDelegate = talksDelegate;
        mTedTalks = new ArrayList<>();
    }

    @Override
    public TalkItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater
                .from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.viewholder_talk, parent, false);
        return new TalkItemViewHolder(view, mTalksDelegate);
    }

    @Override
    public void onBindViewHolder(TalkItemViewHolder holder, int position) {
            holder.setTalksData(mTedTalks.get(position));
    }


    @Override
    public int getItemCount() {
        return mTedTalks.size();
    }

    public void setTalkList(List<TedTalksVO> tedTalksList) {
        mTedTalks = tedTalksList;
        notifyDataSetChanged();
    }
}
