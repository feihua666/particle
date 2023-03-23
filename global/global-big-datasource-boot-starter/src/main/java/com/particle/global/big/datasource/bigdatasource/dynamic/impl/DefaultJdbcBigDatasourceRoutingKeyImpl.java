package com.particle.global.big.datasource.bigdatasource.dynamic.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.dynamic.JdbcBigDatasourceRoutingKey;
import lombok.Data;

/**
 * <p>
 * 默认的路由键 字符串
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 11:19
 */
@Data
public class DefaultJdbcBigDatasourceRoutingKeyImpl extends DefaultDynamicBigDatasourceRoutingKeyImpl implements JdbcBigDatasourceRoutingKey<String> {

	// subKey用于内部路由
	private String subKey;

	@Override
	public boolean match(DynamicBigDatasourceRoutingKey<String> key) {
		boolean match = super.match(key);
		return match;
	}

	public static DynamicBigDatasourceRoutingKey create(String key,String subKey) {
		DefaultJdbcBigDatasourceRoutingKeyImpl defaultDynamicBigDatasourceRoutingKey = new DefaultJdbcBigDatasourceRoutingKeyImpl();
		defaultDynamicBigDatasourceRoutingKey.setKey(key);
		defaultDynamicBigDatasourceRoutingKey.setSubKey(subKey);
		return defaultDynamicBigDatasourceRoutingKey;
	}

	@Override
	public String subKey() {
		return subKey;
	}
}
