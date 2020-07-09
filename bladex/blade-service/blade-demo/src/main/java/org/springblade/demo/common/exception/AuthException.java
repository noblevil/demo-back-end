package org.springblade.demo.common.exception;

import org.springblade.demo.common.response.ResultCode;

/**
 * Created with IntelliJ IDEA.
 * Author: cc
 * Date: 2020/07/09/11:09
 * Description: 权限异常类，出现越权行为时抛出，由全局异常handler处理
 */
public class AuthException extends RuntimeException {
	//错误代码
	ResultCode resultCode;

	public AuthException(ResultCode resultCode){
		super(resultCode.message());
		this.resultCode = resultCode;
	}

	public ResultCode getResultCode(){
		return resultCode;
	}
}
