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
package org.springblade.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.demo.entity.OrgQualifData;
import org.springblade.demo.vo.OrgQualifDataVO;

/**
 * 机构资质材料  服务类
 *
 * @author BladeX
 * @since 2020-07-11
 */
public interface IOrgQualifDataService extends IService<OrgQualifData> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param orgQualifData
	 * @return
	 */
	IPage<OrgQualifDataVO> selectOrgQualifDataPage(IPage<OrgQualifDataVO> page, OrgQualifDataVO orgQualifData);

}
