/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 16/03/2023 18:43:09
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lg_release_application
-- ----------------------------
DROP TABLE IF EXISTS `lg_release_application`;
CREATE TABLE `lg_release_application`
(
    `id`                       bigint                                                        NOT NULL COMMENT '主键',
    `dept`                     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请部门',
    `date`                     date                                                          NOT NULL COMMENT '申请日期',
    `reason`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请理由',
    `dept_advice`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门建议',
    `dept_advice_sign`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门建议签名',
    `dept_advice_sign_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门建议签名图片地址',
    `dept_advice_date`         date NULL DEFAULT NULL COMMENT '部门签名日期',
    `approval_by`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '批准',
    `drawn_up_by`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '拟定',
    `review_by`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核',
    `deleted`                  tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
    `occ_version`              int NULL DEFAULT 0 COMMENT '乐观锁并发控制',
    `create_time`              datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`              datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
