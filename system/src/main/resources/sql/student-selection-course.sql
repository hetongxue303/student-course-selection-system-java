/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100407 (10.4.7-MariaDB)
 Source Host           : 127.0.0.1:3306
 Source Schema         : student-selection-course

 Target Server Type    : MySQL
 Target Server Version : 100407 (10.4.7-MariaDB)
 File Encoding         : 65001

 Date: 06/12/2022 13:38:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account`  (
  `account_id` bigint NOT NULL AUTO_INCREMENT COMMENT '账户ID',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户密码',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '是否启用(1是 0否)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除(1是 0否)',
  `last_login_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0.0.0.0' COMMENT '最后登录IP',
  `last_login_time` datetime NOT NULL COMMENT '最后登录时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`account_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES (1, 'admin', '$2a$10$FIqJPHdQxvemiJ5rmebxu.QLCBA3q17T7Kb43neTAGjiqAU6ELB/a', '1', '0', '127.0.0.1', '2022-11-20 00:40:13', '2022-11-09 16:50:05', '2022-11-09 16:50:07');
INSERT INTO `sys_account` VALUES (2, 'teacher', '$2a$10$FIqJPHdQxvemiJ5rmebxu.QLCBA3q17T7Kb43neTAGjiqAU6ELB/a', '1', '0', '127.0.0.1', '2022-11-20 00:40:13', '2022-11-09 16:50:26', '2022-11-09 16:50:28');
INSERT INTO `sys_account` VALUES (3, 'student', '$2a$10$FIqJPHdQxvemiJ5rmebxu.QLCBA3q17T7Kb43neTAGjiqAU6ELB/a', '1', '0', '127.0.0.1', '2022-11-20 00:40:13', '2022-11-09 16:50:39', '2022-11-09 16:50:40');

-- ----------------------------
-- Table structure for sys_account_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_role`;
CREATE TABLE `sys_account_role`  (
  `account_id` bigint NOT NULL COMMENT '账户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  INDEX `account_id`(`account_id` ASC) USING BTREE,
  CONSTRAINT `sys_account_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_account_role_ibfk_3` FOREIGN KEY (`account_id`) REFERENCES `sys_account` (`account_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_account_role
-- ----------------------------
INSERT INTO `sys_account_role` VALUES (1, 1);
INSERT INTO `sys_account_role` VALUES (2, 2);
INSERT INTO `sys_account_role` VALUES (3, 3);

-- ----------------------------
-- Table structure for sys_account_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_user`;
CREATE TABLE `sys_account_user`  (
  `account_id` bigint NOT NULL COMMENT '账户ID',
  `user_id` bigint NOT NULL COMMENT '用户信息ID',
  INDEX `account_id`(`account_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `sys_account_user_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `sys_account` (`account_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_account_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_account_user
-- ----------------------------
INSERT INTO `sys_account_user` VALUES (1, 1);
INSERT INTO `sys_account_user` VALUES (2, 2);
INSERT INTO `sys_account_user` VALUES (3, 3);

-- ----------------------------
-- Table structure for sys_college
-- ----------------------------
DROP TABLE IF EXISTS `sys_college`;
CREATE TABLE `sys_college`  (
  `college_id` bigint NOT NULL AUTO_INCREMENT COMMENT '学院ID',
  `college_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院描述',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除(1是 0否)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`college_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学院表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_college
-- ----------------------------
INSERT INTO `sys_college` VALUES (1, '通信与信息工程学院', '暂无', '0', '2022-11-09 18:38:50', '2022-11-09 18:38:55');
INSERT INTO `sys_college` VALUES (2, '智能工程学院', '暂无', '0', '2022-11-09 18:39:36', '2022-11-09 18:39:40');
INSERT INTO `sys_college` VALUES (3, '大数据与计算机科学学院', '暂无', '0', '2022-11-09 18:39:56', '2022-11-09 18:40:02');
INSERT INTO `sys_college` VALUES (4, '艺术传媒学院', '暂无', '0', '2022-11-09 18:40:20', '2022-11-09 18:40:25');
INSERT INTO `sys_college` VALUES (5, '外国语学院', '暂无', '0', '2022-11-09 18:40:37', '2022-11-09 18:40:42');
INSERT INTO `sys_college` VALUES (6, '数字经济学院', '暂无', '0', '2022-11-09 18:40:58', '2022-11-09 18:41:03');
INSERT INTO `sys_college` VALUES (7, '信息管理学院', '暂无', '0', '2022-11-09 18:41:16', '2022-11-09 18:41:20');
INSERT INTO `sys_college` VALUES (8, '国际商务学院', '暂无', '0', '2022-11-09 18:41:33', '2022-11-09 18:41:38');

-- ----------------------------
-- Table structure for sys_college_major
-- ----------------------------
DROP TABLE IF EXISTS `sys_college_major`;
CREATE TABLE `sys_college_major`  (
  `college_id` bigint NOT NULL COMMENT '学院ID',
  `major_id` bigint NOT NULL COMMENT '专业ID',
  INDEX `c_id`(`college_id` ASC) USING BTREE,
  INDEX `m_id`(`major_id` ASC) USING BTREE,
  CONSTRAINT `sys_college_major_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_college_major_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `sys_major` (`major_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学院专业关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_college_major
-- ----------------------------
INSERT INTO `sys_college_major` VALUES (1, 1);
INSERT INTO `sys_college_major` VALUES (1, 2);
INSERT INTO `sys_college_major` VALUES (1, 3);
INSERT INTO `sys_college_major` VALUES (1, 4);
INSERT INTO `sys_college_major` VALUES (1, 5);

-- ----------------------------
-- Table structure for sys_major
-- ----------------------------
DROP TABLE IF EXISTS `sys_major`;
CREATE TABLE `sys_major`  (
  `major_id` bigint NOT NULL AUTO_INCREMENT COMMENT '专业ID',
  `major_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业描述',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除(1是 0否)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '专业表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_major
-- ----------------------------
INSERT INTO `sys_major` VALUES (1, '信息工程', '暂无', '0', '2022-11-09 19:25:27', '2022-11-09 19:25:32');
INSERT INTO `sys_major` VALUES (2, '物联网工程', '暂无', '0', '2022-11-09 20:09:29', '2022-11-09 20:09:31');
INSERT INTO `sys_major` VALUES (3, '电子信息工程', '暂无', '0', '2022-11-09 20:09:57', '2022-11-09 20:10:00');
INSERT INTO `sys_major` VALUES (4, '电子信息科学与技术', '暂无', '0', '2022-11-09 20:10:39', '2022-11-09 20:10:46');
INSERT INTO `sys_major` VALUES (5, '电信工程与管理', '暂无', '0', '2022-11-09 20:11:28', '2022-11-09 20:11:31');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `menu_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型(0:目录 1:菜单 2:按钮)',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件地址',
  `sort` int NULL DEFAULT NULL COMMENT '菜单排序',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标名称',
  `per_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `is_display` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否显示(0:否 1:是)',
  `is_frame` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否外链(0:否 1:是)',
  `is_cache` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否缓存(0:否 1:是)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除(0:否 1:是)',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'user', '用户管理', '0', 0, '/user', 'Layout', 1, 'user', NULL, '1', '0', '0', '0', '暂无', '2022-10-30 14:17:20', '2022-10-30 14:17:27');
INSERT INTO `sys_menu` VALUES (2, 'admin', '管理员', '1', 1, '/user/admin', '/user/admin/Index.vue', 2, 'admin', 'user:admin:list', '1', '0', '0', '0', '暂无', '2022-10-30 14:21:11', '2022-10-30 14:21:17');
INSERT INTO `sys_menu` VALUES (3, NULL, '新增管理员', '2', 2, '', NULL, 3, '', 'user:admin:add', '1', '0', '0', '0', '暂无', '2022-10-30 14:22:51', '2022-10-30 14:22:56');
INSERT INTO `sys_menu` VALUES (4, NULL, '删除管理员', '2', 2, NULL, NULL, 4, '', 'user:admin:del', '1', '0', '0', '0', '暂无', '2022-10-30 14:24:19', '2022-10-30 14:24:28');
INSERT INTO `sys_menu` VALUES (5, NULL, '更新管理员', '2', 2, NULL, NULL, 5, '', 'user:admin:update', '1', '0', '0', '0', '暂无', '2022-10-30 14:26:09', '2022-10-30 14:26:07');
INSERT INTO `sys_menu` VALUES (6, 'teacher', '教师管理', '1', 1, '/user/teacher', '/user/teacher/Index.vue', 6, 'teacher', 'user:teacher:list', '1', '0', '0', '0', '暂无', '2022-10-30 14:27:23', '2022-10-30 14:27:25');
INSERT INTO `sys_menu` VALUES (7, NULL, '新增教师', '2', 6, NULL, NULL, 7, NULL, 'user:teacher:add', '1', '0', '0', '0', '暂无', '2022-10-30 14:27:38', '2022-10-30 14:27:32');
INSERT INTO `sys_menu` VALUES (8, NULL, '删除教师', '2', 6, NULL, NULL, 8, NULL, 'user:teacher:del', '1', '0', '0', '0', '暂无', '2022-10-30 14:29:15', '2022-10-30 14:29:17');
INSERT INTO `sys_menu` VALUES (9, NULL, '更新教师', '2', 6, NULL, NULL, 9, NULL, 'user:teacher:update', '1', '0', '0', '0', '暂无', '2022-10-30 14:32:10', '2022-10-30 14:32:06');
INSERT INTO `sys_menu` VALUES (10, 'student', '学生管理', '1', 1, '/user/student', '/user/student/Index.vue', 10, 'student', 'user:student:list', '1', '0', '0', '0', '暂无', '2022-10-30 14:33:13', '2022-10-30 14:33:15');
INSERT INTO `sys_menu` VALUES (11, NULL, '新增学生', '2', 10, NULL, NULL, 11, NULL, 'user:student:add', '1', '0', '0', '0', '暂无', '2022-10-30 14:38:04', '2022-10-30 14:38:04');
INSERT INTO `sys_menu` VALUES (12, NULL, '删除学生', '2', 10, NULL, NULL, 12, NULL, 'user:student:del', '1', '0', '0', '0', '暂无', '2022-10-30 14:38:04', '2022-10-30 14:38:04');
INSERT INTO `sys_menu` VALUES (13, NULL, '更新学生', '2', 10, NULL, NULL, 13, NULL, 'user:student:update', '1', '0', '0', '0', '暂无', '2022-10-30 14:38:04', '2022-10-30 14:38:04');
INSERT INTO `sys_menu` VALUES (14, 'school', '学校管理', '0', 0, '/school', 'Layout', 14, 'school', NULL, '1', '0', '0', '0', '暂无', '2022-10-30 14:38:04', '2022-10-30 14:38:04');
INSERT INTO `sys_menu` VALUES (15, 'college', '学院管理', '1', 14, '/school/college', '/school/college/Index.vue', 15, 'college', 'school:college:list', '1', '0', '0', '0', '暂无', '2022-10-30 14:38:04', '2022-10-30 14:38:04');
INSERT INTO `sys_menu` VALUES (16, NULL, '新增学院', '2', 15, NULL, NULL, 16, NULL, 'school:college:add', '1', '0', '0', '0', '暂无', '2022-10-30 14:38:04', '2022-10-30 14:38:04');
INSERT INTO `sys_menu` VALUES (17, NULL, '删除学院', '2', 15, NULL, NULL, 17, NULL, 'school:college:del', '1', '0', '0', '0', '暂无', '2022-10-30 14:38:04', '2022-10-30 14:38:04');
INSERT INTO `sys_menu` VALUES (18, NULL, '更新学院', '2', 15, NULL, NULL, 18, NULL, 'school:college:update', '1', '0', '0', '0', '暂无', '2022-10-30 14:38:04', '2022-10-30 14:38:04');
INSERT INTO `sys_menu` VALUES (19, 'major', '专业管理', '1', 14, '/school/major', '/school/major/Index.vue', 19, 'major', 'school:major:list', '1', '0', '0', '0', '暂无', '2022-10-30 14:46:04', '2022-10-30 14:46:07');
INSERT INTO `sys_menu` VALUES (20, NULL, '新增专业', '2', 19, NULL, NULL, 20, NULL, 'school:major:add', '1', '0', '0', '0', '暂无', '2022-10-30 14:50:38', '2022-10-30 14:50:46');
INSERT INTO `sys_menu` VALUES (21, NULL, '删除专业', '2', 19, NULL, NULL, 21, NULL, 'school:major:del', '1', '0', '0', '0', '暂无', '2022-10-30 14:50:40', '2022-10-30 14:50:48');
INSERT INTO `sys_menu` VALUES (22, NULL, '更新专业', '2', 19, NULL, NULL, 22, NULL, 'school:major:update', '1', '0', '0', '0', '暂无', '2022-10-30 14:50:44', '2022-10-30 14:50:54');
INSERT INTO `sys_menu` VALUES (23, 'course', '课程管理', '1', 14, '/school/course', '/school/course/Index.vue', 23, 'course', 'school:course:list', '1', '0', '0', '0', '暂无', '2022-10-30 14:50:42', '2022-10-30 14:50:51');
INSERT INTO `sys_menu` VALUES (24, NULL, '新增课程', '2', 23, NULL, NULL, 24, NULL, 'school:course:add', '1', '0', '0', '0', '暂无', '2022-10-30 14:54:07', '2022-10-30 14:54:11');
INSERT INTO `sys_menu` VALUES (25, NULL, '删除课程', '2', 23, NULL, NULL, 25, NULL, 'school:course:del', '1', '0', '0', '0', '暂无', '2022-10-30 14:54:09', '2022-10-30 14:54:13');
INSERT INTO `sys_menu` VALUES (26, NULL, '更新课程', '2', 23, NULL, NULL, 26, NULL, 'school:course:update', '1', '0', '0', '0', '暂无', '2022-10-30 14:55:41', '2022-10-30 14:55:43');
INSERT INTO `sys_menu` VALUES (27, 'selection', '选课管理', '1', 14, '/school/selection', '/school/selection/Index.vue', 27, 'selection', 'school:selection:list', '1', '0', '0', '0', '暂无', '2022-11-21 21:00:58', '2022-11-21 21:01:04');
INSERT INTO `sys_menu` VALUES (28, NULL, '新增选课', '2', 27, NULL, NULL, 28, NULL, 'school:selection:add', '1', '0', '0', '0', '暂无', '2022-11-21 21:00:58', '2022-11-21 21:01:04');
INSERT INTO `sys_menu` VALUES (29, NULL, '删除选课', '2', 27, NULL, NULL, 29, NULL, 'school:selection:del', '1', '0', '0', '0', '暂无', '2022-11-21 21:00:58', '2022-11-21 21:01:04');
INSERT INTO `sys_menu` VALUES (30, NULL, '更新选课', '2', 27, NULL, NULL, 30, NULL, 'school:selection:update', '1', '0', '0', '0', '暂无', '2022-11-21 21:00:58', '2022-11-21 21:01:04');
INSERT INTO `sys_menu` VALUES (31, 'system', '系统管理', '0', 0, '/system', 'Layout', 31, 'system', NULL, '1', '0', '0', '0', '暂无', '2022-11-21 21:07:06', '2022-11-21 21:07:17');
INSERT INTO `sys_menu` VALUES (32, 'account', '账户管理', '1', 31, '/system/account', '/system/account/Index.vue', 32, 'account', 'system:account:list', '1', '0', '0', '0', '暂无', '2022-11-21 21:07:08', '2022-11-21 21:07:20');
INSERT INTO `sys_menu` VALUES (33, NULL, '新增账户', '2', 32, NULL, NULL, 33, NULL, 'system:account:add', '1', '0', '0', '0', '暂无', '2022-11-21 21:07:13', '2022-11-21 21:07:22');
INSERT INTO `sys_menu` VALUES (34, NULL, '删除账户', '2', 32, NULL, NULL, 34, NULL, 'system:account:del', '1', '0', '0', '0', '暂无', '2022-11-21 21:07:10', '2022-11-21 21:07:24');
INSERT INTO `sys_menu` VALUES (35, NULL, '更新账户', '2', 32, NULL, NULL, 35, NULL, 'system:account:update', '1', '0', '0', '0', '暂无', '2022-11-21 21:07:15', '2022-11-21 21:07:27');
INSERT INTO `sys_menu` VALUES (36, 'role', '角色管理', '1', 31, '/system/role', '/system/role/Index.vue', 36, 'role', 'system:role:list', '1', '0', '0', '0', '暂无', '2022-11-21 21:10:02', '2022-11-21 21:10:10');
INSERT INTO `sys_menu` VALUES (37, NULL, '新增角色', '2', 36, NULL, NULL, 37, NULL, 'system:role:add', '1', '0', '0', '0', '暂无', '2022-11-21 21:10:02', '2022-11-21 21:10:10');
INSERT INTO `sys_menu` VALUES (38, NULL, '删除角色', '2', 36, NULL, NULL, 38, NULL, 'system:role:del', '1', '0', '0', '0', '暂无', '2022-11-21 21:10:02', '2022-11-21 21:10:10');
INSERT INTO `sys_menu` VALUES (39, NULL, '更新角色', '2', 36, NULL, NULL, 39, NULL, 'system:role:update', '1', '0', '0', '0', '暂无', '2022-11-21 21:10:02', '2022-11-21 21:10:10');
INSERT INTO `sys_menu` VALUES (40, 'menu', '菜单管理', '1', 31, '/system/menu', '/system/menu/Index.vue', 40, 'menu', 'system:menu:list', '1', '0', '0', '0', '暂无', '2022-11-21 21:12:54', '2022-11-21 21:13:01');
INSERT INTO `sys_menu` VALUES (41, NULL, '新增菜单', '2', 40, NULL, NULL, 41, NULL, 'system:menu:add', '1', '0', '0', '0', '暂无', '2022-11-21 21:12:54', '2022-11-21 21:13:01');
INSERT INTO `sys_menu` VALUES (42, NULL, '删除菜单', '2', 40, NULL, NULL, 42, NULL, 'system:menu:del', '1', '0', '0', '0', '暂无', '2022-11-21 21:12:54', '2022-11-21 21:13:01');
INSERT INTO `sys_menu` VALUES (43, NULL, '更新菜单', '2', 40, NULL, NULL, 43, NULL, 'system:menu:update', '1', '0', '0', '0', '暂无', '2022-11-21 21:12:54', '2022-11-21 21:13:01');
INSERT INTO `sys_menu` VALUES (44, 'about', NULL, '0', 0, '/', 'Layout', 44, NULL, NULL, '1', '0', '0', '0', '暂无', '2022-11-21 21:19:10', '2022-11-21 21:19:14');
INSERT INTO `sys_menu` VALUES (45, 'about', '关于系统', '0', 44, '/about', '/about/Index.vue', 45, 'about', NULL, '1', '0', '0', '0', '暂无', '2022-11-21 21:19:12', '2022-11-21 21:19:17');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色key值',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记(0:存在 1:已删除)',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '角色状态(0:正常 1:停用)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', '0', '0', '2022-10-30 14:11:32', '2022-10-30 14:11:34');
INSERT INTO `sys_role` VALUES (2, '教师', 'teacher', '0', '0', '2022-10-30 14:12:17', '2022-10-30 14:12:22');
INSERT INTO `sys_role` VALUES (3, '学生', 'student', '0', '0', '2022-10-30 14:12:49', '2022-10-30 14:12:54');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL DEFAULT 1 COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  INDEX `menu_id`(`menu_id` ASC) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 26);
INSERT INTO `sys_role_menu` VALUES (1, 27);
INSERT INTO `sys_role_menu` VALUES (1, 28);
INSERT INTO `sys_role_menu` VALUES (1, 29);
INSERT INTO `sys_role_menu` VALUES (1, 30);
INSERT INTO `sys_role_menu` VALUES (1, 31);
INSERT INTO `sys_role_menu` VALUES (1, 32);
INSERT INTO `sys_role_menu` VALUES (1, 33);
INSERT INTO `sys_role_menu` VALUES (1, 34);
INSERT INTO `sys_role_menu` VALUES (1, 35);
INSERT INTO `sys_role_menu` VALUES (1, 36);
INSERT INTO `sys_role_menu` VALUES (1, 37);
INSERT INTO `sys_role_menu` VALUES (1, 38);
INSERT INTO `sys_role_menu` VALUES (1, 39);
INSERT INTO `sys_role_menu` VALUES (1, 40);
INSERT INTO `sys_role_menu` VALUES (1, 41);
INSERT INTO `sys_role_menu` VALUES (1, 42);
INSERT INTO `sys_role_menu` VALUES (1, 43);
INSERT INTO `sys_role_menu` VALUES (1, 44);
INSERT INTO `sys_role_menu` VALUES (1, 45);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `college_id` bigint NULL DEFAULT NULL COMMENT '学院ID',
  `major_id` bigint NULL DEFAULT NULL COMMENT '专业ID',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `user_no` bigint NULL DEFAULT NULL COMMENT '学号/工号',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `real_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '3' COMMENT '性别(1:男 2:女 3:保密)',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '是否启用(1是 0否)',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除(1是 0否)',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `c_id`(`college_id` ASC) USING BTREE,
  INDEX `m_id`(`major_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `sys_major` (`major_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_ibfk_3` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, NULL, 1, 1, 1, '刘同学', '刘RR', '0', '1974597686@qq.com', '15310970229', 'https://profile.csdnimg.cn/6/A/B/0_qq_14818715', '1', '0', '暂无', '2022-10-13 14:20:19', '2022-10-13 14:20:25');
INSERT INTO `sys_user` VALUES (2, 1, 1, 2, 61328, 'XX教师', '张三', '1', '513565071@qq.com', '15685977859', 'https://profile.csdnimg.cn/6/A/B/0_qq_14818715', '1', '0', '暂无', '2022-10-13 14:23:03', '2022-10-13 14:23:10');
INSERT INTO `sys_user` VALUES (3, 3, 1, 3, 2021230522, 'XX学生', '李四', '0', '265462959@QQ.com', '15456585889', 'https://profile.csdnimg.cn/6/A/B/0_qq_14818715', '1', '0', '暂无', '2022-10-30 14:59:09', '2022-10-30 14:59:15');

SET FOREIGN_KEY_CHECKS = 1;
