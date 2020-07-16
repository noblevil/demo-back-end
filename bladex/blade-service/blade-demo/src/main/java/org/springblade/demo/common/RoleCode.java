package org.springblade.demo.common;

import lombok.Data;
import org.springblade.demo.annotation.Role;

/**
 * Created with IntelliJ IDEA.
 * Author: cc
 * Date: 2020/07/10/10:56
 * Description:
 */
public enum RoleCode {

	VISITOR(-1, "visitor"),

	ADMIN(0, "admin"),

	TEACH(1, "teach"),

	ORG(2, "org");

	// 角色代码
	int code;
	// 角色信息
	String role;

	RoleCode(int code, String role){
		this.code = code;
		this.role = role;
	}

	public int getCode() {
		return code;
	}

	public String getRole() {
		return role;
	}

	/**
	 * 判断字符串是否与某个RoleCode等价
	 * @param role 要比较的角色字符串
	 * @return true：等价，false：不等价
	 */
	public boolean equals(String role){
		return this.getRole().equals(role);
	}

	/**
	 * 验证role值的合法性
	 * @param role 用户登录请求中传入的role值
	 * @return
	 */
	public static boolean checkValidity(String role) {
		for (RoleCode rc : RoleCode.values()) {
			if(rc.getRole().equals(role))
				return true;
		}
		return false;
	}

	public static RoleCode[] getAllRoles() {
		return RoleCode.values();
	}

}
