# 全局Elasticsearch模块

该模块将访问Elasticsearch的模块单独提出来，主要是封装一些常用的基础类。

## 功能特性

1. **ES客户端封装**：提供简化版的Elasticsearch客户端操作接口
2. **自动配置**：通过starter方式自动配置ES相关组件
3. **索引管理**：支持索引的创建、删除和管理
4. **文档操作**：提供文档的增删改查操作支持

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-elasticsearch-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
spring:
  elasticsearch:
    uris: http://localhost:9200
```

### 核心组件

- ElasticsearchTemplate：ES操作模板类
- ElasticsearchRepository：ES数据访问仓库接口
- 自动配置类：负责ES客户端的自动配置

## 依赖组件

- Spring Data Elasticsearch
- Elasticsearch客户端

## 示例代码

```java
// 定义文档实体
@Document(indexName = "users")
public class UserDocument {
    @Id
    private String id;
    private String name;
    private int age;
    
    // getter和setter方法
}

// 使用Repository操作文档
@Repository
public interface UserRepository extends ElasticsearchRepository<UserDocument, String> {
    List<UserDocument> findByName(String name);
}

// 注入并使用
@Autowired
private UserRepository userRepository;

public void saveUser(UserDocument user) {
    userRepository.save(user);
}
```