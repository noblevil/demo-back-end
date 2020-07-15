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
import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.aspectj.weaver.patterns.IToken;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.demo.annotation.JwtIgnore;
import org.springblade.demo.annotation.Role;
import org.springblade.demo.common.RoleCode;
import org.springblade.demo.entity.*;
import org.springblade.demo.service.*;
import org.springblade.demo.util.JwtTokenUtil;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.demo.vo.CourseVO;
import org.springblade.core.boot.ctrl.BladeController;
import sun.text.resources.no.CollationData_no;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 课程内容  控制器
 *
 * @author BladeX
 * @since 2020-07-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/course")
@Api(value = "课程内容 ", tags = "课程内容 接口")
public class CourseController extends BladeController {

	private ICourseService courseService;
	private ICourseClassService courseClassService;
	private IOrgInfoService orgInfoService;
	private IOrgAccountService orgAccountService;
	private ITeachAccountService teachAccountService;
	private IRelTeachCourseClassService relTeachCourseClassService;


	/**
	 * 根据机构id获取所属课程信息
	 * 例如：http://localhost:9101/course/getCourseListById?orgId=2
	 */
	@JwtIgnore
	@GetMapping("/getCourseListById")  //【API-5】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据机构id获取所属课程信息", notes = "传入机构id")
	public  R<List<Course>> getCourseListById(Integer orgId) {

		Course course=new Course() ;
		course.setOrgId(orgId);
		List<Course> list = courseService.list(Condition.getQueryWrapper(course));
		return R.data(list);
	}

	/**
	 * 根据机构id获取所有班次列表
	 * 例如：http://localhost:9101/course/getClassCourseListById?orgId=2
	 */
	@JwtIgnore
	@GetMapping("/getClassCourseListById")  //【API-6】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据机构id获取所有班次", notes = "传入机构id")
	public  R<List<CourseClass>> getTeachListById(Integer orgId) {

		Course course=new Course() ;
		course.setOrgId(orgId);
		System.out.println("函数前");

		List<Course> courseList = courseService.list(Condition.getQueryWrapper(course));  //获取机构所属课程
		List<CourseClass> courseClassList = new ArrayList<CourseClass>();

		for (Course object : courseList) {
			int courseId = object.getCourseId();
			CourseClass courseClass = new CourseClass();
			courseClass.setCourseId(courseId);
			System.out.println(courseClass);
			CourseClass detail = courseClassService.getOne(Condition.getQueryWrapper(courseClass));

			courseClassList.add(detail);  //将查询到的教师信息添加到列表

		}
		return R.data(courseClassList);


	}


	/**
	 * 筛选：条件查询  获取课程信息详情（根据声明实体的名）
	 * 根据机构id查询：http://localhost:9101/course/queryCourseList?orgId=2
	 * 根据课程id查询：http://localhost:9101/course/queryCourseList?courseId=101
	 * 多个条件查询：http://localhost:9101/course/queryCourseList?studentRank=小学&courseSubject=理科
	 * 参数为空：返回全部
	 */
	@JwtIgnore
	@GetMapping("/queryCourseList")  //【API-8】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件查询：获取课程信息详情", notes = "传入任意字段的值")
	public  R<List<Course>> queryOrgList(Course course) {
		List<Course> list = courseService.list(Condition.getQueryWrapper(course));
		return R.data(list);
	}

	/**
	 * 筛选：条件查询  获取课程班次信息详情（根据声明实体的名）
	 * 根据课程id查询：http://localhost:9101/course/queryCourseClassList?courseId=101
	 * 根据课程名查询：http://localhost:9101/course/queryCourseClassList?className=小一
	 * 多个条件查询：http://localhost:9101/course/queryCourseClassList?scourseId=101&className=小一
	 * 参数为空：返回全部
	 */
	@JwtIgnore
	@GetMapping("/queryCourseClassList")  //【API-9】
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件查询：获取课程班次信息详情", notes = "传入任意字段的值")
	public  R<List<CourseClass>> queryOrgList(CourseClass courseClass) {
		List<CourseClass> list = courseClassService.list(Condition.getQueryWrapper(courseClass));
		return R.data(list);
	}

	/**
	 * 筛选：根据机构名或机构id获取机构所属的课程（返回课程信息）——7.11
	 * http://cyf.ngrok2.xiaomiqiu.cn/course/queryCourse?condition=机构名或机构id
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.TEACH})
	@GetMapping("/queryCourse")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据机构名或机构id获取机构所属的课程（返回课程信息）", notes = "传入机构名或机构id")
	public  R<List<Course>> queryCourseByOrgId(String condition) {
		Course courseCondition=new Course();
		Pattern pattern = Pattern.compile("[0-9]*");
		boolean type=pattern.matcher(condition).matches();  //用正则表达式判断传入的参数类型，若为数字，返回true
		if(type) {  //数字类型
			Integer orgId = Integer.valueOf(condition);
			System.out.println("传入机构id=" + orgId);
			courseCondition.setOrgId(orgId);  //设置筛选条件
		}
		else {
			//根据机构名获取机构id
			OrgInfo orgCondition=new OrgInfo();  //筛选条件
			orgCondition.setOrgName(condition);
			OrgInfo orgInfo=orgInfoService.getOne(Condition.getQueryWrapper(orgCondition));
			try{
				Integer orgId=orgInfo.getOrgId();
				courseCondition.setOrgId(orgId);
			}
			catch (Exception e){
				return R.fail("机构账户不存在");
//				return R.data(200,null,"机构账户不存在");
			}
		}

		//获取机构所属的所有课程信息
		List<Course> list = courseService.list(Condition.getQueryWrapper(courseCondition));
		return R.data(list);
	}


	/**
	 * 筛选：根据机构名或机构id获取机构所属的课程名称（只返回课程名）——7.11
	 * http://cyf.ngrok2.xiaomiqiu.cn/course/queryCourseName?condition=机构名或机构id
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.TEACH})
	@GetMapping("/queryCourseName")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件查询：获取机构所属的课程名", notes = "传入机构名或机构id")
	public  R<List<String>> queryCourseName(String condition) {

		Course courseCondition=new Course();
		Pattern pattern = Pattern.compile("[0-9]*");
		boolean type=pattern.matcher(condition).matches();  //用正则表达式判断传入的参数类型，若为数字，返回true
		if(type) {  //数字类型
			Integer orgId = Integer.valueOf(condition);
			System.out.println("传入机构id=" + orgId);
			courseCondition.setOrgId(orgId);  //设置筛选条件
		}
		else {
			//根据机构名获取机构id
			OrgInfo orgCondition=new OrgInfo();  //筛选条件
			orgCondition.setOrgName(condition);
			OrgInfo orgInfo=orgInfoService.getOne(Condition.getQueryWrapper(orgCondition));
			try{
				Integer orgId=orgInfo.getOrgId();
				courseCondition.setOrgId(orgId);
			}
			catch (Exception e){
				return R.fail("机构账户不存在");
			}
		}

		//获取机构所属的所有课程信息
		List<Course> list = courseService.list(Condition.getQueryWrapper(courseCondition));
		//提取课程名字段
		List<String> courseNames = new ArrayList<>();
		for(int i=0;i<list.size();++i){
			String name=list.get(i).getCourseName();
			courseNames.add(name);
		}
		return R.data(courseNames);
	}

	/**
	 * 筛选：根据机构账户名获取机构所属的课程——7.11
	 * 例子：http://localhost:9101/course/getOrgCourseByOrgAccount?orgAccount=1101234561
	 * http://cyf.ngrok2.xiaomiqiu.cn/course/getOrgCourseByOrgAccount?orgAccount=机构账户名
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.ORG})
	@GetMapping("/getOrgCourseByOrgAccount")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件查询：根据机构账户名获取机构所属的课程", notes = "传入机构账户名")
	public  R<List<Course>> getOrgCourseByOrgAccount(String orgAccount) {
		OrgAccount orgAccountCondition=new OrgAccount();
		orgAccountCondition.setOrgAccount(orgAccount);
		//根据机构账户名获取机构id
		try{
			int orgId=orgAccountService.getOne(Condition.getQueryWrapper(orgAccountCondition)).getOrgId();
			//根据机构名获取机构id
			Course courseCondition=new Course();
			courseCondition.setOrgId(orgId);
			List<Course> list = courseService.list(Condition.getQueryWrapper(courseCondition));

			return R.data(list);
		}catch (Exception e){
			return R.fail("机构账户不存在");
		}
	}

	/**
	 * 根据教师账户名获取教师所有课程——7.13
	 * http://localhost:9101/course/getTeachCourseByTeachAccount?teachAccount=110
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.TEACH})
	@GetMapping("/getTeachCourseByTeachAccount")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据教师账户名获取教师所有课程", notes = "传入教师账户名")
	public  R<List<Course>> getTeachCourseByTeachAccount(String teachAccount) {
		//根据教师账户名获取教师id
		TeachAccount teachAccountCondition=new TeachAccount();
		teachAccountCondition.setTeachAccount(teachAccount);
		TeachAccount teachAccountInfo=teachAccountService.getOne(Condition.getQueryWrapper(teachAccountCondition));
		int teachId;
		try{
			teachId=teachAccountInfo.getTeachId();
		}catch (Exception e){
			return R.fail("此教师账户名不存在！");
		}
		//根据教师id查询教师班次关系表获取班次id列表
		RelTeachCourseClass relTeachCourseClassCondition=new RelTeachCourseClass();
		relTeachCourseClassCondition.setTeachId(teachId);
		List<RelTeachCourseClass> relTeachCourseClassList=relTeachCourseClassService.list(Condition.getQueryWrapper(relTeachCourseClassCondition));
		//根据班次id获取课程id列表
		List<Integer> courseIdList=new ArrayList<>();
		for(int i=0;i<relTeachCourseClassList.size();++i){
			CourseClass courseClassCondition=new CourseClass();
			courseClassCondition.setCourseClassId(relTeachCourseClassList.get(i).getCourseClassId());
			CourseClass courseClassInfo=courseClassService.getOne(Condition.getQueryWrapper(courseClassCondition));
			int courseId=courseClassInfo.getCourseId();
			courseIdList.add(courseId);
		}
		//根据课程id获取课程详情列表
		List<Course> courseInfoList=new ArrayList<>();
		for(int i=0;i<courseIdList.size();++i){
			Course courseCondition=new Course();
			courseCondition.setCourseId(courseIdList.get(i));
			courseInfoList.add(courseService.getOne(Condition.getQueryWrapper(courseCondition)));
		}
		return R.data(courseInfoList);
	}


	/**
  * ybj 7.12
  * 根据机构账户名orgAccount获取机构下属课程信息表
  * http://localhost:9101/course/getOrgCourse?orgAccount=机构账户名
  * @param orgAccount
  * @return
  */
 @JwtIgnore
 // @Role(include = {RoleCode.ORG})
 @GetMapping("/getOrgCourse")
 @ApiOperationSupport(order = 1)
 @ApiOperation(value = "根据机构账户名获取机构下属所有课程信息", notes = "传入机构账户orgAccount")
 public R<List<Course>> getOrgCourse(String orgAccount){
    //根据机构账户获取机构id
    OrgAccount OrgAccountCondition = new OrgAccount();
    OrgAccountCondition.setOrgAccount(orgAccount);
    int orgId = orgAccountService.getOne(Condition.getQueryWrapper(OrgAccountCondition)).getOrgId();
    //根据机构id获取机构所属课程信息列表
    Course course = new Course();
    course.setOrgId(orgId);
    return R.data(courseService.list(Condition.getQueryWrapper(course)));
 }




	/**
	 *  ybj	7.12
	 * 新增机构下属的课程信息
	 * http://localhost:9101/orgaccount/course?orgAccount=1101234561&课程信息
	 *http://localhost:9101/orgaccount/course?orgAccount=1101234561&courseName=线性代数3&courseSubject=理科&courseLevel=大学&courseLink=无&contentIntro=就是数学课&validPeriod=1年&studentRank=大学&totalLessons=48学时&textbook=无&publishCompany=无&studentGrade=大学一年级&isbnNumber=无
	 *
	 * @param params
	 * @return
	 */
	@JwtIgnore
//	@Role(include = {RoleCode.ORG})
	@PostMapping("/addCourse")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据机构账户名以及课程信息新增机构下属课程", notes = "传入机构账户orgAccount,课程信息")
	public R add(@Valid @RequestBody String params) {

		JSONObject obj = JSON.parseObject(params);
		OrgAccount orgAccountCondition = new OrgAccount();
		orgAccountCondition.setOrgAccount((obj.getString("orgAccount")));
		int orgId;
		try {
			orgId = orgAccountService.getOne(Condition.getQueryWrapper(orgAccountCondition)).getOrgId();
		}
		catch (Exception e)
		{
			return R.fail("机构不存在");
		}
		//设定机构id
		Course course = new Course();
		course.setOrgId(orgId);
		//obj.remove("orgAccount");
		//System.out.println(obj);
		//解析对象
		course.setCourseName(obj.getString("courseName"));
		course.setCourseSubject(obj.getString("courseSubject"));
		course.setCourseLevel(obj.getString("courseLevel"));
		course.setCourseLink(obj.getString("courseLink"));
		course.setContentIntro(obj.getString("contentIntro"));
		course.setValidPeriod(obj.getString("validPeriod"));
		course.setStudentRank(obj.getString("studentRand"));
		course.setStudentGrade(obj.getString("studentGrade"));
		course.setTotalLessons(obj.getString("totalLessons"));
		course.setTextbook(obj.getString("textbook"));
		course.setPublishCompany(obj.getString("publishCompany"));
		course.setIsbnNumber(obj.getString("isbnNumber"));
		return R.status(courseService.save(course));

	}



	/**
	 * ybj 7.12
	 * 删除机构下属的课程信息
	 *http://localhost:9101/course/deleteCourse?orgAccount=1101234561&courseId=101
	 */

	@JwtIgnore
	//	@Role(include = {RoleCode.ORG})
	@PostMapping("/deleteCourse")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据机构账户名以及课程Id删除掉机构下属课程", notes = "传入机构账户orgAccount,课程号courseId")
	public R delete(String orgAccount, Integer courseId) {
		//根据orgAccount找到orgId
		OrgAccount orgAccountCondtion = new OrgAccount();
		orgAccountCondtion.setOrgAccount(orgAccount);
		int orgId;
		try {
			orgId = orgAccountService.getOne(Condition.getQueryWrapper(orgAccountCondtion)).getOrgId();
		}
		catch (Exception e) {
			return R.fail("机构账户不存在");
		}
		//根据orgId找到courseList
		Course course = new Course();
		course.setOrgId(orgId);
		List<Course> courseList = new ArrayList<>();
		courseList = courseService.list(Condition.getQueryWrapper(course));
		//删除courseList中对应的courseId项
		return R.status(courseService.removeById(courseId));
	}


	/**
	 * 修改 课程信息——7.13
	 * http://localhost:9101/course/updateCourseInfo（以Requestbody方式传入信息）
	 */
	@JwtIgnore
	//	@Role(include = {RoleCode.ORG})
	@PostMapping("/updateCourseInfo")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改课程信息", notes = "传入course")
	public R updateCourseInfo(@Valid @RequestBody Course course) {
		return R.status(courseService.updateById(course));
	}

	//===========================以下为自动生成的接口==============================

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入course")
	public R<Course> detail(Course course) {
		Course detail = courseService.getOne(Condition.getQueryWrapper(course));
		return R.data(detail);
	}

	/**
	 * 分页 课程内容
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入course")
	public R<IPage<Course>> list(Course course, Query query) {
		IPage<Course> pages = courseService.page(Condition.getPage(query), Condition.getQueryWrapper(course));
		return R.data(pages);
	}

	/**
	 * 自定义分页 课程内容
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入course")
	public R<IPage<CourseVO>> page(CourseVO course, Query query) {
		IPage<CourseVO> pages = courseService.selectCoursePage(Condition.getPage(query), course);
		return R.data(pages);
	}

	/**
	 * 新增 课程内容
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入course")
	public R save(@Valid @RequestBody Course course) {
		return R.status(courseService.save(course));
	}

	/**
	 * 修改 课程内容
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入course")
	public R update(@Valid @RequestBody Course course) {
		return R.status(courseService.updateById(course));
	}

	/**
	 * 新增或修改 课程内容
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入course")
	public R submit(@Valid @RequestBody Course course) {
		return R.status(courseService.saveOrUpdate(course));
	}


	/**
	 * 删除 课程内容
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(courseService.removeByIds(Func.toLongList(ids)));
	}


}
