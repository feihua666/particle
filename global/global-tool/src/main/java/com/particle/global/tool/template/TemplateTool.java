package com.particle.global.tool.template;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;

import java.util.Map;

/**
 * <p>
 * 模板工具类
 * </p>
 *
 * @author yangwei
 * @since 2021-11-26 18:33
 */
public class TemplateTool {

	/**
	 * 默认的全局模版引擎
	 */
	public static final TemplateEngine defaultTemplateEngine = TemplateUtil.createEngine(new TemplateConfig());

	/**
	 * 渲染字符串模板和数据
	 * @param template
	 * @param data
	 * @return
	 */
	public static String render(String template, Map<String,Object> data){
		return render(template,data,defaultTemplateEngine);
	}

	/**
	 * 渲染字符串模板和数据
	 * @param template
	 * @param data
	 * @param engine 自定义引擎
	 * @return
	 */
	public static String render(String template, Map<String,Object> data,TemplateEngine engine){
		if (data == null || data.isEmpty()) {
			return template;
		}
		if (StrUtil.isBlank(template)) {
			return template;
		}
		Template template1 = engine.getTemplate(template);
		return template1.render(data);
	}
}
