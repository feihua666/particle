package com.particle.global.big.datasource.bigdatasource.dynamic;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.dynamic.executor.DelegateBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.dynamic.executor.DelegateBigDatasourceExecutor;
import com.particle.global.big.datasource.bigdatasource.dynamic.impl.DefaultDynamicBigDatasourceRouterImpl;
import com.particle.global.big.datasource.bigdatasource.dynamic.properties.BigDatasourceProperty;
import com.particle.global.big.datasource.bigdatasource.dynamic.properties.DynamicBigDatasourceProperties;
import com.particle.global.big.datasource.bigdatasource.dynamic.provider.DynamicBigDatasourceProvider;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.JdbcBigDatasource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 动态大数据源
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 09:48
 */
@Slf4j
public class DynamicBigDatasource implements BigDatasource , InitializingBean, DisposableBean {

	/**
	 * 大数据源map
	 */
	protected Map<DynamicBigDatasourceRoutingKey, BigDatasource> bigDatasourceMap = new HashMap<>();

	protected List<DynamicBigDatasourceProvider> providers;

	/**
	 * 默认大数据源路由器
	 */
	protected DynamicBigDatasourceRouter dynamicBigDatasourceRouter = new DefaultDynamicBigDatasourceRouterImpl(bigDatasourceMap);


	@Override
	public String getName() {
		return DynamicBigDatasource.class.getName();
	}

	@Override
	public BigDatasourceType getType() {
		return null;
	}


	@Override
	public BigDatasourceApiExecutor getApiExecutor() throws BigDatasourceException {
		DelegateBigDatasourceApiExecutor delegateBigDatasourceApiExecutor = new DelegateBigDatasourceApiExecutor(dynamicBigDatasourceRouter);
		return delegateBigDatasourceApiExecutor;
	}

	@Override
	public BigDatasourceExecutor getExecutor(BigDatasourceApi bigDatasourceApi) throws BigDatasourceException {
		DelegateBigDatasourceExecutor delegateBigDatasourceExecutor = new DelegateBigDatasourceExecutor(dynamicBigDatasourceRouter,bigDatasourceApi);
		return delegateBigDatasourceExecutor;
	}

	/**
	 * 路由大数据源
	 * @return
	 */
	protected BigDatasource routing() {
		return dynamicBigDatasourceRouter.routing(DynamicBigDatasourceRoutingKeyHolder.get());
	}

	/**
	 * 添加大数据源
	 * @param routingKey
	 */
	public void addBigDatasource(DynamicBigDatasourceRoutingKey routingKey, BigDatasource bigDatasource) {
		bigDatasourceMap.put(routingKey, bigDatasource);
	}

	public void addBigDatasource(Map<DynamicBigDatasourceRoutingKey, BigDatasource> map) {
		if (map != null) {
			bigDatasourceMap.putAll(map);
		}
	}

	/**
	 * 添加大数据源
	 * 字符串大数据源路由键支持
	 * @param routingKey
	 * @param bigDatasource
	 */
	public void addBigDatasource(String routingKey, BigDatasource bigDatasource) {
		DynamicBigDatasourceRoutingKey dynamicBigDatasourceRoutingKey = DynamicBigDatasourceRoutingKeyFactory.of(routingKey);
		addBigDatasource(dynamicBigDatasourceRoutingKey, bigDatasource);
	}


		/**
		 * 允许外部设置
		 * @param dynamicBigDatasourceRouter
		 */
	public void setDynamicBigDatasourceRouter(DynamicBigDatasourceRouter dynamicBigDatasourceRouter) {
		this.dynamicBigDatasourceRouter = dynamicBigDatasourceRouter;
	}

	/**
	 * 允许外部获取，但注意不要修改
	 * @return
	 */
	public Map<DynamicBigDatasourceRoutingKey, BigDatasource> getBigDatasourceMap() {
		return bigDatasourceMap;
	}

	public void setProviders(List<DynamicBigDatasourceProvider> providers) {
		this.providers = providers;
	}

	@Override
	public void destroy() throws Exception {
		close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (providers != null) {
			for (DynamicBigDatasourceProvider provider : providers) {
				Map<DynamicBigDatasourceRoutingKey, BigDatasource> m = provider.loadDataSources();
				if (m != null) {
					addBigDatasource(m);
				}
			}
		}
	}

	@Override
	public void close() throws IOException {
		log.info("dynamic-bigdatasource start closing ....");
		for (Map.Entry<DynamicBigDatasourceRoutingKey, BigDatasource> item : bigDatasourceMap.entrySet()) {
			item.getValue().close();

		}
		log.info("dynamic-bigdatasource all closed success,bye");
	}
}
