package com.particle.generator.infrasructure.test;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.extra.template.engine.TemplateFactory;
import cn.hutool.extra.template.engine.enjoy.EnjoyEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-01-09 18:31
 */
public class EnjoyTest {
	public static void main(String[] args) {

		ArrayList<Object> objects = new ArrayList<>();
		objects.add("123");

		Dict data = Dict.of("maps", objects);

		String addDataTemplate = "#(maps.add('ssss'))";
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setCustomEngine(EnjoyEngine.class);
		Template template = TemplateUtil.createEngine(templateConfig).getTemplate(addDataTemplate);

		String render = template.render(data);
		System.out.println(render);
		System.out.println(data);
	}
}
