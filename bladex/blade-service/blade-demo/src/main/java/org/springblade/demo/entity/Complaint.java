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
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 校外投诉 实体类
 *
 * @author BladeX
 * @since 2020-07-15
 */
@Data
@ApiModel(value = "Complaint对象", description = "校外投诉 ")
public class Complaint implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "complaint_id", type = IdType.AUTO)
	private Integer complaintId;
	/**
	 * 企业ID 机构标识符
	 */
	@ApiModelProperty(value = "企业ID 机构标识符")
	private Integer orgId;
	/**
	 * 投诉标题
	 */
	@ApiModelProperty(value = "投诉标题")
	private String title;
	/**
	 * 所投诉内容
	 */
	@ApiModelProperty(value = "所投诉内容")
	private String content;
	/**
	 * 改进建议
	 */
	@ApiModelProperty(value = "改进建议")
	private String suggest;


}
