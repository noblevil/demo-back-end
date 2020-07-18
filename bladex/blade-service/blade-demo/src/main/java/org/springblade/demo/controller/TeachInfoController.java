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
import org.springblade.demo.annotation.JwtIgnore;
import org.springblade.demo.annotation.Role;
import org.springblade.demo.common.RoleCode;
import org.springblade.demo.entity.*;
import org.springblade.demo.service.*;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.demo.vo.TeachInfoVO;
import org.springblade.core.boot.ctrl.BladeController;
import sun.text.resources.no.CollationData_no;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
	private ITeachAccountService teachAccountService;
	private IOrgAccountService orgAccountService;
	private IOrgInfoService orgInfoService;

	/**
	 * 根据机构id获取所属的教师列表
	 * 例如：http://localhost:9101/teachinfo/getTeachListById?orgId=1
	 */
	@JwtIgnore
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
	@JwtIgnore
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
	@JwtIgnore
	@GetMapping("/queryTeachList")  //【API-7】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件查询：获取教师信息详情", notes = "传入orgId+教师表任意字段的值（除教师id）")
	public  R<List<TeachInfo>> queryOrgList(String orgAccount,TeachInfo teachInfo) {
		//根据机构账户找到机构id
		OrgAccount orgAccount1 = new OrgAccount();
		orgAccount1.setOrgAccount(orgAccount);
		int orgId = orgAccountService.getOne(Condition.getQueryWrapper(orgAccount1)).getOrgId();
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

	/**
	 * 查询：根据teachAccount获取教师基本信息——7.11
	 * http://localhost:9101/teachinfo/getProfile?teachAccount=教师账户名
	 * http://cyf.ngrok2.xiaomiqiu.cn/teachinfo/getProfile?teachAccount=教师账户名
	 * http://cyf.ngrok2.xiaomiqiu.cn/teachinfo/getProfile?teachAccount=110
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.TEACH})
	@GetMapping("/getProfile")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据teachAccount获取教师基本信息", notes = "传入教师账户名")
	public R<JSONObject> getProfile(String teachAccount) {
		TeachAccount teachAccountCondition=new TeachAccount();
		teachAccountCondition.setTeachAccount(teachAccount);
		//根据teachAccount查询教师id
		int teachId;
		try {
			teachId = teachAccountService.getOne(Condition.getQueryWrapper(teachAccountCondition)).getTeachId();
			//设置查询条件
		}
		catch (Exception e){
			return R.fail("教师账户不存在");
		}
		TeachInfo teachInfoCondition=new TeachInfo();
		teachInfoCondition.setTeachId(teachId);
		RelOrgTeach relOrgTeachCondition=new RelOrgTeach();
		relOrgTeachCondition.setTeachId(teachId);
		//结果返回
		JSONObject result=new JSONObject();
		result.put("teachInfo",teachInfoService.getOne(Condition.getQueryWrapper(teachInfoCondition)));
		result.put("teachAccount",teachAccountService.getOne(Condition.getQueryWrapper(teachAccountCondition)));
		result.put("relOrgTeach",relOrgTeachService.list(Condition.getQueryWrapper(relOrgTeachCondition)));
		return R.data(result);
	}

	/**
	 * 根据机构账户获取机构所属教师信息——7.11
	 * 例如：http://localhost:9101/teachinfo/getOrgTeacherByOrgAccount?orgAccount=1101234561
	 * http://cyf.ngrok2.xiaomiqiu.cn/teachinfo/getOrgTeacherByOrgAccount?orgAccount=机构账户名
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.ORG})
	@GetMapping("/getOrgTeacherByOrgAccount")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据机构账户获取机构所属教师信息", notes = "传入机构账户名")
	public  R<List<TeachInfo>> getOrgTeacherByOrgAccount(String orgAccount) {
		OrgAccount orgAccountCondition=new OrgAccount();
		orgAccountCondition.setOrgAccount(orgAccount);
		//根据机构账户名获得机构id
		try{
			int orgId=orgAccountService.getOne(Condition.getQueryWrapper(orgAccountCondition)).getOrgId();
			//获取机构下所有教师的id
			RelOrgTeach relOrgTeachCondition=new RelOrgTeach();
			relOrgTeachCondition.setOrgId(orgId);
			List<RelOrgTeach> teachIds=relOrgTeachService.list(Condition.getQueryWrapper(relOrgTeachCondition));
			//根据教师id获取教师信息
			List<TeachInfo> result=new ArrayList<>();
			for(int i=0;i<teachIds.size();++i)
			{
				int teachId=teachIds.get(i).getTeachId();
				int status=teachIds.get(i).getOrgTeachStatus();
				if(status==3) continue;
				TeachInfo teachInfoCondition=new TeachInfo();
				teachInfoCondition.setTeachId(teachId);
				TeachInfo info=teachInfoService.getOne(Condition.getQueryWrapper(teachInfoCondition));
				result.add(info);
			}
			return R.data(result);
		}catch (Exception e){
			return R.fail("机构账户不存在");
		}
	}

	/**
	 * 新增机构所属的教师（机构确认：状态设为0）——7.12
	 * http://localhost:9101/teachinfo/orgAddTeach?mailbox=1234567890@qq.com&setTeachAccount=10086&orgAccount=1101234563
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.ORG})
	@PostMapping("/orgAddTeach")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "新增机构所属的教师（机构确认）", notes = "传入邮箱、账户名、机构账户名")
	public  R<String> orgAddTeach(String mailbox,String setTeachAccount,String orgAccount) {
		System.out.println("=============传入参数:"+mailbox+","+setTeachAccount+","+orgAccount+"============");
		//根据机构名称获取机构id
		OrgAccount orgAccountCondition=new OrgAccount();
		orgAccountCondition.setOrgAccount(orgAccount);
		OrgAccount orgAccount1;
		try{
			orgAccount1 =orgAccountService.getOne(Condition.getQueryWrapper(orgAccountCondition));
		}catch (Exception e){
			System.out.println("后台异常：异常点1");
			return  R.fail("后台异常：异常点1，请联系后台人员cyf");
		}
		int orgId;
		try{
			orgId=orgAccount1.getOrgId();
			System.out.println("机构id:"+orgId);
		}catch (Exception e){
			return R.fail("机构不存在！");
		}
		//查询数据库是否已有该邮箱（即：教师是否已注册）
		int teachId;
		TeachAccount teachAccountCondition=new TeachAccount();
		teachAccountCondition.setTeachEmail(mailbox);
		TeachAccount teachAccount=teachAccountService.getOne(Condition.getQueryWrapper(teachAccountCondition));
		if(teachAccount==null){  //教师未注册账户
			//判断账户名是否已被使用
			List<String> teachAccountALL=new ArrayList<>();
			List<TeachAccount> teachAccounts=teachAccountService.list(Condition.getQueryWrapper(null));
			for(int i=0;i<teachAccounts.size();++i){
				teachAccountALL.add(teachAccounts.get(i).getTeachAccount());
			}
			System.out.println(teachAccountALL);
			if(teachAccountALL.contains(setTeachAccount)){
				return R.fail("账户名已存在！");
			}
			//为教师创建账户（默认密码：123456）
			TeachAccount teachAccount_new=new TeachAccount();
			teachAccount_new.setTeachAccount(setTeachAccount);  //根据传入的账户名设置自定义账户名
			teachAccount_new.setTeachEmail(mailbox);  //设置邮箱
			teachAccount_new.setPasswd("123456");
			LocalDateTime nowTime=LocalDateTime.now();
			teachAccount_new.setCreateTime(nowTime);
			try{
				R status=R.status(teachAccountService.save(teachAccount_new));
				System.out.println(status);  //测试账户信息添加状态
			}catch (Exception e){
				System.out.println("机构id:"+orgId+"教师账户信息："+teachAccount_new);
				e.printStackTrace();
				return R.fail("添加教师账户失败！");
			}
			//查询添加的账户自动生成的教师id
			TeachAccount teachAccountCondition2=new TeachAccount();
			teachAccountCondition2.setTeachEmail(mailbox);
			TeachAccount teachAccountDetail;
			try{
				teachAccountDetail=teachAccountService.getOne(Condition.getQueryWrapper(teachAccountCondition2));
			}catch (Exception e){
				System.out.println("后台异常：异常点2");
				return  R.fail("后台异常：异常点2，请联系后台人员cyf");
			}
			teachId=teachAccountDetail.getTeachId();
			//为教师信息在数据库中添加一个记录
			TeachInfo teachInfo_new=new TeachInfo();
			teachInfo_new.setTeachId(teachId);
			try{
				R status=R.status(teachInfoService.save(teachInfo_new));
				System.out.println(status);
			}catch (Exception e){
				System.out.println(e);
				return R.fail("教师信息添加失败！");
			}
		}
		else{  //已注册账户，直接获取教师id添加到rel_org_account中，设置状态为0
			teachId=teachAccount.getTeachId();  //教师账户存在，说明教师个人信息也存在，直接添加机构教师关系记录
		}
		//判断教师机构关系中是否已经有此记录
		RelOrgTeach relOrgTeach_exist_condition=new RelOrgTeach();
		relOrgTeach_exist_condition.setOrgId(orgId);
		relOrgTeach_exist_condition.setTeachId(teachId);
		RelOrgTeach relOrgTeach_exist;
		try{
			relOrgTeach_exist=relOrgTeachService.getOne(Condition.getQueryWrapper(relOrgTeach_exist_condition));
		}catch (Exception e){
			System.out.println("后台异常：异常点3");
			return  R.fail("后台异常：异常点3，请联系后台人员cyf");
		}
		if(relOrgTeach_exist!=null) return R.fail("教师机构关系已存在！");
		//将教师id加到rel_org_account中，设置状态为0
		RelOrgTeach relOrgTeach_new=new RelOrgTeach();
		relOrgTeach_new.setOrgId(orgId);
		relOrgTeach_new.setTeachId(teachId);
		relOrgTeach_new.setOrgTeachStatus(0);  //机构单方面确认
		R status_OT=R.status(relOrgTeachService.save(relOrgTeach_new));
		System.out.println(status_OT);
		return R.success("教师添加到机构成功！");
	}


	/**
	 * 删除机构下属教师（将教师设为离职状态（3））——7.12
	 * http://localhost:9101/teachinfo/orgRemoveTeach?orgAccount=1101234563&teachId=3
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.ORG})
	@PostMapping("/orgRemoveTeach")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "教师从机构离职", notes = "传入机构账户名、教师id")
	public  R<String> orgRemoveTeach(String orgAccount,int teachId) {
		//根据机构账户名获取机构id
		OrgAccount orgAccountCondition=new OrgAccount();
		orgAccountCondition.setOrgAccount(orgAccount);
		OrgAccount orgAccount1=orgAccountService.getOne(Condition.getQueryWrapper(orgAccountCondition));
		int orgId;
		try{
			orgId=orgAccount1.getOrgId();
		}catch (Exception e){
			return R.fail("机构不存在！");
		}
		//根据机构id、教师id获取rel_org_teach指定的记录
		RelOrgTeach relOrgTeach=new RelOrgTeach();
		relOrgTeach.setOrgId(orgId);
		relOrgTeach.setTeachId(teachId);
		RelOrgTeach relOrgTeach_modify=relOrgTeachService.getOne(Condition.getQueryWrapper(relOrgTeach));
		System.out.println(relOrgTeach_modify);
		if(relOrgTeach_modify==null){
			return R.fail("教师id不存在！");
		}
		else{
			relOrgTeach_modify.setOrgTeachStatus(3);  //状态3表示教师从机构离职
			System.out.println(relOrgTeach_modify);
			relOrgTeachService.updateById(relOrgTeach_modify);
		}
		return R.success("教师从机构离职成功!");
	}
//	/**
//	 * 更新rel_org_teach==>测试！！
//	 * http://localhost:9101/teachinfo/orgRemoveTeach?orgName=小神童&teachId=3
//	 */
//	@JwtIgnore
////	@Role(include = {RoleCode.ORG})
//	@PostMapping("/orgTeachUpdate")
//	@ApiOperationSupport(order = 1)
//	@ApiOperation(value = "教师从机构离职", notes = "传入机构id、教师id")
//	public  R<String> orgTeachUpdate(int orgId,int teachId) {
//		RelOrgTeach relOrgTeach=new RelOrgTeach();
//		relOrgTeach.setOrgId(orgId);
//		relOrgTeach.setTeachId(teachId);
//		RelOrgTeach relOrgTeach_modify;
//		try{
//			relOrgTeach_modify=relOrgTeachService.getOne(Condition.getQueryWrapper(relOrgTeach));
//		}catch (Exception e){
//			return R.fail("找不到此机构与教师的关系！");
//		}
//		System.out.println(relOrgTeach_modify);
//		relOrgTeach_modify.setOrgTeachStatus(3);
//		//更新信息
//		relOrgTeachService.updateById(relOrgTeach_modify);
//
//		return R.success("教师从机构离职成功!");
//	}

	/**
	 * 传入教师id、机构id和状态，修改教师与机构间的状态——7.13
	 * http://localhost:9101/teachinfo/changeOrgTeachStatus?teachId=1&orgId=1&orgTeachStatus=1
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.ORG})
	@PostMapping("/changeOrgTeachStatus")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "传入教师id、机构id和状态，修改教师与机构间的状态", notes = "传入teachId、orgId和orgTeachStatus")
	public  R<String> orgRemoveTeach(int teachId,int orgId,int orgTeachStatus) {
		//根据教师id和机构id获取相应记录
		RelOrgTeach relOrgTeach=new RelOrgTeach();
		relOrgTeach.setTeachId(teachId);
		relOrgTeach.setOrgId(orgId);
		//判断机构教师关系是否存在
		RelOrgTeach relOrgTeachModify;
		try{
			relOrgTeachModify=relOrgTeachService.getOne(Condition.getQueryWrapper(relOrgTeach));
		}
		catch (Exception e){
			return R.fail("教师关系信息不存在！");
		}
		relOrgTeachModify.setOrgTeachStatus(orgTeachStatus);
		return R.status(relOrgTeachService.updateById(relOrgTeachModify));
	}

	  /**
  * ybj 7.12
  * 修改机构下属教师信息
  * @param teachInfo
  * http://localhost:9101/teachinfo/updateOrgTeacher/
  * @return
  */
 @JwtIgnore
 // @Role(include = {RoleCode.ORG})
 @PostMapping("/updateOrgTeacher")
 @ApiOperationSupport(order = 5)
 @ApiOperation(value = "修改", notes = "传入teachInfo")
 public R<List<TeachInfo>> updateOrgTeacher(String orgName, TeachInfo teachInfo) {
    //更新教师信息
    TeachInfo teachInfoCondition = new TeachInfo();
    teachInfoService.updateById(teachInfo);
    //根据orgName获取orgId
    OrgInfo orgInfo = new OrgInfo();
    orgInfo.setOrgName(orgName);
    int orgId;
    try{
       orgId = orgInfoService.getOne(Condition.getQueryWrapper(orgInfo)).getOrgId();
    }catch (Exception e){
       return R.fail("机构不存在");
    }
    //根据orgId查找机构下所有教师Id
    RelOrgTeach relOrgTeach = new RelOrgTeach();
    relOrgTeach.setOrgId(orgId);
    List<RelOrgTeach> relOrgTeachList = relOrgTeachService.list(Condition.getQueryWrapper(relOrgTeach));
    //查找机构下所有教师信息
    List<TeachInfo> list = new ArrayList<>();
    for(int i=0;i<relOrgTeachList.size();++i)
    {
       int teachId = relOrgTeachService.getOne(Condition.getQueryWrapper(relOrgTeachList.get(i))).getTeachId();
       list.add(teachInfoService.getById(teachId));
    }
    return R.data(list);
 }


	/**
	 * ybj 7.14
	 * 根据teachAccount修改教师账户和教师信息
	 * Request Example:http://localhost:9101/teachinfo/changeTeachByTeachAccount +请求体
	 * @param Params
	 * @return
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.TEACH})
	@ApiOperationSupport(order = 2)
	@ApiOperation(value="",notes="传入teachId,teachAccount,teachInfo")
	@PostMapping("/changeTeachByTeachId")
	public R changeTeachByTeachId(@Valid @RequestBody String Params){
		//根据teachAccount找到teachId
		System.out.println("请求体内容:\n"+ Params);


		JSONObject obj = JSON.parseObject(Params);
		Integer teachId = obj.getInteger("teachId");
		JSONObject tcobj = (JSONObject)obj.get("teachAccount");
		JSONObject tfobj = (JSONObject)obj.get("teachInfo");
		TeachAccount teachAcc = new TeachAccount();
		teachAcc.setTeachId(teachId);

		TeachAccount teachAccountCondition;
		teachAccountCondition = teachAccountService.getOne(Condition.getQueryWrapper(teachAcc));
		TeachInfo teachInfo = new TeachInfo();
		TeachInfo teachIn;
		teachInfo.setTeachId(teachId);
		teachIn = teachInfoService.getOne(Condition.getQueryWrapper(teachInfo));

		//更新教师账户信息
		teachAccountCondition.setTeachId(tcobj.getInteger("teachId"));
		teachAccountCondition.setTeachAccount(tcobj.getString("teachAccount"));
		teachAccountCondition.setPasswd(tcobj.getString("passwd"));
		teachAccountCondition.setTeachEmail(tcobj.getString("teachEmail"));
		teachAccountCondition.setTeachPhone(tcobj.getString("teachPhone"));

		String time = tcobj.getString("createTime");
		time = time.replace('T',' ');
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//LocalDateTime createTime = LocalDateTime.parse(tcobj.getString("createTime"),fmt1);
		LocalDateTime createTime = LocalDateTime.parse(time,fmt1);

		teachAccountCondition.setCreateTime(createTime);

		//更新教师个人信息
		teachIn.setTeachId(tfobj.getInteger("teachId"));
		teachIn.setTeachName(tfobj.getString("teachName"));
		teachIn.setSex(tfobj.getString("sex"));
		teachIn.setTeachingSubject(tfobj.getString("teachingSubject"));
		teachIn.setIsTeachQualifCert(tfobj.getString("isTeachQualifCert"));
		teachIn.setTeachQualifClass(tfobj.getString("teachQualifClass"));
		teachIn.setCertificateNum(tfobj.getString("certificateNum"));
		teachIn.setProfessionalTitle(tfobj.getString("professionTitle"));
		teachIn.setCountryNature(tfobj.getString("countryNature"));
		teachIn.setNationality(tfobj.getString("nationality"));
		teachIn.setHighestEducation(tfobj.getString("highestEducation"));
		teachIn.setEducationalInstitution(tfobj.getString("educationalInstitution"));
		teachIn.setHighestDegree(tfobj.getString("highestDegree"));
		teachIn.setDegreeObtainedInstitution(tfobj.getString("degreeObtainedInstitution"));
		teachIn.setMajor(tfobj.getString("major"));
		teachIn.setWorkType(tfobj.getString("workType"));
		teachIn.setIdNum(tfobj.getString("idNum"));
		teachIn.setNativePlace(tfobj.getString("nativePlace"));
		teachIn.setPoliticalStatus(tfobj.getString("politicalStatus"));

		//json->LocalDate 日期处理
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthdate = LocalDate.parse(tfobj.getString("teachBirth"),fmt2);
		teachIn.setTeachBirth(birthdate);
		LocalDate graduatedate= LocalDate.parse(tfobj.getString("graduationDate"),fmt2);
		teachIn.setGraduationDate(graduatedate);
		return R.status(teachAccountService.updateById(teachAccountCondition)&&teachInfoService.updateById(teachIn));
	}
	//===========================以下为自动生成的接口==============================
	/**
	 * 详情
	 */
	@JwtIgnore
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
	@JwtIgnore
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
	@JwtIgnore
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入teachInfo")
	public R save(TeachInfo teachInfo) {
		return R.status(teachInfoService.save(teachInfo));
	}

	/**
	 * 修改 教师信息
	 */
	@JwtIgnore
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入teachInfo")
	public R update(@Valid @RequestBody TeachInfo teachInfo) {
		return R.status(teachInfoService.updateById(teachInfo));
	}

	/**
	 * 新增或修改 教师信息
	 */
	@JwtIgnore
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
