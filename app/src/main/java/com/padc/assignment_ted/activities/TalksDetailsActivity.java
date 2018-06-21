package com.padc.assignment_ted.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.assignment_ted.R;
import com.padc.assignment_ted.adapters.SugestedTalkListAdapter;
import com.padc.assignment_ted.data.models.TedTalkModel;
import com.padc.assignment_ted.data.vos.TedTalksVO;
import com.padc.assignment_ted.utils.GlideApp;
import com.padc.assignment_ted.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyo Thiha on 6/3/18.
 */
public class TalksDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_talk_back_drop)
    ImageView ivTalkBackDrop;
    @BindView(R.id.tv_talker_name)
    TextView tvTalkerName;
    @BindView(R.id.tv_talk_content)
    TextView tvTalkContent;
    @BindView(R.id.tv_time_secs)
    TextView tvTime;
    @BindView(R.id.tv_talk_content_full)
    TextView tvTalkFullContent;
    @BindView(R.id.tv_poster)
    TextView tvPoster;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.tv_talker_profile_name)
    TextView tvTalkerProfileName;
    @BindView(R.id.tv_talker_occupation)
    TextView tvTalkerOccupation;
    @BindView(R.id.tv_talker_bio)
    TextView tvTalkerBio;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String talkId = getIntent().getStringExtra("talkId");
        TedTalksVO tedTalks = TedTalkModel.getObjectReference().getTedTalkById(talkId);

        setContentView(R.layout.activity_talks_detail);
        ButterKnife.bind(this,this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView rvWatchNext =  findViewById(R.id.rv_watch_next);
        rvWatchNext.setAdapter(new SugestedTalkListAdapter());
        rvWatchNext.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                ,LinearLayoutManager.VERTICAL,false));

        bindData(tedTalks);

    }

    private void bindData(TedTalksVO tedTalks) {

        //text Views
        tvTalkerName.setText(tedTalks.getSpeaker().getSpeakerName());
        tvTalkContent.setText(tedTalks.getTitle());
        String time = Util.getHourMinuteSecond(Double.parseDouble(String.valueOf(tedTalks.getTalkDurationSecs())));
        tvTime.setText(time);
        tvTalkFullContent.setText(tedTalks.getDescription());
        tvPoster.setVisibility(View.GONE);
        tvTalkerProfileName.setText(tedTalks.getSpeaker().getSpeakerName());
        tvTalkerOccupation.setVisibility(View.INVISIBLE);
        tvTalkerBio.setVisibility(View.GONE);

        //ImageViews

        GlideApp.with(ivTalkBackDrop.getContext())
                .load(tedTalks.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(ivTalkBackDrop);

        ivProfile.setImageResource(R.drawable.placeholder);
    }

}
