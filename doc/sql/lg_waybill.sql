/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 3/12/2023
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lg_waybill
-- ----------------------------
DROP TABLE IF EXISTS `lg_waybill`;
CREATE TABLE `lg_waybill`
(
    `id`                    bigint                                                       NOT NULL COMMENT '主键',
    `code`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '运单号',
    `logistics_order_id`    bigint NULL DEFAULT NULL COMMENT '物流订单ID',
    `logistics_order_code`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物流订单编号',
    `sku`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SKU',
    `qa_id`                 bigint NULL DEFAULT NULL COMMENT '质检员ID',
    `de_id`                 bigint NULL DEFAULT NULL COMMENT '发货员ID',
    `price`                 decimal(10, 2) NULL DEFAULT NULL COMMENT '运费',
    `address_id`            bigint NULL DEFAULT NULL COMMENT '地址ID',
    `warehouse_id`          bigint NULL DEFAULT NULL COMMENT '仓库ID',
    `ecp`                   int NULL DEFAULT NULL COMMENT '快递公司编号',
    `receiver_id`           bigint NULL DEFAULT NULL COMMENT '收货人ID',
    `receiver_name`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人姓名',
    `receiver_phone`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人手机',
    `receiver_email`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人电子邮箱',
    `receiver_province`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人省份',
    `receiver_city`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人城市',
    `receiver_district`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人地区',
    `receiver_street`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人街道',
    `receiver_address`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人详细地址',
    `receiver_address_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人四级地址编码',
    `sender_id`             bigint NULL DEFAULT NULL COMMENT '发货人ID',
    `sender_name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货人姓名',
    `sender_phone`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货人手机',
    `sender_email`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货人电子邮箱',
    `sender_province`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货人省份',
    `sender_city`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货人城市',
    `sender_district`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货人地区',
    `sender_street`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货人街道',
    `sender_address`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货人详细地址',
    `sender_address_code`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货人四级地址编码',
    `status`                int NULL DEFAULT NULL COMMENT '状态',
    `logs`                  json DEFAULT NULL COMMENT '物流日志',
    `receipt_time`          datetime NULL DEFAULT NULL COMMENT '签收时间',
    `deleted`               tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
    `occ_version`           int NULL DEFAULT 0 COMMENT '乐观锁并发控制',
    `create_time`           datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`           datetime NULL DEFAULT NULL COMMENT '更新时间',
    `remark`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
