/**
 * <p>
 * 提供本地支持的能力，旨在不改变代码的情况下一致的文件使用 spring-cloud-stream
 * 相当于是一个本地的mq实现
 * 截止到现在本项目使用的是 3.2.8版本的spring-cloud-stream ，在其中有一个测试包实现了 一个名为 integration 的 binder
 * 引入如下：
 *         <dependency>
 *             <groupId>org.springframework.cloud</groupId>
 *             <artifactId>spring-cloud-stream</artifactId>
 *             <type>test-jar</type>
 *             <classifier>test-binder</classifier>
 *             <scope>test</scope>
 *         </dependency>
 *
 * 代码来自于：https://github.com/spring-cloud/spring-cloud-stream/tree/v3.2.8/core/spring-cloud-stream/src/test/java/org/springframework/cloud/stream/binder/test
 * 并稍作修改
 *
 * 如果spring-cloud-stream升级到了4.0.0，那么该包应该不不需要了，界时 META-INF/spring.binders 也不需要了
 * </p>
 *
 * @author yangwei
 * @since 2023-05-22 11:21
 */
package com.particle.global.messaging.binder.local;