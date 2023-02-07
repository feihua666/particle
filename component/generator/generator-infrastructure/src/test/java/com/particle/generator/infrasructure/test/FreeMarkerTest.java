package com.particle.generator.infrasructure.test;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-01-09 16:35
 */
public class FreeMarkerTest {
	public static void main(String[] args) {

		//ArrayList<Object> objects = new ArrayList<>();
		//objects.add("123");
		Map<String, Object> maps = new HashMap<>();
		maps.put("aaa", "3333");
		Dict data = Dict.of("maps", maps);

		String addDataTemplate = "${maps?api.put('ssss','sss')}";
		Template template = TemplateUtil.createEngine().getTemplate(addDataTemplate);

		String render = template.render(data);
		System.out.println(render);
		System.out.println(data);
	}
}
