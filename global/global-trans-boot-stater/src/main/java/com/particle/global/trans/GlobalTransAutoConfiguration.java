package com.particle.global.trans;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.concurrency.threadpool.CustomExecutors;
import com.particle.global.mybatis.plus.mapper.NativeSqlMapper;
import com.particle.global.tool.str.StringTool;
import com.particle.global.trans.api.DataObtainForTableNameTrans;
import com.particle.global.trans.api.TableNameResolver;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
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
@Configuration
@ComponentScan
public class GlobalTransAutoConfiguration {


	/**
	 * 默认表名实现
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public TableNameResolver defaultTableNameResolver() {
		return new TableNameResolver() {
			@Override
			public String resolve(Class tableClass) {
				TableName annotation = AnnotationUtil.getAnnotation(tableClass, TableName.class);
				if (annotation != null) {
					return annotation.value();
				}
				String simpleName = tableClass.getSimpleName();
				return StringTool.humpToLine(simpleName);
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
				List<Map<String, Object>> list = nativeSqlMapper.selectListByMyWrapper(tableName, Wrappers.query().select(selectColumn, whereColumn).in(whereColumn, keys));
				return list;
			}
		};
	}




	/**
	 * 翻译线程池
	 * @param beanFactory
	 * @return
	 */
	@Bean(name = "transTaskExecutor", destroyMethod = "shutdown")
	public ExecutorService transTaskExecutor(BeanFactory beanFactory) {
		if (ClassLoaderUtil.isPresent("io.micrometer.core.instrument.MeterRegistry")) {
			return CustomExecutors.newExecutorService(beanFactory,
					"transTaskExecutor",
					1,
					100,
					100,
					new LinkedBlockingQueue<>(100),
					// 如果拒绝自己执行
					new ThreadPoolExecutor.CallerRunsPolicy(),
					true,beanFactory.getBean(MeterRegistry.class));
		}
		return CustomExecutors.newExecutorService(beanFactory,
				"transTaskExecutor",
				1,
				100,
				100,
				new LinkedBlockingQueue<>(100),
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				true,null);
	}
}
