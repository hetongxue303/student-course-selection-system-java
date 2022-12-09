package com.hetongxue.utils;

import com.hetongxue.base.constant.Base;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * JWT工具类
 *
 * @author 何同学
 */
@Component
public class JwtUtils {

    /**
     * 过期时间(单位:毫秒)
     */
    private static final long EXPIRATION_TIME = Base.TOKEN_EXPIRATION_TIME;
    /**
     * 密钥
     */
    private static final String SECRET = Base.TOKEN_SECRET;
    /**
     * 签名算法
     */
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = Base.TOKEN_SIGNATURE_ALGORITHM;
    /**
     * Bearer
     */
    private static final String PREFIX = Base.TOKEN_PREFIX;

    /**
     * 生成token
     *
     * @param UserId   用户ID
     * @param username 用户名
     * @return java.lang.String
     */
    public String generateToken(Long UserId, String username) {
        String token = Jwts.builder()
                // 设置头部参数
                .setHeaderParam("typ", "JWT")
                // 设置ID
                .setId(String.valueOf(UserId))
                // 设置主题
                .setSubject(username)
                // 设置发行时间
                .setIssuedAt(new Date())
                // 设置过期时间(claim设置在过期时间之前 否则可能会出现过期时间不生效问题)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // 设置签发方式
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
        return Base.TOKEN_IS_PREFIX ? PREFIX + token : token;
    }

    /**
     * 验证token
     *
     * @param token 用户token
     * @return boolean
     */
    public boolean VerifyToken(String token) {
        try {
            if (Base.TOKEN_IS_PREFIX) {
                return !ObjectUtils.isEmpty(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.substring(PREFIX.length())).getBody());
            }
            return ObjectUtils.isEmpty(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody());
        } catch (Exception e) {
            System.err.println("token验证失败");
            return false;
        }
    }

    /**
     * 解析token
     *
     * @param token 用户token
     * @return io.jsonwebtoken.Claims
     */
    public Claims getClaims(String token) {
        try {
            if (Base.TOKEN_IS_PREFIX) {
                return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.substring(PREFIX.length())).getBody();
            }
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            System.err.println("token解析失败");
            return null;
        }
    }

    /**
     * 判断是否过期
     *
     * @param token 用户token
     * @return boolean
     */
    public boolean isExpired(String token) {
        try {
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            System.err.println("token异常");
            return false;
        }

    }

    /**
     * 获取用户ID
     *
     * @param token 用户token
     * @return java.lang.Long
     */
    public Long getUserId(String token) {
        try {
            return Long.valueOf(getClaims(token).getId());
        } catch (Exception e) {
            System.err.println("用户ID获取失败");
            return null;
        }
    }

    /**
     * 获取用户名
     *
     * @param token 用户token
     * @return java.lang.String
     */
    public String getUsername(String token) {
        try {
            return getClaims(token).getSubject();
        } catch (Exception e) {
            System.err.println("用户名获取失败");
            return null;
        }
    }

    /**
     * 刷新token
     *
     * @param token 用户token
     * @return java.lang.String
     */
    public String refreshToken(String token) {
        try {
            return generateToken(getUserId(token), getUsername(token));
        } catch (Exception e) {
            System.err.println("刷新token失败");
            return null;
        }
    }

}