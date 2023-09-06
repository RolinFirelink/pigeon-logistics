package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * 区域信息视图类
 *
 * @author dxy
 */
@Data
@Builder
@Schema(description = "区域信息视图类")
public class RegionVO {

    @Schema(description = "区域名称", accessMode = Schema.AccessMode.READ_ONLY)
    private String name;

    @Schema(description = "区域全称", accessMode = Schema.AccessMode.READ_ONLY)
    private String fullName;

    @Schema(description = "国际区域编码", accessMode = Schema.AccessMode.READ_ONLY)
    private String statisCode;

    @Schema(description = "区域级别", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer regionType;

    @JsonInclude(NON_NULL)
    @Schema(description = "子区域信息列表", accessMode = Schema.AccessMode.READ_ONLY)
    private List<RegionVO> children;
}
