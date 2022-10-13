package com.particle.global.datasource.sqlinit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 内容来自 {@link org.springframework.boot.autoconfigure.sql.init.DataSourceInitializationConfiguration}
 * 主要是配置自定义的schema 支持 import开始的语句
 * 该配置将会导致 {@link org.springframework.boot.autoconfigure.sql.init.DataSourceInitializationConfiguration} 自动配置失效
 * </p>
 *
 * @author yangwei
 * @since 2021-09-17 16:44
 */
@Slf4j
@EnableConfigurationProperties(SqlInitializationProperties.class)
@Configuration(proxyBeanMethods = false)
public class CustomDataSourceInitializationConfiguration {

	@Bean
	SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer(DataSource datasource,
																			   SqlInitializationProperties initializationProperties) {
		log.info("自定义增强sql脚本初始化已生效，这将导 spring 原生 {} 自动配置失效！",
				"org.springframework.boot.autoconfigure.sql.init.DataSourceInitializationConfiguration");

		DatabaseInitializationSettings settings = createFrom(initializationProperties);
		return new CustomDataSourceScriptDatabaseInitializer(determineDataSource(datasource,
				initializationProperties.getUsername(), initializationProperties.getPassword()), settings);
	}

	private static DataSource determineDataSource(DataSource dataSource, String username, String password) {
		if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
			return DataSourceBuilder.derivedFrom(dataSource).username(username).password(password)
					.type(SimpleDriverDataSource.class).build();
		}
		return dataSource;
	}

	public static DatabaseInitializationSettings createFrom(List<String> schemaLocations,SqlInitializationProperties properties) {
		DatabaseInitializationSettings settings = new DatabaseInitializationSettings();
		settings.setSchemaLocations(
				scriptLocations(schemaLocations, "schema", properties.getPlatform()));
		settings.setContinueOnError(properties.isContinueOnError());
		settings.setSeparator(properties.getSeparator());
		settings.setEncoding(properties.getEncoding());
		settings.setMode(properties.getMode());
		return settings;
	}
	/**
	 * 以下拷贝自   org.springframework.boot.autoconfigure.sql.init.SettingsCreator
	 * @param properties
	 * @return
	 */

	static DatabaseInitializationSettings createFrom(SqlInitializationProperties properties) {
		DatabaseInitializationSettings settings = new DatabaseInitializationSettings();
		settings.setSchemaLocations(
				scriptLocations(properties.getSchemaLocations(), "schema", properties.getPlatform()));
		settings.setDataLocations(scriptLocations(properties.getDataLocations(), "data", properties.getPlatform()));
		settings.setContinueOnError(properties.isContinueOnError());
		settings.setSeparator(properties.getSeparator());
		settings.setEncoding(properties.getEncoding());
		settings.setMode(properties.getMode());
		return settings;
	}

	private static List<String> scriptLocations(List<String> locations, String fallback, String platform) {
		if (locations != null) {
			return locations;
		}
		List<String> fallbackLocations = new ArrayList<>();
		fallbackLocations.add("optional:classpath*:" + fallback + "-" + platform + ".sql");
		fallbackLocations.add("optional:classpath*:" + fallback + ".sql");
		return fallbackLocations;
	}
}
