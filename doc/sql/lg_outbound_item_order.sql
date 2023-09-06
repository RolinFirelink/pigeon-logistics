/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 03/12/2023
*/

SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lg_outbound_item_order
-- ----------------------------
DROP TABLE IF EXISTS `lg_outbound_item_order`;
CREATE TABLE `lg_outbound_item_order`
(
    `id`                  bigint                                                        NOT NULL COMMENT '出库表明细表id',
    `outbound_order_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '出库单编号',
    `code`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '商品编号',
    `name`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品全名',
    `specifications`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
    `unit`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
    `count`               double                                                        NULL DEFAULT NULL COMMENT '数量',
    `price`               varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '单价：单位数量的价格',
    `sign_img_url`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '签名图片地址',
    `remark`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `deleted`             tinyint                                                       NULL DEFAULT 0 COMMENT '逻辑删除',
    `occ_version`         int                                                           NULL DEFAULT 0 COMMENT '乐观锁并发控制',
    `create_time`         datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`         datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `outbound_order_id` (`outbound_order_code`) USING BTREE,
    CONSTRAINT `lg_outbound_item_order_ibfk_1` FOREIGN KEY (`outbound_order_code`) REFERENCES `lg_outbound_order` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

SET
    FOREIGN_KEY_CHECKS = 1;
