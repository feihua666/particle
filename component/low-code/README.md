# 低代码组件

主要目的是替代 generator 组件。
generator 组件可以很好的生成后端代码，但不能生成对其对应的前端代码。在想加前端生成时发现代码可理解性差，
强依赖于 mybatis plus generator，充斥一大堆不易理解的模板改起来很费劲！
注：该组件也是由 generator 生成后二开的。
所以起名为低代码，可以根据图形化界面操作，更易理解，且代码模板碎片化，改起来方便。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>low-code-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 核心组件

- [LowcodeAutoConfiguration](low-code-boot-starter/src/main/java/com/particle/lowcode/LowcodeAutoConfiguration.java)：低代码自动配置类
- 可视化配置界面
- 代码生成引擎
- 模板管理器
- 前端代码生成器
- 后端代码生成器

## 菜单功能

开发者低代码：
- 低代码基础配置
  - 低代码数据源管理
    - 低代码数据源查询
    - 低代码数据源添加
    - 低代码数据源删除
    - 低代码数据源修改
  - 低代码模型管理
    - 低代码模型查询
    - 低代码模型添加
    - 低代码模型删除
    - 低代码模型修改
    - 加载载模型项
  - 低代码模型项管理
    - 低代码模型项查询
    - 低代码模型项添加
    - 低代码模型项删除
    - 低代码模型项修改
  - 低代码片段模板管理
    - 低代码片段模板管理查询
    - 低代码片段模板管理添加
    - 低代码片段模板管理删除
    - 低代码片段模板管理修改
    - 片段模板渲染测试
    - 片段模板复制
- 低代码生成配置
  - 低代码生成管理
    - 低代码生成管理查询
    - 低代码生成管理添加
    - 低代码生成管理删除
    - 低代码生成管理修改
    - 重新加载模型json数据
    - 设计和渲染
  - 低代码模型项设计
    - 低代码模型项设计查询
    - 低代码模型项设计修改

## 自动配置

低代码组件由 [LowcodeAutoConfiguration](low-code-boot-starter/src/main/java/com/particle/lowcode/LowcodeAutoConfiguration.java) 类完成自动配置，主要提供了以下功能：

- 注册低代码相关Mapper扫描路径
- 提供基于Swagger的低代码接口文档


## 使用说明

低代码组件通过图形化界面配置，一键生成前后端代码，大幅提升开发效率。
