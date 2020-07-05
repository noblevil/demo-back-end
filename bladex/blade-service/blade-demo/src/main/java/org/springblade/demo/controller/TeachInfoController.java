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
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.demo.entity.RelOrgTeach;
import org.springblade.demo.service.IRelOrgTeachService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.demo.entity.TeachInfo;
import org.springblade.demo.vo.TeachInfoVO;
import org.springblade.demo.service.ITeachInfoService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师信息  控制器
 *
 * @author BladeX
 * @since 2020-07-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/teachinfo")
@Api(value = "教师信息 ", tags = "教师信息 接口")
public class TeachInfoController extends BladeController {

	private ITeachInfoService teachInfoService;
	private IRelOrgTeachService relOrgTeachService;

	/**
	 * 根据机构id获取所属的教师列表
	 * 例如：http://localhost:9101/teachinfo/getTeachListById?orgId=1
	 */
	@GetMapping("/getTeachListById")  //【API-4】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据机构id获取所属的教师列表", notes = "传入机构id")
	public  R<List<TeachInfo>> getTeachListById(Integer orgId) {
		RelOrgTeach relOrgTeach=new RelOrgTeach();
		relOrgTeach.setOrgId(orgId);
		List<RelOrgTeach> teachIdlist = relOrgTeachService.list(Condition.getQueryWrapper(relOrgTeach));
		List<TeachInfo> teachInfoList = new ArrayList<TeachInfo>();
		for(int i=0;i<teachIdlist.size();++i)
		{
			RelOrgTeach teacher=teachIdlist.get(i);  //获取教师id
			int id=teacher.getTeachId();  //获取教师id
			TeachInfo teachId = new TeachInfo();
			teachId.setTeachId(id);
			TeachInfo detail = teachInfoService.getOne(Condition.getQueryWrapper(teachId));  //根据教师id获取教师信息详情
			assert false;
			teachInfoList.add(detail);  //将查询到的教师信息添加到列表
		}
		assert false;
		return R.data(teachInfoList);
	}


	/**
	 * 根据机构id获取机构所属教师关系rel_org_teach（测试）【为上面的api服务】
	 * 例如：http://localhost:9101/teachinfo/getRelTeachById?orgId=1
	 */
	@GetMapping("/getRelTeachById")  //【API-测试】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据机构id获取机构所属教师关系", notes = "传入机构id")
	public  R<List<RelOrgTeach>> queryOrgList(Integer orgId) {
		RelOrgTeach relOrgTeach=new RelOrgTeach() ;
		relOrgTeach.setOrgId(orgId);
		List<RelOrgTeach> list = relOrgTeachService.list(Condition.getQueryWrapper(relOrgTeach));
		return R.data(list);
	}




	/**
	 * 筛选：条件查询  获取教师信息详情（根据声明实体的名）
	 * 例子：http://localhost:9101/teachinfo/queryTeachList?orgId=1&professionalTitle=讲师
	 * 参数为空：返回全部
	 */
	@GetMapping("/queryTeachList")  //【API-7】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件查询：获取教师信息详情", notes = "传入orgId+教师表任意字段的值（除教师id）")
	public  R<List<TeachInfo>> queryOrgList(Integer orgId,TeachInfo teachInfo) {
		List<TeachInfo> resultList=new ArrayList<>();
		RelOrgTeach relOrgTeach=new RelOrgTeach() ;
		relOrgTeach.setOrgId(orgId);
		List<RelOrgTeach> teachIds=relOrgTeachService.list(Condition.getQueryWrapper(relOrgTeach));
		for(int i=0;i<teachIds.size();++i)
		{
			int id=teachIds.get(i).getTeachId();
			TeachInfo tInfo = teachInfo;    //根据传入参数设置教师字段的查询条件
			tInfo.setTeachId(id);    //根据教师id进行筛选
			TeachInfo teachDetail=teachInfoService.getOne(Condition.getQueryWrapper(tInfo));
			if(teachDetail!=null) resultList.add(teachDetail);
		}
		return R.data(resultList);
	}

	//===========================以下为自动生成的接口==============================
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入teachInfo")
	public R<TeachInfo> detail(TeachInfo teachInfo) {
		TeachInfo detail = teachInfoService.getOne(Condition.getQueryWrapper(teachInfo));
		return R.data(detail);
	}

	/**
	 * 分页 教师信息
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入teachInfo")
	public R<IPage<TeachInfo>> list(TeachInfo teachInfo, Query query) {
		IPage<TeachInfo> pages = teachInfoService.page(Condition.getPage(query), Condition.getQueryWrapper(teachInfo));
		return R.data(pages);
	}

	/**
	 * 自定义分页 教师信息
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入teachInfo")
	public R<IPage<TeachInfoVO>> page(TeachInfoVO teachInfo, Query query) {
		IPage<TeachInfoVO> pages = teachInfoService.selectTeachInfoPage(Condition.getPage(query), teachInfo);
		return R.data(pages);
	}

	/**
	 * 新增 教师信息
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入teachInfo")
	public R save(@Valid @RequestBody TeachInfo teachInfo) {
		return R.status(teachInfoService.save(teachInfo));
	}

	/**
	 * 修改 教师信息
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入teachInfo")
	public R update(@Valid @RequestBody TeachInfo teachInfo) {
		return R.status(teachInfoService.updateById(teachInfo));
	}

	/**
	 * 新增或修改 教师信息
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入teachInfo")
	public R submit(@Valid @RequestBody TeachInfo teachInfo) {
		return R.status(teachInfoService.saveOrUpdate(teachInfo));
	}

	/**
	 * 删除 教师信息
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(teachInfoService.removeByIds(Func.toLongList(ids)));
	}

}
