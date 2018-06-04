package com.padc.assignment_ted.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.padc.assignment_ted.R;

/**
 * Created by Phyo Thiha on 6/3/18.
 */
public class TalksDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        setContentView(R.layout.activity_talks_detail);
    }
}
