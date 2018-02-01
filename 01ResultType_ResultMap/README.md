 ### 创建我们需要的数据库表结构：
 
设置客户端编码

 ```sql
set character_set_client=utf8;
```
 
 1、创建用户user表：
 
```sql
CREATE TABLE `userAndOrders`.`user`( 
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` VARCHAR(32) NOT NULL COMMENT '用户名称',
  `birthday` DATE COMMENT '生日',
  `sex` CHAR(1) COMMENT '性别',
  `address` VARCHAR(256) COMMENT '地址', PRIMARY KEY (`id`) 
  ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;
  
INSERT INTO `userAndOrders`.`user` (`username`,`birthday`, `sex`,`address`) VALUES('李洋','2018-01-30','男','北京市朝阳区') ;
INSERT INTO `userAndOrders`.`user` (`username`,`birthday`, `sex`,`address`) VALUES('黄骅','2018-01-30','女','北京市朝阳区') ;
INSERT INTO `userAndOrders`.`user` (`username`,`birthday`, `sex`,`address`) VALUES('张天阳','2018-01-30','男','北京市朝阳区') ;
INSERT INTO `userAndOrders`.`user` (`username`,`birthday`, `sex`,`address`) VALUES('广南华','2018-01-30','男','北京市朝阳区') ;  
```
2、创建商品items表

```sql
CREATE TABLE `userAndOrders`.`items`(  
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL COMMENT '商品名称',
  `price` FLOAT(10,1) NOT NULL COMMENT '商品价格',
  `detail` TEXT COMMENT '商品详情',
  `pic` VARCHAR(512) COMMENT '商品图片',
  `createtime` DATETIME COMMENT '生产日期',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `userAndOrders`.`items` (`name`,`price`) VALUES('羽绒服',240) ;
INSERT INTO `userAndOrders`.`items` (`name`,`price`) VALUES('大眼镜',70) ;
INSERT INTO `userAndOrders`.`items` (`name`,`price`) VALUES('棉拖鞋',40) ;
```

3、创建订单orders表

```sql

CREATE TABLE `userAndOrders`.`orders`(		/* Table name not specified */  
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL COMMENT '下单用户id',
  `number` VARCHAR(32) NOT NULL COMMENT '订单号',
  `createtime` DATETIME NOT NULL COMMENT '创建订单时间',
  `note` VARCHAR(100) COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `userAndOrders`.`orders` ( `user_id`,`number`,`createtime`,`note`) VALUES(1,'20171231','2018-01-31 00:05:28', '测试001') ;
INSERT INTO `userAndOrders`.`orders` ( `user_id`,`number`,`createtime`,`note`) VALUES(2,'20171231','2018-01-31 00:05:28', '测试002') ;
INSERT INTO `userAndOrders`.`orders` ( `user_id`,`number`,`createtime`,`note`) VALUES(3,'20171231','2018-01-31 00:05:28', '测试003') ;
INSERT INTO `userAndOrders`.`orders` ( `user_id`,`number`,`createtime`,`note`) VALUES(4,'20171231','2018-01-31 00:05:28', '测试004') ;
INSERT INTO `userAndOrders`.`orders` ( `user_id`,`number`,`createtime`,`note`) VALUES(1,'20171231','2018-01-31 00:05:28', '测试005') ;
INSERT INTO `userAndOrders`.`orders` ( `user_id`,`number`,`createtime`,`note`) VALUES(4,'20171231','2018-01-31 00:05:28', '测试006') ;
```

4、创建订单详情order_detail表

```sql
CREATE TABLE `userAndOrders`.`order_detail`(  
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `orders_id` INT(11) NOT NULL COMMENT '订单id',
  `item_id` INT(11) NOT NULL COMMENT '商品id',
  `item_num` INT(11) COMMENT '商品购买数量',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `userAndOrders`.`order_detail` (`orders_id`,`item_id`) VALUES ('1', '1') ;
INSERT INTO `userAndOrders`.`order_detail` (`orders_id`,`item_id`) VALUES ('1', '2') ;
INSERT INTO `userAndOrders`.`order_detail` (`orders_id`,`item_id`) VALUES ('2', '2') ;
INSERT INTO `userAndOrders`.`order_detail` (`orders_id`,`item_id`) VALUES ('3', '1') ;
INSERT INTO `userAndOrders`.`order_detail` (`orders_id`,`item_id`) VALUES ('4', '3') ;
```

### 一对一查询
 
 ```java
public interface OrderCustomI {
    public List<Order> findAllOrders();
    public List<OrderCustom> findOrdersByUser();
    public List<OrderResultMap> findOrdersByUserResultMap();
}
```
```xml
<mapper namespace="com.shi.mapping.UserMapperI">
    <!--订单信息加用户信息-->
    <resultMap id="resultMapOrder" type="com.shi.pojo.OrderResultMap">
        <id column="id" property="id"/>
        <result column="number" property="number"/>
        <result column="note" property="note"/>
        <!--用户信息-->
        <association property="user" javaType="com.shi.bean.User">
            <id column="user_id" property="id"/>
            <result column="username" property="username"/>
            <result column="address" property="address"/>
        </association>
    </resultMap>
    <!--使用resultType查询订单信息-->
    <select id="findAllOrders" resultType="com.shi.bean.Order">
        select * from orders
    </select>
    <!--使用resultType查询订单以及和订单相关联的用户信息-->
    <select id="findOrdersByUser"  resultType="com.shi.pojo.OrderCustom">
      SELECT orders.*, user.`username`, user.`sex`, user.address FROM orders,USER WHERE orders.`user_id` = user.`id`
    </select>
    <!--使用resultMap查询订单以及和订单相关联的用户信息-->
    <select id="findOrdersByUserResultMap" resultMap="resultMapOrder">
        SELECT orders.*, user.`username`, user.`sex`, user.address FROM orders,USER WHERE orders.`user_id` = user.`id`
    </select>
</mapper>
```
 
#### 1、resultType：
 
 使用resultType实现较为简单，如果pojo中没有包括查询出来的列名，需要增加列名对应的属性，即可完成映射。
 如果没有查询结果的特殊要求，建议使用resultType
 
#### 2、resultMap：
  
需要单独定义resultMap，实现有点麻烦，如果对查询结果有特殊要求，使用resultMap可以完成将关联查询映射pojo的属性中。
**resultMap可以实现延迟加载，resultType无法实现延时加载。**
  
### 一对多查询
  
#### 1、查询订单信息   加用户信息  加订单详情信息
  
```java
public interface OrderCustomI {
    public List<OrderResultMap02> findOrdersByUserResultMap02();
}
```
需要使用的resultMap对象：
```xml
<mapper namespace="com.shi.mapping.UserMapperI">
    <!--订单信息   加用户信息  加订单详情信息-->
    <resultMap id="resultMapOrder02" type="com.shi.pojo.OrderResultMap02" extends="resultMapOrder">
        <!-- 上面使用了extends继承了resultMapOrder，所以这里不需要再配置order和user的属性信息-->
        <!--订单明细属性信息-->
        <collection property="listData" ofType="com.shi.bean.OrderDetail">
            <id column="id" property="id"/>
            <result column="orders_id" property="orders_id"/>
            <result column="item_id" property="item_id"/>
            <result column="item_num" property="item_num"/>
        </collection>
     </resultMap>
    <!--使用resultMap查询订单，订单相关联的用户信息，以及订单详情-->
    <select id="findOrdersByUserResultMap02" resultMap="resultMapOrder02">
    SELECT orders.*, user.`username`, user.`sex`, user.address, order_detail.* FROM orders,USER,order_detail WHERE orders.`user_id` = user.`id`
    </select>
</mapper>

```

#### 2、查询用户信息，订单信息，订单详情信息，以及订单商品信息

```java
public interface OrderCustomI {
    public List<UserResultMap> findOrdersByResultMapUser();  
}
```

```xml
<mapper namespace="com.shi.mapping.UserMapperI">
    <!--订单信息   加用户信息  加订单详情信息-->
    <resultMap id="resultMapUser" type="com.shi.pojo.UserResultMap">
        <!-- 用户信息 -->
        <id column="id" property="id"/>
        <result column="username" property="username"/>
        <result column="address" property="address"/>
        <!--订单信息-->
        <collection property="listData" ofType="com.shi.pojo.UserOrder">
            <id column="id" property="id"/>
            <result column="number" property="number"/>
            <result column="user_id" property="user_id"/>
            <result column="note" property="note"/>

            <collection property="listData" ofType="com.shi.pojo.UserOrderDetail">
                <id column="id" property="id"/>
                <result column="orders_id" property="orders_id"/>
                <result column="item_id" property="item_id"/>
                <result column="item_num" property="item_num"/>
                <association property="listData" javaType="com.shi.bean.Item">
                    <id column="id" property="id"/>
                    <result column="name" property="name"/>
                    <result column="price" property="price"/>
                </association>
            </collection>
        </collection>
    </resultMap>
    <!--查询用户及用户购买的商品数量-->
    <select id="findOrdersByResultMapUser" resultMap="resultMapUser">
        SELECT orders.*
        , user.`username`
        , user.`sex`
        , user.address
        , order_detail.*
        , items.*
        FROM orders, USER, order_detail, items
        WHERE orders.user_id = user.id AND order_detail.orders_id = orders.id AND order_detail.item_id = items.id
    </select>
</mapper>
```

### resultMap小结

#### 1、resultType：
作用：
    将查询结果按照sql列名pojo属性名一致性映射到pojo中。
场合：
    常见一些明细记录的展示，比如用户购买商品明细，将相关查询信息全部展示在页面时，
此时可直接使用resultType将每一条记录映射到pojo中，在前端页面遍历list(list中是pojo)即可。    

#### 2、resultMap：

使用association和collection完成一对一和一对多高级映射。

##### association： 
作用：
    将关联查询信息映射到一个pojo对象中。
场合：
    为了方便查询关联信息可以使用association将关联订单信息映射为用户对象的pojo属性中，
比如：查询订单及关联用户信息。
    使用resultType无法将查询结果映射到pojo对象的pojo属性中，根据对结果集查询遍历的需要
选择使用resultType还是resultMap。

##### collection：
作用：
    将关联查询信息映射到一个list集合中。
场合：
    为了方便查询遍历关联信息可以使用collection将关联信息映射到list集合中，比如：查询用户权限范围
模块下的菜单，可使用collection将模块映射到模块list中，将菜单列表映射到模块对象的list属性中，这样做的
目的也是方便对查询结果集进行遍历查询。
    如果使用resultType无法将查询结果映射到list集合中。    