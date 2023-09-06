package com.pigeon.logistics.aspect;

import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.annotation.Log;
import com.pigeon.logistics.annotation.UpdateLog;
import com.pigeon.logistics.entity.SysLogEntity;
import com.pigeon.logistics.enums.BusinessTypeEnum;
import com.pigeon.logistics.listener.EventPublishListener;
import com.pigeon.logistics.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

/**
 * @author dxy
 * @date 2023年02月09日
 */
@Aspect
@Slf4j
@Component
public class SysLogAspect {

    private final EventPublishListener eventPubListener;

    public SysLogAspect(EventPublishListener eventPubListener) {
        this.eventPubListener = eventPubListener;
    }

    /**
     * 以注解所标注的方法作为切入点
     */
    @Pointcut("@annotation(com.pigeon.logistics.annotation.Log)")
    public void sysLog() {
    }

    /**
     * 在切点之后织入
     */
    @After("sysLog()")
    public void doAfter(JoinPoint joinPoint) {
        var operation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Log.class);
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            var request = attributes.getRequest();
            String method = request.getMethod();
            String url = request.getRequestURL().toString();
            String ip = IpUtils.getIpAddr(request);

            var sysLog = SysLogEntity.builder()
                    .businessType(operation.businessType())
                    .title(operation.title())
                    .requestMethod(method)
                    .requestUrl(url)
                    .requestIp(ip)
                    // 从登录中token获取登录人员信息即可
                    .operator("dxy")
                    .requestTime(LocalDateTime.now())
                    .build();

            // 发布消息
            eventPubListener.pushListener(sysLog);
            log.info("The operation log sent successfully：{}", sysLog);
        }
    }

    // FIXME 不知道如何优雅织入，于是写了一坨屎让它跑起来
    @Pointcut("@annotation(com.pigeon.logistics.annotation.DeleteLog)")
    public void deleteLog() {
    }

    /**
     * 在切点之后织入
     */
    @After("deleteLog()")
    public void doAfter2(JoinPoint joinPoint) {
        var operation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(DeleteLog.class);
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            var request = attributes.getRequest();
            String method = request.getMethod();
            String url = request.getRequestURL().toString();
            String ip = IpUtils.getIpAddr(request);

            var sysLog = SysLogEntity.builder()
                    .businessType(BusinessTypeEnum.DELETE)
                    .title(operation.title())
                    .requestMethod(method)
                    .requestUrl(url)
                    .requestIp(ip)
                    // 从登录中token获取登录人员信息即可
                    .operator("dxy")
                    .requestTime(LocalDateTime.now())
                    .build();

            // 发布消息
            eventPubListener.pushListener(sysLog);
            log.info("The operation log sent successfully：{}", sysLog);
        }
    }

    // FIXME 不知道如何优雅织入，于是写了一坨屎让它跑起来
    @Pointcut("@annotation(com.pigeon.logistics.annotation.InsertLog)")
    public void insertLog() {
    }

    /**
     * 在切点之后织入
     */
    @After("insertLog()")
    public void doAfter3(JoinPoint joinPoint) {
        var operation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(InsertLog.class);
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            var request = attributes.getRequest();
            String method = request.getMethod();
            String url = request.getRequestURL().toString();
            String ip = IpUtils.getIpAddr(request);

            var sysLog = SysLogEntity.builder()
                    .businessType(BusinessTypeEnum.INSERT)
                    .title(operation.title())
                    .requestMethod(method)
                    .requestUrl(url)
                    .requestIp(ip)
                    // 从登录中token获取登录人员信息即可
                    .operator("dxy")
                    .requestTime(LocalDateTime.now())
                    .build();

            // 发布消息
            eventPubListener.pushListener(sysLog);
            log.info("The operation log sent successfully：{}", sysLog);
        }
    }

    // FIXME 不知道如何优雅织入，于是写了一坨屎让它跑起来
    @Pointcut("@annotation(com.pigeon.logistics.annotation.UpdateLog)")
    public void updateLog() {
    }

    /**
     * 在切点之后织入
     */
    @After("updateLog()")
    public void doAfter4(JoinPoint joinPoint) {
        var operation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(UpdateLog.class);
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            var request = attributes.getRequest();
            String method = request.getMethod();
            String url = request.getRequestURL().toString();
            String ip = IpUtils.getIpAddr(request);

            var sysLog = SysLogEntity.builder()
                    .businessType(BusinessTypeEnum.UPDATE)
                    .title(operation.title())
                    .requestMethod(method)
                    .requestUrl(url)
                    .requestIp(ip)
                    // 从登录中token获取登录人员信息即可
                    .operator("dxy")
                    .requestTime(LocalDateTime.now())
                    .build();

            // 发布消息
            eventPubListener.pushListener(sysLog);
            log.info("The operation log sent successfully：{}", sysLog);
        }
    }

}
