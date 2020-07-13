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

import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 教师信息 实体类
 *
 * @author BladeX
 * @since 2020-07-12
 */
@Data
@ApiModel(value = "TeachInfo对象", description = "教师信息 ")
public class TeachInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 教师ID
	 */
	@ApiModelProperty(value = "教师ID")
	private Integer teachId;
	/**
	 * 教师名字
	 */
	@ApiModelProperty(value = "教师名字")
	private String teachName;
	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
	private String sex;
	/**
	 * 教师出生日期
	 */
	@ApiModelProperty(value = "教师出生日期")
	private LocalDate teachBirth;
	/**
	 * 教师授课学科
	 */
	@ApiModelProperty(value = "教师授课学科")
	private String teachingSubject;
	/**
	 * 是否有教师资格证
	 */
	@ApiModelProperty(value = "是否有教师资格证")
	private String isTeachQualifCert;
	/**
	 * 任教资格分类
	 */
	@ApiModelProperty(value = "任教资格分类")
	private String teachQualifClass;
	/**
	 * 证书号码
	 */
	@ApiModelProperty(value = "证书号码")
	private String certificateNum;
	/**
	 * 专业职称
	 */
	@ApiModelProperty(value = "专业职称")
	private String professionalTitle;
	/**
	 * 国籍性质
	 */
	@ApiModelProperty(value = "国籍性质")
	private String countryNature;
	/**
	 * 国籍
	 */
	@ApiModelProperty(value = "国籍")
	private String nationality;
	/**
	 * 最高学历
	 */
	@ApiModelProperty(value = "最高学历")
	private String highestEducation;
	private String educationalInstitution;
	/**
	 * 最高学位
	 */
	@ApiModelProperty(value = "最高学位")
	private String highestDegree;
	/**
	 * 学位获得院校或机构
	 */
	@ApiModelProperty(value = "学位获得院校或机构")
	private String degreeObtainedInstitution;
	/**
	 * 所学专业
	 */
	@ApiModelProperty(value = "所学专业")
	private String major;
	/**
	 * 毕业日期
	 */
	@ApiModelProperty(value = "毕业日期")
	private LocalDate graduationDate;
	/**
	 * 工作类型
	 */
	@ApiModelProperty(value = "工作类型")
	private String workType;
	private Integer idType;
	private String idNum;
	/**
	 * 籍贯
	 */
	@ApiModelProperty(value = "籍贯")
	private String nativePlace;
	/**
	 * 政治面貌
	 */
	@ApiModelProperty(value = "政治面貌")
	private String politicalStatus;


}
