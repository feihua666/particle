# 简介
[Particle](https://gitee.com/feihua666/particle)是一个基于SpringBoot2.x、vuejs，前后端分离的快速开发脚手架。  
后端采用springboot以最小单位拆分业务组件，旨在快速开发（组装）新模块/项目。  

## 主要技术点
**后端**：swagger接口文档、表单验证、MyBatisPlus、springSecurity、springboot、lombok、mapstruct  

**前端**：vuejs、element-ui  
## 理念
基于DDD领域驱动设计理念，每个功能模块、每个聚合根、每个方法精心考量，细致打磨！
## 软件架构
本项目旨在开发基础的、常用的业务功能，使其组件化的curd操作，以快速组装可用的新模块或项目。  
基于成熟的MVC三层架构、DDD领域模型设计的四层拆分，结构清晰明了。

整体分为两部分：

1.  组件  
    组件是组装模块的最小单位
2.  模块  
    模块是服务运行的最小单位，可以单独发布或组装更大的模块发布
