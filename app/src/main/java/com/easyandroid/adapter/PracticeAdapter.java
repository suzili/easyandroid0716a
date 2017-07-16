package com.easyandroid.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyandroid.R;
import com.easyandroid.activity.PracticeOnlineChoiceQuestionsActivity;
import com.easyandroid.bean.Practice;

import java.util.List;

/**
 * Created by asus on 2017/7/10.
 */

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> implements View.OnClickListener {
    private List<Practice> mPracticeList;

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), PracticeOnlineChoiceQuestionsActivity.class);
        view.getContext().startActivity(intent);

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mPracticeOnlineItem;
        private TextView mNumber;
        private LinearLayout mPracticeItem;






        public ViewHolder(View view){
            super(view);
            mPracticeOnlineItem = (TextView) view.findViewById(R.id.practice_online_item);
            mNumber = (TextView)view.findViewById(R.id.number);
            mPracticeItem = (LinearLayout) view.findViewById(R.id.practice_item);
        }
    }
    public PracticeAdapter(List<Practice> practices){
        mPracticeList = practices;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.practice_item , parent ,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Practice practice = mPracticeList.get(position);
        holder.mNumber.setText(practice.getNumber());
        holder.mPracticeOnlineItem.setText(practice.getPractice_online_item());
        holder.mPracticeItem.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mPracticeList.size();
    }
}
