# 全局AI模块

该模块基于 Spring Boot 和 Spring AI 提供人工智能相关的功能支持。

## 功能特性

1. **AI 集成支持**：集成 Spring AI，提供对各种 AI 模型的访问能力
2. **向量数据库支持**：内置 Milvus 向量数据库支持，便于处理向量相似度搜索

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-ai-boot-starter</artifactId>
</dependency>
```

### 配置选项

### 启用/禁用配置

1. 禁用AI自动装配功能：
   ```yaml
   spring.ai.vectorstore.type=none
   spring.ai.model.chat=none
   ```

### 核心组件

该模块基于 Spring AI 实现，主要包括：
- Spring AI 相关自动配置类
- Milvus 向量数据库集成支持

## 依赖组件

- Spring AI 相关依赖
- Milvus 向量数据库客户端
- Spring Boot Starter Web
- Spring Boot Security（可选）
