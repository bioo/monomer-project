package com.practice.my.practice.commons.exception;


import com.practice.my.practice.commons.dto.BaseResult;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.IllegalBlockSizeException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


/**
 * 全局异常处理
 *
 */
@ControllerAdvice
public class CustomExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public BaseResult handleSystemException(CustomException e){
		e.printStackTrace();
		logger.error(e.getMessage());
		return BaseResult.fail(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	@ResponseBody
	public BaseResult handleDuplicateKeyException(DuplicateKeyException e){
		e.printStackTrace();
		logger.error(String.format("数据库已存在该记录：%s",e.getMessage()));
		return BaseResult.fail(403,String.format("数据库已存在该记录：%s",e.getMessage()));
	}

	@ExceptionHandler(AuthorizationException.class)
	@ResponseBody
	public BaseResult handleAuthorizationException(AuthorizationException e){
		e.printStackTrace();
		logger.error("权限不足");
		return BaseResult.fail(401,"权限不足");
	}

	@ExceptionHandler(NoSuchMethodException.class)
	@ResponseBody
	public BaseResult handleNoSuchMethodException(NoSuchMethodException e){
		e.printStackTrace();
		logger.error("方法未找到");
		return BaseResult.fail(404,"方法未找到");
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseBody
	public BaseResult handleNumberFormatException(NumberFormatException e){
		e.printStackTrace();
		logger.error("字符串转换为数字异常");
		return BaseResult.fail("字符串转换为数字异常");
	}

	@ExceptionHandler(FileNotFoundException.class)
	@ResponseBody
	public BaseResult handleFileNotFoundException(FileNotFoundException e){
		e.printStackTrace();
		logger.error("文件未找到");
		return BaseResult.fail(410,"文件未找到");
	}

	@ExceptionHandler(EOFException.class)
	@ResponseBody
	public BaseResult handleEOFException(EOFException e){
		e.printStackTrace();
		logger.error("文件异常结束");
		return BaseResult.fail("文件异常结束");
	}

	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	public BaseResult handleDataAccessException(DataAccessException e){
		e.printStackTrace();
		logger.error("数据库操作失败");
		return BaseResult.fail(410,"数据库操作失败");
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public BaseResult handleNullPointerException(NullPointerException e){
		e.printStackTrace();
		logger.error("空指针，调用了未经初始化或者是不存在的对象！");
		return BaseResult.fail("空指针，调用了未经初始化或者是不存在的对象！");
	}

	@ExceptionHandler(IOException.class)
	@ResponseBody
	public BaseResult handleIOException(IOException e){
		e.printStackTrace();
		logger.error("IO读写异常！");
		return BaseResult.fail("IO读写异常！");
	}

	@ExceptionHandler(ClassNotFoundException.class)
	@ResponseBody
	public BaseResult handleClassNotFoundException(ClassNotFoundException e){
		e.printStackTrace();
		logger.error("指定的类不存在！");
		return BaseResult.fail("指定的类不存在！");
	}

	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public BaseResult handleArithmeticException(ArithmeticException e){
		e.printStackTrace();
		logger.error("数学运算异常！");
		return BaseResult.fail("数学运算异常！");

	}

	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ResponseBody
	public BaseResult handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e){
		e.printStackTrace();
		logger.error("数组下标越界！");
		return BaseResult.fail("数组下标越界！");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public BaseResult handleIllegalArgumentException(IllegalArgumentException e){
		e.printStackTrace();
		logger.error("调用方法的参数错误！");
		return BaseResult.fail(501,"调用方法的参数错误！");
	}

	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public BaseResult handleClassCastException(ClassCastException e){
		e.printStackTrace();
		logger.error("类型强制转换错误！");
		return BaseResult.fail("类型强制转换错误！");
	}

	@ExceptionHandler(SecurityException.class)
	@ResponseBody
	public BaseResult handleSecurityException(SecurityException e){
		e.printStackTrace();
		logger.error("违背安全原则异常：");
		return BaseResult.fail("违背安全原则异常！");
	}

	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public BaseResult handleSQLException(SQLException e){
		e.printStackTrace();
		logger.error("操作数据库异常！");
		return BaseResult.fail("操作数据库异常！");
	}

	@ExceptionHandler(NoSuchMethodError.class)
	@ResponseBody
	public BaseResult handleNoSuchMethodError(NoSuchMethodError e){
		e.printStackTrace();
		logger.error("调用了未定义的方法！");
		return BaseResult.fail(405,"调用了未定义的方法！");
	}

	@ExceptionHandler(InternalError.class)
	@ResponseBody
	public BaseResult handleInternalError(InternalError e){
		e.printStackTrace();
		logger.error("Java虚拟机发生了内部错误！");
		return BaseResult.fail("Java虚拟机发生了内部错误！");
	}

	@ExceptionHandler(IllegalBlockSizeException.class)
	@ResponseBody
	public BaseResult handleIllegalBlockSizeException(IllegalBlockSizeException e){
		e.printStackTrace();
		logger.error("数据权限保护，您无权操作该数据！");
		return BaseResult.fail(407,"数据权限保护，您无权操作该数据！");
	}
}
