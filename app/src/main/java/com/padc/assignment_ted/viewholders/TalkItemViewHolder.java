package com.padc.assignment_ted.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.assignment_ted.R;
import com.padc.assignment_ted.data.vos.TedTalksVO;
import com.padc.assignment_ted.delegates.TalksDelegate;
import com.padc.assignment_ted.utils.GlideApp;
import com.padc.assignment_ted.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyo Thiha on 5/29/18.
 */

public class TalkItemViewHolder extends RecyclerView.ViewHolder {

    private TalksDelegate mTalkDelegate;

    private TedTalksVO mTedTalk;

    @BindView(R.id.tv_talker)
    TextView tvTalker;
    @BindView(R.id.tv_talk_content)
    TextView tvTalkContent;
    @BindView(R.id.tv_time)
    TextView tvTalkDuration;
    @BindView(R.id.iv_talk_hero)
    ImageView ivTalkHero;

    public TalkItemViewHolder(View itemView, TalksDelegate talksDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mTalkDelegate = talksDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTalkDelegate.onTapTalk(mTedTalk);
            }
        });
    }

    public void setTalksData(TedTalksVO tedTalks) {
        mTedTalk = tedTalks;
        tvTalker.setText(mTedTalk.getSpeaker().getSpeakerName());
        tvTalkContent.setText(mTedTalk.getTitle());
        String time = Util.getHourMinuteSecond(Double.parseDouble(String.valueOf(mTedTalk.getTalkDurationSecs())));
        tvTalkDuration.setText(time);

        GlideApp.with(ivTalkHero.getContext())
                .load(mTedTalk.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(ivTalkHero);


    }
}
