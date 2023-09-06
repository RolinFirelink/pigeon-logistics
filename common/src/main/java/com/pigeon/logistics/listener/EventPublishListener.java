package com.pigeon.logistics.listener;

import com.pigeon.logistics.entity.SysLogEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author dxy
 * @date 2023年02月09日
 */
@Component
public class EventPublishListener {

    private final ApplicationContext applicationContext;

    public EventPublishListener(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void pushListener(SysLogEntity sysLogEvent) {
        applicationContext.publishEvent(sysLogEvent);
    }
}
