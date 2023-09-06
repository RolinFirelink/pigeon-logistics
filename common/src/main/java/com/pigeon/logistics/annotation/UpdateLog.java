package com.pigeon.logistics.annotation;

import com.pigeon.logistics.enums.BusinessTypeEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 更新操作日志记录
 *
 * @author dxy
 * @date 2023年3月22日
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Log(businessType = BusinessTypeEnum.UPDATE)
public @interface UpdateLog {

    @AliasFor(
            annotation = Log.class
    )
    String value() default "";

    /**
     * 模块
     */
    @AliasFor(
            annotation = Log.class
    )
    String title() default "default";

}
