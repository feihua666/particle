package com.particle.dataquery.infrastructure.datasource.bigdatasource;

import cn.hutool.core.map.MapUtil;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceJdbcConfig;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceService;
import com.particle.dataquery.infrastructure.datasource.structmapping.DataQueryDatasourceInfrastructureStructMapping;
import com.particle.dataquery.infrastructure.provider.dos.DataQueryProviderDO;
import com.particle.dataquery.infrastructure.provider.service.IDataQueryProviderService;
import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKeyFactory;
import com.particle.global.big.datasource.bigdatasource.dynamic.provider.AbstractDynamicBigDatasourceProvider;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.JdbcBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.config.JdbcBigDatasourceConfig;
import com.particle.global.domain.ApplicationContextHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据查询服务为大数据源提供数据源配置支持
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 22:35
 */
@Slf4j
@DependsOn(ApplicationContextHelper.applicationContextHelperName)
@Component
public class DataQueryDatasourceDynamicBigDatasourceProvider extends AbstractDynamicBigDatasourceProvider {

	public static final String dataQueryGlobalBigDatasourceRoutingKey = "dataQueryGlobalBigDatasource";

	private IDataQueryDatasourceService iDataQueryDatasourceService;
	private IDataQueryProviderService iDataQueryProviderService;
	private DataQueryDictGateway dataQueryDictGateway;

	@Override
	public Map<DynamicBigDatasourceRoutingKey, BigDatasource> doLoadDataSources() {
		List<DataQueryDatasourceDO> list = iDataQueryDatasourceService.list();
		if (list.isEmpty()) {
			return MapUtil.empty();
		}

		// 已经禁用的供应商下面的数据源都不加载
		List<Long> providerIds = list.stream().map(DataQueryDatasourceDO::getDataQueryProviderId).distinct().collect(Collectors.toList());
		List<DataQueryProviderDO> dataQueryProviderDOS = iDataQueryProviderService.listByIds(providerIds);
		Map<Long, DataQueryProviderDO> providerIdMap = dataQueryProviderDOS.stream().collect(Collectors.toMap(DataQueryProviderDO::getId, Function.identity()));

		List<DataQueryDatasource> dataQueryDatasources = list.stream().filter(item->!providerIdMap.get(item.getDataQueryProviderId()).getIsDisabled()).map(item -> DataQueryDatasourceInfrastructureStructMapping.instance.dataQueryDatasourceDOToDataQueryDatasource(DataQueryDatasource.create(), item)).collect(Collectors.toList());

		Map<DynamicBigDatasourceRoutingKey, BigDatasource> map = new HashMap<>();

		List<Long> typeDictIds = list.stream().map(DataQueryDatasourceDO::getTypeDictId).distinct().collect(Collectors.toList());
		Map<Long, String> mapDictValueByIds = dataQueryDictGateway.getMapDictValueByIds(typeDictIds);


		// 根据数据源类型分组
		Map<Long, List<DataQueryDatasource>> queryDatasourceDOMap = dataQueryDatasources.stream().collect(Collectors.groupingBy(DataQueryDatasource::getTypeDictId));

		for (Map.Entry<Long, List<DataQueryDatasource>> entry : queryDatasourceDOMap.entrySet()) {
			DataQueryDatasourceType dataQueryDatasourceType = DataQueryDatasourceType.valueOf(mapDictValueByIds.get(entry.getKey()));
			// jdbc数据源
			if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_jdbc) {
				// jdbc数据源统一使用一个
				JdbcBigDatasource jdbcBigDatasource = JdbcBigDatasource.create(dataQueryGlobalBigDatasourceRoutingKey, BigDatasourceType.datasource_jdbc);
				for (DataQueryDatasource dataQueryDatasource : entry.getValue()) {
					DataQueryDatasourceJdbcConfig dataQueryDatasourceJdbcConfig = dataQueryDatasource.jdbcConfig();
					// 以 id 做为路由key
					jdbcBigDatasource.addDataSourceByJdbcBigDatasourceConfig(
							dataQueryDatasource.getId().toString(),

							JdbcBigDatasourceConfig.create(dataQueryDatasourceJdbcConfig.getDriverClassName(),
									dataQueryDatasourceJdbcConfig.getUrl(),
									dataQueryDatasourceJdbcConfig.getUsername(),
									dataQueryDatasourceJdbcConfig.getPassword())
					);
					map.put(DynamicBigDatasourceRoutingKeyFactory.of(dataQueryGlobalBigDatasourceRoutingKey), jdbcBigDatasource);
				}

			}// end jdbc 数据源
			else {
				log.warn("datasource type for {} ignored. that not support now",dataQueryDatasourceType.itemValue());
			}
		}


		return map;
	}

	@Autowired
	public void setiDataQueryDatasourceService(IDataQueryDatasourceService iDataQueryDatasourceService) {
		this.iDataQueryDatasourceService = iDataQueryDatasourceService;
	}
	@Autowired
	public void setiDataQueryProviderService(IDataQueryProviderService iDataQueryProviderService) {
		this.iDataQueryProviderService = iDataQueryProviderService;
	}

	@Autowired
	public void setDataQueryDictGateway(DataQueryDictGateway dataQueryDictGateway) {
		this.dataQueryDictGateway = dataQueryDictGateway;
	}
}
