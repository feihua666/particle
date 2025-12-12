# 文档支持模块

文档支持模块旨在对常用的文档操作提供支持，如 Excel 导入导出、文档在线预览等技术支持。

## 功能特性

1. **文档模板下载**：提供通用的文档模板下载支持
2. **模板配置管理**：支持通过配置文件管理文档模板
3. **RESTful 接口**：提供标准的 RESTful 接口供前端调用
4. **多格式支持**：支持多种文档格式的处理
5. **可扩展模板提供者**：支持自定义模板提供者实现

## 核心组件

### 文档控制器
- [DocumentController](src/main/java/com/particle/global/document/endpoint/DocumentController.java)：文档相关服务接口，提供下载模板等 RESTful 接口

### 模板服务
- [GlobalDocumentTemplateService](src/main/java/com/particle/global/document/template/GlobalDocumentTemplateService.java)：文档模板服务入口，负责协调不同的模板提供者
- [GlobalDocumentTemplate](src/main/java/com/particle/global/document/template/GlobalDocumentTemplate.java)：文档模板模型类
- [GlobalDocumentTemplateProvider](src/main/java/com/particle/global/document/template/GlobalDocumentTemplateProvider.java)：文档模板提供者接口
- [DefalutGlobalDocumentTemplateProvider](src/main/java/com/particle/global/document/template/DefalutGlobalDocumentTemplateProvider.java)：默认文档模板提供者实现，从 classpath 加载模板

## 配置选项

### 模板配置

文档模板通过 meta 配置文件进行配置：

1. 在 resources 下建一个模板文件  
   如：`global-document-template/文档测试模板.xlsx`

2. 在 resources 下建一个 meta 文件，用来描述模板位置及名称  
   如：`META-INF/template/globalDocumentTemplateTest.meta`，其中 `META-INF/template` 是约定的路径（已硬编码在 [DefalutGlobalDocumentTemplateProvider](src/main/java/com/particle/global/document/template/DefalutGlobalDocumentTemplateProvider.java) 中）

配置内容为：
```properties
# 必填用来读取模板
particle.document.template.path=global-document-template/文档测试模板.xlsx
# 选填，默认使用 path 中的文件名
particle.document.template.name=文档测试模板.xlsx
```

3. 调用下载模板接口，指定参数 templateIdentifier 为 meta 文件名称，不带后缀
```
GET /document/downloadTemplate?templateIdentifier=globalDocumentTemplateTest
```

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-document-boot-starter</artifactId>
</dependency>
```

### 自定义模板提供者

实现 [GlobalDocumentTemplateProvider](src/main/java/com/particle/global/document/template/GlobalDocumentTemplateProvider.java) 接口可以自定义模板提供方式：

```java
@Component
public class CustomDocumentTemplateProvider implements GlobalDocumentTemplateProvider {
    
    @Override
    public boolean support(String templateIdentifier) {
        // 判断是否支持该模板标识
        return "customTemplate".equals(templateIdentifier);
    }
    
    @Override
    public GlobalDocumentTemplate resolveTemplate(String templateIdentifier) {
        // 自定义模板解析逻辑
        InputStream inputStream = getCustomTemplateStream();
        return GlobalDocumentTemplate.create("自定义模板.xlsx", inputStream);
    }
}
```

## 自动配置

[GlobalDocumentAutoConfiguration](src/main/java/com/particle/global/document/GlobalDocumentAutoConfiguration.java) 自动配置了以下内容：
- 注册默认的文档模板提供者 [DefalutGlobalDocumentTemplateProvider](src/main/java/com/particle/global/document/template/DefalutGlobalDocumentTemplateProvider.java)
- 注册文档模板服务 [GlobalDocumentTemplateService](src/main/java/com/particle/global/document/template/GlobalDocumentTemplateService.java)
- 注册文档控制器 [DocumentController](src/main/java/com/particle/global/document/endpoint/DocumentController.java)

## 依赖组件

- Spring Boot Starter Web
- global-dto: 全局数据传输对象
- global-swagger-boot-starter: Swagger 接口文档支持
- global-project-info-boot-starter: 项目信息支持
- global-tool: 全局工具类