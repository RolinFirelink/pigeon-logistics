package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 旅行申请表视图类
 *
 * @param id
 * @param applicationDept
 * @param applicationDate
 * @param applicationReason
 * @param deptAdviceSign
 * @param deptAdviceSignImgUrl
 * @param deptAdviceDate
 * @param approvalBy
 * @param drawnUpBy
 * @param reviewBy
 * @param items
 * @author dxy
 * @date 2022年3月9日
 */
@Schema(description = "旅行申请表视图类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReleaseApplicationFormVO(
        @Schema(description = "旅行放行条号", type = "string(int64)", accessMode = Schema.AccessMode.READ_ONLY)
        @JsonSerialize(using = ToStringSerializer.class)
        Long id,

        @Schema(description = "申请部门")
        String applicationDept,

        @Schema(description = "申请日期")
        LocalDateTime applicationDate,

        @Schema(description = "申请理由")
        String applicationReason,

        @Schema(description = "部门建议")
        String deptAdvice,

        @Schema(description = "部门建议签名")
        String deptAdviceSign,

        @Schema(description = "部门建议签名图片地址")
        String deptAdviceSignImgUrl,

        @Schema(description = "部门签名日期")
        LocalDateTime deptAdviceDate,

        @Schema(description = "批准")
        String approvalBy,

        @Schema(description = "拟定")
        String drawnUpBy,

        @Schema(description = "审核")
        String reviewBy,

        @Schema(description = "放行条明细")
        List<ReleaseApplicationFormItemVO> items
) {

    public ReleaseApplicationFormVO(List<ReleaseApplicationFormItemVO> items) {
        this(null, null, null, null, null, null, null, null, null, null, null, items);
    }
}
