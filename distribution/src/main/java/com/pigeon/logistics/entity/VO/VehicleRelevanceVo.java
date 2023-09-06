package com.pigeon.logistics.entity.VO;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @param id       关联数据id
 * @param driverId 司机id
 * @param vehicleId    车辆id
 */
@Schema(description = "前端司机车辆关联数据传入类")
public record VehicleRelevanceVo(

        @Schema(description = "关联数据id")
        Long id,

        @Schema(description = "司机id")
        Long driverId,

        @Schema(description = "车辆id")
        Long vehicleId
) {
}
