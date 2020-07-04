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
import java.time.LocalDate;

/**
 * 课程班级信息 实体类
 *
 * @author BladeX
 * @since 2020-07-04
 */
@Data
@ApiModel(value = "CourseClass对象", description = "课程班级信息 ")
public class CourseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 序号
	*/
		@ApiModelProperty(value = "序号")
		@TableId(value = "course_class_id", type = IdType.AUTO)
	private Integer courseClassId;
	/**
	* 班级名称
	*/
		@ApiModelProperty(value = "班级名称")
		private String className;
	/**
	* 课程ID
	*/
		@ApiModelProperty(value = "课程ID")
		private Integer courseId;
	/**
	* 计划招生人数
	*/
		@ApiModelProperty(value = "计划招生人数")
		private Integer enrollNum;
	/**
	* 课程开始日期
	*/
		@ApiModelProperty(value = "课程开始日期")
		private LocalDate teachingTime;
	/**
	* 课程上课时间
	*/
		@ApiModelProperty(value = "课程上课时间")
		private LocalDate startTime;
	/**
	* 课程结束日期
	*/
		@ApiModelProperty(value = "课程结束日期")
		private LocalDate endTime;


}
