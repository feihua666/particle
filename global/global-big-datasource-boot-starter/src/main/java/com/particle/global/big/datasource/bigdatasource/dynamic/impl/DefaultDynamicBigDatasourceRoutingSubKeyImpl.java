package com.particle.global.big.datasource.bigdatasource.dynamic.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingSubKey;
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
public class DefaultDynamicBigDatasourceRoutingSubKeyImpl extends DefaultDynamicBigDatasourceRoutingKeyImpl implements DynamicBigDatasourceRoutingSubKey {

	private String subKey;

	@Override
	public boolean match(DynamicBigDatasourceRoutingKey<String> key) {
		boolean match = super.match(key);
		if (match) {
			if (key instanceof DynamicBigDatasourceRoutingSubKey) {
				return StrUtil.equals(((DynamicBigDatasourceRoutingSubKey) key).subKey(),subKey);
			}
		}

		return match;
	}

	public static DynamicBigDatasourceRoutingKey create(String key,String subKey) {
		DefaultDynamicBigDatasourceRoutingSubKeyImpl defaultDynamicBigDatasourceRoutingKey = new DefaultDynamicBigDatasourceRoutingSubKeyImpl();
		defaultDynamicBigDatasourceRoutingKey.setKey(key);
		defaultDynamicBigDatasourceRoutingKey.setSubKey(subKey);
		return defaultDynamicBigDatasourceRoutingKey;
	}

	@Override
	public String subKey() {
		return subKey;
	}
}
