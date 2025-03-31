 package com.particle.agi;

 import com.particle.agi.infrastructure.ai.AgiAgentChatMemory;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 /**
  * <p>
  * 自动配置类
  * </p>
  *
  * @author yw
  * @since 2025-02-24 10:51:18
  */
 @Configuration(proxyBeanMethods = false)
 public class AgiChatAutoConfiguration {

     @Bean
     public AgiAgentChatMemory agiAgentChatMemory() {
         return new AgiAgentChatMemory();
     }
 }
