package com.particle.global.big.datasource.bigdatasource.test;

import com.google.common.collect.Lists;
import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.DefaultBigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKeyHolder;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.ElasticsearchBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.api.config.ElasticsearchBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.config.ElasticsearchBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.enums.ElasticsearchBigDatasourceApiConfigDataType;
import com.particle.global.tool.json.JsonTool;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 动态大数据源测试
 * </p>
 *
 * @author yangwei
 * @since 2023-12-10 20:40:00
 */
public class DynamicElasticsearchBigDatasourceTest {

	public static void main(String[] args) throws Exception {
		DynamicBigDatasource dynamicBigDatasource = new DynamicBigDatasource();
		dynamicBigDatasource.addBigDatasource("elasticsearch",elasticsearchBigDatasource());

		DynamicBigDatasourceRoutingKeyHolder.set("elasticsearch");

		BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();

		Object elasticsearchExecuteMultiResponse = apiExecutor.execute(elasticsearchBigDatasourceListApi(), elasticsearchBigDatasourceMapQueryCommand(),null);
		System.out.println("elasticsearchExecuteMultiResponse :" +  JsonTool.toJsonStr(elasticsearchExecuteMultiResponse));


		Object elasticsearchExecuteOneResponse = apiExecutor.execute(elasticsearchBigDatasourceOneApi(), elasticsearchBigDatasourceMapQueryCommand(),null);
		System.out.println("elasticsearchExecuteOneResponse :" +  JsonTool.toJsonStr(elasticsearchExecuteOneResponse));
		//
		Object elasticsearchExecutePageResponse = apiExecutor.execute(elasticsearchBigDatasourcePageApi(), elasticsearchBigDatasourceMapPageQueryCommand(),null);
		System.out.println("elasticsearchExecutePageResponse :" +  JsonTool.toJsonStr(elasticsearchExecutePageResponse));


		BigDatasourceExecutor executor = dynamicBigDatasource.getExecutor(elasticsearchBigDatasourceListApi());

		Object elasticsearchExecuteMultiResponse1 = executor.execute(elasticsearchBigDatasourceMapPageQueryCommand(),null);
		System.out.println("elasticsearchExecuteMultiResponse1 :" +  JsonTool.toJsonStr(elasticsearchExecuteMultiResponse1));

		executor = dynamicBigDatasource.getExecutor(elasticsearchBigDatasourcePageApi());
		Object elasticsearchExecutePageResponse1 = executor.execute(elasticsearchBigDatasourceMapPageQueryCommand(),null);
		System.out.println("elasticsearchExecutePageResponse1 :" +  JsonTool.toJsonStr(elasticsearchExecutePageResponse1));


		dynamicBigDatasource.close();
	}


	public static BigDatasource elasticsearchBigDatasource() throws Exception {
		ElasticsearchBigDatasourceConfig elasticsearchBigDatasourceConfig = ElasticsearchBigDatasourceConfig.create(
				Lists.newArrayList("http://localhost:9200"),
				"elasticsearch",
				"elasticsearchelasticsearch");
		ElasticsearchBigDatasource elasticsearchBigDatasource = ElasticsearchBigDatasource.createByElasticsearchBigDatasourceConfig("testElasticsearchName", BigDatasourceType.datasource_es,elasticsearchBigDatasourceConfig);

		return elasticsearchBigDatasource;
	}
	public static BigDatasourceApi elasticsearchBigDatasourceListApi(){

		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				"testName",
				"elasticsearchBigDatasourceListApi",
				BigDatasourceApiResponseWrapType.multiple,
				ElasticsearchBigDatasourceApiConfig.createByWithRawType(ElasticsearchBigDatasourceApiConfigDataType.multiple,
						"",
						"{\n" +
								"    \"query\": {\n" +
								"        \"match\": {\n" +
								"            \"title\": \"测试es0\"\n" +
								"        }\n" +
								"    },\n" +
								"    \"sort\": {\n" +
								"            \"id\": {\n" +
								"                \"order\": \"desc\"\n" +
								"            }\n" +
								"        }\n" +
								"}", null).withIndexNames("test_esdo")
		);
		return defaultBigDatasourceApi;
	}
	public static BigDatasourceApi elasticsearchBigDatasourceOneApi(){

		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				"testName",
				"elasticsearchBigDatasourceOneApi",
				BigDatasourceApiResponseWrapType.single,
				ElasticsearchBigDatasourceApiConfig.createByWithRawType(ElasticsearchBigDatasourceApiConfigDataType.single,
						"",
						"{\n" +
								"    \"indexNames\": \"test_esdo\",\n" +
								"    \"query\": {\n" +
								"        \"term\": {\n" +
								"            \"id\": 1733067962731585551\n" +
								"        }\n" +
								"    }\n" +
								"}",null)
		);
		return defaultBigDatasourceApi;
	}
	public static BigDatasourceApi elasticsearchBigDatasourcePageApi(){
		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				"testName",
				"elasticsearchBigDatasourcePageApi",
				BigDatasourceApiResponseWrapType.page,
				ElasticsearchBigDatasourceApiConfig.createByWithRawType(ElasticsearchBigDatasourceApiConfigDataType.page,
						"",
						"{\n" +
								"    \"indexNames\": \"test_esdo\",\n" +
								"    \"query\": {\n" +
								"        \"match\": {\n" +
								"            \"title\": \"测试es0\"\n" +
								"        }\n" +
								"    },\n" +
								"    \"sort\": [\n" +
								"        {\n" +
								"            \"id\": {\n" +
								"                \"order\": \"desc\"\n" +
								"            }\n" +
								"        }\n" +
								"    ]\n" +
								"}",""
				)
		);
		return defaultBigDatasourceApi;
	}
	public static Map<String,Object> elasticsearchBigDatasourceMapQueryCommand(){

		Map<String,Object> map = new HashMap<>();
		map.put("name", "系统管理");

		return  map;
	}
	public static Map<String,Object> elasticsearchBigDatasourceMapPageQueryCommand(){return elasticsearchBigDatasourceMapQueryCommand();}
}
