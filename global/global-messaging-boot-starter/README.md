# 全局消息模块

该模块主要用于MQ异步消息支持，基于Spring Cloud Stream实现消息生产和消费的统一使用方式，屏蔽了底层中间件的差异。

## 功能特性

1. **消息中间件抽象**：屏蔽底层中间件差异，支持Kafka和RabbitMQ
2. **本地异步支持**：在没有Kafka和RabbitMQ依赖时，支持本地异步消息处理
3. **统一接口**：消息生产和消费使用统一的接口方式
4. **持久化支持**：支持消息事件的持久化存储

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-messaging-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
spring:
  cloud:
    stream:
      kafka:
        binder:
          brokers: localhost:9092
```

### 核心组件

- MessageEventRepository：消息事件仓储接口
- 消息发布器：负责将消息发布到消息中间件
- 消息消费包装器：统一处理消息消费逻辑
- 消费记录器：记录消息消费情况

## 依赖组件

- Spring Cloud Stream
- Kafka/RabbitMQ客户端（可选）
- Spring Boot Starter JDBC（用于消息持久化）

## 示例代码

```java
// 发送消息
@Service
public class MessageService {
    @Autowired
    private MessagePublisher messagePublisher;
    
    public void sendMessage(String message) {
        MessageEvent event = new MessageEvent();
        event.setContent(message);
        messagePublisher.publish(event);
    }
}

// 接收消息
@Component
public class MessageConsumer {
    @StreamListener("input")
    public void handleMessage(MessageEvent event) {
        // 处理消息
    }
}
```