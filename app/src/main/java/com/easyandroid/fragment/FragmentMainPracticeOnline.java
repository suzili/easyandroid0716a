package com.easyandroid.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.easyandroid.R;
import com.easyandroid.adapter.PracticeAdapter;
import com.easyandroid.bean.Practice;
import com.easyandroid.util.OpenNavListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 三臻 on 2017/7/9.
 */

public class FragmentMainPracticeOnline extends BaseFragment {
    private List<Practice> practiceList = new ArrayList<>();
    private ImageView mImageViewOpenNv;
    private OpenNavListener mOpenNavListener;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_practice_online;
    }

    @Override
    protected void initView() {
        mImageViewOpenNv = (ImageView) findViewById(R.id.imageView_openNv);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.practice_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        PracticeAdapter adapter = new PracticeAdapter(practiceList);
        recyclerView.setAdapter(adapter);
        initpractice();

    }

    private void initpractice() {
        for (int i = 1;i < 12; i++){
            Practice one = new Practice("使用Java语言编写的源程序保存时的文件扩展名是","1.");
            practiceList.add(one);
            Practice two = new Practice("《朝花夕拾》原名《_________》,是鲁迅的回忆性散文集,请简介一下其中的一篇（课内学过的除外）的主要内容 ：___________________________________.","2.");
            practiceList.add(two);
            Practice three = new Practice("说出ArrayList,Vector, LinkedList的存储性能和特性 ","3.");
            practiceList.add(three);
            Practice four = new Practice("final, finally, finalize的区别。","4.");
            practiceList.add(four);
        }
    }

    @Override
    protected void bindEvent() {
        mImageViewOpenNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOpenNavListener.openNav();
            }
        });
    }

    public void SetOpenNavListener(OpenNavListener mOpenNavListener) {
        this.mOpenNavListener = mOpenNavListener;
    }
}
