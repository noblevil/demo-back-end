package org.springblade.demo.interceptor;

import org.springblade.demo.common.response.ResultCode;
import org.springblade.demo.annotation.JwtIgnore;
import org.springblade.demo.common.exception.CustomException;
import org.springblade.demo.model.Audience;
import org.springblade.demo.util.AuthUtil;
import org.springblade.demo.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ========================
 * token验证拦截器
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/7/18 9:46
 * Version: v1.0
 * ========================
 */

@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private Audience audience;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);

            // 放行带有JwtIgnore注解的路由
            if (jwtIgnore != null)
                return true;
        }

        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // 获取请求头信息authorization信息
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.info("## authHeader= {}", authHeader);

        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            log.info("### 用户未登录，请先登录 ###");
            throw new CustomException(ResultCode.USER_NOT_LOGGED_IN);
        }

        // 获取token
        final String token = authHeader.substring(7);

        if(audience == null){
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            audience = (Audience) factory.getBean("audience");
        }

        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息，详见common/exception/handler/GlobalExceptionHandler.java
		JwtTokenUtil.parseJWT(token, audience.getBase64Secret());

        //验证token是否需要刷新
        if (!JwtTokenUtil.isSafe(token, audience)) {
        	// 生成新token
			String userId = JwtTokenUtil.getUserId(token, audience.getBase64Secret());
			String userName = JwtTokenUtil.getUsername(token, audience.getBase64Secret());
			String role = JwtTokenUtil.getUserRole(token, audience.getBase64Secret());
			String newToken = JwtTokenUtil.createJWT(userId, userName, role, audience);

			// 将新的token添加到响应头
        	response.addHeader("New-Token", newToken);
        }

        // 验证用户访问权限（根据注解）
		AuthUtil.AuthCheck(JwtTokenUtil.getUserRole(token, audience.getBase64Secret()), handler);

		// servlet路径
		String servletPath = request.getServletPath();
		System.out.println("servletPath:" + servletPath);

        return true;
    }

    @Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

	}

}
