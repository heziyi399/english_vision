package com.english.english_vision.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter

public enum ResponseEnum {

	ERROR(-1, "服务端错误"),

	SUCCESS(0, "成功"),

	PASSWORD_ERROR(1,"密码错误"),

	USERNAME_EXIST(2, "用户名已存在"),

	PARAM_ERROR(3, "参数错误"),
OPERATE_ERROR(8,"操作失败"),
	EMAIL_EXIST(4, "邮箱已存在"),
	WORD_NOT_EXIST(5, "暂无单词列表"),
	WORD_ERROR(6, "单词拼写错误"),
	WORD_ERROR_EXIST(7, "不好意思，暂未收录您搜索的单词！"),
	DIARY_NOT_EXIST(8,"未搜索到您查询的关键字！"),
	NEED_LOGIN(10, "用户未登录, 请先登录"),

	USERNAME_OR_PASSWORD_ERROR(11, "用户名或密码错误"),
	BLOG_NOT_EXIST(12,"未搜索到帖子！")


	;


    private final Integer code;

	private final String desc;

	ResponseEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
