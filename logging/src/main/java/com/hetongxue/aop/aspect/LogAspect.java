package com.hetongxue.aop.aspect;

import com.hetongxue.aop.annotation.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 自定义日志切面
 *
 * @author 何同学
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    /**
     * 设置切点：指定自己定义的日志注解
     *
     * @return void
     */
    @Pointcut("@annotation(com.hetongxue.aop.annotation.LogAnnotation)")
    public void logPointcut() {
    }

    /**
     * 设置通知：注解对应设置的切点方法名
     *
     * @param point
     * @return java.lang.Object
     */
    @Around("logPointcut()")
    public Object log(ProceedingJoinPoint point) throws Throwable {
        // 记录开始时长(毫秒)
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 记录日志
        recordLog(point, time);

        return result;
    }

    /**
     * 日志输出
     *
     * @param point
     * @param time
     */
    private void recordLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("===============================begin===============================");
        log.warn("Operate       ：{}", logAnnotation.operate());
        log.warn("Detail        ：{}", logAnnotation.detail());
        log.warn("Ip Address    ：{}", getRequestIp());
        log.warn("Http Method   ：{}", request.getRequestURL().toString());
        log.warn("Class Method  ：{}", point.getTarget().getClass().getName() + "." + signature.getName() + "()");
//        log.warn("Request Args  ：{}", new Gson().toJson(point.getArgs()[0]));
        log.warn("Take          ：{} ms", time);
        log.info("================================end================================");
    }

    /**
     * 获取请求的IP地址
     *
     * @return java.lang.String
     */
    private String getRequestIp() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        assert request != null;
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}