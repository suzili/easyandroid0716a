package com.easyandroid.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 三臻 on 2017/7/15.
 */

public class FormatUtil {

	/**
	 * 检查邮箱地址
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email){
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 检查手机号
	 * @param tel
	 * @return
	 */
	public static boolean checkTel(String tel){
		boolean flag = false;
		if (tel.length() > 6){
			flag = true;
		}
		return flag;
	}

	/**
	 * 检查JSONObject res
	 * @param mJsonObject
	 * @return
	 */
	public static boolean checkJsonRes(JSONObject mJsonObject){
		boolean flag = false;
		try {
			flag = mJsonObject.getBoolean("res");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
