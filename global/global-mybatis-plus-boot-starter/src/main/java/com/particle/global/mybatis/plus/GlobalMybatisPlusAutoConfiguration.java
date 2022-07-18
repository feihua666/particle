package com.particle.global.mybatis.plus;

import com.particle.global.mybatis.plus.config.GlobalMybatisPlusConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 17:36
 */
@Configuration
@Import(GlobalMybatisPlusConfig.class)
public class GlobalMybatisPlusAutoConfiguration {
}
