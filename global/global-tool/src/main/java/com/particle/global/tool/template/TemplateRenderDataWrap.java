package com.particle.global.tool.template;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 包装要渲染的数据
 * </p>
 *
 * @author yangwei
 * @since 2023-03-18 12:05
 */
@Data
public class TemplateRenderDataWrap <T>{
	/**
	 *  这样的好处是
	 *  如果：
	 *  1. data属性为字符串，通过包装可以在模板中使用类似 #{data}
	 *  1. data属性为对象，通过包装可以在模板中使用类似 #{data.[fieldName]}
	 */
	private T data;

	private Object ext;

	public static <T> TemplateRenderDataWrap<T> create(T data) {
		TemplateRenderDataWrap<T> tTemplateRenderDataWrap = new TemplateRenderDataWrap<>();
		tTemplateRenderDataWrap.setData(data);
		return tTemplateRenderDataWrap;
	}
	public static <T> TemplateRenderDataWrap<T> create(T data,Object ext) {
		TemplateRenderDataWrap<T> templateRenderDataWrap = create(data);
		templateRenderDataWrap.setExt(ext);
		return templateRenderDataWrap;
	}
	public Map<String,T> toRenderMap(){
		Map map = new HashMap();
		map.put("data", data);
		map.put("ext", ext);
		return map;
	}
}
