package org.springblade.demo.util;

import org.springblade.demo.annotation.Role;
import org.springblade.demo.common.RoleCode;
import org.springblade.demo.common.exception.AuthException;
import org.springblade.demo.common.response.ResultCode;
import org.springframework.web.method.HandlerMethod;

/**
 * Created with IntelliJ IDEA.
 * Author: cc
 * Date: 2020/07/09/11:11
 * Description: 权限验证工具类
 */
public class AuthUtil {

	public static void AuthCheck(String role, Object handler) {

		//admin拥有所有权限
		if (RoleCode.ADMIN.equals(role))
			return;

		// 对带有Role注解的类或方法进行权限认定
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Role classAnnt = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Role.class);
			Role methodAnnt = handlerMethod.getMethodAnnotation(Role.class);

			// 若方法上，Role注解不为空
			if (methodAnnt != null) {
				if (!checkInclude(role, methodAnnt.include())) {
					throw new AuthException(ResultCode.PERMISSION_UNAUTHORISE);
				}
			} else {
				// 若方法上，Role注解不为空
				if (classAnnt != null) {
					if (!checkInclude(role, classAnnt.include())) {
						throw new AuthException(ResultCode.PERMISSION_UNAUTHORISE);
					}
				}
			}
		}
	}

	/**
	 * 判断当前角色是否在访问列表内
	 * @param role 当前角色
	 * @param rcs 访问列表（include列表或exclude列表）
	 * @return true：在列表内，false：不在列表内
	 */
	public static boolean checkInclude(String role, RoleCode[] rcs) {
		for (RoleCode rc : rcs) {
			if (rc.getRole().equals(role))
				return true;
		}
		return false;
	}

}
