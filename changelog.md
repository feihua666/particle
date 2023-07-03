
# Changelog
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