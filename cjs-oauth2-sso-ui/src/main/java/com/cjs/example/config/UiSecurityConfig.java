package com.cjs.example.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**").permitAll()
                .anyRequest()
                .authenticated()
                .and().logout().permitAll()
                .logoutUrl("/signOut")//自定义退出的地址
                .logoutSuccessUrl("/login")//退出后跳转页面
                .deleteCookies("JSESSIONID")//删除cookies
                .and().csrf().disable();//关闭跨站请求伪造

    }

}
