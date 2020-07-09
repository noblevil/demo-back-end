package org.springblade.demo.annotation;

import java.lang.annotation.*;

/**
 * 角色注解
 *
 */
@Target({ElementType.METHOD})								// 方法注解
@Retention(RetentionPolicy.RUNTIME)							// 运行时
@Documented
public @interface Role{
	// 默认为空，多个角色用逗号分隔
	String[] value() default "";

}

