package com.particle.global.web.mvc.http.jackson2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.particle.global.tool.calendar.CalendarTool;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	@Override
	public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {

		//DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES 相当于配置，JSON串含有未知字段时，反序列化依旧可以成功
		jacksonObjectMapperBuilder.failOnUnknownProperties(false);
		//针对于Date类型，文本格式化
		jacksonObjectMapperBuilder.dateFormat(new CustomDateFormat());
		//序列化时的命名策略——驼峰命名法
		jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
		//针对于JDK新时间类。序列化时带有T的问题，自定义格式化字符串
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern( CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
		javaTimeModule.addSerializer(localDateTimeSerializer);
		LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern( CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
		javaTimeModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
		jacksonObjectMapperBuilder.modules(javaTimeModule,new ParameterNamesModule());

		//默认关闭，将char[]数组序列化为String类型。若开启后序列化为JSON数组。
		jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS);

		//默认开启，若map的value为null，则不对map条目进行序列化。(已废弃)。
		// 推荐使用：jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
		//jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_NULL_MAP_VALUES);

		//默认开启，将Date类型序列化为数字时间戳(毫秒表示)。关闭后，序列化为文本表现形式(2019-10-23T01:58:58.308+0000)
		//若设置时间格式化。那么均输出格式化的时间类型。
		jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		//默认关闭。打开后BigDecimal序列化为文本。(已弃用)，推荐使用JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN配置
		// jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
		//默认关闭，即使用BigDecimal.toString()序列化。开启后，使用BigDecimal.toPlainString序列化，不输出科学计数法的值。
		jacksonObjectMapperBuilder.featuresToEnable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

		/**
		 * JsonGenerator.Feature的相关参数（JSON生成器）
		 */

		//默认关闭，即序列化Number类型及子类为{"amount1":1.1}。开启后，序列化为String类型，即{"amount1":"1.1"}
		//jacksonObjectMapperBuilder.featuresToEnable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);

		/******
		 *  反序列化
		 */
		//默认关闭，当JSON字段为""(EMPTY_STRING)时，解析为普通的POJO对象抛出异常。开启后，该POJO的属性值为null。
		jacksonObjectMapperBuilder.featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);


		/**
		 * 序列换成json时,将所有的long变成string
		 * 因为js中得数字类型不能包含所有的java long值
		 */
		jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
		jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
	}


	@Override
	public int getOrder() {
		// 此值需要大于0 ，因为在 spring默认的配置中是0
		return 1;
	}
}
