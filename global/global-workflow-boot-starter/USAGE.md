# Global Workflow Boot-Starter 使用指南

## 概述

`global-workflow-boot-starter` 是一个工作流系统全局启动器,核心特性是**支持节点独立测试验证**,无需定义完整工作流即可在前端无限画布上实时测试节点执行效果。

## 核心设计理念

### 传统工作流 vs 本模块

| 维度 | 传统方式 | 本模块方式 |
|------|---------|-----------|
| 开发流程 | 定义完整DAG → 部署 → 测试 | 拖拽节点 → 即时测试 → 调整配置 → 保存 |
| 反馈速度 | 分钟级(需部署) | 秒级(实时API调用) |
| 调试难度 | 高(需查看完整日志) | 低(单节点输出清晰) |
| 学习成本 | 高(需理解DAG概念) | 低(所见即所得) |

## 架构说明

```
前端无限画布 (Vue Flow / X6)
    ↓ 拖拽节点 + 配置参数
REST API (/api/workflow/node-test/*)
    ↓
WorkflowNodeTestService (节点测试服务)
    ↓ 构造最小DAG(单节点)
DagEngine (DAG执行引擎)
    ↓
NodeExecutorRegistry (节点执行器注册表)
    ↓
具体节点执行器 (AI/HTTP/Crawler/Groovy等)
```

## 快速开始

### 1. 添加依赖

在业务模块的 `pom.xml` 中添加:

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-workflow-boot-starter</artifactId>
    <version>7.0.3-beta-SNAPSHOT</version>
</dependency>
```

### 2. Java代码使用

#### 同步测试节点

```java
@Autowired
private WorkflowNodeTestService nodeTestService;

// 测试AI节点
Map<String, Object> config = new HashMap<>();
config.put("modelId", 1L);
config.put("promptTemplate", "请分析以下数据: {{input}}");

Map<String, Object> inputVariables = new HashMap<>();
inputVariables.put("input", "今日销售额100万元,同比增长20%");

NodeTestRequest request = NodeTestRequest.builder()
    .nodeType("AI")
    .config(config)
    .inputVariables(inputVariables)
    .timeout(30000L)
    .build();

NodeTestResult result = nodeTestService.testNode(request);

if (result.getSuccess()) {
    System.out.println("AI输出: " + result.getOutput().get("result"));
    System.out.println("耗时: " + result.getExecutionTimeMs() + "ms");
} else {
    System.err.println("错误: " + result.getErrorMessage());
}
```

#### 异步测试节点

```java
// 适用于耗时较长的节点(如爬虫、大批量数据处理)
String taskId = nodeTestService.testNodeAsync(request);

// 轮询查询结果
NodeTestResult result = null;
int retryCount = 0;
while (result == null && retryCount < 30) {
    Thread.sleep(1000);
    result = nodeTestService.getTestResult(taskId);
    retryCount++;
}

if (result != null) {
    // 处理结果
} else {
    System.err.println("测试超时");
}
```

#### 获取支持的节点类型

```java
List<String> supportedTypes = nodeTestService.getSupportedNodeTypes();
// 返回: [AI, HTTP, GROOVY_SCRIPT, CRAWLER, DELAY, TRANSFORM, DATABASE]
```

#### 获取节点配置模板

```java
Object template = nodeTestService.getNodeConfigTemplate("AI");
// 返回配置示例,用于前端自动生成表单
```

### 3. REST API使用

#### 测试节点

```bash
POST http://localhost:8080/api/workflow/node-test/test
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

响应:
```json
{
  "success": true,
  "output": {
    "result": "分析结果..."
  },
  "executionTimeMs": 1234,
  "startTime": "2026-04-16T16:00:00",
  "endTime": "2026-04-16T16:00:01",
  "nodeType": "AI"
}
```

#### 异步测试

```bash
POST http://localhost:8080/api/workflow/node-test/test-async
Content-Type: application/json

{
  "nodeType": "HTTP",
  "config": {
    "url": "https://httpbin.org/delay/5",
    "method": "GET"
  }
}
```

响应:
```json
{
  "taskId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890"
}
```

查询结果:
```bash
GET http://localhost:8080/api/workflow/node-test/result/a1b2c3d4-e5f6-7890-abcd-ef1234567890
```

#### 获取支持的节点类型

```bash
GET http://localhost:8080/api/workflow/node-test/supported-types
```

响应:
```json
["AI", "HTTP", "GROOVY_SCRIPT", "CRAWLER", "DELAY", "TRANSFORM", "DATABASE"]
```

#### 获取节点配置模板

```bash
GET http://localhost:8080/api/workflow/node-test/config-template/AI
```

响应:
```json
{
  "nodeType": "AI",
  "description": "请根据节点类型填写配置",
  "example": {
    "modelId": 1,
    "promptTemplate": "请输入提示词模板",
    "temperature": 0.7
  }
}
```

### 4. 前端集成示例 (Vue3 + TypeScript)

```typescript
// composables/useWorkflowNodeTest.ts
import { ref } from 'vue';

export function useWorkflowNodeTest() {
  const testing = ref(false);
  const result = ref<any>(null);

  async function testNode(nodeConfig: any) {
    testing.value = true;
    result.value = null;

    try {
      const response = await fetch('/api/workflow/node-test/test', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          nodeType: nodeConfig.type,
          config: nodeConfig.config,
          inputVariables: nodeConfig.inputs || {},
          timeout: 30000
        })
      });

      result.value = await response.json();
    } catch (error) {
      result.value = {
        success: false,
        errorMessage: error.message
      };
    } finally {
      testing.value = false;
    }
  }

  return {
    testing,
    result,
    testNode
  };
}
```

```vue
<!-- components/WorkflowNode.vue -->
<template>
  <div class="workflow-node">
    <h3>{{ node.name }}</h3>
    
    <!-- 配置表单 -->
    <el-form :model="node.config">
      <el-form-item label="配置">
        <el-input v-model="node.config.promptTemplate" type="textarea" />
      </el-form-item>
    </el-form>

    <!-- 测试按钮 -->
    <el-button 
      type="primary" 
      @click="testNode(node)"
      :loading="testing"
    >
      测试节点
    </el-button>

    <!-- 测试结果 -->
    <div v-if="result" class="test-result">
      <el-alert 
        :type="result.success ? 'success' : 'error'"
        :title="result.success ? '测试成功' : '测试失败'"
      >
        <div v-if="result.success">
          <p>耗时: {{ result.executionTimeMs }}ms</p>
          <pre>{{ JSON.stringify(result.output, null, 2) }}</pre>
        </div>
        <div v-else>
          <p>{{ result.errorMessage }}</p>
        </div>
      </el-alert>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useWorkflowNodeTest } from '@/composables/useWorkflowNodeTest';

const props = defineProps<{
  node: any
}>();

const { testing, result, testNode } = useWorkflowNodeTest();
</script>
```

## 扩展自定义节点

### 1. 实现NodeExecutor接口

```java
@Component
public class MyCustomNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return "MY_CUSTOM".equals(node.getType());
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) {
        // 获取节点配置
        Map<String, Object> config = node.getConfig();
        
        // 获取输入变量
        Map<String, Object> variables = context.getVariables();
        
        // 执行业务逻辑
        String result = doSomething(config, variables);
        
        // 返回输出
        Map<String, Object> output = new HashMap<>();
        output.put("result", result);
        
        return NodeExecutionResult.success(output);
    }
}
```

### 2. 自动注册

Spring会自动将 `@Component` 标注的执行器注册到 `NodeExecutorRegistry`,无需额外配置。

### 3. 测试新节点

```bash
POST /api/workflow/node-test/test
{
  "nodeType": "MY_CUSTOM",
  "config": {
    "param1": "value1"
  }
}
```

## 典型应用场景

### 场景1: AI智能体工作流

```
[用户输入] → [AI分析节点] → [条件判断] → [AI生成节点] → [输出]
```

用户在画布上:
1. 拖拽AI节点
2. 配置prompt模板: `请分析{{input}}并给出建议`
3. 点击"测试",输入样例数据
4. 查看AI输出,调整temperature等参数
5. 重复测试直到满意
6. 连接下一个节点

### 场景2: 爬虫+AI处理

```
[爬虫节点] → [数据清洗节点] → [AI总结节点] → [存储节点]
```

1. 配置爬虫节点URL和提取规则
2. 测试爬虫,确认能正确抓取数据
3. 配置AI节点对抓取内容进行总结
4. 测试AI节点,验证总结效果
5. 连接所有节点,保存工作流

### 场景3: 数据处理流水线

```
[数据库查询] → [Groovy转换] → [HTTP推送] → [日志记录]
```

每个节点都可独立测试:
- 数据库节点: 测试SQL是否正确
- Groovy节点: 测试转换逻辑
- HTTP节点: 测试接口调用
- 最后串联成完整流程

## 注意事项

### 性能考虑

- 节点测试每次都会创建新的DAG实例,适合开发调试,不适合生产环境高频调用
- 异步测试的结果缓存30分钟后自动清理,避免内存泄漏
- 对于耗时节点(>10s),建议使用异步测试

### 安全性

- 生产环境应对节点测试API增加权限控制
- Groovy脚本节点应限制可访问的类和方法
- HTTP节点应限制可访问的域名白名单

### 局限性

- 节点测试只能验证单个节点的逻辑,无法测试节点间的数据传递
- 复杂的工作流逻辑(如循环、分支)需要在完整DAG中测试
- 当前版本不支持节点测试的历史记录保存

## 未来规划

- [ ] 支持节点测试历史记录
- [ ] 提供节点性能分析(内存、CPU占用)
- [ ] 支持批量节点测试(一次性测试多个配置)
- [ ] 集成WebSocket实现实时日志推送
- [ ] 提供节点测试报告导出功能

## 常见问题

### Q: 为什么我的节点类型不被支持?

A: 检查是否已实现并注册了对应的 `NodeExecutor`,确保 `supports()` 方法正确判断节点类型。

### Q: 如何在节点间传递数据?

A: 节点测试是单节点执行,不支持数据传递。完整工作流执行时,通过 `ExecutionContext.variables` 传递。

### Q: 测试超时怎么办?

A: 增加 `timeout` 参数值,或改用异步测试 `testNodeAsync()`。

### Q: 如何调试节点执行失败?

A: 查看 `NodeTestResult.errorStack` 获取详细堆栈信息,或在执行器中添加日志。
