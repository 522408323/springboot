CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名唯一',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '加密的密码',
  `salt` varchar(100) NOT NULL DEFAULT '' COMMENT '盐值',
  `mobile` varchar(50) NOT NULL DEFAULT '' COMMENT '手机号码唯一',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `nick_name` varchar(100) NOT NULL DEFAULT '' COMMENT '昵称',
  `head_img_url` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态：1正常，2禁用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_username` (`user_name`) USING BTREE,
  UNIQUE KEY `index_mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- mongodb数据库
use boot_mongo--切换/创建mongodb数据库
-- 创建表
db.createCollection("schoolInfo")
-- 给schoolInfo表插入数据
db.schoolInfo.insert({"schoolId":"1", "schoolName":"北京八十五中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"2", "schoolName":"北京一六六中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"3", "schoolName":"北京一二五中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"4", "schoolName":"北京二十四中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"5", "schoolName":"北京一七七中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"6", "schoolName":"北京六十五中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"7", "schoolName":"北京一六三中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"8", "schoolName":"北京五十四中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"9", "schoolName":"交道口中学", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"10", "schoolName":"北京一二六中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"11", "schoolName":"北京一九五中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"12", "schoolName":"北京一六五中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"13", "schoolName":"北京二十二中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"14", "schoolName":"东直门中学", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"15", "schoolName":"北京二十五中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"16", "schoolName":"北京一中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"17", "schoolName":"北京二十一中", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"18", "schoolName":"东城师范学校", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"19", "schoolName":"北京五中分校", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})
db.schoolInfo.insert({"schoolId":"20", "schoolName":"北京二中分校", "status":"0", "type":"1", "areaCode":"110101", "areaName":"东城区", "cityCode":"11", "cityName":"北京", "provinceCode":"11", "provinceName":"北京"})