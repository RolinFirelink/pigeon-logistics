package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rolin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_dictionary")
public class DictionaryEntity {

    /**
     * 唯一主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 节点名
     */
    private String name;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 排序值
     */
    private Integer sortId;
}
