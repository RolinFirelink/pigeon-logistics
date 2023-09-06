/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 06/04/2023 16:42:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lg_sys_
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` bigint NOT NULL COMMENT '唯一主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点名',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父结点id',
  `sort_id` int NULL DEFAULT NULL COMMENT '排序值',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `first_level_index`(`name` ASC) USING BTREE COMMENT '名称的索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
