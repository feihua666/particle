# redis全局组件
redis 在项目中非常常用，这里提出一个全局组件以方便积累和常用依赖

其中 spring-boot-starter-data-redis 只是组了一个局，默认依赖了 lettuce-core （redis的一个客户端，这里spring官方推荐的，原来的jedis不再默认使用）
同时也依赖了 spring-data-redis

