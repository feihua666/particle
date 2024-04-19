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

	static String excelTestTemplate = "/Users/yw/fh/git-source/particle/global/global-tool/src/test/java/com/particle/global/tool/template/ExcelTestTemplate.xlsx";

	public static void main(String[] args) throws FileNotFoundException {
		// testBeanRead();
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
		BufferedInputStream inputStream = FileUtil.getInputStream(excelTestTemplate);
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

	private static void testBeanRead(){

		Map of = Dict.of("A", TestBean.nameProperty, "B", TestBean.name1Property);

		List<TestBean> ts = ExcelTool.readBeanAll(FileUtil.getInputStream(excelTestTemplate), TestBean.class, of);

		System.out.println(ts);

		ts = ExcelTool.readBeanAll(FileUtil.getInputStream("/Users/yw/temp/test.xlsx"), TestBean.class);
		System.out.println(ts);
	}


	@Data
	private static class TestBean{
		public static String nameProperty = "name";
		public static String name1Property = "name1";

		@ExcelHead(readAlias = "姓名", writeAlias = "姓名")
		private String name;

		private String name1;

		@ExcelHead(readAlias = "B")
		private int age;
		private double score;
		private boolean isPass;
		private Date examDate;
	}
}
