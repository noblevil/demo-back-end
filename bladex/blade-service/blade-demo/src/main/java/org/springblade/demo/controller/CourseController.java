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
import org.springblade.demo.annotation.JwtIgnore;
import org.springblade.demo.entity.CourseClass;
import org.springblade.demo.service.ICourseClassService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.demo.entity.Course;
import org.springblade.demo.vo.CourseVO;
import org.springblade.demo.service.ICourseService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.ArrayList;
import java.util.List;

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
