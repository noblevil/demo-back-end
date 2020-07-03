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
package org.springblade.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.demo.entity.Complaint;
import org.springblade.demo.vo.ComplaintVO;
import org.springblade.demo.wrapper.ComplaintWrapper;
import org.springblade.demo.service.IComplaintService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 控制器
 *
 * @author BladeX
 * @since 2020-06-16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/complaint")
@Api(value = "", tags = "接口")
public class ComplaintController extends BladeController {

	private IComplaintService complaintService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入complaint")
	public R<ComplaintVO> detail(Complaint complaint) {
		Complaint detail = complaintService.getOne(Condition.getQueryWrapper(complaint));

		detail.setClickNum(detail.getClickNum() + 1);

		complaintService.updateById(detail);

		return R.data(ComplaintWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")

	@ApiOperation(value = "分页", notes = "传入complaint")
	public R<IPage<ComplaintVO>> list(Complaint complaint, Query query) {
		IPage<Complaint> pages = complaintService.page(Condition.getPage(query), Condition.getQueryWrapper(complaint));
		return R.data(ComplaintWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页
	 */
	@GetMapping("/page")

	@ApiOperation(value = "分页", notes = "传入complaint")
	public R<IPage<ComplaintVO>> page(ComplaintVO complaint, Query query) {
		IPage<ComplaintVO> pages = complaintService.selectComplaintPage(Condition.getPage(query), complaint);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")

	@ApiOperation(value = "新增", notes = "传入complaint")
	public R save(@Valid @RequestBody Complaint complaint) {
		return R.status(complaintService.save(complaint));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")

	@ApiOperation(value = "修改", notes = "传入complaint")
	public R update(@Valid @RequestBody Complaint complaint) {
		return R.status(complaintService.updateById(complaint));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")

	@ApiOperation(value = "新增或修改", notes = "传入complaint")
	public R submit(@Valid @RequestBody Complaint complaint) {
		return R.status(complaintService.saveOrUpdate(complaint));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")

	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(complaintService.removeByIds(Func.toLongList(ids)));
	}


}
