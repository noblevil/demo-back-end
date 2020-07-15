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

/**
 * 机构资质材料 实体类
 *
 * @author BladeX
 * @since 2020-07-11
 */
@Data
@ApiModel(value = "OrgQualifData对象", description = "机构资质材料 ")
public class OrgQualifData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 机构ID 机构标识符
	*/
		@ApiModelProperty(value = "机构ID 机构标识符")

		private Integer orgId;
	/**
	* 法人姓名
	*/
		@ApiModelProperty(value = "法人姓名")
		private String legalRepresentName;
	/**
	* 手机号码
	*/
		@ApiModelProperty(value = "手机号码")
		private String legalRepresentPhone;
	/**
	* 证件类型 1、身份证；2、港澳通行证；3、台湾通行证；4、护照；5、外国人身份证；6、港澳台居住证；
	*/
		@ApiModelProperty(value = "证件类型 1、身份证；2、港澳通行证；3、台湾通行证；4、护照；5、外国人身份证；6、港澳台居住证；")
		private Integer idType;
	/**
	* 证件号码
	*/
		@ApiModelProperty(value = "证件号码")
		private String idNum;
	/**
	* 身份证正面 用于存放地址
	*/
		@ApiModelProperty(value = "身份证正面 用于存放地址")
		private String idCardFront;
	/**
	* 身份证反面
	*/
		@ApiModelProperty(value = "身份证反面")
		private String idCardReverse;
	/**
	* ICP备案号
	*/
		@ApiModelProperty(value = "ICP备案号")
		private String icpRecordNum;
	/**
	* 电信业务营业许可证
	*/
		@ApiModelProperty(value = "电信业务营业许可证")
		private String telecommunicationBusinessLicense;
	/**
	* 网络安全等级备案证明 证明图片的存储地址
	*/
		@ApiModelProperty(value = "网络安全等级备案证明 证明图片的存储地址")
		private String networkSecLevRec;


}
