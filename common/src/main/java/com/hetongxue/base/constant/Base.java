package com.hetongxue.base.constant;

import io.jsonwebtoken.SignatureAlgorithm;

import java.util.concurrent.TimeUnit;

/**
 * 基础常量
 *
 * @author 何同学
 */
public class Base {


    // security
    public static final String SECURITY_AUTHORIZATION = "authorization";
    public static final String SECURITY_CAPTCHA = "captcha";

    // mybatis plus
    public static final String CREATE_TIME_KEY = "createTime";
    public static final String UPDATE_TIME_KEY = "updateTime";

    // token
    public static final long TOKEN_EXPIRATION_TIME = 30 * 60 * 1000;// 30分钟(单位:毫秒)
    public static final String TOKEN_SECRET = "568548eddf5fe99ews458dftgv4v87gh";
    public static final SignatureAlgorithm TOKEN_SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    public static final boolean TOKEN_IS_PREFIX = true;
    public static final String TOKEN_PREFIX = "Bearer ";

    // redis
    public static final long REDIS_TIMEOUT = 3 * 24 * 60 * 60;// 3天
    public static final TimeUnit REDIS_TIMEUNIT = TimeUnit.DAYS;

}