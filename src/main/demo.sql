/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 24/07/2020 22:04:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cst_customer
-- ----------------------------
DROP TABLE IF EXISTS `cst_customer`;
CREATE TABLE `cst_customer`  (
  `cust_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `cust_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cust_industry` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cust_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cust_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cust_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cust_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cust_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cst_customer
-- ----------------------------
INSERT INTO `cst_customer` VALUES (1, '诺丁城', NULL, '52', '唐三', '110', NULL);

-- ----------------------------
-- Table structure for cst_linkman
-- ----------------------------
DROP TABLE IF EXISTS `cst_linkman`;
CREATE TABLE `cst_linkman`  (
  `lkm_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `lkm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lkm_gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lkm_memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lkm_mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lkm_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lkm_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lkm_position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lkm_cust_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`lkm_id`) USING BTREE,
  INDEX `FKh9yp1nql5227xxcopuxqx2e7q`(`lkm_cust_id`) USING BTREE,
  CONSTRAINT `FKh9yp1nql5227xxcopuxqx2e7q` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cst_linkman
-- ----------------------------
INSERT INTO `cst_linkman` VALUES (1, 'wer', NULL, NULL, NULL, '与小刚', '119', NULL, 1);
INSERT INTO `cst_linkman` VALUES (2, NULL, NULL, NULL, NULL, '弗兰德', '112', NULL, 1);

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `id` int(0) NOT NULL,
  `dname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `loc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (10, '教研部', '北京');
INSERT INTO `dept` VALUES (20, '学工部', '上海');
INSERT INTO `dept` VALUES (30, '销售部', '广州');
INSERT INTO `dept` VALUES (40, '财务部', '深圳');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `id` int(0) NOT NULL,
  `ename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job_id` int(0) NULL DEFAULT NULL,
  `mgr` int(0) NULL DEFAULT NULL,
  `joindate` date NULL DEFAULT NULL,
  `salary` decimal(7, 2) NULL DEFAULT NULL,
  `bonus` decimal(7, 2) NULL DEFAULT NULL,
  `dept_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `emp_jobid_ref_job_id_fk`(`job_id`) USING BTREE,
  INDEX `emp_deptid_ref_dept_id_fk`(`dept_id`) USING BTREE,
  CONSTRAINT `emp_deptid_ref_dept_id_fk` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `emp_jobid_ref_job_id_fk` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (1001, '孙悟空', 4, 1004, '2000-12-17', 8000.00, NULL, 20);
INSERT INTO `emp` VALUES (1002, '卢俊义', 3, 1006, '2001-02-20', 16000.00, 3000.00, 30);
INSERT INTO `emp` VALUES (1003, '林冲', 3, 1006, '2001-02-22', 12500.00, 5000.00, 30);
INSERT INTO `emp` VALUES (1004, '唐僧', 2, 1009, '2001-04-02', 29750.00, NULL, 20);
INSERT INTO `emp` VALUES (1005, '李逵', 4, 1006, '2001-09-28', 12500.00, 14000.00, 30);
INSERT INTO `emp` VALUES (1006, '宋江', 2, 1009, '2001-05-01', 28500.00, NULL, 30);
INSERT INTO `emp` VALUES (1007, '刘备', 2, 1009, '2001-09-01', 24500.00, NULL, 10);
INSERT INTO `emp` VALUES (1008, '猪八戒', 4, 1004, '2007-04-19', 30000.00, NULL, 20);
INSERT INTO `emp` VALUES (1009, '罗贯中', 1, NULL, '2001-11-17', 50000.00, NULL, 10);
INSERT INTO `emp` VALUES (1010, '吴用', 3, 1006, '2001-09-08', 15000.00, 0.00, 30);
INSERT INTO `emp` VALUES (1011, '沙僧', 4, 1004, '2007-05-23', 11000.00, NULL, 20);
INSERT INTO `emp` VALUES (1012, '李逵', 4, 1006, '2001-12-03', 9500.00, NULL, 30);
INSERT INTO `emp` VALUES (1013, '小白龙', 4, 1004, '2001-12-03', 30000.00, NULL, 20);
INSERT INTO `emp` VALUES (1014, '关羽', 4, 1007, '2002-01-23', 13000.00, NULL, 10);

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` int(0) NOT NULL,
  `jname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES (1, '董事长', '管理整个公司，接单');
INSERT INTO `job` VALUES (2, '经理', '管理部门员工');
INSERT INTO `job` VALUES (3, '销售员', '向客人推销产品');
INSERT INTO `job` VALUES (4, '文员', '使用办公软件');

-- ----------------------------
-- Table structure for salarygrade
-- ----------------------------
DROP TABLE IF EXISTS `salarygrade`;
CREATE TABLE `salarygrade`  (
  `grade` int(0) NOT NULL,
  `losalary` int(0) NULL DEFAULT NULL,
  `hisalary` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`grade`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salarygrade
-- ----------------------------
INSERT INTO `salarygrade` VALUES (1, 7000, 12000);
INSERT INTO `salarygrade` VALUES (2, 12010, 14000);
INSERT INTO `salarygrade` VALUES (3, 14010, 20000);
INSERT INTO `salarygrade` VALUES (4, 20010, 30000);
INSERT INTO `salarygrade` VALUES (5, 30010, 99990);

-- ----------------------------
-- Table structure for school_area
-- ----------------------------
DROP TABLE IF EXISTS `school_area`;
CREATE TABLE `school_area`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(0) NULL DEFAULT NULL COMMENT '父级ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否禁用 0 禁用 1 生效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of school_area
-- ----------------------------
INSERT INTO `school_area` VALUES (1, NULL, '苏州总部', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (3, 38, '无锡校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (4, 38, '南京校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (5, 38, '石路校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (6, 38, '相城校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (7, 37, '温州校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (8, 37, '宁波校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (9, 37, '绍兴校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (10, 37, '嘉兴校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (11, 33, '武侯校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (12, 33, '锦江校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (13, 33, '长沙校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (14, 33, '武汉校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (15, 33, '重庆校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (16, 40, '海淀校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (17, 40, '朝阳校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (18, 40, '郑州校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (19, 36, '静安校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (20, 36, '松江校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (21, 36, '嘉定校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (22, 36, '浦东校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (23, 36, '合肥校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (24, 39, '萧山校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (25, 39, '余杭校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (26, 39, '江干校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (27, 35, '白云校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (28, NULL, '天河校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (29, NULL, '福田校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (30, NULL, '龙岗校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (31, 34, '深圳校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (32, 33, '成都校区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (33, 0, '王大区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (34, 0, '唐大区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (35, 0, '广州', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (36, 0, '谈大区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (37, 0, '姜大区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (38, 0, '邓大区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (39, 0, '常大区', NULL, NULL, '1');
INSERT INTO `school_area` VALUES (40, 0, '宇大区', NULL, NULL, '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (2, '学员');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (3, NULL, '小奥');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `sys_user_id` bigint(0) NOT NULL,
  `sys_role_id` bigint(0) NOT NULL,
  PRIMARY KEY (`sys_user_id`, `sys_role_id`) USING BTREE,
  INDEX `FK1ef5794xnbirtsnudta6p32on`(`sys_role_id`) USING BTREE,
  CONSTRAINT `FK1ef5794xnbirtsnudta6p32on` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKsbjvgfdwwy5rfbiag1bwh9x2b` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (3, 2);

SET FOREIGN_KEY_CHECKS = 1;
