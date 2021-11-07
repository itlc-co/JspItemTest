# 创建项目数据库默认字符集utf-8
create database if not exists smbms default character set utf8 collate utf8_unicode_ci;
# 设置不自动提交
# 选择项目数据库
use smbms;
# 如果数据存在则将其删除
drop table if exists `smbms_user`;
# 创建项目用户数据表
create table `smbms_user`(
	`id` bigint(20) primary key auto_increment not null comment 'ID',
	`userCode` varChar(15) default null collate utf8_unicode_ci comment '用户编码(账号)' ,
	`userName` varChar(5) default null collate utf8_unicode_ci comment '用户名称',
	`userPassword` varChar(16) default null collate utf8_unicode_ci comment '用户密码',
	`gender` int(10) default null collate utf8_unicode_ci comment '性别(1:女,2:男)',
	`birthday` date default null collate utf8_unicode_ci comment '出生日期',
	`age` int(10) default null collate utf8_unicode_ci comment '年龄',
	`phone` varChar(15) default null collate utf8_unicode_ci comment '电话',
	`address` varChar(50) default null collate utf8_unicode_ci comment '地址',
	`userRole` bigint(20) default null collate utf8_unicode_ci comment '用户角色(关联角色表id值)',
	`userRoleName` varChar(15) default null collate utf8_unicode_ci comment '用户角色名称',
	`createBy` bigint(20) default null collate utf8_unicode_ci comment '创建者(关联id)',
	`createionDate` datetime default null collate utf8_unicode_ci comment '创建时间',
	`regenerator` bigint(20) default null collate utf8_unicode_ci comment '更新者(关联id)',
	`modifyDate` datetime default null collate utf8_unicode_ci comment '更新日期'
)engine InnoDB character set utf8 collate utf8_unicode_ci;
# 插入user表的数据
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('admin','系统管理员','1234567',2,'1983-10-10',1,'13688889999','成府路207号',1,'管理者',1,'2013-03-21 16:52:07',NULL,NULL);
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('liming','李明','0000000',2,'1983-12-10',15,'13688884457','区前门东大街9号',1,'普通员工',1,'2014-12-31 19:52:09',NULL,NULL);	
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('hanlubiao','韩路彪','0000000',2,'1984-12-10',20,'13688889999','北辰中心12号',1,'普通员工',1,'2013-03-21 16:52:07',NULL,NULL);
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('zhanghua','张华','0000000',2,'1993-06-15',33,'13544561111','学院路61号',2,'经理',1,'2013-02-11 10:51:17',NULL,NULL);
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('wangyang','王洋','0000000',2,'1973-06-15',32,'13544891111','西二旗16层',3,'普通员工',2,'2018-02-11 10:51:17',NULL,NULL);
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('zhaoyan','赵燕','0000000',1,'1990-06-15',28,'13599761111','回龙观小区10号楼',3,'普通员工',1,'2016-02-11 10:51:17',NULL,NULL);
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('sunlei','孙磊','0000000',2,'1989-06-15',30,'13522891111','管庄新月小区12楼',3,'普通员工',1,'2017-02-11 10:51:17',NULL,NULL);
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('sunxing','孙兴','0000000',2,'1999-06-15',22,'13566991111','建国门南大街10号',3,'普通员工',1,'2015-02-11 10:51:17',NULL,NULL);
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('zhangchen','张晨','0000000',2,'1989-10-15',26,'13523471111','管庄路口北柏林爱乐三期13号楼',2,'普通员工',1,'2016-02-11 11:51:17',1,'2016-04-14 14:15:36');
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('dengchao','邓超','0000000',2,'1988-06-15',27,'13523491111','北航家属院10号楼',3,'普通员工',1,'2019-12-11 10:51:17',1,'2020-04-14 14:15:36');
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('yangguo','杨过','0000000',2,'1986-06-15',30,'13525951111','北苑家园茉莉园20号楼',3,'普通员工',1,'2017-12-11 11:50:17',1,'2019-04-14 14:15:36');
insert into `smbms_user` (`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`age`,`phone`,`address`,`userRole`,`userRoleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('zhaomin','赵敏','0000000',1,'1996-06-15',26,'13569341111','昌平天通苑3区12号楼',3,'普通员工',2,'2018-12-10 10:12:17',NULL,NULL);
# 创建角色信息表(如果存在则删除)
drop table if exists `smbms_role`;
create table `smbms_role`(
	`id` bigint(20) primary key auto_increment comment 'ID',
	`roleCode` varChar(15) default null collate utf8_unicode_ci comment '角色编码',
	`roleName` varChar(15) default null collate utf8_unicode_ci comment '角色名称',
	`createBy` bigint(20) default null collate utf8_unicode_ci comment '创建者',
	`createionDate` datetime default null collate utf8_unicode_ci comment '创建时间',
	`regenerator` bigint(20) default null collate utf8_unicode_ci comment '修改者',
	`modifyDate` datetime default null collate utf8_unicode_ci comment '修改时间'
)engine InnoDB character set utf8 collate utf8_unicode_ci;
# 向角色信息表中插入数据
insert  into `smbms_role` (`roleCode`,`roleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('SMBMS_ADMIN','系统管理员',1,'2016-04-13 00:00:00',NULL,NULL);
insert  into `smbms_role` (`roleCode`,`roleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('SMBMS_MANAGER','经理',1,'2016-04-13 00:00:00',1,'2017-04-13 00:00:00');
insert  into `smbms_role` (`roleCode`,`roleName`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('SMBMS_EMPLOYEE','普通员工',2,'2016-04-13 00:00:00',2,'2017-08-13 00:00:00');
# 创建供应商信息表(存在就删除)
drop table if exists `smbms_provider`;
create table `smbms_provider`(
	`id` bigint(20) primary key auto_increment comment 'ID',
	`proCode` varChar(20) collate utf8_unicode_ci default null comment '供应商编码',
	`proName` varChar(20) collate utf8_unicode_ci default null comment '供应商名称',
	`proDesc` varChar(50) collate utf8_unicode_ci default null comment '供应商详细描述',
	`proContact` varChar(20) collate utf8_unicode_ci default null comment '供应商联系人',
	`proPhone` varChar(20) collate utf8_unicode_ci default null comment '供应商联系电话',
	`proAddress` varChar(50) collate utf8_unicode_ci default null comment '供应商地址',
	`proFax` varChar(20) collate utf8_unicode_ci default null comment '传真',
	`createBy` bigint(20) collate utf8_unicode_ci default null comment '创建者(ID)',
	`createionDate` datetime collate utf8_unicode_ci default null comment '创建时间',
	`regenerator` bigint(20) collate utf8_unicode_ci default null comment '更新者(ID)',
	`modifyDate` datetime default null collate utf8_unicode_ci comment '更新时间'
)engine InnoDB character set utf8 collate utf8_unicode_ci;
# 向供应商信息表中插入数据
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('BJ_GYS001','北京三木堂商贸有限公司','长期合作伙伴，主营产品:茅台、五粮液、郎酒、酒鬼酒、泸州老窖、赖茅酒、法国红酒等','张国强','13566667777','丰台区育芳园北路','010-58858787',1,'2013-03-21 16:52:07',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('HB_GYS001','石家庄帅益食品贸易有限公司','长期合作伙伴，主营产品:饮料、水饮料、植物蛋白饮料、休闲食品、果汁饮料、功能饮料等','王军','13309094212','河北省石家庄新华区','0311-67738876',1,'2016-04-13 04:20:40',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('GZ_GYS001','深圳市泰香米业有限公司','初次合作伙伴，主营产品：良记金轮米,龙轮香米等','郑程瀚','13402013312','广东省深圳市福田区深南大道6006华丰大厦','0755-67776212',1,'2014-03-21 16:56:07',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('GZ_GYS002','深圳市喜来客商贸有限公司','长期合作伙伴，主营产品：坚果炒货.果脯蜜饯.天然花茶.营养豆豆.特色美食.进口食品.海味零食.肉脯肉','林妮','18599897645','广东省深圳市福龙工业区B2栋3楼西','0755-67772341',1,'2013-03-22 16:52:07',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('JS_GYS001','兴化佳美调味品厂','长期合作伙伴，主营产品：天然香辛料、鸡精、复合调味料','徐国洋','13754444221','江苏省兴化市林湖工业区','0523-21299098',1,'2015-11-22 16:52:07',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('BJ_GYS002','北京纳福尔食用油有限公司','长期合作伙伴，主营产品：山茶油、大豆油、花生油、橄榄油等','马莺','13422235678','珠江帝景1号楼','010-588634233',1,'2012-03-21 17:52:07',2,'2017-04-23 10:11:11');
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('BJ_GYS003','北京国粮食用油有限公司','初次合作伙伴，主营产品：花生油、大豆油、小磨油等','王驰','13344441135','北京大兴青云店开发区','010-588134111',1,'2016-04-13 00:00:00',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('ZJ_GYS001','慈溪市广和绿色食品厂','长期合作伙伴，主营产品：豆瓣酱、黄豆酱、甜面酱，辣椒，大蒜等农产品','薛圣丹','18099953223','浙江省宁波市慈溪周巷小安村','0574-34449090',1,'2013-11-21 06:02:07',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('GX_GYS001','优百商贸有限公司','长期合作伙伴，主营产品：日化产品','李立国','13323566543','广西南宁市秀厢大道42-1号','0771-98861134',1,'2013-03-21 19:52:07',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('JS_GYS002','南京火头军信息技术有限公司','长期合作伙伴，主营产品：不锈钢厨具等','陈女士','13098992113','江苏省南京市浦口区浦口大道1号新城总部大厦A座903室','025-86223345',1,'2013-03-25 16:52:07',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('GZ_GYS003','广州市白云区美星五金制品厂','长期合作伙伴，主营产品：海绵床垫、坐垫、靠垫、海绵枕头、头枕等','梁天','13562276775','广州市白云区钟落潭镇福龙路20号','020-85542231',1,'2016-12-21 06:12:17',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('BJ_GYS004','北京隆盛日化科技','长期合作伙伴，主营产品：日化环保清洗剂，家居洗涤专卖、洗涤用品网、墙体除霉剂、墙面霉菌清除剂等','孙欣','13689865678','大兴区旧宫','010-35576786',1,'2014-11-21 12:51:11',1,'2015-10-28 10:52:07');
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('SD_GYS001','山东豪克华光联合发展有限公司','长期合作伙伴，主营产品：洗衣皂、洗衣粉、洗衣液、洗洁精、消杀类、香皂等','吴洪转','13245468787','山东济阳济北工业区仁和街21号','0531-53362445',1,'2015-01-28 10:52:07',NULL,NULL);
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('JS_GYS003','无锡喜源坤商行','长期合作伙伴，主营产品：日化品批销','周一清','18567674532','江苏无锡盛岸西路','0510-32274422',1,'2016-04-23 11:11:11',2,'2018-04-23 11:11:11');
insert  into `smbms_provider`(`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values('ZJ_GYS002','乐摆日用品厂','长期合作伙伴，主营产品：各种中、高档塑料杯，塑料乐扣水杯（密封杯）、保鲜杯（保鲜盒）、广告杯、礼品杯','王世杰','13212331567','浙江省金华市义乌市义东路','0579-34452321',1,'2016-08-22 10:01:30',NULL,NULL);
# 创建账单信息表(存在则删除)
drop table if exists `smbms_bill`;
create table `smbms_bill`(
	`id` bigint(20) primary key auto_increment comment 'ID',
	`billCode` varChar(20) default null collate utf8_unicode_ci comment '账单编码',
	`productName` varChar(20) default null collate utf8_unicode_ci comment '商品名称',
	`productDesc` varChar(20) default null collate utf8_unicode_ci comment '商品描述',
	`productUnit` varChar(10) default null collate utf8_unicode_ci comment '商品单位',
	`productCount` decimal(20,2) default null collate utf8_unicode_ci comment '商品数量',
	`totalPrice` decimal(20,2) default null collate utf8_unicode_ci comment '商品总额',
	`isPayment` int(10) default null collate utf8_unicode_ci comment '是否支付(1:未支付,2:已支付)',
	`createBy` bigint(20) collate utf8_unicode_ci default null comment '创建者(ID)',
	`createionDate` datetime collate utf8_unicode_ci default null comment '创建时间',
	`regenerator` bigint(20) collate utf8_unicode_ci default null comment '更新者(ID)',
	`modifyDate` datetime default null collate utf8_unicode_ci comment '更新时间',
	`providerId` bigint(20) default null comment '供应商ID'
)engine InnoDB character set utf8 collate utf8_unicode_ci;
# 向账单信息表中插入数据
insert  into `smbms_bill`(`id`,`billCode`,`productName`,`productDesc`,`productUnit`,`productCount`,`totalPrice`,`isPayment`,`createBy`,`createionDate`,`regenerator`,`modifyDate`,`providerId`) values (2,'BILL2016_002','香皂、肥皂、药皂','日用品-皂类','块','1000.00','10000.00',2,1,'2016-03-23 04:20:40',NULL,NULL,13),(3,'BILL2016_003','大豆油','食品-食用油','斤','300.00','5890.00',2,1,'2014-12-14 13:02:03',NULL,NULL,6),(4,'BILL2016_004','橄榄油','食品-进口食用油','斤','200.00','9800.00',2,1,'2013-10-10 03:12:13',NULL,NULL,7),(5,'BILL2016_005','洗洁精','日用品-厨房清洁','瓶','500.00','7000.00',2,1,'2014-12-14 13:02:03',NULL,NULL,9),(6,'BILL2016_006','美国大杏仁','食品-坚果','袋','300.00','5000.00',2,1,'2016-04-14 06:08:09',NULL,NULL,4),(7,'BILL2016_007','沐浴液、精油','日用品-沐浴类','瓶','500.00','23000.00',1,1,'2016-07-22 10:10:22',NULL,NULL,14),(8,'BILL2016_008','不锈钢盘碗','日用品-厨房用具','个','600.00','6000.00',2,1,'2016-04-14 05:12:13',NULL,NULL,14),(9,'BILL2016_009','塑料杯','日用品-杯子','个','350.00','1750.00',2,1,'2016-02-04 11:40:20',NULL,NULL,14),(10,'BILL2016_010','豆瓣酱','食品-调料','瓶','200.00','2000.00',2,1,'2013-10-29 05:07:03',NULL,NULL,8),(11,'BILL2016_011','海之蓝','饮料-国酒','瓶','50.00','10000.00',1,1,'2016-04-14 16:16:00',NULL,NULL,1),(12,'BILL2016_012','芝华士','饮料-洋酒','瓶','20.00','6000.00',1,1,'2016-09-09 17:00:00',NULL,NULL,1),(13,'BILL2016_013','长城红葡萄酒','饮料-红酒','瓶','60.00','800.00',2,1,'2016-11-14 15:23:00',NULL,NULL,1),(14,'BILL2016_014','泰国香米','食品-大米','斤','400.00','5000.00',2,1,'2016-10-09 15:20:00',NULL,NULL,3),(15,'BILL2016_015','东北大米','食品-大米','斤','600.00','4000.00',2,1,'2016-11-14 14:00:00',NULL,NULL,3),(16,'BILL2016_016','可口可乐','饮料','瓶','2000.00','6000.00',2,1,'2012-03-27 13:03:01',NULL,NULL,2),(17,'BILL2016_017','脉动','饮料','瓶','1500.00','4500.00',2,1,'2016-05-10 12:00:00',NULL,NULL,2),(18,'BILL2016_018','哇哈哈','饮料','瓶','2000.00','4000.00',2,1,'2015-11-24 15:12:03',NULL,NULL,2);
# 创建客户收货信息表(存在则删除)
drop table if exists `smbms_customer`;
create table `smbms_customer`(
  	`id` bigint(20) primary key auto_increment comment 'ID',
  	`userId` bigint(20) default null comment '用户ID(用户表关联字段)',
  	`customerContact` varchar(15) collate utf8_unicode_ci default null comment '联系人姓名',
  	`customerAddressDesc` varchar(50) collate utf8_unicode_ci default null comment '收货地址明细',
  	`customerpostCode` varchar(15) collate utf8_unicode_ci default null comment '邮编',
  	`customerPhone` varchar(20) collate utf8_unicode_ci default null comment '联系人电话',
  	`createBy` bigint(20) default null comment '创建者',
  	`createionDate` datetime default null comment '创建时间',
  	`regenerator` bigint(20) default null comment '修改者',
  	`modifyDate` datetime default null comment '修改时间'
)engine InnoDB character set utf8 collate utf8_unicode_ci;
# 向客户收货信息表中插入数据
insert into `smbms_customer`(`id`,`userId`,`customerContact`,`customerAddressDesc`,`customerpostCode`,`customerPhone`,`createBy`,`createionDate`,`regenerator`,`modifyDate`) values(1,1,'王丽','东城区东交民巷44号','100010','13678789999',1,'2016-04-13 00:00:00',1,'2018-04-13 00:00:00'),(2,1,'张红丽','丹棱街3号','100000','18567672312',1,'2016-04-13 00:00:00',2,'2018-12-13 00:00:00'),(3,1,'任志强','东城区美术馆后街23号','100021','13387906742',1,'2016-04-13 00:00:00',NULL,NULL),(4,2,'曹颖','朝阳门南大街14号','100053','13568902323',1,'2016-04-13 00:00:00',1,'2018-10-13 00:00:00'),(5,3,'李慧','西城区三里河路南三巷3号','100032','18032356666',1,'2016-04-13 00:00:00',NULL,NULL),(6,3,'王国强','顺义区高丽营镇金马工业区18号','100061','13787882222',1,'2016-04-13 00:00:00',2,'2018-10-18 00:00:00');