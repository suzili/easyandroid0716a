package com.easyandroid.util;

import com.easyandroid.dto.PerUserDto;

/**
 * Created by NightStar on 2017/7/12.
 */

public class Constant {
	public static final String IP = "http://192.168.1.24:8080";
	public static final PerUserDto user = new PerUserDto(1L, "罗勇测试账号", "15700586290", "", "upload/exProject/images/7edc94d8-e1be-48b0-a593-34033a2a7acc.jpg", "");

	//获取项目类型列表
	public static String apptype = IP + "/teaProject/getAppType";
	//获取项目阶段列表
	public static String appstage = IP + "/teaProject/getStage";
	//注册
	public static String appRegister = IP + "/appUser/proRegister";
}
