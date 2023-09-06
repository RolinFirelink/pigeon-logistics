package com.pigeon.logistics.listener;

import com.pigeon.logistics.entity.SysLogEntity;
import com.pigeon.logistics.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author dxy
 * @date 2023年02月09日
 */
@Slf4j
@Component
public class LogEventListener {

    private final LogService logService;

    public LogEventListener(LogService logService) {
        this.logService = logService;
    }

    /**
     * 监听操作，保存操作记录
     *
     * @param event 操作对象
     */
    @Async // 开启异步
    @EventListener(SysLogEntity.class) // 开启监听
    public void saveSysLog(SysLogEntity event) {
        log.info("About to be asynchronously saved to the database");
        logService.save(event);
    }

}
