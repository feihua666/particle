package com.particle.global.big.datasource.bigdatasource.impl.neo4j.config;

import com.particle.global.big.datasource.bigdatasource.config.BigDatasourceAccountConfig;
import lombok.Data;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataProperties;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;

import java.net.URI;

/**
 * <p>
 * neo4j大数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-11-21 13:49:39
 */
@Data
public class Neo4jBigDatasourceConfig extends BigDatasourceAccountConfig {

	/**
	 * 基于spring neo4j 配置，主要配置连接信息
	 */
	private Neo4jProperties neo4jProperties;

	/**
	 * 基于spring data neo4j 配置，主要配置使用哪个数据库
	 */
	private Neo4jDataProperties neo4jDataProperties;


	public static Neo4jBigDatasourceConfig create(Neo4jProperties neo4jProperties, Neo4jDataProperties neo4jDataProperties) {
		Neo4jBigDatasourceConfig neo4jBigDatasourceConfig = new Neo4jBigDatasourceConfig();
		neo4jBigDatasourceConfig.setNeo4jProperties(neo4jProperties);
		neo4jBigDatasourceConfig.setNeo4jDataProperties(neo4jDataProperties);

		Neo4jProperties.Authentication authentication = neo4jProperties.getAuthentication();
		String username = authentication.getUsername();
		String password = authentication.getPassword();

		neo4jBigDatasourceConfig.setUsername(username);
		neo4jBigDatasourceConfig.setPassword(password);

		return neo4jBigDatasourceConfig;
	}

	/**
	 * 简单方式创建
	 * @param uri 如：bolt://localhost:7687
	 * @return
	 */
	public static Neo4jBigDatasourceConfig create(String uri,String username,String password,String database) {
		Neo4jProperties neo4jProperties = new Neo4jProperties();
		neo4jProperties.setUri(URI.create(uri));
		Neo4jProperties.Authentication authentication = neo4jProperties.getAuthentication();
		authentication.setUsername(username);
		authentication.setPassword(password);

		Neo4jDataProperties neo4jDataProperties = new Neo4jDataProperties();
		neo4jDataProperties.setDatabase(database);

		return create(neo4jProperties, neo4jDataProperties);

	}

	/**
	 * 简单方式创建不指定使用哪个数据库
	 * @param uri
	 * @param username
	 * @param password
	 * @return
	 */
	public static Neo4jBigDatasourceConfig create(String uri,String username,String password) {
		return create(uri, username, password, null);
	}
}
