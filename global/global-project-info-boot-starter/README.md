# 全局项目信息模块

该项目信息模块提供项目工程相关信息，包括作者、版本、分支等，支持Maven构建信息和Git分支信息。

## 功能特性

1. **项目信息收集**：自动收集项目基本信息
2. **构建信息支持**：支持Maven构建信息获取
3. **版本管理**：提供项目版本信息管理
4. **Git集成**：支持Git分支和提交信息获取

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-project-info-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
particle:
  project:
    info:
      # 项目信息相关配置
```

### 核心组件

- ProjectInfo：项目信息实体类
- ProjectInfoService：项目信息服务类

## 依赖组件

- Spring Boot Starter
- Maven插件（用于构建信息收集）

## 示例代码

```java
// 获取项目信息
@Service
public class ProjectInfoService {
    @Autowired
    private ProjectInfo projectInfo;
    
    public String getProjectVersion() {
        return projectInfo.getVersion();
    }
    
    public String getGitBranch() {
        return projectInfo.getGitBranch();
    }
}
```
