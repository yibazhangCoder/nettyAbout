/*
MySQL Backup
Source Server Version: 5.5.28
Source Database: wechat
Date: 2019/7/4 20:28:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `chat_msg`
-- ----------------------------
DROP TABLE IF EXISTS `chat_msg`;
CREATE TABLE `chat_msg` (
  `id` varchar(64) NOT NULL,
  `send_user_id` varchar(64) NOT NULL,
  `accept_user_id` varchar(64) NOT NULL,
  `msg` varchar(255) NOT NULL,
  `sign_flag` int(1) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `friends_request`
-- ----------------------------
DROP TABLE IF EXISTS `friends_request`;
CREATE TABLE `friends_request` (
  `id` varchar(64) NOT NULL,
  `send_user_id` varchar(64) NOT NULL,
  `accept_user_id` varchar(64) NOT NULL,
  `request_date_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `my_friends`
-- ----------------------------
DROP TABLE IF EXISTS `my_friends`;
CREATE TABLE `my_friends` (
  `id` varchar(64) NOT NULL,
  `my_user_id` varchar(64) NOT NULL,
  `my_friend_user_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(64) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `face_image` varchar(255) NOT NULL COMMENT '小头像',
  `face_image_big` varchar(255) NOT NULL COMMENT '大头像',
  `nickname` varchar(20) NOT NULL COMMENT '昵称',
  `qrcode` varchar(255) NOT NULL COMMENT '二维码',
  `cid` varchar(64) DEFAULT NULL COMMENT '设备id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `friends_request` VALUES ('1560588881000130702','1560570926000777050','1560338304000108363','2019-06-15 16:54:41');
INSERT INTO `users` VALUES ('1560338304000108363','yibazhang','fcea920f7412b5da7be0cf42b8c93759','images/faceimg/male6.png','','yibazhang','\\2019\\female\\2019-06\\qrcode_yibazhang.jpg','8dae402e337c1e8676260f42bb1da1b7'), ('1560338548000118527','yangshi','e10adc3949ba59abbe56e057f20f883e','images/faceimg/male8.png','','yangshi','\\2019\\female\\2019-06\\qrcode_yangshi.jpg','8dae402e337c1e8676260f42bb1da1b7'), ('1560338961000166705','gmingni','e10adc3949ba59abbe56e057f20f883e','images/faceimg/male3.png','','gmingni','/static/2019/female/2019-06/qrcode_gmingni.jpg','8dae402e337c1e8676260f42bb1da1b7'), ('1560569310000665842','Fjjjgg','3fa0a56c3082c3180e1027393ae4d07d','images/faceimg/male10.png','','Fjjjgg','/static/2019/female/2019-06/qrcode_Fjjjgg.jpg','8dae402e337c1e8676260f42bb1da1b7'), ('1560570926000777050','niuniu','e10adc3949ba59abbe56e057f20f883e','images/faceimg/male3.png','','niuniu','/static/2019/female/2019-06/qrcode_niuniu.jpg','8dae402e337c1e8676260f42bb1da1b7'), ('1562077320000185147','nihaoma','00fbd6d3e86be9c1f3cd8e7f2db17505','images/faceimg/male6.png','','nihaoma','/static/2019/female/2019-07/qrcode_nihaoma.jpg','8267aa13a4c805c0b81442ee9218b07f'), ('1562161424000162132','wobuhao','e4230ee1bde2ba3666509e2750baa564','images/faceimg/male6.png','','wobuhao','/static/2019/female/2019-07/qrcode_wobuhao.jpg','8267aa13a4c805c0b81442ee9218b07f'), ('1562162465000235961','busuan','2f0c9901e3204a59ef02c46d4ce569a6','images/faceimg/male6.png','','busuan','/static/2019/female/2019-07/qrcode_busuan.jpg','8267aa13a4c805c0b81442ee9218b07f');
