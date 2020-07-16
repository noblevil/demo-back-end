/*
 Navicat Premium Data Transfer

 Source Server         : WinMysql
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : bladex

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 16/07/2020 17:11:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) NOT NULL COMMENT '企业ID 机构标识符',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '投诉标题',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所投诉内容',
  `suggest` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '改进建议',
  PRIMARY KEY (`complaint_id`) USING BTREE,
  INDEX `org_id_fk_complaint_org_account`(`org_id`) USING BTREE,
  CONSTRAINT `org_id_fk_complaint_org_account` FOREIGN KEY (`org_id`) REFERENCES `org_account` (`org_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '校外投诉 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaint
-- ----------------------------
INSERT INTO `complaint` VALUES (1, 1, '标题1', '课程时间太长', '调整每节课的长度');
INSERT INTO `complaint` VALUES (2, 2, '标题2', '上课太无聊，老师讲课不生动', '要求调整上课老师');
INSERT INTO `complaint` VALUES (3, 1, '标题3', '上课太无聊，老师讲课不生动', '要求调整上课老师');
INSERT INTO `complaint` VALUES (4, 2, '标题4', '上课太无聊，老师讲课不生动', '要求调整上课老师');
INSERT INTO `complaint` VALUES (5, 2, '标题5', '上课太无聊，老师讲课不生动', '要求调整上课老师');
INSERT INTO `complaint` VALUES (6, 2, '标题6', '上课太无聊，老师讲课不生动', '要求调整上课老师');
INSERT INTO `complaint` VALUES (7, 2, '标题7', '上课太无聊，老师讲课不生动', '要求调整上课老师');
INSERT INTO `complaint` VALUES (8, 2, '标题8', '上课太无聊，老师讲课不生动', '要求调整上课老师');
INSERT INTO `complaint` VALUES (9, 2, '标题9', '上课太无聊，老师讲课不生动', '要求调整上课老师');
INSERT INTO `complaint` VALUES (10, 1, '标题10', '上课太无聊，老师讲课不生动', '要求调整上课老师');
INSERT INTO `complaint` VALUES (11, 3, 'title', 'Content', 'Suggest');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `org_id` int(11) DEFAULT NULL COMMENT '外键',
  `course_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `course_subject` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学科 1、语文；2、数学；3、英语；4、思想政治；5、历史；6、地理；7、物理；8、化学；9、生物',
  `course_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程级别 (初级、中级、高级)',
  `course_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程链接',
  `content_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程介绍',
  `valid_period` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程有效期',
  `student_rank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '招生对象学段',
  `student_grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '招生对象年级',
  `total_lessons` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '总课次',
  `textbook` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教材',
  `publish_company` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出版社',
  `ISBN_number` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发行号ISBN',
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `org_id_fk_course_org_account`(`org_id`) USING BTREE,
  CONSTRAINT `org_id_fk_course_org_account` FOREIGN KEY (`org_id`) REFERENCES `org_account` (`org_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程内容 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (101, 1, '数学', '理科', '小学', '无', '就是数学课', '1年', '小学', '一年级', '45小时', '无', '无', '无');
INSERT INTO `course` VALUES (102, 2, '物理', '理科', '小学', '无', '就是物理课', '1年', '小学', '一年级', '45小时', '无', '无', '无');
INSERT INTO `course` VALUES (103, 2, '化学', '理科', '小学', '无', '就是化学课', '1年', '小学', '一年级', '45小时', '无', '无', '无');
INSERT INTO `course` VALUES (104, 4, '语文', '文科', '初中', '无', '就是语文课', '1年', '初中', '一年级', '45小时', '无', '无', '无');
INSERT INTO `course` VALUES (105, 5, '政治', '文科', '小学', '无', '就是政治课', '1年', '小学', '一年级', '45小时', '无', '无', '无');
INSERT INTO `course` VALUES (106, 6, '英语', '文科', '高中', '无', '就是英语课', '1年', '高中', '一年级', '45小时', '无', '无', '无');
INSERT INTO `course` VALUES (107, 7, '历史', '文科', '小学', '无', '就是历史课', '1年', '小学', '一年级', '45小时', '无', '无', '无');
INSERT INTO `course` VALUES (108, 8, '历史', '文科', '小学', '无', '就是历史课', '1年', '小学', '一年级', '45小时', '无', '无', '无');
INSERT INTO `course` VALUES (113, 1, '英语', '文科', '测试', '测试', '测试', '测试', NULL, '', NULL, '', '', NULL);

-- ----------------------------
-- Table structure for course_class
-- ----------------------------
DROP TABLE IF EXISTS `course_class`;
CREATE TABLE `course_class`  (
  `course_class_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `class_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `enroll_num` int(11) NOT NULL COMMENT '计划招生人数',
  `teaching_time` date NOT NULL COMMENT '课程开始日期',
  `start_time` date NOT NULL COMMENT '课程上课时间',
  `end_time` date NOT NULL COMMENT '课程结束日期',
  PRIMARY KEY (`course_class_id`) USING BTREE,
  INDEX `course_id_fk_course_class_course`(`course_id`) USING BTREE,
  CONSTRAINT `course_id_fk_course_class_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程班级信息 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_class
-- ----------------------------
INSERT INTO `course_class` VALUES (1, '小一', 101, 50, '2020-06-02', '2019-01-20', '2020-01-20');
INSERT INTO `course_class` VALUES (2, '小二', 102, 50, '2020-06-02', '2019-01-20', '2020-01-20');
INSERT INTO `course_class` VALUES (3, '小三', 103, 50, '2020-06-02', '2019-01-20', '2020-01-20');
INSERT INTO `course_class` VALUES (4, '小四', 104, 50, '2020-06-02', '2019-01-20', '2020-01-20');
INSERT INTO `course_class` VALUES (5, '小五', 105, 50, '2020-06-02', '2019-01-20', '2020-01-20');
INSERT INTO `course_class` VALUES (6, '小六', 106, 50, '2020-06-02', '2019-01-20', '2020-01-20');
INSERT INTO `course_class` VALUES (7, '初一', 107, 50, '2020-06-02', '2019-01-20', '2020-01-20');
INSERT INTO `course_class` VALUES (8, '初二', 108, 50, '2020-06-02', '2019-01-20', '2020-01-20');

-- ----------------------------
-- Table structure for org_account
-- ----------------------------
DROP TABLE IF EXISTS `org_account`;
CREATE TABLE `org_account`  (
  `org_id` int(11) NOT NULL COMMENT '机构ID',
  `org_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构账号名',
  `org_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `org_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `passwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) DEFAULT NULL COMMENT '账户创建时间',
  PRIMARY KEY (`org_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构登录信息 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_account
-- ----------------------------
INSERT INTO `org_account` VALUES (1, '1101234561', '11011110001', '12345671@qq.com', '123456', '2020-07-03 20:59:51');
INSERT INTO `org_account` VALUES (2, '1101234562', '11011110002', '12345672@qq.com', '123456', '2020-07-03 20:59:51');
INSERT INTO `org_account` VALUES (3, '1101234563', '11011110003', '12345673@qq.com', '123456', '2020-07-03 20:59:51');
INSERT INTO `org_account` VALUES (4, '1101234564', '11011110004', '12345674@qq.com', '123456', '2020-07-03 20:59:51');
INSERT INTO `org_account` VALUES (5, '1101234565', '11011110005', '12345675@qq.com', '123456', '2020-07-03 20:59:51');
INSERT INTO `org_account` VALUES (6, '1101234566', '11011110006', '12345676@qq.com', '123456', '2020-07-03 20:59:51');
INSERT INTO `org_account` VALUES (7, '1101234567', '11011110007', '12345677@qq.com', '123456', '2020-07-03 20:59:51');
INSERT INTO `org_account` VALUES (8, '1101234568', '11011110008', '12345678@qq.com', '123456', '2020-07-03 20:59:51');
INSERT INTO `org_account` VALUES (9, '1101234569', '11011110009', '12345679@qq.com', '123456', '2020-07-03 20:59:51');

-- ----------------------------
-- Table structure for org_info
-- ----------------------------
DROP TABLE IF EXISTS `org_info`;
CREATE TABLE `org_info`  (
  `org_id` int(11) NOT NULL COMMENT '企业ID',
  `org_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构类型',
  `org_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业名称（机构名称）',
  `org_simply_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业简称',
  `established_date` date DEFAULT NULL,
  `org_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业电话号码',
  `register_address` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业注册地址',
  `often_address` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '企业常用地址 (实际经营地址)作为地图显示的内容',
  `is_invest_abroad` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否境外投资',
  `school_licence` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '办学许可证号',
  `school_licence_department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '办学许可证审批部门',
  `unified_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '统一社会信用代码',
  `linkman_one` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '常用联系人1',
  `linkman_one_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人1电话',
  `linkman_two` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '常用联系人2',
  `linkman_two_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人2电话',
  `train_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '培训类别',
  `train_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '培训内容',
  `train_form` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '培训形式 (面授  在线)',
  `enroll_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '招生对象',
  `enroll_region` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '招生范围 （区域）',
  `business_license` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业营业执照或事业单位法人证书',
  `related_certificates` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '相关证书 软件企业认定证书、高新技术企业证书、计算机软件著作权登记证书',
  `list_type` tinyint(4) DEFAULT 0 COMMENT '0：白名单；1：黑名单；2：灰名单',
  `org_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构所在地：通过三级菜单选择',
  PRIMARY KEY (`org_id`) USING BTREE,
  CONSTRAINT `org_id_fk_org_info_org_account` FOREIGN KEY (`org_id`) REFERENCES `org_account` (`org_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构信息 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_info
-- ----------------------------
INSERT INTO `org_info` VALUES (1, '民办', '机构1', '机构1', '2020-07-01', '19999999999', '大学城', '大学城', '0', '是', '有', '456', '19999999999', '19999999999', '你', '19999999999', '数学', '中学教育', '面授', '4000', '4000', '有', '使用', 0, NULL);
INSERT INTO `org_info` VALUES (2, '民办', '学小易', '学小易', '2020-07-01', '19999999999', '大学城', '大学城', '0', '是', '有', '456', '我', '19999999999', '你', '19999999999', '数学', '中学教育', '面授', '4000', '4000', '有', '使用', 0, NULL);
INSERT INTO `org_info` VALUES (3, '公办', '小神童', '小神童', '2020-07-01', '110', '大学城', '大学城', '否', '是', '有', '110', '我', '110', '你', '120', '在线', '小学教育', '面授', '3000', '3000', '有', '使用', 0, NULL);
INSERT INTO `org_info` VALUES (4, '公办', '小明星', '小明星', '2020-07-01', '110', '大学城', '大学城', '否', '是', '有', '110', '我', '110', '你', '120', '在线', '小学教育', '面授', '3000', '3000', '有', '使用', 0, NULL);
INSERT INTO `org_info` VALUES (5, '公办', '小机械', '小机械', '2020-07-01', '110', '大学城', '大学城', '否', '是', '有', '110', '我', '110', '你', '120', '在线', '小学教育', '线上', '3000', '3000', '有', '使用', 0, NULL);
INSERT INTO `org_info` VALUES (6, '公办', '明启', '明启', '2020-07-01', '110', '大学城', '大学城', '否', '是', '有', '110', '我', '110', '你', '120', '在线', '小学教育', '线上', '3000', '3000', '有', '使用', 0, NULL);
INSERT INTO `org_info` VALUES (7, '公办', '机构7', '机构7', '2020-07-01', '18888888888', '大学城', '大学城', '0', '是', '有', '123', '我', '188888888888', '你', '188888888888', '语文', '中学教育', '线上', '4000', '4000', '有', '使用', 0, NULL);
INSERT INTO `org_info` VALUES (8, '公办', '机构8', '机构8', '2020-07-01', '110', '大学城', '大学城', '否', '是', '有', '110', '我', '110', '你', '120', '在线', '小学教育', '面授', '3000', '3000', '有', '使用', 0, NULL);

-- ----------------------------
-- Table structure for org_qualif_data
-- ----------------------------
DROP TABLE IF EXISTS `org_qualif_data`;
CREATE TABLE `org_qualif_data`  (
  `org_id` int(11) NOT NULL COMMENT '机构ID 机构标识符',
  `legal_represent_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '法人姓名',
  `legal_represent_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `id_type` int(11) DEFAULT NULL COMMENT '证件类型 1、身份证；2、港澳通行证；3、台湾通行证；4、护照；5、外国人身份证；6、港澳台居住证；',
  `id_num` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证件号码',
  `id_card_front` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证正面 用于存放地址',
  `id_card_reverse` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证反面',
  `icp_record_num` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ICP备案号',
  `telecommunication_business_license` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电信业务营业许可证',
  `network_sec_lev_rec` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网络安全等级备案证明 证明图片的存储地址',
  PRIMARY KEY (`org_id`) USING BTREE,
  CONSTRAINT `org_id_fk_org_qualif_data_org_account` FOREIGN KEY (`org_id`) REFERENCES `org_account` (`org_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构资质材料 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_qualif_data
-- ----------------------------
INSERT INTO `org_qualif_data` VALUES (1, '小卓', '11011001101', 1, '1', '1', '2', '0101', '0001', '已开证明');
INSERT INTO `org_qualif_data` VALUES (2, '小陈', '11011001102', 2, '1', '1', '2', '0102', '0002', '已开证明');
INSERT INTO `org_qualif_data` VALUES (3, '小张', '11011001103', 3, '1', '1', '2', '0103', '0003', '已开证明');
INSERT INTO `org_qualif_data` VALUES (4, '小李', '11011001104', 4, '1', '1', '2', '0104', '0004', '已开证明');
INSERT INTO `org_qualif_data` VALUES (5, '小吴', '11011001105', 5, '1', '1', '2', '0105', '0005', '已开证明');
INSERT INTO `org_qualif_data` VALUES (6, '小胡', '11011001106', 6, '1', '1', '2', '0106', '0006', '已开证明');

-- ----------------------------
-- Table structure for rel_org_teach
-- ----------------------------
DROP TABLE IF EXISTS `rel_org_teach`;
CREATE TABLE `rel_org_teach`  (
  `rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `teach_id` int(11) DEFAULT NULL,
  `org_teach_status` int(4) DEFAULT NULL COMMENT '0 表示机构确认  教师没确认   1 表示机构 教师双方确认2 表示 教师提出离职申请 机构还未确认  3 表示 已经离职',
  `start_time` datetime(0) DEFAULT NULL,
  `end_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`rel_id`) USING BTREE,
  INDEX `org_id_rel_org_teach`(`org_id`) USING BTREE,
  INDEX `teach_id_el_org_teach`(`teach_id`) USING BTREE,
  CONSTRAINT `org_id_rel_org_teach` FOREIGN KEY (`org_id`) REFERENCES `org_account` (`org_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teach_id_el_org_teach` FOREIGN KEY (`teach_id`) REFERENCES `teach_account` (`teach_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rel_org_teach
-- ----------------------------
INSERT INTO `rel_org_teach` VALUES (1, 1, 1, 1, '2020-05-04 21:25:42', '2020-07-03 21:25:48');
INSERT INTO `rel_org_teach` VALUES (2, 1, 2, 1, '2020-05-04 21:25:42', '2020-07-03 21:25:48');
INSERT INTO `rel_org_teach` VALUES (3, 3, 3, 3, '2020-05-04 21:25:42', '2020-07-03 21:25:48');
INSERT INTO `rel_org_teach` VALUES (4, 4, 4, 1, '2020-05-04 21:25:42', '2020-07-03 21:25:48');
INSERT INTO `rel_org_teach` VALUES (5, 5, 5, 1, '2020-05-04 21:25:42', '2020-07-03 21:25:48');
INSERT INTO `rel_org_teach` VALUES (6, 6, 6, 1, '2020-05-04 21:25:42', '2020-07-03 21:25:48');
INSERT INTO `rel_org_teach` VALUES (7, 7, 7, 1, '2020-05-04 21:25:42', '2020-07-03 21:25:48');
INSERT INTO `rel_org_teach` VALUES (8, 1, 8, 3, '2020-05-04 21:25:42', '2020-07-03 21:25:48');
INSERT INTO `rel_org_teach` VALUES (21, 1, 30, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (22, 1, 31, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (23, 3, 32, 0, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (24, 1, 33, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (25, 1, 34, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (26, 3, 35, 0, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (27, 1, 36, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (28, 1, 37, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (29, 1, 38, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (30, 1, 39, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (31, 1, 40, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (32, 1, 41, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (33, 1, 42, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (34, 1, 43, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (35, 1, 44, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (36, 1, 45, 0, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (37, 1, 46, 0, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (38, 1, 47, 0, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (39, 1, 48, 0, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (40, 1, 49, 0, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (41, 1, 50, 0, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (42, 1, 51, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (43, 1, 52, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (44, 1, 53, 3, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (45, 1, 54, 0, NULL, NULL);
INSERT INTO `rel_org_teach` VALUES (46, 1, 55, 3, NULL, NULL);

-- ----------------------------
-- Table structure for rel_teach_course_class
-- ----------------------------
DROP TABLE IF EXISTS `rel_teach_course_class`;
CREATE TABLE `rel_teach_course_class`  (
  `rel_Id` int(11) NOT NULL,
  `teach_id` int(11) DEFAULT NULL,
  `course_class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rel_Id`) USING BTREE,
  INDEX `teach_id_rel_course_class_teach`(`teach_id`) USING BTREE,
  INDEX `course_class_id_rel_course_class_teach`(`course_class_id`) USING BTREE,
  CONSTRAINT `course_class_id_rel_course_class_teach` FOREIGN KEY (`course_class_id`) REFERENCES `course_class` (`course_class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teach_id_rel_course_class_teach` FOREIGN KEY (`teach_id`) REFERENCES `teach_account` (`teach_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rel_teach_course_class
-- ----------------------------
INSERT INTO `rel_teach_course_class` VALUES (1, 1, 1);
INSERT INTO `rel_teach_course_class` VALUES (2, 2, 2);
INSERT INTO `rel_teach_course_class` VALUES (3, 3, 3);
INSERT INTO `rel_teach_course_class` VALUES (4, 4, 4);
INSERT INTO `rel_teach_course_class` VALUES (5, 5, 5);
INSERT INTO `rel_teach_course_class` VALUES (6, 6, 6);
INSERT INTO `rel_teach_course_class` VALUES (7, 7, 7);

-- ----------------------------
-- Table structure for teach_account
-- ----------------------------
DROP TABLE IF EXISTS `teach_account`;
CREATE TABLE `teach_account`  (
  `teach_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师ID',
  `teach_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号名',
  `teach_phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `teach_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `passwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`teach_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师账号信息 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teach_account
-- ----------------------------
INSERT INTO `teach_account` VALUES (1, '110', '11011001101', '110111@qq.com', '123456', '2020-07-21 21:26:25');
INSERT INTO `teach_account` VALUES (2, '120', '11011001102', '110112@qq.com', '123456', '2020-07-21 21:26:25');
INSERT INTO `teach_account` VALUES (3, '130', '11011001103', '110113@qq.com', '123456', '2020-07-21 21:26:25');
INSERT INTO `teach_account` VALUES (4, '140', '11011001104', '110114@qq.com', '123456', '2020-07-21 21:26:25');
INSERT INTO `teach_account` VALUES (5, '150', '11011001105', '110115@qq.com', '123456', '2020-07-21 21:26:25');
INSERT INTO `teach_account` VALUES (6, '160', '11011001106', '110116@qq.com', '123456', '2020-07-21 21:26:25');
INSERT INTO `teach_account` VALUES (7, '170', '11011001107', '110117@qq.com', '123456', '2020-07-21 21:26:25');
INSERT INTO `teach_account` VALUES (8, '180', '11011001108', '110118@qq.com', '123456', '2020-07-21 21:26:25');
INSERT INTO `teach_account` VALUES (9, '190', '11011001109', '110119@qq.com', '123456', '2020-07-21 21:26:25');
INSERT INTO `teach_account` VALUES (17, '10086', NULL, '1234567890@qq.com', '123456', '2020-07-12 20:17:26');
INSERT INTO `teach_account` VALUES (18, '10000', NULL, '233333333@qq.com', '123456', '2020-07-12 20:20:35');
INSERT INTO `teach_account` VALUES (19, '13333', NULL, '1888888888@qq.com', '123456', '2020-07-12 21:35:53');
INSERT INTO `teach_account` VALUES (20, '123123', NULL, '123@123', '123456', '2020-07-13 13:32:26');
INSERT INTO `teach_account` VALUES (21, '213123123213', NULL, '1221312312@123.com', '123456', '2020-07-13 13:33:00');
INSERT INTO `teach_account` VALUES (22, '123213', NULL, '123@123213', '123456', '2020-07-13 18:17:32');
INSERT INTO `teach_account` VALUES (23, '32323', NULL, '1234567@123.com', '123456', '2020-07-13 18:17:59');
INSERT INTO `teach_account` VALUES (24, 'ceshi1', NULL, 'ceshi@163.com', '123456', '2020-07-13 18:18:42');
INSERT INTO `teach_account` VALUES (25, 'dasdasdw', NULL, '1231231@163.com', '123456', '2020-07-13 18:20:52');
INSERT INTO `teach_account` VALUES (26, '123123123', NULL, 'sadasd@12312', '123456', '2020-07-13 18:21:20');
INSERT INTO `teach_account` VALUES (29, 'cyf', NULL, 'cyf@qq.com', '123456', '2020-07-13 18:49:55');
INSERT INTO `teach_account` VALUES (30, '12312312', NULL, '1234561@123.com', '123456', '2020-07-13 18:53:01');
INSERT INTO `teach_account` VALUES (31, 'sadasdasd', NULL, '123211222@qq.com', '123456', '2020-07-13 18:55:33');
INSERT INTO `teach_account` VALUES (32, 'cyf123', NULL, '1333@qq.com', '123456', '2020-07-13 18:57:10');
INSERT INTO `teach_account` VALUES (33, 'asdasd', NULL, 'asdasd@163.com', '123456', '2020-07-13 19:00:18');
INSERT INTO `teach_account` VALUES (34, 'testqu', NULL, 'testqu@123.com', '123456', '2020-07-13 19:02:49');
INSERT INTO `teach_account` VALUES (35, '432', NULL, '13433@qq.com', '123456', '2020-07-13 19:20:09');
INSERT INTO `teach_account` VALUES (36, 'sadasd', NULL, '1111@123.com', '123456', '2020-07-13 21:01:13');
INSERT INTO `teach_account` VALUES (37, '1212', NULL, '12321@qq.com', '123456', '2020-07-15 13:44:01');
INSERT INTO `teach_account` VALUES (38, '213123', NULL, '213213', '123456', '2020-07-15 13:46:18');
INSERT INTO `teach_account` VALUES (39, '12121232', NULL, '111122', '123456', '2020-07-15 14:10:18');
INSERT INTO `teach_account` VALUES (40, '1231231231212', NULL, '21312312', '123456', '2020-07-15 14:10:46');
INSERT INTO `teach_account` VALUES (41, '123123123121212312', NULL, '213123121221', '123456', '2020-07-15 14:10:58');
INSERT INTO `teach_account` VALUES (42, '22', NULL, '11', '123456', '2020-07-15 14:53:35');
INSERT INTO `teach_account` VALUES (43, 'adadasd', NULL, '123123', '123456', '2020-07-15 19:50:16');
INSERT INTO `teach_account` VALUES (44, 'asdasdasd', NULL, 'asdasd', '123456', '2020-07-15 19:52:02');
INSERT INTO `teach_account` VALUES (45, '123123123123', NULL, '123123123123', '123456', '2020-07-15 20:06:46');
INSERT INTO `teach_account` VALUES (46, '21213123', NULL, '12312321312', '123456', '2020-07-15 20:06:54');
INSERT INTO `teach_account` VALUES (47, '2121', NULL, '21312', '123456', '2020-07-15 20:07:12');
INSERT INTO `teach_account` VALUES (48, '21321312213', NULL, '213213123213', '123456', '2020-07-15 20:21:13');
INSERT INTO `teach_account` VALUES (49, '12312321212', NULL, '123123213', '123456', '2020-07-15 20:21:19');
INSERT INTO `teach_account` VALUES (50, '12321321122', NULL, '1231231231', '123456', '2020-07-15 20:21:25');
INSERT INTO `teach_account` VALUES (51, '1111121', NULL, '22222211', '123456', '2020-07-15 20:21:32');
INSERT INTO `teach_account` VALUES (52, '23232111', NULL, '23232111', '123456', '2020-07-15 20:21:38');
INSERT INTO `teach_account` VALUES (53, '12321321321', NULL, '12321122223', '123456', '2020-07-15 20:21:52');
INSERT INTO `teach_account` VALUES (54, 'asdasdasdasaww', NULL, 'asjdklasd', '123456', '2020-07-15 21:02:09');
INSERT INTO `teach_account` VALUES (55, 'asdaw123123', NULL, '2121212321', '123456', '2020-07-15 21:02:24');

-- ----------------------------
-- Table structure for teach_info
-- ----------------------------
DROP TABLE IF EXISTS `teach_info`;
CREATE TABLE `teach_info`  (
  `teach_id` int(11) NOT NULL COMMENT '教师ID',
  `teach_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师名字',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `teach_birth` date DEFAULT NULL COMMENT '教师出生日期',
  `teaching_subject` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师授课学科',
  `is_teach_qualif_cert` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否有教师资格证',
  `teach_qualif_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任教资格分类',
  `certificate_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证书号码',
  `professional_title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专业职称',
  `country_nature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国籍性质',
  `nationality` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国籍',
  `highest_education` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最高学历',
  `educational_institution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `highest_degree` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最高学位',
  `degree_obtained_institution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学位获得院校或机构',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所学专业',
  `graduation_date` date DEFAULT NULL COMMENT '毕业日期',
  `work_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作类型',
  `id_type` tinyint(255) DEFAULT NULL,
  `id_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `native_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '籍贯',
  `political_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '政治面貌',
  PRIMARY KEY (`teach_id`) USING BTREE,
  CONSTRAINT `teach_id_fk_teach_info_teach_account` FOREIGN KEY (`teach_id`) REFERENCES `teach_account` (`teach_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师信息 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teach_info
-- ----------------------------
INSERT INTO `teach_info` VALUES (1, '小张', '男', '1990-06-30', '语文', '是', '小学一年级', '001', '讲师', '中国大陆', '研究生', '研究生', '中山大学', '博士', '广州大学', '语文', '2020-06-30', '全职', 1, '3423252353', '广东东莞', '群众');
INSERT INTO `teach_info` VALUES (2, '小陈', '男', '1990-09-16', '数学', '是', '小学二年级', '002', '讲师', '本国', '中国', '本科', '广州大学', '学士', '广州大学', '数学', '2020-06-30', '全日', 1, '001', '广东广州', '党员');
INSERT INTO `teach_info` VALUES (3, '小明', '男', '1990-10-10', '英语', '是', '小学三年级', '003', '讲师', '本国', '中国', '本科', '广东工业大学', '学士', '广东工业大学', '英语', '2020-06-30', '全日', 1, '001', '广东广州', '团员');
INSERT INTO `teach_info` VALUES (4, '小张', '男', '1990-12-01', '化学', '是', '小学四年级', '004', '讲师', '本国', '中国', '本科', '华南理工大学', '学士', '华南理工大学', '化学', '2020-06-30', '全日', 1, '001', '广东广州', '群众');
INSERT INTO `teach_info` VALUES (5, '小美', '男', '1990-11-09', '物理', '是', '小学五年级', '005', '讲师', '本国', '中国', '本科', '华南师范大学', '学士', '华南师范大学', '物理', '2020-06-30', '全日', 1, '001', '广东广州', '党员');
INSERT INTO `teach_info` VALUES (6, '小同', '男', '1990-06-17', '历史', '是', '小学六年级', '005', '讲师', '本国', '中国', '本科', '暨南大学', '学士', '暨南大学', '历史', '2020-06-30', '全日', 1, '001', '广东广州', '团员');
INSERT INTO `teach_info` VALUES (7, '小鱼', '男', '1990-08-19', '地理', '是', '初中一年级', '005', '讲师', '本国', '中国', '本科', '广东技术师范大学', '学士', '广东技术师范大学', '地理', '2020-06-30', '全日', 1, '001', '广东广州', '群众');
INSERT INTO `teach_info` VALUES (8, '小黑', '男', '1990-08-19', '地理', '是', '初中一年级', '005', '教授', '本国', '中国', '本科', '广东技术师范大学', '学士', '广东技术师范大学', '地理', '2020-06-30', '全日', 1, '001', '广东广州', '群众');
INSERT INTO `teach_info` VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (46, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (47, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (48, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (49, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (50, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (51, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (52, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (53, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (54, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teach_info` VALUES (55, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
