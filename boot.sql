/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : boot

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 03/04/2020 15:36:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `id` char(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `name` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `author` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `price` double NOT NULL,
  `qty` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`, `author`, `price`, `qty`) USING BTREE,
  INDEX `id`(`id`, `name`, `author`, `price`, `qty`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('4598DD7B', '高等数学1', '教育出版社', 56.8, 68);
INSERT INTO `books` VALUES ('10077177', '高等数学2', '教育出版社', 45, 48);
INSERT INTO `books` VALUES ('AB1D702B', '高等数学3', '教育出版社', 32.3, 1);
INSERT INTO `books` VALUES ('D5C41E52', '红楼梦', '红楼梦', 64, 68);
INSERT INTO `books` VALUES ('0ecd0a1e', '红楼梦1', '曹雪芹', 26.5, 33);
INSERT INTO `books` VALUES ('5A96E8DC', '红楼梦2', '曹雪芹', 34.6, 58);
INSERT INTO `books` VALUES ('939005E0', '红楼梦3', '曹雪芹', 32.8, 8);
INSERT INTO `books` VALUES ('6B31EF94', '红楼梦4', '曹雪芹', 40, 50);
INSERT INTO `books` VALUES ('B1056E06', '鲁宾逊漂流记', '丹尼尔·笛福', 54, 100);
INSERT INTO `books` VALUES ('8E1538F2', '鲁宾逊漂流记2', '丹尼尔·笛福 ', 78, 69);
INSERT INTO `books` VALUES ('C98045B2', '论语', '孔子', 57, 34);
INSERT INTO `books` VALUES ('C067A859', '三国演义1', '罗贯中', 56.8, 49);
INSERT INTO `books` VALUES ('FFC89396', '三国演义2', '罗贯中', 43, 12);
INSERT INTO `books` VALUES ('16adfa8b', '水浒传', '施耐庵', 33.5, 0);
INSERT INTO `books` VALUES ('5E83EF5A', '西游记1', '吴承恩', 34.8, 15);
INSERT INTO `books` VALUES ('8C0C8581', '西游记2', '吴承恩', 45.3, 90);

-- ----------------------------
-- Table structure for details
-- ----------------------------
DROP TABLE IF EXISTS `details`;
CREATE TABLE `details`  (
  `id` char(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `login_id` char(36) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `book_id` char(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `value` int(11) NULL DEFAULT NULL,
  `state` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of details
-- ----------------------------
INSERT INTO `details` VALUES ('1A605EF2', 'BDB6F4DE-3DB3-4DFC-95C4-3EE9D59091D3', '10077177', 2, 1);
INSERT INTO `details` VALUES ('379E4376', 'BDB6F4DE-3DB3-4DFC-95C4-3EE9D59091D3', 'D5C41E52', 2, 1);
INSERT INTO `details` VALUES ('3B46A4E8', '8CC24BBD-C943-4A58-AB96-EEB7DF41DDC3', 'F440E9DB', 6, 1);
INSERT INTO `details` VALUES ('6E5F3276', 'BDB6F4DE-3DB3-4DFC-95C4-3EE9D59091D3', '0ecd0a1e', 2, 1);
INSERT INTO `details` VALUES ('CEE01FD4', 'BDB6F4DE-3DB3-4DFC-95C4-3EE9D59091D3', 'C98045B2', 1, 1);
INSERT INTO `details` VALUES ('D2119763', 'BDB6F4DE-3DB3-4DFC-95C4-3EE9D59091D3', '4598DD7B', 10, 1);
INSERT INTO `details` VALUES ('E262F281', 'BDB6F4DE-3DB3-4DFC-95C4-3EE9D59091D3', 'C067A859', 1, 1);
INSERT INTO `details` VALUES ('E72B0BA7', 'A34C748C-3259-4E6A-B96B-0C38CED4BD0B', '4598DD7B', 1, 1);
INSERT INTO `details` VALUES ('FD5449B7', 'BDB6F4DE-3DB3-4DFC-95C4-3EE9D59091D3', '8E1538F2', 1, 1);

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` char(36) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `username` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `password` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('BDB6F4DE-3DB3-4DFC-95C4-3EE9D59091D3', 'admin', 'Y205dmRBPT0=', '2020-04-01 21:18:47');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` char(36) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `username` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `password` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('A34C748C-3259-4E6A-B96B-0C38CED4BD0B', '20162020', 'Y205dmRESXdNVFk9', '2020-04-01 21:18:48');

SET FOREIGN_KEY_CHECKS = 1;
