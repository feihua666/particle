package com.particle.global.big.datasource.bigdatasource.test;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.DefaultBigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKeyHolder;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.http.HttpBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.http.api.config.HttpBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.config.HttpBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigContentType;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestMethod;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestUrlRenderType;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceAuthScriptType;
import com.particle.global.tool.json.JsonTool;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 动态大数据源测试
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 12:59
 */
public class DynamicHttpBigDatasourceTest {

	public static void main(String[] args) throws Exception {
		DynamicBigDatasource dynamicBigDatasource = new DynamicBigDatasource();
		dynamicBigDatasource.addBigDatasource("http",httpBigDatasource());

		DynamicBigDatasourceRoutingKeyHolder.set("http");

		BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();

		//Object httpBigDatasourceAESEncryptApiResponse = apiExecutor.execute(httpBigDatasourceAESEncryptApi(), aesEncryptCommand());
		//System.out.println("httpBigDatasourceAESEncryptApiResponse :" +  JsonTool.toJsonStr(httpBigDatasourceAESEncryptApiResponse));

		Object httpExecutePageResponse = apiExecutor.execute(httpBigDatasourcePageApi(), httpBigDatasourceMapPageQueryCommand(),null);
		System.out.println("httpExecutePageResponse :" +  JsonTool.toJsonStr(httpExecutePageResponse));

	}


	public static BigDatasource httpBigDatasource() throws Exception {
		HttpBigDatasourceConfig httpBigDatasourceConfig = HttpBigDatasourceConfig.create(
				"http://localhost:8080",
				HttpBigDatasourceAuthScriptType.groovy_script,
				"headers.put('c-token-id','e37818ef-8f15-48c4-a6f8-fc85dfe36395')",null,null);
		HttpBigDatasource httpBigDatasource = HttpBigDatasource.create("testHttpName", BigDatasourceType.datasource_http,httpBigDatasourceConfig);

		return httpBigDatasource;
	}

	/**
	 * 调用 {@link com.particle.tools.adapter.web.front.AESController#encrypt(com.particle.tools.client.dto.command.AesEncryptCommand)}
	 * @return
	 */
	public static BigDatasourceApi httpBigDatasourceAESEncryptApi(){

		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				"testName",
				"httpBigDatasourceAESEncryptApi",
				BigDatasourceApiResponseWrapType.proxy,
				HttpBigDatasourceApiConfig.create(
						HttpBigDatasourceApiConfigRequestMethod.post,
						HttpBigDatasourceApiConfigContentType.application_json,
						HttpBigDatasourceApiConfigContentType.application_json,
						HttpBigDatasourceApiConfigRequestUrlRenderType.raw,
						"/front/web/aes/encrypt"
				)
		);
		return defaultBigDatasourceApi;
	}
	public static BigDatasourceApi httpBigDatasourcePageApi(){
		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				"testName",
				"httpBigDatasourcePageApi",
				BigDatasourceApiResponseWrapType.proxy,
				HttpBigDatasourceApiConfig.create(
						HttpBigDatasourceApiConfigRequestMethod.get,
						null,
						HttpBigDatasourceApiConfigContentType.application_json,
						HttpBigDatasourceApiConfigRequestUrlRenderType.raw,
						"/admin/web/area/page"
				)
		);
		return defaultBigDatasourceApi;
	}

	public static Map<String,Object> aesEncryptCommand(){

		Map<String,Object> map = new HashMap<>();
		map.put("data", "系统管理");
		map.put("key", "asderftghyujhgfr");

		return  map;
	}
	public static Map<String,Object> httpBigDatasourceMapQueryCommand(){

		Map<String,Object> map = new HashMap<>();
		map.put("name", "系统管理");

		return  map;
	}
	public static Map<String,Object> httpBigDatasourceMapPageQueryCommand(){return httpBigDatasourceMapQueryCommand();}
}
