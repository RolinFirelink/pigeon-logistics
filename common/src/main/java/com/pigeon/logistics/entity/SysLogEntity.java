package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigeon.logistics.enums.BusinessTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 操作日志实体类
 *
 * @author dxy
 * @date 2023年02月09日
 */
@Data
@Builder
@TableName("sys_log")
public class SysLogEntity {

    /**
     * 日志主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 操作模块
     */
    private String title;

    /**
     * 业务类型：0其他 1新增 2修改 3删除
     */
    private BusinessTypeEnum businessType;

    /**
     * 请求方式
     */
    @TableField("method")
    private String requestMethod;

    /**
     * 操作人员
     */
    private String operator;

    /**
     * 请求URL
     */
    @TableField("url")
    private String requestUrl;

    /**
     * 请求地址
     */
    @TableField("ip")
    private String requestIp;

    /**
     * 请求操作时间
     */
    @TableField("time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestTime;
}
