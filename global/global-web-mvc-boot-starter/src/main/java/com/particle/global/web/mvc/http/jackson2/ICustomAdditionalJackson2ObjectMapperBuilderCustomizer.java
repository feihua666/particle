package com.particle.global.web.mvc.http.jackson2;

import com.fasterxml.jackson.databind.Module;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.List;

/**
 * <p>
 * 额外配置
 * 统一自定义配置，提供一个额外的入口
 * 作用赋：{@link Jackson2ObjectMapperBuilderCustomizer}
 * 添加当前接口支持的缘由是：在添加 global-actuator-boot-starter 中的 spring boot admin时，发现其返回的数据实例id序列化有问题，这里单独提供扩展
 * </p>
 *
 * @author yangwei
 * @since 2023-06-30 13:50
 */
public interface ICustomAdditionalJackson2ObjectMapperBuilderCustomizer {

	void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder);

	/**
	 * 额外modules
	 * @param jacksonObjectMapperBuilder
	 * @return
	 */
	default List<Module> modules(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder){
		return null;
	}
}
