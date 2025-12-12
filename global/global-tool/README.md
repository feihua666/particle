# 全局工具类模块

该模块提供通用工具类，包含常用的静态工具方法。

## 功能特性

1. **工具类集合**：集成Hutool等常用工具库
2. **业务工具**：提供业务相关的工具方法
3. **模板处理**：支持模板渲染和处理
4. **JSON处理**：提供JSON序列化和反序列化工具

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-tool</artifactId>
</dependency>
```

### 配置选项

该模块主要提供工具方法，通常不需要特殊配置。

### 核心组件

- JsonTool：JSON处理工具类
- StringTool：字符串处理工具类
- DateTool：日期处理工具类
- TemplateTool：模板处理工具类
- 各种工具类：提供特定功能的工具方法

## 依赖组件

- Hutool：Java工具库
- Google Guava：Google工具库
- 其他常用工具库

## 示例代码

```java
// 使用JSON工具类
public class UserService {
    public String getUserJson(User user) {
        return JsonTool.toJsonStr(user);
    }
    
    public User parseUserJson(String json) {
        return JsonTool.parseObject(json, User.class);
    }
}

// 使用字符串工具类
public class StringUtils {
    public boolean isBlank(String str) {
        return StringTool.isBlank(str);
    }
}

// 使用模板工具类
public class TemplateService {
    public String renderTemplate(String template, Map<String, Object> params) {
        return TemplateTool.render(template, params);
    }
}
```