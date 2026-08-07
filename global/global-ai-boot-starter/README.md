# global-ai-boot-starter

AI 通用能力模块，提供多模型管理、路由调用等抽象能力。

## 功能特性

### 1. 多模型管理
- **AiModelProvider**: AI 模型提供商聚合根（如：OpenAI、阿里灵积、Ollama）
- **AiModel**: AI 模型实体（如：gpt-4、qwen-plus）
- **AiModelConfig**: 运行时模型配置对象
- **AiModelProviderConfig**: 运行时提供商配置对象

### 2. 路由调用
- **AiModelRegistry**: 模型注册表，管理所有 ChatModel Bean 和调用策略
- **AiModelRouterService**: 路由服务，根据模型配置动态选择策略进行调用
- **AiModelInvocationStrategy**: 调用策略接口，支持不同提供商的调用协议
- **ModelConfigGateway**: 配置网关接口，由业务模块实现提供数据库配置

## 架构设计

```
global-ai-boot-starter (抽象层)
├── model/                          # 领域模型
│   ├── AiModelProvider.java        # 提供商聚合根
│   └── AiModel.java                # 模型实体
├── config/                         # 配置对象
│   ├── AiModelProviderConfig.java  # 提供商配置
│   └── AiModelConfig.java          # 模型配置
├── strategy/                       # 调用策略
│   ├── AiModelInvocationStrategy.java  # 策略接口
│   └── AiModelInvokeResult.java        # 调用结果
├── gateway/                        # 网关
│   ├── AiModelRegistry.java        # 模型注册表
│   ├── AiModelRouterService.java   # 路由服务
│   └── ModelConfigGateway.java     # 配置网关接口
└── GlobalAiAutoConfiguration.java  # 自动配置
```

## 使用方式

### 1. 引入依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-ai-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
```

### 2. 实现配置网关

在业务模块（如 `component/agi`）中实现 `ModelConfigGateway` 接口：

```java
@Component
public class AgiModelConfigGatewayImpl implements ModelConfigGateway {
    
    @Autowired
    private ModelProviderMapper providerMapper;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public AiModelConfig getModelConfig(Long modelId) {
        // 从数据库查询模型配置
        ModelDO modelDO = modelMapper.selectById(modelId);
        ModelProviderDO providerDO = providerMapper.selectById(modelDO.getProviderId());
        
        return AiModelConfig.builder()
            .modelId(modelId)
            .providerCode(providerDO.getProviderCode())
            .modelCode(modelDO.getModelCode())
            .temperature(modelDO.getDefaultTemperature())
            .providerConfig(buildProviderConfig(providerDO))
            .build();
    }
    
    // ... 其他方法实现
}
```

### 3. 调用模型

```java
@Service
public class SomeService {
    
    @Autowired
    private AiModelRouterService modelRouter;
    
    @Autowired
    private ModelConfigGateway modelGateway;
    
    public String callAi(Long modelId, String prompt) {
        // 根据模型ID调用
        AiModelInvokeResult result = modelRouter.invokeByModelId(prompt, modelId, modelGateway);
        return result.getOutput();
    }
    
    public Flux<ChatResponse> callAiStream(Long modelId, String prompt) {
        // 流式调用
        return modelRouter.invokeStreamByModelId(prompt, modelId, modelGateway);
    }
}
```

### 4. 注册调用策略

```java
@Component
public class DashScopeStrategy implements AiModelInvocationStrategy {
    
    @Override
    public boolean supports(String providerCode) {
        return "dashscope".equals(providerCode);
    }
    
    @Override
    public AiModelInvokeResult invoke(String prompt, AiModelConfig modelConfig) {
        // 实现 DashScope 调用逻辑
        // ...
    }
    
    @Override
    public String getSupportedProviderCode() {
        return "dashscope";
    }
}
```

## 集成 DAG 引擎

在 `global-dag-boot-starter` 的 AI 节点执行器中使用：

```java
@Component
public class AiNodeExecutor implements NodeExecutor {
    
    @Autowired
    private AiModelRouterService modelRouter;
    
    @Autowired
    private ModelConfigGateway modelGateway;
    
    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) {
        Long modelId = (Long) node.getConfig().get("modelId");
        String prompt = resolveTemplate((String) node.getConfig().get("promptTemplate"), context);
        
        AiModelInvokeResult result = modelRouter.invokeByModelId(prompt, modelId, modelGateway);
        
        return NodeExecutionResult.success(Map.of("result", result.getOutput()));
    }
}
```

## 依赖说明

- `spring-ai-starter-model-openai`: OpenAI 模型支持
- `spring-ai-starter-model-ollama`: Ollama 本地模型支持
- `spring-ai-starter-vector-store-milvus`: Milvus 向量数据库支持

## 配置示例

```yaml
spring:
  ai:
    openai:
      base-url: https://api.openai.com
      api-key: ${OPENAI_API_KEY}
      chat:
        options:
          model: gpt-4
          temperature: 0.7
    dashscope:
      api-key: ${DASHSCOPE_API_KEY}
      chat:
        options:
          model: qwen-plus
          temperature: 0.7
    ollama:
      base-url: http://localhost:11434
      chat:
        options:
          model: qwen2:7b
          temperature: 0.7
```

## 扩展指南

### 添加新的提供商支持

1. 实现 `AiModelInvocationStrategy` 接口
2. 注册为 Spring Bean
3. 自动被 `AiModelRegistry` 收集

### 模型配置变更

调用 `ModelConfigGateway.refreshCache()` 刷新缓存。

## 注意事项

1. 所有类使用 `Ai` 前缀，避免与 Spring AI 类冲突
2. `ModelConfigGateway` 是接口，必须由业务模块实现
3. 支持降级：策略调用失败时自动降级到 Spring AI 原生调用
