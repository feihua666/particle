package com.particle.global.big.datasource.bigdatasource;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.DefaultBigDatasourceExecutor;
import lombok.Data;

/**
 * <p>
 * 大数据源抽象层
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 10:34
 */
@Data
public abstract class AbstractBigDatasource implements BigDatasource {

	/**
	 * 数据源名称
	 */
	private String name;
	/**
	 * 数据源类型
	 */
	private BigDatasourceType type;

	private BigDatasourceExecutor bigDatasourceExecutor;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public BigDatasourceType getType() {
		return type;
	}


	@Override
	public BigDatasourceExecutor getExecutor(BigDatasourceApi bigDatasourceApi) throws BigDatasourceException {
		if (bigDatasourceExecutor == null) {
			bigDatasourceExecutor = new DefaultBigDatasourceExecutor(bigDatasourceApi, getApiExecutor());
		}
		return bigDatasourceExecutor;
	}
}
