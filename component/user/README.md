# 用户组件

用户组件提供基础的用户相关支持，包括用户登录、密码等相关功能。

注意：
建表语句脚本中的两个字段已废弃，将自动使用部门业务组件中的部门用户关系表来替代，但实体中的`comp_id`和`dept_id`字段依然有效程序中有自动处理
`comp_id` bigint DEFAULT NULL COMMENT '公司id，冗余字段，由dept_id对应公司派生',
`dept_id` bigint DEFAULT NULL COMMENT '部门id',

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>user-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 功能特性

- 用户账号管理
- 用户认证授权
- 密码安全管理
- 用户信息维护
- 用户状态管理
- 登录标识管理
- 登录记录管理

## 核心组件

- 用户实体模型
- 认证授权服务
- 密码管理器
- 用户信息服务
- 登录控制器
- 登录标识管理
- 用户密码管理
- 登录记录管理

## 菜单功能

- 用户管理（根菜单）
  - 用户管理页面
    - 用户查询、创建、删除、修改功能
    - 重置用户密码功能
  - 登录标识页面
    - 用户登录标识查询、创建、删除、修改功能
    - 重置账号密码功能
  - 用户密码页面
    - 用户密码查询、创建、删除、修改功能
  - 登录记录页面
    - 用户登录记录查询、删除功能

## 自动配置

组件基于 Spring Boot AutoConfiguration 机制，在应用启动时会自动配置以下内容：
- 扫描并注册相关 Mapper
- 配置 Swagger 接口文档信息

## 字典数据

组件提供以下字典数据：
- 性别：男(m)、女(f)、未知(unknown)
- 用户来源：微信公众号、微信小程序、今日头条、新浪微博、后台添加、系统初始化
- 用户分类：其它分类(other)
- 用户账号类型：帐号(front_account)、手机号登录(front_mobile)、邮箱登录(front_email)、微信登录(front_wx)、QQ登录(front_qq)、抖音登录(front_douyin)、新浪微博登录(front_sina_weibo)、微信公众号登录(wx_mp)、微信小程序登录(wx_ma)、微信小程序手机号登录(wx_ma_mobile)
- 用户生效触发器标识：成功登录(login_success)

## 使用说明

用户组件为系统提供核心的用户管理功能，是系统安全和权限控制的基础。
