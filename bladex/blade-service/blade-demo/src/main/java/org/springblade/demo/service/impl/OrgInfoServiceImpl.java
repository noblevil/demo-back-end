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
package org.springblade.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.demo.entity.OrgInfo;
import org.springblade.demo.mapper.OrgInfoMapper;
import org.springblade.demo.service.IOrgInfoService;
import org.springblade.demo.vo.OrgInfoVO;
import org.springframework.stereotype.Service;

/**
 * 机构信息  服务实现类
 *
 * @author BladeX
 * @since 2020-07-03
 */
@Service
public class OrgInfoServiceImpl extends ServiceImpl<OrgInfoMapper, OrgInfo> implements IOrgInfoService {

	@Override
	public IPage<OrgInfoVO> selectOrgInfoPage(IPage<OrgInfoVO> page, OrgInfoVO orgInfo) {
		return page.setRecords(baseMapper.selectOrgInfoPage(page, orgInfo));
	}

//	@Override
//	public OrgInfo selectOrgInfoById(Integer id) {
//		OrgInfo detail=new OrgInfo();
//		OrgInfoMapper orgInfoMapper=
//		detail=selectById(id);
//		//使用mapper自定义的接口
//		return null;  //待实现
//	}

}
