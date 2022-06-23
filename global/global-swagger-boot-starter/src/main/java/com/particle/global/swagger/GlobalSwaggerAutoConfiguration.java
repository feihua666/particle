package com.particle.global.swagger;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * <p>
 * swagger 全局默认开启
 * </p>
 *
 * @author yangwei
 * @since 2022-05-19 16:50
 */
@ComponentScan
@EnableOpenApi
public class GlobalSwaggerAutoConfiguration {
}
