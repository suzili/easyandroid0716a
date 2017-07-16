package com.easyandroid.fragment.workExchange;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easyandroid.R;
import com.easyandroid.dto.Comment;
import com.easyandroid.dto.CommentFilter;
import com.easyandroid.dto.ExProjectInfo;
import com.easyandroid.dto.PerUserDto;
import com.easyandroid.dto.ResBoolean;
import com.easyandroid.util.RequestUtil;
import com.easyandroid.util.TimeFormatUtil;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.easyandroid.util.Constant.IP;
import static com.easyandroid.util.Constant.user;

/**
 * 项目交流  项目详情
 */
public class ProjectDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar_profile;
    private Gallery project_details_gallery;
    private Button upimage_button,downimage_button;
    private LinearLayout layout_commentlayout;
    private LinearLayout layout_more_comment;

    private TextView textView_project_name;
    private TextView textView_projectType;
    private TextView textView_like_num;
    private TextView textView_browse_num;
    private TextView textView_project_brief;
    private TextView textView_projectBy;
    private TextView textView_project_uploadTime;

    private TextView textView_nocomment;
    private Button button_release_comment;
    private EditText editText_comment_input;

    private ExProjectInfo projectInfo;
    private boolean userY_N = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        //判断是否有 登陆者
        userY_N = true;
        if (user != null){
            if(!"".equals(user)){
                userY_N = true;
            }
        }
        initView();
        setToolBar();
        setValue();
        bindEvent();
    }
    public void initView(){
        toolbar_profile = (Toolbar) findViewById(R.id.toolbar_project_details);
        project_details_gallery = (Gallery) findViewById(R.id.project_details_gallery);
        upimage_button = (Button) findViewById(R.id.button_upimage);
        downimage_button = (Button) findViewById(R.id.button_downimage);
        layout_commentlayout = (LinearLayout) findViewById(R.id.layout_commentlayout);
        layout_more_comment = (LinearLayout) findViewById(R.id.layout_more_comment);

        textView_project_name = (TextView) findViewById(R.id.textView_project_name);
        textView_projectType = (TextView) findViewById(R.id.textView_projectType);
        textView_like_num = (TextView) findViewById(R.id.textView_like_num);
        textView_browse_num = (TextView) findViewById(R.id.textView_browse_num);
        textView_project_brief = (TextView) findViewById(R.id.textView_project_brief);
        textView_projectBy = (TextView) findViewById(R.id.textView_projectBy);
        textView_project_uploadTime = (TextView) findViewById(R.id.textView_project_uploadTime);

        textView_nocomment = (TextView) findViewById(R.id.textView_nocomment);
        button_release_comment = (Button) findViewById(R.id.button_release_comment);
        editText_comment_input = (EditText) findViewById(R.id.editText_comment_input);
    }

    public void setToolBar(){
        setSupportActionBar(toolbar_profile);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
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
        if(projectInfo == null){
            String projectInfoString = getIntent().getStringExtra("project").toString();
            projectInfo = new Gson().fromJson(projectInfoString,ExProjectInfo.class);
        }
        //开始对界面进行设置操作
        if(projectInfo != null){
            //设置图片轮播
            String[] images = projectInfo.getImages().split(",");
            setImageCarousel(images);
            //设置项目名称
            if(!"".equals(projectInfo.getProName())){
                textView_project_name.setText(projectInfo.getProName());
            }
            //项目类型
            if(!"".equals(projectInfo.getClassType())){
                textView_projectType.setText(projectInfo.getClassType());
            }
            //点赞量
            if(!"".equals(projectInfo.getLikeCount()) && projectInfo.getLikeCount()>=0){
                textView_like_num.setText(projectInfo.getLikeCount()+"");
            }
            //浏览量
            if(!"".equals(projectInfo.getSeeCount()) && projectInfo.getSeeCount()>=0){
                textView_browse_num.setText(projectInfo.getSeeCount()+"");
            }
            //设置项目简介
            if(!"".equals(projectInfo.getIntroduce())){
                textView_project_brief.setText(projectInfo.getIntroduce());
            }
            //作品来源
            if(!"".equals(projectInfo.getPubName())){
                textView_projectBy.setText(projectInfo.getPubName());
            }
            //项目上传时间
            if(!"".equals(projectInfo.getUpData())){
                textView_project_uploadTime.setText(TimeFormatUtil.timeFormatUtil(projectInfo.getUpData()));
            }
            //获取评论
            final CommentFilter commentFilter = new CommentFilter();
            commentFilter.setBelongId(projectInfo.getId());
            commentFilter.setPageCount(2);//查询两条评论
            RequestUtil<CommentFilter> commentHttp = new RequestUtil<CommentFilter>(IP+"/exProject/getExProjectComment");
            commentHttp.conn(commentFilter, CommentFilter.class, new RequestUtil.RequestCallback() {
                @Override
                public void before() {
                }
                @Override
                public void success(String res) {
                    //设置评论值
                    if(null != res && !"".equals(res)){
                        List<Comment> commentList = new ArrayList<Comment>(Arrays.asList(new Gson().fromJson(res,Comment[].class)));
                        if(commentList.size()>0){
                            commentY_NsetVisib(1);//设置相关组件的可见性
                            for (Comment comment : commentList){
                                addCommentToLayout(comment);
                            }
                        }else{
                            Toast.makeText(ProjectDetailsActivity.this, "返回数据为空", Toast.LENGTH_SHORT).show();
                            commentY_NsetVisib(0);
                        }
                    }else{
                        //没有评论空空如也！
                        commentY_NsetVisib(0);
                    }
                }
                @Override
                public void error(Exception e) {
                    //没有评论空空如也！
                    commentY_NsetVisib(0);
                }
            });
        }
    }

    public void bindEvent(){
        upimage_button.setOnClickListener(this);
        downimage_button.setOnClickListener(this);
        layout_more_comment.setOnClickListener(this);
        button_release_comment.setOnClickListener(this);
    }
    //所有点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //------------上一张图片
            case R.id.button_upimage:
                //得到当前显示图片
                int position = project_details_gallery.getSelectedItemPosition();
                //-1上一张
                if(position > 0){
                    project_details_gallery.setSelection(position-1);
                }else{
                    project_details_gallery.setSelection(2);
                }
                break;
            //------------下一张图片
            case R.id.button_downimage:
                //得到当前显示图片
                int position_1 = project_details_gallery.getSelectedItemPosition();
                //-1上一张
                if(position_1 < 2){
                    project_details_gallery.setSelection(position_1+1);
                }else{
                    project_details_gallery.setSelection(0);
                }
                break;
            //------------查看更多评论点击事件,跳转到跟多评论界面
            case R.id.layout_more_comment:
                if(projectInfo != null){
                    Intent intent = new Intent(ProjectDetailsActivity.this,MoreCommentActivity.class);
                    String dataString = new Gson().toJson(projectInfo);
                    //将项目信息传到跟多评论界面
                    intent.putExtra("projectInfo",dataString);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "本界面数据异常", Toast.LENGTH_SHORT).show();
                }
                break;
            //-------------------发布评论
            case R.id.button_release_comment:
                if(userY_N){
                    String comment_content = editText_comment_input.getText()+"";
                    if (!"".equals(comment_content)){
                        final Comment comment = new Comment();
                        comment.setCmerId(user.getId());//评论者 id
                        comment.setBelogerId(projectInfo.getId());//被评论项目id
                        comment.setCmerName(user.getUserName());//评论者姓名
                        comment.setCmerPor(user.getPortrait());//评论者 头像地址
                        comment.setContent(comment_content);//评论内容
                        comment.setLikeCount(0);//评论点赞量 ， 刚发布为0
                        RequestUtil<Comment> releaseCommentHttp = new RequestUtil<Comment>(IP+"/exProject/upComment");
                        releaseCommentHttp.conn(comment, Comment.class, new RequestUtil.RequestCallback() {
                            @Override
                            public void before() {
                            }

                            @Override
                            public void success(String res) {
                                if(null != res && !"".equals(res)){
                                    ResBoolean resboolean = new Gson().fromJson(res,ResBoolean.class);
                                    if (resboolean.getRes()){
                                        //评论成功，添加到先有评论当中, 显示查看更多
                                        commentY_NsetVisib(1);
                                        addCommentToLayout(comment);
                                        //清空评论区
                                        editText_comment_input.setText("");
                                    }else {
                                        Toast.makeText(ProjectDetailsActivity.this, "sorry，评论提交失败，请重新评论", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(ProjectDetailsActivity.this, "系统故障，评论失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void error(Exception e) {
                                Toast.makeText(ProjectDetailsActivity.this, "系统故障，评论失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        Toast.makeText(this, "您还没有编写评论呢", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "您还没有登陆，请登陆后再评论", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void setImageCarousel(String[] images){
        //设置界面展示图片
        final ImageAdapter imageAdapter = new ImageAdapter(ProjectDetailsActivity.this,images);
        //设置图片与图片之间的横向距离
        project_details_gallery.setSpacing(20);
        project_details_gallery.setAdapter(imageAdapter);
        project_details_gallery.setSelection(1);//设置显示第2张照片
        //设置滑动事件
        project_details_gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //当图片选中时
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageAdapter.setSelectItem(position);
                imageAdapter.notifyDataSetChanged();//滑动时响应事件，通知适配器数据改变
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //设置图片点击事件
        project_details_gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    //设置评论有无时，更多评论和  “无评论” 组件的 可见性
    public void commentY_NsetVisib(int choose){
        if(choose == 0){//没有评论
            textView_nocomment.setVisibility(View.VISIBLE);
            layout_more_comment.setGravity(View.GONE);
        }else{//有评论
            textView_nocomment.setVisibility(View.GONE);
            layout_more_comment.setVisibility(View.VISIBLE);
        }
    }

    //添加评论到指定区域
    public void addCommentToLayout(Comment comment){
        View view = LayoutInflater.from(ProjectDetailsActivity.this).inflate(R.layout.list_item_comment,null);
        ImageView avatar_imageView = (ImageView) view.findViewById(R.id.imageView_commenter_avatar);//评论人头像
        TextView comment_content = (TextView)view.findViewById(R.id.textView_comment_content);//评论内容
        TextView textView_commenter_name = (TextView) view.findViewById(R.id.textView_commenter_name);//评论人名字
        TextView textView_like_num = (TextView) view.findViewById(R.id.textView_like_num);//评论点赞量
        TextView textView_comment_time = (TextView) view.findViewById(R.id.textView_comment_time);//评论发布时间
        //评论者头像
        Glide.with(ProjectDetailsActivity.this).load(IP+comment.getCmerPor()).error(R.drawable.club_avatar).into(avatar_imageView);
        //评论内容
        if(null != comment.getContent() && !"".equals(comment.getContent())){
            comment_content.setText(comment.getContent());
        }
        //评论者名称
        if(null != comment.getCmerName() && !"".equals(comment.getCmerName())){
            textView_commenter_name.setText(comment.getCmerName());
        }
        //点赞量
        if(comment.getLikeCount()>=0){
            textView_like_num.setText(comment.getLikeCount()+"");
        }
        //评论时间
        if(null != comment.getCmTime() &&!"".equals(comment.getCmTime())){
            textView_comment_time.setText(comment.getCmTime());
        }
        layout_commentlayout.addView(view);
    }
}
