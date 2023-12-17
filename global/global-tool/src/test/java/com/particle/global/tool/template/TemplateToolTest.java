package com.particle.global.tool.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
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


		String paramsJson = "{\n" +
				"    \"entName\": \"樊科实业（上海）有限公司\",\n" +
				"    \"regNo\": \"\",\n" +
				"    \"revenue\": \"\",\n" +
				"    \"regionId\": \"\",\n" +
				"    \"opScope\": \"\",\n" +
				"    \"totalProfit\": \"\",\n" +
				"    \"nicId\": \"\",\n" +
				"    \"esDate\": \"\",\n" +
				"    \"assetsRe\": \"\",\n" +
				"    \"entStatus\": \"\",\n" +
				"    \"regcap\": \"222$333\",\n" +
				"    \"tImport\": \"\",\n" +
				"    \"entType\": \"\",\n" +
				"    \"seicId\": \"\",\n" +
				"    \"tExport\": \"$333\",\n" +
				"    \"empNum\": \"\",\n" +
				"    \"indexTotal\": \"\",\n" +
				"    \"pageSize\": \"\",\n" +
				"    \"pageIndex\": \"\",\n" +
				"    \"socNum\": \"\",\n" +
				"    \"faren\": \"\",\n" +
				"    \"sortOrder\": \"\",\n" +
				"    \"maibusinc\": \"\",\n" +
				"    \"ratgro\": \"\",\n" +
				"    \"vendinc\": \"\",\n" +
				"    \"sortField\": \"\"\n" +
				"}";

		JSONObject entries = JSONUtil.parseObj(paramsJson);

		String render = TemplateTool.render(template, TemplateRenderDataWrap.create(entries));
		System.out.println(render);

		JSONObject jsonObject = JSONUtil.parseObj(render);
		CollectionTool.clearMap(jsonObject);
		System.out.println(jsonObject.toString());
	}
}
