package com.particle.global.tool.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.particle.global.tool.collection.CollectionTool;
import com.particle.global.tool.json.JsonTool;

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
		// test1();
		test2();
	}

	public static void test1() {
		String render = TemplateTool.render("#(empty == '' || empty == null  ? 'empty' : 'notEmpty') #(null?? 'null')", Dict.of("empty", "", "null", null));
		System.out.println(render);


		render = TemplateTool.render("#(emptyStrToNull(empty)?? aa) #(null?? 'null')",
				Dict.of("empty", "", "null", null,"aa","aa"));
		System.out.println(render);

		render = TemplateTool.render("#(firstNoneEmptyStr(empty,null,aa))",
				Dict.of("empty", "", "null", null,"aa","aa"));
		System.out.println(render);

		render = TemplateTool.render("#(firstNoneEmptyStr(empty,null,aa))",
				Dict.of("empty", "", "null", null,"aa","aa"));
		System.out.println(render);

		String t = "#set(tableHeader = [\n" +
				"{\n" +
				"\"valueLabel\": \"注销日期\",\n" +
				"\"valueKey\": \"candate\"\n" +
				"}\n" +
				"])" +
				"#(tableHeader)";

		render = TemplateTool.render(t,
				Dict.of("empty", "", "null", null,"aa","aa"));
		System.out.println(render);
	}

	public static void test2() {
		String template = FileUtil.readUtf8String("/Users/yw/yuansu/temp/esparam.template");
		String[] split = "sss$".split("\\$");
		System.out.println(split);

		String render1 = TemplateTool.render("#set(age = \"\") #(age.toInt()) #(regReplaceAll('330000','0{2}$|0{4}$',''))", TemplateRenderDataWrap.create(null));
		System.out.println("render1render1" + render1);

		String string = ReUtil.replaceAll("330000", "0{2}$|0{4}$", "");
		System.out.println("sstring=" + string);

		String paramsJson = "{\n" +
				"  \"regionId\": \"330000\",\n" +
				"  \"pageIndex\": \"3\",\n" +
				"  \"regcap\": \"50$\",\n" +
				"  \"pageSize\": \"20\",\n" +
				"  \"entStatus\": \"1\",\n" +
				"  \"vendinc\": \"500$600\",\n" +
				"  \"revenue\": \"20$\",\n" +
				"  \"nicId\": \"G\"\n" +
				"}";

		JSONObject entries = JSONUtil.parseObj(paramsJson);

		String render = TemplateTool.render(template, TemplateRenderDataWrap.create(entries));
		System.out.println(render);

		JSONObject jsonObject = JSONUtil.parseObj(render);
		CollectionTool.clearMap(jsonObject);
		System.out.println(jsonObject.toString());
	}
}
