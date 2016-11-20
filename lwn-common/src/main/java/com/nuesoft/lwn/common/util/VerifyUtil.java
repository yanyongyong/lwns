package com.nuesoft.lwn.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户输入验证工具类
 *
 * Created by yanyong on 2016/11/13.
 */
public class VerifyUtil {

	private static Pattern pattern;
	private static Matcher matcher;
	public static final String USERNAME_REGEX = "^[A-Za-z][\\w_]{3,15}$";
	public static final String PASSWORD_REGEX = "[a-zA-Z0-9]{6}";
	public static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";


	/**
	 * 验证用户名是否合法
	 *
	 * @param userName
	 * @return
	 */
	public static boolean verifyUserName(String userName) {
		boolean flag = false;
		pattern = Pattern.compile(USERNAME_REGEX);
		matcher = pattern.matcher(userName);
		flag = matcher.matches();
		return flag;
	}

	/**
	 * 手机号验证
	 *
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 验证密码是否合法
	 *
	 * @param password
	 * @return
	 */
	public static boolean verifyPassword(String password) {
		boolean flag = false;
		pattern = Pattern.compile(PASSWORD_REGEX);
		matcher = pattern.matcher(password);
		flag = matcher.matches();
		return flag;
	}


	/**
	 * 验证邮箱是否合法
	 *
	 * @param email
	 * @return
	 */
	public static boolean verifyEmail(String email) {
		boolean flag = false;
		pattern = Pattern.compile(EMAIL_REGEX);
		matcher = pattern.matcher(email);
		flag = matcher.matches();
		return flag;
	}
}
