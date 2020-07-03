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
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 机构信息 实体类
 *
 * @author BladeX
 * @since 2020-06-29
 */
@Data
@ApiModel(value = "Info对象", description = "机构信息 ")
public class Info implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 企业ID
	*/
		@ApiModelProperty(value = "企业ID")
		@TableId("Org_Id")
	private Integer orgId;
	/**
	* 企业名称
	*/
		@ApiModelProperty(value = "企业名称")
		@TableField("Org_Name")
	private String orgName;
	/**
	* 企业简称
	*/
		@ApiModelProperty(value = "企业简称")
		@TableField("Org_Simply_Name")
	private String orgSimplyName;
	/**
	* 企业注册地址
	*/
		@ApiModelProperty(value = "企业注册地址")
		@TableField("Org_Register_Add")
	private String orgRegisterAdd;
	/**
	* 企业常用地址 作为地图显示的内容
	*/
		@ApiModelProperty(value = "企业常用地址 作为地图显示的内容")
		@TableField("Org_Often_Add")
	private String orgOftenAdd;
	/**
	* 是否境外投资
	*/
		@ApiModelProperty(value = "是否境外投资")
		@TableField("Is_Invest_Abroad")
	private String isInvestAbroad;
	/**
	* 企业电话号码
	*/
		@ApiModelProperty(value = "企业电话号码")
		@TableField("Org_Phone_Num")
	private String orgPhoneNum;
	/**
	* 常用联系人1
	*/
		@ApiModelProperty(value = "常用联系人1")
		@TableField("Often_User_Fri")
	private String oftenUserFri;
	/**
	* 联系人1电话
	*/
		@ApiModelProperty(value = "联系人1电话")
		@TableField("Often_User_Fri_Num")
	private String oftenUserFriNum;
	/**
	* 常用联系人2
	*/
		@ApiModelProperty(value = "常用联系人2")
		@TableField("Often_User_Sec")
	private String oftenUserSec;
	/**
	* 联系人2电话
	*/
		@ApiModelProperty(value = "联系人2电话")
		@TableField("Often_User_Sec_Num")
	private String oftenUserSecNum;
	/**
	* 统一社会信用代码
	*/
		@ApiModelProperty(value = "统一社会信用代码")
		@TableField("Unified_Code")
	private String unifiedCode;
	/**
	* 企业营业执照或事业单位法人证书
	*/
		@ApiModelProperty(value = "企业营业执照或事业单位法人证书")
		@TableField("Org_Business_License")
	private String orgBusinessLicense;
	/**
	* 相关证书 软件企业认定证书、高新技术企业证书、计算机软件著作权登记证书
	*/
		@ApiModelProperty(value = "相关证书 软件企业认定证书、高新技术企业证书、计算机软件著作权登记证书")
		@TableField("Related_Certificates")
	private String relatedCertificates;
	/**
	* 办学许可证号
	*/
		@ApiModelProperty(value = "办学许可证号")
		@TableField("School_Licence_Num")
	private String schoolLicenceNum;
	/**
	* 办学许可证审批部门
	*/
		@ApiModelProperty(value = "办学许可证审批部门")
		@TableField("Lience_Department")
	private String lienceDepartment;
	/**
	* 0：白名单；1：黑名单；2：灰名单
	*/
		@ApiModelProperty(value = "0：白名单；1：黑名单；2：灰名单")
		@TableField("List_Type")
	private Integer listType;
	/**
	* 机构区域
	*/
		@ApiModelProperty(value = "机构区域")
		@TableField("Org_Region")
	private String orgRegion;
	/**
	* 0~6
	*/
		@ApiModelProperty(value = "0~6")
		@TableField("Train_Level")
	private Integer trainLevel;
	/**
	* 0~7
	*/
		@ApiModelProperty(value = "0~7")
		@TableField("Train_Subject")
	private Integer trainSubject;
	/**
	* 0~1
	*/
		@ApiModelProperty(value = "0~1")
		@TableField("Train_Form")
	private Integer trainForm;


}
