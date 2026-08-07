# Global Workflow Boot-Starter

工作流系统全局启动器,提供可视化工作流编排能力。

## 功能特性

- **节点独立测试**: 支持在无限画布上单独测试验证节点,无需定义完整工作流
- **实时反馈**: 拖拽节点后可立即测试执行效果,查看输出结果
- **异步执行**: 支持耗时节点的异步测试,避免阻塞UI
- **DAG引擎集成**: 基于 `global-dag-boot-starter` 提供强大的工作流执行能力
- **可扩展节点**: 支持自定义节点类型,业务模块可注册专用节点

## 核心概念

### 节点测试 vs 工作流执行

| 维度 | 节点测试 | 工作流执行 |
|------|---------|-----------|
| 使用场景 | 前端拖拽时实时验证 | 正式发布的工作流运行 |
| 执行范围 | 单个节点 | 完整DAG(多节点+依赖) |
| 响应速度 | 快速反馈(秒级) | 可能较长(分钟级) |
| 数据持久化 | 不保存 | 保存执行历史 |

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-workflow-boot-starter</artifactId>
    <version>7.0.3-beta-SNAPSHOT</version>
</dependency>
```

### 2. 节点测试API使用

#### 同步测试节点

```java
@Autowired
private WorkflowNodeTestService nodeTestService;

// 构造测试请求
NodeTestRequest request = NodeTestRequest.builder()
    .nodeType("AI")
    .config(Map.of(
        "modelId", 1L,
        "promptTemplate", "请分析: {{input}}"
    ))
    .inputVariables(Map.of("input", "今日销售额100万"))
    .timeout(30000L)
    .build();

// 执行测试
NodeTestResult result = nodeTestService.testNode(request);

if (result.getSuccess()) {
    System.out.println("输出: " + result.getOutput());
    System.out.println("耗时: " + result.getExecutionTimeMs() + "ms");
} else {
    System.err.println("错误: " + result.getErrorMessage());
}
```

#### 异步测试节点

```java
// 创建异步测试任务
String taskId = nodeTestService.testNodeAsync(request);

// 轮询查询结果
NodeTestResult result = null;
while (result == null) {
    Thread.sleep(1000);
    result = nodeTestService.getTestResult(taskId);
}
```

### 3. REST API使用

#### 测试节点

```bash
POST /api/workflow/node-test/test
Content-Type: application/json

{
  "nodeType": "AI",
  "config": {
    "modelId": 1,
    "promptTemplate": "请分析: {{input}}"
  },
  "inputVariables": {
    "input": "测试数据"
  },
  "timeout": 30000
}
```

#### 获取支持的节点类型

```bash
GET /api/workflow/node-test/supported-types
```

#### 获取节点配置模板

```bash
GET /api/workflow/node-test/config-template/AI
```

## 架构设计

```
前端无限画布
    ↓ (拖拽节点)
WorkflowNodeTestController (REST API)
    ↓
WorkflowNodeTestService (测试服务)
    ↓
DagEngine (DAG执行引擎)
    ↓
NodeExecutorRegistry (节点执行器注册表)
    ↓
具体节点执行器 (AI/HTTP/Crawler等)
```

## 扩展指南

### 自定义节点执行器

```java
@Component
public class MyCustomNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return "MY_CUSTOM".equals(node.getType());
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) {
        // 实现节点逻辑
        Map<String, Object> output = new HashMap<>();
        output.put("result", "success");
        return NodeExecutionResult.success(output);
    }
}
```

注册后自动被 `WorkflowNodeTestService` 识别,可通过API测试。

## 前端集成示例

### Vue3 + TypeScript

```typescript
// 测试节点
async function testNode(nodeConfig: any) {
  const response = await fetch('/api/workflow/node-test/test', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      nodeType: nodeConfig.type,
      config: nodeConfig.config,
      inputVariables: nodeConfig.inputs
    })
  });
  
  const result = await response.json();
  
  if (result.success) {
    console.log('节点输出:', result.output);
    console.log('执行耗时:', result.executionTimeMs, 'ms');
  } else {
    console.error('测试失败:', result.errorMessage);
  }
}
```

### 无限画布交互流程

1. 用户从节点库拖拽节点到画布
2. 点击节点打开配置面板
3. 填写配置后点击"测试"按钮
4. 调用 `/api/workflow/node-test/test` API
5. 显示测试结果(成功/失败、输出数据、耗时)
6. 用户根据结果调整配置,反复测试直到满意
7. 最后保存完整工作流定义

## 注意事项

- 节点测试不会持久化,仅用于开发调试
- 异步测试结果缓存30分钟后自动清理
- 确保对应的 `NodeExecutor` 已正确注册
- AI节点需要配置好模型提供商和API密钥
