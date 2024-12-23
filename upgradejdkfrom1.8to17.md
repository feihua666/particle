# jdk升级1.8到17

### 关于import  xxx.xx* 问题
升级到 jdk17 后，在 java 8 中，import xxx.xx* 是可以的，但是jdk17中，有一个类报错已经重新导入改为了， import xxx.xx.x
出错的类：com.particle.global.neo4j.test.demo.test.DirectUseTest

### 关于js引擎问题
在使用js引擎找不到的问题，是因为jdk17移除了默认依赖，需要手动依赖
参见：https://github.com/dromara/hutool/issues/1915
具体报错使用类：com.particle.dream.infrastructure.ssq.gateway.impl.SsqCodeCrawlingGatewayImpl

### 依赖问题
```aiignore
取消了httpclient依赖
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
</dependency>
改为
<dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
</dependency>
取消了 servlet-api 依赖
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
</dependency>
改为
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
</dependency>
取消了 validation-api 依赖
<dependency>
    <groupId>validation</groupId>
    <artifactId>validation-api</artifactId>
</dependency>
改为
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
</dependency>
移除了 sleuth
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
删除了模块 sleuth
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-sleuth-boot-starter</artifactId>
    <version>${particle.version}</version>
</dependency>
在模块 global-actuator-boot-starter 中新增加了，以支持日志链路追踪
<!-- 日志链路追踪 global-sleuth-boot-starter 在springboot3中已不再使用-->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>

升级了 参考：https://github.com/git-commit-id/git-commit-id-maven-plugin
<groupId>pl.project13.maven</groupId>
<artifactId>git-commit-id-plugin</artifactId>
升级到
<groupId>io.github.git-commit-id</groupId>
<artifactId>git-commit-id-maven-plugin</artifactId>
```
### 其它问题
修改了不不兼容可即将过期的spring框架方法调用
