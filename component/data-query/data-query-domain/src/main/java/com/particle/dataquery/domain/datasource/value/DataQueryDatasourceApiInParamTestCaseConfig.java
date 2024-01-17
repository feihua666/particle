package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiParamType;
import com.particle.global.dto.basic.Value;
import com.particle.global.tool.json.JsonTool;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 入参测试用例配置
 * </p>
 *
 * @author yangwei
 * @since 2024-01-16 13:01:21
 */
@Data
public class DataQueryDatasourceApiInParamTestCaseConfig extends Value {


	/**
	 * 测试用例配置信息
	 */
	private List<TestCaseItem> inParamTestCases;

	public static DataQueryDatasourceApiInParamTestCaseConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiInParamTestCaseConfig config = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiInParamTestCaseConfig.class);
		return config;
	}


	@Data
	public static class TestCaseItem {
		/**
		 * 名称
		 */
		private String name;
		/**
		 * 内容值
		 */
		private String content;


		public Object contentToObj(DataQueryDatasourceApiParamType dataQueryDatasourceApiParamType) {
			return dataQueryDatasourceApiParamType.adaptType(content);
		}


		public static TestCaseItem create(
									  String name,
									  String content) {
			TestCaseItem testCaseItem = new TestCaseItem();
			testCaseItem.name = name;
			testCaseItem.content = content;

			return testCaseItem;
		}
	}
}
