package com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.config;

import com.particle.global.big.datasource.bigdatasource.config.BigDatasourceAccountConfig;
import lombok.Data;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.ssl.SslBundles;

import java.util.List;

/**
 * <p>
 * elasticsearch 大数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-12-08 13:31:42
 */
@Data
public class ElasticsearchBigDatasourceConfig extends BigDatasourceAccountConfig {

	/**
	 * 基于spring elasticsearch 配置，主要配置连接信息
	 */
	private ElasticsearchProperties elasticsearchProperties;
	/**
	 * ssl配置，这个是必填的，不能为null，如果为null，则请不根配置ssl信息
	 */
	private SslBundles sslBundles;


	public static ElasticsearchBigDatasourceConfig create(ElasticsearchProperties elasticsearchProperties,SslBundles sslBundles) {
		ElasticsearchBigDatasourceConfig elasticsearchBigDatasourceConfig = new ElasticsearchBigDatasourceConfig();
		elasticsearchBigDatasourceConfig.setElasticsearchProperties(elasticsearchProperties);
		elasticsearchBigDatasourceConfig.setSslBundles(sslBundles);

		String username = elasticsearchProperties.getUsername();
		String password = elasticsearchProperties.getPassword();

		elasticsearchBigDatasourceConfig.setUsername(username);
		elasticsearchBigDatasourceConfig.setPassword(password);

		return elasticsearchBigDatasourceConfig;
	}

	/**
	 * 简单方式创建
	 * @param uris 如：http://localhost:9200
	 * @return
	 */
	public static ElasticsearchBigDatasourceConfig create(List<String> uris, String username, String password) {
		ElasticsearchProperties elasticsearchProperties = new ElasticsearchProperties();
		elasticsearchProperties.setUris(uris);

		elasticsearchProperties.setUsername(username);
		elasticsearchProperties.setPassword(password);

		return create(elasticsearchProperties,null);

	}
}
