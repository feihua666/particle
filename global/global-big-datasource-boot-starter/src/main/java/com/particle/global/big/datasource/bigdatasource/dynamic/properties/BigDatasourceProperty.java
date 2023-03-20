package com.particle.global.big.datasource.bigdatasource.dynamic.properties;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>
 * 具体数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 20:40
 */
@Slf4j
@Data
@Accessors(chain = true)
public class BigDatasourceProperty {
	/**
	 * 数据源名称，仅展示
	 */
	private String name;
	/**
	 * 数据源类型
	 */
	private BigDatasourceType type;
	/**
	 * jdbc支持多数据源，如果类型为该项，请填写 jdbc
	 */
	private DynamicDataSourceProperties jdbc;
}
