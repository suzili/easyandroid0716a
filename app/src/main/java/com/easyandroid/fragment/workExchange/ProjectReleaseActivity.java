package com.easyandroid.fragment.workExchange;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.easyandroid.R;

public class ProjectReleaseActivity extends AppCompatActivity {
    private Toolbar project_release_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_release);
        initView();
        setToolBar();
        setValue();
        bindEvent();
    }

    private void initView() {
        project_release_toolbar = (Toolbar) findViewById(R.id.project_release_toolbar);
    }
    private void setToolBar(){
        setSupportActionBar(project_release_toolbar);
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

    private void setValue(){
    }
    private void bindEvent(){

    }
}
