package com.easyandroid.fragment.workExchange;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.easyandroid.R;
import com.easyandroid.dto.Comment;
import com.easyandroid.dto.CommentFilter;
import com.easyandroid.dto.ExProjectInfo;
import com.easyandroid.util.RequestUtil;
import com.easyandroid.util.RequestUtil.RequestCallback;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.easyandroid.util.Constant.IP;

public class MoreCommentActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout mLayoutChoseCommentSort;
    private Button mButtonSortByTime;
    private Button mButtonSortByHot;
    private ListView mAllCommentListview;
    private Toolbar all_comment_toolbar;

    private String user;
    private boolean userY_N = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_comment);

        //判断用户是否登陆
        if(null != user){
            if(!"".equals(user)){
                userY_N  =true;
            }
        }
        initView();
        setToolBar();
        setValue();
        bindEvent();
    }
    public void initView(){
        mLayoutChoseCommentSort = (LinearLayout) findViewById(R.id.layout_chose_comment_sort);
        mButtonSortByTime = (Button) findViewById(R.id.button_sort_byTime);
        mButtonSortByHot = (Button) findViewById(R.id.button_sort_byHot);
        mAllCommentListview = (ListView) findViewById(R.id.all_comment_listview);
        all_comment_toolbar = (Toolbar) findViewById(R.id.all_comment_toolbar);
    }
    public void setToolBar(){
        setSupportActionBar(all_comment_toolbar);
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.setTitle("");
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.back);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    public void setValue(){
        String projectInfoString = getIntent().getStringExtra("projectInfo");
        if(null != projectInfoString && !"".equals(projectInfoString)){
            ExProjectInfo projectInfo = new Gson().fromJson(projectInfoString,ExProjectInfo.class);
            //获取12条评论
            CommentFilter commentFilter = new CommentFilter();
            commentFilter.setBelongId(projectInfo.getId());
            commentFilter.setPageCount(12);
            RequestUtil<CommentFilter> commentHttp = new RequestUtil<CommentFilter>(IP+"/exProject/getExProjectComment");
            commentHttp.conn(commentFilter, CommentFilter.class, new RequestCallback() {
                @Override
                public void before() {
                    Toast.makeText(MoreCommentActivity.this, "评论飞快加载中...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void success(String res) {
                    if(null != res && !"".equals(res)){
                        List<Comment> commentList = new ArrayList<Comment>(Arrays.asList(new Gson().fromJson(res,Comment[].class)));
                        if(commentList.size()>0){
                            CommentListAdapter adapter = new CommentListAdapter(MoreCommentActivity.this,R.layout.list_item_comment,commentList);
                            mAllCommentListview.setAdapter(adapter);
                        }else {
                            Toast.makeText(MoreCommentActivity.this, "返回数据为空", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MoreCommentActivity.this, "返回数据为空", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void error(Exception e) {

                }
            });
        }
    }
    public void bindEvent(){
        mButtonSortByTime.setOnClickListener(this);
        mButtonSortByHot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //按照时间排序
            case R.id.button_sort_byTime:
                mButtonSortByTime.setTextColor(0xff3f3f3f);
                mButtonSortByHot.setTextColor(0xff858585);
                mLayoutChoseCommentSort.setBackgroundResource(R.drawable.more_comment_choose_bg1);
                Toast.makeText(this, "按照时间排序", Toast.LENGTH_SHORT).show();
                break;
            //按照热度排序
            case R.id.button_sort_byHot:
                mButtonSortByTime.setTextColor(0xff858585);
                mButtonSortByHot.setTextColor(0xff3f3f3f);
                mLayoutChoseCommentSort.setBackgroundResource(R.drawable.more_comment_choose_bg);
                Toast.makeText(this, "按照热度排序", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
