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

import org.springblade.demo.entity.Complaint;
import org.springblade.demo.vo.ComplaintVO;
import org.springblade.demo.mapper.ComplaintMapper;
import org.springblade.demo.service.IComplaintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 校外投诉  服务实现类
 *
 * @author BladeX
 * @since 2020-07-15
 */
@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements IComplaintService {

	@Override
	public IPage<ComplaintVO> selectComplaintPage(IPage<ComplaintVO> page, ComplaintVO complaint) {
		return page.setRecords(baseMapper.selectComplaintPage(page, complaint));
	}

}
