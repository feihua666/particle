package com.particle.global.big.datasource.bigdatasource.dynamic.provider;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;

import javax.sql.DataSource;
import java.util.Map;

/**
 * <p>
 * 抽象类
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 21:48
 */
public abstract class AbstractDynamicBigDatasourceProvider implements DynamicBigDatasourceProvider{
	@Override
	public Map<DynamicBigDatasourceRoutingKey, BigDatasource> loadDataSources() {
		return doLoadDataSources();
	}

	public abstract Map<DynamicBigDatasourceRoutingKey, BigDatasource> doLoadDataSources();
}
