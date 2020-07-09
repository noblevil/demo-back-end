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
import org.springblade.demo.annotation.Role;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.demo.entity.Info;
import org.springblade.demo.vo.InfoVO;
import org.springblade.demo.wrapper.InfoWrapper;
import org.springblade.demo.service.IInfoService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 机构信息  控制器
 *
 * @author BladeX
 * @since 2020-06-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/info")
@Api(value = "机构信息 ", tags = "机构信息 接口")
public class InfoController extends BladeController {

	private IInfoService infoService;

	@PostMapping("/ceshi")
	@Role("")
	public String tes(@RequestParam String str) {
		return str + "post success";
	}

	/**
	 * 详情
	 */
	@GetMapping("/detail")

	@ApiOperation(value = "详情", notes = "传入info")
	public R<InfoVO> detail(Info info) {
		Info detail = infoService.getOne(Condition.getQueryWrapper(info));
		return R.data(InfoWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 机构信息
	 */
	@GetMapping("/list")
	@ApiOperation(value = "分页", notes = "传入info")
	public R<IPage<InfoVO>> list(Info info, Query query) {
		IPage<Info> pages = infoService.page(Condition.getPage(query), Condition.getQueryWrapper(info));
		return R.data(InfoWrapper.build().pageVO(pages));
	}

	/**
	 * 自定义分页 机构信息
	 */
	@GetMapping("/page")

	@ApiOperation(value = "分页", notes = "传入info")
	public R<IPage<InfoVO>> page(InfoVO info, Query query) {
		IPage<InfoVO> pages = infoService.selectInfoPage(Condition.getPage(query), info);
		return R.data(pages);
	}

	/**
	 * 新增 机构信息
	 */
	@PostMapping("/save")

	@ApiOperation(value = "新增", notes = "传入info")
	public R save(@Valid @RequestBody Info info) {
		return R.status(infoService.save(info));
	}

	/**
	 * 修改 机构信息
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入info")
	public R update(@Valid @RequestBody Info info) {
		return R.status(infoService.updateById(info));
	}

	/**
	 * 新增或修改 机构信息
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入info")
	public R submit(@Valid @RequestBody Info info) {
		return R.status(infoService.saveOrUpdate(info));
	}


	/**
	 * 删除 机构信息
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(infoService.removeByIds(Func.toLongList(ids)));
	}


}
