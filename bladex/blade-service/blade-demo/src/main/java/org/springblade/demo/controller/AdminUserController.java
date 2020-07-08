package org.springblade.demo.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import org.springblade.demo.annotation.JwtIgnore;
import org.springblade.demo.common.response.Result;
import org.springblade.demo.entity.OrgInfo;
import org.springblade.demo.entity.User;
import org.springblade.demo.model.Audience;
import org.springblade.demo.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/7/18 10:41
 * Version: v1.0
 * ========================
 */
@Slf4j
@RestController
@RequestMapping("/jwt")
@AllArgsConstructor
public class AdminUserController {

    private Audience audience;

    @PostMapping("/ceshi")
	public Result t(@RequestBody User user){
    	return Result.SUCCESS(user.getUsername());
	}

    @PostMapping("/signin")
	@JwtIgnore
    public Result adminLogin(HttpServletResponse response, String username, String password) {

        if(!password.equals("123456"))
            return Result.FAIL("密码错误");

		String userId = "1";
		String role = "admin";

        // 创建token
        String token = JwtTokenUtil.createJWT(userId, username, role, audience);
        log.info("### 登录成功, token={} ###", token);

        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        JSONObject result = new JSONObject();
        result.put("token", token);
        return Result.SUCCESS(result);
    }

    @GetMapping("/users")
    public Result userList() {
        log.info("### 查询所有用户列表 ###");
        return Result.SUCCESS();
    }
}
