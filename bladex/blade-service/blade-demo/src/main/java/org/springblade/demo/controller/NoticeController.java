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
import org.aspectj.weaver.ast.Not;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.demo.annotation.JwtIgnore;
import org.springblade.demo.entity.Notice;
import org.springblade.demo.service.INoticeService;
import org.springblade.demo.vo.NoticeVO;
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
@RequestMapping("/notice")
@Api(value = "", tags = "接口")
public class NoticeController extends BladeController {

	private INoticeService noticeService;

	/**
	 * cyf:7.18
	 * 获取所有通知列表（通知id和通知标题）
	 * http://localhost:9101/notice/getAllNoticeList
	 */
	@JwtIgnore
	@GetMapping("/getAllNoticeList")
	@ApiOperation(value = "获取所有通知列表", notes = "无需参数")
	public R<List<JSONObject>> getAllNoticeList() {
		List<Notice> list=noticeService.list();
		List<JSONObject> result=new ArrayList<>();
		for(int i=0;i<list.size();++i){
			JSONObject obj=new JSONObject();
			obj.put("noticeId",list.get(i).getNoticeId());
			obj.put("title",list.get(i).getTitle());
			result.add(obj);
		}
		return R.data(result);
	}


	/**
	 * cyf:7.18
	 * 通过noticeId获取通知详情
	 * http://localhost:9101/notice/getNoticeDetailById?noticeId=1
	 */
	@JwtIgnore
	@GetMapping("/getNoticeDetailById")
	@ApiOperation(value = "通过noticeId获取通知详情", notes = "传入noticeId")
	public R<Notice> getNoticeDetailById(int noticeId) {
		Notice noticeCondition=new Notice();
		noticeCondition.setNoticeId(noticeId);
		Notice detail = noticeService.getOne(Condition.getQueryWrapper(noticeCondition));
		return R.data(detail);
	}

	//===========================以下为自动生成的接口==============================
	/**
	 * 详情
	 */
	@JwtIgnore
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入notice")
	public R<Notice> detail(Notice notice) {
		Notice detail = noticeService.getOne(Condition.getQueryWrapper(notice));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入notice")
	public R<IPage<Notice>> list(Notice notice, Query query) {
		IPage<Notice> pages = noticeService.page(Condition.getPage(query), Condition.getQueryWrapper(notice));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入notice")
	public R<IPage<NoticeVO>> page(NoticeVO notice, Query query) {
		IPage<NoticeVO> pages = noticeService.selectNoticePage(Condition.getPage(query), notice);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入notice")
	public R save(@Valid @RequestBody Notice notice) {
		return R.status(noticeService.save(notice));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入notice")
	public R update(@Valid @RequestBody Notice notice) {
		return R.status(noticeService.updateById(notice));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入notice")
	public R submit(@Valid @RequestBody Notice notice) {
		return R.status(noticeService.saveOrUpdate(notice));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(noticeService.removeByIds(Func.toLongList(ids)));
	}


}
