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
 * @since 2020-07-12
 */
@Data
@ApiModel(value = "RelOrgTeach对象", description = "RelOrgTeach对象")
public class RelOrgTeach implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "rel_id", type = IdType.AUTO)
	private Integer relId;
	private Integer orgId;
	private Integer teachId;
	/**
	* 0 表示机构确认  教师没确认   1 表示机构 教师双方确认2 表示 教师提出离职申请 机构还未确认  3 表示 已经离职
	*/
		@ApiModelProperty(value = "0 表示机构确认  教师没确认   1 表示机构 教师双方确认2 表示 教师提出离职申请 机构还未确认  3 表示 已经离职")
		private Integer orgTeachStatus;
	private LocalDateTime startTime;
	private LocalDateTime endTime;


}
