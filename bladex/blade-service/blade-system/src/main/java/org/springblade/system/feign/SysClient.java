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
package org.springblade.system.feign;

import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.*;
import org.springblade.system.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 系统服务Feign实现类
 *
 * @author Chill
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class SysClient implements ISysClient {

	private IDeptService deptService;

	private IPostService postService;

	private IRoleService roleService;

	private IMenuService menuService;

	private ITenantService tenantService;

	private IParamService paramService;

	@Override
	@GetMapping(MENU)
	public R<Menu> getMenu(Long id) {
		return R.data(menuService.getById(id));
	}

	@Override
	@GetMapping(DEPT)
	public R<Dept> getDept(Long id) {
		return R.data(deptService.getById(id));
	}

	@Override
	public R<String> getDeptIds(String tenantId, String deptNames) {
		return R.data(deptService.getDeptIds(tenantId, deptNames));
	}

	@Override
	@GetMapping(DEPT_NAME)
	public R<String> getDeptName(Long id) {
		return R.data(deptService.getById(id).getDeptName());
	}

	@Override
	@GetMapping(DEPT_NAMES)
	public R<List<String>> getDeptNames(String deptIds) {
		return R.data(deptService.getDeptNames(deptIds));
	}

	@Override
	@GetMapping(DEPT_CHILD)
	public R<List<Dept>> getDeptChild(Long deptId) {
		return R.data(deptService.getDeptChild(deptId));
	}

	@Override
	public R<Post> getPost(Long id) {
		return R.data(postService.getById(id));
	}

	@Override
	public R<String> getPostIds(String tenantId, String postNames) {
		return R.data(postService.getPostIds(tenantId, postNames));
	}

	@Override
	public R<String> getPostName(Long id) {
		return R.data(postService.getById(id).getPostName());
	}

	@Override
	public R<List<String>> getPostNames(String postIds) {
		return R.data(postService.getPostNames(postIds));
	}

	@Override
	@GetMapping(ROLE)
	public R<Role> getRole(Long id) {
		return R.data(roleService.getById(id));
	}

	@Override
	public R<String> getRoleIds(String tenantId, String roleNames) {
		return R.data(roleService.getRoleIds(tenantId, roleNames));
	}

	@Override
	@GetMapping(ROLE_NAME)
	public R<String> getRoleName(Long id) {
		return R.data(roleService.getById(id).getRoleName());
	}

	@Override
	@GetMapping(ROLE_ALIAS)
	public R<String> getRoleAlias(Long id) {
		return R.data(roleService.getById(id).getRoleAlias());
	}

	@Override
	@GetMapping(ROLE_NAMES)
	public R<List<String>> getRoleNames(String roleIds) {
		return R.data(roleService.getRoleNames(roleIds));
	}

	@Override
	@GetMapping(ROLE_ALIASES)
	public R<List<String>> getRoleAliases(String roleIds) {
		return R.data(roleService.getRoleAliases(roleIds));
	}

	@Override
	@GetMapping(TENANT)
	public R<Tenant> getTenant(Long id) {
		return R.data(tenantService.getById(id));
	}

	@Override
	@GetMapping(TENANT_ID)
	public R<Tenant> getTenant(String tenantId) {
		return R.data(tenantService.getByTenantId(tenantId));
	}

	@Override
	@GetMapping(PARAM)
	public R<Param> getParam(Long id) {
		return R.data(paramService.getById(id));
	}

	@Override
	@GetMapping(PARAM_VALUE)
	public R<String> getParamValue(String paramKey) {
		return R.data(paramService.getValue(paramKey));
	}

}
