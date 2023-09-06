/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 03/07/2023 10:38:22
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lg_storage_receipt_order
-- ----------------------------
DROP TABLE IF EXISTS `lg_storage_receipt_order`;
CREATE TABLE `lg_storage_receipt_order`
(
    `id`                  bigint  NOT NULL COMMENT '主键ID',
    `code`                varchar(64)      DEFAULT NULL COMMENT '入库单号',
    `plan_id`             bigint           DEFAULT NULL COMMENT '计划ID',
    `warehouse_id`        bigint           DEFAULT NULL COMMENT '仓库ID',
    `warehouse_name`      varchar(255)     DEFAULT NULL COMMENT '仓库名称\n',
    `edit_date`           datetime         DEFAULT NULL COMMENT '录单日期',
    `sender_id`           bigint           DEFAULT NULL COMMENT '发货人ID',
    `sender_name`         varchar(64)      DEFAULT NULL COMMENT '发货人姓名',
    `sender_phone`        varchar(32)      DEFAULT NULL COMMENT '发货人手机',
    `sender_address`      varchar(255)     DEFAULT NULL COMMENT '发货人详细地址',
    `sender_address_code` varchar(255)     DEFAULT NULL COMMENT '四级地址编码',
    `remark`              varchar(255)     DEFAULT NULL COMMENT '备注，客户须知（收货须知）',
    `total_amount`        decimal(10, 2)   DEFAULT NULL COMMENT '总金额\n',
    `receipt_handler`     varchar(64)      DEFAULT NULL COMMENT '收货单位及经手人',
    `send_handler`        varchar(64)      DEFAULT NULL COMMENT '送货单位及经手人',
    `deleted`             tinyint NOT NULL DEFAULT '0',
    `occ_version`         int              DEFAULT '0' COMMENT '乐观锁',
    `create_time`         datetime         DEFAULT NULL COMMENT '创建时间',
    `update_time`         datetime         DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
