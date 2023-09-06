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
-- Table structure for lg_outbound_order
-- ----------------------------
DROP TABLE IF EXISTS `lg_outbound_order`;
CREATE TABLE `lg_outbound_order`
(
    `id`                        bigint NOT NULL,
    `code`                      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单据编号',
    `delivering_warehouse_id`   bigint NULL DEFAULT NULL COMMENT '发货仓库ID',
    `delivering_warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货仓库名称',
    `customer_name`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称/提货部门',
    `contract`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
    `contract_phone`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
    `contract_address`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系地址',
    `summary`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '摘要',
    `total_amount`              decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
    `editor`                    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '制单人',
    `handler`                   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '经手人',
    `edit_date`                 datetime NULL DEFAULT NULL COMMENT '录单日期',
    `remark`                    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注，客户须知（收货须知）',
    `deleted`                   tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
    `occ_version`               int NULL DEFAULT 0 COMMENT '乐观锁并发控制',
    `create_time`               datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`               datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
