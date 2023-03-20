package com.particle.global.big.datasource.bigdatasource.dynamic.provider;

import cn.hutool.core.map.MapUtil;
import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.creator.BigDatasourceCreator;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKeyFactory;
import com.particle.global.big.datasource.bigdatasource.dynamic.properties.BigDatasourceProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配置文件加载方式
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 21:50
 */
@Data
public class YmlDynamicBigDatasourceProvider extends AbstractDynamicBigDatasourceProvider{

	private Map<String, BigDatasourceProperty> bigDatasourcePropertyMap;

	private List<BigDatasourceCreator> bigDatasourceCreatorList;


	@Override
	public Map<DynamicBigDatasourceRoutingKey, BigDatasource> doLoadDataSources() {

		if (bigDatasourcePropertyMap == null || bigDatasourceCreatorList == null) {
			return MapUtil.empty();
		}
		Map<DynamicBigDatasourceRoutingKey, BigDatasource> dataSourceMap = new HashMap<>();

		for (Map.Entry<String, BigDatasourceProperty> entry : bigDatasourcePropertyMap.entrySet()) {
			for (BigDatasourceCreator bigDatasourceCreator : bigDatasourceCreatorList) {
				if (bigDatasourceCreator.support(entry.getValue())) {
					dataSourceMap.put(
							DynamicBigDatasourceRoutingKeyFactory.of(entry.getKey()),
							bigDatasourceCreator.createBigDatasource(entry.getValue())
					);
				}
			}
		}
		return dataSourceMap;
	}
}
