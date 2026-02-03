package io.github.webhook.clinet;

import io.github.webhook.config.WebhookConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private WebhookConfig webhookConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭 CSRF（前后端分离推荐）
                .csrf().disable()

                // 放行登录页与静态资源
                .authorizeRequests()
                .antMatchers("/login.html", "/actuator/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()

                .and()

                // 表单登录配置
                .formLogin()
                .loginPage("/login.html")              // 自定义登录页
                .loginProcessingUrl("/login")          // 提交地址
                .successHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.getWriter().write("{\"code\":0,\"msg\":\"登录成功\"}");
                })
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.getWriter().write("{\"code\":401,\"msg\":\"用户名或密码错误\"}");
                })
                .permitAll()

                .and()

                // 退出登录
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, auth) -> {
                    resp.sendRedirect("/login.html");
                });
    }

    // 用户认证配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        WebhookConfig.Admin admin = webhookConfig.getAdmin();
        String username = "admin";
        String password = "123456";
        if (admin != null) {
            username = admin.getUsername();
            password = admin.getPassword();
        }
        auth.inMemoryAuthentication()
                .withUser(username)
                .password(passwordEncoder().encode(password))
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
