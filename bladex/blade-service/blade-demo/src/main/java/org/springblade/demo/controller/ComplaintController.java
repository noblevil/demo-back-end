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
import com.alibaba.fastjson.JSONObject;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.demo.annotation.JwtIgnore;
import org.springblade.demo.entity.Complaint;
import org.springblade.demo.entity.OrgInfo;
import org.springblade.demo.service.IComplaintService;
import org.springblade.demo.service.IOrgInfoService;
import org.springblade.demo.vo.ComplaintVO;
import org.springframework.cloud.client.ConditionalOnDiscoveryEnabled;
import org.springframework.web.bind.annotation.*;
import org.xnio.channels.SuspendableAcceptChannel;

import javax.validation.Valid;
import javax.xml.bind.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * 校外投诉  控制器
 *
 * @author BladeX
 * @since 2020-07-15
 */
@RestController
@AllArgsConstructor
@RequestMapping("/complaint")
@Api(value = "校外投诉 ", tags = "校外投诉 接口")
public class ComplaintController extends BladeController {

	private IComplaintService complaintService;
	private IOrgInfoService orgInfoService;

	/**
	 * cyf:7.15
	 * 根据机构id获取机构名称
	 * http://localhost:9101/complaint/getOrgNameByOrgId?orgId=1
	 */
	@JwtIgnore
	@GetMapping("/getOrgNameByOrgId")
	@ApiOperation(value = "根据机构id获取机构名称", notes = "orgId")
	public String getOrgNameByOrgId(int orgId) {
		OrgInfo orgInfoCondition=new OrgInfo();
		orgInfoCondition.setOrgId(orgId);
		String orgName=orgInfoService.getOne(Condition.getQueryWrapper(orgInfoCondition)).getOrgName();
		return orgName;
	}

	/**
	 * cyf:7.16
	 * 根据机构名称获取机构id
	 * http://localhost:9101/complaint/getOrgIdByOrgName?orgName=小神童
	 */
	@JwtIgnore
	@GetMapping("/getOrgIdByOrgName")
	@ApiOperation(value = "根据机构id获取机构名称", notes = "orgName")
	public int getOrgIdByOrgName(String orgName) {
		OrgInfo orgInfoCondition=new OrgInfo();
		orgInfoCondition.setOrgName(orgName);
		return orgInfoService.getOne(Condition.getQueryWrapper(orgInfoCondition)).getOrgId();
	}


	/**
	 * cyf:7.15
	 * 获取所有投诉信息列表
	 * http://localhost:9101/complaint/getComplaintList
	 */
	@JwtIgnore
	@GetMapping("/getComplaintList")
	@ApiOperation(value = "获取所有投诉信息列表", notes = "无需参数")
//	public R<List<Complaint>> getComplaintList() {
//		List<Complaint> list = complaintService.list();
//		return R.data(list);
//	}
	public R<List<JSONObject>> getComplaintList() {
		List<Complaint> list = complaintService.list();
		List<JSONObject> jsonObjectList=new ArrayList<>();
		for(int i=0;i<list.size();++i){
			JSONObject jsonObject=new JSONObject();
			String orgName=getOrgNameByOrgId(list.get(i).getOrgId());
			jsonObject.put("orgName",orgName);
			jsonObject.put("orgId",list.get(i).getOrgId());
			jsonObject.put("complaintId",list.get(i).getComplaintId());
			jsonObject.put("title",list.get(i).getTitle());
			jsonObject.put("content",list.get(i).getContent());
			jsonObject.put("suggest",list.get(i).getSuggest());
			jsonObjectList.add(jsonObject);
		}
		return R.data(jsonObjectList);
	}

	/**
	 * cyf:7.15
	 * 获取所有投诉信息列表，并获取机构名称（分页）
	 */
	@JwtIgnore
	@GetMapping("/getComplaintListWithPage")
	@ApiOperation(value = "获取所有投诉信息列表（分页）", notes = "传入页号current和页面长度size")
//	public R<IPage<Complaint>> getComplaintList(Query query) {
//		IPage<Complaint> list = complaintService.page(Condition.getPage(query));
//		return R.data(list);
//	}
	public R<List<JSONObject>> getComplaintList(Query query) {
		IPage<Complaint> list = complaintService.page(Condition.getPage(query));
		//类型转换
		List<Complaint> complaints= list.getRecords();
		List<JSONObject> jsonObjectList=new ArrayList<>();
		for(int i=0;i<list.getSize();++i){
			Complaint complaintDetail=complaints.get(i);
			JSONObject obj=new JSONObject();
			obj.put("orgId",complaintDetail.getOrgId());
			obj.put("orgNmae",getOrgNameByOrgId(complaintDetail.getOrgId()));
			obj.put("complaintId", complaintDetail.getComplaintId());
			obj.put("title", complaintDetail.getTitle());
			obj.put("content", complaintDetail.getContent());
			obj.put("suggest", complaintDetail.getSuggest());
			jsonObjectList.add(obj);
		}
		return R.data(jsonObjectList);
	}


	/**
	 * cyf:7.15
	 * 新增投诉信息（以RequestBody方式传入）
	 * http://localhost:9101/complaint/addComplaint
	 * body内容：{
	 *     "orgId": 1,
	 *     "title":"标题10",
	 *     "content":"上课太无聊，老师讲课不生动",
	 *     "suggest":"要求调整上课老师"
	 * }
	 */
	@JwtIgnore
	@PostMapping("/addComplaint")
	@ApiOperation(value = "新增新增投诉信息", notes = "以RequestBody方式传入投诉信息complaint")
//	public R addComplaint(@Valid @RequestBody Complaint complaint) {
//
//		return R.status(complaintService.save(complaint));
//	}
	public R addComplaint( @Valid @RequestBody String parameters) {  //参入参数由后台进行解析
		JSONObject jsonObject =JSONObject.parseObject(parameters);
		String orgName=jsonObject.getString("orgName");
		String title=jsonObject.getString("title");
		String content=jsonObject.getString("content");
		String suggest=jsonObject.getString("suggest");

		int orgId;
		try{
			orgId=getOrgIdByOrgName(orgName);
		}catch (Exception e){
//			System.out.println("获取机构id异常！");
			System.out.println("参数解析："+orgName+","+title+","+content+","+suggest);
			e.printStackTrace();
			return R.fail("获取机构id异常");
		}
		System.out.println("参数解析："+orgId+","+orgName+","+title+","+content+","+suggest);
		Complaint complaint=new Complaint();
		complaint.setOrgId(orgId);
		complaint.setTitle(title);
		complaint.setContent(content);
		complaint.setSuggest(suggest);
		return R.status(complaintService.save(complaint));
	}

	/**
	 * cyf:7.15
	 * 根据机构名获取机构的投诉标题列表（暂不考虑区域）
	 * http://localhost:9101/complaint/getOrgComplaintTitleList?orgName=学小易
	 */
	@JwtIgnore
	@GetMapping("/getOrgComplaintTitleList")
	@ApiOperation(value = "根据机构名获取机构的投诉标题列表", notes = "机构名称")
	public R<List<JSONObject>> getOrgComplaintTitleList(String orgName) {
		//根据机构名获取机构id
		OrgInfo orgInfoCondition = new OrgInfo();
		orgInfoCondition.setOrgName(orgName);
		OrgInfo orgInfo = orgInfoService.getOne(Condition.getQueryWrapper(orgInfoCondition));
		int orgId = orgInfo.getOrgId();
		//根据机构	id查询机构投诉列表
		Complaint complaintCondition = new Complaint();
		complaintCondition.setOrgId(orgId);
		List<Complaint> list = complaintService.list(Condition.getQueryWrapper(complaintCondition));
		//筛选出需要的字段
		List<JSONObject> objList = new ArrayList<>();  //注意导入的包是：import com.alibaba.fastjson.JSONObject;，而不是：json.JSONObject，后者返回数据为空
		for (int i = 0; i < list.size(); ++i) {
			JSONObject obj = new JSONObject();
			obj.put("orgId", orgId);
			obj.put("orgName", orgName);
			obj.put("complaintId", list.get(i).getComplaintId());
			obj.put("title", list.get(i).getTitle());
			objList.add(obj);
			System.out.println(obj);

		}
		System.out.println(objList);
		return R.data(objList);
	}

	/**
	 * cyf:7.15
	 * 根据机构名获取机构的投诉信息列表（暂不考虑区域）
	 * http://localhost:9101/complaint/getOrgComplaintList?orgName=学小易
	 */
	@JwtIgnore
	@GetMapping("/getOrgComplaintList")
	@ApiOperation(value = "根据机构名获取机构的投诉信息列表", notes = "机构名称")
	public R<List<Complaint>> getOrgComplaintList(String orgName) {
		//根据机构名获取机构id
		OrgInfo orgInfoCondition = new OrgInfo();
		orgInfoCondition.setOrgName(orgName);
		OrgInfo orgInfo = orgInfoService.getOne(Condition.getQueryWrapper(orgInfoCondition));
		int orgId = orgInfo.getOrgId();
		//根据机构id查询机构投诉列表
		Complaint complaintCondition = new Complaint();
		complaintCondition.setOrgId(orgId);
		List<Complaint> list = complaintService.list(Condition.getQueryWrapper(complaintCondition));
		return R.data(list);
	}


	/**
	 * cyf:7.15
	 * 根据投诉id获取投诉信息详情
	 */
	@JwtIgnore
	@GetMapping("/getComplaintDetailById")
	@ApiOperation(value = "根据投诉id获取投诉信息详情", notes = "传入参数complaintId")
//	public R<Complaint> getComplaintDetailById(int complaintId) {
//		Complaint complaintCondition = new Complaint();
//		complaintCondition.setComplaintId(complaintId);
//		Complaint complaintDetail = complaintService.getOne(Condition.getQueryWrapper(complaintCondition));
//		return R.data(complaintDetail);
//	}
	public R<JSONObject> getComplaintDetailById(int complaintId) {
		JSONObject jsonObject=new JSONObject();
		Complaint complaintCondition = new Complaint();
		complaintCondition.setComplaintId(complaintId);
		Complaint complaintDetail = complaintService.getOne(Condition.getQueryWrapper(complaintCondition));
		jsonObject.put("orgId",complaintDetail.getOrgId());
		jsonObject.put("orgNmae",getOrgNameByOrgId(complaintDetail.getOrgId()));
		jsonObject.put("complaintId", complaintDetail.getComplaintId());
		jsonObject.put("title", complaintDetail.getTitle());
		jsonObject.put("content", complaintDetail.getContent());
		jsonObject.put("suggest", complaintDetail.getSuggest());

		return R.data(jsonObject);
	}

	/**
	 * cyf:7.15
	 * 根据机构名获取机构的投诉标题列表（考虑机构所在地）
	 * http://localhost:9101/complaint/getOrgComplaintTitleList2?orgName=学小易
	 */
	@JwtIgnore
	@GetMapping("/getOrgComplaintTitleList2")
	@ApiOperation(value = "根据机构名获取机构的投诉标题列表", notes = "机构名称")
	public R<List<JSONObject>> getOrgComplaintTitleList2(String orgLocation,String orgName) {
		//根据机构名获取机构id
		OrgInfo orgInfoCondition = new OrgInfo();
		orgInfoCondition.setOrgLocation(orgLocation);
		orgInfoCondition.setOrgName(orgName);
		OrgInfo orgInfo = orgInfoService.getOne(Condition.getQueryWrapper(orgInfoCondition));
		int orgId = orgInfo.getOrgId();
		//根据机构	id查询机构投诉列表
		Complaint complaintCondition = new Complaint();
		complaintCondition.setOrgId(orgId);
		List<Complaint> list = complaintService.list(Condition.getQueryWrapper(complaintCondition));
		//筛选出需要的字段
		List<JSONObject> objList = new ArrayList<>();  //注意导入的包是：import com.alibaba.fastjson.JSONObject;，而不是：json.JSONObject，后者返回数据为空
		for (int i = 0; i < list.size(); ++i) {
			JSONObject obj = new JSONObject();
			obj.put("orgId", orgId);
			obj.put("orgName", orgName);
			obj.put("complaintId", list.get(i).getComplaintId());
			obj.put("title", list.get(i).getTitle());
			objList.add(obj);
			System.out.println(obj);

		}
		System.out.println(objList);
		return R.data(objList);
	}

	/**
	 * cyf:7.15
	 * 根据机构名获取机构的投诉信息列表（考虑机构所在地）
	 * http://localhost:9101/complaint/getOrgComplaintList2?orgName=学小易
	 */
	@JwtIgnore
	@GetMapping("/getOrgComplaintList2")
	@ApiOperation(value = "根据机构名获取机构的投诉信息列表", notes = "机构名称")
	public R<List<Complaint>> getOrgComplaintList2(String orgLocation,String orgName) {
		//根据机构名获取机构id
		OrgInfo orgInfoCondition = new OrgInfo();
		orgInfoCondition.setOrgLocation(orgLocation);
		orgInfoCondition.setOrgName(orgName);
		OrgInfo orgInfo = orgInfoService.getOne(Condition.getQueryWrapper(orgInfoCondition));
		int orgId = orgInfo.getOrgId();
		//根据机构id查询机构投诉列表
		Complaint complaintCondition = new Complaint();
		complaintCondition.setOrgId(orgId);
		List<Complaint> list = complaintService.list(Condition.getQueryWrapper(complaintCondition));
		return R.data(list);
	}

	//===========================以下为自动生成的接口==============================

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入complaint")
	public R<Complaint> detail(Complaint complaint) {
		Complaint detail = complaintService.getOne(Condition.getQueryWrapper(complaint));
		return R.data(detail);
	}

	/**
	 * 分页 校外投诉
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入complaint")
	public R<IPage<Complaint>> list(Complaint complaint, Query query) {
		IPage<Complaint> pages = complaintService.page(Condition.getPage(query), Condition.getQueryWrapper(complaint));
		return R.data(pages);
	}

	/**
	 * 自定义分页 校外投诉
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入complaint")
	public R<IPage<ComplaintVO>> page(ComplaintVO complaint, Query query) {
		IPage<ComplaintVO> pages = complaintService.selectComplaintPage(Condition.getPage(query), complaint);
		return R.data(pages);
	}

	/**
	 * 新增 校外投诉
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入complaint")
	public R save(@Valid @RequestBody Complaint complaint) {
		return R.status(complaintService.save(complaint));
	}

	/**
	 * 修改 校外投诉
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入complaint")
	public R update(@Valid @RequestBody Complaint complaint) {
		return R.status(complaintService.updateById(complaint));
	}

	/**
	 * 新增或修改 校外投诉
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入complaint")
	public R submit(@Valid @RequestBody Complaint complaint) {
		return R.status(complaintService.saveOrUpdate(complaint));
	}


	/**
	 * 删除 校外投诉
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(complaintService.removeByIds(Func.toLongList(ids)));
	}


}
