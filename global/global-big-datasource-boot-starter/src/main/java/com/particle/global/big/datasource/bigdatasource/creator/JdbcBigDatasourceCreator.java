package com.particle.global.big.datasource.bigdatasource.creator;

import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.properties.BigDatasourceProperty;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.JdbcBigDatasource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * jdbc 大数据源创建器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 22:14
 */
@Component
public class JdbcBigDatasourceCreator implements BigDatasourceCreator{
	@Override
	public boolean support(BigDatasourceProperty bigDatasourceProperty) {
		return bigDatasourceProperty.getType() == BigDatasourceType.datasource_jdbc;
	}

	@Override
	public BigDatasource createBigDatasource(BigDatasourceProperty bigDatasourceProperty) {
		JdbcBigDatasource jdbcBigDatasource = JdbcBigDatasource.create(
				bigDatasourceProperty.getName(),
				bigDatasourceProperty.getType(),
				bigDatasourceProperty.getJdbc()
		);
		for (Map.Entry<String, DataSourceProperty> entry : bigDatasourceProperty.getJdbc().getDatasource().entrySet()) {
			jdbcBigDatasource.addDataSourceByDataSourceProperty(entry.getKey(),entry.getValue());
		}
		return jdbcBigDatasource;
	}
}
