/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 12/04/2023 22:41:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lg_vehicle_driving_permit
-- ----------------------------
DROP TABLE IF EXISTS `lg_vehicle_driving_permit`;
CREATE TABLE `lg_vehicle_driving_permit`  (
  `id` bigint NOT NULL COMMENT '唯一主键',
  `vehicle_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '号牌号码',
  `vehicle_id` bigint NULL DEFAULT NULL COMMENT '车辆id',
  `vehicle_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆类型',
  `owner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆所属人',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属人地址',
  `use_nature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '使用性质',
  `brand_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌型号',
  `identification_code` bigint NULL DEFAULT NULL COMMENT '车辆识别代号',
  `engine_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发动机号码',
  `register_time` datetime NULL DEFAULT NULL COMMENT '注册日期',
  `issue_time` datetime NULL DEFAULT NULL COMMENT '发证日期',
  `max_capacity` int NULL DEFAULT NULL COMMENT '核定最大载人数',
  `total_mass` int NULL DEFAULT NULL COMMENT '总质量',
  `maintenance_quality` int NULL DEFAULT NULL COMMENT '整备质量',
  `approved_mass` int NULL DEFAULT NULL COMMENT '核定载质量',
  `dimension_exterior` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外廓尺寸(按照 (长×宽×高)+厘米 的格式存入)\r\n示例: 1000×1000×5000厘米 ',
  `traction_mass` int NULL DEFAULT NULL COMMENT '准牵引总质量',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `inspection_record` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '检验记录\r\n按照以下格式存入 检验有效期至xxxx年xx月+(车牌归属地+第一位车牌号)+(到期月份)\r\n示例: 检验有效期至2018年11月冀J(01) ',
  `picture` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '行驶证照片',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `occ_version` int NULL DEFAULT 0 COMMENT '乐观锁并发控制',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
