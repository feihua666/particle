# 部门组件

部门组件提供对组织机构的管理及数据和功能支持。

本组件认为用户归属为部门，其中部门是一个树结构。
如果用户有其它归属部门可以使用部门树扩展，部门树的使用场景可以理解为一个用户可以根据业务不同有不同的部门。

## 功能特性

- 部门组织架构管理
- 部门树形结构维护
- 用户部门关系管理
- 部门权限继承
- 多维度部门分类

## 使用方法

在项目的 pom.xml 中添加以下依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>dept-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 配置选项


## 核心组件

- 部门实体模型
- 部门树形结构管理
- 用户部门关系服务
