/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 教师账号信息 实体类
 *
 * @author BladeX
 * @since 2020-07-11
 */
@Data
@ApiModel(value = "TeachAccount对象", description = "教师账号信息 ")
public class TeachAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 教师ID
	*/
		@ApiModelProperty(value = "教师ID")
		@TableId(value = "teach_id",type= IdType.AUTO)
		private Integer teachId;
	/**
	* 账号名
	*/
		@ApiModelProperty(value = "账号名")
		private String teachAccount;
	/**
	* 手机号
	*/
		@ApiModelProperty(value = "手机号")
		private String teachPhone;
	/**
	* 邮箱
	*/
		@ApiModelProperty(value = "邮箱")
		private String teachEmail;
	/**
	* 密码
	*/
		@ApiModelProperty(value = "密码")
		private String passwd;
	private LocalDateTime createTime;


}
