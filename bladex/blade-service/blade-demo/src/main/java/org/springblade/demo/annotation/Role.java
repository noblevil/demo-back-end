package org.springblade.demo.annotation;

import org.springblade.demo.common.RoleCode;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Author: cc
 * Date: 2020/07/10/10:16
 * Description: 搭配common/RoleCode使用
 */
@Target({ElementType.METHOD, ElementType.TYPE})								// 方法注解
@Retention(RetentionPolicy.RUNTIME)							// 运行时
@Documented
public @interface Role{
	// 默认为空，多个角色用逗号分隔

	/**
	 * 允许访问的角色类型
	 * 默认值为admin权限的用户，只起占位作用
	 */
	RoleCode[] include() default RoleCode.ADMIN;

	/**
	 * 不允许访问的角色类型
	 * 默认值为visitor权限的用户，只起占位作用
	 */
	RoleCode[] exclude() default RoleCode.VISITOR;
}
