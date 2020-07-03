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

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2020-06-16
 */
@Data
@TableName("index_complaint")
@ApiModel(value = "Complaint对象", description = "Complaint对象")
public class Complaint implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 序号
	*/
		@ApiModelProperty(value = "序号")
		@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	* 投诉类型
	*/
		@ApiModelProperty(value = "投诉类型")
		private String type;
	/**
	* 投诉对象
	*/
		@ApiModelProperty(value = "投诉对象")
		private String obj;
	/**
	* 投诉对象名称
	*/
		@ApiModelProperty(value = "投诉对象名称")
		private String objName;
	/**
	* 投诉标题
	*/
		@ApiModelProperty(value = "投诉标题")
		private String title;
	/**
	* 投诉内容
	*/
		@ApiModelProperty(value = "投诉内容")
		private String content;
	/**
	* 证明材料
	*/
		@ApiModelProperty(value = "证明材料")
		private String file;
	/**
	* 联系人
	*/
		@ApiModelProperty(value = "联系人")
		private String contactPerson;
	/**
	* 联系方式
	*/
		@ApiModelProperty(value = "联系方式")
		private String phone;
	/**
	* 发表时间
	*/
		@ApiModelProperty(value = "发表时间")
		private LocalDateTime createTime;
	/**
	* 点击数
	*/
		@ApiModelProperty(value = "点击数")
		private Integer clickNum;
	/**
	* 投诉状态
	*/
		@ApiModelProperty(value = "投诉状态")
		private Integer status;
	/**
	* 所在市
	*/
		@ApiModelProperty(value = "所在市")
		private String city;
	/**
	* 所在区
	*/
		@ApiModelProperty(value = "所在区")
		private String district;


}
