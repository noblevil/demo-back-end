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
package org.springblade.demo.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.demo.entity.Blog;
import org.springblade.demo.vo.BlogVO;
import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2020-06-14
 */
public class BlogWrapper extends BaseEntityWrapper<Blog, BlogVO>  {

	public static BlogWrapper build() {
		return new BlogWrapper();
 	}

	@Override
	public BlogVO entityVO(Blog blog) {
		BlogVO blogVO = Objects.requireNonNull(BeanUtil.copy(blog, BlogVO.class));

		//User createUser = UserCache.getUser(blog.getCreateUser());
		//User updateUser = UserCache.getUser(blog.getUpdateUser());
		//blogVO.setCreateUserName(createUser.getName());
		//blogVO.setUpdateUserName(updateUser.getName());

		return blogVO;
	}

}
