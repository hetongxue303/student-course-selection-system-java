package com.hetongxue.configuration.security;

import com.hetongxue.configuration.security.filter.CaptchaFilter;
import com.hetongxue.configuration.security.filter.JwtAuthenticationFilter;
import com.hetongxue.configuration.security.handler.*;
import com.hetongxue.system.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

/**
 * spring security配置类
 *
 * @author 何同学
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfiguration {

    /**
     * 配置参数
     */
    public static final String LOGIN_PATH = "/auth/login";
    public static final String LOGOUT_PATH = "/auth/logout";
    public static final String NO_PERMISSION = "/noPermission";
    public static final String CAPTCHA_IMAGE = "/auth/captchaImage";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CAPTCHA_KEY = "code";

    /**
     * 请求白名单
     */
    private static final String[] REQUEST_WHITE_LIST = {LOGIN_PATH, LOGOUT_PATH, NO_PERMISSION, CAPTCHA_IMAGE};

    @Resource
    private UserDetailsServiceImpl userService;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private UserLogoutSuccessHandler logoutSuccessHandler;
    @Resource
    private UserAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private UserAccessDeniedHandler accessDeniedHandler;
    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Resource
    private CaptchaFilter captchaFilter;

    /**
     * 配置安全过滤器链
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 配置授权过滤器
        http.authorizeRequests()
                // 指定未登录时允许访问的URL
                .antMatchers(REQUEST_WHITE_LIST).permitAll()
                // 指定认证成功后才能访问的URL
                .anyRequest().authenticated();

        // 配置登录过滤器
        http.formLogin()
                // 设置登录URL
                .loginProcessingUrl(LOGIN_PATH)
                // 设置登陆请求参数
                .usernameParameter(USERNAME).passwordParameter(PASSWORD)
                // 设置认证成功处理器
                .successHandler(loginSuccessHandler)
                // 认证失败处理器
                .failureHandler(loginFailureHandler);

        // 配置注销过滤器
        http.logout()
                // 设置注销URL
                .logoutUrl(LOGOUT_PATH)
                // 设置注销成功处理类
                .logoutSuccessHandler(logoutSuccessHandler);

        // 配置异常处理器
        http.exceptionHandling()
                // 设置匿名用户处理类(未登录 需要跳转登陆)
                .authenticationEntryPoint(authenticationEntryPoint)
                // 设置无权访问处理类(已登录 但无权限时)
                .accessDeniedHandler(accessDeniedHandler);
        // 设置无权限跳转URL
//                .accessDeniedPage(NO_PERMISSION);

        // 配置跨域
        http.cors()
                // 设置跨域配置类
                .configurationSource(corsConfigurationSource());

        // 配置csrf
        http.csrf().disable();

        // 配置会话管理
        http.sessionManagement(sessionManagementConfigurer ->
                // 设置创建会话策略
                sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 设置 captcha 过滤器
        http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);

        // 设置 JWT 过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 设置 httpBasic
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

//    /**
//     * web安全过滤器
//     *
//     * @return WebSecurityCustomizer
//     */
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().antMatchers(REQUEST_WHITE_LIST);
//    }

    /**
     * 配置认证处理器
     *
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // 设置密码编码器
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        // 设置UserDetails实现类
        authenticationProvider.setUserDetailsService(userService);
        // 这里要隐藏系统默认的提示信息，否则一直显示账户或密码错误
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    /**
     * 配置加密方式
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 解决添加security后 springboot自身配置的跨域不生效问题(主要由于security的过滤器优先级高于springboot的优先级)
     *
     * @return CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.HEAD);
        config.addAllowedMethod(HttpMethod.OPTIONS);
        config.addAllowedMethod(HttpMethod.PATCH);
        config.addAllowedMethod(HttpMethod.TRACE);
        config.addAllowedOrigin("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}