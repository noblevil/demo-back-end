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
import org.springblade.demo.entity.RelOrgTeach;
import org.springblade.demo.service.IRelOrgTeachService;
import org.springblade.demo.vo.RelOrgTeachVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2020-07-12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/relorgteach")
@Api(value = "", tags = "接口")
public class RelOrgTeachController extends BladeController {

	private IRelOrgTeachService relOrgTeachService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入relOrgTeach")
	public R<RelOrgTeach> detail(RelOrgTeach relOrgTeach) {
		RelOrgTeach detail = relOrgTeachService.getOne(Condition.getQueryWrapper(relOrgTeach));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入relOrgTeach")
	public R<IPage<RelOrgTeach>> list(RelOrgTeach relOrgTeach, Query query) {
		IPage<RelOrgTeach> pages = relOrgTeachService.page(Condition.getPage(query), Condition.getQueryWrapper(relOrgTeach));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入relOrgTeach")
	public R<IPage<RelOrgTeachVO>> page(RelOrgTeachVO relOrgTeach, Query query) {
		IPage<RelOrgTeachVO> pages = relOrgTeachService.selectRelOrgTeachPage(Condition.getPage(query), relOrgTeach);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入relOrgTeach")
	public R save(@Valid @RequestBody RelOrgTeach relOrgTeach) {
		return R.status(relOrgTeachService.save(relOrgTeach));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入relOrgTeach")
	public R update(@Valid @RequestBody RelOrgTeach relOrgTeach) {
		return R.status(relOrgTeachService.updateById(relOrgTeach));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入relOrgTeach")
	public R submit(@Valid @RequestBody RelOrgTeach relOrgTeach) {
		return R.status(relOrgTeachService.saveOrUpdate(relOrgTeach));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(relOrgTeachService.removeByIds(Func.toLongList(ids)));
	}


}
