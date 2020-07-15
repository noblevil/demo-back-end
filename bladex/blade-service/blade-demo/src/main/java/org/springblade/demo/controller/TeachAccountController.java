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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.demo.entity.TeachAccount;
import org.springblade.demo.service.ITeachAccountService;
import org.springblade.demo.vo.TeachAccountVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 教师账号信息  控制器
 *
 * @author BladeX
 * @since 2020-07-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/teachaccount")
@Api(value = "教师账号信息 ", tags = "教师账号信息 接口")
public class TeachAccountController extends BladeController {

	private ITeachAccountService teachAccountService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入teachAccount")
	public R<TeachAccount> detail(TeachAccount teachAccount) {
		TeachAccount detail = teachAccountService.getOne(Condition.getQueryWrapper(teachAccount));
		return R.data(detail);
	}

	/**
	 * 分页 教师账号信息
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入teachAccount")
	public R<IPage<TeachAccount>> list(TeachAccount teachAccount, Query query) {
		IPage<TeachAccount> pages = teachAccountService.page(Condition.getPage(query), Condition.getQueryWrapper(teachAccount));
		return R.data(pages);
	}

	/**
	 * 自定义分页 教师账号信息
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入teachAccount")
	public R<IPage<TeachAccountVO>> page(TeachAccountVO teachAccount, Query query) {
		IPage<TeachAccountVO> pages = teachAccountService.selectTeachAccountPage(Condition.getPage(query), teachAccount);
		return R.data(pages);
	}

	/**
	 * 新增 教师账号信息
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入teachAccount")
	public R save(@Valid @RequestBody TeachAccount teachAccount) {
		return R.status(teachAccountService.save(teachAccount));
	}

	/**
	 * 修改 教师账号信息
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入teachAccount")
	public R update(@Valid @RequestBody TeachAccount teachAccount) {
		return R.status(teachAccountService.updateById(teachAccount));
	}

	/**
	 * 新增或修改 教师账号信息
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入teachAccount")
	public R submit(@Valid @RequestBody TeachAccount teachAccount) {
		return R.status(teachAccountService.saveOrUpdate(teachAccount));
	}


	/**
	 * 删除 教师账号信息
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(teachAccountService.removeByIds(Func.toLongList(ids)));
	}


}
