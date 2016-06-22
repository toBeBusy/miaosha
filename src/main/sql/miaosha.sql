--数据库初始化脚本

--创建数据库
CREATE database seckill;
--使用数据库
use seckill;
--创建秒杀库存表
CREATE TABLE SECKILL(
seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
name varchar(120) NOT NULL COMMENT '商品名称',
number int NOT NULL COMMENT '库存数量',
start_time timestamp NOT NULL COMMENT '秒杀开启时间',
end_time timestamp NOT NULL COMMENT '秒杀结束时间',
create_time timestamp NOT NULL default timestamp COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

--初始化数据
insert into
	seckill(name,number,start_time,end_time)
values
	('1000元秒杀iphone6',100,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
	('500元秒杀ipad2',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
	('300元秒杀小米4',300,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
	('200元秒杀红米Note',400,'2015-11-01 00:00:00','2015-11-02 00:00:00');
	
--秒杀成功明细表
create table success_killed(
	seckill_id bigint NOT NULL comment '秒杀商品id',
	user_phone bigint NOT NULL comment '用户手机号',
	state tinyint NOT NULL default -1 comment '状态提示：-1：无效； 0：成功； 1：已付款',
	create_time timestamp default current_timestamp comment '创建时间'，
	primary key (seckill_id,user_phone),
	key idx_create_time(create_time)
)
ENGINE=InnoDB default charset=utf8 comment='秒杀成功明细表';

--链接数据库控制台
mysql -uroot -p111111
