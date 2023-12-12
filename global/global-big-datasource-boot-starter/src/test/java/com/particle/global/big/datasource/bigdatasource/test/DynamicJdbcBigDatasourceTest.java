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
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.JdbcBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config.JdbcBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.config.JdbcBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigDataType;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.tool.json.JsonTool;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

/**
 * <p>
 * 动态大数据源测试
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 12:59
 */
public class DynamicJdbcBigDatasourceTest {

	public static void main(String[] args) throws Exception {
		DynamicBigDatasource dynamicBigDatasource = new DynamicBigDatasource();
		dynamicBigDatasource.addBigDatasource("jdbc",jdbcBigDatasource());

		DynamicBigDatasourceRoutingKeyHolder.set("jdbc","particle_dev");

		BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();

		//Object jdbcExecuteMultiResponse = apiExecutor.execute(jdbcBigDatasourceListApi(), jdbcBigDatasourceMapQueryCommand(),null);
		//System.out.println("jdbcExecuteMultiResponse :" +  JsonTool.toJsonStr(jdbcExecuteMultiResponse));
		//
		Object jdbcExecutePageResponse = apiExecutor.execute(jdbcBigDatasourcePageApi(), jdbcBigDatasourceMapPageQueryCommand(),null);
		System.out.println("jdbcExecutePageResponse :" +  JsonTool.toJsonStr(jdbcExecutePageResponse));


		//BigDatasourceExecutor executor = dynamicBigDatasource.getExecutor(jdbcBigDatasourceListApi());
		//
		//Object jdbcExecuteMultiResponse1 = executor.execute(jdbcBigDatasourceMapPageQueryCommand());
		//System.out.println("jdbcExecuteMultiResponse1 :" +  JsonTool.toJsonStr(jdbcExecuteMultiResponse1));

		//executor = dynamicBigDatasource.getExecutor(jdbcBigDatasourcePageApi());

		//Object jdbcExecutePageResponse1 = executor.execute(jdbcBigDatasourceMapPageQueryCommand());
		//System.out.println("jdbcExecutePageResponse1 :" +  JsonTool.toJsonStr(jdbcExecutePageResponse1));

	}


	public static BigDatasource jdbcBigDatasource() throws Exception {
		JdbcBigDatasourceConfig jdbcBigDatasourceConfig = JdbcBigDatasourceConfig.create(
				"com.mysql.cj.jdbc.Driver",
				"jdbc:mysql://localhost/particle_test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8",
				"root", "rootroot");
		JdbcBigDatasource jdbcBigDatasource = JdbcBigDatasource.create("testJdbcName", BigDatasourceType.datasource_jdbc);

		jdbcBigDatasource.addDataSourceByJdbcBigDatasourceConfig("particle_dev",jdbcBigDatasourceConfig);

		return jdbcBigDatasource;
	}
	public static BigDatasourceApi jdbcBigDatasourceListApi(){

		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				"jdbcBigDatasourceListApi",
				BigDatasourceApiResponseWrapType.multiple,
				JdbcBigDatasourceApiConfig.createByWithRawType(JdbcBigDatasourceApiConfigDataType.multiple,
						"<script>" + "select * from component_func " +
								"<where>" +
								"<if test=\"data.data.name != null\"> name = #{data.data.name}</if>" +
								"</where>" + "</script>")
		);
		return defaultBigDatasourceApi;
	}
	public static BigDatasourceApi jdbcBigDatasourcePageApi(){
		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				"jdbcBigDatasourcePageApi",
				BigDatasourceApiResponseWrapType.page,
				JdbcBigDatasourceApiConfig.createByWithRawType(JdbcBigDatasourceApiConfigDataType.page,
						"<script>" + "select * from component_func " +
								"<where>" +
								"<if test=\"data.data.name != null\"> name = #{data.data.name}</if>" +
								"</where>" + "</script>"
				)
		);
		return defaultBigDatasourceApi;
	}
	public static Map<String,Object> jdbcBigDatasourceMapQueryCommand(){

		Map<String,Object> map = new HashMap<>();
		map.put("name", "系统管理");

		return  map;
	}
	public static Map<String,Object> jdbcBigDatasourceMapPageQueryCommand(){return jdbcBigDatasourceMapQueryCommand();}
}
