# 全局过滤器模块

该模块封装了常用的过滤器，包括日志打印、traceId响应等。
注意：在forward情况下，在tomcat中会再走一遍请求，同时也会过filter（就像重新请求一样），但是继承了OncePerRequestFilter的过滤器默认在forward情况下不会再执行了。
一般目前前后端分离情况下，不会用到forward，但有一些错误情况，比如404，找不到路径，会forward重定向到/error,这时OncePerRequestFilter可以继承并重写方法shouldNotFilterErrorDispatch返回false，

## 功能特性

1. **请求过滤**：提供统一的请求过滤处理机制
2. **日志记录**：自动记录请求和响应日志
3. **链路追踪**：支持traceId生成和传递
4. **跨域支持**：提供跨域请求处理支持

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-web-filter-boot-starter</artifactId>
</dependency>
```

### 配置选项

```yaml
particle:
  web:
    filter:
      # 过滤器相关配置
```

注意：在forward情况下，在tomcat中会再走一遍请求，同时也会过filter（就像重新请求一样），但是继承了OncePerRequestFilter的过滤器默认在forward情况下不会再执行了。一般目前前后端分离情况下，不会用到forward，但有一些错误情况，比如404，找不到路径，会forward重定向到/error,这时OncePerRequestFilter可以继承并重写方法shouldNotFilterErrorDispatch返回false。

### 核心组件

- GlobalWebFilterAutoConfiguration：全局过滤器自动配置
- 请求日志过滤器：记录请求和响应日志
- TraceId过滤器：生成和传递链路追踪ID
- 跨域过滤器：处理跨域请求

## 依赖组件

- Spring Boot Starter Web
- Global Tool模块
- Micrometer Tracing

## 示例代码

```java
// 自定义过滤器
@Component
public class CustomFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // 请求前处理
        long startTime = System.currentTimeMillis();
        
        try {
            chain.doFilter(request, response);
        } finally {
            // 响应后处理
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            log.info("请求处理耗时: {}ms, URL: {}", duration, httpRequest.getRequestURI());
        }
    }
}

// 在配置类中注册过滤器
@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<CustomFilter> customFilterRegistration() {
        FilterRegistrationBean<CustomFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CustomFilter());
        registration.addUrlPatterns("/*");
        registration.setName("customFilter");
        registration.setOrder(1);
        return registration;
    }
}
```