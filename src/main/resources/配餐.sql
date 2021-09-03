create database if not exists food_pairing CHARSET=utf8;
use food_pairing;

-- 用户表
create table if not exists sys_user(

    `id` bigint comment '主键',
    `name` varchar(40) not null comment '用户名',
    `account` varchar(40) not null comment '账户名',
    `password` varchar(20) not null comment '明文密码',
    `encryption` varchar(200) not null comment '加密密码',
    `create_date` datetime not null comment '创建时间',
    `is_delete` int  default  '0'   comment '逻辑删除',
    primary  key (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="用户表";

-- 角色表
create table if not exists  sys_role(
    `id` bigint comment '主键' primary  key,
    `name` varchar(40) not null comment '角色名'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="角色表";

-- 用户与角色关系表
create table if not exists  sys_user_role_relation(
    `id` bigint comment '主键' primary  key,
    `role_id` bigint comment '角色id',
    `user_id` bigint comment '用户id'

)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="用户与角色关系表";

-- 权限表
create table if not exists  sys_permission(
    `id` bigint primary  key comment '主键' ,
    `code` varchar(20) not null comment '权限标识',
    `name` varchar(40) not null comment '权限名'

)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="权限表";

-- 角色权限关系表
create table if not exists  sys_role_permission_relation(
    `id` bigint primary  key comment '主键' ,
    `role_id` bigint comment '角色id',
    `permission_id` bigint comment '用户id'

)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="角色权限关系表";


--  路由表
create table if not exists  sys_url(
    `id` bigint  primary  key comment '主键' ,
    `code` varchar(20) not null comment '权限标识',
    `url` varchar(300) not null comment '路由'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="路由表";



-- 供应商
create table if not exists  tb_supplier(
    `id` bigint primary  key comment '主键' ,
    `name` varchar(80) not null comment '供应商名称',
    `phone_num` varchar(11) not null comment '手机号',
    `address` varchar(300) not null comment '地址',
    `certification_image` varchar(200) default '' comment '资质图片',
    `expiration` date not null  comment '到期时间',
    `is_delete` int default '0' comment '逻辑删除  (0代表未删除，1代表删除)'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="供应商";


-- 采购单
CREATE TABLE IF NOT EXISTS  tb_purchase(
    `id` BIGINT PRIMARY  KEY  COMMENT '主键 订单号' ,
    `total_money` DECIMAL(18,2)  NOT NULL COMMENT '总金额',
    `pay_status` INT DEFAULT '0' COMMENT '支付状态',
    `type` INT  NOT NULL DEFAULT '0' COMMENT '采购类型（0代表临时，1代表批量）',
    `worker_id` BIGINT NOT NULL  COMMENT '采购人id',
    `supplier_id` BIGINT NOT NULL COMMENT '供应商id',
    `comment` VARCHAR(200) COMMENT '备注',
    `purchase_date` DATE NOT NULL COMMENT '采购日期',
    `distribution_start`  DATE NOT NULL COMMENT '配货开始日期',
    `distribution_end` DATE NOT NULL COMMENT '配货结束时间',
    `create_date` DATETIME NOT NULL COMMENT '创建时间',
    `update_date` DATETIME NOT NULL COMMENT '最后修改时间',
    `is_delete` INT DEFAULT  '0' COMMENT '逻辑删除(0代表未删除，1代表删除)'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="采购单";


-- 采购单明细
create table if not exists  tb_purchase_item(

    `id` bigint primary  key  comment '主键' ,
    `ingredient_id` bigint not null comment '食材id',
    `purchase_id` bigint not null comment '订单号',
    `purchase_price` decimal(10,2) not null comment '进货价',
    `num` int not null comment '进货数量'

)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="采购单明细";

-- 食材分类
create table if not exists  tb_food_category(
    `id` bigint primary  key  comment '主键' ,
    `name` varchar(30) not null comment '分类名称'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="食材分类";

-- 食材计量单位
CREATE TABLE IF NOT EXISTS  tb_food_unit(
    `id` BIGINT PRIMARY  KEY  COMMENT '主键' ,
    `unit` VARCHAR(20) NOT NULL COMMENT '单位'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="食材计量单位";

-- 食材表
-- 食材表
CREATE TABLE IF NOT EXISTS  tb_food(
    `id` BIGINT PRIMARY  KEY  COMMENT '主键' ,
    `name` VARCHAR(40) NOT NULL  COMMENT '食物名称',
    `category_id` BIGINT NOT NULL COMMENT '分类id',
    `specification` VARCHAR(30) COMMENT '规格',
    `brand` VARCHAR(40) NOT NULL COMMENT '品牌',
    `unit` INT NOT NULL COMMENT '分配单位（克）',
    `unit_name` VARCHAR(20) COMMENT '单位',
    `image` VARCHAR(200)  COMMENT '图片',
    `market_price` DECIMAL(10,2) NOT NULL COMMENT '进货价格',
    `purchase_price`  DECIMAL(10,2) NOT NULL COMMENT '销售价格',
    `stock` INT DEFAULT '0' COMMENT '库存',
    `update_version` INT DEFAULT '0' COMMENT '乐观锁',
    `is_delete` INT DEFAULT '0' COMMENT '逻辑删除(0代表未删除，1代表删除)'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="食材";

-- 配送路线表
create table if not exists  tb_distribution_path(
    `id` bigint primary  key  comment '主键' ,
    `address` varchar(300) not null comment '配送地址'

)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="配送路线表";

-- 配送类型
create table if not exists  tb_distribution_type(
    `id` bigint primary  key  comment '主键' ,
    `name` varchar(40) not null comment '类型名称',
    `day` int  not null comment '提前配送天数'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="配送类型";

-- 配送区域
create table if not exists  tb_distribution_scope(
    `id` bigint primary  key  comment '主键' ,
    `scope` varchar(100) not null comment '配送范围'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="配送区域";

-- 配送小组
create table if not exists  tb_distribution_group(
    `id` bigint primary  key  comment '主键' ,
    `group_name` varchar(100) not null comment '小组名称'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="配送小组";

-- 客户类型
create table if not exists  tb_client_type(
    `id` bigint primary  key  comment '主键' ,
    `type_name` varchar(30) not null comment '客户类型名称'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="客户类型";

-- 配送客户
CREATE TABLE IF NOT EXISTS  tb_distribution_client(
                                                      `id` BIGINT PRIMARY  KEY  COMMENT '主键' ,
                                                      `name` VARCHAR(50)  NOT NULL COMMENT '客户名称',
    `phone_name`  VARCHAR(50)  NOT NULL COMMENT '联系人名称',
    `phone_num` VARCHAR(11) NOT NULL COMMENT '联系电话',
    `address` VARCHAR(300) NOT NULL COMMENT '配送地址',
    `client_type` BIGINT DEFAULT '0' COMMENT '0普通，1vip1推类',
    `type_id` BIGINT NOT NULL COMMENT '配送类型id',
    `group_id` VARCHAR(100) NOT NULL COMMENT '配送小组',
    `path_id` VARCHAR(300) NOT NULL  COMMENT '配送路线',
    `scope_id` VARCHAR(100)  NOT NULL COMMENT '配送区域',
    `is_delete` INT DEFAULT '0' COMMENT '逻辑删除(0代表未删除，1代表删除)',
    `distributionSorting` INT DEFAULT '1' COMMENT '配货分拣(0代表分拣，1不分拣)'
    )ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="配送客户";

-- 餐别类型维护
create table if not exists  tb_meal_rank(
    `id` bigint primary  key  comment '主键' ,
    `name` varchar(30) not null comment '名称',
    `seq` int default '1' comment '显示顺序',
    `category_name` varchar(30) not null  comment '统计分类'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="餐别类型维护";

-- 客户餐别
create table if not exists  tb_client_meal_rank(
    `id` bigint primary  key  comment '主键' ,
    `client_id`  bigint  not null comment '客户id',
    `meal_rank_id` bigint not null comment '餐别id',
    `meal_price` decimal(10,2) not null comment '餐标（每餐价格）',
    `person_num` int not null comment '用餐人数'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="客户餐别";

-- 排餐订单
create table if not exists  tb_meal_order(
    `id` bigint primary  key  comment '主键' ,
    `client_id` bigint not null comment '客户id',
    `meal_price` decimal(10,2) not null comment '餐标（每餐价格）',
    `person_num` int not null comment '用餐人数' ,
    `total_price` decimal(18,2) not null comment '总金额',
    `meal_rank_id` bigint not null comment '餐别id',
    `meal_time` date not null comment '用餐时间',
    `carriage_type` INT DEFAULT 0 COMMENT '配送标志（0代表未配送，1代表配送中）'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="排餐订单";

-- 排餐订单明细
create table if not exists  tb_meal_order_item(
    `id` bigint primary  key  comment '主键' ,
    `meal_plan_id` bigint not null comment '排餐订单号',
    `food_id` bigint not null comment '食材id',
    `market_price` decimal(10,2) not null comment '销售价格',
    `food_num` int not null comment '食材数量',
    `total_quantity` int not null comment '总量'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci COMMENT="排餐订单明细";
