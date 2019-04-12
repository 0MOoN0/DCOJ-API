/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : dcoj

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 12/04/2019 19:41:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment`  (
  `aid` int(11) NOT NULL COMMENT '附件对象ID',
  `uid` int(11) NOT NULL COMMENT '对应用户ID',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应url',
  `status` tinyint(4) NOT NULL COMMENT '0 失效 待删除 1 有效',
  `upload_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for examination
-- ----------------------------
DROP TABLE IF EXISTS `examination`;
CREATE TABLE `examination`  (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NULL DEFAULT NULL COMMENT '0练习型 1 考试型',
  `class_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` json NULL,
  `difficult` int(255) NULL DEFAULT NULL,
  `submit_times` int(11) NULL DEFAULT 0,
  `ac_times` int(11) NULL DEFAULT 0,
  `wa_times` int(11) NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_format` json NULL,
  `output_format` json NULL,
  `samples` json NULL,
  `run_time` int(11) NULL DEFAULT NULL,
  `memory` int(255) NULL DEFAULT NULL,
  `used_times` int(11) NULL DEFAULT 0,
  `rte_times` int(11) NULL DEFAULT 0,
  `tle_times` int(11) NULL DEFAULT 0,
  `ce_times` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (3, '0', '{\"des\": \"这是一道选择题\"}', 1, 0, 0, 0, '2019-04-02 08:04:23', '2019-04-02 08:04:23', 0, 'A', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `problem` VALUES (4, '0', '{\"des\": \"这是一道选择题2\"}', 2, 0, 0, 0, '2019-04-02 08:04:24', '2019-04-02 08:04:24', 0, 'B', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `problem` VALUES (5, '3', NULL, 2, 0, 0, 0, '2019-04-02 08:04:24', '2019-04-02 08:04:24', 0, NULL, '编程题目标题', NULL, NULL, NULL, 1, 1, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for problem_user
-- ----------------------------
DROP TABLE IF EXISTS `problem_user`;
CREATE TABLE `problem_user`  (
  `pid` int(11) NOT NULL COMMENT '题目ID',
  `uid` int(11) NOT NULL COMMENT '题目ID',
  `status` enum('SE','CE','TLE','RE','WA','AC') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '判卷结果',
  `gmt_create` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modify` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_user
-- ----------------------------
INSERT INTO `problem_user` VALUES (3, 0, 'AC', '2019-04-03 16:07:59', '2019-04-03 16:36:54');

-- ----------------------------
-- Table structure for submission
-- ----------------------------
DROP TABLE IF EXISTS `submission`;
CREATE TABLE `submission`  (
  `sub_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(10) UNSIGNED NOT NULL COMMENT '用户ID',
  `pid` int(10) UNSIGNED NOT NULL COMMENT '题目ID',
  `source_code` int(10) NOT NULL COMMENT '附件ID',
  `using_time` decimal(5, 3) NOT NULL COMMENT '判卷所用的平均时间',
  `memory` int(11) NOT NULL,
  `status` enum('SE','CE','TLE','RE','WA','AC') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `submit_time` datetime(0) NOT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modify` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `lang` enum('JAVA8','CPP','C','PYTHON27','PYTHON35') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `eid` int(11) NULL DEFAULT NULL COMMENT '试卷ID',
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submission
-- ----------------------------
INSERT INTO `submission` VALUES (3, 123, 0, 123, 3.000, 123, 'AC', '2019-04-02 10:18:32', '2019-04-02 09:45:41', '2019-04-02 10:18:34', 'PYTHON35', NULL);
INSERT INTO `submission` VALUES (8, 123, 0, 123, 3.000, 123, 'SE', '2019-04-02 10:28:03', '2019-04-02 10:28:03', '2019-04-02 10:28:03', 'JAVA8', NULL);
INSERT INTO `submission` VALUES (9, 123, 0, 123, 3.000, 123, 'SE', '2019-04-03 14:03:32', '2019-04-03 14:03:32', '2019-04-03 14:03:32', 'JAVA8', NULL);
INSERT INTO `submission` VALUES (10, 0, 1, 11, 3.000, 123, 'AC', '2019-04-03 14:16:09', '2019-04-03 14:16:09', '2019-04-03 14:16:09', 'PYTHON27', NULL);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `used_times` int(11) NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag_problem
-- ----------------------------
DROP TABLE IF EXISTS `tag_problem`;
CREATE TABLE `tag_problem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `tid` int(255) NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_case
-- ----------------------------
DROP TABLE IF EXISTS `test_case`;
CREATE TABLE `test_case`  (
  `tc_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '测试用例ID',
  `pid` int(11) NOT NULL COMMENT '题目ID',
  `stdin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stdout` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modify` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`tc_id`) USING BTREE,
  INDEX `tc_id`(`tc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_case
-- ----------------------------
INSERT INTO `test_case` VALUES (1, 10, NULL, 'hll', '2019-04-10 17:17:57', '2019-04-10 20:05:30');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` int(255) NULL DEFAULT NULL,
  `motto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(255) NULL DEFAULT NULL,
  `submit_times` int(10) UNSIGNED NULL DEFAULT 0,
  `ac_times` int(11) NULL DEFAULT 0,
  `wa_times` int(11) NULL DEFAULT 0,
  `rte_times` int(11) NULL DEFAULT 0,
  `tle_times` int(11) NULL DEFAULT 0,
  `cet_times` int(11) NULL DEFAULT 0,
  `finished_problems` int(255) NULL DEFAULT 0,
  `verified` int(255) NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
