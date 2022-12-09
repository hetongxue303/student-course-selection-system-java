package com.hetongxue.configuration.security.filter;

import com.hetongxue.base.constant.Base;
import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.configuration.security.exception.JwtAuthenticationException;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.system.service.RoleService;
import com.hetongxue.system.service.UserService;
import com.hetongxue.utils.JwtUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * JWT认证过滤器(每一次的请求都会访问该过滤器)
 *
 * @author 何同学
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(Base.SECURITY_AUTHORIZATION);
        if (Objects.isNull(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 校验token是否存在
        if (ObjectUtils.isEmpty(jwtUtils.getClaims(token))) {
            throw new JwtAuthenticationException("凭证异常");
        }
        // 校验token是否过期
        String redisToken = String.valueOf(redisUtils.getValue(Base.SECURITY_AUTHORIZATION));
        if (Objects.isNull(redisToken) || jwtUtils.isExpired(token)) {
            throw new JwtAuthenticationException("凭证过期");
        }
        // 校验用户token与redis中的token是否一致
        if (!Objects.equals(token, redisToken)) {
            throw new JwtAuthenticationException("凭证不一致");
        }
        // 验证合法性
        User user = userService.selectOneByUsername(jwtUtils.getUsername(token));
        if (!user.getUserId().equals(jwtUtils.getUserId(token))) {
            throw new JwtAuthenticationException("凭证不合法");
        }
        // 获取权限列表
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(SecurityUtils.generateAuthority(roleService.selectRoleListByUserId(user.getUserId()), menuService.selectMenuListByUserId(user.getUserId())));
        // 封装Authentication
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        // 存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }

}