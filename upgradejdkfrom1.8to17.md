# jdk升级1.8到17

### 关于import  xxx.xx* 问题
升级到 jdk17 后，在 java 8 中，import xxx.xx* 是可以的，但是jdk17中，有一个类报错已经重新导入改为了， import xxx.xx.x
出错的类：com.particle.global.neo4j.test.demo.test.DirectUseTest

### 关于js引擎问题
在使用js引擎找不到的问题，是因为jdk17移除了默认依赖，需要手动依赖
参见：https://github.com/dromara/hutool/issues/1915
具体报错使用类：com.particle.dream.infrastructure.ssq.gateway.impl.SsqCodeCrawlingGatewayImpl
