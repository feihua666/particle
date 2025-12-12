# 全局Neo4j图数据库模块

该模块提供对 Neo4j 图数据库的集成支持，封装了常用的图数据库操作功能。

Neo4j 是世界领先的开源图形数据库，专门用于存储和处理图形数据结构，适用于社交网络、推荐系统、欺诈检测等图数据密集型应用场景。

## 功能特性

1. **无缝集成**：与 Spring Data Neo4j 无缝集成
2. **连接管理**：支持 Neo4j 数据库连接的自动配置和管理
3. **操作封装**：封装常用的图数据库操作，简化开发复杂度
4. **事务支持**：完整的事务管理支持
5. **高性能**：基于 Neo4j 官方驱动，提供高性能的数据访问

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-neo4j-boot-starter</artifactId>
</dependency>
```

### 配置选项

在 application.yml 中配置 Neo4j 连接信息：

```yaml
spring:
  data:
    neo4j:
      uri: bolt://localhost:7687
      username: neo4j
      password: password
```

### 核心组件

- Neo4jRepository：图数据库访问仓库接口
- Neo4jTemplate：图数据库操作模板类
- Neo4jClient：底层客户端访问类
- Driver：Neo4j 官方驱动类

类关系梳理：
Neo4jRepository 依赖了 Neo4jTemplate，Neo4jTemplate 依赖了 Neo4jClient，Neo4jClient 依赖了 Driver，Driver 是 neo4j 的驱动包类

## 依赖组件

- Spring Data Neo4j：Spring 对 Neo4j 的数据访问支持
- Neo4j Driver：Neo4j 官方 Java 驱动
- Spring Boot Starter Data Neo4j

## 示例代码

```java
// 定义节点实体
@Node
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    @Relationship(type = "FRIEND")
    private List<Person> friends;
    
    // getters and setters
}

// 定义仓库接口
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    List<Person> findByName(String name);
}

// 使用示例
@Autowired
private PersonRepository personRepository;

public void createPerson(String name) {
    Person person = new Person();
    person.setName(name);
    personRepository.save(person);
}
```