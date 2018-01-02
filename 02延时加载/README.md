### 延时加载

resultMap可以实现高级映射（使用association、collection实现一对一及一对多映射），**association、collection具备延迟加载功能。**

需求：
如果查询订单并且关联查询用户信息，如果先查询订单信息即可满足要求，当我们需要查询用户信息时再查询用户信息。
把对用户信息的按需取去查询就是延迟加载。

延迟加载：先从单表查询、需要时再从关联表去关联查询，大大提高数据库性能，因为查询单表要比关联查询多表速度要快。

### config

|||

### mapper.xml
需要定义两个mapper的方法对应的statement。

1、只查询订单信息
select * from orders
在查询订单的statement中使用association去延时加载下面的statement（关联查询用户信息）

2、关联查询用户信息
    通过上边查询到的订单信息中user_id去关联查询用户信息

### mapper.java




























