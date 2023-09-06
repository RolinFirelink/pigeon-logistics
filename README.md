![JDK17](https://img.shields.io/badge/JDK-17-green.svg )
![SpringBoot2.7.9](https://img.shields.io/badge/SpringBoot-2.7.9-green.svg )
![spring-statemachine3.2.0](https://img.shields.io/badge/SpringStatemachine-3.2.0-green.svg )
![MySQL8.0](https://img.shields.io/badge/MySQL-8.0.32-green.svg )
![Mybatis plus](https://img.shields.io/badge/MybatisPlus-3.5.3-green.svg )
![Druid](https://img.shields.io/badge/Druid-1.2.15-green.svg )
![OpenAPI](https://img.shields.io/badge/OpenAPI-3.0-green.svg )

### 😀介绍

数字鸽业物流系统服务端

TODO

- [X] 
  提高控制器健壮性，使用spring-boot-starter-validation来对请求参数进行校验。[资料](https://mp.weixin.qq.com/s?__biz=Mzg2OTcyMDc4NA==&mid=2247484417&idx=2&sn=66e7350f7fc60a849cd47ef877a39b58&chksm=ce99f3bef9ee7aa84134d8364264f4c8a1c71bbd6a95dba47036f0c5547d3557bbcd0c91201c&scene=178&cur_album_id=2543834365349412867#rd)
- [X] 提高Beanutils工具的健壮性。
- [ ] 使用PDF导出各个业务的表单

### 🏭系统功能

#### 发运管理

- [X] 物流订单管理（退/缺货处理？）
- [X] 运单管理
- [X] 放行条管理
- [X] 区域信息查询
- [ ] 地址管理
- [ ] 客户管理（查询交易情况、新增、更新、删除、信用评价）

#### 供应链管理（仓储管理）

屠宰厂、加工厂，一车一批次

- [ ] 采购管理
- [ ] 入库管理（收货单/进库单，签收入库、盘点入库、人工申请→员工审查确认入库；-下拉菜单：屠宰厂 加工厂）
- [X] 出库管理（出货单/出库单，签收出库；-下拉菜单：屠宰厂 加工厂）
- [ ] 库存盘点（相关资产）
- [ ] 物资调拨
- [ ] 供应商管理

#### 配送管理

- [x] 配货管理（线路计划-配送路径优化、出车计划）
- [ ] 调度管理
- [ ] 在途监控（物流定位）
- [ ] 车队管理（车辆管理、司机管理）
- [ ] 送货管理

### 报表

- [ ] TODO

### ⛰项目依赖

JDK: 17

SpringBoot: 2.7.9

MySQL: 8.0.32

SpringCloud:

SpringCloud Alibaba:

### 项目结构

```
pigeon-logistics
 ├── doc  # 文档与资料
 │   └── sql  # 数据库结构与数据转储文件
 ├── core  # 系统引擎模块，启动项目
 ├── common  # 系统公共依赖，主要放置配置文件和日志记录等
 ├── delivery  # 发运管理模块（物流订单管理，运单管理，地址管理，客户管理，放行条管理）
 ├── distribution  # 配送管理模块（线路信息，调度管理，计划管理，车队管理，执行监控）
 └── supply_chain  # 供应链管理（出/入库管理，供应商管理，采购管理，库存盘点）
```

### 🏃快速开始

#### 1 数据库配置

首先在本地创建 `pigeon_logistics` 数据库，选择好字符集：`utf8mb4`，排序规则：`utf8mb8_general_ci`
。然后在数据库下执行项目携带的SQL文件即可（包含各个表和表结构）。

#### 2 启动配置

将`application.yml`中的配置项`spring.profile.active=dev`更改为你修改的配置文件。
或者直接在`application-dev.yml`修改相关配置项。
找到`LogisticsApplication.java`启动即可。

需要在虚拟机参数加上命令：

```text
--add-opens java.base/sun.security.action=ALL-UNNAMED
```

#### 附：开启热部署

> 热部署：当项目中的代码发生了改变之后，IDE自动重新编译部署项目，达到自动更新运行中的项目目的（自动让修改的代码生效）。

**在 IntelliJ IDEA 社区版中开启 Spring Boot 项目热部署**

1 添加热部署工具依赖

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

2 在设置中开启项目自动编译

选中该项：`Build,Execution,Deployment` -> `Compiler` -> `Build project automatically`

3 开启运行中的热部署

打开注册表：`Alt` + `Ctrl` + `Shift` + `/` -> `Registry`

选中该项：`compiler.automake.allow.when.app.running`

>
老版本可能没有这个选项了。2021.2之后的版本需要在设置中选中该项：`Advanced Settings` -> `Compiler` -> `Allow auto-make to start even if developed application is currently running`

4 使用`Debug`启动（不是`Run`）

### 📓接口文档

使用Swagger3做接口文档，地址为：http://localhost:8017/doc.html
> IP地址跟端口号请根据实际情况修改。

### 🔗链接

#### Spring

https://spring.io

#### SpringBoot

https://spring.io/projects/spring-boot

开发手册：https://docs.spring.io/spring-boot/docs/2.7.0/reference/html/

API 文档：https://docs.spring.io/spring-boot/docs/2.7.0/api/

> 注：该项目依赖的 Springboot 版本是 2.7.0，该版本与之前版本差异较大。

#### SpringCloud

https://docs.gitcode.net/spring/guide/spring-cloud/documentation-overview.html

#### SpringCloud Alibaba

Nacos Spring Cloud：https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html

#### Swagger

https://swagger.io/docs/

https://swagger.io/specification/

> 注：该项目使用Swagger3，这个版本之前的差异较大。

#### druid

#### MyBatis

CRUD接口：https://baomidou.com/pages/49cc81

#### 四级省市数据库

来源：国家统计局
https://github.com/StarCompute/RegionData

插件：

### ❤️贡献者

丁欣裕、陈子昂、薛天常 、钟林钦

### 🎫许可证

