package com.easyandroid.fragment;

import android.widget.TextView;

import com.easyandroid.R;

/**
 * Created by 三臻 on 2017/7/9.
 */

public class FragmentMainOnlineClass extends BaseFragment {
    private TextView mTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_online_class;
    }

    @Override
    protected void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void bindEvent() {
        mTextView.setText("在线课堂");
    }
}