package com.particle.dataquery.infrastructure.datasource.bigdatasource;

import cn.hutool.core.map.MapUtil;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceEsConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceHttpConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceNeo4jConfig;
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
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.ElasticsearchBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.config.ElasticsearchBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.HttpBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.http.config.HttpBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceAuthScriptType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.JdbcBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.config.JdbcBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.Neo4jBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.config.Neo4jBigDatasourceConfig;
import com.particle.global.domain.ApplicationContextHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

	private static final String enableConfigKey = "particle.dataquery.dynamic-big-datasource.init.enable";
	private IDataQueryDatasourceService iDataQueryDatasourceService;
	private IDataQueryProviderService iDataQueryProviderService;
	private DataQueryDictGateway dataQueryDictGateway;

	@Value("${"+ enableConfigKey +":false}")
	private Boolean enable = false;

	@Autowired(required = false)
	private INeo4jBigDatasourceLoader iNeo4jBigDatasourceLoader;

	/**
	 * 定义一个记录器，记录已经加载的数据源
	 * key=数据源id，value=是否已加载
	 */
	private static Map<String, Boolean> loadedBigDatasourceMap = new ConcurrentHashMap<>();

	/**
	 * 是否已加载jdbc大数据源，这里单独记录一下，因为jdbc大数据源比较特殊，是一个多数据源
	 */
	private static boolean hasLoadJdbcBigDatasource = false;

	/**
	 * jdbc大数据源实例
	 */
	private static JdbcBigDatasource jdbcBigDatasource;


	@Override
	public Map<DynamicBigDatasourceRoutingKey, BigDatasource> doLoadDataSources() {

		// 可以通过配置文件控制是否开启，默认不开启
		// 因为如果配置了数据源，换了其它环境由于网络不通等问题导致项目启动不了
		if (enable == null || enable == false) {
			log.info("dataquery datasource provider has bean disabled default. to enable use {}=true",enableConfigKey);
			return MapUtil.empty();
		}

		List<DataQueryDatasourceDO> list = iDataQueryDatasourceService.list();
		if (list.isEmpty()) {
			return MapUtil.empty();
		}
		return loadDataSources(list);
	}

	/**
	 * 根据名单加载
	 * @param list
	 * @return
	 */
	public synchronized Map<DynamicBigDatasourceRoutingKey, BigDatasource> loadDataSources(List<DataQueryDatasourceDO> list) {

		// 已经禁用的供应商下面的数据源都不加载
		List<Long> providerIds = list.stream().map(DataQueryDatasourceDO::getDataQueryProviderId).distinct().collect(Collectors.toList());
		List<DataQueryProviderDO> dataQueryProviderDOS = iDataQueryProviderService.listByIds(providerIds);
		Map<Long, DataQueryProviderDO> providerIdMap = dataQueryProviderDOS.stream().collect(Collectors.toMap(DataQueryProviderDO::getId, Function.identity()));

		List<DataQueryDatasource> dataQueryDatasources = list.stream()
				.filter(item->{
					DataQueryProviderDO dataQueryProviderDO = providerIdMap.get(item.getDataQueryProviderId());
					if (dataQueryProviderDO.getIsDisabled()) {
						log.warn("数据源名称 {} 对应的供应商 {} 已禁用，已过滤",item.getName(),dataQueryProviderDO.getName());
					}
					return !dataQueryProviderDO.getIsDisabled();
				})
				.map(item -> DataQueryDatasourceInfrastructureStructMapping.instance.dataQueryDatasourceDOToDataQueryDatasource(DataQueryDatasource.create(), item))
				.collect(Collectors.toList());

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
				if (jdbcBigDatasource == null) {
					jdbcBigDatasource = JdbcBigDatasource.create(dataQueryGlobalBigDatasourceRoutingKey, BigDatasourceType.datasource_jdbc);
				}
				for (DataQueryDatasource dataQueryDatasource : entry.getValue()) {
					String dataSourceKey = dataQueryDatasource.getId().getId().toString();
					// 如果已经存在，不再创建，防止并发问题导致多数据源
					if (loadedBigDatasourceMap.containsKey(dataSourceKey)) {
						continue;
					}

					DataQueryDatasourceJdbcConfig dataQueryDatasourceJdbcConfig = dataQueryDatasource.jdbcConfig();

					// 以 id 做为路由key
					jdbcBigDatasource.addDataSourceByJdbcBigDatasourceConfig(
							// String dataSourceName,注意这里使用id
							dataSourceKey,

							JdbcBigDatasourceConfig.create(dataQueryDatasourceJdbcConfig.getDriverClassName(),
									dataQueryDatasourceJdbcConfig.getUrl(),
									dataQueryDatasourceJdbcConfig.getUsername(),
									dataQueryDatasourceJdbcConfig.getPassword())
					);
					loadedBigDatasourceMap.put(dataSourceKey, true);

				}
				if (!hasLoadJdbcBigDatasource) {
					map.put(DynamicBigDatasourceRoutingKeyFactory.of(dataQueryGlobalBigDatasourceRoutingKey), jdbcBigDatasource);
					hasLoadJdbcBigDatasource = !hasLoadJdbcBigDatasource;
				}

			}// end jdbc 数据源
			else if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_http) {
				for (DataQueryDatasource dataQueryDatasource : entry.getValue()) {
					String dataSourceKey = dataQueryDatasource.getId().getId().toString();
					// 如果已经存在，不再创建，防止并发问题导致多数据源
					if (loadedBigDatasourceMap.containsKey(dataSourceKey)) {
						continue;
					}
					DataQueryDatasourceHttpConfig config = dataQueryDatasource.httpConfig();
					HttpBigDatasourceConfig httpBigDatasourceConfig = HttpBigDatasourceConfig.create(config.getDomainUrl(),
							config.getAuthScriptType() == null ? null : HttpBigDatasourceAuthScriptType.valueOf(config.getAuthScriptType().itemValue()),
							config.getAuthScriptTemplate(),
							config.getUsername(),
							config.getPassword(),
							config.getProxyConfig());
					HttpBigDatasource httpBigDatasource = HttpBigDatasource.create(dataQueryDatasource.getName(),
							BigDatasourceType.datasource_http,httpBigDatasourceConfig);

					map.put(DynamicBigDatasourceRoutingKeyFactory.of(dataSourceKey), httpBigDatasource);
					loadedBigDatasourceMap.put(dataSourceKey, true);

				}
			}// end http 数据源
			else if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_neo4j) {
				for (DataQueryDatasource dataQueryDatasource : entry.getValue()) {
					String dataSourceKey = dataQueryDatasource.getId().getId().toString();
					// 如果已经存在，不再创建，防止并发问题导致多数据源
					if (loadedBigDatasourceMap.containsKey(dataSourceKey)) {
						continue;
					}
					DataQueryDatasourceNeo4jConfig config = dataQueryDatasource.neo4jConfig();
					Neo4jBigDatasourceConfig neo4jBigDatasourceConfig = Neo4jBigDatasourceConfig.create(
							config.getUri(),
							config.getUsername(),
							config.getPassword(),
							null);
					Neo4jBigDatasource neo4jBigDatasource = null;
					if (iNeo4jBigDatasourceLoader != null) {
						log.debug("use neo4jBigDatasourceLoader load neo4jBigDatasource");
						neo4jBigDatasource = iNeo4jBigDatasourceLoader.load(dataQueryDatasource.getName(),
								BigDatasourceType.datasource_neo4j,
								neo4jBigDatasourceConfig);
					}else {
						log.debug("use default neo4jBigDatasource");
						neo4jBigDatasource = Neo4jBigDatasource.createByNeo4jBigDatasourceConfig(
								dataQueryDatasource.getName(),
								BigDatasourceType.datasource_neo4j,
								neo4jBigDatasourceConfig);
					}

					map.put(DynamicBigDatasourceRoutingKeyFactory.of(dataSourceKey), neo4jBigDatasource);
					loadedBigDatasourceMap.put(dataSourceKey, true);

				}
			}// end neo4j 数据源
			else if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_es) {
				for (DataQueryDatasource dataQueryDatasource : entry.getValue()) {
					String dataSourceKey = dataQueryDatasource.getId().getId().toString();
					// 如果已经存在，不再创建，防止并发问题导致多数据源
					if (loadedBigDatasourceMap.containsKey(dataSourceKey)) {
						continue;
					}
					DataQueryDatasourceEsConfig config = dataQueryDatasource.esConfig();
					ElasticsearchBigDatasourceConfig elasticsearchBigDatasourceConfig = ElasticsearchBigDatasourceConfig.create(
							Arrays.stream(config.getUris().split(",")).collect(Collectors.toList()),
							config.getUsername(),
							config.getPassword());
					ElasticsearchBigDatasource elasticsearchBigDatasource = ElasticsearchBigDatasource.createByElasticsearchBigDatasourceConfig(
							dataQueryDatasource.getName(),
							BigDatasourceType.datasource_es,
							elasticsearchBigDatasourceConfig);

					map.put(DynamicBigDatasourceRoutingKeyFactory.of(dataSourceKey), elasticsearchBigDatasource);
					loadedBigDatasourceMap.put(dataSourceKey, true);

				}
			}// end es 数据源
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
