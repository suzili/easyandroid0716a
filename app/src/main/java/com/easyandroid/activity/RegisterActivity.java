package com.easyandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.easyandroid.R;
import com.easyandroid.dto.APPType;
import com.easyandroid.util.Constant;
import com.easyandroid.util.HttpUtil;
import com.easyandroid.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.easyandroid.util.FormatUtil.checkEmail;
import static com.easyandroid.util.FormatUtil.checkJsonRes;
import static com.easyandroid.util.FormatUtil.checkTel;

public class RegisterActivity extends AppCompatActivity {
	private ImageView mImageViewBack;
	private EditText mEditTextUser;
	private EditText mEditTextPassword;
	private EditText mEditTextMail;
	private EditText mEditTextPhone;
	private Button mButtonReg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		initView();
	}

	private void initView() {
		mImageViewBack = (ImageView) findViewById(R.id.imageView_back);
		mEditTextUser = (EditText) findViewById(R.id.editText_user);
		mEditTextPassword = (EditText) findViewById(R.id.editText_password);
		mEditTextMail = (EditText) findViewById(R.id.editText_mail);
		mEditTextPhone = (EditText) findViewById(R.id.editText_phone);
		mButtonReg = (Button) findViewById(R.id.button_reg);

		mImageViewBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mButtonReg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkRegister();
			}
		});
	}

	private void checkRegister() {
		String userName = mEditTextUser.getText() + "";
		String password = mEditTextPassword.getText() + "";
		String email = mEditTextMail.getText() + "";
		String phone = mEditTextPhone.getText() + "";
		if ("".equals(userName) || userName.length() < 6) {
			mEditTextUser.setError("用户名长度必须大于等于6位");
		} else if ("".equals(password) || password.length() < 6) {
			mEditTextPassword.setError("密码长度必须大于等于6位");
		} else if (!checkEmail(email)) {
			mEditTextMail.setError("邮箱地址不符合规范");
		} else if (!checkTel(phone)) {
			mEditTextPhone.setError("手机号不符合规范");
		} else {
			Map regMap = new HashMap();
			regMap.put("userName", userName);
			regMap.put("password", password);
			regMap.put("email", email);
			regMap.put("phone", phone);
			Register(regMap);
		}
	}

	private void Register(final Map regMap) {
		new Thread() {
			@Override
			public void run() {
				HttpUtil mHttpUtil = HttpUtil.getInstance();
				mHttpUtil.postForm(Constant.appRegister, regMap, new HashMap<String, File>(), new HttpUtil.PostCallBack() {
					@Override
					public void success(String res) {
						try {
							JSONObject mJsonObject = new JSONObject(res);
							if (checkJsonRes(mJsonObject)) {
								ToastUtil.makeToastShort(RegisterActivity.this, "success:" + mJsonObject.getString("message"));
								finish();
							} else {
								ToastUtil.makeToastShort(RegisterActivity.this, "fail:" + mJsonObject.getString("message"));
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void error(Exception e) {

					}
				});
			}
		}.start();
	}
}
