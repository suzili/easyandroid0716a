package com.easyandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.easyandroid.R;

public class PracticeOnlineFillBlanksActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mFillblanksanswer;
    private Button mFillblanksbt;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_online_fill_blanks);
        mFillblanksanswer = (LinearLayout) findViewById(R.id.fillblanksanswer);
        mFillblanksbt = (Button) findViewById(R.id.fill_blanks_bt);
        mFillblanksbt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        mFillblanksanswer.setVisibility(View.VISIBLE);
    }
}
