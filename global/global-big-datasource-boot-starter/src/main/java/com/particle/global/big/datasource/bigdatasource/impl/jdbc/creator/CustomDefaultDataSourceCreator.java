package com.particle.global.big.datasource.bigdatasource.impl.jdbc.creator;

import cn.hutool.core.util.ClassLoaderUtil;
import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.creator.atomikos.AtomikosDataSourceCreator;
import com.baomidou.dynamic.datasource.creator.basic.BasicDataSourceCreator;
import com.baomidou.dynamic.datasource.creator.beecp.BeeCpDataSourceCreator;
import com.baomidou.dynamic.datasource.creator.dbcp.Dbcp2DataSourceCreator;
import com.baomidou.dynamic.datasource.creator.druid.DruidDataSourceCreator;
import com.baomidou.dynamic.datasource.creator.hikaricp.HikariDataSourceCreator;
import com.baomidou.dynamic.datasource.creator.jndi.JndiDataSourceCreator;
import com.baomidou.dynamic.datasource.enums.DdConstants;
import com.baomidou.dynamic.datasource.event.DataSourceInitEvent;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceCreatorAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 复制于 {@link DefaultDataSourceCreator}
 * 由于其不满足需求
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 21:36
 */
public class CustomDefaultDataSourceCreator extends DefaultDataSourceCreator{

	private List<DataSourceCreator> creators;
	private DataSourceInitEvent dataSourceInitEvent;

	private DynamicDataSourceProperties dynamicDataSourceProperties;

	public CustomDefaultDataSourceCreator(DynamicDataSourceProperties dynamicDataSourceProperties,DataSourceInitEvent dataSourceInitEvent){
		this.dynamicDataSourceProperties = dynamicDataSourceProperties;
		this.dataSourceInitEvent = dataSourceInitEvent;
		super.setDataSourceInitEvent(dataSourceInitEvent);
		// 初始化默认的数据源创建器
		initDefaultDataSourceCreators();
	}

	public CustomDefaultDataSourceCreator(DynamicDataSourceProperties dynamicDataSourceProperties,DataSourceInitEvent dataSourceInitEvent,List<DataSourceCreator> creators){
		this.dynamicDataSourceProperties = dynamicDataSourceProperties;
		this.dataSourceInitEvent = dataSourceInitEvent;
		super.setDataSourceInitEvent(dataSourceInitEvent);
		setCreators(creators);
	}

	/**
	 * 默认数据源创建器，保持 dynamic datasource的顺序
	 */
	protected void initDefaultDataSourceCreators() {

		Map<Integer, DataSourceCreator> defaultDataSourceCreators = new HashMap<>();
		/**
		 * 与自动配置类保持一致即可 {@link DynamicDataSourceCreatorAutoConfiguration}
		 */
		defaultDataSourceCreators.put(DynamicDataSourceCreatorAutoConfiguration.JNDI_ORDER, new JndiDataSourceCreator());
		if (ClassLoaderUtil.isPresent(DdConstants.DRUID_DATASOURCE)) {
			defaultDataSourceCreators.put(DynamicDataSourceCreatorAutoConfiguration.DRUID_ORDER, new DruidDataSourceCreator(dynamicDataSourceProperties.getDruid(),proxyFilters -> Collections.emptyList()));
		}
		if (ClassLoaderUtil.isPresent(DdConstants.HIKARI_DATASOURCE)) {
			defaultDataSourceCreators.put(DynamicDataSourceCreatorAutoConfiguration.HIKARI_ORDER, new HikariDataSourceCreator(dynamicDataSourceProperties.getHikari()));
		}
		if (ClassLoaderUtil.isPresent(DdConstants.BEECP_DATASOURCE)) {
			defaultDataSourceCreators.put(DynamicDataSourceCreatorAutoConfiguration.BEECP_ORDER, new BeeCpDataSourceCreator(dynamicDataSourceProperties.getBeecp()));
		}
		if (ClassLoaderUtil.isPresent(DdConstants.DBCP2_DATASOURCE)) {
			defaultDataSourceCreators.put(DynamicDataSourceCreatorAutoConfiguration.DBCP2_ORDER, new Dbcp2DataSourceCreator(dynamicDataSourceProperties.getDbcp2()));
		}
		if (ClassLoaderUtil.isPresent(DdConstants.ATOMIKOS_DATASOURCE)) {
			defaultDataSourceCreators.put(DynamicDataSourceCreatorAutoConfiguration.ATOMIKOS_ORDER, new AtomikosDataSourceCreator(dynamicDataSourceProperties.getAtomikos()));
		}
		defaultDataSourceCreators.put(DynamicDataSourceCreatorAutoConfiguration.DEFAULT_ORDER, new BasicDataSourceCreator());

		List<Integer> collect = defaultDataSourceCreators.keySet().stream().sorted().collect(Collectors.toList());
		for (Integer integer : collect) {
			addDataSourceCreator(defaultDataSourceCreators.get(integer));
		}
	}
	/**
	 * 添加数据源创建器
	 * @param dataSourceCreator
	 */
	public void addDataSourceCreator(DataSourceCreator dataSourceCreator){
		if (dataSourceCreator instanceof InitializingBean) {
			try {
				((InitializingBean) dataSourceCreator).afterPropertiesSet();
			} catch (Exception e) {
				throw new BigDatasourceException(e);
			}
		}
		if (creators == null) {
			creators = new ArrayList<>();
		}
		creators.add(dataSourceCreator);

		super.setCreators(this.creators);
	}

	@Override
	public void setCreators(List<DataSourceCreator> creators) {
		for (DataSourceCreator creator : creators) {
			addDataSourceCreator(creator);
		}
	}
}
