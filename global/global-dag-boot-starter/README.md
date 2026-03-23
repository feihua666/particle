# Global DAG Boot-Starter

DAG（有向无环图）工作流系统全局启动器，为Particle框架提供强大的工作流执行能力。

## 功能特性

- **DAG执行引擎**：支持复杂的有向无环图工作流执行
- **并发执行**：支持节点的并发执行，提高执行效率
- **依赖管理**：自动处理节点间的依赖关系
- **可扩展性**：支持自定义节点类型和执行器
- **监控日志**：完整的执行监控和日志记录
- **配置管理**：灵活的配置管理机制

## 快速开始

### 1. 添加依赖

在项目的`pom.xml`中添加依赖：

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-dag-boot-starter</artifactId>
    <version>7.0.1-beta-SNAPSHOT</version>
</dependency>
```

### 2. 自定义节点执行器

实现`DagNodeExecutor`接口创建自定义节点：

```java
@Component
public class HttpNodeExecutor implements DagNodeExecutor {

    @Override
    public NodeResult execute(NodeContext nodeContext) {
        // 实现节点执行逻辑
        NodeResult result = new NodeResult();
        result.setNodeId(nodeContext.getNodeId());
        result.setStartTime(java.time.LocalDateTime.now());

        try {
            // 执行业务逻辑
            result.setStatus(NodeResult.ExecutionStatus.SUCCESS);
        } catch (Exception e) {
            result.setStatus(NodeResult.ExecutionStatus.FAILED);
            result.setErrorMessage(e.getMessage());
        } finally {
            result.setEndTime(java.time.LocalDateTime.now());
        }

        return result;
    }

    @Override
    public String getNodeType() {
        return "http"; // 节点类型标识
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
List<NodeDefinition> nodes = new ArrayList<>();
NodeDefinition node1 = new NodeDefinition();
node1.setId("node-1");
node1.setName("HTTP Node");
node1.setType("http"); // 对应自定义执行器的类型
nodes.add(node1);

dagDefinition.setNodes(nodes);

// 创建边（定义节点依赖关系）
List<EdgeDefinition> edges = new ArrayList<>();
EdgeDefinition edge = new EdgeDefinition();
edge.setSourceNodeId("node-1");
edge.setTargetNodeId("node-2");
edges.add(edge);

dagDefinition.setEdges(edges);
```

### 4. 执行DAG

```java
@Autowired
private DagEngine dagEngine;

// 创建执行上下文
DagContext dagContext = new DagContext();
dagContext.setExecutionId("execution-1");
dagContext.setDagDefinition(dagDefinition);

// 执行DAG
DagResult result = dagEngine.execute(dagContext);
```

## 核心组件

### DagEngine
DAG执行引擎，负责执行整个工作流。

### DagNodeExecutor
节点执行器接口，定义节点执行的标准。

### DagDefinitionParser
DAG定义解析器，支持JSON等格式的DAG定义解析。

### NodeRegistry
节点注册中心，管理不同类型的节点执行器。

### DagMonitor
DAG监控器，提供执行过程的监控能力。

### DagLogManager
DAG日志管理器，提供完整的日志记录功能。

## 配置属性

可以在`application.properties`中配置：

```properties
# 默认DAG超时时间（毫秒）
particle.dag.default.timeout=3600000

# 默认重试次数
particle.dag.default.retry-count=3

# 默认最大并发数
particle.dag.default.max-concurrency=10

# 默认错误处理策略
particle.dag.default.error-handling-strategy=fail-fast

# 默认日志级别
particle.dag.default.log-level=INFO
```

## 使用示例

参考测试包中的示例代码：

- `DagUsageExample`：DAG使用示例
- `HttpNodeExecutor`：HTTP节点执行器示例
- `DataProcessNodeExecutor`：数据处理节点执行器示例
- `DagEngineIntegrationTest`：集成测试示例

## 架构设计

- **模型层**：DagDefinition、NodeDefinition等模型类
- **引擎层**：DagEngine核心执行引擎
- **注册层**：NodeRegistry节点注册管理
- **解析层**：DagDefinitionParser定义解析
- **监控层**：DagMonitor和DagLogManager监控日志
- **配置层**：DagConfigurationManager配置管理

## 扩展性

系统设计具有良好的扩展性：
- 可以轻松添加新的节点类型
- 支持不同的DAG定义格式
- 可以自定义监控和日志策略
- 支持自定义配置管理

## 注意事项

- 确保节点类型在NodeRegistry中正确注册
- DAG定义中避免循环依赖
- 合理设置超时和重试策略
- 监控长时间运行的DAG执行
