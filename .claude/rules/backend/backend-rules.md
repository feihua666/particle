
# 后端开发规则

## 技术栈
- Spring Boot 3.x
- MyBatis Plus
- MySQL 8.0+
- Java 17+
- Maven 3.9+
- Log4j2

## 目录结构
```
particle/
├── common/                 # 通用后端业务组件基础规范模块
│   ├── common-adapter/
│   ├── common-app/
│   ├── common-client/
│   ├── common-constant/
│   ├── common-domain/
│   └── common-infrastructure/
├── component/              # 后端业务组件模块
│   └── {业务名}/
│       ├── {业务名}-adapter/
│       ├── {业务名}-app/
│       ├── {业务名}-client/
│       ├── {业务名}-domain/
│       └── {业务名}-infrastructure/
├── global/                 # 全局后端功能模块
│   └── global-{功能名}-boot-starter/
└── project/                # 项目应用模块，聚合、启动
```

## 代码简化
- 统一 pojo 类使用 Lombok 注解简化代码
    - 强制使用 `@Data` 代替 getter/setter
    - 适当使用 `@Builder` 实现建造者模式（如有需要）
    - 禁止使用 `@NoArgsConstructor` / `@AllArgsConstructor` 生成构造函数

## 日志规范
- 统一使用 `@Slf4j` 注解打印日志
- 日志级别: debug/info/warn/error
- 示例: `log.info("用户登录: {}", username);`

## 配置

配置文件统一使用 yaml 格式，放在 `resources` 目录下

## 日志
- 禁止 使用 e.printStackTrace() 打印异常栈信息
- 禁止使用 System.out 打印日志
- 使用日志框架打印日志
- 配置文件中添加如下配置启用日志：
  ```yaml
  spring:
    profiles:
      include:
        - log4j2
  ```
