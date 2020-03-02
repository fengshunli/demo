/*
 Navicat Premium Data Transfer

 Source Server         : 118.190.146.65
 Source Server Type    : MySQL
 Source Server Version : 50564
 Source Host           : 118.190.146.65:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50564
 File Encoding         : 65001

 Date: 02/03/2020 17:48:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
