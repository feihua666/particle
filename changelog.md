
# Changelog

# 4.0.1-beta(2024-12-06)
### 新特性

* 【navigation】导航业务组件添加
* 【global-freemarker-boot-starter】freemarker模板全局组件添加，旨提供统一的配置，目前添加是为了支持 navigation 业务组件
* 【particle-demo】vue版本升级，同步elementplus版本升级，调整前端样式，前端logo支持在环境变量中使用
* 【open-platform】开放平台调用记录的日实时汇总添加开关配置，在不需要时可关闭
* 【user】调整登录页面，支持动态验证码登
* 【data-query】调整前端页面，支持部分模糊查询字段，如：配置字段可以模糊查询，以方便查询里面的引用情况
### bug修复

* 暂无
### 其它

* 部分数据库字段调整长度
* 其它细节功能的修改


# 4.0.0-beta(2024-10-22)
### 新特性

* 【particle-demo】默认使用jdbc存储session方便快速部署多实例
* 【open-platform】开放接口记录调用添加时间，以记录更加准备的时间；开放接口文档相关修改；配合【global-openapi-boot-starter】支持添加计费功能、调用额度配置；添加后台管理开放接口查询功能、批量查询功能
* 【feedback】意见反馈业务组件添加
* 【low-code】低代码加载模型项已支持mysql唯一单字段索引，低代码加载模型项生成建表语句过滤字符编码
* 【global-oss-boot-starter】oss下载支持urlencode编码
* 【crm】客户管理业务组件添加
* 【global-actuator-boot-starter】bootadmin支持可配置禁用
* 【config】参数配置管理业务组件添加，支持系统参数配置，favicon.ico、logo、logo text、web title 支持接口配置
* 【data-constraint】数据范围约束业务组件添加，以支持数据权限管理，自定义数据对象，自定义数据范围
* 【user】初始化虚拟超级管理员，以方便测试功能权限
* 【op-log】操作日志添加全局mvc异常日志记录方便排查问题，可以后台异常日志查看
* 【data】数据业务组件添加，旨在一些处理数据或以数据提供开放接口的场景提供支持
* 【global-openapi-boot-starter】添加限制规则功能，可对接口进行按访问次数、访问费用等限制，添加开放接口限流功能，可配置qps流量限制
* 【global-big-datasource-boot-starter】大数据源es查询支持自定义查询query
* 【data-query】
1. 支持经量级预热，已经默认开启，默认不再开启数据查询接口调用预热
2. 数据源接口支持数据查询字符串字段
3. 出参扩展配置支持自定义输出最终结果，如：Response.buildSuccess()
4. 数据查询字符串字典支持，添加数据查询字符串字典项
5. 出参扩展配置支持自定义设置数据源接口供应商业务响应代码，一般用于http业务响应代码
   全局支持根据Response设置响应状态码
### bug修复

* 【global-bootstrap-boot-starter】应用关闭监听，添加逻辑判断，防止重复调用，只调用一次
* 【usage-count】修改记录使用次数记录，在没有配置时报500的问题，直接修改为20
* 【low-code】低代码生成片段 adapter-feign-client，变量引用错误，应该为：parent.outputVar.nameVar
* 【global-openapi-boot-starter】在开放接口校验流水号缓存时没有进行清楚，导致OOM问题，改为固定缓存大小
* 【global-mybatis-plus-boot-starter】修改在使用查询command添加@like注解时，字符串为空，导致拼接%问
### 其它

* 其它细节功能的修改

# 3.0.0-beta(2024-01-23)
### 新特性

* 【global-security-boot-starter】添加LoginTool工具类，以支持在租户区分时的差异
* 【global-big-datasource-boot-starter】大数据源添加接口是否使用缓存，默认不使用，入参与出参扩展配置，neo4j支持、es支持，jdbc分页查询时是否查询总数，可配置、响应结果数据翻译支持
* 【global-neo4j-boot-starter】图数据库基础规范类组件，以支持对图数据库neo4j的访问
* 【data-query】支持开发模式，以防止误操作删除等带来问题，支持了刷新缓存功能，数据查询接口预热支持，修改大部分weekCache为timeCache这些缓存一般必须存在，提升性能
* 【global-elasticsearch-boot-starter】es模块对接
### bug修复

* 【global-openapi-boot-starter】缓存key问题修改，否则导致数据错乱
### 其它

* 无


# 2.1.0-beta(2023-10-31)
### 新特性

* 【前端】支持组件内容变更监听
* 【report】新增支持开放接口配置、支持上传到oss及其它迎合报告业务组件的配套修
* 【global-oss-boot-starter】支持程序传参控制是否拼接endpoint
* 【global-exception-handle-boot-starter】修改全局异常捕获，尝试解析原始异常是否为BizException
* 【usage-count】新增用于进行接口或功能访问计数和限制，其它配套功能微调
* 【user】用户登录相关修改，主要是添加提示合理的异常信息适配，登录用户租户信息中添加是否正式字段
* 【global-session-boot-starter】添加session支持可配置header或cookie存储，默认header
### bug修复

* 【global-big-datasource-boot-starter】修改大数据源执行器缓存配置不生效问
* 【tenant】一键添加租户判断审核状态数据不正确问题
* 【user】登录时使用参数拼接的方式如果有&字符会分隔成参数问题，登录时错误提示问题处理
### 其它
* 【global-tool】模板树渲染获取数据脚本去掉try catch 主要不转换异常，统一由全局异常处理  

# 2.0.0-beta(2023-08-28)
### 新特性

* 【open-platform】添加开放接口管理业务组件，配合 global-openapi-boot-starter，实现方便的接入开放接口，支持对 data-query 业务组件的配置化兼容
* 【global-openapi-boot-starter】添加全局开放接口全局组件，以对外提供api支持
* 【oauth2authorization】oauth2 server jdbc支持以实现后台可界面化管理，配合 global-security-boot-starter 的 oauth2 authorization server使用
* 【global-session-boot-starter】修改springsession支持jdbc和redis简单配置就可使用
* 【global-redis-boot-starter】添加 global-redis-boot-starter 全局redis组件
* 【global-scheduler-boot-starter】添加AbstractGlobalScheduler，方便定时使用带锁执行
* 【tenant】支持根据域名解析租户
* 【user】添加当前登录用户可修改头像、昵称等接口
* 【global-security-boot-starter】添加oauth2 authorization server支持，添加resource server支持
* 【global-scheduler-boot-starter】添加scheduler手动执行任务web接口，以方便手动任务触发
* 【global-mybatis-plus-boot-starter 】@In查询可以使用自定义字段名称
* 升级动态多数据源到4.1.1 添加swagger启用控制 升级 swagger2到openapi3
### bug修复

* 【tenant】租户一键创建密码取值错误问题处理
* 【global-notification-boot-starter】修改notification，alertAccount 字符proxyConfig改为proxy
* 【global-trans-boot-stater】修改翻译时去掉了租户不考虑问题，否则在多租户下这将导致数据错乱

# 1.0.0-beta(2023-07-03)
### 新特性
* 【global-captcha-boot-starter】添加验证码全局模块，支持图片、动画、算术、中文，支持动态验证码如：短信、邮件
* 【global-data-audit-boot-starter】添加全局操作日志及数据审计功能框架部分
* 【op-log】添加操作日志、数据审计持久化业务组件
* 【global-big-datasource-boot-starter】大数据源支持 delete，put等方法
* 【global-document-boot-starter】添加全局文档模块，支持将文件模板配置到 resources/META-INF下，以供下载模板
* 【global-wxjava-boot-starter】添加全局微信对接模块，尚未完全开发完成，只是添加了依赖占位
* 【global-scheduler-boot-starter】添加任务计划全局模块，统一配置任务计划线程池
* 【global-mybatis-plus-boot-starter 】添加一个分页遍历工具方法
### bug修复
* 【user】修改密码bug、添加用户时，登录标识处理bug、绑定登录标识，校验问题、用户列表支持返回角色信息
* 【global-concurrency-boot-starter】线程池可完美继承主程序线程变量
* 【global-tool】ThreadContextTool使用ttl线程变量，结合线程池使用，添加proxyConfig全局工具对象，以支持接口调用代理情况、重构HttpClientTool
* 【tenant】添加租户用户添加多参数支持功能、添加租户用户列表查询可以根据部门查询、租户申请添加用户时默认正式

# 0.1.0-beta (2023-04-24)
### 新特性
* 【data-query】
    1. 数据服务添加groovy脚本动态配置支持
    2. 添加数据服务支持动态加载数据源
    3. 多接口聚合能力
* 【func】添加应用管理，功能可以自由组合成应用
* 【dept】组织机构/部门业务组件包括部门、部门树、部门名称
* 【tenant】多租户业务组件包括租户管理、租户应用管理、租户用户管理、租户申请管理
* 【global-mybatis-plus-boot-starter】升级mybatis plus到3.5.3，添加基本mybatis plus数据权限技术支持
* 【global-trans-boot-stater】添加数据翻译支持多级翻译
### bug修复
* 【data-query】
    1. 数据查询字典数据媒体类型修改，更便于理解
    2. 代码生成的import引用文件名与实际文件名首字母大小写不匹配
* 【low-code】代码生成的权限问题处理

# 0.0.1-beta (2023-03-22)
0.0.1-beta初始发布
### 业务组件
* 【area】
* 【component-adapter-boot-starter】
* 【component-light-share】
* 【componenttemplate】
* 【data-constraint】
* 【data-query】
* 【dict】
* 【func】
* 【generator】
* 【low-code】
* 【role】
* 【test】
* 【tools】
* 【user】
### 全局组件
* 【global-actuator-boot-starter】
* 【global-adapter-boot-starter】
* 【global-big-datasource-boot-starter】
* 【global-bootstrap-boot-starter】
* 【global-catchlog-boot-starter】
* 【global-concurrency-boot-starter】
* 【global-data-permission-boot-starter】
* 【global-datasource-boot-starter】
* 【global-domain-boot-starter】
* 【global-dto】
* 【global-exception-handle-boot-starter】
* 【global-exception】
* 【global-light-share】
* 【global-logging-boot-starter】
* 【global-messaging-boot-starter】
* 【global-mybatis-plus-boot-starter】
* 【global-notification-boot-starter】
* 【global-project-info-boot-starter】
* 【global-ratelimit-boot-starter】
* 【global-security-boot-starter】
* 【global-session-boot-starter】
* 【global-sleuth-boot-starter】
* 【global-swagger-boot-starter】
* 【global-test】
* 【global-tool】
* 【global-trans-boot-stater】
* 【global-validation-boot-starter】
* 【global-web-filter-boot-starter】
* 【global-web-mvc-boot-starter】
