package com.pigeon.logistics.entity.VO;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 自定义分页数据
 * fixme long类型，前端的精度丢失问题
 *
 * @author dxy
 * @date 2023年3月9日
 */
@Data
@Schema(description = "分页数据")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomPage<T> {
    @Schema(description = "当前页码", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private long pageNo;
    @Schema(description = "页面大小", example = "10", accessMode = Schema.AccessMode.READ_ONLY)
    private long pageSize;
    @Schema(description = "总记录数", example = "68", accessMode = Schema.AccessMode.READ_ONLY)
    private long totalRecord;
    @Schema(description = "分页记录列表", example = "68", accessMode = Schema.AccessMode.READ_ONLY)
    private List<T> records;
    @Schema(description = "总页数", example = "7", accessMode = Schema.AccessMode.READ_ONLY)
    private long totalPage;
    @Schema(description = "分页条件", accessMode = Schema.AccessMode.READ_ONLY)
    private SearchCondition searchCondition;

    public CustomPage(Page<?> page) {
        this.pageNo = page.getCurrent();
        this.pageSize = page.getSize();
        this.totalRecord = page.getTotal();
        this.totalPage = page.getPages();
    }

}
