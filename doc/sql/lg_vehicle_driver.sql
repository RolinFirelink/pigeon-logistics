/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 08/04/2023 14:56:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lg_vehicle_relevance
-- ----------------------------
DROP TABLE IF EXISTS `lg_vehicle_driver`;
CREATE TABLE `lg_vehicle_driver`  (
  `id` bigint NOT NULL COMMENT '唯一主键',
  `driver_id` bigint NULL DEFAULT NULL COMMENT '司机id',
  `vehicle_id` bigint NULL DEFAULT NULL COMMENT '车辆id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `occ_version` int NULL DEFAULT 0 COMMENT '乐观锁并发控制',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
