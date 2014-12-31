CREATE DATABASE IF NOT EXISTS `car_manager` DEFAULT CHARACTER SET utf8;
USE `car_manager`;

drop table if exists `importlog`;
create table `importlog`(
  `id` int not null auto_increment,
  `importFileName` varchar(100) default null comment '成功导入文件名',
  `importTime` datetime default null comment '导入时间',
  primary key (`id`)
) engine=InnoDB default charset=utf8;

drop table if exists `car`;
create table `car`(
  `id` int(32) not null auto_increment ,
  `chePai` varchar(10) default null comment '车牌',
  `cheZhu` varchar(100) default null comment '车主',
  `dianHua` varchar(13) default null comment '电话',
  `chePingPai` varchar(100) default null comment '车辆品牌',
  `cheXinHao` varchar(100) default null comment '车辆型号',
  `faDongJi` varchar(100) default null comment '发动机',
  `cheJiaHao` varchar(100) default null comment '车架号',
  `dengJiRQ` datetime default null comment '登记日期',
  `baoXianRQ` datetime default null comment '保险日期',
  `shenFengZheng` varchar(100) default null comment '身份证',
  `diZhi` varchar(100) default null comment '地址',
  `remark` varchar(100) default null comment 'remark',
  `bakA` numeric(10) default null comment 'bak_a',
  `bakB` varchar(100) default null comment 'bak_b',
  `bakC` varchar(100) default null comment 'bak_c',
  `bakD` varchar(100) default null comment 'bak_d',
  primary key (`id`)
) engine=InnoDB default charset = utf8;

alter table `car` add index car_cp_index(`chepai`);
alter table `car` add index car_dh_index(`dianhua`);
