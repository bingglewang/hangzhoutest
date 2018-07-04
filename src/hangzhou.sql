/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : hangzhou

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2018-07-04 11:12:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `orderId` varchar(25) NOT NULL COMMENT '订单号',
  `orderName` varchar(25) DEFAULT NULL COMMENT '订单名称',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('123', '撒点粉');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(25) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(25) DEFAULT NULL COMMENT '用户名称',
  `orderId` varchar(25) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('48', '水电费', '123');
INSERT INTO `user` VALUES ('49', '阿什顿飞', '123');
