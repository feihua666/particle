package com.particle.global.big.datasource.bigdatasource.impl.http.enums;

import com.particle.global.tool.json.JsonTool;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * http 大数据源接口，内容类型
 * </p>
 *
 * @author yangwei
 * @since 2023-03-27 13:45
 */
public enum HttpBigDatasourceApiConfigContentType {

	/**
	 * application/json
	 */
	application_json
	,
	/**
	 * multipart/form-data
	 */
	multipart_form_data
	,
	/**
	 * application/x-www-form-urlencoded
	 */
	application_x_www_form_urlencoded
	,
	/**
	 * text/plain
	 */
	text_plain
	,
	/**
	 * text/xml
	 */
	text_xml,
	/**
	 * 无，在get方法时请求内容类型不用填写
	 */
	none

	;

	private static Map<HttpBigDatasourceApiConfigContentType, String> contentTypeMapping;
	private static Map<HttpBigDatasourceApiConfigContentType, Function<Object,String>> commandStringMapping;

	private static void initContentTypeMapping(){
		if (contentTypeMapping == null) {
			contentTypeMapping = new HashMap<>();
			contentTypeMapping.put(application_json, MediaType.APPLICATION_JSON_VALUE);
			contentTypeMapping.put(multipart_form_data, MediaType.MULTIPART_FORM_DATA_VALUE);
			contentTypeMapping.put(application_x_www_form_urlencoded, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
			contentTypeMapping.put(text_plain, MediaType.TEXT_PLAIN_VALUE);
			contentTypeMapping.put(text_xml, MediaType.TEXT_XML_VALUE);
			contentTypeMapping.put(none, "");
		}
	}
	private static void initCommandStringMapping(){
		if (commandStringMapping == null) {
			Function<Object,String> jsonFunc = JsonTool::toJsonStr;
			Function<Object,String> stringFunc = t -> t == null? null : t.toString();
			commandStringMapping = new HashMap<>();
			commandStringMapping.put(application_json, jsonFunc);
			commandStringMapping.put(multipart_form_data, jsonFunc);
			commandStringMapping.put(application_x_www_form_urlencoded, jsonFunc);
			commandStringMapping.put(text_plain, stringFunc);
			commandStringMapping.put(text_xml, stringFunc);
			commandStringMapping.put(none, t -> {
				if (t == null) {
					return null;
				}
				if (t instanceof String) {
					return t.toString();
				}
				return jsonFunc.apply(t);
			});
		}
	}
	/**
	 * 获取对应的 contentType
	 * @return
	 */
	public String getContentType(){
		initContentTypeMapping();
		return contentTypeMapping.get(this);
	}

	/**
	 * 根据内容类型获取对应的字符串值
	 * @param command
	 * @return
	 */
	public String getCommandString(Object command) {
		initCommandStringMapping();
		return commandStringMapping.get(this).apply(command);
	}
}
