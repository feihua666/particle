package com.particle.global.big.datasource.bigdatasource.impl.http;

import com.particle.global.big.datasource.bigdatasource.AbstractBigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.DefaultBigDatasourceExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.http.config.HttpBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.executor.HttpBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.impl.BigDatasourceHttpJoddClientImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * <p>
 * http大数据源实现
 * </p>
 *
 * @author yangwei
 * @since 2023-03-27 13:51
 */
@Slf4j
public class HttpBigDatasource extends AbstractBigDatasource {

	private HttpBigDatasourceConfig  httpBigDatasourceConfig;

	private HttpBigDatasourceApiExecutor httpBigDatasourceApiExecutor;
	@Override
	public BigDatasourceApiExecutor getApiExecutor() throws BigDatasourceException {
		return httpBigDatasourceApiExecutor;
	}

	@Override
	public BigDatasourceExecutor getExecutor(BigDatasourceApi bigDatasourceApi) throws BigDatasourceException {
		return new DefaultBigDatasourceExecutor(bigDatasourceApi, httpBigDatasourceApiExecutor);
	}

	@Override
	public void close() throws IOException {
		log.info("{} close do nothing",HttpBigDatasource.class.getName());
	}

	public static HttpBigDatasource create(String name,
										   BigDatasourceType type,
										   HttpBigDatasourceConfig  httpBigDatasourceConfig) {
		HttpBigDatasource httpBigDatasource = new HttpBigDatasource();
		httpBigDatasource.setName(name);
		httpBigDatasource.setType(type);
		httpBigDatasource.setHttpBigDatasourceConfig(httpBigDatasourceConfig);

		HttpBigDatasourceApiExecutor httpBigDatasourceApiExecutor = HttpBigDatasourceApiExecutor.create(BigDatasourceHttpJoddClientImpl.create(), httpBigDatasourceConfig);
		httpBigDatasource.setHttpBigDatasourceApiExecutor(httpBigDatasourceApiExecutor);

		return httpBigDatasource;
	}


	public HttpBigDatasourceConfig getHttpBigDatasourceConfig() {
		return httpBigDatasourceConfig;
	}

	public void setHttpBigDatasourceConfig(HttpBigDatasourceConfig httpBigDatasourceConfig) {
		this.httpBigDatasourceConfig = httpBigDatasourceConfig;
	}

	public HttpBigDatasourceApiExecutor getHttpBigDatasourceApiExecutor() {
		return httpBigDatasourceApiExecutor;
	}

	public void setHttpBigDatasourceApiExecutor(HttpBigDatasourceApiExecutor httpBigDatasourceApiExecutor) {
		this.httpBigDatasourceApiExecutor = httpBigDatasourceApiExecutor;
	}
}
