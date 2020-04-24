# spring-cloud-demo测试

- register
  - eureka注册中心

------

- configservice
  - 远程配置中心  可git  svn   本地（native）
  - 当前为私有 https://github.com/SultanDebug/config.git

------

- framework
  - kafkacore：kafka基础包引入、基本操作
  - mybatiscore：mybatis包引入、配置类
  - rabbitmqcore：rabbitmq包引入、连接配置
  - rediscore：redis包引入、连接配置、分布式锁、pubsub、基本操作
  - zkcore：zk包引入、连接配置、基础操作

------

- gateway、cloud-gateway
  - gateway：zuul网关
  - cloud-gateway：springcloud-gateway网关

------

- interface
  - feign接口集合
  - pojo管理

------

- micservice
  - all-deicovery：注册中心切换和多实例随机端口测试,配置本地和远程切换，eureka、zookeeper测试，feign、feigninterceptor（threadlocal-token）测试
  - all-provider：all-deicovery配套测试服务
  - democlient：feign测试服务消费方，websocket多实例方案
  - demoservice：redis、ssdb（**异常**）、rabbitmq、zk、feign、feigninterceptor（threadlocal-token）综合测试
  - feignservice：demoservice配套测试服务，mybatis引入测试
  - kafkaservice：kafka数据分片、客户组测试
  - nettyservice：nettyAPI测试，NIO/BIO服务器，rpc，设计模式，（条件注解、代理、lettuce-jedis性能测试、布隆过滤器、序列化反序列化、zk leader选举、zk分布式锁、zk队列、zk上级节点监听、rocketmq事物消息）
  - rabbitservice：ack异常测试，topic分发测试
  - zkservice：curatorFramework API测试

------

- spring-boot-starter-hzqtest
  - starter学习

------

###### 广积粮 缓上床 持续完善服务框架
