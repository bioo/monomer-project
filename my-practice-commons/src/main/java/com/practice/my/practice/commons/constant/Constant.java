package com.practice.my.practice.commons.constant;

/**
 * 全局常量
 * 
 * @author FOM
 *
 */
public interface Constant {

	/**
	 * session Key常亮
	 */
	public static final String SESSION_USER = "user";

	/**
	 * 超级管理员默认ID
	 */
	public static final long SUPER_ADMIN = 1; //写成Long后匹配错误

	/**
	 * 菜单类型
	 */
	public static class MenuType {
		public static final int SYS_MENU_NUM = 1000;  //系统菜单编号预留
		public static final int CATALOG = 0; 	//目录
		public static final int MENU = 1;		//菜单
		public static final int BUTTON = 2;		//按钮
	}
	/**
	 * 管理员状态
	 */
	public static class UserStatus {
		public static final int STATUS_LOCKED  = 0;		//锁定，禁止登录
		public static final int STATUS_NORMAL  = 1;		//正常
	}

	/**
	 * 定时器状态
	 */
	public static class ScheduleStatus {
		public static final int STATUS_NORMAL  = 0;		//正常
		public static final int STATUS_PAUSE  = 1;		//暂停
	}
}
