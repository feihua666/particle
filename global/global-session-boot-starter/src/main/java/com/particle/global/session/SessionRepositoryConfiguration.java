package com.particle.global.session;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.dialect.DialectName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.session.JdbcSessionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.SessionRepositoryCustomizer;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.jdbc.*;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * <p>
 * 默认简单map方式内存存储
 * </p>
 *
 * @author yangwei
 * @since 2022-07-29 15:27
 */
@Configuration
@Slf4j
public class SessionRepositoryConfiguration {

	/**
	 * 内存存储需要添加 EnableSpringHttpSession 注解，因为springboot autoConfigure中默认没有支持内存存储
	 */
	@Configuration
	@EnableSpringHttpSession
	@ConditionalOnProperty(prefix = "spring.session",name = "store-type",havingValue = "none",matchIfMissing = true)
	@ConditionalOnMissingBean(SessionRepository.class)
	@ConditionalOnClass(EnableSpringHttpSession.class)
	static class SessionRepositoryMapConfiguration{
		public SessionRepositoryMapConfiguration() {
			log.debug("SessionRepositoryMapConfiguration enabled");
		}

		/**
		 * 默认简单map方式内存存储
		 * @return
		 */
		//@ConditionalOnMissingBean(SessionRepository.class)
		@Bean
		public MapSessionRepository sessionRepository() {
			return new MapSessionRepository(new ConcurrentHashMap<>());
		}
	}
	/**
	 * 使用 redis存储session时生效
	 * 无需使用 EnableRedisHttpSession 注解，因为springboot全局自动配置中会自动检测
	 */
	@Configuration
	//@EnableRedisHttpSession
	@ConditionalOnProperty(prefix = "spring.session",name = "store-type",havingValue = "redis")
	@ConditionalOnClass(EnableRedisHttpSession.class)
	static class SessionRepositoryRedisConfiguration{
		public SessionRepositoryRedisConfiguration() {
			log.debug("SessionRepositoryRedisConfiguration enabled");
		}
	}
	/**
	 * 使用 jdbc存储session时生效
	 */
	@Configuration
	//@EnableJdbcHttpSession
	@ConditionalOnProperty(prefix = "spring.session",name = "store-type",havingValue = "jdbc")
	@ConditionalOnClass(EnableJdbcHttpSession.class)
	static class SessionRepositoryJdbcConfiguration{
		public SessionRepositoryJdbcConfiguration() {
			log.debug("SessionRepositoryJdbcConfiguration enabled");
		}

		@Bean
		public JdbcSessionRepositoryCustomizer jdbcSessionReposityCustomizer(JdbcSessionProperties jdbcSessionProperties,GlobalSessionProperties globalSessionProperties) {
			return new JdbcSessionRepositoryCustomizer(jdbcSessionProperties, globalSessionProperties);
		}
	}

	/**
	 * 自定义sql
	 */
	public static class JdbcSessionRepositoryCustomizer implements SessionRepositoryCustomizer<JdbcIndexedSessionRepository> {

		/**
		 * 使用配置中没有配置表名，默认使用该表名
		 */
		private static final String defaultTableName = "global_spring_session";
		/**
		 * 一共有两个表，另一个表是以指定表名为后缀拼接成一表名
		 */
		private static final String attributes_suffix = "_ATTRIBUTES";
		/**
		 * 指定的表名，如果没有指定使用默认的 {@link JdbcSessionRepositoryCustomizer#defaultTableName}
		 */
		private String specifiedTableName;
		/**
		 * 是否将表名转为小写
		 */
		private Boolean lowerCaseTableNameSuffix_attributes = true;
		/**
		 * 将所有sql转为小写
		 */
		private Boolean lowerCaseAllSql = true;

		private String jdbcType;

		public JdbcSessionRepositoryCustomizer(JdbcSessionProperties jdbcSessionProperties,GlobalSessionProperties globalSessionProperties) {
			this.specifiedTableName = jdbcSessionProperties.getTableName();
			this.jdbcType = Optional.of(globalSessionProperties.getJdbc()).map(item -> item.getType()).orElse(null);
		}

		@Override
		public void customize(JdbcIndexedSessionRepository sessionRepository) {
			if (StrUtil.isEmpty(jdbcType)) {
				log.warn("JdbcSessionRepositoryCustomizer jdbc type is empty, this may cause a set session attrbute DuplicateKeyException,you can config this by particle.session.jdbc.type");
			}else {
				Supplier<SessionRepositoryCustomizer<JdbcIndexedSessionRepository>> sessionRepositoryCustomizerSupplier = customizerMap.get(jdbcType);
				if (sessionRepositoryCustomizerSupplier == null) {
					throw new RuntimeException("jdbcType=" + jdbcType + " Can not support,the valid value can be " + Arrays.stream(DatabaseDialect.values()).collect(Collectors.toList()));
				}
				sessionRepositoryCustomizerSupplier.get().customize(sessionRepository);
			}

			// 如果没有明确指定，重新设置一下表名
			if (StrUtil.isEmpty(specifiedTableName)) {
				sessionRepository.setTableName(defaultTableName);
				// 处理一下sql
			}

			if (lowerCaseTableNameSuffix_attributes || lowerCaseAllSql) {

				String tableName = (String) ReflectUtil.getFieldValue(sessionRepository, "tableName");
				String attributesTableName = tableName + attributes_suffix;
				String attributesTableNameLowerCase = attributesTableName.toLowerCase();
				String sqlProperties[] = new String[]{
						"createSessionQuery",
						"createSessionAttributeQuery",
						"getSessionQuery",
						"updateSessionQuery",
						"updateSessionAttributeQuery",
						"deleteSessionAttributeQuery",
						"deleteSessionQuery",
						"listSessionsByPrincipalNameQuery",
						"deleteSessionsByExpiryTimeQuery",
				};
				for (String sqlProperty : sqlProperties) {
					String sql = (String)ReflectUtil.getFieldValue(sessionRepository, sqlProperty);
					if (lowerCaseAllSql) {
						ReflectUtil.setFieldValue(sessionRepository,sqlProperty,sql.toLowerCase());
					} else if (lowerCaseTableNameSuffix_attributes) {
						ReflectUtil.setFieldValue(sessionRepository,sqlProperty,sql.replace(attributesTableName,attributesTableNameLowerCase));
					}
				}
				}
		}
	}


	/**
	 * key 支持 枚举：{@link DatabaseDialect}
	 */
	private static Map<String, Supplier< SessionRepositoryCustomizer<JdbcIndexedSessionRepository>>> customizerMap = new HashMap<>();

	static {
		customizerMap.put(DatabaseDialect.mysql.name(), () -> new MySqlJdbcIndexedSessionRepositoryCustomizer());
		customizerMap.put(DatabaseDialect.oracle.name(), () -> new OracleJdbcIndexedSessionRepositoryCustomizer());
		customizerMap.put(DatabaseDialect.postgresql.name(), () -> new PostgreSqlJdbcIndexedSessionRepositoryCustomizer());
		customizerMap.put(DatabaseDialect.db2.name(), () -> new Db2JdbcIndexedSessionRepositoryCustomizer());
	}

	/**
	 * 支持自定义的数据库类型
	 * 参考：{@link DialectName}
	 */
	public static enum DatabaseDialect{
		mysql,
		oracle,
		postgresql,
		db2
	}
}
