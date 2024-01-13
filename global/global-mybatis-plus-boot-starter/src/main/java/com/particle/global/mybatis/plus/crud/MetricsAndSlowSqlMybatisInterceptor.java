package com.particle.global.mybatis.plus.crud;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.TemporalAccessorUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.light.share.constant.ClassAdapterConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

/**
 * <p>
 * 监控dao层qps
 * </p>
 *
 * @author yangwei
 * @since 2021-08-31 10:33
 */
@Intercepts(
		{
				@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
				@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
				@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
		}
)
@Slf4j
public class MetricsAndSlowSqlMybatisInterceptor implements Interceptor {

	private static final String slowSqlNotifyThresholdValueKey = "particle.notify.slowSql.threshold";

	/**
	 * 慢sql通知阈值
	 */
	@Value("${" + slowSqlNotifyThresholdValueKey + ":1000}")
	private long slowSqlNotifyThreshold = 1000;
	/**
	 * 打印完整sql
	 */
	@Value("${particle.logSqlEnable:true}")
	private boolean logSqlEnable = true;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
		try{
			return invocation.proceed();
		}finally {
			long end = System.currentTimeMillis();
			long duration = end - start;
			Object target = invocation.getTarget();
			Object[] args = invocation.getArgs();
			MappedStatement ms = (MappedStatement) args[0];
			Object parameter = args[1];

			String finalSql = null;

			if (ClassLoaderUtil.isPresent(ClassAdapterConstants.NOTIFY_TOOL_CLASS_NAME)) {

				// 超过阈值通知
				if (duration > slowSqlNotifyThreshold) {
					if (finalSql == null) {
						finalSql = finalSql(ms, parameter);
					}
					com.particle.global.notification.notify.NotifyParam notifyParam = com.particle.global.notification.notify.NotifyParam.system()
								.setTitle("慢sql")
								.setContentType("mybatis.interceptor.slowSql")
								.setSuggest("您可以修改配置 " + slowSqlNotifyThresholdValueKey + " 来改变阈值")
								.setContent(StrUtil.format("sql执行时间{}ms,超过阈值{}ms，sql={}", duration, slowSqlNotifyThreshold, finalSql));
					com.particle.global.notification.notify.NotifyTool.notify(notifyParam);
					}

			}
			if (ClassLoaderUtil.isPresent(ClassAdapterConstants.MONITOR_TOOL_TOOL_CLASS_NAME)) {
				String commandType = ms.getSqlCommandType().name();
				// sql监控
				com.particle.global.actuator.monitor.MonitorTool.timer(
						"mybatis.interceptor.request",
						end - start,
						"dao层监控",
						"executorMethod",invocation.getMethod().getName(),
						"mapperMethod",ms.getId(),
						"commandType",commandType

				);
			}


			if (logSqlEnable) {
				if (finalSql == null) {
					finalSql = finalSql(ms, parameter);
				}
				log.info("sql={}",finalSql);
			}
			log.debug("mapperMethod={},duration={}ms",ms.getId(),duration);

		}

	}

	/**
	 * 获取最终sql
	 * @param ms
	 * @return
	 */
	private String finalSql(MappedStatement ms,Object parameter){
		return getSql(ms.getConfiguration(), ms.getBoundSql(parameter),ms.getId() );
	}


	private static String getSql(Configuration configuration, BoundSql boundSql,
								 String sqlId) {
		String sql = showSql(configuration, boundSql);
		StringBuilder str = new StringBuilder(100);
		str.append(sqlId);
		str.append(":");
		str.append(sql);
		return str.toString();
	}

	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			value = "'" + DateUtil.format(((Date) obj), DatePattern.NORM_DATETIME_PATTERN) + "'";
		} else if (obj instanceof LocalDateTime) {
			value = "'" + LocalDateTimeUtil.format(((LocalDateTime) obj), DatePattern.NORM_DATETIME_PATTERN) + "'";
		} else if (obj instanceof LocalDate) {
			value = "'" + LocalDateTimeUtil.format(((LocalDate) obj), DatePattern.NORM_DATE_PATTERN) + "'";
		} else if (obj instanceof LocalTime) {
			value = "'" + TemporalAccessorUtil.format(((LocalTime) obj), DatePattern.NORM_TIME_PATTERN) + "'";
		}else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}

	private static String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (!parameterMappings.isEmpty() && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?",
						Matcher.quoteReplacement(getParameterValue(parameterObject)));

			} else {
				MetaObject metaObject = configuration
						.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql
								.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else {
						sql = sql.replaceFirst("\\?", "缺失");
					}//打印出缺失，提醒该参数缺失并防止错位
				}
			}
		}
		return sql;
	}
}
