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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 课程内容 实体类
 *
 * @author BladeX
 * @since 2020-07-13
 */
@Data
@ApiModel(value = "Course对象", description = "课程内容 ")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 课程ID
	*/
		@ApiModelProperty(value = "课程ID")
		@TableId(value = "course_id", type = IdType.AUTO)
	private Integer courseId;
	/**
	* 外键
	*/
		@ApiModelProperty(value = "外键")
		private Integer orgId;
	/**
	* 课程名称
	*/
		@ApiModelProperty(value = "课程名称")
		private String courseName;
	/**
	* 学科 1、语文；2、数学；3、英语；4、思想政治；5、历史；6、地理；7、物理；8、化学；9、生物
	*/
		@ApiModelProperty(value = "学科 1、语文；2、数学；3、英语；4、思想政治；5、历史；6、地理；7、物理；8、化学；9、生物")
		private String courseSubject;
	/**
	* 课程级别 (初级、中级、高级)
	*/
		@ApiModelProperty(value = "课程级别 (初级、中级、高级)")
		private String courseLevel;
	/**
	* 课程链接
	*/
		@ApiModelProperty(value = "课程链接")
		private String courseLink;
	/**
	* 课程介绍
	*/
		@ApiModelProperty(value = "课程介绍")
		private String contentIntro;
	/**
	* 课程有效期
	*/
		@ApiModelProperty(value = "课程有效期")
		private String validPeriod;
	/**
	* 招生对象学段
	*/
		@ApiModelProperty(value = "招生对象学段")
		private String studentRank;
	/**
	* 招生对象年级
	*/
		@ApiModelProperty(value = "招生对象年级")
		private String studentGrade;
	/**
	* 总课次
	*/
		@ApiModelProperty(value = "总课次")
		private String totalLessons;
	/**
	* 教材
	*/
		@ApiModelProperty(value = "教材")
		private String textbook;
	/**
	* 出版社
	*/
		@ApiModelProperty(value = "出版社")
		private String publishCompany;
	/**
	* 发行号ISBN
	*/
		@ApiModelProperty(value = "发行号ISBN")
		@TableField("ISBN_number")
	private String isbnNumber;


}
