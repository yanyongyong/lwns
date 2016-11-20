package com.nuesoft.lwn.common.enums;

import lombok.Getter;

/**
 * 用户操作错误枚举
 *
 * @created on 2016-09-15 17:52
 */
public enum  UserInputEnum {
	//注册
	REGISTER_SUCCESS("00000","新增成功"),
	USERNAME_HAS_EXIST("01000","用户名已存在"),
	USERNAME_NOT_LEGAL("01001","用户名不合法"),
	USER_PHONE("11000","电话号码不合法"),
	PASSWORD_NOT_LEGAL("01002","密码不合法"),
	EMAIL_NOT_LEGAL("01003","邮箱地址不合法"),
	EMAIL_HAS_EXIST("01004","邮箱地址已存在"),

	//删除
	DELECT_SUCCESS("11111","删除成功"),

	UPDATE_PASSWORD_SUCCESS("111101","修改密码成功"),


	//更新
	UPDATE_SUCCESS("11110","更新成功"),
	//登陆
	//logon_SUCCESS("0000","登陆成功"),
	USERNAME_NO_EXIST("02001","用户名不存在"),
	PASSWORD_ERROR("02002","密码错误");

	@Getter
	private String errorCode;
	@Getter
	private String errorMsg;

	/**
	 * 有参构造方法
	 * @param errorCode
	 * @param errorMsg
	 */
	UserInputEnum(String errorCode,String errorMsg){
		this.errorCode =errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * 返回错误信息
	 * @param errorCode
	 * @return
	 */
	public static String getErrorMsg(String errorCode){
		for(UserInputEnum userInputEnum:UserInputEnum.values()){
			if(userInputEnum.errorCode.equals(errorCode)){
				return  userInputEnum.errorMsg;
			}
		}
		return  null;
	}
}
