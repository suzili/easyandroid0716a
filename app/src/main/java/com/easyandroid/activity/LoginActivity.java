package com.easyandroid.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.easyandroid.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEditTextAccountInput;
    private EditText mEditTextPasswordInput;
    private ImageView mIamgeViewShowRmState;
    private LinearLayout mLayoutPersonalUser;
    private ImageView mImageViewPersonal;
    private LinearLayout mLayoutSchoolUser;
    private ImageView mIamgeViewSchoolUser;
    private Button mButton_login;
    private Button mButtonReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setValue();
        bindEvent();
    }
    public void initView(){
        mEditTextAccountInput = (EditText) findViewById(R.id.editText_account_input);
        mEditTextPasswordInput = (EditText) findViewById(R.id.editText_password_input);
        mIamgeViewShowRmState = (ImageView) findViewById(R.id.iamgeView_show_rm_state);
        mLayoutPersonalUser = (LinearLayout) findViewById(R.id.layout_personalUser);
        mImageViewPersonal = (ImageView) findViewById(R.id.imageView_personal);
        mLayoutSchoolUser = (LinearLayout) findViewById(R.id.layout_schoolUser);
        mIamgeViewSchoolUser = (ImageView) findViewById(R.id.iamgeView_schoolUser);
        mButton_login = (Button) findViewById(R.id.button_login);
        mButtonReg = (Button) findViewById(R.id.button_reg);
    }
    public void setValue(){

    }
    public void bindEvent(){
        mLayoutPersonalUser.setOnClickListener(this);
        mLayoutSchoolUser.setOnClickListener(this);
        mButton_login.setOnClickListener(this);
        mButtonReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //---------------------选择个人用户
            case R.id.layout_personalUser:
                setShowLoginType(1);
                break;
            //---------------------选择学校用户
            case R.id.layout_schoolUser:
                setShowLoginType(2);
                break;
            case R.id.button_login:
                //判断输入是否正确
                String account = mEditTextAccountInput.getText().toString();
                String password = mEditTextPasswordInput.getText().toString();
                if("".equals(account)){
                    mEditTextAccountInput.setError("请输入您的登录账号");
                }else if("".equals(password)){
                        mEditTextPasswordInput.setError("请输入您的登录密码");
                }else{
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.button_reg:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }
    public void setShowLoginType(int choose){
        if(choose == 1){//个人用户
            mImageViewPersonal.setBackgroundResource(R.drawable.ic_personal_user_image);
            mIamgeViewSchoolUser.setBackgroundResource(R.drawable.ic_school_user_image);
        }else{//学校用户
            mImageViewPersonal.setBackgroundResource(R.drawable.ic_school_user_image);
            mIamgeViewSchoolUser.setBackgroundResource(R.drawable.ic_personal_user_image);
        }
    }
}
