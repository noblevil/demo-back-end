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
import org.springblade.demo.entity.OrgQualifData;
import org.springblade.demo.service.IOrgQualifDataService;
import org.springblade.demo.vo.OrgQualifDataVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 机构资质材料  控制器
 *
 * @author BladeX
 * @since 2020-07-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orgqualifdata")
@Api(value = "机构资质材料 ", tags = "机构资质材料 接口")
public class OrgQualifDataController extends BladeController {

	private IOrgQualifDataService orgQualifDataService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入orgQualifData")
	public R<OrgQualifData> detail(OrgQualifData orgQualifData) {
		OrgQualifData detail = orgQualifDataService.getOne(Condition.getQueryWrapper(orgQualifData));
		return R.data(detail);
	}

	/**
	 * 分页 机构资质材料
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入orgQualifData")
	public R<IPage<OrgQualifData>> list(OrgQualifData orgQualifData, Query query) {
		IPage<OrgQualifData> pages = orgQualifDataService.page(Condition.getPage(query), Condition.getQueryWrapper(orgQualifData));
		return R.data(pages);
	}

	/**
	 * 自定义分页 机构资质材料
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入orgQualifData")
	public R<IPage<OrgQualifDataVO>> page(OrgQualifDataVO orgQualifData, Query query) {
		IPage<OrgQualifDataVO> pages = orgQualifDataService.selectOrgQualifDataPage(Condition.getPage(query), orgQualifData);
		return R.data(pages);
	}

	/**
	 * 新增 机构资质材料
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入orgQualifData")
	public R save(@Valid @RequestBody OrgQualifData orgQualifData) {
		return R.status(orgQualifDataService.save(orgQualifData));
	}

	/**
	 * 修改 机构资质材料
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入orgQualifData")
	public R update(@Valid @RequestBody OrgQualifData orgQualifData) {
		return R.status(orgQualifDataService.updateById(orgQualifData));
	}

	/**
	 * 新增或修改 机构资质材料
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入orgQualifData")
	public R submit(@Valid @RequestBody OrgQualifData orgQualifData) {
		return R.status(orgQualifDataService.saveOrUpdate(orgQualifData));
	}


	/**
	 * 删除 机构资质材料
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(orgQualifDataService.removeByIds(Func.toLongList(ids)));
	}


}
