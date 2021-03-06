package com.padc.assignment_ted.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.assignment_ted.R;
import com.padc.assignment_ted.viewholders.SuggestedTalkItemViewHolder;

/**
 * Created by Phyo Thiha on 6/4/18.
 */
public class SugestedTalkListAdapter extends RecyclerView.Adapter {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater
                                        .from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.viewholder_talk_suggestion
                                            ,parent,false);

        return new SuggestedTalkItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
