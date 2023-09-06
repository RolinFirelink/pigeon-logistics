/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 16/03/2023 18:43:19
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lg_release_application_item
-- ----------------------------
DROP TABLE IF EXISTS `lg_release_application_item`;
CREATE TABLE `lg_release_application_item`
(
    `id`                          bigint                                                        NOT NULL,
    `release_application_form_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `name`                        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `specifications`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `unit`                        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `count`                       double NULL DEFAULT NULL,
    `price`                       decimal(10, 2) NULL DEFAULT NULL,
    `sign_img_url`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `remark`                      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `deleted`                     tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
    `occ_version`                 int NULL DEFAULT 0 COMMENT '乐观锁并发控制',
    `create_time`                 datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`                 datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
