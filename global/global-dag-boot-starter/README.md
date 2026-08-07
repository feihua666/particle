# Global DAG Boot-Starter

DAG（有向无环图）工作流系统全局启动器，为Particle框架提供强大的工作流执行能力。

## 功能特性

- **DAG执行引擎**：支持复杂的有向无环图工作流执行
- **并发执行**：支持节点的并发执行，提高执行效率
- **依赖管理**：自动处理节点间的依赖关系
- **AI 模型调用**：内置 AI 节点执行器，支持多模型动态调用
- **条件分支**：支持 Groovy/SpEL 表达式条件评估
- **可扩展性**：支持自定义节点类型和执行器
- **监控日志**：完整的执行监控和日志记录
- **配置管理**：灵活的配置管理机制

## 内置节点类型

| 节点类型 | 执行器 | 说明 |
|---------|--------|------|
| `HTTP` | `HttpRequestNodeExecutor` | 执行 HTTP 请求 |
| `GROOVY_SCRIPT` | `GroovyScriptNodeExecutor` | 执行 Groovy 脚本 |
| `DELAY` | `DelayNodeExecutor` | 延迟等待 |
| `TRANSFORM` | `DataProcessNodeExecutor` | 数据转换/过滤 |
| `DATABASE` | `DatabaseNodeExecutor` | 数据库操作 |
| `AI` | `AiNodeExecutor` | AI 模型调用 |

## 快速开始

### 1. 添加依赖

在项目的`pom.xml`中添加依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-dag-boot-starter</artifactId>
    <version>7.0.3-beta-SNAPSHOT</version>
</dependency>
```

### 2. 自定义节点执行器

实现`NodeExecutor`接口创建自定义节点：

```java
@Component
public class MyNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return "MY_TYPE".equals(node.getType());
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) {
        // 实现节点执行逻辑
        Map<String, Object> output = new HashMap<>();
        output.put("result", "success");
        return NodeExecutionResult.success(output);
    }
}
```

### 3. 创建DAG定义

```java
// 创建DAG定义
DagDefinition dagDefinition = new DagDefinition();
dagDefinition.setId("my-dag");
dagDefinition.setName("My DAG Example");

// 创建节点
List<DagNode> nodes = new ArrayList<>();

// AI 节点示例
DagNode aiNode = new DagNode();
aiNode.setId("ai-node-1");
aiNode.setName("AI Processing");
aiNode.setType("AI");
Map<String, Object> config = new HashMap<>();
config.put("modelId", 1L);  // 模型ID
config.put("promptTemplate", "请分析以下内容：{{input}}");
aiNode.setConfig(config);
nodes.add(aiNode);

dagDefinition.setNodes(nodes);

// 创建边（定义节点依赖关系）
List<DagEdge> edges = new ArrayList<>();
DagEdge edge = new DagEdge();
edge.setId("edge-1");
edge.setFromNodeId("ai-node-1");
edge.setToNodeId("next-node");
edges.add(edge);

dagDefinition.setEdges(edges);
```

### 4. 执行DAG

```java
@Autowired
private DagEngine dagEngine;

// 创建执行上下文
ExecutionContext context = new ExecutionContext();

// 执行DAG
ExecutionHandle handle = dagEngine.execute(dagDefinition, context);

// 等待结果
ExecutionResult result = handle.awaitResult();
```

## AI 节点使用示例

### 配置模型

首先需要在业务模块（如 `component/agi`）中配置模型提供商和模型：

```java
// 实现 ModelConfigGateway 接口
@Component
public class AgiModelConfigGatewayImpl implements ModelConfigGateway {
    
    @Override
    public AiModelConfig getModelConfig(Long modelId) {
        // 从数据库查询模型配置
        // ...
        return AiModelConfig.builder()
            .modelId(modelId)
            .providerCode("dashscope")
            .modelCode("qwen-plus")
            .build();
    }
}
```

### 在 DAG 中使用 AI 节点

```java
// 创建 AI 节点
DagNode aiNode = new DagNode();
aiNode.setId("ai-node-1");
aiNode.setName("智能分析");
aiNode.setType("AI");

// 配置节点
Map<String, Object> config = new HashMap<>();
config.put("modelId", 1L);  // 必填：模型ID
config.put("promptTemplate", 
    "请分析以下数据并给出结论：\n" +
    "数据：{{inputData}}\n" +
    "要求：{{requirement}}");
config.put("temperature", 0.7);  // 可选：覆盖默认温度
config.put("maxTokens", 2000);   // 可选：覆盖最大 token 数
aiNode.setConfig(config);

// 执行上下文传入变量
ExecutionContext context = new ExecutionContext();
context.getVariables().put("inputData", "今日销售额 100 万元");
context.getVariables().put("requirement", "简要分析趋势");

// 执行
ExecutionHandle handle = dagEngine.execute(dagDefinition, context);
ExecutionResult result = handle.awaitResult();

// 获取 AI 输出
Map<String, Object> nodeOutput = context.getNodeExecutions()
    .get("ai-node-1")
    .getOutput();
String aiResult = (String) nodeOutput.get("result");
```

### Prompt 模板语法

支持 `{{variableName}}` 格式的变量替换：

```
{{variableName}}  ← 从执行上下文 variables 中替换
```

执行时会从 `ExecutionContext.variables` 中查找对应的值并替换。

## 核心组件

### DagEngine
DAG执行引擎，负责执行整个工作流。

### NodeExecutor
节点执行器接口，定义节点执行的标准。

### ExecutionPlanner
执行规划器，支持激进（并行最大化）和串行两种策略。

### ExecutionContext
执行上下文，用于节点间数据传递和状态管理。

### ExecutionHandle
执行句柄，提供 pause/resume/stop/awaitResult 等控制方法。

## 配置属性

可以在`application.properties`中配置：

```properties
# DAG 引擎配置
particle.dag.default.timeout=3600000
particle.dag.default.max-concurrency=10
particle.dag.default.fault-tolerant=true
```

## 使用示例

参考测试包中的示例代码：

- `DagEngineIntegrationTest`：集成测试示例
- `DefaultNodeExecutorsTest`：内置执行器测试
- `GroovyConditionEvaluatorTest`：条件评估器测试

## 架构设计

```
model/              # 领域模型（DagDefinition, DagNode, DagEdge）
  ↓
plan/               # 执行规划（ExecutionPlanner, PlanningStrategy）
  ↓
engine/             # 执行引擎（DagEngine, ExecutionHandle）
  ↓
runtime/            # 运行时（ExecutionContext, NodeExecutor）
  ├── executor/     #   节点执行器体系
  └── condition/    #   条件评估器
```

## 依赖说明

| 依赖 | 说明 |
|------|------|
| `global-ai-boot-starter` | 可选：使用 AI 节点时需要引入 |
| `spring-expression` | 可选：使用 SpEL 条件表达式 |
| `groovy-all` | 可选：使用 Groovy 脚本 |
| `spring-jdbc` | 可选：使用数据库节点 |

## 注意事项

- 确保节点类型有对应的执行器注册
- DAG 定义中避免循环依赖
- 合理设置超时和重试策略
- 使用 AI 节点时需要实现 `ModelConfigGateway` 接口
- 监控长时间运行的 DAG 执行
