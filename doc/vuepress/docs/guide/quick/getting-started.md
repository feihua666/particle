# 快速上手

我们将通过一个简单的 Demo 来做为示例开始，在此之前，我们假设您已经：  


+ 拥有 Java 开发环境以及相应 IDE
+ 熟悉 Spring Boot
+ 熟悉 Maven
+ 熟悉 Vuejs
+ 熟悉 element-ui

+ jdk 1.8以上
+ mysql数据库

## 克隆项目

``` bash
git clone https://gitee.com/feihua666/scatter.git
```

## 添加一个模块
假设模块的名称为 scatter-hello,模块路径为 scatter/modules/scatter-hello

+ 通过模块生成器生成  
代码位置：scatter/generator/rest/test/components/ComponentGenerator.java  

``` java
package scatter.generator.rest.test.components;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by yangwei
 * Created at 2020/8/20 12:46
 */
@Slf4j
public class ComponentGenerator {

    public static void main(String[] args) {
        // position可选两个值：components modules
        // components 代码生成到scatter/components下
        // modules 代码生成到scatter/modules
        String position = "modules";
        // 生成的组件名称，这里我们写 scatter-hello
        String componentName = "scatter-hello";

        new scatter.generator.component.ComponentGenerator (componentName,position).generate();
    }
}

```

+ 通过模块服务生成器生成  
  代码位置：scatter/modules/scatter-generator/scatter-generator-server/src/main/java/scatter/scatterhello/server/scatterhelloServerApplication.java  
  这是一个springboot项目，运行之前需要确保已配置好正确的数据库  

``` yml
server:
  # 端口设置为9998
  port: 9998
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/scatter_generator?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8
    username: username #请修改这里
    password: password #请修改这里
    schema:
      - classpath:db/scatter-generator-schema.sql
    data:
      - classpath:db/scatter-generator-data.sql
    initialization-mode: NEVER # 初始运行请设置为 ALWAYS 以添加必要的表
```
启动后访问 http://localhost:9998 并点击顶部导航最右侧 项目/组件生成  
输入项目名称 scatter-hello 如下图：

![项目/组件生成](/guide/quick/component-generator.png)


::: warning
在模块生成后需要在父pom.xml中添加模块声明  
``` xml
<module>scatter-hello</module>
```
:::

## 模块目录结构
```
.
├── scatter-hello
│   ├── scatter-hello-pojo // (实体及其它pojo)
│   │── scatter-hello-rest // (mvc模块)
│   │── scatter-hello-client // (spring cloud feign client 不需要可以删除)
│   │── scatter-hello-client-impl // (spring cloud feign client 实现 不需要可以删除)
```

## 模块启动

+ 在scatter-hello-rest中添加启动类 ScatterhelloServerApplication 启动 

``` java
package scatter.scatterhello.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import scatter.common.rest.config.CommonRestConfig;
import scatter.scatterhello.rest.scatterhelloConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import({CommonRestConfig.class})
public class ScatterhelloServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScatterhelloServerApplication.class, args);
    }
}

```

+ 在scatter-hello中再添加一个scatter-hello-server模块并添加ScatterhelloServerApplication来启动 不再详述  
注意：新建一个scatter-hello-server模块需要在其pom.xml添加 scatter-hello-rest 依赖
::: warning
不管在哪里启动，记得添加maven插件
``` xml
<build>
  <plugins>
      <plugin>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
  </plugins>
</build>
```
:::

## 结尾
到此一个空的springboot项目就完成了，如果需要其它组件的功能，事实上也是需要的，请查阅后面章节的组件介绍和使用方法  
关于前端server 服务，需要在 scatter-hello 添加一个web目录，并使用vuecli创建一个项目，  
关于前端如何使用已封装的前端组件，两种方式  

1.直接使用相对路径方式import即可，相对路径idea插件可以试试[copy-raletive-path](https://gitee.com/feihua666/copy-relative-path)  
  
2. 在components/common/web/components/pc下的组件可以使用其vue插件安装方式，一次性安装，如：components/common/web/components/pc/element/ElementPlusPlugin.js  
示例如下：
``` javascript
import Vue from 'vue'
...
import ToolsPlugin from '../../../../../components/common/web/tools/AllToolsPlugin.js'
import ElementPlugin from '../../../../../components/common/web/components/pc/element/ElementPlusPlugin.js'
import CommonPlugin from '../../../../../components/common/web/components/pc/common/CommonPlugin.js'
// 自定义工具插件
Vue.use(ToolsPlugin)
// 自定义基于element组件
Vue.use(ElementPlugin)
// 自定义基于通用组件
Vue.use(CommonPlugin)
...
```