/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-07-16 17:21:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_phr_order
-- ----------------------------
DROP TABLE IF EXISTS `t_phr_order`;
CREATE TABLE `t_phr_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `amt` decimal(10,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=909 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_phr_order
-- ----------------------------
INSERT INTO `t_phr_order` VALUES ('907', '20', null, null, '2018-07-13 15:17:15', null);
INSERT INTO `t_phr_order` VALUES ('908', '30', null, null, '2018-07-13 15:17:42', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_0
-- ----------------------------
INSERT INTO `user_0` VALUES ('1', '112', '10', 'penghuari', '29', null);
INSERT INTO `user_0` VALUES ('2', '112', '10', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_0` VALUES ('3', '19890502', '2', '2', '2', null);
INSERT INTO `user_0` VALUES ('4', '2', '0', null, '0', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_1
-- ----------------------------
INSERT INTO `user_1` VALUES ('1', '111', '10', 'penghuari', '29', null);
INSERT INTO `user_1` VALUES ('2', '111', '10', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_1` VALUES ('3', '111', '10', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_1` VALUES ('4', '111', '10', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_1` VALUES ('5', '111', '10', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_1` VALUES ('6', '111', '10', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_1` VALUES ('7', '111', '10', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_1` VALUES ('8', '111', '10', 'penghuariaaaaaaaaaaaaaaaaaa', '29', null);
INSERT INTO `user_1` VALUES ('9', '19890503', '2', '2', '2', null);
