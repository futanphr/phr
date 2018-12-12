/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : springboot2

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-07-16 17:21:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_0
-- ----------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_0
-- ----------------------------
INSERT INTO `user_0` VALUES ('1', '112', '11', 'penghuari', '29', null);
INSERT INTO `user_0` VALUES ('2', '112', '11', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_0` VALUES ('3', '112', '11', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_0` VALUES ('4', '112', '11', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_0` VALUES ('5', '112', '11', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_0` VALUES ('6', '112', '11', '', '29', null);
INSERT INTO `user_0` VALUES ('7', '19890502', '1', '1', '1', null);

-- ----------------------------
-- Table structure for user_1
-- ----------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_1
-- ----------------------------
INSERT INTO `user_1` VALUES ('1', '111', '11', 'penghuari', '29', null);
INSERT INTO `user_1` VALUES ('2', '19890501', '1', '1', '1', null);
