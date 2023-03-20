package com.particle.global.big.datasource.bigdatasource.dynamic.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
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
public class DefaultDynamicBigDatasourceRoutingKeyImpl implements DynamicBigDatasourceRoutingKey<String> {

	private String key;

	@Override
	public boolean match(DynamicBigDatasourceRoutingKey<String> key) {
		if (key instanceof DefaultDynamicBigDatasourceRoutingKeyImpl) {
			return StrUtil.equals(this.key,((DefaultDynamicBigDatasourceRoutingKeyImpl) key).key);
		}
		return false;
	}

	@Override
	public String toStringKey() {
		return key;
	}

	public static DynamicBigDatasourceRoutingKey create(String key) {
		DefaultDynamicBigDatasourceRoutingKeyImpl defaultDynamicBigDatasourceRoutingKey = new DefaultDynamicBigDatasourceRoutingKeyImpl();
		defaultDynamicBigDatasourceRoutingKey.setKey(key);
		return defaultDynamicBigDatasourceRoutingKey;
	}
}
