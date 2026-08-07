package com.particle.global.web.mvc.http.jackson2;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.particle.global.tool.calendar.CalendarTool;
import com.particle.global.tool.json.jackson2.Jackson2ObjectMapperBuilderCustomize;
import com.particle.global.tool.obj.NullObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自定义配置 Jackson2ObjectMapper
 * 原理参见 {@link JacksonAutoConfiguration} {@link JacksonAutoConfiguration.JacksonObjectMapperBuilderConfiguration#jacksonObjectMapperBuilder(org.springframework.context.ApplicationContext, java.util.List)}
 * </p>
 * 参考：https://www.jianshu.com/p/89f8040fe956
 * @author yangwei
 * @since 2022-07-28 13:56
 */
@Component
public class CustomJackson2ObjectMapperBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer, Ordered {

	@Autowired(required = false)
	private List<ICustomAdditionalJackson2ObjectMapperBuilderCustomizer> customAdditionalJackson2ObjectMapperBuilderCustomizerList;

	@Override
	public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
		List<com.fasterxml.jackson.databind.Module> modules = new ArrayList<>();
		if (customAdditionalJackson2ObjectMapperBuilderCustomizerList != null) {
			for (ICustomAdditionalJackson2ObjectMapperBuilderCustomizer iCustomAdditionalJackson2ObjectMapperBuilderCustomizer : customAdditionalJackson2ObjectMapperBuilderCustomizerList) {
				iCustomAdditionalJackson2ObjectMapperBuilderCustomizer.customize(jacksonObjectMapperBuilder);
				List<Module> moduleList = iCustomAdditionalJackson2ObjectMapperBuilderCustomizer.modules(jacksonObjectMapperBuilder);
				if (CollectionUtil.isNotEmpty(moduleList)) {
					modules.addAll(moduleList);
				}
			}
		}
		Jackson2ObjectMapperBuilderCustomize.customize(jacksonObjectMapperBuilder,modules);


	}


	@Override
	public int getOrder() {
		// 此值需要大于0 ，因为在 spring默认的配置中是0
		return 1;
	}
}
