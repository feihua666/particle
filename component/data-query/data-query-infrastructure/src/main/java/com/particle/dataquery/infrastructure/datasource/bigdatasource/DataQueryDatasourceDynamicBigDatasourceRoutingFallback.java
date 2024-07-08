package com.particle.dataquery.infrastructure.datasource.bigdatasource;

import com.google.common.collect.Lists;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceService;
import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingFallback;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.dynamic.JdbcBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.dynamic.impl.DefaultJdbcBigDatasourceRoutingKeyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.particle.dataquery.infrastructure.datasource.bigdatasource.DataQueryDatasourceDynamicBigDatasourceProvider.dataQueryGlobalBigDatasourceRoutingKey;

/**
 * <p>
 * 在路由失败时尝试动态加载
 * </p>
 *
 * @author yangwei
 * @since 2023-03-22 23:37
 */
@Slf4j
@Component
public class DataQueryDatasourceDynamicBigDatasourceRoutingFallback implements DynamicBigDatasourceRoutingFallback {

	@Autowired
	private DataQueryDatasourceDynamicBigDatasourceProvider dataQueryDatasourceDynamicBigDatasourceProvider;
	@Autowired
	private IDataQueryDatasourceService iDataQueryDatasourceService;

	@Override
	public BigDatasource routedFailedFallback(DynamicBigDatasourceRoutingKey routingKey) {

		if (routingKey instanceof JdbcBigDatasourceRoutingKey) {
			String key = routingKey.toStringKey();
			String subKey = ((JdbcBigDatasourceRoutingKey) routingKey).subKey();
			// dataquery使用一个大数据源
			if (dataQueryGlobalBigDatasourceRoutingKey.equals(key)) {
				// subKey 到这里就是一个数据查询数据源id
				Long dataQueryDatasourceId = Long.parseLong(subKey);
				BigDatasource bigDatasourceByDataQueryDatasourceId = getBigDatasourceByDataQueryDatasourceId(dataQueryDatasourceId);
				return bigDatasourceByDataQueryDatasourceId;
			}
		}
		// 这里的key就是数据源id
		String key = routingKey.toStringKey();
		Long dataQueryDatasourceId = Long.parseLong(key);
		BigDatasource bigDatasourceByDataQueryDatasourceId = getBigDatasourceByDataQueryDatasourceId(dataQueryDatasourceId);
		if (bigDatasourceByDataQueryDatasourceId != null) {
			return bigDatasourceByDataQueryDatasourceId;
		}
		return null;
	}
	private BigDatasource getBigDatasourceByDataQueryDatasourceId(Long dataQueryDatasourceId){
		DataQueryDatasourceDO byId = iDataQueryDatasourceService.getById(dataQueryDatasourceId);
		Map<DynamicBigDatasourceRoutingKey, BigDatasource> dynamicBigDatasourceRoutingKeyBigDatasourceMap = dataQueryDatasourceDynamicBigDatasourceProvider.loadDataSources(Lists.newArrayList(byId));
		if (dynamicBigDatasourceRoutingKeyBigDatasourceMap != null && !dynamicBigDatasourceRoutingKeyBigDatasourceMap.isEmpty()) {
			// 如果不为空，肯定是加载了一个数据源
			BigDatasource bigDatasource = dynamicBigDatasourceRoutingKeyBigDatasourceMap.entrySet().iterator().next().getValue();
			log.info("dataquery routing fallback added a bigDatasource name is {} type is {}",bigDatasource.getName(),bigDatasource.getType().name());
			return bigDatasource;
		}
		return null;
	}
}
