package com.easyandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by MrH on 2017/5/31.
 */

public abstract class BaseFragment extends Fragment{


    protected View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(),null);
        initView();
        bindEvent();
        return view;
    }


    public View findViewById(int id){
        return view.findViewById(id);
    }
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void bindEvent();
}
