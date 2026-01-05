package com.particle.global.mybatis.plus.crud;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.TemporalAccessorUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.particle.global.light.share.constant.ClassAdapterConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.logging.log4j.core.pattern.NameAbbreviator;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import static com.baomidou.mybatisplus.core.toolkit.PluginUtils.realTarget;

/**
 * <p>
 * 监控dao层qps
 * </p>
 *
 * @author yangwei
 * @since 2021-08-31 10:33
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class})
})
@Slf4j
public class MetricsAndSlowSqlMybatisInterceptor implements Interceptor {

	private static final String slowSqlNotifyThresholdValueKey = "particle.notify.slowSql.threshold";
    // 参数同 log4j2 配置 pattern中 %c{1.}
    private static final NameAbbreviator abbreviator = NameAbbreviator.getAbbreviator("1.");

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

            // 1. 获取被拦截的 StatementHandler 对象（注意：可能是代理对象，需先解包）
            StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());

            // 2. 通过 MetaObject 反射获取 StatementHandler 的内部属性（MyBatis 提供的安全反射工具）
            MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

            // 3. 获取 MappedStatement（核心：封装 SQL、参数映射、Mapper 信息）
            MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
            // 获取 Mapper 方法名（如：com.xxx.mapper.UserMapper.selectById）
            String mapperMethod = mappedStatement.getId();

            // 4. 获取 BoundSql（封装最终执行的 SQL 语句 + 参数映射）
            BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
            String sql = boundSql.getSql(); // 最终执行的 SQL（已解析动态 SQL）

            // 5. 核心：直接从 StatementHandler 的 delegate 中获取 Configuration
            Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");

            String finalSql = null;

			if (ClassLoaderUtil.isPresent(ClassAdapterConstants.NOTIFY_TOOL_CLASS_NAME)) {

				// 超过阈值通知
				if (duration > slowSqlNotifyThreshold) {
					if (finalSql == null) {
						finalSql = finalSql(configuration, boundSql, mapperMethod);
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
				String commandType = mappedStatement.getSqlCommandType().name();
				// sql监控
				com.particle.global.actuator.monitor.MonitorTool.timer(
						"mybatis.interceptor.request",
						end - start,
						"dao层监控",
						"executorMethod",invocation.getMethod().getName(),
						"mapperMethod",mappedStatement.getId(),
						"commandType",commandType

				);
			}


			if (logSqlEnable) {
				if (finalSql == null) {
					finalSql = finalSql(configuration, boundSql, mapperMethod);
				}
				log.info("duration={}ms,sql={}",duration,finalSql);
			}

		}

	}

	/**
	 * 获取最终sql
	 * @param configuration
	 * @param boundSql
	 * @param sqlId
	 * @return
	 */
	private static String finalSql(Configuration configuration, BoundSql boundSql,
								 String sqlId) {
		String sql = showSql(configuration, boundSql);
		StringBuilder str = new StringBuilder(100);
		str.append(formatSqlId(sqlId));
		str.append(": ");
		str.append(sql);
		return str.toString();
	}

    /**
     * 获取sqlId
     * @param sqlId
     * @return
     */
    private static String formatSqlId(String sqlId) {
        String packageName = sqlId.substring(0, sqlId.lastIndexOf("."));
        String methodName = sqlId.substring(sqlId.lastIndexOf(".") + 1);
        StringBuilder sb = new StringBuilder();
        abbreviator.abbreviate(packageName, sb);
        sb.append(".").append(methodName);
        return sb.toString();
	}
    /**
     * 获取参数值
     * @param obj
     * @return
     */
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

    /**
     * 获取sql
     * @param configuration
     * @param boundSql
     * @return
     */
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
                        //打印出缺失，提醒该参数缺失并防止错位
						sql = sql.replaceFirst("\\?", "缺失");
					}
				}
			}
		}
		return sql;
	}
}
