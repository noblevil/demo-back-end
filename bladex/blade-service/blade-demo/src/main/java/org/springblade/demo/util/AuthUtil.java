package org.springblade.demo.util;

import org.springblade.demo.annotation.Role;
import org.springblade.demo.common.exception.AuthException;
import org.springblade.demo.common.response.ResultCode;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Author: cc
 * Date: 2020/07/09/11:11
 * Description: 权限验证工具类
 */
public class AuthUtil {

	public static boolean AuthCheck(String role, Object handler) {
		//admin拥有所有权限
		if (role.equals("admin"))
			return true;

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Role roleAnno = handlerMethod.getMethodAnnotation(Role.class);

			// 对带有Role注解的方法进行权限认定
			if (roleAnno != null && !Arrays.asList(roleAnno.value()).contains(role))
				throw new AuthException(ResultCode.PERMISSION_UNAUTHORISE);
		}

		return true;
	}

}
