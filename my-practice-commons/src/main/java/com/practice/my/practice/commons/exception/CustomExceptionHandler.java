package com.practice.my.practice.commons.exception;


import com.practice.my.practice.commons.util.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 全局异常处理
 *
 */
@ControllerAdvice
public class CustomExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	

	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public Map<String, Object> handleFomException(CustomException e){
		
		Result result = new Result();
		result.put("code", e.getCode());
		result.put("msg", e.getMessage());
		
		return result;
	}
	
	@ExceptionHandler(DuplicateKeyException.class)
	@ResponseBody
	public Map<String, Object> handleDuplicateKeyException(DuplicateKeyException e){
		logger.error("数据库记录重复添加:");
		return Result.error("数据库已存在该记录");
	}
	
	@ExceptionHandler(AuthorizationException.class)
	@ResponseBody
	public Map<String, Object> handleAuthorizationException(AuthorizationException e){
		logger.error("权限不足:");
		return Result.error("权限不足，联系管理员");
	}
	

}
