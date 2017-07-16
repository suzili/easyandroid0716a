package com.easyandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.easyandroid.R;

public class PracticeOnlineShortAnswerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mShortanswerbt;
    private LinearLayout mShortansweran;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_online_short_answer);
        mShortanswerbt = (Button) findViewById(R.id.short_answer_bt);
        mShortansweran = (LinearLayout) findViewById(R.id.shortansweran);
        mShortanswerbt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.short_answer_bt:
                mShortansweran.setVisibility(View.VISIBLE);
        }
    }
}
