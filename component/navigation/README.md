# 导航组件

导航组件管理系统中的导航菜单和链接，支持多维度导航结构和个性化定制。

## 使用方法

在项目的 pom.xml 中引入依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>navigation-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 功能特性

- 导航菜单管理
- 链接地址管理
- 动态导航生成

## 核心组件

- [NavigationAutoConfiguration](navigation-boot-starter/src/main/java/com/particle/navigation/NavigationAutoConfiguration.java)：导航自动配置类
- [NavigationCategory](navigation-domain/src/main/java/com/particle/navigation/domain/NavigationCategory.java)：导航分类领域模型
- [NavigationSite](navigation-domain/src/main/java/com/particle/navigation/domain/NavigationSite.java)：导航网站领域模型
- [NavigationSiteCategoryRel](navigation-domain/src/main/java/com/particle/navigation/domain/NavigationSiteCategoryRel.java)：导航网站分类关系领域模型
- [NavigationStaticDeploy](navigation-domain/src/main/java/com/particle/navigation/domain/NavigationStaticDeploy.java)：导航静态部署领域模型
- [NavigationFriendshipLink](navigation-domain/src/main/java/com/particle/navigation/domain/NavigationFriendshipLink.java)：导航友情链接领域模型
- [NavigationSubmit](navigation-domain/src/main/java/com/particle/navigation/domain/NavigationSubmit.java)：导航提交领域模型
- [NavigationSiteTag](navigation-domain/src/main/java/com/particle/navigation/domain/NavigationSiteTag.java)：导航网站标签领域模型
- [NavigationSiteTagRel](navigation-domain/src/main/java/com/particle/navigation/domain/NavigationSiteTagRel.java)：导航网站标签关系领域模型

## 自动配置

导航组件由 [NavigationAutoConfiguration](navigation-boot-starter/src/main/java/com/particle/navigation/NavigationAutoConfiguration.java) 类完成自动配置，主要提供了以下功能：

- 注册导航相关Mapper扫描路径
- 提供基于Swagger的导航接口文档

## 使用说明

导航组件为前端提供统一的导航结构。
