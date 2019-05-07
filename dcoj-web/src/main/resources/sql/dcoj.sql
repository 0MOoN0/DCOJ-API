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

 Date: 07/05/2019 12:11:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件对象ID',
  `uid` int(11) NOT NULL COMMENT '对应用户ID',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应url',
  `status` tinyint(4) NOT NULL COMMENT '0 失效 待删除 1 有效',
  `upload_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attachment
-- ----------------------------
INSERT INTO `attachment` VALUES (2, 0, 'XXX/XXX/XXX', 0, '2019-04-26 11:01:46', '2019-04-26 11:01:46', '2019-04-26 11:01:46');

-- ----------------------------
-- Table structure for examination
-- ----------------------------
DROP TABLE IF EXISTS `examination`;
CREATE TABLE `examination`  (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NULL DEFAULT NULL COMMENT '0练习型 1 考试型',
  `class_id` int(11) NULL DEFAULT NULL,
  `status` enum('editing','published','unavailable') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编辑、发布、不可用',
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for object_problem
-- ----------------------------
DROP TABLE IF EXISTS `object_problem`;
CREATE TABLE `object_problem`  (
  `object_problem_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题目类型 0 选择题 1 填空题 2 判断题',
  `description` json NULL COMMENT '题目描述',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答案',
  `status` int(255) NULL DEFAULT 0 COMMENT '0未发布1已发布',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0,
  PRIMARY KEY (`object_problem_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of object_problem
-- ----------------------------
INSERT INTO `object_problem` VALUES (1, '0', '{\"des\": \" 下列选项中，用于在定义子类时声明父类名的关键字是：( ) \", \"opt1\": \"interface\", \"opt2\": \"package\", \"opt3\": \"extends\", \"opt4\": \"class\"}', 'C', 0, '2019-04-22 21:04:05', '2019-04-24 15:35:07', 1);
INSERT INTO `object_problem` VALUES (2, '0', '{\"des\": \"String str1 = “abc”，“abc”分配在内存哪个区域？ \", \"opt1\": \"堆\", \"opt2\": \"栈\", \"opt3\": \"字符串常量区\", \"opt4\": \"寄存器\"}', 'C', 0, '2019-04-22 21:04:05', '2019-04-24 15:54:39', 0);
INSERT INTO `object_problem` VALUES (3, '1', '{\"des\": \"<div class=\\\"question-main\\\"><span>下列程序段的输出结果是：（</span> <span>）</span> <span> <br> public void complicatedexpression_r(){<br> &nbsp;&nbsp;&nbsp;&nbsp;int x=20, y=30;<br> &nbsp;&nbsp;&nbsp;&nbsp;boolean   b;<br> &nbsp;&nbsp;&nbsp;&nbsp;b = x &gt; 50 &amp;&amp; y &gt; 60 || x &gt; 50 &amp;&amp; y &lt; -60 || x &lt; -50 &amp;&amp; y &gt; 60 || x &lt; -50 &amp;&amp; y &lt; -60;<br> &nbsp;&nbsp;&nbsp;&nbsp;System.out.println(b);<br> }<br> </span></div>\"}', 'false', 0, '2019-04-22 21:04:05', '2019-04-23 18:21:19', 0);
INSERT INTO `object_problem` VALUES (4, '1', '{\"des\": \"局部内部类可以用private、abstract、_____ 修饰符修饰\"}', 'final', 0, '2019-04-22 21:04:05', '2019-04-23 18:21:16', 0);
INSERT INTO `object_problem` VALUES (5, '2', '{\"des\": \"如果a.equals(b)返回true，那么a,b两个对象的hashcode必须相同\"}', 'true', 0, '2019-04-22 21:04:05', '2019-04-22 21:55:22', 0);
INSERT INTO `object_problem` VALUES (6, '2', '{\"des\": \"数组越界可以引发异常\"}', 'true', 0, '2019-04-22 21:04:05', '2019-04-22 21:55:25', 0);
INSERT INTO `object_problem` VALUES (7, '0', '{\"des\": \"测试选择题\", \"opt1\": \"A\", \"opt2\": \"B\", \"opt3\": \"C\", \"opt4\": \"D\"}', 'string', 0, '2019-04-24 16:04:47', '2019-04-24 16:04:47', 0);

-- ----------------------------
-- Table structure for object_problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `object_problem_tag`;
CREATE TABLE `object_problem_tag`  (
  `object_problem_id` int(11) NOT NULL,
  `object_tag_id` int(11) NOT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of object_problem_tag
-- ----------------------------
INSERT INTO `object_problem_tag` VALUES (1, 1, '2019-04-23 10:04:56', '2019-04-24 15:34:04', 1);
INSERT INTO `object_problem_tag` VALUES (2, 1, '2019-04-23 10:04:56', '2019-04-24 15:54:27', 0);
INSERT INTO `object_problem_tag` VALUES (3, 1, '2019-04-23 10:04:56', '2019-04-23 10:04:56', 0);
INSERT INTO `object_problem_tag` VALUES (4, 1, '2019-04-23 10:04:56', '2019-04-23 10:04:56', 0);
INSERT INTO `object_problem_tag` VALUES (5, 1, '2019-04-23 10:04:56', '2019-04-23 10:04:56', 0);
INSERT INTO `object_problem_tag` VALUES (6, 1, '2019-04-23 10:04:56', '2019-04-23 10:04:56', 0);
INSERT INTO `object_problem_tag` VALUES (2, 7, '2019-04-23 10:04:14', '2019-04-24 15:54:30', 0);

-- ----------------------------
-- Table structure for object_submission
-- ----------------------------
DROP TABLE IF EXISTS `object_submission`;
CREATE TABLE `object_submission`  (
  `object_submit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客观题提交ID',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `object_problem_id` int(11) NOT NULL COMMENT '客观题题目ID',
  `result_status` tinyint(255) NOT NULL COMMENT '提交结果',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的提交答案',
  `queryable_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '可查询时间',
  PRIMARY KEY (`object_submit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of object_submission
-- ----------------------------
INSERT INTO `object_submission` VALUES (2, 2, 1, 1, '2019-04-30 09:34:47', '2019-04-30 09:34:47', 'C', '2019-04-30 10:27:12');
INSERT INTO `object_submission` VALUES (3, 2, 2, 1, '2019-04-30 09:36:43', '2019-04-30 09:36:43', 'C', '2019-04-30 10:27:12');
INSERT INTO `object_submission` VALUES (4, 2, 2, 1, '2019-04-30 09:40:43', '2019-04-30 09:40:43', 'C', '2019-04-30 10:27:12');

-- ----------------------------
-- Table structure for object_tag
-- ----------------------------
DROP TABLE IF EXISTS `object_tag`;
CREATE TABLE `object_tag`  (
  `object_tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `used_times` int(11) NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0,
  PRIMARY KEY (`object_tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of object_tag
-- ----------------------------
INSERT INTO `object_tag` VALUES (1, 'Java', -1, '2019-04-21 22:04:48', '2019-04-24 15:53:16', 0);
INSERT INTO `object_tag` VALUES (2, 'Python', 0, '2019-04-21 22:04:48', '2019-04-21 22:17:18', 0);
INSERT INTO `object_tag` VALUES (3, 'C/C++', 0, '2019-04-21 22:04:48', '2019-04-21 22:04:48', 0);
INSERT INTO `object_tag` VALUES (4, '继承', 0, '2019-04-21 22:04:48', '2019-04-24 15:29:42', 0);
INSERT INTO `object_tag` VALUES (5, '封装', 0, '2019-04-21 22:04:48', '2019-04-21 22:09:14', 0);
INSERT INTO `object_tag` VALUES (6, '实现', 0, '2019-04-21 22:04:48', '2019-04-21 22:09:22', 0);
INSERT INTO `object_tag` VALUES (7, '字符串', -1, '2019-04-22 20:45:30', '2019-04-24 15:53:16', 0);
INSERT INTO `object_tag` VALUES (8, '多态', 0, '2019-04-24 10:04:52', '2019-04-24 10:04:52', 0);

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
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `status` enum('SE','CE','TLE','RE','WA','AC') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '判卷结果',
  `gmt_create` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_user
-- ----------------------------
INSERT INTO `problem_user` VALUES (10, 1, 'AC', '2019-04-03 16:07:59', '2019-04-26 15:26:36');
INSERT INTO `problem_user` VALUES (0, 0, 'AC', '2019-04-26 10:24:57', '2019-04-26 10:24:58');

-- ----------------------------
-- Table structure for program_problem
-- ----------------------------
DROP TABLE IF EXISTS `program_problem`;
CREATE TABLE `program_problem`  (
  `program_problem_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` json NULL,
  `difficult` int(255) NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT 0 COMMENT '0未发布1已发布',
  `submit_times` int(11) NULL DEFAULT 0,
  `ac_times` int(11) NULL DEFAULT 0,
  `wa_times` int(11) NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0,
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
  PRIMARY KEY (`program_problem_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of program_problem
-- ----------------------------
INSERT INTO `program_problem` VALUES (1, '{\"des\": \"这是一道选择题XXX\"}', 2, 0, 0, 0, 0, '2019-03-19 19:03:47', '2019-04-26 10:27:15', 0, '修改后的title', NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (2, '{\"des\": \"这是一道选择题YYY\"}', NULL, 0, 0, 0, 0, '2019-03-19 19:03:44', '2019-04-13 17:09:24', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (3, '{\"des\": \"这是一道选择题2\"}', 2, 0, 0, 0, 0, '2019-03-19 19:03:44', '2019-03-19 22:13:44', 0, '编程题目标题', NULL, NULL, NULL, 2, 1, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (4, NULL, NULL, 0, 0, 0, 0, '2019-03-19 19:03:44', '2019-03-19 19:45:38', 1, '编程题目标题', NULL, NULL, NULL, 1, 1, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (5, '{\"des\": \"这是一道选择题\"}', NULL, 0, 0, 0, 0, '2019-03-19 19:03:31', '2019-03-19 19:03:31', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (6, '{\"des\": \"这是一道选择题2\"}', NULL, 0, 0, 0, 0, '2019-03-19 19:03:32', '2019-03-19 19:03:32', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (7, NULL, NULL, 0, 0, 0, 0, '2019-03-19 19:03:32', '2019-03-19 19:03:32', 0, '编程题目标题', NULL, NULL, NULL, 1, 1, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (8, '{\"des\": \"这是一道选择题\"}', 1, 0, 0, 0, 0, '2019-03-19 22:03:30', '2019-04-26 16:27:22', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (9, '{\"additionalProp1\": {\"des\": \"这是一道选择题999\"}}', 0, 0, 0, 0, 0, '2019-03-19 22:03:30', '2019-04-13 18:11:38', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (10, NULL, 2, 1, 0, 0, 0, '2019-03-19 22:03:30', '2019-04-26 11:07:17', 0, '编程题目标题', NULL, NULL, NULL, 1, 1, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (11, NULL, 2, 1, 0, 0, 0, '2019-03-30 20:03:25', '2019-04-26 11:07:19', 0, '编程题目标题', NULL, NULL, NULL, 2, 1, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (12, NULL, 2, 0, 0, 0, 0, '2019-04-03 22:04:00', '2019-04-03 22:04:00', 0, '编程题目标题', NULL, NULL, NULL, 2, 1, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (13, NULL, 2, 0, 0, 0, 0, '2019-04-13 15:04:01', '2019-04-13 17:27:15', 0, '编程题目标题', NULL, NULL, NULL, 2, 1, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (14, '{\"additionalProp1\": {\"des\": \"这是一道填空题\"}}', 0, 0, 0, 0, 0, '2019-04-13 19:04:13', '2019-04-13 19:04:13', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (15, '{\"additionalProp1\": {\"des\": \"这是一道填空题\"}}', 0, 0, 0, 0, 0, '2019-04-13 19:04:28', '2019-04-13 19:04:28', 0, '测试编程题', '{\"additionalProp1\": {}, \"additionalProp2\": {}, \"additionalProp3\": {}}', '{\"additionalProp1\": {}, \"additionalProp2\": {}, \"additionalProp3\": {}}', '[{}]', 1, 1, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (16, '{\"additionalProp1\": {\"des\": \"这是一道填空题\"}}', 0, 0, 0, 0, 0, '2019-04-13 23:04:05', '2019-04-13 23:04:05', 0, '测试编程题2', '{\"additionalProp1\": {}, \"additionalProp2\": {}, \"additionalProp3\": {}}', '{\"additionalProp1\": {}, \"additionalProp2\": {}, \"additionalProp3\": {}}', '[{\"input\": \"AAA\", \"output\": \"AAA\"}]', 1, 1, 0, 0, 0, 0);
INSERT INTO `program_problem` VALUES (20, '{\"insert\": \"二叉树可以用于排序。其原理很简单：对于一个排序二叉树添加新节点时，先与根节点比较，若小则交给左子树继续处理，否则交给右子树。\\n当遇到空子树时，则把该节点放入那个位置。 \\n比如，10 8 5 7 12 4 的输入顺序，应该建成二叉树如下图所示，其中.表示空白。\\n...|-12\\n 10-|\\n ...|-8-|\\n .......|...|-7\\n .......|-5-|\\n ...........|-4 \\n本题目要求：根据已知的数字，建立排序二叉树，并在标准输出中横向打印该二叉树。\\n\"}', 0, 0, 0, 0, 0, '2019-04-26 17:04:01', '2019-04-26 17:20:21', 0, '横向打印二叉树123', '{\"insert\": \"输入数据为一行空格分开的N个整数。 N<100，每个数字不超过10000。\\n输入数据中没有重复的数字。\\n\"}', '{\"insert\": \"输出该排序二叉树的横向表示。为了便于评卷程序比对空格的数目，请把空格用句点代替：\\n\"}', '[{\"input\": \"10 5 20 \", \"output\": \"...|-20\\n10-|\\n...|-5 \\n\"}]', 1, 128, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for program_problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `program_problem_tag`;
CREATE TABLE `program_problem_tag`  (
  `program_problem_id` int(11) NULL DEFAULT NULL,
  `program_tag_id` int(255) NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of program_problem_tag
-- ----------------------------
INSERT INTO `program_problem_tag` VALUES (1, 1, '2019-03-20 10:03:37', '2019-03-30 18:15:41', 1);
INSERT INTO `program_problem_tag` VALUES (1, 1, '2019-03-20 10:03:04', '2019-03-30 19:05:27', 1);
INSERT INTO `program_problem_tag` VALUES (1, 2, '2019-03-20 10:03:04', '2019-03-30 19:05:27', 1);
INSERT INTO `program_problem_tag` VALUES (1, 3, '2019-03-20 10:03:04', '2019-03-30 19:05:27', 1);
INSERT INTO `program_problem_tag` VALUES (1, 4, '2019-03-20 10:03:04', '2019-03-30 19:05:27', 1);
INSERT INTO `program_problem_tag` VALUES (1, 5, '2019-03-20 10:03:04', '2019-03-30 19:05:27', 1);
INSERT INTO `program_problem_tag` VALUES (2, 1, '2019-03-20 10:03:04', '2019-03-20 10:03:04', 0);
INSERT INTO `program_problem_tag` VALUES (2, 2, '2019-03-20 10:03:04', '2019-03-20 10:03:04', 0);
INSERT INTO `program_problem_tag` VALUES (2, 3, '2019-03-20 10:03:04', '2019-03-20 10:03:04', 0);
INSERT INTO `program_problem_tag` VALUES (2, 4, '2019-03-20 10:03:04', '2019-03-20 10:03:04', 0);
INSERT INTO `program_problem_tag` VALUES (2, 5, '2019-03-20 10:03:04', '2019-03-20 10:03:04', 0);
INSERT INTO `program_problem_tag` VALUES (1, 1, '2019-03-30 19:03:27', '2019-04-26 10:27:00', 0);
INSERT INTO `program_problem_tag` VALUES (1, 3, '2019-03-30 19:03:27', '2019-04-26 10:27:01', 0);
INSERT INTO `program_problem_tag` VALUES (1, 6, '2019-03-30 19:03:27', '2019-04-26 10:27:03', 0);
INSERT INTO `program_problem_tag` VALUES (1, 7, '2019-03-30 19:03:27', '2019-04-26 10:27:04', 0);
INSERT INTO `program_problem_tag` VALUES (1, 8, '2019-03-30 19:03:27', '2019-04-26 10:27:06', 0);
INSERT INTO `program_problem_tag` VALUES (11, 1, '2019-03-30 20:03:25', '2019-03-30 20:03:25', 0);
INSERT INTO `program_problem_tag` VALUES (11, 3, '2019-03-30 20:03:25', '2019-03-30 20:03:25', 0);
INSERT INTO `program_problem_tag` VALUES (11, 6, '2019-03-30 20:03:25', '2019-03-30 20:03:25', 0);
INSERT INTO `program_problem_tag` VALUES (11, 7, '2019-03-30 20:03:25', '2019-03-30 20:03:25', 0);
INSERT INTO `program_problem_tag` VALUES (11, 8, '2019-03-30 20:03:25', '2019-03-30 20:03:25', 0);
INSERT INTO `program_problem_tag` VALUES (12, 1, '2019-04-03 22:04:00', '2019-04-03 22:04:00', 0);
INSERT INTO `program_problem_tag` VALUES (12, 3, '2019-04-03 22:04:00', '2019-04-03 22:04:00', 0);
INSERT INTO `program_problem_tag` VALUES (12, 6, '2019-04-03 22:04:00', '2019-04-03 22:04:00', 0);
INSERT INTO `program_problem_tag` VALUES (12, 7, '2019-04-03 22:04:00', '2019-04-03 22:04:00', 0);
INSERT INTO `program_problem_tag` VALUES (12, 8, '2019-04-03 22:04:00', '2019-04-03 22:04:00', 0);
INSERT INTO `program_problem_tag` VALUES (13, 1, '2019-04-13 15:04:01', '2019-04-13 17:27:23', 0);
INSERT INTO `program_problem_tag` VALUES (13, 2, '2019-04-13 15:04:01', '2019-04-13 17:27:26', 0);
INSERT INTO `program_problem_tag` VALUES (9, 1, '2019-04-13 18:11:17', '2019-04-13 18:12:54', 0);
INSERT INTO `program_problem_tag` VALUES (9, 1, '2019-04-13 18:11:17', '2019-04-13 18:11:38', 1);
INSERT INTO `program_problem_tag` VALUES (9, 1, '2019-04-13 18:04:38', '2019-04-13 18:12:08', 1);
INSERT INTO `program_problem_tag` VALUES (9, 2, '2019-04-13 18:04:38', '2019-04-13 18:12:08', 1);
INSERT INTO `program_problem_tag` VALUES (9, 1, '2019-04-13 18:04:08', '2019-04-13 18:12:34', 1);
INSERT INTO `program_problem_tag` VALUES (9, 2, '2019-04-13 18:04:08', '2019-04-13 18:12:37', 1);
INSERT INTO `program_problem_tag` VALUES (14, 9, '2019-04-13 19:04:14', '2019-04-13 19:04:14', 0);
INSERT INTO `program_problem_tag` VALUES (14, 10, '2019-04-13 19:04:14', '2019-04-13 19:04:14', 0);
INSERT INTO `program_problem_tag` VALUES (15, 1, '2019-04-13 19:04:28', '2019-04-13 19:04:28', 0);
INSERT INTO `program_problem_tag` VALUES (16, 9, '2019-04-13 23:04:05', '2019-04-13 23:04:05', 0);
INSERT INTO `program_problem_tag` VALUES (20, 12, '2019-04-26 17:04:02', '2019-04-26 17:20:21', 1);
INSERT INTO `program_problem_tag` VALUES (20, 13, '2019-04-26 17:04:02', '2019-04-26 17:20:21', 1);
INSERT INTO `program_problem_tag` VALUES (20, 12, '2019-04-26 17:04:21', '2019-04-26 17:23:17', 1);
INSERT INTO `program_problem_tag` VALUES (20, 13, '2019-04-26 17:04:21', '2019-04-26 17:23:17', 1);
INSERT INTO `program_problem_tag` VALUES (20, 12, '2019-04-26 17:04:17', '2019-04-26 17:04:17', 0);
INSERT INTO `program_problem_tag` VALUES (20, 13, '2019-04-26 17:04:17', '2019-04-26 17:04:17', 0);

-- ----------------------------
-- Table structure for program_submission
-- ----------------------------
DROP TABLE IF EXISTS `program_submission`;
CREATE TABLE `program_submission`  (
  `sub_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(10) UNSIGNED NOT NULL COMMENT '用户ID',
  `pid` int(10) UNSIGNED NOT NULL COMMENT '题目ID',
  `source_code` int(10) NOT NULL COMMENT '附件ID',
  `using_time` decimal(5, 3) NOT NULL COMMENT '判卷所用的平均时间',
  `memory` int(11) NOT NULL,
  `status` enum('SE','CE','TLE','RE','WA','AC') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `submit_time` datetime(0) NOT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `lang` enum('JAVA8','CPP','C','PYTHON27','PYTHON35') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `eid` int(11) NOT NULL DEFAULT 0 COMMENT '试卷ID',
  `gid` int(11) NOT NULL DEFAULT 0 COMMENT '用户组ID',
  `queryable_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '用户可查询此提交的时间',
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of program_submission
-- ----------------------------
INSERT INTO `program_submission` VALUES (14, 2, 10, 2, 3.000, 123, 'AC', '2019-04-22 19:47:10', '2019-04-22 19:47:10', '2019-04-26 15:12:34', 'PYTHON27', 11, 0, '2019-04-22 19:47:10');
INSERT INTO `program_submission` VALUES (15, 2, 1, 0, 3.000, 123, 'AC', '2019-04-22 19:47:12', '2019-04-22 19:47:12', '2019-04-22 19:47:12', 'PYTHON27', 11, 0, '2019-04-22 19:47:12');
INSERT INTO `program_submission` VALUES (16, 2, 1, 0, 3.000, 123, 'AC', '2019-04-22 19:47:13', '2019-04-22 19:47:13', '2019-04-22 19:47:13', 'PYTHON27', 11, 0, '2019-04-22 19:47:13');
INSERT INTO `program_submission` VALUES (17, 1, 1, 0, 3.000, 123, 'AC', '2019-04-22 19:48:56', '2019-04-22 19:48:56', '2019-04-22 19:48:56', 'PYTHON27', 11, 0, '2019-04-22 19:48:56');
INSERT INTO `program_submission` VALUES (18, 1, 1, 0, 3.000, 123, 'AC', '2019-04-22 19:48:57', '2019-04-22 19:48:57', '2019-04-22 19:48:57', 'PYTHON27', 11, 0, '2019-04-22 19:48:57');
INSERT INTO `program_submission` VALUES (19, 1, 1, 0, 3.000, 123, 'AC', '2019-04-22 19:48:58', '2019-04-22 19:48:58', '2019-04-22 19:48:58', 'PYTHON27', 11, 0, '2019-04-22 19:48:58');
INSERT INTO `program_submission` VALUES (20, 0, 1, 0, 3.000, 123, 'SE', '2019-04-22 20:49:00', '2019-04-22 20:49:00', '2019-04-22 20:51:10', 'PYTHON27', 11, 0, '2019-04-22 20:49:00');
INSERT INTO `program_submission` VALUES (21, 0, 1, 0, 3.000, 123, 'AC', '2019-04-22 20:49:02', '2019-04-22 20:49:01', '2019-04-22 20:49:01', 'PYTHON27', 11, 0, '2019-04-22 20:49:01');
INSERT INTO `program_submission` VALUES (22, 0, 1, 0, 3.000, 123, 'AC', '2019-04-22 20:49:03', '2019-04-22 20:49:02', '2019-04-22 20:49:02', 'PYTHON27', 11, 0, '2019-04-22 20:49:02');
INSERT INTO `program_submission` VALUES (23, 0, 1, 0, 3.000, 123, 'AC', '2019-04-22 20:49:04', '2019-04-22 20:49:03', '2019-04-22 20:49:03', 'PYTHON27', 11, 0, '2019-04-22 20:49:03');
INSERT INTO `program_submission` VALUES (24, 0, 1, 0, 3.000, 123, 'AC', '2019-04-22 20:49:05', '2019-04-22 20:49:04', '2019-04-22 20:49:04', 'PYTHON27', 11, 0, '2019-04-22 20:49:04');
INSERT INTO `program_submission` VALUES (25, 0, 1, 0, 3.000, 123, 'AC', '2019-04-22 20:49:06', '2019-04-22 20:49:05', '2019-04-22 20:49:05', 'PYTHON27', 11, 0, '2019-04-22 20:49:05');
INSERT INTO `program_submission` VALUES (26, 0, 1, 0, 3.000, 123, 'AC', '2019-04-22 20:49:07', '2019-04-22 20:49:06', '2019-04-22 20:49:06', 'PYTHON27', 11, 0, '2019-04-22 20:49:06');
INSERT INTO `program_submission` VALUES (27, 0, 1, 0, 3.000, 123, 'AC', '2019-04-22 20:49:08', '2019-04-22 20:49:07', '2019-04-22 20:49:07', 'PYTHON27', 11, 0, '2019-04-22 20:49:07');
INSERT INTO `program_submission` VALUES (28, 0, 1, 0, 3.000, 123, 'AC', '2019-04-22 20:49:09', '2019-04-22 20:49:08', '2019-04-22 20:49:08', 'PYTHON27', 11, 0, '2019-04-22 20:49:08');
INSERT INTO `program_submission` VALUES (29, 0, 1, 0, 3.000, 123, 'AC', '2019-04-22 20:49:10', '2019-04-22 20:49:09', '2019-04-22 20:49:09', 'PYTHON27', 11, 0, '2019-04-22 20:49:09');
INSERT INTO `program_submission` VALUES (30, 4, 1, 0, 3.000, 0, 'AC', '2019-04-22 23:03:58', '2019-04-22 23:03:58', '2019-04-22 23:03:58', 'PYTHON27', 11, 0, '2019-04-22 23:03:58');
INSERT INTO `program_submission` VALUES (31, 4, 1, 0, 3.000, 1, 'AC', '2019-04-22 23:03:59', '2019-04-22 23:03:59', '2019-04-22 23:03:59', 'PYTHON27', 11, 0, '2019-04-22 23:03:59');
INSERT INTO `program_submission` VALUES (32, 4, 1, 0, 3.000, 2, 'AC', '2019-04-22 23:04:00', '2019-04-22 23:04:00', '2019-04-22 23:04:00', 'PYTHON27', 11, 0, '2019-04-22 23:04:00');
INSERT INTO `program_submission` VALUES (33, 4, 1, 0, 3.000, 3, 'AC', '2019-04-22 23:04:01', '2019-04-22 23:04:01', '2019-04-22 23:04:01', 'PYTHON27', 11, 0, '2019-04-22 23:04:01');
INSERT INTO `program_submission` VALUES (34, 4, 1, 0, 3.000, 4, 'AC', '2019-04-22 23:04:02', '2019-04-22 23:04:02', '2019-04-22 23:04:02', 'PYTHON27', 11, 0, '2019-04-22 23:04:02');
INSERT INTO `program_submission` VALUES (35, 4, 1, 0, 0.000, 0, 'AC', '2019-04-22 23:12:14', '2019-04-22 23:12:14', '2019-04-22 23:12:14', 'JAVA8', 11, 0, '2019-04-22 23:12:14');
INSERT INTO `program_submission` VALUES (36, 4, 1, 0, 1.000, 1, 'AC', '2019-04-22 23:12:15', '2019-04-22 23:12:15', '2019-04-22 23:12:15', 'JAVA8', 11, 0, '2019-04-22 23:12:15');
INSERT INTO `program_submission` VALUES (37, 4, 1, 0, 2.000, 2, 'AC', '2019-04-22 23:12:16', '2019-04-22 23:12:16', '2019-04-22 23:12:16', 'JAVA8', 11, 0, '2019-04-22 23:12:16');
INSERT INTO `program_submission` VALUES (38, 4, 1, 0, 3.000, 3, 'AC', '2019-04-22 23:12:18', '2019-04-22 23:12:17', '2019-04-22 23:12:17', 'JAVA8', 11, 0, '2019-04-22 23:12:17');
INSERT INTO `program_submission` VALUES (39, 4, 1, 0, 4.000, 4, 'AC', '2019-04-22 23:12:19', '2019-04-22 23:12:18', '2019-04-22 23:12:18', 'JAVA8', 11, 0, '2019-04-22 23:12:18');
INSERT INTO `program_submission` VALUES (40, 5, 1, 0, 0.000, 0, 'AC', '2019-04-22 23:12:43', '2019-04-22 23:12:43', '2019-04-22 23:12:43', 'JAVA8', 11, 0, '2019-04-22 23:12:43');
INSERT INTO `program_submission` VALUES (41, 5, 1, 0, 1.000, 1, 'AC', '2019-04-22 23:12:44', '2019-04-22 23:12:44', '2019-04-22 23:12:44', 'JAVA8', 11, 0, '2019-04-22 23:12:44');
INSERT INTO `program_submission` VALUES (42, 5, 1, 0, 2.000, 2, 'AC', '2019-04-22 23:12:45', '2019-04-22 23:12:45', '2019-04-22 23:12:45', 'JAVA8', 11, 0, '2019-04-22 23:12:45');
INSERT INTO `program_submission` VALUES (43, 5, 1, 0, 3.000, 3, 'AC', '2019-04-22 23:12:46', '2019-04-22 23:12:46', '2019-04-22 23:12:46', 'JAVA8', 11, 0, '2019-04-22 23:12:46');
INSERT INTO `program_submission` VALUES (44, 5, 1, 0, 4.000, 4, 'AC', '2019-04-22 23:12:47', '2019-04-22 23:12:47', '2019-04-22 23:12:47', 'JAVA8', 11, 0, '2019-04-22 23:12:47');
INSERT INTO `program_submission` VALUES (45, 6, 1, 0, 1.000, 1, 'AC', '2019-04-23 16:20:30', '2019-04-23 16:20:33', '2019-04-23 16:20:33', 'C', 11, 0, '2019-04-23 16:20:33');
INSERT INTO `program_submission` VALUES (46, 6, 1, 0, 1.000, 1, 'AC', '2019-04-23 16:24:39', '2019-04-23 16:24:41', '2019-04-23 16:24:41', 'C', 11, 0, '2019-04-23 16:24:41');
INSERT INTO `program_submission` VALUES (47, 6, 1, 0, 1.000, 1, 'AC', '2019-04-23 16:31:36', '2019-04-23 16:31:36', '2019-04-23 16:31:36', 'C', 11, 0, '2019-04-23 16:31:36');
INSERT INTO `program_submission` VALUES (48, 6, 1, 0, 1.000, 1, 'AC', '2019-04-23 16:34:21', '2019-04-23 16:34:21', '2019-04-23 16:34:21', 'C', 11, 0, NULL);
INSERT INTO `program_submission` VALUES (49, 6, 1, 0, 1.000, 1, 'AC', '2019-04-23 16:37:41', '2019-04-23 16:37:42', '2019-04-23 16:37:42', 'C', 11, 0, '2019-04-23 16:37:42');
INSERT INTO `program_submission` VALUES (50, 6, 1, 0, 1.000, 1, 'AC', '2019-04-23 16:40:46', '2019-04-23 16:40:46', '2019-04-23 16:40:46', 'C', 11, 0, '2019-04-23 16:40:46');
INSERT INTO `program_submission` VALUES (51, 6, 1, 0, 1.000, 1, 'AC', '2019-04-23 16:42:05', '2019-04-23 16:42:06', '2019-04-23 16:42:06', 'C', 11, 0, '2019-04-23 16:42:06');
INSERT INTO `program_submission` VALUES (52, 6, 1, 0, 1.000, 1, 'AC', '2019-04-23 16:43:00', '2019-04-23 16:43:00', '2019-04-23 16:43:00', 'C', 11, 0, '2019-04-23 16:43:00');
INSERT INTO `program_submission` VALUES (53, 123, 0, 123, 3.000, 123, 'SE', '2019-04-24 20:44:47', '2019-04-24 20:44:47', '2019-04-24 20:44:47', 'JAVA8', 0, 0, '2019-04-24 20:44:47');
INSERT INTO `program_submission` VALUES (54, 5, 1, 0, 0.000, 0, 'AC', '2019-04-24 20:46:38', '2019-04-24 20:46:37', '2019-04-24 20:46:37', 'JAVA8', 11, 0, '2019-04-24 20:46:37');
INSERT INTO `program_submission` VALUES (55, 5, 1, 0, 1.000, 1, 'AC', '2019-04-24 20:46:39', '2019-04-24 20:46:38', '2019-04-24 20:46:38', 'JAVA8', 11, 0, '2019-04-24 20:46:38');
INSERT INTO `program_submission` VALUES (56, 5, 1, 0, 2.000, 2, 'AC', '2019-04-24 20:46:40', '2019-04-24 20:46:39', '2019-04-24 20:46:39', 'JAVA8', 11, 0, '2019-04-24 20:46:39');
INSERT INTO `program_submission` VALUES (57, 5, 1, 0, 3.000, 3, 'AC', '2019-04-24 20:46:41', '2019-04-24 20:46:40', '2019-04-24 20:46:40', 'JAVA8', 11, 0, '2019-04-24 20:46:40');
INSERT INTO `program_submission` VALUES (58, 5, 1, 0, 4.000, 4, 'AC', '2019-04-24 20:46:42', '2019-04-24 20:46:41', '2019-04-24 20:46:41', 'JAVA8', 11, 0, '2019-04-24 20:46:41');
INSERT INTO `program_submission` VALUES (59, 123, 0, 123, 3.000, 123, 'SE', '2019-04-26 09:48:23', '2019-04-26 09:48:23', '2019-04-26 09:48:23', 'JAVA8', 0, 0, '2019-04-26 09:48:23');
INSERT INTO `program_submission` VALUES (60, 6, 1, 0, 1.000, 1, 'AC', '2019-04-26 09:49:02', '2019-04-26 09:49:02', '2019-04-26 09:49:02', 'CPP', 11, 0, '2019-04-26 09:49:02');
INSERT INTO `program_submission` VALUES (61, 123, 0, 123, 3.000, 123, 'SE', '2019-04-29 20:38:41', '2019-04-29 20:38:42', '2019-04-29 20:38:42', 'JAVA8', 0, 0, '2019-04-29 20:38:42');
INSERT INTO `program_submission` VALUES (62, 123, 0, 123, 3.000, 123, 'SE', '2019-04-29 20:43:14', '2019-04-29 20:43:15', '2019-04-29 20:43:15', 'JAVA8', 0, 0, '2019-04-29 20:43:15');
INSERT INTO `program_submission` VALUES (63, 123, 0, 123, 3.000, 123, 'SE', '2019-04-29 20:44:27', '2019-04-29 20:44:27', '2019-04-29 20:44:27', 'JAVA8', 0, 0, '2019-04-29 20:44:27');
INSERT INTO `program_submission` VALUES (64, 6, 1, 0, 1.000, 1, 'AC', '2019-04-29 20:48:19', '2019-04-29 20:48:19', '2019-04-29 20:48:19', 'CPP', 11, 0, '2019-04-29 20:48:19');
INSERT INTO `program_submission` VALUES (65, 6, 1, 0, 1.000, 1, 'AC', '2019-04-29 22:24:47', '2019-04-29 22:24:46', '2019-04-29 22:24:46', 'CPP', 11, 0, '2019-04-29 22:24:46');

-- ----------------------------
-- Table structure for program_submission_detail
-- ----------------------------
DROP TABLE IF EXISTS `program_submission_detail`;
CREATE TABLE `program_submission_detail`  (
  `sd_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '提交详情的ID',
  `judge_detail` json NOT NULL COMMENT '判卷机返回的提交详情',
  `sub_id` int(11) NOT NULL COMMENT '提交ID',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`sd_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of program_submission_detail
-- ----------------------------
INSERT INTO `program_submission_detail` VALUES (1, '{\"time\": 0.12, \"memory\": 9, \"result\": \"WA\", \"test_cases\": [{\"time\": 0.104, \"memory\": 8.552448, \"result\": \"AC\", \"error_message\": null}, {\"time\": 0.109, \"memory\": 8.572928, \"result\": \"WA\", \"error_message\": null}]}', 14, '2019-04-25 20:29:53', '2019-04-25 21:49:44');

-- ----------------------------
-- Table structure for program_tag
-- ----------------------------
DROP TABLE IF EXISTS `program_tag`;
CREATE TABLE `program_tag`  (
  `program_tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `used_times` int(11) NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0,
  PRIMARY KEY (`program_tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of program_tag
-- ----------------------------
INSERT INTO `program_tag` VALUES (1, '标签111', 2, '2019-03-19 01:44:56', '2019-04-26 10:26:44', 0);
INSERT INTO `program_tag` VALUES (2, '标签222', 1, '2019-03-19 01:44:56', '2019-04-13 18:12:08', 0);
INSERT INTO `program_tag` VALUES (3, '标签333', 2, '2019-03-19 01:44:56', '2019-04-26 10:26:44', 0);
INSERT INTO `program_tag` VALUES (4, '标签444', 0, '2019-03-19 13:03:10', '2019-03-30 19:05:27', 0);
INSERT INTO `program_tag` VALUES (5, '标签555', 0, '2019-03-19 19:03:32', '2019-04-25 00:12:05', 0);
INSERT INTO `program_tag` VALUES (6, '标签666', 2, '2019-03-19 19:03:33', '2019-04-26 10:26:44', 0);
INSERT INTO `program_tag` VALUES (7, '标签777', 2, '2019-03-19 19:03:14', '2019-04-26 10:26:44', 0);
INSERT INTO `program_tag` VALUES (8, '标签777', 2, '2019-03-19 19:03:43', '2019-04-26 10:26:44', 0);
INSERT INTO `program_tag` VALUES (9, '标签777', 2, '2019-03-19 19:03:21', '2019-04-13 23:37:05', 0);
INSERT INTO `program_tag` VALUES (10, '标签888', 1, '2019-03-30 15:03:59', '2019-04-13 19:30:14', 0);
INSERT INTO `program_tag` VALUES (11, '标签999', 0, '2019-04-13 13:04:38', '2019-04-26 10:29:25', 1);
INSERT INTO `program_tag` VALUES (12, '二叉', 1, '2019-04-26 10:04:28', '2019-04-26 17:23:17', 0);
INSERT INTO `program_tag` VALUES (13, '二叉树', 1, '2019-04-26 16:36:13', '2019-04-26 17:23:17', 0);

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
  `stdout` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`tc_id`) USING BTREE,
  INDEX `tc_id`(`tc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_case
-- ----------------------------
INSERT INTO `test_case` VALUES (1, 10, NULL, 'hll', '2019-04-10 17:17:57', '2019-04-10 20:05:30');
INSERT INTO `test_case` VALUES (2, 10, 'null', 'null', '2019-04-25 21:58:00', '2019-04-25 21:58:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `is_deleted` int(255) NULL DEFAULT 0,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '21232F297A57A5A743894A0E4A801FC3', NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, NULL, '2019-04-26 13:04:25', '2019-04-26 13:04:25', 0);

SET FOREIGN_KEY_CHECKS = 1;
