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
package org.springblade.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.demo.entity.OrgInfo;
import org.springblade.demo.vo.OrgInfoVO;

import java.util.List;

/**
 * 机构信息  Mapper 接口
 *
 * @author BladeX
 * @since 2020-07-03
 */
public interface OrgInfoMapper extends BaseMapper<OrgInfo> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param orgInfo
	 * @return
	 */
	List<OrgInfoVO> selectOrgInfoPage(IPage page, OrgInfoVO orgInfo);
//	public OrgInfo selectById(Integer id);

}
