package org.springblade.demo.config;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


/**
 * 自定义登录成功配置
 *
 * @author Chill
 */
@Configuration
@AllArgsConstructor
@EnableResourceServer
public class BladeResourceServerConfiguration extends ResourceServerConfigurerAdapter {



	@Override
	@SneakyThrows
	public void configure(HttpSecurity http) {
		http.headers().frameOptions().disable();
		http.formLogin()
			.and()
			.authorizeRequests()
			.antMatchers(
				"/blog/list/**"
			).permitAll()
			.anyRequest().authenticated().and()
			.csrf().disable();
	}

}

