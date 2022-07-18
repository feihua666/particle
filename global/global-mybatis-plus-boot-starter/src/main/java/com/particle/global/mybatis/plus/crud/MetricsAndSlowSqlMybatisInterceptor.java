package com.particle.global.mybatis.plus.crud;

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

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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


	/**
	 * 慢sql通知阈值
	 */
	@Value("${scatter.notify.slowSql.threshold:1000}")
	private long slowSqlNotifyThreshold;
	/**
	 * 打印完整sql
	 */
	@Value("${scatter.logSqlEnable:true}")
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

			// 超过阈值通知
			//if (duration > slowSqlNotifyThreshold) {
			//	NotifyParam notifyParam = NotifyParam.system()
			//			.setTitle("慢sql")
			//			.setContentType("mybatis.interceptor.slowSql")
			//			.setSuggest("您可以修改配置 scatter.notify.slowSql.threshold 来改变阈值")
			//			.setContent(StrUtil.format("sql执行时间{}ms,超过阈值{}ms，sql={}", duration, slowSqlNotifyThreshold, finalSql(ms,parameter)));
			//	NotifyTool.notify(notifyParam);
			//}
			//
			//String commandType = ms.getSqlCommandType().name();
			//// sql监控
			//MonitorTool.timer(
			//		"mybatis.interceptor.request",
			//		end - start,
			//		"dao层监控",
			//		"executorMethod",invocation.getMethod().getName(),
			//		"mapperMethod",ms.getId(),
			//		"commandType",commandType
			//
			//);
			if (logSqlEnable) {
				log.info("sql={}",finalSql(ms,parameter));
			}
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
			DateFormat formatter = DateFormat.getDateTimeInstance(
					DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
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
