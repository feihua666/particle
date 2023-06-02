package com.particle.global.tool.json;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.particle.global.tool.calendar.CalendarTool;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.SneakyThrows;

import java.util.Collection;

/**
 * <p>
 * json工具
 * </p>
 *
 * @author yangwei
 * @since 2022-04-13 19:52
 */
public class JsonTool {


	public static JSONConfig jsonConfig = JSONConfig.create().setDateFormat(CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()).setIgnoreNullValue(false);
	/**
	 * 转化为json
	 * @param object
	 * @return
	 */
	public static String toJsonStr(Object object) {
		if (object == null) {
			return null;
		}
		if (object instanceof Collection) {

			return JSONUtil.parseArray(object,
					jsonConfig).toString();
		}
		return JSONUtil.parseObj(object,
				jsonConfig).toString();
	}

	/**
	 * 首选确定一点，本项目整体使用 jackson 对json和对象之间做http转换
	 * 在某一些 filter过滤器中如果使用 {@link JsonTool#toJsonStr(java.lang.Object)} 转为json字符串，最终表现可能和 spring controller 中返回的数据转json不一致
	 * 其原因是因为转换原理不同，如果要手动转json响应到页面，使用该方法表现一致
	 * @param object 支持集合和对象
	 * @return
	 */
	@SneakyThrows(JsonProcessingException.class)
	public static String toJsonStrForHttp(Object object, ObjectMapper objectMapper) {
		return objectMapper.writeValueAsString(object);
	}

	/**
	 * 同上面的方法，多加一个视图
	 * @param object
	 * @param objectMapper
	 * @param view
	 * @return
	 */
	@SneakyThrows(JsonProcessingException.class)
	public static String toJsonStrForHttp(Object object, ObjectMapper objectMapper,Class view) {
		return objectMapper.writerWithView(view).writeValueAsString(object);
	}
}
