package org.springblade.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springblade.demo.annotation.JwtIgnore;
import org.springblade.demo.entity.OrgAccount;
import org.springblade.demo.entity.OrgInfo;
import org.springblade.demo.entity.TeachAccount;
import org.springblade.demo.service.IOrgAccountService;
import org.springblade.demo.service.ITeachAccountService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
@Api(value = "注册登录 ", tags = "注册登录 接口")
public class LoginController {
	private ITeachAccountService teachAccountService;
	private IOrgAccountService orgAccountService;


	/**
	 * 	cyf:7.17
	 * 	用户登录（以RequestBody方式传入）
	 * @param object
	 * @return
	 * http://localhost:9101/login/userLogin
	 * {
	 *     "account":"1101234561",
	 *     "password":"123456",
	 *     "role":"org"
	 * }
	 */
	@JwtIgnore
	@PostMapping("/userLogin")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "用户登录", notes = "账号account、密码password、角色role")
	public R<String> userLogin(@Valid @RequestBody JSONObject object) {
		String role=object.getString("role");
		String account=object.getString("account");
		String password=object.getString("password");
		System.out.println("传入参数："+role+","+account+','+password);
		if(role.equals("org")){
			OrgAccount orgAccountCondition=new OrgAccount();
			orgAccountCondition.setOrgAccount(account);
			orgAccountCondition.setPasswd(password);
			OrgAccount orgAccount=orgAccountService.getOne(Condition.getQueryWrapper(orgAccountCondition));
			System.out.println("查询到的账户信息："+orgAccount);
			if(orgAccount==null){
				return R.status(false);
			}
		}
		else if(role.equals("teach")){
			TeachAccount teachAccountCondition=new TeachAccount();
			teachAccountCondition.setTeachAccount(account);
			teachAccountCondition.setPasswd(password);
			TeachAccount teachAccount=teachAccountService.getOne(Condition.getQueryWrapper(teachAccountCondition));
			System.out.println("查询到的账户信息："+teachAccount);
			if(teachAccount==null){
				return R.status(false);
			}
		}
		else return R.fail("未知用户类型！");
		return R.status(true);
	}
}
