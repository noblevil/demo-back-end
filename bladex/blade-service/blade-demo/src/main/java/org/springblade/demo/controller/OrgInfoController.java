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

import com.alibaba.fastjson.JSON;
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
import org.springblade.demo.annotation.Role;
import org.springblade.demo.common.RoleCode;
import org.springblade.demo.entity.*;
import org.springblade.demo.service.*;
import org.springblade.demo.vo.OrgInfoVO;
import org.springframework.web.bind.annotation.*;
import sun.text.resources.no.CollationData_no;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

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
	private ICourseService courseService;
	private ITeachAccountService teachAccountService;
	private IRelOrgTeachService relOrgTeachService;
	private IOrgAccountService orgAccountService;
	private IOrgQualifDataService orgQualifDataService;
	/**
	 * 根据机构id获取机构信息详情
	 * 例如：http://localhost:9101/orginfo/getOrgDetailById?orgId=2
	 */
	@JwtIgnore
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
	@JwtIgnore
	@GetMapping("/getAllOrgList")  //【API-2】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取所有机构信息详情")
	public R<List<OrgInfo>> getAllOrgList( ){
		List<OrgInfo> list = orgInfoService.list();
		return R.data(list);
	}

	/**
	 * 分页（第几页，每页多少条数据） 查询所有机构信息
	 * 例如：http://localhost:9101/orginfo/getAllOrgListByPage?current=2&size=1（第二页，每页1条信息）
	 */
	@JwtIgnore
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
	@JwtIgnore
	@GetMapping("/queryOrgList")  //【API-3】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件查询：获取机构信息详情", notes = "传入任意字段的值")
	public  R<List<OrgInfo>> queryOrgList(String trainSubject, OrgInfo orgInfo) {

		// 实现拿来符合出来 科目 之外的机构信息
		List<OrgInfo> list = orgInfoService.list(Condition.getQueryWrapper(orgInfo));

		// 如果科目非空 则要处理
		if(!trainSubject.equals("")){

			List<OrgInfo> returnList = new ArrayList<OrgInfo>();
			for (OrgInfo org: list) {
				// 处理每一个机构
				int orgId = org.getOrgId();
				Course course = new Course();
				course.setOrgId(orgId);
				//得到机构所有的对应课程
				List<Course> courseList = courseService.list(Condition.getQueryWrapper(course));
				// 保存 科目 列表
				List<String> trainSubjects = new ArrayList<String>();
				// 得到没有去重的科目列表
				for (Course obj : courseList) {
					trainSubjects.add(obj.getCourseSubject());
				}
				// 如果列表包含 这个科目 就添加到 return list
				if(trainSubjects.contains(trainSubject)){
					returnList.add(org);
				}

			}

			list = returnList;

		}
		return R.data(list);
	}

	/**
	 * 获取教师所属的所有机构的信息——7.13
	 * http://localhost:9101/orginfo/getOrgByTeachAccount?teachAccount=110
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.TEACH})
	@GetMapping("/getOrgByTeachAccount")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据教师账户名获取教师所属的所有机构信息", notes = "传入教师账户名")
	public  R<List<OrgInfo>> getOrgByTeachAccount(String teachAccount) {
		TeachAccount teachAccountCondition=new TeachAccount();
		teachAccountCondition.setTeachAccount(teachAccount);
		TeachAccount teachAccountInfo=teachAccountService.getOne(Condition.getQueryWrapper(teachAccountCondition));
		//获得教师账户名
		int teachId;
		try{
			teachId=teachAccountInfo.getTeachId();
		}catch (Exception e){
			return R.fail("此教师账户名不存在！");
		}
		//查询rel_org_teach获取教师所属的全部机构id
		RelOrgTeach relOrgTeachCondition=new RelOrgTeach();
		relOrgTeachCondition.setTeachId(teachId);
		List<RelOrgTeach> relOrgTeachesList=relOrgTeachService.list(Condition.getQueryWrapper(relOrgTeachCondition));
		//遍历获取机构id，得到机构详情列表
		List<OrgInfo> orgInfoList=new ArrayList<>();
		for(int i=0;i<relOrgTeachesList.size();++i){
			OrgInfo orgInfoCondition=new OrgInfo();
			orgInfoCondition.setOrgId(relOrgTeachesList.get(i).getOrgId());
			OrgInfo orgInfoDetail=orgInfoService.getOne(Condition.getQueryWrapper(orgInfoCondition));
			orgInfoList.add(orgInfoDetail);
		}
		return R.data(orgInfoList);
	}


	/**
	 * ybj
	 * http://localhost:9101/orginfo/searchOrg?condition=机构名或机构id
	 * 筛选：根据机构名搜索机构——7.12
	 *
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.TEACH})
	@GetMapping("/searchOrg")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件查询：获取机构所属的课程名", notes = "传入机构名")
	public  R<List<OrgInfo>> queryOrgInfo(String condition) {
		//Course courseCondition=new Course();
		OrgInfo orgInfoCondition = new OrgInfo();
		Pattern pattern = Pattern.compile("[0-9]*");
		boolean type=pattern.matcher(condition).matches();  //用正则表达式判断传入的参数类型，若为数字，返回true
		if(type) {  //数字类型
			Integer orgId = Integer.valueOf(condition);
			System.out.println("传入机构id=" + orgId);
			orgInfoCondition.setOrgId(orgId);  //设置筛选条件
		}
		else {
			//根据机构名获取机构id
			OrgInfo orgCondition=new OrgInfo();  //筛选条件
			orgCondition.setOrgName(condition);
			OrgInfo orgInfo=orgInfoService.getOne(Condition.getQueryWrapper(orgCondition));
			try{
				Integer orgId=orgInfo.getOrgId();
				orgInfoCondition.setOrgId(orgId);
			}
			catch (Exception e){
				return R.fail("机构账户不存在");
//				return R.data(200,null,"机构账户不存在");
			}
		}
		//获取机构所属的所有课程信息
		List<OrgInfo> list = orgInfoService.list(Condition.getQueryWrapper(orgInfoCondition));
		return R.data(list);
	}


	/**
	 * ybj	7.13
	 * 机构
	 * 根据orgAccount获取机构信息、机构账户、机构资质材料信息
	 * Request Example：http://localhost:9101/orginfo/getOrgInfo?orgAccount=1101234561
	 * @param orgAccount
	 * @return
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.ORG})
	@ApiOperationSupport(order = 1)
	@ApiOperation(value="获取机构信息、机构账户、机构资质材料信息",notes="传入机构账户orgAccount")
	@GetMapping("/getOrgInfo")
	public R<JSONObject> getOrgInfo(String orgAccount){
		//根据机构账户名得到机构id
		OrgAccount orgAccountCondition = new OrgAccount();
		orgAccountCondition.setOrgAccount(orgAccount);
		int orgId;
		try{
			orgId = orgAccountService.getOne(Condition.getQueryWrapper(orgAccountCondition)).getOrgId();
		}catch (Exception e){
			return R.fail("机构不存在!");
		}
		JSONObject obj = new JSONObject();
		//根据orgId获取三个表的信息，存入JSONObject对象
		OrgInfo orgInfo = new OrgInfo();
		orgInfo.setOrgId(orgId);
		obj.put("orgInfo", orgInfoService.getOne(Condition.getQueryWrapper(orgInfo)));

		OrgAccount orgAcc = new OrgAccount();
		orgAcc.setOrgId(orgId);
		obj.put("orgAccount", orgAccountService.getOne(Condition.getQueryWrapper(orgAcc)));

		OrgQualifData orgQualifData = new OrgQualifData();
		orgQualifData.setOrgId(orgId);
		obj.put("orgQualifData", orgQualifDataService.getOne(Condition.getQueryWrapper(orgQualifData)));
		return R.data(obj);
	}


	/**
	 * ybj	7.13
	 * 机构
	 *根据orgAccount对机构账户相关信息(orgPhone,passwd)进行修改，更新到数据库机构账户表中
	 * Request Example：http://localhost:9101/orginfo/UpdateOrgAccountInfo?+(Body)
	 * @param obj
	 * @return
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.ORG})
	@ApiOperationSupport(order = 2)
	@ApiOperation(value="",notes="")
	@PostMapping("/UpdateOrgAccountInfo")
	public R UpdateOrgAccountInfo(@Valid @RequestBody JSONObject obj){
		System.out.println("=====传入参数：obj="+obj);
		//根据orgAccount得到orgId
//		System.out.println(obj);
		String orgAccount = obj.getString("orgAccount");
		OrgAccount orgAcc = new OrgAccount();
		orgAcc.setOrgAccount(orgAccount);
		try {
			int orgId = orgAccountService.getOne(Condition.getQueryWrapper(orgAcc)).getOrgId();
		}catch (Exception e){
			return R.fail("机构不存在!");
		}
		//获取机构账户完整信息并修改
		OrgAccount orgAccountCondition = orgAccountService.getOne(Condition.getQueryWrapper(orgAcc));
		orgAccountCondition.setOrgPhone(obj.getString("orgPhone"));
		orgAccountCondition.setPasswd(obj.getString("passwd"));
		return R.data(orgAccountService.updateById(orgAccountCondition));
	}


	/**
	 * ybj 7.13
	 * 机构
	 * 根据orgAccount修改机构信息（linkmanOne,linkmanOnePhone,linkmanTwo,linkmanTwoPhone），更新数据到机构信息表
	 * Request Example：http://localhost:9101/orginfo/UpdateOrgInfo?+(Body)
	 * @param obj
	 * @return
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.ORG})
	@ApiOperationSupport(order = 3)
	@ApiOperation(value="",notes="")
	@PostMapping("/UpdateOrgInfo")
	public R UpdateOrgInfo(@Valid @RequestBody JSONObject obj){
		System.out.println("=====传入参数：obj="+obj);
		String orgAccount = obj.getString("orgAccount");
		OrgAccount orgAccountCondition = new OrgAccount();
		orgAccountCondition.setOrgAccount(orgAccount);
		//orgId
		int orgId;
		try{
			orgId = orgAccountService.getOne(Condition.getQueryWrapper(orgAccountCondition)).getOrgId();
		}catch (Exception e){
			return R.fail("机构不存在!");
		}
		//根据orgId获取机构信息
		OrgInfo orgInfoCondition = new OrgInfo();
		orgInfoCondition.setOrgId(orgId);
		OrgInfo orgInfo = new OrgInfo();
		orgInfo = orgInfoService.getOne(Condition.getQueryWrapper(orgInfoCondition));
		orgInfo.setLinkmanOne(obj.getString("linkmanOne"));
		orgInfo.setLinkmanOnePhone(obj.getString("linkmanOnePhone"));
		orgInfo.setLinkmanTwo(obj.getString("linkmanTwo"));
		orgInfo.setLinkmanTwoPhone(obj.getString("linkmanTwoPhone"));
		return R.status(orgInfoService.updateById(orgInfo));
	}

	/**
	 * ybj 7.18
	 * http://localhost:9101/orginfo/insertOrgInfo?  (Requestbody请求体传入字段信息)
	 * 注册机构信息，包括orgAccount,orgInfo,orgQualifData信息
	 * @param Params
	 * @return
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.ORG})
	@ApiOperationSupport(order = 4)
	@ApiOperation(value="注册机构信息",notes="传入orgAccount,orgInfo,orgQualifData信息")
	@PostMapping("/insertOrgInfo")
	public R insertOrgInfo(@Valid @RequestBody String Params) {
		System.out.println("请求体内容\n" + Params);
		JSONObject obj = JSON.parseObject(Params);

		//嵌套JSONObject分割处理
		JSONObject ifobj = (JSONObject) obj.get("orgInfo");
		JSONObject acobj = (JSONObject) obj.get("orgAccountInfo");
		JSONObject qaobj = (JSONObject) obj.get("orgQualifInfo");

		OrgInfo orginfo = new OrgInfo();
		OrgAccount orgAccount = new OrgAccount();
		OrgQualifData orgQualifData = new OrgQualifData();

		//JSONObject对象转实体类orginfo,orgAccount,orgQualifDate
		orginfo = (OrgInfo) ifobj.toJavaObject(ifobj, OrgInfo.class);
		orgAccount = (OrgAccount) acobj.toJavaObject(acobj, OrgAccount.class);
		orgQualifData = (OrgQualifData) qaobj.toJavaObject(qaobj, OrgQualifData.class);

		//首先新增机构账号
		orgAccountService.save(orgAccount);
		//获取新增机构id
		int orgId;
		orgId = orgAccount.getOrgId();
		//设置org_id
		orginfo.setOrgId(orgId);
		orgQualifData.setOrgId(orgId);
		//新增orginfo,orgQualifData记录
		return R.status(orgInfoService.save(orginfo) && orgQualifDataService.save(orgQualifData));
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
