# 缓存
缓存在系统中旨在一些多查询少写入场景提供高性能的查询速度，本组件旨在封装一些基于spring的缓存
包括开启缓存等配置

只需要引入依赖和添加 @EnableCaching 注解，即可自动注入缓存，默认实现是 org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration 内存map缓存
如果想要改为其它缓存只需要添加对应的依赖，和配置以spring.cache开头的对应配置即可