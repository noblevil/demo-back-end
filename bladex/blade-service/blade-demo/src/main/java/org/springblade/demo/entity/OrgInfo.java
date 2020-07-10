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
import java.time.LocalDate;

/**
 * 机构信息 实体类
 *
 * @author BladeX
 * @since 2020-07-03
 */
@Data
@ApiModel(value = "OrgInfo对象", description = "机构信息 ")
public class OrgInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 企业ID
	*/
		@ApiModelProperty(value = "企业ID")
		@TableId(value = "org_id")
		private Integer orgId;
	/**
	* 机构类型
	*/
		@ApiModelProperty(value = "机构类型")
		private String orgType;
	/**
	* 企业名称（机构名称）
	*/
		@ApiModelProperty(value = "企业名称（机构名称）")
		private String orgName;
	/**
	* 企业简称
	*/
		@ApiModelProperty(value = "企业简称")
		private String orgSimplyName;

	@ApiModelProperty(value = "设立日期")
	private LocalDate establishedDate;
	/**
	* 企业电话号码
	*/
		@ApiModelProperty(value = "企业电话号码")
		private String orgPhone;
	/**
	* 企业注册地址
	*/
		@ApiModelProperty(value = "企业注册地址")
		private String registerAddress;
	/**
	* 企业常用地址 (实际经营地址)作为地图显示的内容
	*/
		@ApiModelProperty(value = "企业常用地址 (实际经营地址)作为地图显示的内容")
		private String oftenAddress;
	/**
	* 是否境外投资
	*/
		@ApiModelProperty(value = "是否境外投资")
		private String isInvestAbroad;
	/**
	* 办学许可证号
	*/
		@ApiModelProperty(value = "办学许可证号")
		private String schoolLicence;
	/**
	* 办学许可证审批部门
	*/
		@ApiModelProperty(value = "办学许可证审批部门")
		private String schoolLicenceDepartment;
	/**
	* 统一社会信用代码
	*/
		@ApiModelProperty(value = "统一社会信用代码")
		private String unifiedCode;
	/**
	* 常用联系人1
	*/
		@ApiModelProperty(value = "常用联系人1")
		private String linkmanOne;
	/**
	* 联系人1电话
	*/
		@ApiModelProperty(value = "联系人1电话")
		private String linkmanOnePhone;
	/**
	* 常用联系人2
	*/
		@ApiModelProperty(value = "常用联系人2")
		private String linkmanTwo;
	/**
	* 联系人2电话
	*/
		@ApiModelProperty(value = "联系人2电话")
		private String linkmanTwoPhone;
	/**
	* 培训类别
	*/
		@ApiModelProperty(value = "培训类别")
		private String trainType;
	/**
	* 培训内容
	*/
		@ApiModelProperty(value = "培训内容")
		private String trainContent;
	/**
	* 培训形式 (面授  在线)
	*/
		@ApiModelProperty(value = "培训形式 (面授  在线)")
		private String trainForm;
	/**
	* 招生对象
	*/
		@ApiModelProperty(value = "招生对象")
		private String enrollObject;
	/**
	* 招生范围 （区域）
	*/
		@ApiModelProperty(value = "招生范围 （区域）")
		private String enrollRegion;
	/**
	* 企业营业执照或事业单位法人证书
	*/
		@ApiModelProperty(value = "企业营业执照或事业单位法人证书")
		private String businessLicense;
	/**
	* 相关证书 软件企业认定证书、高新技术企业证书、计算机软件著作权登记证书
	*/
		@ApiModelProperty(value = "相关证书 软件企业认定证书、高新技术企业证书、计算机软件著作权登记证书")
		private String relatedCertificates;
	/**
	* 0：白名单；1：黑名单；2：灰名单
	*/
		@ApiModelProperty(value = "0：白名单；1：黑名单；2：灰名单")
		private Integer listType;


}
