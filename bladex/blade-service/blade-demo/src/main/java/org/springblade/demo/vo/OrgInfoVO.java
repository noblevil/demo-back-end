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
package org.springblade.demo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.demo.entity.OrgInfo;

/**
 * 机构信息 视图实体类
 *
 * @author BladeX
 * @since 2020-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OrgInfoVO对象", description = "机构信息 ")
public class OrgInfoVO extends OrgInfo {
	private static final long serialVersionUID = 1L;

}
