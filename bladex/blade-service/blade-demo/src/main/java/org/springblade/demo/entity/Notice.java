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
 * 实体类
 *
 * @author BladeX
 * @since 2020-07-18
 */
@Data
@ApiModel(value = "Notice对象", description = "Notice对象")
public class Notice implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 通知id
	*/
		@ApiModelProperty(value = "通知id")
		@TableId(value = "notice_id", type = IdType.AUTO)
	private Integer noticeId;
	/**
	* 通知标题
	*/
		@ApiModelProperty(value = "通知标题")
		private String title;
	/**
	* 通知内容
	*/
		@ApiModelProperty(value = "通知内容")
		private String content;
	/**
	* 通知发布时间
	*/
		@ApiModelProperty(value = "通知发布时间")
		private LocalDateTime time;
	/**
	* 通知发布者
	*/
		@ApiModelProperty(value = "通知发布者")
		private String publisher;


}
