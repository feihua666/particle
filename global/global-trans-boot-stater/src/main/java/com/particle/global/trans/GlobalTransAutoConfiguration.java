package com.particle.global.trans;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.concurrency.threadpool.CustomExecutors;
import com.particle.global.mybatis.plus.mapper.NativeSqlMapper;
import com.particle.global.tool.condition.ConditionalOnClassTool;
import com.particle.global.tool.str.StringTool;
import com.particle.global.trans.api.DataObtainForTableNameTrans;
import com.particle.global.trans.api.TableNameResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 全局翻译自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 10:35
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ComponentScan
public class GlobalTransAutoConfiguration {

	public static final String transTaskExecutor = "transTaskExecutor";

	/**
	 * 默认表名实现
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = "particle.trans")
	public TableNameResolver defaultTableNameResolver() {
		return new TableNameResolver() {
			private Map<String,String> table;
			@Override
			public String resolve(Class tableClass,String tableName) {
				if (StrUtil.isNotEmpty(tableName)) {

					ConditionalOnClassTool.ConditionalOnClassResult conditionalOnClassResult = ConditionalOnClassTool.checkResult(tableName);

					String tn = conditionalOnClassResult.getExpression();
					if (table != null) {
						//
						tn = table.get(tn);
						if (!StrUtil.isEmpty(tn)) {
							conditionalOnClassResult = ConditionalOnClassTool.checkResult(tn);
						}
					}
					if (conditionalOnClassResult.getHasCondition()) {
						if (conditionalOnClassResult.getClassPresent()) {
							return conditionalOnClassResult.getExpression();
						}else {
							return null;
						}
					}else {
						return conditionalOnClassResult.getExpression();
					}
				}
				if (tableClass == Void.class) {
					return null;
				}
				TableName annotation = AnnotationUtil.getAnnotation(tableClass, TableName.class);
				if (annotation != null) {
					return annotation.value();
				}
				String simpleName = tableClass.getSimpleName();
				return StringTool.humpToLine(simpleName);
			}

			public Map<String, String> getTable() {
				return table;
			}

			public void setTable(Map<String, String> table) {
				this.table = table;
			}
		};
	}

	/**
	 * 默认获取数据实现
	 * @param nativeSqlMapper
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public DataObtainForTableNameTrans defaultDataObtainForTableNameTrans(NativeSqlMapper nativeSqlMapper) {
		return new DataObtainForTableNameTrans() {
			@Override
			public List<Map<String, Object>> dataObtain(String tableName, String selectColumn, String whereColumn, Collection<Object> keys) {
				List<Map<String, Object>> list = Collections.EMPTY_LIST;
				try {
					list = nativeSqlMapper.selectListByMyWrapper(tableName, Wrappers.query().select(selectColumn, whereColumn).in(whereColumn, keys));
				}catch (Exception e){
					log.error("翻译获取数据异常",e);
				}
				return list;
			}
		};
	}

	/**
	 * 翻译线程池
	 * @param beanFactory
	 * @return
	 */
	@Bean(name = transTaskExecutor, destroyMethod = "shutdown")
	public ExecutorService transTaskExecutor(BeanFactory beanFactory) {
		return CustomExecutors.newExecutorService(beanFactory,
				transTaskExecutor,
				10,
				20,
				1000 * 60,
				new LinkedBlockingQueue<>(100),
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				false);
	}
}
