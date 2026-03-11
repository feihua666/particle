package com.particle.global.autoconfigure;

import com.particle.global.autoconfigure.feign.GlobalFeignConfiguration;
import com.particle.global.autoconfigure.tool.GlobalToolConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * 全局自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 14:59
 */
@Configuration(proxyBeanMethods = false)
@Import({GlobalToolConfiguration.class, GlobalFeignConfiguration.class})
// 指定一下顺序，因为 TraceTool中使用了micrometer tracing，所以需要 MicrometerTracingAutoConfiguration 之后
@AutoConfigureAfter(name = "org.springframework.boot.actuate.autoconfigure.tracing.MicrometerTracingAutoConfiguration")
public class GlobalAutoConfigureAutoConfiguration {

}
