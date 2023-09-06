/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 08/04/2023 14:56:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lg_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `lg_vehicle`;
CREATE TABLE `lg_vehicle`  (
  `id` bigint NOT NULL COMMENT '唯一主键',
  `model_code` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车型代码(一般是17位)',
  `insurance_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '保险分类 \r\n存储格式中各种保险用逗号隔开 \r\n示例: 保险1,保险2,保险3 ',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车型名称',
  `manufacturer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生产厂商名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '购买价格',
  `vehicle_kind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆种类',
  `vehicle_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆类型',
  `seat_number` tinyint NULL DEFAULT NULL COMMENT '核定座位数',
  `max_weight` int NULL DEFAULT NULL COMMENT '最大载重量(单位kg)',
  `list_year` int NULL DEFAULT NULL COMMENT '上市年份(数字代表上市时间为几几年)',
  `risk_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车型风险分类',
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆品牌',
  `vehicle_series` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车系',
  `driving_permit_id` bigint NULL DEFAULT NULL COMMENT '汽车行驶证表id(-1代表没有绑定行驶证)',
  `annual_audit` tinyint NULL DEFAULT NULL COMMENT '是否年检(0代表已经年检,1代表还未年检)',
  `insurance` tinyint NULL DEFAULT NULL COMMENT '车辆是否交车强险(0代表已经交过,1代表没交)',
  `scrap` tinyint NULL DEFAULT NULL COMMENT '车辆是否报废(0代表没有报废,1代表已经报废)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `occ_version` int NULL DEFAULT 0 COMMENT '乐观锁并发控制',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
