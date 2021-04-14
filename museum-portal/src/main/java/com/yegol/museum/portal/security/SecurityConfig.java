package com.yegol.museum.portal.security;


import com.yegol.museum.portal.service.UserDetailsServicesImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

//@Configuration表示当前的类是一个Spring的配置类
@Configuration
//这个配置类是配置Spring-Security的,
//prePostEnabled= true表示启动权限管理功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //为了方便实现框架中规定的方法达到设置权限的目的
    //继承WebSecurityConfigurerAdapter类重写其中的方法

    @Resource
    private UserDetailsServicesImpl userDetailsService;
    //重写configure方法参数为AuthenticationManagerBuilder类型
    //这个方法是规定用户登录验证信息的
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "index.html",
                        "/layui/**",
                        "/yegui/**",
                        "/login.html",
                        "/register.html",
                        "/login",
                        "/register"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureUrl("/login.html?error")
                .defaultSuccessUrl("/index.html")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html?logout");
    }
}