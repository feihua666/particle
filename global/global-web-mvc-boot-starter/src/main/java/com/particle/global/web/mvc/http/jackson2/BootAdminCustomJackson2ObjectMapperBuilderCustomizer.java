package com.particle.global.web.mvc.http.jackson2;


import com.fasterxml.jackson.databind.Module;
import com.google.common.collect.Lists;
import de.codecentric.boot.admin.server.utils.jackson.AdminServerModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.List;

/**
 * <p>
 * 自定义序列化，spring boot admin server ui问题
 * 该类本应该放在 global-actuator-boot-starter 模块中，但有maven依赖循环问题，所以先放在这
 * 原因：{@see https://github.com/codecentric/spring-boot-admin/issues/1446}
 * </p>
 *
 * @author yangwei
 * @since 2023-06-30 13:59
 */
public class BootAdminCustomJackson2ObjectMapperBuilderCustomizer implements ICustomAdditionalJackson2ObjectMapperBuilderCustomizer {
	@Override
	public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
	}

	@Override
	public List<Module> modules(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
		return Lists.newArrayList(new AdminServerModule(new String[]{".*password$"}));
	}
}
