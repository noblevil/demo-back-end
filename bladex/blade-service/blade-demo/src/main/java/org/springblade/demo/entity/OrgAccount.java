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

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 机构登录信息 实体类
 *
 * @author BladeX
 * @since 2020-07-11
 */
@Data
@ApiModel(value = "OrgAccount对象", description = "机构登录信息 ")
public class OrgAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 机构ID
	*/
		@ApiModelProperty(value = "机构ID")
		@TableId(value = "org_id")
		private Integer orgId;
	/**
	* 机构账号名
	*/
		@ApiModelProperty(value = "机构账号名")
		private String orgAccount;
	/**
	* 手机号
	*/
		@ApiModelProperty(value = "手机号")
		private String orgPhone;
	/**
	* 邮箱
	*/
		@ApiModelProperty(value = "邮箱")
		private String orgEmail;
	/**
	* 密码
	*/
		@ApiModelProperty(value = "密码")
		private String passwd;
	/**
	* 账户创建时间
	*/
		@ApiModelProperty(value = "账户创建时间")
		private LocalDateTime createTime;


}
