package com.particle.global.tool.template;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.google.common.collect.Lists;
import com.particle.global.tool.document.excel.ExcelHead;
import com.particle.global.tool.document.excel.ExcelTool;
import com.particle.global.tool.http.HttpClientTool;
import lombok.Data;
import org.apache.http.message.BasicHeader;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * excel相关测试工具类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-26 14:07
 */
public class ExcelTest {
	public static void main(String[] args) throws FileNotFoundException {
		testWriteTool();
		//testWrite();
	}


	private static void testWriteTool() throws FileNotFoundException {
		TestBean bean1 = new TestBean();
		bean1.setName("张三");
		bean1.setAge(22);
		bean1.setPass(true);
		bean1.setScore(66.30);
		bean1.setExamDate(DateUtil.date());

		TestBean bean2 = new TestBean();
		bean2.setName("李四");
		bean2.setAge(28);
		bean2.setPass(false);
		bean2.setScore(38.50);
		bean2.setExamDate(DateUtil.date());

		List<TestBean> rows = CollUtil.newArrayList(bean1, bean2);
		BufferedInputStream inputStream = FileUtil.getInputStream("/Users/yw/temp/test.xlsx");
		FileOutputStream fileOutputStream = new FileOutputStream("/Users/yw/temp/test1.xlsx");
		ExcelTool.writeBeanAll(inputStream, rows, TestBean.class, fileOutputStream);
	}

	private static void testWrite() {
		TestBean bean1 = new TestBean();
		bean1.setName("张三");
		bean1.setAge(22);
		bean1.setPass(true);
		bean1.setScore(66.30);
		bean1.setExamDate(DateUtil.date());

		TestBean bean2 = new TestBean();
		bean2.setName("李四");
		bean2.setAge(28);
		bean2.setPass(false);
		bean2.setScore(38.50);
		bean2.setExamDate(DateUtil.date());

		List<TestBean> rows = CollUtil.newArrayList(bean1, bean2);
		// 通过工具类创建writer
		ExcelWriter writer = ExcelUtil.getWriter("/Users/yw/temp/writeBeanTest.xlsx");

		//自定义标题别名
		writer.addHeaderAlias("name", "姓名");
		writer.addHeaderAlias("age", "年龄");
		writer.addHeaderAlias("score", "分数");
		writer.addHeaderAlias("isPass", "是否通过");
		writer.addHeaderAlias("examDate", "考试时间");

		// 默认的，未添加alias的属性也会写出，如果想只写出加了别名的字段，可以调用此方法排除之
		writer.setOnlyAlias(true);

		// 合并单元格后的标题行，使用默认标题样式
		//writer.merge(4, "一班成绩单");
		// 一次性写出内容，使用默认样式，强制输出标题
		writer.write(rows, true);
		// 关闭writer，释放内存
		writer.close();
	}

	private static void testBean(){

		Map of = Dict.of("A", TestBean.nameProperty, "B", TestBean.name1Property);

		List<TestBean> ts = ExcelTool.readBeanAll(FileUtil.getInputStream("/Users/yw/temp/test.xlsx"), TestBean.class, of);

		System.out.println(ts);

		ts = ExcelTool.readBeanAll(FileUtil.getInputStream("/Users/yw/temp/test.xlsx"), TestBean.class);
		System.out.println(ts);
	}

	private static void test(){
		ExcelReader reader = ExcelUtil.getReader(FileUtil.file("/Users/yw/yuansu/temp/component_user.xlsx"));
		List<List<Object>> read = reader.read(1);
		for (List<Object> objectList : read) {
			JSONObject jsonObject = get();
			String userId = objectList.get(0).toString();

			jsonObject.set("userId", userId);
			jsonObject.set("tenantId", "1649388860938944514");


			try {
				String s = HttpClientTool.postJson("http://10.15.20.142:8080/admin/web/tenant_user/create", jsonObject.toString(),
						HttpClientTool.ExtConfig.builder().build().addHeader("c-token-id", "a4824c94-4c66-4f1d-9d5c-6f3bfc95e8a5")
				);
				JSONObject jsonObject1 = JSONUtil.parseObj(s);
				if (!jsonObject1.getBool("success")) {
					System.err.println(jsonObject.toString());
					System.err.println(s);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static JSONObject get(){
		String str = "{\n" +
				"    \"userId\": \"1651119271870377986\",\n" +
				"    \"name\": \"hgzsyjzx\",\n" +
				"    \"tenantId\": \"1649388860938944514\",\n" +
				"    \"isExpired\": false,\n" +
				"    \"expiredReason\": null,\n" +
				"    \"expireAt\": null,\n" +
				"    \"isLeave\": false,\n" +
				"    \"leaveReason\": null,\n" +
				"    \"leaveAt\": null\n" +
				"}";
		return JSONUtil.parseObj(str);
	}


	@Data
	private static class TestBean{
		public static String nameProperty = "name";
		public static String name1Property = "name1";

		@ExcelHead(readAlias = "A")
		private String name;
		@ExcelHead(readAlias = "B")
		private String name1;


		private int age;
		private double score;
		private boolean isPass;
		private Date examDate;
	}
}
