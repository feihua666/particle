package com.particle.global.tool.template;

import cn.hutool.core.lang.Dict;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-07-04 14:12
 */
public class TemplateToolTest {
	public static void main(String[] args) {
		String render = TemplateTool.render("#(empty == '' || empty == null  ? 'empty' : 'notEmpty') #(null?? 'null')", Dict.of("empty", "", "null", null));
		System.out.println(render);


		render = TemplateTool.render("#(emptyStrToNull(empty)?? aa) #(null?? 'null')",
				Dict.of("empty", "", "null", null,"aa","aa"));
		System.out.println(render);

		render = TemplateTool.render("#(firstNoneEmptyStr(empty,null,aa))",
				Dict.of("empty", "", "null", null,"aa","aa"));
		System.out.println(render);
	}
}
