package com.particle.global.big.datasource.bigdatasource.test;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.DefaultBigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKeyHolder;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.Neo4jBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.api.config.Neo4jBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.config.Neo4jBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.enums.Neo4jBigDatasourceApiConfigDataType;
import com.particle.global.tool.json.JsonTool;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 动态大数据源测试
 * </p>
 *
 * @author yangwei
 * @since 2023-11-21 13:46:03
 */
public class DynamicNeo4jBigDatasourceTest {

	public static void main(String[] args) throws Exception {
		DynamicBigDatasource dynamicBigDatasource = new DynamicBigDatasource();
		dynamicBigDatasource.addBigDatasource("neo4j",neo4jBigDatasource());

		DynamicBigDatasourceRoutingKeyHolder.set("neo4j");

		BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();

		Object neo4jExecuteMultiResponse = apiExecutor.execute(neo4jBigDatasourceListApi(), neo4jBigDatasourceMapQueryCommand(),null);
		System.out.println("neo4jExecuteMultiResponse :" +  JsonTool.toJsonStr(neo4jExecuteMultiResponse));


		Object neo4jExecuteOneResponse = apiExecutor.execute(neo4jBigDatasourceOneApi(), neo4jBigDatasourceMapQueryCommand(),null);
		System.out.println("neo4jExecuteOneResponse :" +  JsonTool.toJsonStr(neo4jExecuteOneResponse));
		//
		Object neo4jExecutePageResponse = apiExecutor.execute(neo4jBigDatasourcePageApi(), neo4jBigDatasourceMapPageQueryCommand(),null);
		System.out.println("neo4jExecutePageResponse :" +  JsonTool.toJsonStr(neo4jExecutePageResponse));


		BigDatasourceExecutor executor = dynamicBigDatasource.getExecutor(neo4jBigDatasourceListApi());

		Object neo4jExecuteMultiResponse1 = executor.execute(neo4jBigDatasourceMapPageQueryCommand(),null);
		System.out.println("neo4jExecuteMultiResponse1 :" +  JsonTool.toJsonStr(neo4jExecuteMultiResponse1));

		executor = dynamicBigDatasource.getExecutor(neo4jBigDatasourcePageApi());
		Object neo4jExecutePageResponse1 = executor.execute(neo4jBigDatasourceMapPageQueryCommand(),null);
		System.out.println("neo4jExecutePageResponse1 :" +  JsonTool.toJsonStr(neo4jExecutePageResponse1));

	}


	public static BigDatasource neo4jBigDatasource() throws Exception {
		Neo4jBigDatasourceConfig neo4jBigDatasourceConfig = Neo4jBigDatasourceConfig.create(
				"bolt://localhost:7687",
				"neo4j",
				"neo4jneo4j");
		Neo4jBigDatasource neo4jBigDatasource = Neo4jBigDatasource.createByNeo4jBigDatasourceConfig("testNeo4jName", BigDatasourceType.datasource_neo4j,neo4jBigDatasourceConfig);

		return neo4jBigDatasource;
	}
	public static BigDatasourceApi neo4jBigDatasourceListApi(){

		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				"neo4jBigDatasourceListApi",
				BigDatasourceApiResponseWrapType.multiple,
				Neo4jBigDatasourceApiConfig.createByWithRawType(Neo4jBigDatasourceApiConfigDataType.multiple,
						"match (n) return n",null)
		);
		return defaultBigDatasourceApi;
	}
	public static BigDatasourceApi neo4jBigDatasourceOneApi(){

		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				"neo4jBigDatasourceOneApi",
				BigDatasourceApiResponseWrapType.single,
				Neo4jBigDatasourceApiConfig.createByWithRawType(Neo4jBigDatasourceApiConfigDataType.single,
						"match (n) return n limit 1",null)
		);
		return defaultBigDatasourceApi;
	}
	public static BigDatasourceApi neo4jBigDatasourcePageApi(){
		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				"neo4jBigDatasourcePageApi",
				BigDatasourceApiResponseWrapType.page,
				Neo4jBigDatasourceApiConfig.createByWithRawType(Neo4jBigDatasourceApiConfigDataType.page,
						"match (n) return n","match (n) return count(*)"
				)
		);
		return defaultBigDatasourceApi;
	}
	public static Map<String,Object> neo4jBigDatasourceMapQueryCommand(){

		Map<String,Object> map = new HashMap<>();
		map.put("name", "系统管理");

		return  map;
	}
	public static Map<String,Object> neo4jBigDatasourceMapPageQueryCommand(){return neo4jBigDatasourceMapQueryCommand();}
}
