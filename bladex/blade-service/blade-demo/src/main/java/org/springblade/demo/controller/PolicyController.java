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

import com.alibaba.fastjson.JSONObject;
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
import org.springblade.demo.annotation.JwtIgnore;
import org.springblade.demo.entity.Policy;
import org.springblade.demo.service.IPolicyService;
import org.springblade.demo.vo.PolicyVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2020-07-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/policy")
@Api(value = "", tags = "接口")
public class PolicyController extends BladeController {

	private IPolicyService policyService;


	/**
	 * cyf:7.18
	 * 获取所有政策列表（政策id和政策标题）
	 * http://localhost:9101/policy/getAllPolicyList
	 */
	@JwtIgnore
	@GetMapping("/getAllPolicyList")
	@ApiOperation(value = "获取所有政策列表", notes = "无需参数")
	public R<List<JSONObject>> getAllNoticeList() {
		List<Policy> list=policyService.list();
		List<JSONObject> result=new ArrayList<>();
		for(int i=0;i<list.size();++i){
			JSONObject obj=new JSONObject();
			obj.put("policyId",list.get(i).getPolicyId());
			obj.put("title",list.get(i).getTitle());
			result.add(obj);
		}
		return R.data(result);
	}


	/**
	 * cyf:7.18
	 * 通过policyId获取政策详情
	 * http://localhost:9101/policy/getPolicyDetailById?policyId=1
	 */
	@JwtIgnore
	@GetMapping("/getPolicyDetailById")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入policyId")
	public R<Policy> getNoticeDetailById(int policyId) {
		Policy policyCondition=new Policy();
		policyCondition.setPolicyId(policyId);
		Policy detail = policyService.getOne(Condition.getQueryWrapper(policyCondition));
		return R.data(detail);
	}

	//===========================以下为自动生成的接口==============================
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入policy")
	public R<Policy> detail(Policy policy) {
		Policy detail = policyService.getOne(Condition.getQueryWrapper(policy));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入policy")
	public R<IPage<Policy>> list(Policy policy, Query query) {
		IPage<Policy> pages = policyService.page(Condition.getPage(query), Condition.getQueryWrapper(policy));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入policy")
	public R<IPage<PolicyVO>> page(PolicyVO policy, Query query) {
		IPage<PolicyVO> pages = policyService.selectPolicyPage(Condition.getPage(query), policy);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入policy")
	public R save(@Valid @RequestBody Policy policy) {
		return R.status(policyService.save(policy));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入policy")
	public R update(@Valid @RequestBody Policy policy) {
		return R.status(policyService.updateById(policy));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入policy")
	public R submit(@Valid @RequestBody Policy policy) {
		return R.status(policyService.saveOrUpdate(policy));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(policyService.removeByIds(Func.toLongList(ids)));
	}


}
