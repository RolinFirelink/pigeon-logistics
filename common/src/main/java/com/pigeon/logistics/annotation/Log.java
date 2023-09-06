package com.pigeon.logistics.annotation;

import com.pigeon.logistics.enums.BusinessTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志记录
 *
 * @author dxy
 * @date 2023年3月22日
 */
@Target({ElementType.TYPE, ElementType.METHOD}) // 注解智能用于方法
@Retention(RetentionPolicy.RUNTIME) // 修饰注解的生命周期
public @interface Log {

    //    @AliasFor("title")
    String value() default "default";

    /**
     * 模块
     */
    //    @AliasFor("value")
    String title() default "default";

    /**
     * 功能
     */
    BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;
}
