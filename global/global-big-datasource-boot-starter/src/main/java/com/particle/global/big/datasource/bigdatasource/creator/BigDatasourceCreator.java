package com.particle.global.big.datasource.bigdatasource.creator;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.properties.BigDatasourceProperty;

/**
 * <p>
 * 大数据源创建器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 21:56
 */
public interface BigDatasourceCreator {

	/**
	 * 数据源创建器
	 * @param bigDatasourceProperty
	 * @return
	 */
	boolean support(BigDatasourceProperty bigDatasourceProperty);

	/**
	 *
	 * @param bigDatasourceProperty
	 * @return
	 */
	BigDatasource createBigDatasource(BigDatasourceProperty bigDatasourceProperty);

}
