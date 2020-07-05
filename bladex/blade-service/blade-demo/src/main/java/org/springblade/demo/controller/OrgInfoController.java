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
import org.springblade.demo.entity.OrgInfo;
import org.springblade.demo.service.IOrgInfoService;
import org.springblade.demo.vo.OrgInfoVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 机构信息  控制器
 *
 * @author BladeX
 * @since 2020-07-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orginfo")
@Api(value = "机构信息 ", tags = "机构信息 接口")
public class OrgInfoController extends BladeController {

	private IOrgInfoService orgInfoService;

	/**
	 * 根据机构id获取机构信息详情
	 * 例如：http://localhost:9101/orginfo/getOrgDetailById?id=2
	 */
	@GetMapping("/getOrgDetailById")  //【API-1】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据机构id获取机构信息详情", notes = "传入机构id")
	public R<OrgInfo> getOrgDetailById(Integer orgId) {
		OrgInfo org = new OrgInfo();
		org.setOrgId(orgId);
		OrgInfo detail = orgInfoService.getOne(Condition.getQueryWrapper(org));
		return R.data(detail);
	}

	/**
	 * 获取所有机构信息详情
	 * 例如：http://localhost:9101/orginfo/getAllOrgList
	 * @return
	 */
	@GetMapping("/getAllOrgList")  //【API-2】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取所有机构信息详情")
	public R<List<OrgInfo>> getAllOrgList( ){
		List<OrgInfo> list = orgInfoService.list();
		return R.data(list);
	}

	/**
	 * 分页（第几页，每页多少条数据） 查询所有机构信息，
	 * 例如：http://localhost:9101/orginfo/getAllOrgListByPage?current=2&size=1（第二页，每页1条信息）
	 */
	@GetMapping("/getAllOrgListByPage")    //【API-2-1】
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入页号current和每个页面返回数量size")
	public R<IPage<OrgInfo>> list(Query query) {
		IPage<OrgInfo> pages = orgInfoService.page(Condition.getPage(query));
		return R.data(pages);
	}

	/**
	 * 筛选：条件查询  获取机构信息详情（根据声明实体的名）
	 * 根据机构名称查询：http://localhost:9101/orginfo/queryOrgList?orgName=机构1
	 * 根据id查询：http://localhost:9101/orginfo/queryOrgList?orgId=2
	 * 多个条件查询：http://localhost:9101/orginfo/queryOrgList?orgId=2&orgName=机构2
	 * 参数为空：返回全部
	 */
	@GetMapping("/queryOrgList")  //【API-3】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件查询：获取机构信息详情", notes = "传入任意字段的值")
	public  R<List<OrgInfo>> queryOrgList(OrgInfo orgInfo) {
		List<OrgInfo> list = orgInfoService.list(Condition.getQueryWrapper(orgInfo));
		return R.data(list);
	}

	//===========================以下为自动生成的接口==============================

	/**
	 * 分页 机构信息
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入orgInfo")
	public R<IPage<OrgInfo>> list(OrgInfo orgInfo, Query query) {
		IPage<OrgInfo> pages = orgInfoService.page(Condition.getPage(query), Condition.getQueryWrapper(orgInfo));
		return R.data(pages);
	}

	/**
	 * 自定义分页 机构信息
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入orgInfo")
	public R<IPage<OrgInfoVO>> page(OrgInfoVO orgInfo, Query query) {
		IPage<OrgInfoVO> pages = orgInfoService.selectOrgInfoPage(Condition.getPage(query), orgInfo);
		return R.data(pages);
	}

	/**
	 * 新增 机构信息
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入orgInfo")
	public R save(@Valid @RequestBody OrgInfo orgInfo) {
		return R.status(orgInfoService.save(orgInfo));
	}

	/**
	 * 修改 机构信息
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入orgInfo")
	public R update(@Valid @RequestBody OrgInfo orgInfo) {
		return R.status(orgInfoService.updateById(orgInfo));
	}

	/**
	 * 新增或修改 机构信息
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入orgInfo")
	public R submit(@Valid @RequestBody OrgInfo orgInfo) {
		return R.status(orgInfoService.saveOrUpdate(orgInfo));
	}


	/**
	 * 删除 机构信息
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(orgInfoService.removeByIds(Func.toLongList(ids)));
	}


}
