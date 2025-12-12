# 意见反馈组件

意见反馈组件收集和管理用户对系统的意见和建议，支持反馈提交、回复功能。

## 功能特性

- 用户意见反馈收集
- 意见反馈回复管理
- 反馈处理状态跟踪
- 用户评价反馈

## 使用方法

在项目的 pom.xml 中添加以下依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>feedback-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

## 核心组件

### 领域模型
- [Feedback](feedback-domain/src/main/java/com/particle/feedback/domain/feedback/Feedback.java)：意见反馈实体模型，包含反馈内容、联系信息、处理状态等属性
- [FeedbackReply](feedback-domain/src/main/java/com/particle/feedback/domain/reply/FeedbackReply.java)：意见反馈回复实体模型，包含回复内容、用户评价等属性
- FeedbackId：意见反馈标识符模型
- FeedbackReplyId：意见反馈回复标识符模型

### 应用服务
- IFeedbackApplicationService：意见反馈应用服务接口
- IFeedbackReplyApplicationService：意见反馈回复应用服务接口

### 自动配置
- [FeedbackAutoConfiguration](feedback-boot-starter/src/main/java/com/particle/feedback/FeedbackAutoConfiguration.java)：意见反馈组件自动配置类，负责扫描mapper和配置Swagger文档

### 数据访问
- 意见反馈相关数据仓储接口和实现
- MyBatis Mapper接口

## 自动配置

当classpath下存在相关依赖时，[FeedbackAutoConfiguration](feedback-boot-starter/src/main/java/com/particle/feedback/FeedbackAutoConfiguration.java)会自动配置以下内容：
- 扫描com.particle.feedback.infrastructure.feedback.mapper和com.particle.feedback.infrastructure.reply.mapper包下的Mapper接口
- 注册意见反馈管理相关的REST API文档

## 使用说明

意见反馈组件帮助产品团队收集用户反馈，建立有效的沟通渠道，持续改进产品质量。
