package com.easyandroid.fragment;

import android.widget.TextView;

import com.easyandroid.R;

/**
 * Created by 三臻 on 2017/7/9.
 */

public class FragmentMainPracticeOnline extends BaseFragment {
    private TextView mTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_practice_online;
    }

    @Override
    protected void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void bindEvent() {
        mTextView.setText("在线练习");
    }
}
