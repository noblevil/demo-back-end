package org.springblade.demo.common.exception;

import org.springblade.demo.common.response.ResultCode;

import java.text.MessageFormat;

/**
 * 自定义异常类型
 * @author pyy
 **/
public class CustomException extends RuntimeException {

    //错误代码
    ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public CustomException(ResultCode resultCode, Object... args){
        super(resultCode.getMessage());
        String message = MessageFormat.format(resultCode.getMessage(), args);
        this.resultCode = resultCode;
    }

	public ResultCode getResultCode(){
        return resultCode;
    }

}
