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
import org.springblade.demo.entity.RelTeachCourseClass;
import org.springblade.demo.service.IRelTeachCourseClassService;
import org.springblade.demo.vo.RelTeachCourseClassVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2020-07-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/relteachcourseclass")
@Api(value = "", tags = "接口")
public class RelTeachCourseClassController extends BladeController {

	private IRelTeachCourseClassService relTeachCourseClassService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入relTeachCourseClass")
	public R<RelTeachCourseClass> detail(RelTeachCourseClass relTeachCourseClass) {
		RelTeachCourseClass detail = relTeachCourseClassService.getOne(Condition.getQueryWrapper(relTeachCourseClass));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入relTeachCourseClass")
	public R<IPage<RelTeachCourseClass>> list(RelTeachCourseClass relTeachCourseClass, Query query) {
		IPage<RelTeachCourseClass> pages = relTeachCourseClassService.page(Condition.getPage(query), Condition.getQueryWrapper(relTeachCourseClass));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入relTeachCourseClass")
	public R<IPage<RelTeachCourseClassVO>> page(RelTeachCourseClassVO relTeachCourseClass, Query query) {
		IPage<RelTeachCourseClassVO> pages = relTeachCourseClassService.selectRelTeachCourseClassPage(Condition.getPage(query), relTeachCourseClass);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入relTeachCourseClass")
	public R save(@Valid @RequestBody RelTeachCourseClass relTeachCourseClass) {
		return R.status(relTeachCourseClassService.save(relTeachCourseClass));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入relTeachCourseClass")
	public R update(@Valid @RequestBody RelTeachCourseClass relTeachCourseClass) {
		return R.status(relTeachCourseClassService.updateById(relTeachCourseClass));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入relTeachCourseClass")
	public R submit(@Valid @RequestBody RelTeachCourseClass relTeachCourseClass) {
		return R.status(relTeachCourseClassService.saveOrUpdate(relTeachCourseClass));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(relTeachCourseClassService.removeByIds(Func.toLongList(ids)));
	}


}
