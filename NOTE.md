# 笔记
1. mybatis的@Mapper注解，如果在依赖jar包中使用，依然不起作用（不是在jar中使用，是可以不用添加@MapperScan注解的），需要添加@MapperScan注解指定路径  
2. 关于json序列化问题，小写boolean和包装类型Boolean的使用
   关键问题在于根据javaBean规范boolean类型使用isXxxx和setXxxx,阿里规范不建议用is开头的数据库字段。  
   因为在生成对应的实体时，如果使用小写boolean类型，在json框架一般是根据get方法获取字段（Gson除外，没有这个问题），如果为小写boolean类型isDelete,则get方法和字段名会相同，导致区分不开真正的字段。  
   本项目中仍然使用is开关的数据库字段，因为实体都使用包装类型Boolean，其get方法不是is开关，并无此问题，方便前端判断类型。本项目一般局部变量用小写boolean，
   对象属性用大写包装类型Boolean。
3. maven 指定模块打包 clean install -pl ./project/particle-demo -amd -Dmaven.test.skip=true 注意 -amd参数否则不构建子项目，参考：https://blog.csdn.net/FYW_wu/article/details/114095629
4. idea 将字符首字母转大写正则 参考：https://blog.csdn.net/qq_35634181/article/details/111034194
5. 前端打包不同的环境需要修改 package.json 添加一个scripts 如："build-only-test": "vite build --mode test" 是从build-only修改而来且指定的环境参数
6. shell if 判断 中括号内容两边必须带空格
7. 在 spring boot starter 中的 spring.factories中指定的自动配置类文件需要添加@Configuration注解，而被@import注解引入的不需要加@Configuration
8. 关于在日志中打印 is not eligible for getting processed by all BeanPostProcessors  
   a. 一般是自定义了BeanPostProcessor实例，并在该实例中注入了其它bean，导致本身BeanPostProcessor在初始化时，又依赖了其它bean，而其它bean又要被BeanPostProcessor处理所以打印了日志  
   b. 在org.springframework.context.support.PostProcessorRegistrationDelegate.registerBeanPostProcessors(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, org.springframework.context.support.AbstractApplicationContext)  
      大概270~271行，nonOrderedPostProcessors中包括两个BeanPostProcessor，是globalErrorChannelCustomizer（org.springframework.cloud.stream.config.BindingServiceConfiguration.globalErrorChannelCustomizer）和integrationMbeanExporter（org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration.IntegrationJmxConfiguration.integrationMbeanExporter）  
      导致了一系列的提前创建bean，创建bean断点可以打在 AbstractBeanFactory 中的大概335行（return createBean(beanName, mbd, args);）  
   c. 参考 org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory 大概531行（Object bean = resolveBeforeInstantiation(beanName, mbdToUse);）  
   d. 总结在定义Advisor接口对应的bean和BeanPostProcessor对应的bean时，需要单独使用配置类配置，否则可能导致整个大配置类过早实例化缺少全部BeanPostProcessor处理的机会    
9. 关于分布式部署的注意事项，针对单机多实例部署或微服务部署  
   a. 被 @sheduled 注解的方法需要加锁，可以继承 global-scheduler-boot-starter 模块中的 AbstractGlobalScheduler 使用 lockExecutor 加钞  
   b. 如果使用 lockExecutor ，目前在分布式下需要添加 <artifactId>shedlock-provider-jdbc-template</artifactId>依赖，使用数据库方式支持,详细内容参考 global-concurrency-boot-starter模块下的 README.md 
   c. session,在 global-session-boot-starter 模块中依赖了 spring session，分布式中可以使用数据库或redis等，详见：global-session-boot-starter 模块的 README.md  
   d. mq,如果不与第三方系统mq交互，可以使用默认的local binder，否则需要配置对应的mq配置，可参见 global-messaging-boot-starter 模块的 README.md，及测试资源中的 application.yml配置  
10. 关于使用低代码生成的注意事项  
   a. 生成后端模块需要在父级 component pom.xml中添加 类型模块 <module>xxxxx</module>  
   b. 生成后端服务，如果指定了包名，需要在 boot-starter配置类中配置 mybatis mapper接口包路径  
   c. 添加后端管理菜单尽量使用添加 crud 功能按钮添加，注意权限码前缀结尾一般带冒号，保持一致  
   d. crud添加成功后，需要手动修改对应的数据，在每个权限码（分页查询、添加、修改）前面添加对应的路径code，以备后面进行路由权限判断使用（暂没有使用到，但以备后期使用）  