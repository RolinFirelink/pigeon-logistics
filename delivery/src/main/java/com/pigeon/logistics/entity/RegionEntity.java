package com.pigeon.logistics.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区域信息实体类
 *
 * @author dxy
 * @date 2022年2月11日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("region")
public class RegionEntity {

    /**
     * 区域编号。数据库自增编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 区域国标编码。12位国标代码
     */
    private String statisCode;

    /**
     * 区域编码。6位国标代码。注意：只有三级数据有6位编码，四级（五级）都是12位编码
     */
    private String code;

    /**
     * 区域全称。在当前区域名称上整合了前置数据。【非国标】
     */
    private String fullName;

    /**
     * 类型。1 国家、2省份、3市、4县、5街道、6村 【非国标】
     */
    private Integer regionType;

    /**
     * 排列顺序。自定义显示顺序。【非国标】
     */
    private Integer sort;

    /**
     * 上级对象编号。当前区域数据的上级区域对应的编号 【非国标】
     */
    private Integer parentId;

    /**
     * 是否删除。默认0 【非国标】
     */
    @TableLogic
    private Integer isDel;

    /**
     * 维度
     */
    private Float lat;

    /**
     * 经度
     */
    private Float lng;
}
