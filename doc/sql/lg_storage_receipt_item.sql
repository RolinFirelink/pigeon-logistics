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
-- Table structure for lg_storage_receipt_order_item
-- ----------------------------
DROP TABLE IF EXISTS `lg_storage_receipt_order_item`;
CREATE TABLE `lg_storage_receipt_order_item`
(
    `id`                 bigint  NOT NULL COMMENT '主键ID',
    `storage_receipt_id` bigint  NOT NULL COMMENT '入库单子表主表ID',
    `name`               varchar(255)     DEFAULT NULL COMMENT '商品名称',
    `unit`               varchar(255)     DEFAULT NULL COMMENT '单位',
    `count`              double           DEFAULT NULL COMMENT '数量',
    `price`              varchar(10)      DEFAULT NULL COMMENT '单价：单位数量的价格',
    `amount`             decimal(10, 2)   DEFAULT NULL COMMENT '金额',
    `specifications`     varchar(255)     DEFAULT NULL COMMENT '规格',
    `deleted`            tinyint NOT NULL DEFAULT '0',
    `occ_version`        int     NOT NULL DEFAULT '0' COMMENT '乐观锁',
    `create_time`        datetime         DEFAULT NULL COMMENT '创建时间',
    `remark`             varchar(255)     DEFAULT NULL COMMENT '备注',
    `update_time`        datetime         DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY                  `fk_sto_storageReceiptId` (`storage_receipt_id`),
    CONSTRAINT `fk_sto_storageReceiptId` FOREIGN KEY (`storage_receipt_id`) REFERENCES `logisticsorderentity` (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
