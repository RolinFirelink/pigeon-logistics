package com.pigeon.logistics.annotation;

import com.pigeon.logistics.enums.BusinessTypeEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 删除操作日志记录
 *
 * @author dxy
 * @date 2023年3月22日
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Log(businessType = BusinessTypeEnum.DELETE)
public @interface DeleteLog {

    @AliasFor(annotation = Log.class)
    String value() default "";

    /**
     * 模块
     */
    @AliasFor(annotation = Log.class, attribute = "title")
    String title() default "default";

}
