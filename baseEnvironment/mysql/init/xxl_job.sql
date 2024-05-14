/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : xxl_job

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 16/04/2024 21:35:55
*/

CREATE DATABASE `xxl_job`;
USE `xxl_job`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行器名称',
  `address_type` tinyint(0) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
INSERT INTO `xxl_job_group` VALUES (1, 'xxl-job-executor-sample', '示例执行器', 0, NULL, '2022-06-30 10:35:44');
INSERT INTO `xxl_job_group` VALUES (2, 'xxl-job', 'xxl-job', 1, 'http://127.0.0.1:9999', '2022-06-30 10:21:43');

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `job_group` int(0) NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(0) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(0) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '上次调度时间',
  `trigger_next_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '下次调度时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
INSERT INTO `xxl_job_info` VALUES (2, 2, '示例任务', '2022-06-30 10:17:27', '2022-06-30 10:23:00', 'admin', '', 'CRON', '0/3 * * * * ?', 'DO_NOTHING', 'FIRST', 'myJobHandler', '123456', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2022-06-30 10:17:27', '', 0, 0, 0);
INSERT INTO `xxl_job_info` VALUES (3, 2, 'HTTP', '2022-06-30 10:29:49', '2022-06-30 10:33:51', 'admin', '', 'CRON', '0/10 * * * * ?', 'DO_NOTHING', 'FIRST', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2022-06-30 10:29:49', '', 1, 1656556540000, 1656556550000);

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock`  (
  `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
INSERT INTO `xxl_job_lock` VALUES ('schedule_lock');

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `job_group` int(0) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(0) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(0) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(0) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '调度-日志',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(0) NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '执行-日志',
  `alarm_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time`) USING BTREE,
  INDEX `I_handle_code`(`handle_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------
INSERT INTO `xxl_job_log` VALUES (2, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:17:48', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (3, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:17:51', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (4, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:17:54', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (5, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:17:57', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (6, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:18:00', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (7, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:18:03', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (8, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:18:06', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (9, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:18:09', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (10, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:18:12', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (11, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:18:15', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (12, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:18:18', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (13, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:18:21', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (14, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:18:24', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (15, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:20:45', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (16, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:20:48', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (17, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:20:51', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (18, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:20:54', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (19, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:20:57', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (20, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:21:00', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (21, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:21:03', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (22, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:21:06', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (23, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:21:09', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (24, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:21:12', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (25, 2, 2, '127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:21:15', 500, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:9999<br>code：500<br>msg：xxl-job remoting error(no protocol: 127.0.0.1:9999/run), for url : 127.0.0.1:9999/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` VALUES (26, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:24', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:23:04', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (27, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:27', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:23:44', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (28, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:30', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:24:24', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (29, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:33', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:25:04', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (30, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:36', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:25:44', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (31, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:39', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:26:25', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (32, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:42', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:27:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (33, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:45', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:27:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (34, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:48', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:28:25', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (35, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:51', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:29:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (36, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:54', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:29:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (37, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:22:57', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:30:25', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (38, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:23:00', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:31:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (39, 2, 2, 'http://127.0.0.1:9999', 'myJobHandler', '123456', NULL, 0, '2022-06-30 10:23:03', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:31:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (40, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:30:10', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:30:10', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (41, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:30:20', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:30:20', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (42, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:30:30', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:30:30', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (43, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:30:40', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:30:40', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (44, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:30:50', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:30:50', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (45, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:31:00', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:31:00', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (46, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:31:10', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:31:10', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (47, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:31:20', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:31:20', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (48, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:31:30', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:31:30', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (49, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:31:40', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:31:40', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (50, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:31:50', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:31:50', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (51, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:32:00', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:32:00', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (52, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', '', NULL, 0, '2022-06-30 10:32:10', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:32:10', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (53, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:34:00', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:34:00', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (54, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\nmethod: get\\n\n', NULL, 0, '2022-06-30 10:34:04', 200, '任务触发类型：手动触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:34:04', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (55, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:34:10', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:34:10', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (56, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:34:20', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:34:20', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (57, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:34:30', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:34:30', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (58, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:34:40', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:34:40', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (59, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:34:50', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:34:50', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (60, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:35:00', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:35:00', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (61, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:35:10', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:35:10', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (62, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:35:20', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:35:20', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (63, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:35:30', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:35:30', 500, '', 2);
INSERT INTO `xxl_job_log` VALUES (64, 2, 3, 'http://127.0.0.1:9999', 'httpJobHandler', 'url: http://www.baidu.com\\n\r\nmethod: get\\n\r\n', NULL, 0, '2022-06-30 10:35:40', 200, '任务触发类型：Cron触发<br>调度机器：169.254.157.115<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999<br>code：200<br>msg：null', '2022-06-30 10:35:40', 500, '', 2);

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(0) NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
  `suc_count` int(0) NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
  `fail_count` int(0) NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_trigger_day`(`trigger_day`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
INSERT INTO `xxl_job_log_report` VALUES (1, '2022-06-29 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` VALUES (2, '2022-06-28 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` VALUES (3, '2022-06-27 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` VALUES (4, '2022-06-30 00:00:00', 0, 14, 46, NULL);

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `job_id` int(0) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_logglue
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `i_g_k_v`(`registry_group`, `registry_key`, `registry_value`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `role` tinyint(0) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
INSERT INTO `xxl_job_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
