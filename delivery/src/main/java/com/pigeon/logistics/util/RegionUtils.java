package com.pigeon.logistics.util;

import com.pigeon.logistics.entity.RegionEntity;
import com.pigeon.logistics.entity.VO.RegionVO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 地区工具类
 *
 * @author rainkyzhong
 * @date 2023/3/4
 */
public class RegionUtils {
    /**
     * 将地区的二维表转成树形结构，只到第四级
     */
    public static RegionVO regionListToTree(List<RegionEntity> list) {
        var china = RegionVO.builder()
                .name("中国")
                .fullName("中华人民共和国")
                .regionType(1)
                .statisCode("100000000000")
                .children(new ArrayList<>())
                .build();
        var map = new HashMap<Integer, RegionVO>(16);
        map.put(1, china);
        for (RegionEntity region : list) {

            if (region.getRegionType() < 5) {
                int parent = region.getParentId();

                if (map.containsKey(parent)) {
                    var regionVO = RegionVO.builder()
                            .name(region.getName())
                            .regionType(region.getRegionType())
                            .fullName(region.getFullName())
                            .statisCode(region.getStatisCode())
                            .build();
                    RegionVO parentRegion = map.get(parent);
                    if (parentRegion.getChildren() != null) {
                        parentRegion.getChildren().add(regionVO);
                    } else {
                        parentRegion.setChildren(new ArrayList<>() {{
                            add(regionVO);
                        }});
                    }

                    if (Objects.equals(regionVO.getName(), "市辖区")) {
                        var fullName = regionVO.getFullName();
                        regionVO.setName(StringUtils.remove(fullName, "市辖区").trim());
                    }

                    if (StringUtils.contains(regionVO.getFullName(), "市辖区")) {
                        var fullName = regionVO.getFullName();
                        // 在中间去除“市辖区”字符串后用正则表达式删除多余空格，然后切掉前后空格。
                        regionVO.setFullName(StringUtils.remove(fullName, "市辖区").replaceAll("(?U)\\s+", " ").trim());
                    }

                    map.put(region.getId(), regionVO);
                }
            }
        }
        return china;
    }
}
