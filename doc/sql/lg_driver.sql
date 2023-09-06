/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 29/03/2023 22:22:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lg_driver
-- ----------------------------
DROP TABLE IF EXISTS `lg_driver`;
CREATE TABLE `lg_driver`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机名',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `age` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '年龄',
  `entry_time` datetime NULL DEFAULT NULL COMMENT '入职时间',
  `license_type` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '驾驶证类别',
  `driving_age` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '驾龄',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '现居住地址',
  `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `gender` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '性别(0代表男,1代表女)',
  `head_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机头像',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `occ_version` int NULL DEFAULT 0 COMMENT '乐观锁并发控制',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
